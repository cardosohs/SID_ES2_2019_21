-- ---------------------
-- SPs_g21_destino
-- ---------------------

DROP procedure IF EXISTS SP_Escreve_Pedido;

DELIMITER $$

CREATE PROCEDURE SP_Escreve_Pedido (IN rtabela varchar(20), in rinicio datetime, in rfim datetime)

BEGIN

declare EXIT handler for SQLEXCEPTION 
-- begin
	insert into g21destino.log_migracao (datahoramig,tabela, processo, resultado) values (@horaexecucao,rtabela,`request made`,0);
-- END;

set @horaexecucao=now();

drop table if exists pedido;
create temporary table	pedido (
ttabela varchar(20),
tinicio datetime, 
tfim datetime
);

insert into pedido(ttabela,tinicio, tfim) values(rtabela,rinicio, rfim); -- values(tabela,inico,fim); '2019-03-08 23:39:48'
-- select * from pedido;

SET @t1 =CONCAT('select * from pedido INTO OUTFILE ''c:/destino/request_',rtabela,'.csv'''); 

PREPARE stmt3 FROM @t1;
 EXECUTE stmt3;
 -- insere sucesso para a operação de requer atualização
 insert into g21destino.log_migracao (datahoramig,tabela, processo, resultado) values (@horaexecucao,rtabela,'request made',1);
 DEALLOCATE PREPARE stmt3;
drop table pedido;

END$$

DELIMITER ;



DROP procedure IF EXISTS SP_Full_Update;

DELIMITER $$

CREATE PROCEDURE SP_Full_Update(in hinicio datetime, in hfim datetime)

BEGIN
declare tabela varchar(20);
 DECLARE done TINYINT DEFAULT FALSE;
DECLARE cursor1 
 CURSOR FOR
 SELECT table_name FROM information_schema.tables 
 where table_schema='g21destino' and table_name  like 'log_%';
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
 OPEN cursor1;
 
 get_tabelas: LOOP
 
 FETCH NEXT FROM cursor1 INTO tabela;
  IF done THEN 
    LEAVE get_tabelas;
 Else 
	if tabela != 'log_migracao' then 
		Call g21destino.SP_Escreve_Pedido(tabela,hinicio,hfim);
	end if;
 end if;
 
 END Loop;
 
CLOSE cursor1;

END$$

DELIMITER ;




DROP procedure IF EXISTS SP_Full_Clear;

DELIMITER $$

CREATE PROCEDURE SP_Full_Clear()
BEGIN
#SELECT table_name FROM information_schema.tables where table_schema='logdatabase' and table_name  like 'log_%';

declare tabela varchar(20);
 DECLARE done TINYINT DEFAULT FALSE;
DECLARE cursor1 
 CURSOR FOR
 SELECT table_name FROM information_schema.tables 
 where table_schema='g21destino' and table_name  like 'log_%';
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
 OPEN cursor1;
 
 get_tabelas: LOOP
 /*
 
 */
 FETCH NEXT FROM cursor1 INTO tabela;
  IF done THEN 
    LEAVE get_tabelas;
 Else 
 
 SET @t1 =CONCAT('truncate table ',tabela); 
PREPARE stmt3 FROM @t1;
 EXECUTE stmt3;
 DEALLOCATE PREPARE stmt3;
 
 end if;
 
 END Loop;
 
CLOSE cursor1;

END$$

DELIMITER ;




DROP procedure IF EXISTS SP_Escreve_Pedido_Auto;

DELIMITER $$

CREATE PROCEDURE SP_Escreve_Pedido_Auto(IN rtabela varchar(20))

BEGIN

#uso: sp_escreve_pedido_auto (<nome da tabela>);

declare EXIT handler for SQLEXCEPTION 
insert into g21destino.log_migracao (datahoraMig,tabela, processo, resultado) values (@horaexecucao,rtabela,'request made',0);

set @tabelaAlvo=concat("g21destino.",rtabela);
set @q=concat("set @horainicio = (select max(datahoralog) from ", @tabelaAlvo,")" );
prepare stmt from @q;
execute stmt ;
set @horaexecucao=now();

drop table if exists pedido;
create temporary table	pedido (
ttabela varchar(20),
tinicio datetime, 
tfim datetime
);

insert into pedido(ttabela,tinicio, tfim) values(rtabela,@horainicio,@horaexecucao); #values(tabela,inico,fim); '2019-03-08 23:39:48'
select * from pedido;

SET @t1 =CONCAT('select * from pedido INTO OUTFILE \'c:/destino/request_',rtabela,'.csv\''); 

PREPARE stmt3 FROM @t1;
 EXECUTE stmt3;
 #insere sucesso para a operação de requer atualização
 insert into g21destino.log_migracao (datahoraMig,tabela, processo, resultado) values (@horaexecucao,rtabela,'request made',1);
 DEALLOCATE PREPARE stmt3;
drop table pedido;

END$$

DELIMITER ;




DROP procedure IF EXISTS SP_Full_Update_Auto;

DELIMITER $$

CREATE PROCEDURE SP_Full_Update_Auto()

BEGIN

declare tabela varchar(20);
DECLARE done TINYINT DEFAULT FALSE;
DECLARE cursor1 
 CURSOR FOR
 SELECT table_name FROM information_schema.tables 
 where table_schema='g21destino' and table_name  like 'log_%';
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
 OPEN cursor1;
 
 get_tabelas: LOOP
 
 FETCH NEXT FROM cursor1 INTO tabela;
  IF done THEN 
    LEAVE get_tabelas;
 Else 
 Call g21destino.SP_Escreve_Pedido_Auto(tabela);
 end if;
 
 END Loop;
 
CLOSE cursor1;

END$$

DELIMITER ;




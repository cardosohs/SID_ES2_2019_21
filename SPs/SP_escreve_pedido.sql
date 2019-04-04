CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_Escreve_Pedido`(IN rtabela varchar(20), in rinicio datetime, in rfim datetime)
BEGIN

declare EXIT handler for SQLEXCEPTION 
# begin
	insert into g21destino.log_migracao (datahoraMig,tabela, processo, resultado) values (@horaexecucao,rtabela,'request made',0);
# END;

set @horaexecucao=now();

drop table if exists pedido;
create temporary table	pedido (
ttabela varchar(20),
tinicio datetime, 
tfim datetime
);

insert into pedido(ttabela,tinicio, tfim) values(rtabela,rinicio, rfim); #values(tabela,inico,fim); '2019-03-08 23:39:48'
#select * from pedido;

SET @t1 =CONCAT('select * from pedido INTO OUTFILE \'c:/destino/request_',rtabela,'.csv\''); 

PREPARE stmt3 FROM @t1;
 EXECUTE stmt3;
 #insere sucesso para a operação de requer atualização
 insert into g21destino.log_migracao (datahoraMig,tabela, processo, resultado) values (@horaexecucao,rtabela,'request made',1);
 DEALLOCATE PREPARE stmt3;
drop table pedido;

END
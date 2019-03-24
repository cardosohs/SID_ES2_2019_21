CREATE DEFINER=`root`@`localhost` PROCEDURE `FullUpdate`(in hinicio datetime, in hfim datetime )
BEGIN
#SELECT table_name FROM information_schema.tables where table_schema='logdatabase' and table_name  like 'log_%';

declare tabela varchar(20);
 DECLARE done TINYINT DEFAULT FALSE;
DECLARE cursor1 
 CURSOR FOR
 SELECT table_name FROM information_schema.tables 
 where table_schema='logsg21db' and table_name  like 'log_%';
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
 OPEN cursor1;
 
 get_tabelas: LOOP
 
 FETCH NEXT FROM cursor1 INTO tabela;
  IF done THEN 
    LEAVE get_tabelas;
 Else 
 Call logsg21db.escreve_pedido(tabela,hinicio,hfim);
 end if;
 
 END Loop;
 
CLOSE cursor1;

END
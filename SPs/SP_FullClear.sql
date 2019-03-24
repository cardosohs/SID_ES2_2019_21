CREATE DEFINER=`root`@`localhost` PROCEDURE `FullClear`()
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

END
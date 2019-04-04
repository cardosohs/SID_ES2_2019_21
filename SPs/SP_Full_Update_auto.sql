CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_Full_Update_auto`()
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
	Call g21destino.SP_Escreve_Pedido_auto(tabela);
	end if;
 end if;
 
 END Loop;
 
CLOSE cursor1;

END
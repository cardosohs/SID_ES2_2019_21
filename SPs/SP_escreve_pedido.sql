CREATE DEFINER=`root`@`localhost` PROCEDURE `escreve_pedido`(IN rtabela varchar(20), in rinicio datetime, in rfim datetime)
BEGIN
drop table if exists pedido;
create temporary table	pedido (
ttabela varchar(20),
tinicio datetime, 
tfim datetime
);

insert into pedido(ttabela,tinicio, tfim) values(rtabela,rinicio, rfim); #values(tabela,inico,fim); '2019-03-08 23:39:48'
#select * from pedido;

SET @t1 =CONCAT('select * from pedido INTO OUTFILE \'c:/destino/request_',rtabela,'.csv\''); 
#"FIELDS ENCLOSED BY '\"' 
#TERMINATED BY ',' 
#ESCAPED BY '\"' 
#LINES TERMINATED BY \'\r\n\' ");
PREPARE stmt3 FROM @t1;
 EXECUTE stmt3;
 DEALLOCATE PREPARE stmt3;
drop table pedido;

END
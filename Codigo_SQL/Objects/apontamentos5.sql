/*select * from origemg21db.log_administrador;
select * from logsg21db.log_administrador;
#LOAD XML INFILE 'c:/destino/log_administrador.xml'INTO TABLE LogsG21DB.log_administrador;
# truncate origemg21db.log_administrador;
# truncate logsg21db.log_administrador;
insert into	logsg21db.log_administrador values(1,'1@1','1','1','2019-03-23 13:53:03',1,1);
LOAD XML INFILE 'c:/destino/log_administrador.xml'INTO TABLE logsg21db.log_administrador;
#LOAD XML INFILE 'c:/destino/log_administrador.xml'INTO TABLE origemg21db.log_administrador;
insert into origemg21db.administrador(nomeAdmin, email) values ('nomeasd','a@dasd2');
*/
call logsg21db.fullUpdate();
#call logsg21db.AtualizaTabela('log_administrador', '2019-01-01 15:00:01', '2019-04-16 15:57:01');
#call logsg21db.AtualizaTabela('log_cultura', '2019-01-01 15:00:01', '2019-04-16 15:57:01');
#call logsg21db.AtualizaTabela('log_investigador', '2019-01-01 15:00:01', '2019-04-16 15:57:01');
#call logsg21db.AtualizaTabela('log_medicoes', '2019-01-01 15:00:01', '2019-04-16 15:57:01');
#call logsg21db.AtualizaTabela('log_medicoesluz', '2019-01-01 15:00:01', '2019-04-16 15:57:01');
#call logsg21db.AtualizaTabela('log_medicoestemp', '2019-01-01 15:00:01', '2019-04-16 15:57:01');
#call logsg21db.AtualizaTabela('log_sistema', '2019-01-01 15:00:01', '2019-04-16 15:57:01');
#call logsg21db.AtualizaTabela('log_variaveis', '2019-01-01 15:00:01', '2019-04-16 15:57:01');
#call logsg21db.AtualizaTabela('log_variaveismedidas', '2019-01-01 15:00:01', '2019-04-16 15:57:01');
/*
LOAD XML INFILE 'c:/destino/log_investigador.xml' INTO TABLE logsg21db.log_investigador;
LOAD XML INFILE 'c:/destino/log_investigador.xml' INTO TABLE logsg21db.log_investigador;
select * from logsg21db.log_medicoes;
truncate table logsg21db.log_investigador;
*/




#drop table logsg21db.pedido;
#LOAD XML INFILE 'c:/destino/log_administrador.xml'INTO TABLE logsg21db.log_administrador;
#LOAD XML INFILE 'c:/destino/log_cultura.xml' INTO TABLE logsg21db.log_cultura;

/*
mysql.exe -uroot --xml -e "SELECT * FROM OrigemG21DB.log_administrador where dataHoraLog >='2019-01-01 15:00:01' and dataHoraLog<='2019-04-16 15:57:01'" > C:\Origem\log_administrador.xml
mysql.exe -uroot --xml -e "SELECT * FROM OrigemG21DB.log_cultura where dataHoraLog >='2019-01-01 15:00:01' and dataHoraLog<='2019-04-16 15:57:01'" > C:\Origem\log_cultura.xml
"mysql.exe -uroot --xml -e "SELECT * FROM OrigemG21DB.log_administrador where dataHoraLog >='2019-01-01 15:00:01' and dataHoraLog<='2019-04-16 15:57:01'" > C:\Origem\log_administrador.xml"
*/
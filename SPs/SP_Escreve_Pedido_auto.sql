CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_Escreve_Pedido_auto`(IN rtabela varchar(20))
BEGIN
#uso: sp_escreve_pedido_auto (<nome da tabela>);

#declare EXIT handler for SQLEXCEPTION 
insert into g21destino.log_migracao (datahoraMig,tabela, processo, resultado) values (@horaexecucao,rtabela,'request made',0);

set @tabelaAlvo=concat("g21destino.",rtabela);
if @tabelaAlvo != 'log_migracao' then 
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

	insert into pedido(ttabela,tinicio, tfim) values(rtabela,@horainicio,@horaexecucao); 

	SET @t1 =CONCAT('select * from pedido INTO OUTFILE \'c:/destino/request_',rtabela,'.csv\''); 

	PREPARE stmt3 FROM @t1;
	EXECUTE stmt3;
	#insere sucesso para a operação de requer atualização
	insert into g21destino.log_migracao (datahoraMig,tabela, processo, resultado) values (@horaexecucao,rtabela,'request made',1);
	DEALLOCATE PREPARE stmt3;
	drop table pedido;
end if;
END
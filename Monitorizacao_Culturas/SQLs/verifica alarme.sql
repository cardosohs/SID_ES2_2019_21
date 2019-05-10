-- Evento
CREATE 
EVENT `verifica_alarmes`
ON SCHEDULE EVERY 1 minute 
STARTS TIMESTAMP(NOW() + INTERVAL 1 MINUTE) 
DO call sp_verificaAtividade();

-- -------

-- SP 
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_verificaAtividade`()
BEGIN   
-- obtem a ultima medicao de Luz
    set @ultimaLuz= ( select datahoramed
			from medicoesluz
        order by datahoramed desc 
        limit 1);
-- obtem a ultima medicao de temperatura
     set @ultimaTemp = ( select datahoramed
			from medicoestemp
        order by datahoramed desc 
        limit 1);
        
-- verifica se no último minuto chegou alguma medeciao de Luz        
        if ( timediff(@ultimaluz,NOW())>minute(1) )then  
        insert into alertas (DataHora,NomeVariavel, Descricao)
		values (curtime(),"Luminusidade","ausencia de dados");
        end if;
        
-- verifica se no último minuto chegou alguma medeciao de temperatura
        if ( timediff(@ultimatemp,now())>minute(1)) then 
        insert into alertas (DataHora,NomeVariavel, Descricao)
		values (curtime(),"Temperatura","ausencia de dados");
        end if;
        
END
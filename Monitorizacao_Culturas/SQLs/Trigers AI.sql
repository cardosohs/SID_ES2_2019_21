-- verifica medicoes de luz

CREATE DEFINER=`root`@`localhost` TRIGGER `g21origem`.`medicoestemp_AFTER_INSERT` AFTER INSERT ON `medicoestemp` FOR EACH ROW
BEGIN
	DECLARE LimiteInf Double;
	DECLARE LimiteSup Double;
-- obtem os valores da tabela sistema
set limiteinf = (select limiteinferiorTemp from sistema limit 1);
set limitesup = (select limiteSuperiorTemp from sistema limit 1);
-- verifica se o novo valor ultrapassa algum

	if( new.ValorMedicaoTemp > limitesup ) then 
		  insert into alertas (DataHora,NomeVariavel, LimiteInferior, LimiteSuperior, ValorMedicao,Descricao)
			values (new.DataHoraMed,"Temperatura",LimiteInf,LimiteSup, new.ValorMedicaoTemp,"Acima do limite");
	end if;
    
	if( new.ValorMedicaoTemp < limiteinf ) then 
      insert into alertas (DataHora,NomeVariavel, LimiteInferior, LimiteSuperior, ValorMedicao,Descricao)
		values (new.DataHoraMed,"Temperatura",LimiteInf,LimiteSup, new.ValorMedicaoTemp,"Abaixo do limite");
	end if;
END

-- -- fim

-- -- verifica alarmes nas medicoes de temperatura
CREATE DEFINER=`root`@`localhost` TRIGGER `g21origem`.`medicoestemp_AFTER_INSERT` AFTER INSERT ON `medicoestemp` FOR EACH ROW
BEGIN
	DECLARE LimiteInf Double;
	DECLARE LimiteSup Double;
-- obtem os valores da tabela sistema
set limiteinf = (select limiteinferiorTemp from sistema limit 1);
set limitesup = (select limiteSuperiorTemp from sistema limit 1);
-- verifica se o novo valor ultrapassa algum

	if( new.ValorMedicaoTemp > limitesup ) then 
		  insert into alertas (DataHora,NomeVariavel, LimiteInferior, LimiteSuperior, ValorMedicao,Descricao)
			values (new.DataHoraMed,"Temperatura",LimiteInf,LimiteSup, new.ValorMedicaoTemp,"Acima do limite");
	end if;
    
	if( new.ValorMedicaoTemp < limiteinf ) then 
      insert into alertas (DataHora,NomeVariavel, LimiteInferior, LimiteSuperior, ValorMedicao,Descricao)
		values (new.DataHoraMed,"Temperatura",LimiteInf,LimiteSup, new.ValorMedicaoTemp,"Abaixo do limite");
	end if;
	


END

-- fim  
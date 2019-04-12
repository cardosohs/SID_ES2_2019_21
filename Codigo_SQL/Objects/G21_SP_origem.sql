/************** Comando para adicionar coluna id na tabela mysql.user
*
*
*
*Alter table mysql.user ADD COLUMN id int not null;
*
*
*
*
**************/
-- ----------------------------------
-- SPs_g21origem
-- ----------------------------------

DROP PROCEDURE IF EXiSTS SP_CriaUtilizador;

DELIMITER $$
create procedure SP_CriaUtilizador(in InNome varchar(500), InPassword varchar(500),in InEmail varchar(500),IncategoriaProfe varchar(500),InTipo varchar(1))
begin
	
	Declare ExisteAdmin varchar(50);
	Declare ExisteInvest varchar(50);	
	Declare sqlquery varchar(100);
	
	select a.email into ExisteAdmin from administrador a where a.email= InEmail;
	select i.email into ExisteInvest from investigador i where i.email= InEmail;
	
	IF InTipo = 'A' or Intipo = "I" Then
		IF ExisteAdmin is Null and ExisteInvest is null then			
			set @sqlquery= CONCAT("CREATE USER ","'",InEmail,"'","@","'localhost'","IDENTIFIED BY ","'",InPassword,"'");
			PREPARE stmt FROM @sqlquery;
			EXECUTE stmt;
		
			
			IF Intipo = "A" Then
				insert into administrador(IdAdmin,Email,NomeAdmin) values (null,InEmail,InNome);
				set @sqlqueryAdministrador_1= CONCAT("GRANT ", "Administrador TO '",InEmail,"'@'localhost'");
				PREPARE stmtAdministrador_1 FROM @sqlqueryAdministrador_1;
				EXECUTE stmtAdministrador_1;
				set @sqlqueryAdministrador_2= CONCAT("SET DEFAULT ROLE Administrador FOR '",InEmail,"'@'localhost'");
				PREPARE stmtAdministrador_2 FROM @sqlqueryAdministrador_2;
				EXECUTE stmtAdministrador_2;
			Else
				insert into Investigador values ( null,InEmail,InNome, InCategoriaProfe);
				set @sqlqueryInvestigador_1= CONCAT("GRANT ", "Investigador TO '",InEmail,"'@'localhost'");
				PREPARE stmtInvestigador_1 FROM @sqlqueryInvestigador_1;
				EXECUTE stmtInvestigador_1;
				set @sqlqueryInvestigador_2= CONCAT("SET DEFAULT ROLE Investigador FOR '",InEmail,"'@'localhost'");
				PREPARE stmtInvestigador_2 FROM @sqlqueryInvestigador_2;
				EXECUTE stmtInvestigador_2;				
			END IF;
		ELSE
 			signal sqlstate '45000' SET MESSAGE_TEXT ='ERRO, esse email ja esta registado';
		END IF;
	ELSE
 		signal sqlstate '45000' SET MESSAGE_TEXT ='ERRO, Tipo de utilizador desconhecido';
	END IF;
	
end $$



-- create procedure SP_ConsultaMedicao(in CulturaId int,in DataIn varchar(100))
DELIMITER +
create procedure SP_ConsultaMedicao()
begin

	SELECT cultura.NomeCultura,variaveis.NomeVariavel, medicoes.DataHoraMed, medicoes.ValorMed 
	FROM medicoes 
	INNER JOIN variaveismedidas ON variaveismedidas.IdVarMed=medicoes.IdVarMed 
	INNER JOIN cultura ON cultura.IdCultura=variaveismedidas.IdCultura 
	INNER JOIN variaveis ON variaveis.IdVariavel = variaveismedidas.IdVariavel 
	INNER JOIN investigador on investigador.IdInvestigador = cultura.IdInvestigador 
	where CURRENT_USER = Concat(investigador.Email,'@localhost');

end+
DELIMITER ;

-- Roles 

CREATE ROLE 'Administrador', 'Investigador', 'SensorLuz', 'SensorTemp', 'Migrador';

GRANT SELECT, INSERT, DELETE ON g21origem.variaveis TO 'Administrador';
GRANT SELECT, UPDATE, DELETE ON g21origem.investigador TO 'Administrador';
GRANT SELECT, INSERT, UPDATE, DELETE ON g21origem.sistema TO 'Administrador';
GRANT SELECT, UPDATE, DELETE ON g21origem.administrador TO 'Administrador';
GRANT EXECUTE ON PROCEDURE g21origem.SP_CriaUtilizador TO 'Administrador';

GRANT SELECT ON g21origem.variaveis TO 'Investigador';
GRANT SELECT, INSERT, UPDATE, DELETE ON g21origem.cultura TO 'Investigador';
GRANT SELECT, INSERT, UPDATE, DELETE ON g21origem.variaveismedidas TO 'Investigador';
GRANT SELECT, INSERT, UPDATE, DELETE ON g21origem.medicoes TO 'Investigador';
GRANT SELECT ON g21origem.medicoesTemp TO 'Investigador';
GRANT SELECT ON g21origem.medicoesLuz TO 'Investigador';
GRANT SELECT ON g21origem.sistema TO 'Investigador';

GRANT INSERT ON g21origem.medicoesLuz TO 'SensorLuz';
GRANT INSERT ON g21origem.medicoesTemp TO 'SensorTemp';

GRANT SELECT ON g21origem.log_variaveis TO 'Migrador';
GRANT SELECT ON g21origem.log_cultura TO 'Migrador';
GRANT SELECT ON g21origem.log_investigador TO 'Migrador';
GRANT SELECT ON g21origem.log_administrador TO 'Migrador';
GRANT SELECT ON g21origem.log_variaveismedidas TO 'Migrador';
GRANT SELECT ON g21origem.log_medicoes TO 'Migrador';
GRANT SELECT ON g21origem.log_medicoesluz TO 'Migrador';
GRANT SELECT ON g21origem.log_medicoestemp TO 'Migrador';
GRANT SELECT ON g21origem.log_sistema TO 'Migrador';
GRANT SELECT ON g21origem.log_medicoesselect TO 'Migrador';

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema g21origem
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema g21origem
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `g21origem` DEFAULT CHARACTER SET utf8 ;
USE `g21origem` ;

-- -----------------------------------------------------
-- Table `g21origem`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`Administrador` (
  `IdAdmin` INT NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(50) NOT NULL,
  `NomeAdmin` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`IdAdmin`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC));


-- -----------------------------------------------------
-- Table `g21origem`.`Investigador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`Investigador` (
  `IdInvestigador` INT NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(50) NOT NULL,
  `NomeInvestigador` VARCHAR(100) NOT NULL,
  `CategoriaProfe` VARCHAR(300) NULL DEFAULT NULL,
  PRIMARY KEY (`IdInvestigador`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC));


-- -----------------------------------------------------
-- Table `g21origem`.`Cultura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`Cultura` (
  `IdCultura` INT NOT NULL AUTO_INCREMENT,
  `IdInvestigador` INT NOT NULL,
  `NomeCultura` VARCHAR(100) NOT NULL,
  `DescricaoCultura` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`IdCultura`),
  INDEX `FK_CULTURA_RESPONSAV_INVESTIG` (`IdInvestigador` ASC),
  UNIQUE INDEX `NomeCultura_UNIQUE` (`NomeCultura` ASC),
  CONSTRAINT `FK_CULTURA_RESPONSAV_INVESTIG`
    FOREIGN KEY (`IdInvestigador`)
    REFERENCES `g21origem`.`Investigador` (`IdInvestigador`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `g21origem`.`Variaveis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`Variaveis` (
  `IdVariavel` INT NOT NULL AUTO_INCREMENT,
  `NomeVariavel` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`IdVariavel`),
  UNIQUE INDEX `NomeVariavel_UNIQUE` (`NomeVariavel` ASC));


-- -----------------------------------------------------
-- Table `g21origem`.`VariaveisMedidas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`VariaveisMedidas` (
  `IdVarMed` INT NOT NULL AUTO_INCREMENT,
  `IdCultura` INT NOT NULL,
  `IdVariavel` INT NOT NULL,
  `LimiteInferior` DECIMAL(8,2) NOT NULL,
  `LimiteSuperior` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`IdVarMed`),
  INDEX `FK_VARIAVEI_ASSOCIATI_CULTURA` (`IdCultura` ASC),
  INDEX `FK_VARIAVEI_ASSOCIATI_VARIAVEI` (`IdVariavel` ASC),
  CONSTRAINT `FK_VARIAVEI_ASSOCIATI_CULTURA`
    FOREIGN KEY (`IdCultura`)
    REFERENCES `g21origem`.`Cultura` (`IdCultura`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_VARIAVEI_ASSOCIATI_VARIAVEI`
    FOREIGN KEY (`IdVariavel`)
    REFERENCES `g21origem`.`Variaveis` (`IdVariavel`)
    ON DELETE RESTRICT
    ON UPDATE restrict);


-- -----------------------------------------------------
-- Table `g21origem`.`Medicoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`Medicoes` (
  `IdMed` INT NOT NULL AUTO_INCREMENT,
  `IdVarMed` INT NOT NULL,
  `DataHoraMed` TIMESTAMP NOT NULL,
  `ValorMed` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`IdMed`),
  INDEX `FK_MEDICOES_ASSOCIATI_VARIAVEI` (`IdVarMed` ASC),
  CONSTRAINT `FK_MEDICOES_ASSOCIATI_VARIAVEI`
    FOREIGN KEY (`IdVarMed`)
    REFERENCES `g21origem`.`VariaveisMedidas` (`IdVarMed`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT);


-- -----------------------------------------------------
-- Table `g21origem`.`MedicoesLuz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`MedicoesLuz` (
  `IdMedicao` INT NOT NULL AUTO_INCREMENT,
  `ValorMedicaoLuz` DECIMAL(8,2) NOT NULL,
  `DataHoraMed` TIMESTAMP NOT NULL,
  PRIMARY KEY (`IdMedicao`));


-- -----------------------------------------------------
-- Table `g21origem`.`MedicoesTemp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`MedicoesTemp` (
  `IdMedicao` INT NOT NULL AUTO_INCREMENT,
  `DataHoraMed` TIMESTAMP NOT NULL,
  `ValorMedicaoTemp` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`IdMedicao`));


-- -----------------------------------------------------
-- Table `g21origem`.`Sistema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`Sistema` (
  `IdSistema` INT NOT NULL AUTO_INCREMENT,
  `LimiteInferiorTemp` DECIMAL(8,2) NOT NULL,
  `LimiteSuperiorTemp` DECIMAL(8,2) NOT NULL,
  `LimiteInferiorLuz` DECIMAL(8,2) NOT NULL,
  `LimiteSuperiorLuz` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`IdSistema`));


-- -----------------------------------------------------
-- Table `g21origem`.`log_medicoesselect`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`log_medicoesselect` (
  `IdLog` INT NOT NULL AUTO_INCREMENT,
  `DataHoraLog` TIMESTAMP NULL,
  `Autor` VARCHAR(50) NULL,
  `IntrucaoSel` TEXT NULL,
  PRIMARY KEY (`IdLog`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `g21origem`.`log_investigador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`log_investigador` (
  `IdInvestigador` INT NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `NomeInvestigador` VARCHAR(100) NOT NULL,
  `CategoriaProfe` VARCHAR(300) NULL DEFAULT NULL,
  `IdLog` INT NOT NULL AUTO_INCREMENT,
  `DataHoraLog` TIMESTAMP NULL,
  `Operacao` VARCHAR(1) NULL,
  `Autor` VARCHAR(50) NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21origem`.`log_sistema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`log_sistema` (
  `IdSistema` INT NOT NULL,
  `LimiteInferiorTemp` DECIMAL(8,2) NOT NULL,
  `LimiteSuperiorTemp` DECIMAL(8,2) NOT NULL,
  `LimiteInferiorLuz` DECIMAL(8,2) NOT NULL,
  `LimiteSuperiorLuz` DECIMAL(8,2) NOT NULL,
  `IdLog` INT NOT NULL AUTO_INCREMENT,
  `DataHoraLog` TIMESTAMP NULL,
  `Operacao` VARCHAR(1) NULL,
  `Autor` VARCHAR(50) NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21origem`.`log_medicoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`log_medicoes` (
  `IdMed` INT NOT NULL,
  `IdVarMed` INT NOT NULL,
  `DataHoraMed` TIMESTAMP NOT NULL,
  `ValorMed` DECIMAL(8,2) NOT NULL,
  `IdLog` INT NOT NULL AUTO_INCREMENT,
  `DataHoraLog` TIMESTAMP NULL,
  `Operacao` VARCHAR(1) NULL,
  `Autor` VARCHAR(50) NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21origem`.`log_cultura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`log_cultura` (
  `IdCultura` INT NOT NULL,
  `IdInvestigador` INT NOT NULL,
  `NomeCultura` VARCHAR(100) NOT NULL,
  `DescricaoCultura` TEXT NULL DEFAULT NULL,
  `IdLog` INT NOT NULL AUTO_INCREMENT,
  `DataHoraLog` TIMESTAMP NULL,
  `Operacao` VARCHAR(1) NULL,
  `Autor` VARCHAR(50) NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21origem`.`log_medicoesluz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`log_medicoesluz` (
  `IdMedicao` INT NOT NULL,
  `ValorMedicaoLuz` DECIMAL(8,2) NOT NULL,
  `DataHoraMed` TIMESTAMP NOT NULL,
  `IdLog` INT NOT NULL AUTO_INCREMENT,
  `DataHoraLog` TIMESTAMP NULL,
  `Operacao` VARCHAR(1) NULL,
  `Autor` VARCHAR(50) NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21origem`.`log_administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`log_administrador` (
  `IdAdmin` INT NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `NomeAdmin` VARCHAR(100) NOT NULL,
  `IdLog` INT NOT NULL AUTO_INCREMENT,
  `DataHoraLog` TIMESTAMP NULL,
  `Operacao` VARCHAR(1) NULL,
  `Autor` VARCHAR(50) NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21origem`.`log_variaveismedidas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`log_variaveismedidas` (
  `IdVarMed` INT NOT NULL,
  `IdCultura` INT NOT NULL,
  `IdVariavel` INT NOT NULL,
  `LimiteInferior` DECIMAL(8,2) NOT NULL,
  `LimiteSuperior` DECIMAL(8,2) NOT NULL,
  `IdLog` INT NOT NULL AUTO_INCREMENT,
  `DataHoraLog` TIMESTAMP NULL,
  `Operacao` VARCHAR(1) NULL,
  `Autor` VARCHAR(50) NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21origem`.`log_variaveis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`log_variaveis` (
  `IdVariavel` INT NOT NULL,
  `NomeVariavel` VARCHAR(100) NOT NULL,
  `IdLog` INT NOT NULL AUTO_INCREMENT,
  `DataHoraLog` TIMESTAMP NULL,
  `Operacao` VARCHAR(1) NULL,
  `Autor` VARCHAR(50) NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21origem`.`log_medicoestemp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21origem`.`log_medicoestemp` (
  `IdMedicao` INT NOT NULL,
  `DataHoraMed` TIMESTAMP NOT NULL,
  `ValorMedicaoTemp` DECIMAL(8,2) NOT NULL,
  `IdLog` INT NOT NULL AUTO_INCREMENT,
  `DataHoraLog` TIMESTAMP NULL,
  `Operacao` VARCHAR(1) NULL,
  `Autor` VARCHAR(50) NULL,
  PRIMARY KEY (`IdLog`));

USE `g21origem`;

DELIMITER $$
USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Administrador_AFTER_INSERT` AFTER INSERT ON `Administrador` FOR EACH ROW
BEGIN
	insert into log_administrador(IdAdmin,Email,NomeAdmin,DataHoraLog,Operacao,Autor) 
	values (new.idadmin,new.email,new.nomeadmin,CURRENT_TIMESTAMP,'I',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Administrador_AFTER_UPDATE` AFTER UPDATE ON `Administrador` FOR EACH ROW
BEGIN
	insert into log_administrador(IdAdmin,Email,NomeAdmin,DataHoraLog,Operacao,Autor) 
	values (new.idadmin,new.email,new.nomeadmin,CURRENT_TIMESTAMP,'U',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Administrador_AFTER_DELETE` AFTER DELETE ON `Administrador` FOR EACH ROW
BEGIN
		insert into log_administrador(IdAdmin,Email,NomeAdmin,DataHoraLog,Operacao,Autor) 
	values (old.idadmin,old.email,old.nomeadmin,CURRENT_TIMESTAMP,'D',user());

END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Investigador_AFTER_INSERT` AFTER INSERT ON `Investigador` FOR EACH ROW
BEGIN
	insert into log_investigador(IdInvestigador,Email,NomeInvestigador,CategoriaProfe,DataHoraLog,Operacao,Autor) 
	values (new.idinvestigador,new.email,new.nomeinvestigador,new.categoriaprofe,CURRENT_TIMESTAMP,'I',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Investigador_AFTER_UPDATE` AFTER UPDATE ON `Investigador` FOR EACH ROW
BEGIN	
	insert into log_investigador(IdInvestigador,Email,NomeInvestigador,CategoriaProfe,DataHoraLog,Operacao,Autor) 
	values (new.idinvestigador,new.email,new.nomeinvestigador,new.categoriaprofe,CURRENT_TIMESTAMP,'U',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Investigador_AFTER_DELETE` AFTER DELETE ON `Investigador` FOR EACH ROW
BEGIN
	insert into log_investigador(IdInvestigador,Email,NomeInvestigador,CategoriaProfe,DataHoraLog,Operacao,Autor) 
	values (old.idinvestigador,old.email,old.nomeinvestigador,old.categoriaprofe,CURRENT_TIMESTAMP,'D',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Cultura_AFTER_INSERT` AFTER INSERT ON `Cultura` FOR EACH ROW
BEGIN
	insert into log_cultura(IdCultura,IdInvestigador,NomeCultura,DescricaoCultura,DataHoraLog,Operacao,Autor) 
	values (new.idcultura,new.idinvestigador,new.nomecultura,new.descricaocultura,CURRENT_TIMESTAMP,'I',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Cultura_AFTER_UPDATE` AFTER UPDATE ON `Cultura` FOR EACH ROW
BEGIN
	insert into log_cultura(IdCultura,IdInvestigador,NomeCultura,DescricaoCultura,DataHoraLog,Operacao,Autor) 
	values (new.idcultura,new.idinvestigador,new.nomecultura,new.descricaocultura,CURRENT_TIMESTAMP,'U',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Cultura_AFTER_DELETE` AFTER DELETE ON `Cultura` FOR EACH ROW
BEGIN
	insert into log_cultura(IdCultura,IdInvestigador,NomeCultura,DescricaoCultura,DataHoraLog,Operacao,Autor) 
	values (old.idcultura,old.idinvestigador,old.nomecultura,old.descricaocultura,CURRENT_TIMESTAMP,'D',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Variaveis_AFTER_INSERT` AFTER INSERT ON `Variaveis` FOR EACH ROW
BEGIN
	insert into log_variaveis(IdVariavel,NomeVariavel,DataHoraLog,Operacao,Autor) 
	values (new.idvariavel,new.nomevariavel,CURRENT_TIMESTAMP,'I',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Variaveis_AFTER_UPDATE` AFTER UPDATE ON `Variaveis` FOR EACH ROW
BEGIN
	insert into log_variaveis(IdVariavel,NomeVariavel,DataHoraLog,Operacao,Autor) 
	values (new.idvariavel,new.nomevariavel,CURRENT_TIMESTAMP,'U',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Variaveis_AFTER_DELETE` AFTER DELETE ON `Variaveis` FOR EACH ROW
BEGIN
	insert into log_variaveis(IdVariavel,NomeVariavel,DataHoraLog,Operacao,Autor) 
	values (old.idvariavel,old.nomevariavel,CURRENT_TIMESTAMP,'D',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`VariaveisMedidas_AFTER_INSERT` AFTER INSERT ON `VariaveisMedidas` FOR EACH ROW
BEGIN
	insert into log_variaveismedidas(IdVarMed,IdCultura,IdVariavel,LimiteInferior,LimiteSuperior,DataHoraLog,Operacao,Autor) 
	values (new.idvarmed,new.idcultura,new.idvariavel,new.limiteinferior,new.limitesuperior,CURRENT_TIMESTAMP,'I',user());

END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`VariaveisMedidas_AFTER_UPDATE` AFTER UPDATE ON `VariaveisMedidas` FOR EACH ROW
BEGIN
	insert into log_variaveismedidas(IdVarMed,IdCultura,IdVariavel,LimiteInferior,LimiteSuperior,DataHoraLog,Operacao,Autor) 
	values (new.idvarmed,new.idcultura,new.idvariavel,new.limiteinferior,new.limitesuperior,CURRENT_TIMESTAMP,'U',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`VariaveisMedidas_AFTER_DELETE` AFTER DELETE ON `VariaveisMedidas` FOR EACH ROW
BEGIN
	insert into log_variaveismedidas(IdVarMed,IdCultura,IdVariavel,LimiteInferior,LimiteSuperior,DataHoraLog,Operacao,Autor) 
	values (old.idvarmed,old.idcultura,old.idvariavel,old.limiteinferior,old.limitesuperior,CURRENT_TIMESTAMP,'D',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Medicoes_AFTER_INSERT` AFTER INSERT ON `Medicoes` FOR EACH ROW
BEGIN
	insert into log_medicoes(IdMed,IdVarMed,DatahoraMed,ValorMed,DataHoraLog,Operacao,Autor) 
	values (new.idmed,new.idvarmed,new.datahoramed,new.valormed,CURRENT_TIMESTAMP,'I',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Medicoes_AFTER_UPDATE` AFTER UPDATE ON `Medicoes` FOR EACH ROW
BEGIN
	insert into log_medicoes(IdMed,IdVarMed,DatahoraMed,ValorMed,DataHoraLog,Operacao,Autor) 
	values (new.idmed,new.idvarmed,new.datahoramed,new.valormed,CURRENT_TIMESTAMP,'U',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Medicoes_AFTER_DELETE` AFTER DELETE ON `Medicoes` FOR EACH ROW
BEGIN
	insert into log_medicoes(IdMed,IdVarMed,DatahoraMed,ValorMed,DataHoraLog,Operacao,Autor) 
	values (old.idmed,old.idvarmed,old.datahoramed,old.valormed,CURRENT_TIMESTAMP,'D',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`MedicoesLuz_AFTER_INSERT` AFTER INSERT ON `MedicoesLuz` FOR EACH ROW
BEGIN
	insert into log_medicoesluz(IdMedicao,ValorMedicaoLuz,DataHoraMed,DataHoraLog,Operacao,Autor) 
	values (new.idmedicao,new.valormedicaoluz,new.datahoramed,CURRENT_TIMESTAMP,'I',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`MedicoesLuz_AFTER_UPDATE` AFTER UPDATE ON `MedicoesLuz` FOR EACH ROW
BEGIN
	insert into log_medicoesluz(IdMedicao,ValorMedicaoLuz,DataHoraMed,DataHoraLog,Operacao,Autor) 
	values (new.idmedicao,new.valormedicaoluz,new.datahoramed,CURRENT_TIMESTAMP,'U',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`MedicoesLuz_AFTER_DELETE` AFTER DELETE ON `MedicoesLuz` FOR EACH ROW
BEGIN
	insert into log_medicoesluz(IdMedicao,ValorMedicaoLuz,DataHoraMed,DataHoraLog,Operacao,Autor) 
	values (old.idmedicao,old.valormedicaoluz,old.datahoramed,CURRENT_TIMESTAMP,'D',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`MedicoesTemp_AFTER_INSERT` AFTER INSERT ON `MedicoesTemp` FOR EACH ROW
BEGIN
	insert into log_medicoestemp(IdMedicao,DataHoraMed,ValorMedicaoTemp,DataHoraLog,Operacao,Autor) 
	values (new.idmedicao,new.datahoramed,new.valormedicaotemp,CURRENT_TIMESTAMP,'I',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`MedicoesTemp_AFTER_UPDATE` AFTER UPDATE ON `MedicoesTemp` FOR EACH ROW
BEGIN
	insert into log_medicoestemp(IdMedicao,DataHoraMed,ValorMedicaoTemp,DataHoraLog,Operacao,Autor) 
	values (new.idmedicao,new.datahoramed,new.valormedicaotemp,CURRENT_TIMESTAMP,'U',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`MedicoesTemp_AFTER_DELETE` AFTER DELETE ON `MedicoesTemp` FOR EACH ROW
BEGIN
	insert into log_medicoestemp(IdMedicao,DataHoraMed,ValorMedicaoTemp,DataHoraLog,Operacao,Autor) 
	values (old.idmedicao,old.datahoramed,old.valormedicaotemp,CURRENT_TIMESTAMP,'D',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Sistema_AFTER_INSERT` AFTER INSERT ON `Sistema` FOR EACH ROW
BEGIN
	insert into log_sistema(IdSistema,LimiteInferiorTemp,LimiteSuperiorTemp,LimiteInferiorLuz,LimiteSuperiorLuz,DataHoraLog,Operacao,Autor) 
	values (new.idsistema,new.limiteinferiortemp,new.limitesuperiortemp,new.limiteinferiorluz,new.limitesuperiorluz,CURRENT_TIMESTAMP,'I',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Sistema_AFTER_UPDATE` AFTER UPDATE ON `Sistema` FOR EACH ROW
BEGIN
	insert into log_sistema(IdSistema,LimiteInferiorTemp,LimiteSuperiorTemp,LimiteInferiorLuz,LimiteSuperiorLuz,DataHoraLog,Operacao,Autor) 
	values (new.idsistema,new.limiteinferiortemp,new.limitesuperiortemp,new.limiteinferiorluz,new.limitesuperiorluz,CURRENT_TIMESTAMP,'U',user());
END$$

USE `g21origem`$$
CREATE DEFINER = CURRENT_USER TRIGGER `g21origem`.`Sistema_AFTER_DELETE` AFTER DELETE ON `Sistema` FOR EACH ROW
BEGIN
	insert into log_sistema(IdSistema,LimiteInferiorTemp,LimiteSuperiorTemp,LimiteInferiorLuz,LimiteSuperiorLuz,DataHoraLog,Operacao,Autor) 
	values (old.idsistema,old.limiteinferiortemp,old.limitesuperiortemp,old.limiteinferiorluz,old.limitesuperiorluz,CURRENT_TIMESTAMP,'D',user());
END$$



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
DROP ROLE IF EXISTS 'Administrador', 'Investigador', 'SensorLuz', 'SensorTemp', 'Migrador';
CREATE ROLE IF NOT EXISTS 'Administrador', 'Investigador', 'SensorLuz', 'SensorTemp', 'Migrador';

GRANT SELECT, INSERT, DELETE ON g21origem.variaveis TO 'Administrador';
GRANT SELECT, UPDATE, DELETE ON g21origem.investigador TO 'Administrador';
GRANT SELECT, INSERT, UPDATE, DELETE ON g21origem.sistema TO 'Administrador';
GRANT SELECT, UPDATE, DELETE ON g21origem.administrador TO 'Administrador';
GRANT EXECUTE ON PROCEDURE g21origem.SP_CriaUtilizador TO 'Administrador';
GRANT EXECUTE ON PROCEDURE g21origem.SP_ConsultaMedicao TO 'Investigador';

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



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

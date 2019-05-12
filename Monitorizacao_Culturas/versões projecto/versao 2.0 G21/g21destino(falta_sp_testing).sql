-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema g21destino
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema g21destino
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `g21destino` DEFAULT CHARACTER SET utf8 ;
USE `g21destino` ;

-- -----------------------------------------------------
-- Table `g21destino`.`log_medicoesselect`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21destino`.`log_medicoesselect` (
  `IdLog` INT NOT NULL,
  `DataHoraLog` TIMESTAMP NOT NULL,
  `Autor` VARCHAR(50) NOT NULL,
  `IntrucaoSel` TEXT NOT NULL,
  PRIMARY KEY (`IdLog`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `g21destino`.`log_investigador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21destino`.`log_investigador` (
  `IdInvestigador` INT NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `NomeInvestigador` VARCHAR(100) NOT NULL,
  `CategoriaProfe` VARCHAR(300) NULL DEFAULT NULL,
  `IdLog` INT NOT NULL,
  `DataHoraLog` TIMESTAMP NOT NULL,
  `Operacao` VARCHAR(1) NOT NULL,
  `Autor` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21destino`.`log_sistema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21destino`.`log_sistema` (
  `IdSistema` INT NOT NULL,
  `LimiteInferiorTemp` DECIMAL(8,2) NOT NULL,
  `LimiteSuperiorTemp` DECIMAL(8,2) NOT NULL,
  `LimiteInferiorLuz` DECIMAL(8,2) NOT NULL,
  `LimiteSuperiorLuz` DECIMAL(8,2) NOT NULL,
  `IdLog` INT NOT NULL,
  `DataHoraLog` TIMESTAMP NOT NULL,
  `Operacao` VARCHAR(1) NOT NULL,
  `Autor` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21destino`.`log_medicoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21destino`.`log_medicoes` (
  `IdMed` INT NOT NULL,
  `IdVarMed` INT NOT NULL,
  `DataHoraMed` TIMESTAMP NOT NULL,
  `ValorMed` DECIMAL(8,2) NOT NULL,
  `IdLog` INT NOT NULL,
  `DataHoraLog` TIMESTAMP NOT NULL,
  `Operacao` VARCHAR(1) NOT NULL,
  `Autor` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21destino`.`log_cultura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21destino`.`log_cultura` (
  `IdCultura` INT NOT NULL,
  `IdInvestigador` INT NOT NULL,
  `NomeCultura` VARCHAR(100) NOT NULL,
  `DescricaoCultura` TEXT NULL DEFAULT NULL,
  `IdLog` INT NOT NULL,
  `DataHoraLog` TIMESTAMP NOT NULL,
  `Operacao` VARCHAR(1) NOT NULL,
  `Autor` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21destino`.`log_medicoesluz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21destino`.`log_medicoesluz` (
  `IdMedicao` INT NOT NULL,
  `ValorMedicaoLuz` DECIMAL(8,2) NOT NULL,
  `DataHoraMed` TIMESTAMP NOT NULL,
  `IdLog` INT NOT NULL,
  `DataHoraLog` TIMESTAMP NOT NULL,
  `Operacao` VARCHAR(1) NOT NULL,
  `Autor` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21destino`.`log_administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21destino`.`log_administrador` (
  `IdAdmin` INT NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `NomeAdmin` VARCHAR(100) NOT NULL,
  `IdLog` INT NOT NULL,
  `DataHoraLog` TIMESTAMP NOT NULL,
  `Operacao` VARCHAR(1) NOT NULL,
  `Autor` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21destino`.`log_variaveismedidas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21destino`.`log_variaveismedidas` (
  `IdVarMed` INT NOT NULL,
  `IdCultura` INT NOT NULL,
  `IdVariavel` INT NOT NULL,
  `LimiteInferior` DECIMAL(8,2) NOT NULL,
  `LimiteSuperior` DECIMAL(8,2) NOT NULL,
  `IdLog` INT NOT NULL,
  `DataHoraLog` TIMESTAMP NOT NULL,
  `Operacao` VARCHAR(1) NOT NULL,
  `Autor` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21destino`.`log_variaveis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21destino`.`log_variaveis` (
  `IdVariavel` INT NOT NULL,
  `NomeVariavel` VARCHAR(100) NOT NULL,
  `IdLog` INT NOT NULL,
  `DataHoraLog` TIMESTAMP NOT NULL,
  `Operacao` VARCHAR(1) NOT NULL,
  `Autor` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdLog`));


-- -----------------------------------------------------
-- Table `g21destino`.`log_medicoestemp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21destino`.`log_medicoestemp` (
  `IdMedicao` INT NOT NULL,
  `DataHoraMed` TIMESTAMP NOT NULL,
  `ValorMedicaoTemp` DECIMAL(8,2) NOT NULL,
  `IdLog` INT NOT NULL,
  `DataHoraLog` TIMESTAMP NOT NULL,
  `Operacao` VARCHAR(1) NOT NULL,
  `Autor` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdLog`));

-- -----------------------------------------------------
-- Table `g21destino`.`log_migracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `g21destino`.`log_migracao` (
  `IdMigracao` INT NOT NULL AUTO_INCREMENT,
  `DataHoraMig` TIMESTAMP NOT NULL,
  `Tabela` VARCHAR(100) NULL,
  `Processo` VARCHAR(20) NULL,
  `Resultado` BIT(0) NULL,
  PRIMARY KEY (`IdMigracao`))
ENGINE = InnoDB;


-- ---------------------
-- SPs_g21_destino
-- ---------------------

DROP procedure IF EXISTS SP_Escreve_Pedido;

DELIMITER $$

CREATE PROCEDURE SP_Escreve_Pedido (IN rtabela varchar(20), in rinicio datetime, in rfim datetime)

BEGIN

declare EXIT handler for SQLEXCEPTION 
-- begin
	insert into g21destino.log_migracao (datahoramig,tabela, processo, resultado) values (@horaexecucao,rtabela,`request made`,0);
-- END;

set @horaexecucao=now();

drop table if exists pedido;
create temporary table	pedido (
ttabela varchar(20),
tinicio datetime, 
tfim datetime
);

insert into pedido(ttabela,tinicio, tfim) values(rtabela,rinicio, rfim); -- values(tabela,inico,fim); '2019-03-08 23:39:48'
-- select * from pedido;

SET @t1 =CONCAT('select * from pedido INTO OUTFILE ''c:/destino/request_',rtabela,'.csv'''); 

PREPARE stmt3 FROM @t1;
 EXECUTE stmt3;
 -- insere sucesso para a operação de requer atualização
 insert into g21destino.log_migracao (datahoramig,tabela, processo, resultado) values (@horaexecucao,rtabela,'request made',1);
 DEALLOCATE PREPARE stmt3;
drop table pedido;

END$$

DELIMITER ;



DROP procedure IF EXISTS SP_Full_Update;

DELIMITER $$

CREATE PROCEDURE SP_Full_Update(in hinicio datetime, in hfim datetime )

BEGIN
#SELECT table_name FROM information_schema.tables where table_schema='logdatabase' and table_name  like 'log_%';

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
 Call logsg21db.SP_Escreve_Pedido(tabela,hinicio,hfim);
 end if;
 
 END Loop;
 
CLOSE cursor1;

END$$

DELIMITER ;



DROP procedure IF EXISTS SP_Full_Clear;

DELIMITER $$

CREATE PROCEDURE SP_Full_Clear()
BEGIN
#SELECT table_name FROM information_schema.tables where table_schema='logdatabase' and table_name  like 'log_%';

declare tabela varchar(20);
 DECLARE done TINYINT DEFAULT FALSE;
DECLARE cursor1 
 CURSOR FOR
 SELECT table_name FROM information_schema.tables 
 where table_schema='g21destino' and table_name  like 'log_%';
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

END$$

DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

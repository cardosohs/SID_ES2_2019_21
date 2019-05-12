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
  `IdMigracao` INT NOT NULL,
  `DataHoraMig` TIMESTAMP NOT NULL,
  `Tabela` VARCHAR(100) NULL,
  `Processo` VARCHAR(20) NULL,
  `Resultado` BIT(0) NULL,
  PRIMARY KEY (`IdMigracao`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- MySQL Workbench Forward Engineering
-- -----------------------------------------------------
-- Schema cinemapds
-- -----------------------------------------------------
-- drop database cinemapds;
CREATE SCHEMA IF NOT EXISTS `cinemapds`;
USE `cinemapds` ;

-- -----------------------------------------------------
-- Table `cinemapds`.`admin`
-- -----------------------------------------------------


-- -----------------------------------------------------
-- Table `cinemapds`.`filme`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemapds`.`filme` (
  `idfilme` INT NOT NULL AUTO_INCREMENT,
  `filme_nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idfilme`));


-- -----------------------------------------------------
-- Table `cinemapds`.`sala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemapds`.`sala` (
  `idsala` INT NOT NULL AUTO_INCREMENT,
  `sala_horario` TIME NOT NULL,
  `nome_sala` VARCHAR(2) NOT NULL,
  `filme_idfilme` INT NOT NULL,
  PRIMARY KEY (`idsala`),
  INDEX `fk_sala_filme` (`filme_idfilme` ASC) VISIBLE,
  CONSTRAINT `fk_sala_filme`
    FOREIGN KEY (`filme_idfilme`)
    REFERENCES `cinemapds`.`filme` (`idfilme`)
    ON DELETE CASCADE);


-- -----------------------------------------------------
-- Table `cinemapds`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemapds`.`cliente` (
  `idcliente` INT NOT NULL AUTO_INCREMENT,
  `cliente_nome` VARCHAR(200) NOT NULL,
  `cliente_cpf` VARCHAR(14) NOT NULL,
  `cliente_meia_entrada` TINYINT NOT NULL,
  PRIMARY KEY (`idcliente`));


-- -----------------------------------------------------
-- Table `cinemapds`.`assento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemapds`.`assento` (
  `idassento` INT NOT NULL AUTO_INCREMENT,
  `row` SMALLINT NOT NULL,
  `col` SMALLINT NOT NULL,
  `sala_idsala` INT NOT NULL,
  `cliente_idcliente` INT NOT NULL,
  PRIMARY KEY (`idassento`),
  INDEX `fk_assento_sala1` (`sala_idsala` ASC) VISIBLE,
  INDEX `fk_assento_cliente1_idx` (`cliente_idcliente` ASC) VISIBLE,
  CONSTRAINT `fk_assento_sala1`
    FOREIGN KEY (`sala_idsala`)
    REFERENCES `cinemapds`.`sala` (`idsala`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_assento_cliente1`
    FOREIGN KEY (`cliente_idcliente`)
    REFERENCES `cinemapds`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `cinemapds`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemapds`.`funcionario` (
  `id_funcionario` INT NOT NULL AUTO_INCREMENT,
  `cpf_funcionario` VARCHAR(14) NOT NULL,
  `nome_funcionario` VARCHAR(200) NOT NULL,
  `admin_funcionario` tinyint not null,
  PRIMARY KEY (`id_funcionario`));


-- -----------------------------------------------------
-- Table `cinemapds`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemapds`.`venda` (
  `idvenda` INT NOT NULL AUTO_INCREMENT,
  `venda_valor` DOUBLE NOT NULL,
  `funcionario_id_funcionario` INT NOT NULL,
  `cliente_idcliente` INT NOT NULL,
  `assento_idassento` INT NOT NULL,
  PRIMARY KEY (`idvenda`),
  INDEX `fk_venda_funcionario1` (`funcionario_id_funcionario` ASC) VISIBLE,
  INDEX `fk_venda_cliente1` (`cliente_idcliente` ASC) VISIBLE,
  INDEX `fk_venda_assento1` (`assento_idassento` ASC) VISIBLE,
  CONSTRAINT `fk_venda_assento1`
    FOREIGN KEY (`assento_idassento`)
    REFERENCES `cinemapds`.`assento` (`idassento`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_venda_cliente1`
    FOREIGN KEY (`cliente_idcliente`)
    REFERENCES `cinemapds`.`cliente` (`idcliente`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_venda_funcionario1`
    FOREIGN KEY (`funcionario_id_funcionario`)
    REFERENCES `cinemapds`.`funcionario` (`id_funcionario`)
    ON DELETE CASCADE);

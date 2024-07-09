CREATE TABLE `ProducaoAcademica` (
  `idProducaoAcademica` integer NOT NULL AUTO_INCREMENT,
  `tituloProducao` varchar(255) NOT NULL,
  `idTipoProducaoAcademica` integer NOT NULL,
  `idProjeto` integer,
  `descricaoProducaoAcademica` varchar(255),
  `dataPublicacao` date NOT NULL,
  `idAreaConhecimento` integer NOT NULL,
  `indProjetoIndependente` char NOT NULL,
  PRIMARY KEY(idProducaoAcademica)
);

CREATE TABLE `TipoProducaoAcademica` (
  `idTipoProducaoAcademica` integer NOT NULL AUTO_INCREMENT,
  `nomeTipoProducaoAcademica` varchar(255) NOT NULL,
  `descricaoTipoProducaoAcademica` varchar(255),
  PRIMARY KEY(idTipoProducaoAcademica)
);

CREATE TABLE `Pesquisador` (
  `idPesquisador` integer NOT NULL AUTO_INCREMENT,
  `idTipoPesquisador` integer NOT NULL,
  `idPessoa` integer NOT NULL,
  `dataInicioPrograma` date NOT NULL,
  `dataFimPrograma` date,
  `idUniversidade` integer,
  PRIMARY KEY(idPesquisador)
);

CREATE TABLE `Pessoa` (
  `idPessoa` integer NOT NULL AUTO_INCREMENT,
  `nomePessoa` varchar(255) NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `orcid` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY(idPessoa)
);

CREATE TABLE `TipoPesquisador` (
  `idTipoPesquisador` integer NOT NULL AUTO_INCREMENT,
  `nomeTipoPesquisador` varchar(255) NOT NULL,
  `descricaoTipoPesquisador` varchar(255),
  PRIMARY KEY(idTipoPesquisador)
);

CREATE TABLE `PesquisadorProducaoAcademica` (
  `idPesquisadorProducao` integer NOT NULL AUTO_INCREMENT,
  `idPesquisador` integer NOT NULL,
  `idPapelPesquisador` integer NOT NULL,
  `idProducaoAcademica` integer NOT NULL,
  PRIMARY KEY(idPesquisadorProducao)
);

CREATE TABLE `PapelPesquisador` (
  `idPapelPesquisador` integer NOT NULL AUTO_INCREMENT,
  `nomePapel` varchar(255) NOT NULL,
  `descricaoPapel` varchar(255),
  PRIMARY KEY(idPapelPesquisador)
);

CREATE TABLE `AreaConhecimento` (
  `idAreaConhecimento` integer NOT NULL AUTO_INCREMENT,
  `descricaoAreaConhecimento` varchar(255) NOT NULL,
  `idAreaConhecimentoSup` integer,
  `resumo` text,
  PRIMARY KEY(idAreaConhecimento)
);

CREATE TABLE `Projeto` (
  `idProjeto` integer NOT NULL AUTO_INCREMENT,
  `nomeProjeto` varchar(255) NOT NULL,
  `descricaoProjeto` varchar(255),
  `codigoProjeto` varchar(255) NOT NULL,
  `dataInicio` date NOT NULL,
  `dataFim` date,
  PRIMARY KEY(idProjeto)
);

CREATE TABLE `PesquisadorProjeto` (
  `idPesquisadorProjeto` integer NOT NULL AUTO_INCREMENT,
  `dataInicio` date NOT NULL,
  `dataFim` date,
  `idPesquisador` integer NOT NULL,
  `idProjeto` integer NOT NULL,
  PRIMARY KEY(idPesquisadorProjeto)
);

CREATE TABLE `Usuario` (
  `idUsuario` integer NOT NULL AUTO_INCREMENT,
  `idPessoa` integer NOT NULL,
  `login` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `dataValidade` date NOT NULL,
  `idTipoUsuario` integer NOT NULL,
  PRIMARY KEY(idUsuario)
);

CREATE TABLE `TipoUsuario` (
  `idTipoUsuario` integer NOT NULL AUTO_INCREMENT,
  `nomeTipoUsuario` varchar(255) NOT NULL,
  `descricaoTipoUsuario` varchar(255),
  PRIMARY KEY(idTipoUsuario)
);

CREATE TABLE `universidade` (
  `idUniversidade` integer NOT NULL AUTO_INCREMENT,
  `nomeUniversidade` varchar(255) NOT NULL,
  `descricaoUniversidade` text,
  `local` varchar(255),
  PRIMARY KEY(idUniversidade)
);

ALTER TABLE `PesquisadorProducaoAcademica` ADD FOREIGN KEY (`idPesquisador`) REFERENCES `Pesquisador` (`idPesquisador`);

ALTER TABLE `PesquisadorProducaoAcademica` ADD FOREIGN KEY (`idProducaoAcademica`) REFERENCES `ProducaoAcademica` (`idProducaoAcademica`);

ALTER TABLE `Pesquisador` ADD FOREIGN KEY (`idTipoPesquisador`) REFERENCES `TipoPesquisador` (`idTipoPesquisador`);

ALTER TABLE `PesquisadorProducaoAcademica` ADD FOREIGN KEY (`idPapelPesquisador`) REFERENCES `PapelPesquisador` (`idPapelPesquisador`);

ALTER TABLE `ProducaoAcademica` ADD FOREIGN KEY (`idTipoProducaoAcademica`) REFERENCES `TipoProducaoAcademica` (`idTipoProducaoAcademica`);

ALTER TABLE `AreaConhecimento` ADD FOREIGN KEY (`idAreaConhecimentoSup`) REFERENCES `AreaConhecimento` (`idAreaConhecimento`);

ALTER TABLE `PesquisadorProjeto` ADD FOREIGN KEY (`idPesquisador`) REFERENCES `Pesquisador` (`idPesquisador`);

ALTER TABLE `PesquisadorProjeto` ADD FOREIGN KEY (`idProjeto`) REFERENCES `Projeto` (`idProjeto`);

ALTER TABLE `ProducaoAcademica` ADD FOREIGN KEY (`idProjeto`) REFERENCES `Projeto` (`idProjeto`);

ALTER TABLE `ProducaoAcademica` ADD FOREIGN KEY (`idAreaConhecimento`) REFERENCES `AreaConhecimento` (`idAreaConhecimento`);

ALTER TABLE `Usuario` ADD FOREIGN KEY (`idTipoUsuario`) REFERENCES `TipoUsuario` (`idTipoUsuario`);

ALTER TABLE `Pesquisador` ADD FOREIGN KEY (`idPessoa`) REFERENCES `Pessoa` (`idPessoa`);

ALTER TABLE `Pesquisador` ADD FOREIGN KEY (`idUniversidade`) REFERENCES `universidade` (`idUniversidade`);

ALTER TABLE `Usuario` ADD FOREIGN KEY (`idPessoa`) REFERENCES `Pessoa` (`idPessoa`);

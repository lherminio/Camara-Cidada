CREATE DATABASE IF NOT EXISTS camara_legislativa_db;

USE camara_legislativa_db;
 
-- ============================================================
-- Tabela de Parlamentares (RF01)
-- ============================================================
CREATE TABLE parlamentar (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    nome_parlamentar VARCHAR(150) NOT NULL,
    partido VARCHAR(100) NOT NULL,
    biografia TEXT,
    foto VARCHAR(500),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    legislatura INT NOT NULL,
 
    -- Impede nome parlamentar duplicado na mesma legislatura
    CONSTRAINT uk_nome_parlamentar_legislatura UNIQUE (nome_parlamentar, legislatura)
);
 
-- ============================================================
-- Tabela de Proposições (RF02) - projetos, indicações, requerimentos
-- ============================================================
CREATE TABLE proposicao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    numero_projeto INT NOT NULL,
    ano INT NOT NULL,
    autor VARCHAR(255) NOT NULL,
    ementa TEXT NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    status VARCHAR(50) NOT NULL,
    pdf_projeto VARCHAR(500),
 
    -- índices para acelerar a busca pública (RF04)
    INDEX idx_titulo (titulo),
    INDEX idx_categoria (categoria)
);
 
-- ============================================================
-- Tabela de Votações (RF03)
-- Referencia a Proposição por numero_projeto + ano
-- ============================================================
CREATE TABLE votacao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_projeto INT NOT NULL,
    ano INT NOT NULL,
    resultado VARCHAR(20) NOT NULL
);
 
-- ============================================================
-- Tabela de Votos individuais (RF03)
-- Voto restrito a: SIM, NAO, ABSTENCAO, AUSENCIA
-- ============================================================
CREATE TABLE voto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    votacao_id BIGINT NOT NULL,
    nome_parlamentar VARCHAR(150) NOT NULL,
    voto VARCHAR(20) NOT NULL,
 
    CONSTRAINT fk_voto_votacao FOREIGN KEY (votacao_id) REFERENCES votacao(id)
);
 
-- ============================================================
-- Tabela de Usuários do Sistema (RF06 - login administrativo)
-- ============================================================
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    ultimo_login DATETIME,
    criado_em DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
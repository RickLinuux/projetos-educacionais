CREATE DATABASE creche;
USE creche;

CREATE TABLE Pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    sexo ENUM('M', 'F'),
    endereco VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL
);


CREATE TABLE Crianca (
    id INT PRIMARY KEY,
    turma VARCHAR(255) NOT NULL,
    horario VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES Pessoa(id)
);

CREATE TABLE Professor (
    id INT PRIMARY KEY,
    disciplina VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES Pessoa(id)
);


CREATE TABLE Turma (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    faixa_etaria ENUM('2-3 anos', '3-4 anos', '4-5 anos', '5-6 anos') NOT NULL
);

CREATE TABLE Professor_Turma (
    id_professor INT,
    id_turma INT,
    PRIMARY KEY (id_professor, id_turma),
    FOREIGN KEY (id_professor) REFERENCES Professor(id),
    FOREIGN KEY (id_turma) REFERENCES Turma(id)
);



-- Turmas pré-alocadas, pois, irei recupperar o indice para associoar um professor a turma
INSERT INTO Turma (nome, faixa_etaria) VALUES ('Pré 1', '2-3 anos');
INSERT INTO Turma (nome, faixa_etaria) VALUES ('Pré 2', '3-4 anos');
INSERT INTO Turma (nome, faixa_etaria) VALUES ('Jardim 1', '4-5 anos');
INSERT INTO Turma (nome, faixa_etaria) VALUES ('Jardim 2', '5-6 anos');

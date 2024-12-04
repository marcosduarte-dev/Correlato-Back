CREATE TABLE tb_faculdade (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) UNIQUE,
    ativo BOOLEAN DEFAULT true
);

CREATE TABLE tb_curso (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    id_faculdade INT,
    ativo BOOLEAN DEFAULT true,
    UNIQUE (nome, id_faculdade),
    FOREIGN KEY (id_faculdade) REFERENCES tb_faculdade(id)
);

CREATE TABLE tb_disciplina (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    cod_disciplina VARCHAR(50),
    id_curso INT,
    carga_horaria FLOAT,
    ementa TEXT,
    programa TEXT,
    ativo BOOLEAN DEFAULT true,
    UNIQUE (nome, id_curso),
    UNIQUE (cod_disciplina, id_curso),
    FOREIGN KEY (id_curso) REFERENCES tb_curso(id)
);

CREATE TABLE tb_usuario (
    id UUID PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    nome VARCHAR(255),
    senha VARCHAR(255),
    id_faculdade INT,
    tipo VARCHAR(50),
    ativo BOOLEAN DEFAULT true,
    FOREIGN KEY (id_faculdade) REFERENCES tb_faculdade(id)
);

CREATE TABLE tb_analise_equivalencia (
    id SERIAL PRIMARY KEY,
    id_professor_responsavel UUID,
    id_disciplina_origem INT,
    id_disciplina_destino INT,
    status VARCHAR(50),
    aprovado BOOLEAN,
    UNIQUE (id_disciplina_origem, id_disciplina_destino),
    FOREIGN KEY (id_professor_responsavel) REFERENCES tb_usuario(id),
    FOREIGN KEY (id_disciplina_origem) REFERENCES tb_disciplina(id),
    FOREIGN KEY (id_disciplina_destino) REFERENCES tb_disciplina(id)
);
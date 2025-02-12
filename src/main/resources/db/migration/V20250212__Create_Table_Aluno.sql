CREATE TABLE tb_aluno (
    id SERIAL PRIMARY KEY,         
    nome VARCHAR(255) NOT NULL,        
    id_curso BIGINT,                   
    email VARCHAR(255) UNIQUE NOT NULL,
    matricula VARCHAR(50) NOT NULL,
    CONSTRAINT fk_curso FOREIGN KEY (id_curso) REFERENCES tb_curso(id) ON DELETE SET NULL
);

CREATE TABLE tb_aluno_analise_equivalencia (
    aluno_id BIGINT NOT NULL,                  
    analise_equivalencia_id BIGINT NOT NULL,   
    PRIMARY KEY (aluno_id, analise_equivalencia_id), 
    CONSTRAINT fk_aluno FOREIGN KEY (aluno_id) REFERENCES tb_aluno(id) ON DELETE CASCADE,
    CONSTRAINT fk_analise FOREIGN KEY (analise_equivalencia_id) REFERENCES tb_analise_equivalencia(id) ON DELETE CASCADE
);
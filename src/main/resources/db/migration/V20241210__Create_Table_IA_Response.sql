CREATE TABLE tb_ia_response (
    id SERIAL PRIMARY KEY,
    id_analise_equivalencia INT UNIQUE,
    response_raw TEXT,
    request_raw TEXT,
    equivalencias TEXT,
    diferencas TEXT,
    aprovado BOOLEAN,
    porcentagem_equivalencia FLOAT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_analise_equivalencia) REFERENCES tb_analise_equivalencia(id)
);
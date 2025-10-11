CREATE TABLE cliente (
    id uuid NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    email VARCHAR(100),
    estado_civil VARCHAR(20) NOT NULL,
    matricula VARCHAR(30) UNIQUE,
    numero_identidade VARCHAR(30) NOT NULL,
    orgao_emissor VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,

    -- Endereço (Embedded) --
    cep VARCHAR(8),
    bairro VARCHAR(100),
    localidade VARCHAR(100),
    logradouro VARCHAR(100),
    uf VARCHAR(2),

    -- constraints --
    CONSTRAINT check_estado_civil CHECK (estado_civil IN ('SOLTEIRO', 'CASADO', 'DIVORCIADO', 'UNIAO_ESTAVEL', 'VIUVO'))
);
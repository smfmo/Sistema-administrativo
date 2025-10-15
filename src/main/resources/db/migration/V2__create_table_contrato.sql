CREATE TABLE contrato (
    id uuid NOT NULL primary key,
    agencia VARCHAR(255) NOT NULL,
    data DATE,
    iof NUMERIC(19, 2) NOT NULL,
    juros_de_acerto NUMERIC(19, 2) NOT NULL,
    numero_do_contrato VARCHAR(255) NOT NULL,
    parcelas VARCHAR(10) NOT NULL,
    prestacao NUMERIC(19, 2) NOT NULL,
    prestamista NUMERIC(19, 2) NOT NULL,
    sr VARCHAR(255) NOT NULL,
    tipo_de_contrato VARCHAR(255) NOT NULL,
    valor_bruto NUMERIC(19, 2) NOT NULL,
    valor_liquido NUMERIC(19, 2) NOT NULL,
    cliente_id uuid,
    created_by_employee uuid,
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,


    -- constraints --
    CONSTRAINT fk_contrato_cliente FOREIGN KEY (cliente_id) REFERENCES public.cliente(id),
    CONSTRAINT check_tipo_contrato CHECK (tipo_de_contrato IN ('RENOVACAO', 'NOVO', 'HABITACIONAL'))
);
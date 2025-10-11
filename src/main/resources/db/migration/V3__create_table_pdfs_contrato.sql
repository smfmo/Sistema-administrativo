CREATE TABLE pdfs_contrato (
    contrato_id uuid NOT NULL primary key,
    url_pdf VARCHAR(255),

    -- constraints --
    CONSTRAINT fk_contratos_pdf FOREIGN KEY (contrato_id) REFERENCES public.contrato (id)
);
ALTER TABLE
    `restaurante`
ADD
    COLUMN `ativo` TINYINT(1) NOT NULL
AFTER
    `endereco_bairro`;

UPDATE
    `algafood`.`restaurante`
SET
    `ativo` = '1';
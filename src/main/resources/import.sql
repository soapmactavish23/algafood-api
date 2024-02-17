INSERT INTO `cozinha` (`id`, `nome`) VALUES (1, 'Tailândeza');
INSERT INTO `cozinha` (`id`, `nome`) VALUES (2, 'Brasileira');

INSERT INTO `estado` (`id`, `nome`) VALUES (1, 'Pará');
INSERT INTO `estado` (`id`, `nome`) VALUES (2, 'São Paulo');
INSERT INTO `estado` (`id`, `nome`) VALUES (3, 'Rio de Janeiro');

INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (1, 'Belém', 1);
INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (2, 'São Paulo', 2);
INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (3, 'Rio de Janeiro', 3);

INSERT INTO `restaurante` (`id`, `endereco_bairro`, `endereco_cep`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `nome`, `taxa_frete`, `cozinha_id`, `endereco_cidade_id`, data_cadastro, data_atualizacao) VALUES (1, 'Marambaia', '66620-200', 'Final da Santarém', 'Rua da Marinha', '35', 'X-Tudo', 50.00, 2, 1, utc_timestamp, utc_timestamp);
INSERT INTO `restaurante` (`id`, `endereco_bairro`, `endereco_cep`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `nome`, `taxa_frete`, `cozinha_id`, `endereco_cidade_id`, data_cadastro, data_atualizacao) VALUES (2, NULL, NULL, NULL, NULL, NULL, 'Pernabucano', 5.00, 2, NULL, utc_timestamp, utc_timestamp);
INSERT INTO `restaurante` (`id`, `endereco_bairro`, `endereco_cep`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `nome`, `taxa_frete`, `cozinha_id`, `endereco_cidade_id`, data_cadastro, data_atualizacao) VALUES (3, NULL, NULL, NULL, NULL, NULL, 'Bistrô', 30.00, 1, NULL, utc_timestamp, utc_timestamp);

INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (1, 'Cartão de Crédito');
INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (2, 'Cartão de Débito');
INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (3, 'Dinheiro');

INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (1, 1);
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (1, 2);
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (2, 3);
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (3, 3);
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (3, 2);

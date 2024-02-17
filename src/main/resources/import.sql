INSERT INTO `cozinha` (`id`, `nome`) VALUES (1, 'Tailândeza');
INSERT INTO `cozinha` (`id`, `nome`) VALUES (2, 'Brasileira');

INSERT INTO `algafood`.`restaurante` (`nome`, `taxa_frete`, `cozinha_id`) VALUES ('X-Tudo', '50.00', '2');
INSERT INTO `restaurante` (`id`, `nome`, `taxa_frete`, `cozinha_id`) VALUES (2, 'Pernabucano', 5.00, 2);
INSERT INTO `restaurante` (`id`, `nome`, `taxa_frete`, `cozinha_id`) VALUES (3, 'Bistrô', 30.00, 1);

INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (1, 'Cartão de Crédito');
INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (2, 'Cartão de Débito');
INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (3, 'Dinheiro');

INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (1, 1);
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (1, 2);
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (2, 3);
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (3, 3);
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (3, 2);

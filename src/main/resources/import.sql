INSERT INTO `cozinha` (`id`, `nome`) VALUES (1, 'Tailândeza');
INSERT INTO `cozinha` (`id`, `nome`) VALUES (2, 'Brasileira');

INSERT INTO `algafood`.`restaurante` (`nome`, `taxa_frete`, `cozinha_id`) VALUES ('X-Tudo', '50.00', '2');
INSERT INTO `restaurante` (`id`, `nome`, `taxa_frete`, `cozinha_id`) VALUES (2, 'Pernabucano', 5.00, 2);
INSERT INTO `restaurante` (`id`, `nome`, `taxa_frete`, `cozinha_id`) VALUES (3, 'Bistrô', 30.00, 1);

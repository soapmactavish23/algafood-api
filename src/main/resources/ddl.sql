create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit, descricao varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6) not null, data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime(6) not null, email varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
INSERT INTO `cozinha` (`id`, `nome`) VALUES (1, 'Tailândeza')
INSERT INTO `cozinha` (`id`, `nome`) VALUES (2, 'Brasileira')
INSERT INTO `estado` (`id`, `nome`) VALUES (1, 'Pará')
INSERT INTO `estado` (`id`, `nome`) VALUES (2, 'São Paulo')
INSERT INTO `estado` (`id`, `nome`) VALUES (3, 'Rio de Janeiro')
INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (1, 'Belém', 1)
INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (2, 'São Paulo', 2)
INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (3, 'Rio de Janeiro', 3)
INSERT INTO `restaurante` (`id`, `endereco_bairro`, `endereco_cep`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `nome`, `taxa_frete`, `cozinha_id`, `endereco_cidade_id`, data_cadastro, data_atualizacao) VALUES (1, 'Marambaia', '66620-200', 'Final da Santarém', 'Rua da Marinha', '35', 'X-Tudo', 50.00, 2, 1, utc_timestamp, utc_timestamp)
INSERT INTO `restaurante` (`id`, `endereco_bairro`, `endereco_cep`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `nome`, `taxa_frete`, `cozinha_id`, `endereco_cidade_id`, data_cadastro, data_atualizacao) VALUES (2, NULL, NULL, NULL, NULL, NULL, 'Pernabucano', 5.00, 2, NULL, utc_timestamp, utc_timestamp)
INSERT INTO `restaurante` (`id`, `endereco_bairro`, `endereco_cep`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `nome`, `taxa_frete`, `cozinha_id`, `endereco_cidade_id`, data_cadastro, data_atualizacao) VALUES (3, NULL, NULL, NULL, NULL, NULL, 'Bistrô', 30.00, 1, NULL, utc_timestamp, utc_timestamp)
INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (1, 'Cartão de Crédito')
INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (2, 'Cartão de Débito')
INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (3, 'Dinheiro')
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (1, 1)
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (1, 2)
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (2, 3)
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (3, 3)
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (3, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit, descricao varchar(255), preco decimal(19,2), restaurante_id bigint, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6) not null, data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime(6) not null, email varchar(255), senha varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
INSERT INTO `cozinha` (`id`, `nome`) VALUES (1, 'Tailândeza')
INSERT INTO `cozinha` (`id`, `nome`) VALUES (2, 'Brasileira')
INSERT INTO `estado` (`id`, `nome`) VALUES (1, 'Pará')
INSERT INTO `estado` (`id`, `nome`) VALUES (2, 'São Paulo')
INSERT INTO `estado` (`id`, `nome`) VALUES (3, 'Rio de Janeiro')
INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (1, 'Belém', 1)
INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (2, 'São Paulo', 2)
INSERT INTO `cidade` (`id`, `nome`, `estado_id`) VALUES (3, 'Rio de Janeiro', 3)
INSERT INTO `restaurante` (`id`, `endereco_bairro`, `endereco_cep`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `nome`, `taxa_frete`, `cozinha_id`, `endereco_cidade_id`, data_cadastro, data_atualizacao) VALUES (1, 'Marambaia', '66620-200', 'Final da Santarém', 'Rua da Marinha', '35', 'X-Tudo', 50.00, 2, 1, utc_timestamp, utc_timestamp)
INSERT INTO `restaurante` (`id`, `endereco_bairro`, `endereco_cep`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `nome`, `taxa_frete`, `cozinha_id`, `endereco_cidade_id`, data_cadastro, data_atualizacao) VALUES (2, NULL, NULL, NULL, NULL, NULL, 'Pernabucano', 5.00, 2, NULL, utc_timestamp, utc_timestamp)
INSERT INTO `restaurante` (`id`, `endereco_bairro`, `endereco_cep`, `endereco_complemento`, `endereco_logradouro`, `endereco_numero`, `nome`, `taxa_frete`, `cozinha_id`, `endereco_cidade_id`, data_cadastro, data_atualizacao) VALUES (3, NULL, NULL, NULL, NULL, NULL, 'Bistrô', 30.00, 1, NULL, utc_timestamp, utc_timestamp)
INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (1, 'Cartão de Crédito')
INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (2, 'Cartão de Débito')
INSERT INTO `forma_pagamento` (`id`, `descricao`) VALUES (3, 'Dinheiro')
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (1, 1)
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (1, 2)
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (2, 3)
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (3, 3)
INSERT INTO `restaurante_forma_pagamento` (`restaurante_id`, `forma_pagamento_id`) VALUES (3, 2)

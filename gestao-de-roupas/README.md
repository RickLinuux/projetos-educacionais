3. Vestuário: Sistema de Gestão de Loja de
Roupas

Descrição: Um
sistema de gerenciamento para uma loja de roupas que inclui controle de
estoque, gerenciamento de vendas, e um módulo para oferecer descontos e
promoções para clientes fiéis.
Funcionalidades:

Controle
de estoque e cadastro de produtos
Registro
de vendas e emissão de recibos
Gestão
de clientes e programa de fidelidade
Relatórios
de vendas e estoque

src/
|-- controle/
|   |-- ProdutoControle.java
|   |-- ClienteControle.java
|   |-- VendaControle.java
|
|-- modelo/
|   |-- Produto.java
|   |-- Cliente.java
|   |-- Venda.java
|   |-- ItemVenda.java
|   |-- Estoque.java
|
|-- servicos/
|   |-- ProdutoServico.java
|   |-- ClienteServico.java
|   |-- VendaServico.java
|   |-- RelatorioServico.java
|
|-- utils/
|   |-- Sexo.java
|   |-- DescontoUtil.java
|   |-- RelatorioUtil.java



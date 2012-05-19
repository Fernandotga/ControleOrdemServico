Controle de Ordem de Servico
============================


Um pequena empresa deseja criar um sistema de Ordem de Serviço, com um cadastro de clientes, cadastro de serviços e a criação de uma ordem de serviço para um cliente contendo vários serviços e um status ( em aberto, em execução, concluído). 

Pede-se:
a) A modelagem arquitetural (com os pacotes) e o diagrama de classes do Aplicativo Enterprise; 
 - A arquitetura do sistema é em três camadas: 
   - Domain Model: responsável por conter a lógica e as entidades do sistema, implementada no pacote br.edu.fasa.os.domainModel;
   - Data Access: responsável por realizar toda a interface com o SGBD, contém as classes DAO, implementada no pacote br.edu.fasa.os.dataAccess;
   - Presentation: responsável por fazer a interface gráfica com o usuário, implementada no pacote br.edu.fasa.os.presentation;

As duas primeiras camadas estão encapsuladas em um aplicativo corporativo e rodam em um servidor de aplicação e a camada de apresentação é um aplicativo de console que roda no cliente.

[diagramas feitos na sala]

b) O detalhamento da classe OrdemServico e o mapeamento JPA; 
[vide código no git]

c) Quais EJB's o sistema terá? Quais as interfaces e métodos?
	- Beans de Entidade: Cliente, OS e Servico.
	- Beans de Sessão: ClienteDAO, OsDAO, ServicoDAO. [Cada bean desse usa a interface de um repositório e suas funções]

d) Um cliente console para o aplicativo Enterprise chamando o EJB que salva um cliente;
[vide código no git]

Lembretes
=====================

- JAVA EE: Java Enterprise Edition, tecnologia para criação de aplicativos corporativos através de componentes distribuídos e reutilizáveis.

- APLICATIVO CORPORATIVO: Contém vários módulos EJB que em conjunto formam um sistema utilizando Java Enterprise Edition. Esse aplicativo roda em um servidor de aplicação e, para se tornar disponível, deve ser implantado no servidor. As funcionalidades do aplicativo corporativo são acessadas através da rede por aplicativos que rodam nos clientes.

- MODULO EJB: É um bloco de lógica da aplicação, usualmente uma ou mais camadas da arquitetura do sistema. Cada módulo é composto por um conjunto de componentes EJB. 

- EJB: Enterprise Java Bean é um componente reutilizável que pode ser distribuído em rede e fica hospedado em um servidor de aplicação. Ele pode ser acessado remotamente e instanciado localmente por um cliente ou mesmo dentro do servidor. Está contido dentro de um módulo.

- CLIENTE DE APLICATIVO CORPORATIVO: é uma aplicação que roda localmente na máquina de um usuário e acessa remotamente os módulos de um aplicativo corporativo no servidor de aplicação. Ele deve ser cadastrado no aplicativo corporativo para que tenha autorização de acessar seus módulos e deve ter referências para todos os módulos que acessa.


Metodologia
=====================

A) Análise de requisitos;
- Ler o texto, abstrair os requisitos, entidades e processos

B) Modelagem conceitual;
- Entidades e Relacionamentos: Clientes 1->N Ordem de Serviço N<->N Serviços

C) Modelagem física BD;
- Tabelas e campos: Clientes 1->N OS 1->N OSItens N<-1 Servicos

D) Diagrama de Classes(domainModel)
- Classes, atributos e associações: Cliente -> OS -> Servico

E) Implementação 

	1) IMPLEMENTAR DOMAIN MODEL
		1.1) Novo Projeto -> JAVA EE -> Aplicativo Corporativo;
		1.2) Criar os pacotes (Novo -> Pacote Java);
			- br.edu.fasa.os.domainModel
			- br.edu.fasa.os.dataAccess
		1.3) Criar as classes de Entidade/Entity Beans (Novo -> Java EE -> Classe de Entidade) no pacote br.edu.fasa.os.domainModel
			- Cliente (Mapear p/ tabela Clientes)
			- OS (Mapear p/ tabela OS)
			- Servico (Mapear p/ tabela Servicos)
		
	2) CONFIGURAR O SGBD
		2.1) Na aba serviços verificar se o SGBD que será utilizado já está com o driver configurado, se não, configure.
			- Serviços -> Banco de Dados -> Drivers
		2.2) Criar conexão com o SGBD;
			- Serviços -> Banco de Dados -> Botão Direito -> Nova conexão
	
	3) IMPLEMENTAR O DATA ACCESS
		3.1) Criar as interfaces (Novo -> Interface Java) de repositórios no pacote br.edu.fasa.os.domainModel. Anotar essas interfaces com @Remote.
			- IRepositório<T>
				- void salvar(T obj);
				- T abrir(long id);
				- void apagar(T obj);
				- List<T> listarTodos();
			- IClienteRepositorio
			- IServicoRepositorio
			- IOSRepositorio
		3.2) Criar o DAO Genérico no pacote br.edu.fasa.os.dataAccess que conterá os métodos comuns a todos os DAO's e implementa a interface IRepositorio.
			- DAOGenerico<T>
			- Utilizando o EntityManager injetado pela anotação @PersistenceContext
		3.3) Criar os DAOs como Session Beans  no pacote br.edu.fasa.os.dataAccess.
			- Novo -> JAVA EE -> Bean de Sessão
			- Todos os DAO's herdam de DAOGenerico e implementam uma interface de repositório
			
	4) IMPLEMENTAR CLIENTE
		4.1) Criar projeto do cliente
			- Novo Projeto -> Java EE -> Cliente Aplicativo Corporativo
			- Adicionar referência para o módulo ejb. (Selecionar Projeto -> Propriedades -> Bibliotecas -> Adicionar Projeto)
			- Criar o pacote br.edu.fasa.os.presentation
			- Buscar os EJB's no servidor através do JNDI
				- Context, InitialContext, context.lookup
			
F) Testes

	1) Implantar Aplicativo;
	2) Verificar a criação do BD
	3) Executar o cliente
	4) Verificar se os dados foram inseridos






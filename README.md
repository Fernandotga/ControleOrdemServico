Controle de Ordem de Servico
============================


Um pequena empresa deseja criar um sistema de Ordem de Servi�o, com um cadastro de clientes, cadastro de servi�os e a cria��o de uma ordem de servi�o para um cliente contendo v�rios servi�os e um status ( em aberto, em execu��o, conclu�do). 

Pede-se:
a) A modelagem arquitetural (com os pacotes) e o diagrama de classes do Aplicativo Enterprise; 
 - A arquitetura do sistema � em tr�s camadas: 
   - Domain Model: respons�vel por conter a l�gica e as entidades do sistema, implementada no pacote br.edu.fasa.os.domainModel;
   - Data Access: respons�vel por realizar toda a interface com o SGBD, cont�m as classes DAO, implementada no pacote br.edu.fasa.os.dataAccess;
   - Presentation: respons�vel por fazer a interface gr�fica com o usu�rio, implementada no pacote br.edu.fasa.os.presentation;

As duas primeiras camadas est�o encapsuladas em um aplicativo corporativo e rodam em um servidor de aplica��o e a camada de apresenta��o � um aplicativo de console que roda no cliente.

[diagramas feitos na sala]

b) O detalhamento da classe OrdemServico e o mapeamento JPA; 
[vide c�digo no git]

c) Quais EJB's o sistema ter�? Quais as interfaces e m�todos?
	- Beans de Entidade: Cliente, OS e Servico.
	- Beans de Sess�o: ClienteDAO, OsDAO, ServicoDAO. [Cada bean desse usa a interface de um reposit�rio e suas fun��es]

d) Um cliente console para o aplicativo Enterprise chamando o EJB que salva um cliente;
[vide c�digo no git]

Lembretes
=====================

- JAVA EE: Java Enterprise Edition, tecnologia para cria��o de aplicativos corporativos atrav�s de componentes distribu�dos e reutiliz�veis.

- APLICATIVO CORPORATIVO: Cont�m v�rios m�dulos EJB que em conjunto formam um sistema utilizando Java Enterprise Edition. Esse aplicativo roda em um servidor de aplica��o e, para se tornar dispon�vel, deve ser implantado no servidor. As funcionalidades do aplicativo corporativo s�o acessadas atrav�s da rede por aplicativos que rodam nos clientes.

- MODULO EJB: � um bloco de l�gica da aplica��o, usualmente uma ou mais camadas da arquitetura do sistema. Cada m�dulo � composto por um conjunto de componentes EJB. 

- EJB: Enterprise Java Bean � um componente reutiliz�vel que pode ser distribu�do em rede e fica hospedado em um servidor de aplica��o. Ele pode ser acessado remotamente e instanciado localmente por um cliente ou mesmo dentro do servidor. Est� contido dentro de um m�dulo.

- CLIENTE DE APLICATIVO CORPORATIVO: � uma aplica��o que roda localmente na m�quina de um usu�rio e acessa remotamente os m�dulos de um aplicativo corporativo no servidor de aplica��o. Ele deve ser cadastrado no aplicativo corporativo para que tenha autoriza��o de acessar seus m�dulos e deve ter refer�ncias para todos os m�dulos que acessa.


Metodologia
=====================

A) An�lise de requisitos;
- Ler o texto, abstrair os requisitos, entidades e processos

B) Modelagem conceitual;
- Entidades e Relacionamentos: Clientes 1->N Ordem de Servi�o N<->N Servi�os

C) Modelagem f�sica BD;
- Tabelas e campos: Clientes 1->N OS 1->N OSItens N<-1 Servicos

D) Diagrama de Classes(domainModel)
- Classes, atributos e associa��es: Cliente -> OS -> Servico

E) Implementa��o 

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
		2.1) Na aba servi�os verificar se o SGBD que ser� utilizado j� est� com o driver configurado, se n�o, configure.
			- Servi�os -> Banco de Dados -> Drivers
		2.2) Criar conex�o com o SGBD;
			- Servi�os -> Banco de Dados -> Bot�o Direito -> Nova conex�o
	
	3) IMPLEMENTAR O DATA ACCESS
		3.1) Criar as interfaces (Novo -> Interface Java) de reposit�rios no pacote br.edu.fasa.os.domainModel. Anotar essas interfaces com @Remote.
			- IReposit�rio<T>
				- void salvar(T obj);
				- T abrir(long id);
				- void apagar(T obj);
				- List<T> listarTodos();
			- IClienteRepositorio
			- IServicoRepositorio
			- IOSRepositorio
		3.2) Criar o DAO Gen�rico no pacote br.edu.fasa.os.dataAccess que conter� os m�todos comuns a todos os DAO's e implementa a interface IRepositorio.
			- DAOGenerico<T>
			- Utilizando o EntityManager injetado pela anota��o @PersistenceContext
		3.3) Criar os DAOs como Session Beans  no pacote br.edu.fasa.os.dataAccess.
			- Novo -> JAVA EE -> Bean de Sess�o
			- Todos os DAO's herdam de DAOGenerico e implementam uma interface de reposit�rio
			
	4) IMPLEMENTAR CLIENTE
		4.1) Criar projeto do cliente
			- Novo Projeto -> Java EE -> Cliente Aplicativo Corporativo
			- Adicionar refer�ncia para o m�dulo ejb. (Selecionar Projeto -> Propriedades -> Bibliotecas -> Adicionar Projeto)
			- Criar o pacote br.edu.fasa.os.presentation
			- Buscar os EJB's no servidor atrav�s do JNDI
				- Context, InitialContext, context.lookup
			
F) Testes

	1) Implantar Aplicativo;
	2) Verificar a cria��o do BD
	3) Executar o cliente
	4) Verificar se os dados foram inseridos






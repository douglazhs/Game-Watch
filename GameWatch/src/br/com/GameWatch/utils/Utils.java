package br.com.GameWatch.utils;

import java.util.ArrayList;

import br.com.GameWatch.main.Comment;
import br.com.GameWatch.main.Game;
import br.com.GameWatch.main.Genre;
import br.com.GameWatch.main.Group;
import br.com.GameWatch.main.Member;
import br.com.GameWatch.main.Post;
import br.com.GameWatch.main.Program;
import br.com.GameWatch.main.Requirements;
import br.com.GameWatch.main.User;

public abstract class Utils {
	public static Program createProgram() {
		String name = "GAME WATCH";
		String creationDate = "08/12/2017";
		String descricao = "- Monitoramento de games lancados ou que ainda lancarao."
				       + "\n- Informacoes sobre crackeamento do game."
				       + "\n- Lojas em que estao disponiveis para compra."
				       + "\n- Aberto a comunidade para adicionar ou editar."
				       + "\n- Avaliacoes feitas por usuarios.";
		String aboutProtection = "[PROGRAMA]"
				              + "\nEste e um sistema para auxiliar gamers com seus jogos. Aqui temos varias"
				              + "\nopcoes desde adicionar jogos, ate edita-los ou avalia-los."
				              + "\n[INSTRUCOES BASICAS]"
				              + "\n  	- ADICIONAR: JOGO > ADICIONAR"
				              + "\n     - AVALIAR: JOGO > AVALIAR"
				              + "\n     - EDITAR: JOGO > EDITAR"
				              + "\n     - VER COMENTARIOS DE JOGO: JOGO > VER JOGO > COMENTARIOS"
				              + "\n     - REQUISITOS PARA RODAR O JOGO: JOGO > VER JOGO > REQUISITOS"
				              + "\n     - VER LISTAS FILTRADAS: VER LISTAS"
				              + "\n     - VER GRUPOS DE CRACK: GRUPOS DE CRACK"
				              + "\n     - AVALIAR GRUPO DE CRACK: GRUPOS DE CRACK > AVALIAR"
				              + "\n     - VER COMENTARIOS DE CRUPOS DE CRACK: GRUPOS DE CRACK > COMENTARIOS"
				              + "\n     - VER INFORMACOES DO SISTEMA: SOBRE PROGRAMA"
				              + "\n[PROTECOES]"
				              + "\nSao medidas de proteção para evitar que os gamers acessem a titulos pirateados."
				              + "\nNao e um software adicional, ele e integrado pelos desenvolvedores diretamente"
				              + "\nno codigo do jogo, como parte dele. Isso é feito para tornar mais complicado o"
				              + "\ndesbloqueio por engenharia reversa e depuração de software.";
		return new Program(name, creationDate, descricao, aboutProtection);
	}
	
	public static User createUser() {
		String name = validString("CADASTRO USUARIO", "[USUARIO] NOME: ", true);
		String email = validString("CADASTRO USUARIO", "[USUARIO] EMAIL: ", false);
		String userName = validString("CADASTRO USUARIO", "[USUARIO] NOME DE USUARIO: ", false);
		String password = validString("CADASTRO USUARIO", "[USUARIO] SENHA: ", false);
		return new User(name, userName, password, email);
	}
	
	public static Game createGame(ArrayList<Genre> genres, ArrayList<Group> groups) {
		boolean groupExist = false;
		boolean genreExist = false;
		boolean addedGenre = false;
		boolean cracked = false;
		int option;
		Group groupSearched = null;
		Genre createdGenre = null;
		Requirements requirement = null;
		ArrayList<Genre> chooseGenres = new ArrayList<>();
		ArrayList<String> chooseStores = new ArrayList<>();
		String[] options = {"SIM", "NAO"};
		
		//Informacoes basicas sobre o jogo. ----------------------------------------------------------------------------
	
		String name = validString("CADASTRO GAME", "[GAME] NOME: ", false);//Nome de jogo pode conter numeros.
		String launchDate = validDate("CADASTRO GAME", "[GAME] DATA DE LANCAMENTO(EX.: 08122000): ");
		String description = validString("CADASTRO GAME", "[GAME] DESCRICAO: ", false);
		String protection = View.inputString("CADASTRO GAME", "[GAME] PROTECAO(APERTE ENTER CASO NAO TENHA): ");//Pode ser nula.
		
		//Informacoes do genero do game. Ele pode conter mais de um. ---------------------------------------------------
		
		do {
			addedGenre = false;
			String genreName = validString("CADASTRO GAME", "[GAME] GENERO: ", false);
			for(Genre genre : chooseGenres) {
				if(genre.getName().compareToIgnoreCase(genreName) == 0) {
					addedGenre = true;
				}
			}
			if(!addedGenre) {//Validar se o gênero ja foi escolhido para nao haver repeticao.
				for (Genre genre : genres) {//Verificacao para ver se ja consta no sistema.
					if(genreName.compareToIgnoreCase(genre.getName()) == 0) {
						View.showMessage("WARNING", "ESTE GENERO JA ESTA CADASTRADO NO SISTEMA!"
					             + "\nELE SERA ADICIONADO AUTOMATICAMENTE.");
						chooseGenres.add(genre);//Adicionando os generes.
						genreExist = true;
					}
				}
				if(!genreExist) {//Se nao existir, eh criado.
					View.showMessage("WARNING", "ESTE GENERO AINDA NAO CONSTA EM NOSSO SISTEMA!"
											+ "\nNOS AJUDE E CADASTRE ELE.");
					createdGenre = Utils.createGenre();
					genres.add(createdGenre);
					chooseGenres.add(createdGenre);//Adicionando os generos.
				}
			}else 
				View.showError("ERRO", "GENERO JA ESCOLHIDO.");
			option = View.menu(options, "CADASTRO GAME", "ESTE GAME POSSUI MAIS DE UM GENERO?");
		}while(option == 1);
		
		//Informacoes sobre o crack do jogo. --------------------------------------------------------------------------
		
		int op = View.menu(options, "CADASTRO GAMES", "GAME JA ESTA CRACKEADO?");
		if(op == 1) {//Caso o game ja esteja crackeado
			String groupName = View.inputString("CADASTRO GAME", "[GAME] NOME DO GRUPO: ");
			for (Group group : groups) {//Verificacao para ver se o grupo ja esta cadastrado no sistema.
				if(groupName.compareToIgnoreCase(group.getName()) == 0){
					View.showMessage("WARNING", "ESTE GRUPO JA ESTA CADASTRADO NO SISTEMA!"
							                + "\nELE SERA ADICIONADO AUTOMATICAMENTE.");
					groupSearched = group;
					groupExist = true;
				}
			}
			if(!groupExist) {//Caso nao esteja, eh criado.
				View.showMessage("WARNING", "ESTE GRUPO NAO ESTA CADASTRADO NO SISTEMA!"
						                + "\nNOS AJUDE E CADASTRE ELE.");
				groupSearched = Utils.createGroup();
				groups.add(groupSearched);
			}
			cracked = true;
		}else {//Senao, apenas o coloca como nao crackeado.
			cracked = false;
		}
		
		//Lojas em que o game esta disponivel-------------------------------------------------------------------------
		
		do {
			String storeName = validString("CADASTRO GAME", "DIGITE ABAIXO AS LOJAS EM QUE O GAME ESTA DISPONIVEL PARA COMPRA."
					                                    + "\n[LOJA] NOME: ", false);
			chooseStores.add(storeName);
			option = View.menu(options, "WARNING","PODE SER ENCONTRADO EM MAIS ALGUMA LOJA?");
		}while(option == 1);
		
		//Requisitos minimos ------------------------------------------------------------------------------------------
		op = View.menu(options, "CADASTRO GAME", "DESEJA ADICIONAR OS REQUISITOS MINIMOS?");
		if(op == 1) {
			requirement = createRequirement("GAME");
		}
		return new Game(name,launchDate, description, cracked, groupSearched, chooseGenres, chooseStores, protection, requirement);
	}
	
	public static Requirements createRequirement(String title) {
		String cpu = validString(title, "CPU(Processador): ", false);
		String gpu = validString(title, "GPU(Placa de video): ", false);
		double sizeDisk = View.inputDouble(title, "TAMANHO DISCO: ");
		int ram = View.inputInt(title, "RAM: ");
		return new Requirements(cpu, gpu, sizeDisk, ram);
	}
	
	public static Group createGroup() {
		String memberName;
		String[] options = {"SIM", "NAO"};
		int op;
		ArrayList<Member> members = new ArrayList<>();
		
		String name = validString("CADASTRO GRUPO", "[GRUPO] NOME: ", false);
		String description = validString("CADASTRO GRUPO", "[GRUPO] DESCRICAO: ", false);
		View.showMessage("CADASTRO GRUPO", "AGORA, INFORME QUAIS SAO OS MEMBROS DESTE GRUPO.");
		do {
			memberName = validString("CADASTRO GRUPO", "[GRUPO] NOME DO MEMBRO: ", true);
			members.add(new Member(memberName));
			op = View.menu(options, "CADASTRO GRUPO", "EXISTE MAIS ALGUM MEMBRO?");
		}while(op != 2);
		return new Group(name, description, members);
	}
	
	public static Genre createGenre() {
		String name = validString("CADASTRO GENERO", "[GENERO] NOME: ", true);
		String description = validString("CADASTRO GENERO", "[GENERO] DESCRICAO: ", false);
		return new Genre(name, description);
	}
	
	public static Post createPost(User user, boolean anonymous) {
		Comment comment= Utils.createComment();
		if(anonymous)
			return new Post(comment);
		return new Post(comment, user);
	}
	
	public static Comment createComment() {
		double avaliationNote;
		
		String message = validString("AVALIACAO", "DIGITE AQUI SEU COMENTARIO: ",false);
		do {
			avaliationNote = View.inputDouble("COMENTARIO", "NOTA: ");
			if(avaliationNote < 0 || avaliationNote > 5)
				View.showError("ERRO", "NOTA INVALIDA! TENTE DIGITAR UM NUMERO ENTRE 0 E 5.");
		}while(avaliationNote < 0 || avaliationNote > 5);
		return new Comment(message, avaliationNote);
	}
	
	//Validacao de Strings. --------------------------------------------------------------------------------------------
	
	public static String validString(String title, String message, boolean isName) {//Validacao de Strings em geral.
		boolean valid = false;
		String string;
		
		do {
			string = View.inputString(title, message);
			if(isName) {//Se a String for um nome, as condicoes de validade sao diferentes.
				if(!string.isEmpty() && !Utils.isNumber(string)) {
					valid = true;
				}else {
					valid = false;
					View.showError("ERRO", "CAMPO VAZIO OU DIGITOU NUMEROS.");
				}
			}else {
				if(!string.isEmpty()) {
					valid = true;
				}else {
					valid = false;
					View.showError("ERRO", "CAMPO VAZIO.");
				}
			}
		}while(!valid);
		return string;
	}
	
	public static String validDate(String title, String message) {//Validacao de data.
		boolean valid = false;
		String date;
		
		do {
			date = View.inputString(title, message);
			if(date.length() == 8 && isNumber(date))//Data tem que ter um total de 8 caracteres e ser toda composta de numeros.
				valid = true;
			if(!valid)
				View.showError("ERRO", "DATA INVALIDA.");
		}while(!valid);
		return date;
	}
	
	public static boolean isNumber(String string) {//Funcao auxiliar das funcoes de validacao.
        return string.matches("[0-9]*");
	}
	
}

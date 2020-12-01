package br.com.GameWatch.main;

import java.util.ArrayList;
import java.util.Collections;

import br.com.GameWatch.utils.Utils;
import br.com.GameWatch.utils.View;

public class Program {
	private String name;
	private String creationDate;
	private String description;
	private String aboutProtection;
	private ArrayList<Game> games = new ArrayList<>();
	private ArrayList<Post> posts = new ArrayList<>();
	private ArrayList<User> users = new ArrayList<>();
	private ArrayList<Group> groups = new ArrayList<>();
	private ArrayList<Genre> genres = new ArrayList<>();
	
	public Program(String name, String creationDate, String description, String aboutProtection) {
		setCreationDate(creationDate);
		setDescription(description);
		setName(name);
		setAboutProtection(aboutProtection);
	}
	
	public void addGame(int option) {
		User user;
		Game game;
		
		if(option == 1) {//Novo usuario.
			user = Utils.createUser();
			this.users.add(user);
			game = Utils.createGame(this.genres, this.groups);
			games.add(game);
		}else {//Usuario ja existente.
			user = loginhUser();
			if(user != null) {
				game = Utils.createGame(this.genres, this.groups);
				games.add(game);
			}else
				throw new IllegalArgumentException("ERRO NO LOGIN.");
		}
		View.showMessage("GAME", "JOGO ADICIONADO COM SUCESSO."
				             + "\nOBRIGADO POR SUA CONTRIBUICAO. SEMPRE QUE PUDER, NOS AJUDE.");
	}
	
	//Avaliacoes -----------------------------------------------------------------------------------------------------------
	
	public void avaliateGame(Game game, int option) {
		User user;
		int op;
		boolean anonymous = false;
		String[] options = {"SIM", "NAO"};
		
		if(option == 1) {//Novo usuario.
			user = Utils.createUser();
			game.addPost(user, anonymous);
		}else{//Usuario ja existente.
			user = loginhUser();
			if(user != null) {
				op = View.menu(options, "WARNING", "DESEJA SE IDENTIFICAR NO POST?");
				if(op == 1) {
					game.addPost(user, anonymous);//Anonymous ja esta como falso na declaracao.
				}else {
					anonymous = true;
					game.addPost(anonymous);
				}
			}else
				throw new IllegalArgumentException("ERRO NO LOGIN.");
		}
		View.showMessage("AVALIACAO", "SUA AVALIACAO FOI COMPUTADA COM SUCESSO."
				                  + "\nOBRIGADO POR SUA AJUDA. ELA SEMPRE SERA BEM-VINDA!");
	}
	
	public void avaliateGroup(Group group, int option) {
		User user;
		int op;
		boolean anonymous = false;
		String[] options = {"SIM", "NAO"};
		
		if(option == 1) {//Novo usuario.
			user = Utils.createUser();
			group.addPost(user, anonymous);
		}else{//Usuario ja existente.
			user = loginhUser();
			if(user != null) {//Se o login correu bem.
				op = View.menu(options, "WARNING", "DESEJA SE IDENTIFICAR NO POST?");
				if(op == 1) {
					group.addPost(user, anonymous);//Deseja se identificar no post com seu nome de usuario.
				}else {
					group.addPost(anonymous);//Nao deseja se identificar.
				}
			}else
				throw new IllegalArgumentException("ERRO NO LOGIN.");
		}
		View.showMessage("AVALIACAO", "SUA AVALIACAO FOI COMPUTADA COM SUCESSO."
                                  + "\nOBRIGADO POR SUA AJUDA. O GRUPOS NECESSITAM DO APOIO DA COMUNIDADE!");
	}
	
	//Editar informacoes do game. -----------------------------------------------------------------------------------
	
	public void editGame(int option, Game game) {
		User user;
		int op;
		int opGame;
		String fieldEdit;//Campo editado. Uma variavel é melhor, pois pode ser reaproveitada.
		String[] options = {"SIM", "NAO"};
		
		if(option == 1) {//Novo usuario.
			user = Utils.createUser();
		}else{//Usuario ja existente.
			user = loginhUser();
		}
		if(user != null) {
			op = View.inputInt("MENU EDICAO", "[1] NOME"
					                      + "\n[2] DESCRICAO"
					                      + "\n[3] DATA DE LANCAMENTO"
					                      + "\n[4] CRACK"
					                      + "\n[5] REQUISITOS"
					                      + "\n[6] VOLTAR");
			switch(op) {
				case 1:
					fieldEdit = Utils.validString("EDITAR", "NOVO NOME: ", true);
					game.setName(fieldEdit);
					break;
				case 2:
					fieldEdit = Utils.validString("EDITAR", "NOVA DESCRICAO: ", false);
					game.setDescription(fieldEdit);
					break;
				case 3:
					fieldEdit = Utils.validDate("EDITAR", "NOVA DATA: ");
					game.setLaunchDate(fieldEdit);
					break;
				case 4:
					opGame = View.menu(options, "EDITAR", "O GAME ESTA CRACKEADO?");
					if(opGame == 1) {
						game.setGroup(Utils.createGroup());
						game.setCracked(true);
					}
					break;
				case 5:
					Requirements newRequirement = Utils.createRequirement("EDITAR");
					game.setRequirement(newRequirement);
					break;
			}
			if(op != 6)
				View.showMessage("OBRIGADO", "OBRIGADO POR SUA CONTRIBUICAO.");
		}
	}
	
	//Listar games. ----------------------------------------------------------------------------------------------------
	
	public String listOrderedGames(int option) {
		String string = "";
		ArrayList<Game> newGames = new ArrayList<>();
		
		newGames = this.games;//Para nao fazer alteracoes no Array original.
		if(option == 1) {
			Collections.sort(newGames, new CompareNotes2());//Ordena pela maior nota.
		}else {
			Collections.sort(newGames, new CompareNotes());//Ordena pela menor nota.
		}
		for(Game game : newGames) {
			string += "NOME: " + game.getName();
			string += "\nNOTA: " + game.getAvaliationNote();
			string += "\n\n";
		}
		return string;
	}
	
	//Mostrar grupos. --------------------------------------------------------------------------------------------------
	
	public String showGroups() {
		String groupInfo = "";
		
		for (Group group : this.groups) {
			groupInfo += group.toString();
		}
		return groupInfo;
	}
	
	//Buscas. ---------------------------------------------------------------------------------------------------------
	
	public Game searchGame(String gameName) {
		for (Game game : this.games) {
			if(gameName.compareToIgnoreCase(game.getName()) == 0) {
				return game;
			}
		}
		return null;
	}
	
	public Group searchGroup(String groupName) {
		for (Group group : this.groups) {
			if(groupName.compareToIgnoreCase(group.getName()) == 0) {
				return group;
			}
		}
		return null;
	}
	
	public Genre searchGenre(String genreName) {
		for (Genre genre : this.genres) {
			if(genreName.compareToIgnoreCase(genre.getName()) == 0){
				return genre;
			}
		}
		return null;
	}
	
	//Login. -----------------------------------------------------------------------------------------------------------
	
	public User loginhUser() {
		boolean sucess = false;
		
		do {
			String userName = View.inputString("LOGIN", "NOME DE USUARIO OU EMAIL: ");
			String password = View.inputString("LOGIN", "SENHA: ");
			for(User user : this.users) {
				if(userName.compareToIgnoreCase(user.getEmail()) == 0 || userName.compareToIgnoreCase(user.getUserName()) == 0) {
					if(password.compareTo(user.getPassword()) == 0) {
						sucess = true;
						return user;
					}else {
						View.showError("ERRO", "SENHA INVALIDA");
						sucess = false;
					}
				}else {
					View.showError("ERRO", "EMAIL OU NOME DE USUARIO INVALIDO!");
					sucess = false;
				}
			}
		}while(!sucess);	
		return null;
	}
	
	//Getters and Setters. -----------------------------------------------------------------------------------------------
	
	public String getAboutProtection() {
		return aboutProtection;
	}

	public void setAboutProtection(String aboutProtection) {
		this.aboutProtection = aboutProtection;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		if(games != null)
			this.games = games;
		else throw new IllegalArgumentException("ERRO EM GAMES.");
	}

	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		if(users != null)
			this.users = users;
		else
			throw new IllegalArgumentException("ERRO EM USUARIOS.");
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<Group> groups) {
		if(groups != null)
			this.groups = groups;
		else throw new IllegalArgumentException("ERRO EM GRUPOS.");
	}

	public ArrayList<Genre> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<Genre> genres) {
		if(genres != null)
			this.genres = genres;
		else throw new IllegalArgumentException("ERRO EM GENEROS.");
	}
	
	//toString ----------------------------------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		String total = "";
		
		total += "NOME: " + this.name;
		total += "\nDESCRICAO:\n" + this.description;
		total += "\nDATA DE CRIACAO: " + this.creationDate;
		return total;
	}	
}

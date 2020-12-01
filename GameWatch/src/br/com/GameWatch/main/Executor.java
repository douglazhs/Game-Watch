//Nome: Douglas Henrique de Souza Pereira
//Matricula: UC19107076

package br.com.GameWatch.main;

import br.com.GameWatch.utils.Utils;
import br.com.GameWatch.utils.View;

public class Executor {
	public static void main(String[] args) {
		int op;
		int opList;
		String[] options = {"MAIOR NOTA", "MENOR NOTA"};
		Program gameWatch = Utils.createProgram();
		
		do {
			op = View.inputInt("MENU PRINCIPAL", "[1] JOGO"
					                         + "\n[2] LISTA DE GAMES"
					                         + "\n[3] GRUPOS DE CRACK"
					                         + "\n[4] HELP"
					                         + "\n[5] SOBRE PROGRAMA"
					                         + "\n[6] SAIR");
			switch(op) {
				case 1:
					try {
						gameMenu(gameWatch);
					}catch(IllegalArgumentException e) {
						View.showError("ERRO", e.getMessage());
					}
					break;
				case 2:
					try {
						opList = View.menu(options, "LISTAGEM", "COMO DESEJA VER OS JOGOS?");
						if(gameWatch.getGames().size() > 0)
							View.showMessage("JOGOS", gameWatch.listOrderedGames(opList));
						else
							View.showWarning("WARNING", "AINDA NAO EXISTEM JOGOS CADASTRADOS NO SISTEMA."
									                + "\nCASO QUEIRA CADASTRAR, VA EM JOGO > CADASTRAR.");
					}catch(IllegalArgumentException e) {
						View.showError("ERRO", e.getMessage());
					}
					break;
				case 3:
					try {
						groupMenu(gameWatch);
					}catch(IllegalArgumentException e) {
						View.showError("ERRO", e.getMessage());
					}
					break;
				case 4:
					View.showMessage("PROTECAO", gameWatch.getAboutProtection());
					break;
				case 5:
					try {
						View.showMessage("PROGRAMA", gameWatch.toString());
					}catch(IllegalArgumentException e) {
						View.showError("ERRO", e.getMessage());
					}
					break;	
			}
		}while(op != 6);
	}
	
	public static void gameMenu(Program gameWatch) {
		int op;
		int opUser;
		int opGame;
		String gameName;
		Game game;
		String[] optionsUser = {"SIM", "NAO"};
		String[] options = {"COMENTARIOS", "REQUISITOS", "VOLTAR"};
		
		do {
			op = View.inputInt("MENU JOGO", "[1] ADICIONAR"
						                + "\n[2] EDITAR"
						                + "\n[3] VER JOGO"
						                + "\n[4] AVALIAR"
						                + "\n[5] VOLTAR");
			switch(op) {
				case 1:
					try {
						opUser = View.menu(optionsUser, "USUARIO", "E UM NOVO USUARIO?");
						gameWatch.addGame(opUser);
					}catch(IllegalArgumentException e) {
						View.showError("ERRO", e.getMessage());
					}
					break;
				case 2:
					try {
						gameName = View.inputString("SEARCH", "NOME DO JOGO: ");
						game = gameWatch.searchGame(gameName);
						if(game != null) {//Caso o game seja encontrado na base de dados.
							opUser = View.menu(optionsUser, "USUARIO", "E UM NOVO USUARIO?");
							gameWatch.editGame(opUser, game);//opUser passado como parametro para o metodo editGame() faca o tratamento.
						}else
							View.showError("ERRO", "JOGO NAO ENCONTRADO NA BASE DE DADOS.");
					}catch(IllegalArgumentException e) {
						View.showError("ERRO", e.getMessage());
					}
					break;
				case 3:
					try {
						gameName = View.inputString("SEARCH", "NOME DO JOGO: ");
						game = gameWatch.searchGame(gameName);
						if(game != null) {//Caso o game seja encontrado na base de dados.
							opGame = View.menu(options, "GAME", game.toString());
							if(opGame == 1){//Opcao para o usuario ver as avaliacoes de outros usuarios sobre o game.
								if(game.showPosts() != "")
									View.showMessage("GAME", game.showPosts());
								else
									View.showWarning("WARNING", "ESTE JOGO AINDA NÃO POSSUI COMENTARIOS."
											                + "\nCASO QUEIRA ADICIONAR UM VA EM JOGO > AVALIAR");
							}else if(opGame == 2) {
								if(game.getRequirement() != null) {
									View.showMessage("GAME", game.getRequirement().toString());
								}else {
									View.showWarning("ERRO", "AINDA NAO FORAM ADICIONADOS OS REQUISITOS MINIMOS."
											           + "\nCASO QUEIRA ADICIONAR VA EM JOGO > EDITAR > REQUISITOS.");
								}
							}
						}else {
							View.showError("ERRO", "JOGO NAO ENCONTRADO.");
						}
					}catch(IllegalArgumentException e) {
						View.showError("ERRO", e.getMessage());
					}
					 break;
				case 4:
					try {
						gameName = View.inputString("SEARCH", "NOME DO JOGO: ");
						game = gameWatch.searchGame(gameName);
						if(game != null) {//Caso o jogo ja exista na base de dados.
							opUser = View.menu(optionsUser, "USUARIO", "E UM NOVO USUARIO?");
							gameWatch.avaliateGame(game, opUser);//opUser passado como parametro para o metodo avaliateGame() faca o tratamento.
						}else
							View.showError("ERRO", "JOGO NAO ENCONTRADO NA BASE DE DADOS.");
					}catch(IllegalArgumentException e) {
						
					}
					break;
			}
		}while(op != 5);
	}
	
	public static void groupMenu(Program gameWatch) {
		int op;
		int opUser;
		int opGroup;
		String[] options = {"SIM", "NAO"};
		String[] groupOptions = {"COMENTARIOS", "VOLTAR"};
		String groupName;
		Group group;
		
		do {
			op = View.inputInt("MENU DE GRUPO", "[1] VER GRUPO"
					                        + "\n[2] DEIXAR MENSAGEM"
					                        + "\n[3] VOLTAR");
			switch(op) {
				case 1:
					try {
						groupName = View.inputString("BUSCA", "NOME DO GRUPO QUE DESEJA: ");
						group = gameWatch.searchGroup(groupName);
						if(group != null) {//Caso o grupo ja conste na base de dados.
							opGroup = View.menu(groupOptions ,"GRUPOS", group.toString());
							if(opGroup == 1) {//Caso o usuario queira ver as avaliacoes do grupo.
								if(group.getPosts() != null)
									View.showMessage("GRUPO", group.showPosts());
								else 
									View.showWarning("WARNING", "O GRUPO AINDA NAO POSSUI MENSAGENS."
											                + "\nCASO QUEIRA AVALIAR VA EM GRUPOS DE CRACK > DEIXAR MENSAGEM.");
							}
						}else
							View.showWarning("WARNING", "AINDA NAO TEMOS NENHUM GRUPO CADASTRADO NO SISTEMA.");
					}catch(IllegalArgumentException e) {
						View.showError("ERRO", e.getMessage());
					}
					
					break;
				case 2:
					try {
						groupName = View.inputString("BUSCA", "NOME DO GRUPO QUE DESEJA: ");
						group = gameWatch.searchGroup(groupName);
						if(group != null) {//Caso o grupo seja encontrado ja na base de dados.
							opUser= View.menu(options, "GRUPO", "E UM NOVO USUARIO?");
							gameWatch.avaliateGroup(group, opUser);//opUser passado como parametro para o metodo avaliateGroup() faca o tratamento.
						}else
							View.showError("ERRO", "GRUPO NAO ENCONTRADO NA BASE DE DADOS.");
					}catch(IllegalArgumentException e) {
						View.showError("ERRO", e.getMessage());
					}
					break;
			}
		}while(op != 3);
	}
}
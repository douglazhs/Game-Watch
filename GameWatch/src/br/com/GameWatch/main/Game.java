package br.com.GameWatch.main;

import java.util.ArrayList;

import br.com.GameWatch.utils.Utils;

public class Game implements Avaliation{
	private String name;
	private String launchDate;
	private String description;
	private String protection;
	private double note;
	private boolean cracked;
	private Group group;
	private Requirements requirement;
	private ArrayList<String> stores = new ArrayList<>();
	private ArrayList<Genre> genres = new ArrayList<>();
	private ArrayList<Post> posts = new ArrayList<>();

	public Game(String name, String launchDate, String description, boolean cracked, Group group, ArrayList<Genre> genres, ArrayList<String> stores, String protection, Requirements requirement) {
		setName(name);
		setLaunchDate(launchDate);
		setDescription(description);
		setGroup(group);
		setGenres(genres);
		setCracked(cracked);
		setStores(stores);
		setProtection(protection);
		setRequirement(requirement);
	}
	
	public void addPost(User user, boolean anonymous) {//Adicionar avaliacao ao grupo com o usuario identificado.
		Post post = Utils.createPost(user, anonymous);
		this.posts.add(post);
		setAvaliationNote(this.getNote(this.posts));//Atualizando a nota do game.
	}
	
	public void addPost(boolean anonymous) {//Adicionar avaliacao ao grupo com o usuario anonimo.
		Post post = Utils.createPost(null, anonymous);
		this.posts.add(post);
		setAvaliationNote(this.getNote(this.posts));//Atualizando a nota do game.
	}
	
	public String showPosts() {
		String total = "";
		
		for (Post post : this.posts) {
			total += post.toString();
		}
		return total;
	}
		
	//Getters and Setters ----------------------------------------------------------------------------------------------------------
	
	public Requirements getRequirement() {
		return requirement;
	}

	public void setRequirement(Requirements requirement) {//Pode ser nulo.
		this.requirement = requirement;
	}

	public String getProtection() {
		return protection;
	}

	public void setProtection(String protection) {//Protecao pode ser nula.
		this.protection = protection;
	}
	
	public ArrayList<String> getStores() {
		return stores;
	}

	public void setStores(ArrayList<String> stores) {
		if(stores != null)
			this.stores = stores;
		else
			throw new IllegalArgumentException("ERRO EM LOJAS.");
	}

	public double getAvaliationNote() {
		return note;
	}

	public void setAvaliationNote(double note) {
		if(note >= 0 && note <= 5)
			this.note = note;
		else
			throw new IllegalArgumentException("ERRO NA NOTA MEDIA DO JOGO.");
	}

	public ArrayList<Genre> getGenres() {
		return this.genres;
	}

	public void setGenres(ArrayList<Genre> genres) {
		if(genres != null)
			this.genres = genres;
		else
			throw new IllegalArgumentException("ERRO EM GENEROS.");
	}

	public boolean isCracked() {
		return cracked;
	}

	public void setCracked(boolean cracked) {
		this.cracked = cracked;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;//Grupo pode ser nulo, pois pode nao estar crackeado ainda.
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(!name.isEmpty() && !Utils.isNumber(name))
			this.name = name;
		else
			throw new IllegalArgumentException("NOME INVALIDO.");
	}
	
	public String getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(String launchDate) {//Ja validada na classe Utils, no metodo createGame().s
		this.launchDate = launchDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(!description.isEmpty())
			this.description = description;
		else throw new IllegalArgumentException("DESCRICAO INVALIDA.");
	}
	
	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		if(posts != null)
			this.posts = posts;
		else throw new IllegalArgumentException("ERRO EM POSTS.");
	}

	//toString -----------------------------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		String total = "";
		String date = this.launchDate.substring(0, 2) + "/" + this.launchDate.substring(2, 4) + "/" + this.launchDate.substring(4, 8); 
		int i = 0;
		
		total += "NOME: " + this.name;
		total += "\nDESCRICAO: " + this.description;
		total += "\nDATA DE LANCAMENTO: " + date;
		total += "\nGENERO: ";
		for(Genre g : this.genres) {
			total += g.getName();
			i++;
			if(this.genres.size() != i)
				total += ", ";
		}
		total += "\nPROTECAO: " + this.protection;
		total += "\nNOTA: " + this.note;
		if(this.cracked) {
			total += "\nCRACK: CRACKED";
			
			total += "\nGRUPO[CRACK]: " + this.group.getName();
		}else
			total += "\nCRACK[CRACK]: UNCRACKED";
		total += "\nLOJAS DISPONIVEIS: ";
		i = 0;
		for (String s : this.stores) {
			total += s;
			i++;
			if(this.stores.size() != i)
				total += ", ";
		}
		return total;
	}
	
	//Metodo de Avaliacao ----------------------------------------------------------------------------------------------------
	
	@Override
	public double getNote(ArrayList<Post> posts) {
		double mediaNote = 0;
		
		for(Post post : posts) {
			mediaNote += post.getComment().getAvaliationNote();
		}
		mediaNote = mediaNote/posts.size();
		return mediaNote;
	}

}

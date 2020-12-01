package br.com.GameWatch.main;

import java.util.ArrayList;

import br.com.GameWatch.utils.Utils;

public class Group implements Avaliation{
	private String name;
	private String description;
	private double avaliationNote;
	private ArrayList<Member> members = new ArrayList<>();
	public ArrayList<Post> posts = new ArrayList<>();
	
	public Group(String name, String description, ArrayList<Member> members) {
		setDescription(description);
		setName(name);
		setMembers(members);
	}
	
	public void addPost(User user, boolean anonymous) {//Adicionar avaliacao ao grupo com o usuario identificado.
		Post post = Utils.createPost(user, anonymous);
		this.posts.add(post);
		this.setAvaliationNote(this.getNote(this.posts));
	}
	
	public void addPost(boolean anonymous) {//Adicionar avaliacao ao grupo com o usuario anonimo.
		Post post = Utils.createPost(null, anonymous);
		this.posts.add(post);
		this.setAvaliationNote(this.getNote(this.posts));
	}
	
	public String showPosts() {
		String total = "";
		
		for (Post post : this.posts) {
			total += post.toString();
			total += "\n\n";
		}
		return total;
	}
	
	//Getters and Setters ----------------------------------------------------------------------------------------------------
	
	public double getAvaliationNote() {
		return avaliationNote;
	}

	public void setAvaliationNote(double avaliationNote) {
		if(avaliationNote >=0 && avaliationNote <= 5)
			this.avaliationNote = avaliationNote;
		else
			throw new IllegalArgumentException("NOTA INVALIDA.");
	}

	public ArrayList<Member> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Member> members) {
		if(members != null)
			this.members = members;
		else throw new IllegalArgumentException("ERRO EM MEMBROS.");
	}
	
	public ArrayList<Post> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<Post> posts) {
		if(posts != null)
			this.posts = posts;
		else
			throw new IllegalArgumentException("ERRO EM POSTS.");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(!name.isEmpty())
			this.name = name;
		else
			throw new IllegalArgumentException("NOME INVALIDO.");
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(!description.isEmpty())
			this.description = description;
		else 
			throw new IllegalArgumentException("DESCRICAO INVALIDA.");
	}
	
	@Override
	public String toString() {
		String total = "";
		int i = 0;
		
		total += "[GRUPO]";
		total += "\nNOME: " + this.name;
		total += "\nDESCRICAO: " + this.description;
		total += "\nNOTA: " + this.avaliationNote;
		total += "\nMEMBROS: ";
		for (Member member : this.members) {
			total += member.getName();
			i++;
			if(this.members.size() != i)
				total += ", ";
		}
		total += "\n\n";
		return total;
	}
	
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

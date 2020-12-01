package br.com.GameWatch.main;

public class Post {
	private User user;
	private Comment comment;
	
	public Post(Comment comment, User user) {//Construtor para usuario identificado.
		setComment(comment);
		setUser(user);
	}
	
	public Post(Comment comment) {//Construtor para usuario anonimo.
		setComment(comment);
	}
	
	//Getters and Setters -------------------------------------------------------------------------------------------------
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if(user != null)
			this.user = user;
		else
			throw new IllegalArgumentException("ERRO EM USUARIO.");
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		if(comment != null)
			this.comment = comment;
		else
			throw new IllegalArgumentException("ERRO EM COMENTARIO.");
	}
	
	@Override
	public String toString() {
		String total = "";
		
		total += "[COMENTARIO]";
		if(this.user != null)
			total += "\n~ @" + this.user.getUserName() + " ~";
		else
			total += "\n~ @usuarioAnonimo ~";
		total += "\nNOTA: " + this.getComment().getAvaliationNote();
		total += "\nMENSAGEM:  " + this.getComment().getMessage();
		total += "\n\n";
		return total;
	}
	
}

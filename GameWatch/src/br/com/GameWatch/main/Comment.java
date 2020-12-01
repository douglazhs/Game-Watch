package br.com.GameWatch.main;

public class Comment {
	private double avaliationNote;
	private String message;
	
	public Comment(String message, double avaliationNote) {
		setAvaliationNote(avaliationNote);
		setMessage(message);
	}

	public double getAvaliationNote() {
		return avaliationNote;
	}

	public void setAvaliationNote(double avaliationNote) {
		if(avaliationNote >= 0 || avaliationNote <= 5)
			this.avaliationNote = avaliationNote;
		else throw new IllegalArgumentException("NOTA INVALIDA.");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if(!message.isEmpty())
			this.message = message;
		else 
			throw new IllegalArgumentException("MENSAGEM INVALIDA.");
	}
}

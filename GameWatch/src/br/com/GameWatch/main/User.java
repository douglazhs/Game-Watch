package br.com.GameWatch.main;

public class User extends Person{
	private String userName;
	private String password;
	private String email;
	
	public User(String name, String userName, String password, String email) {
		setName(name);
		setEmail(email);
		setName(name);
		setUserName(userName);
		setPassword(password);
	}

	//Getters and Setters ------------------------------------------------------------------------------------------------
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		if(!userName.isEmpty())
			this.userName = userName;
		else
			throw new IllegalArgumentException("NOME DE USUARIO INVALIDO.");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(!password.isEmpty())
			this.password = password;
		else
			throw new IllegalArgumentException("SENHA NAO PODE SER VAZIA");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(!email.isEmpty())
			this.email = email;
		else
			throw new IllegalArgumentException("EMAIL INVALIDO.");
	}
	
	//toString ------------------------------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		String total = "";
		
		total += "NOME: " + this.userName;
		total += "\nUSER NAME: " + this.getName();
		return total;
	}
}

package modelo;


public class Admin {
	private String login;
	private String pass;
	
	public Admin(String l, String p) {
		setLogin(l);
		setPass(p);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String Login) {
		login = Login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String Pass) {
		pass = Pass;
	}
	
}

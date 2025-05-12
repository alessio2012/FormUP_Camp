package formup.admin.users;

public class UserBean {
	
	private String nome, cognome, username, password, email;
	private int idUtente;
	
	public String getCognome() { return cognome; }
	public void setCognome(String cognome) { this.cognome = cognome; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	public int getIdUtente() { return idUtente; }
	public void setIdUtente(int idUtente) { this.idUtente = idUtente; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public String getEmail() { return email; }
	public void setEmail( String e ) { this.email = e; }
		
	
	@Override
	public String toString() {
		return "UserBean [nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", password=" + password
				+ ", idUtente=" + idUtente + "]";
	}

	
	
}

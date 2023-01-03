package gui.fx.app.model;

public class User {

	private Long id;
	private String role;
	private String forbidden;
	
	public User() {
		
	}
	
	public User(Long id, String role, String forbidden) {
		super();
		this.id = id;
		this.role = role;
		this.forbidden = forbidden;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getForbidden() {
		return forbidden;
	}
	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + ", forbidden=" + forbidden + "]";
	}
	
}

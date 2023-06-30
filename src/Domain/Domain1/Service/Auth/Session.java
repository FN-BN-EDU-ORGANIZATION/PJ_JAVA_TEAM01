package Domain.Domain1.Service.Auth;

public class Session {
	private String s_id;	//세션 ID
	private String id;		//유저 ID
	private String role;	//유저역할(관리자or회원)
	
	//모든 인자생성자
	//기본 생성자
	//Getter and Setter
	//toString
	
	public Session(){}
	
	public Session(String s_id, String id, String role) {
		super();
		this.s_id = s_id;
		this.id = id;
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "Session [s_id=" + s_id + ", id=" + id + ", role=" + role + "]";
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}

package mini;

public class memberVO {
	private String id;
	private String pw;
	private String nick;
	private String phone;
	private String lv;
	private int exp;
	
	
	// 안에 있는 데이터를 확인하기 위한 메소드
	@Override
	public String toString() {
		return "아이디 : " + id + "\t 별명 : " + nick + "\t 레벨 : " + lv + "\t 경험치 : " + exp;
	}

	public memberVO(String id, String pw, String nick, String phone, String lv, int exp) {
		this.id = id;
		this.pw = pw;
		this.nick = nick;
		this.phone = phone;
		this.lv = lv;
		this.exp = exp;
	}
	
	public memberVO(String id, String nick, String lv, int exp) {
		this.id = id;
		this.nick = nick;
		this.lv = lv;
		this.exp = exp;
	}
	public memberVO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	public memberVO(String nick, String id, String pw) {
		this.nick = nick;
		this.id = id;
		this.pw = id;
	}
	
	// 기본 생성자
	public memberVO() {
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getLv() {
		return lv;
	}
	
	public void setLv(String lv) {
		this.lv = lv;
	}
	
	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
	
	
	
}

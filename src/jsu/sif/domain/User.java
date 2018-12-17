package jsu.sif.domain;

import lombok.Getter;
import lombok.Setter;
@Setter@Getter
public class User {
	private String uid;
	private String username;
	private String password;
	private String country;
	private String phone;
	public User(){}

	public User(String uid, String username, String password, String country, String phone) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.country = country;
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "user [uid=" + uid + ", username=" + username + ", password=" + password + ", phone=" + phone + "]";
	}

	
}

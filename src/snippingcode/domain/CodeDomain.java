package snippingcode.domain;

import java.io.Serializable;

public class CodeDomain implements Serializable {

	public CodeDomain() {

	}

	private long id;

	private String username;

	private String name;

	private String type;

	private String code;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void parseString() {
		System.out.println("*************** code ***************");
		System.out.println("username{" + username + "}");
		System.out.println("name{" + name + "} , type{" + type + "}");
		System.out.println(code);
		System.out.println("*************** code ***************");
	}
}

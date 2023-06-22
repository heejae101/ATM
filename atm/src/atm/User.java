package atm;

import java.util.ArrayList;

public class User {
	
	private int userCode; 	// 중요
	private String name;	// 중요
	private String id;		// 중요
	private String password; // 중요
	private int age;		// 중요
	
	private ArrayList<Account> accs = new ArrayList<Account>();
	
	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}
	

	public User(int userCode, String name, String id, String password) {
		this.userCode = userCode;
		this.name = name;
		this.id = id;
		this.password = password;
	}
	
	public User(int userCode, String name, String id, String password, int age) {
		this.userCode = userCode;
		this.name = name;
		this.id = id;
		this.password = password;
		this.age = age;
	}
	
	public User(int userCode, String name, String id, String password, int age, ArrayList<Account> accs) {
		this.userCode = userCode;
		this.name = name;
		this.id = id;
		this.password = password;
		this.age = age;
		this.accs = accs;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public int getUserCode() {
		return this.userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ArrayList<Account> getAccs(){
		return (ArrayList<Account>) this.accs.clone();
	}

	public void setAccs(ArrayList<Account> accs) {
		this.accs = accs;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		/*
		 * name[userCode] : id/password
		 * L accNumber1(password) : balance1
		 * L accNumber2(password) : balance2
		 * L accNumber3(password) : balance3
		 * L accNumber4(password) : balance4
		 */
		String str = String.format("%s(%d) : %s/%s\n",this.name,this.userCode,this.id,this.password);
		for(int i=0; i<this.accs.size(); i++) {
			str += "\n" + this.accs.get(i);
		}
		return str;
	}
	
}

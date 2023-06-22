package atm;

import java.util.ArrayList;

public class AccountManager {
	
	public static AccountManager instance = new AccountManager();
	private ArrayList<Account> list = new ArrayList<Account>();
	
	private AccountManager() {
		
	}
	
	public static AccountManager getInstance() {
		return instance;
	}
	
	public ArrayList<Account> getList() {
		return this.list;
	}
	
	public void setList(ArrayList<Account> list) {
		this.list = list;
	}
	
	public void add(Account account) {
		list.add(account);
	}
	
	public void remove(Account account) {
		list.remove(account);
	}
	
	
}

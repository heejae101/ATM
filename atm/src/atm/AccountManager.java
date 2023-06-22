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
	
	private int gerateRandomCode() { // XXXXXX-XX-XXXXXX
		int code = 0;
		while(true) {
			code = (int) (Math.random() * 9000) + 1000;
			boolean dupl = false;
			for(Account acc : this.list) {
				if(acc.getUserCode() == code) dupl = true;
			}
			if(!dupl) break;
		}
		
		return code;
	}
	
	private boolean checkAccNumger(int UserCode, int accNumber) {
		for(Account account : this.list) {
			if(account.getUserCode() == UserCode) {
				if(account.getAccNumber() == accNumber)
					return true;
			}
			
		}
		return false;
	}
	
	public void createAcc(User user) {
		Account acc = null;
		int accNumber = gerateRandomCode();
		
		int accPassword = 0;
		do {
			accPassword = Atm.inputNum("���� ��й�ȣ ���� :");
		}while(accPassword == -1);
		
		acc = new Account(user.getUserCode(), accNumber, accPassword);
		this.list.add(acc);
		
		// AccountManager �� list�� �߰��� ��ü�� ������ ���ÿ� ��ȯ����
		// -> user��ü�� ���� acc ���ã�� ��Ͽ��� �߰�
		ArrayList<Account> accs = user.getAccs();
		accs.add(acc);
		user.setAccs(accs);
		System.out.println("���� �輳�� �Ϸ�Ǿ����ϴ�.");
	}
	
	
	public void deleteAcc(User user) {
		int inputCode = Atm.inputNum("���¹�ȣ");
		if(inputCode != -1) {
			if(checkAccNumger(user.getUserCode(),inputCode)) {
				for(int i=0; i<this.list.size(); i++) {
					int userCode = this.list.get(i).getAccNumber();
					if(userCode == inputCode) {
						this.list.remove(i);
						System.out.println("���°� �����Ǿ����ϴ�.");
						break;
					}
				}
			}else {
				System.err.println("�Է��Ͻ� ���¹�ȣ�� ������ ��� ���°� �ƴմϴ�.");
			}
		}
	}
	
	public void viewBalance(User user){
		for(Account account : this.list) {
			if(account.getUserCode() == user.getUserCode()) {
				System.out.println(account);
			}
		}
	}
	
	public void inputMoney(User user) {
		int money = 0;
		
		do {
			money = Atm.inputNum("�Ա��� �ݾ�");
		}while(money == -1);
	
		for(Account account : this.list) {
			if(account.getUserCode() == user.getUserCode()) {
				account.setMoney(money);
				break;
			}
		}
	}

	
}

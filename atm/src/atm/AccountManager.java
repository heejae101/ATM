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
	
	
//	XXXXXX-XX-XXXXXX 6�ڸ�-2�ڸ�-6�ڸ�
	private int gerateRandomCode() {
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
	
	private int getUserMoneyByAccNumber(int log, int accNumber) {
		for(Account account : this.list) {
			if(account.getUserCode() == log && account.getAccNumber() == accNumber) {
				return account.getMoney();
			}
		}
		return -1;
	}
	
	private boolean duplUserAccCode(int accNumber) {
		for(Account account : this.list) {
			if(account.getAccNumber() == accNumber) {
				return true;
			}
		}
		return false;
	}
	
	private void setUserMoneyByUserCode(int log, int AccNumber, int accPassword, int outMoney) {
		for(Account account : this.list) {
			if(account.getAccNumber() == AccNumber && account.getAccPassword() == accPassword) {
				account.setMoney(account.getMoney()-outMoney);
			}
		}
	}
	
//	private boolean checkAccPassword() {
//		
//	}
	
	public void createAcc(User user) {
		int accPassword = 0;
		do {
			accPassword = Atm.inputNum("�����Ͻ� ������ ��й�ȣ�� �Է����ּ���");
		}while(accPassword == -1);
		
		// ���� 
		int accNumber = gerateRandomCode();
		
		Account acc = new Account(user.getUserCode(), accNumber, accPassword);
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
			int inputPassword = Atm.inputNum("���� ��й�ȣ");
			if(checkAccNumger(user.getUserCode(),inputCode)) {
				for(int i=0; i<this.list.size(); i++) {
					int userCode = this.list.get(i).getAccNumber();
					int userAccPassword = this.list.get(i).getAccPassword();
					if(userCode == inputCode && userAccPassword == inputPassword) {
						this.list.remove(i);
						System.out.println("���°� �����Ǿ����ϴ�.");
						break;
					}
				}
			}
			else {
				System.err.println("�Է��Ͻ� ��й�ȣ�� ȸ���� ������ ��ġ���� �ʽ��ϴ�.");
			}
		}else {
			System.err.println("�Է��Ͻ� ���¹�ȣ�� ���� ���¹�ȣ�Դϴ�.");
		}
	}
	
	public void viewBalance(User user){
		System.out.println("ȸ������ ���� ����");
		for(Account account : this.list) {
			if(account.getUserCode() == user.getUserCode()) {
				System.out.println(account);
			}
		}
	}
	
	public void inputMoney(User user) {
		int money = -1;
		int inputCode = -1;
		do {
			money = Atm.inputNum("�Ա��� �ݾ�");
			inputCode = Atm.inputNum("���¹�ȣ");
		}while(money == -1 || inputCode == -1);
	
		for(Account account : this.list) {
			if(account.getUserCode() == user.getUserCode()){
				if(account.getAccNumber()==inputCode) {
					account.setMoney(money);
					break;	
				}
			}
		}
	}
	
	public void outMoney(int log) {
		int inputAccCode = Atm.inputNum("���¹�ȣ");
		for(Account account : this.list) {
			if(account.getUserCode() == log && account.getAccNumber() == inputAccCode) {
				System.out.printf("���� ������ �ܾ�:%d",account.getMoney());
				int inputMoney = Atm.inputNum("�����Ͻ� �ݾ�");
				
				if(inputMoney<account.getMoney()) {
					account.setMoney(account.getMoney()-inputMoney);
					break;
				}else {
					System.out.println("�����Ͻ� �ݾ��� �����մϴ�.");
					break;
				}
			}
		}
	}
	
	public void moveMoney(int log){
		int inputAccNumber = Atm.inputNum("������ ���¹�ȣ");
		int money = getUserMoneyByAccNumber(log, inputAccNumber);
		if(money != -1) {
			int otherUserAccCode = Atm.inputNum("������ ������ ���¹�ȣ");
			if(duplUserAccCode(otherUserAccCode)) {
				int outMoney = Atm.inputNum("������ �ݾ�");
				if(outMoney < money) {
					int accPassword = Atm.inputNum("���� ��й�ȣ");
					setUserMoneyByUserCode(log,inputAccNumber,accPassword,outMoney);
					System.out.printf("%d ��ݵǾ����ϴ�.",outMoney);
				}else {
					System.out.println("�ܾ��� �����մϴ�.");
				}
			}else {
				System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			}
		}else {
			System.out.println("��ġ�ϴ� ���°� �����ϴ�.");
		}
	}
	

}

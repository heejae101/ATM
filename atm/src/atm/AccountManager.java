package atm;

import java.util.ArrayList;

public class AccountManager {
	
	public static AccountManager instance = new AccountManager();
	private ArrayList<Account> list = new ArrayList<Account>();
	
	private AccountManager() {}
	
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
	
	
// TODO: XXXXXX-XX-XXXXXX 6�ڸ�-2�ڸ�-6�ڸ�
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
	
	private boolean duplAccNumber(int accNumber) {
		for(Account account : this.list) {
			if(account.getAccNumber() == accNumber)
				return true;
		}
		return false;
	}
	
	private boolean duplAccNumber(int UserCode, int accNumber) {
		for(Account account : this.list) {
			if(account.getUserCode() == UserCode) {
				if(account.getAccNumber() == accNumber)
					return true;
			}
			
		}
		return false;
	}
	
	
	private boolean checkAccPassword(int accNumber,int accPassword) {
		for(Account account : this.list) {
			if(account.getAccNumber() == accNumber && account.getAccPassword() == accPassword) {
				return true;
			}
		}
		return false;
	}
	
	private Account setUserByUserCode(int log, int accNumber) {
		for(Account account : this.list) {
			if(account.getUserCode() == log && account.getAccNumber() == accNumber) {
				return account;
			}
		}
		return null;
	}
	
	private int getUserMoneyByAccNumber(int log, int accNumber) {
		for(Account account : this.list) {
			if(account.getUserCode() == log && account.getAccNumber() == accNumber) {
				return account.getMoney();
			}
		}
		return -1;
	}
	
	private void setUserMoneyByUserCode(String Operator, int log, int accNumber, int accPassword, int outMoney) {
		for(Account account : this.list) {
			if(account.getAccNumber() == accNumber && account.getAccPassword() == accPassword) {
				if(Operator.equals("+")) {
					account.setMoney(account.getMoney()+outMoney);
					System.out.printf("���� �ܾ�:%d\n",account.getMoney());
				}else if(Operator.equals("-")) {
					account.setMoney(account.getMoney()-outMoney);
					System.out.printf("���� �ܾ�:%d\n",account.getMoney());
				}
			}
		}
	}
	
	private void setOtherUserMoneyByAccCode(int accNumber, int inputMoney) {
		for(Account account : this.list) {
			if(account.getAccNumber() == accNumber) {
				account.setMoney(account.getMoney()+inputMoney);
			}
		}
	}
	
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
	
	public void deleteAcc(int log, User user) {
		int accNumber = Atm.inputNum("���¹�ȣ");
		if (duplAccNumber(log, accNumber)){
			int accPassword = Atm.inputNum("���� ��й�ȣ");
			if (checkAccPassword(accNumber,accPassword)) {
				ArrayList<Account> accs = user.getAccs();
				Account acccount = setUserByUserCode(log,accNumber);
				accs.remove(acccount);
				this.list.remove(acccount);
				user.setAccs(accs);
				System.out.println("���°� �����Ǿ����ϴ�.");
			}
		}
	}
	
	public void viewBalance(int log){
		System.out.println("ȸ������ ���� ����");
		for(Account account : this.list) {
			if(account.getUserCode() == log) {
				System.out.println(account);
			}
		}
	}
	
	public void inputMoney(int log) {
		int accNumber = Atm.inputNum("���¹�ȣ");
		if (duplAccNumber(log, accNumber)){
			int accPassword = Atm.inputNum("���� ��й�ȣ");
			if(checkAccPassword(accNumber,accPassword)) {
				int inputMoney = Atm.inputNum("�Ա��� �ݾ�");
				setUserMoneyByUserCode("+",log,accNumber,accPassword,inputMoney);
			}
		}
	}
	
	public void outMoney(int log) {
		int accNumber = Atm.inputNum("���¹�ȣ");
		
		if (duplAccNumber(log, accNumber)){
			int accPassword = Atm.inputNum("���� ��й�ȣ");
			if (checkAccPassword(accNumber,accPassword)) {
				int money = getUserMoneyByAccNumber(log, accNumber);
				int outMoney = Atm.inputNum("�����Ͻ� �ݾ�");
				
				if(outMoney <= money) {
					setUserMoneyByUserCode("-",log,accNumber,accPassword,outMoney);				
				}else {
					System.err.println("�ܾ��� �����մϴ�.");
				}
				
			}else {
				System.err.println("ȸ�������� ��ġ���� �ʽ��ϴ�.");
			}
		}else {
			System.err.println("��ġ�ϴ� ���¹�ȣ�� �����ϴ�.");
		}
		
	}
	
	public void moveMoney(int log){
		int inputAccNumber = Atm.inputNum("������ ���¹�ȣ");
		int money = getUserMoneyByAccNumber(log, inputAccNumber);
		if(money != -1) {
			int otherUserAccCode = Atm.inputNum("������ ������ ���¹�ȣ");
			if(duplAccNumber(otherUserAccCode)) {
				int outMoney = Atm.inputNum("������ �ݾ�");
				
				if(outMoney <= money) {
					int accPassword = Atm.inputNum("���� ��й�ȣ");
					setUserMoneyByUserCode("-",log,inputAccNumber,accPassword,outMoney);
					setOtherUserMoneyByAccCode(otherUserAccCode,outMoney);
					System.out.printf("%d���� ��ݵǾ����ϴ�.\n",outMoney);
				}else {
					System.err.println("�ܾ��� �����մϴ�.");
				}
				
			}else {
				System.err.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			}
		}else {
			System.err.println("��ġ�ϴ� ���°� �����ϴ�.");
		}
	}
	

}

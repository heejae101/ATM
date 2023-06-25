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
	
	
// TODO: XXXXXX-XX-XXXXXX 6자리-2자리-6자리
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
					System.out.printf("현재 잔액:%d\n",account.getMoney());
				}else if(Operator.equals("-")) {
					account.setMoney(account.getMoney()-outMoney);
					System.out.printf("현재 잔액:%d\n",account.getMoney());
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
			accPassword = Atm.inputNum("생성하실 계좌의 비밀번호를 입력해주세요");
		}while(accPassword == -1);
		
		// 랜덤 
		int accNumber = gerateRandomCode();
		
		Account acc = new Account(user.getUserCode(), accNumber, accPassword);
		this.list.add(acc);
		
		// AccountManager 의 list에 추가된 객체를 생성과 동시에 반환받음
		// -> user객체가 가진 acc 즐겨찾기 목록에도 추가
		ArrayList<Account> accs = user.getAccs();
		accs.add(acc);
		user.setAccs(accs);
		System.out.println("계좌 계설이 완료되었습니다.");
	}
	
	public void deleteAcc(int log, User user) {
		int accNumber = Atm.inputNum("계좌번호");
		if (duplAccNumber(log, accNumber)){
			int accPassword = Atm.inputNum("계좌 비밀번호");
			if (checkAccPassword(accNumber,accPassword)) {
				ArrayList<Account> accs = user.getAccs();
				Account acccount = setUserByUserCode(log,accNumber);
				accs.remove(acccount);
				this.list.remove(acccount);
				user.setAccs(accs);
				System.out.println("계좌가 삭제되었습니다.");
			}
		}
	}
	
	public void viewBalance(int log){
		System.out.println("회원님의 계좌 정보");
		for(Account account : this.list) {
			if(account.getUserCode() == log) {
				System.out.println(account);
			}
		}
	}
	
	public void inputMoney(int log) {
		int accNumber = Atm.inputNum("계좌번호");
		if (duplAccNumber(log, accNumber)){
			int accPassword = Atm.inputNum("계좌 비밀번호");
			if(checkAccPassword(accNumber,accPassword)) {
				int inputMoney = Atm.inputNum("입금할 금액");
				setUserMoneyByUserCode("+",log,accNumber,accPassword,inputMoney);
			}
		}
	}
	
	public void outMoney(int log) {
		int accNumber = Atm.inputNum("계좌번호");
		
		if (duplAccNumber(log, accNumber)){
			int accPassword = Atm.inputNum("계좌 비밀번호");
			if (checkAccPassword(accNumber,accPassword)) {
				int money = getUserMoneyByAccNumber(log, accNumber);
				int outMoney = Atm.inputNum("인출하실 금액");
				
				if(outMoney <= money) {
					setUserMoneyByUserCode("-",log,accNumber,accPassword,outMoney);				
				}else {
					System.err.println("잔액이 부족합니다.");
				}
				
			}else {
				System.err.println("회원정보가 일치하지 않습니다.");
			}
		}else {
			System.err.println("일치하는 계좌번호가 없습니다.");
		}
		
	}
	
	public void moveMoney(int log){
		int inputAccNumber = Atm.inputNum("고객님의 계좌번호");
		int money = getUserMoneyByAccNumber(log, inputAccNumber);
		if(money != -1) {
			int otherUserAccCode = Atm.inputNum("보내실 고객님의 계좌번호");
			if(duplAccNumber(otherUserAccCode)) {
				int outMoney = Atm.inputNum("보내실 금액");
				
				if(outMoney <= money) {
					int accPassword = Atm.inputNum("계좌 비밀번호");
					setUserMoneyByUserCode("-",log,inputAccNumber,accPassword,outMoney);
					setOtherUserMoneyByAccCode(otherUserAccCode,outMoney);
					System.out.printf("%d원이 출금되었습니다.\n",outMoney);
				}else {
					System.err.println("잔액이 부족합니다.");
				}
				
			}else {
				System.err.println("비밀번호가 틀렸습니다.");
			}
		}else {
			System.err.println("일치하는 계좌가 없습니다.");
		}
	}
	

}

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
	
	
//	XXXXXX-XX-XXXXXX 6자리-2자리-6자리
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
	
	
	public void deleteAcc(User user) {
		int inputCode = Atm.inputNum("계좌번호");
		if(inputCode != -1) {
			int inputPassword = Atm.inputNum("계좌 비밀번호");
			if(checkAccNumger(user.getUserCode(),inputCode)) {
				for(int i=0; i<this.list.size(); i++) {
					int userCode = this.list.get(i).getAccNumber();
					int userAccPassword = this.list.get(i).getAccPassword();
					if(userCode == inputCode && userAccPassword == inputPassword) {
						this.list.remove(i);
						System.out.println("계좌가 삭제되었습니다.");
						break;
					}
				}
			}
			else {
				System.err.println("입력하신 비밀번호가 회원님 정보와 일치하지 않습니다.");
			}
		}else {
			System.err.println("입력하신 계좌번호는 없는 계좌번호입니다.");
		}
	}
	
	public void viewBalance(User user){
		System.out.println("회원님의 계좌 정보");
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
			money = Atm.inputNum("입금할 금액");
			inputCode = Atm.inputNum("계좌번호");
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
		int inputAccCode = Atm.inputNum("계좌번호");
		for(Account account : this.list) {
			if(account.getUserCode() == log && account.getAccNumber() == inputAccCode) {
				System.out.printf("현재 계좌의 잔액:%d",account.getMoney());
				int inputMoney = Atm.inputNum("인출하실 금액");
				
				if(inputMoney<account.getMoney()) {
					account.setMoney(account.getMoney()-inputMoney);
					break;
				}else {
					System.out.println("인출하실 금액이 부족합니다.");
					break;
				}
			}
		}
	}
	
	public void moveMoney(int log){
		int inputAccNumber = Atm.inputNum("고객님의 계좌번호");
		int money = getUserMoneyByAccNumber(log, inputAccNumber);
		if(money != -1) {
			int otherUserAccCode = Atm.inputNum("보내실 고객님의 계좌번호");
			if(duplUserAccCode(otherUserAccCode)) {
				int outMoney = Atm.inputNum("보내실 금액");
				if(outMoney < money) {
					int accPassword = Atm.inputNum("계좌 비밀번호");
					setUserMoneyByUserCode(log,inputAccNumber,accPassword,outMoney);
					System.out.printf("%d 출금되었습니다.",outMoney);
				}else {
					System.out.println("잔액이 부족합니다.");
				}
			}else {
				System.out.println("비밀번호가 틀렸습니다.");
			}
		}else {
			System.out.println("일치하는 계좌가 없습니다.");
		}
	}
	

}

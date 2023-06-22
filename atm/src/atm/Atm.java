package atm;

import java.util.ArrayList;
import java.util.Scanner;

public class Atm {
	
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOGIN = 3;
	private final int LOGOUT = 4;
	private final int CEATE_ACC = 5;
	private final int DELETE_ACC = 6;
	private final int VIEW_BALANCE = 7;
	private final int INPUT_MONEY = 8;
	private final int OUT_MONEY = 9;
	private final int MOVE_MONEY = 10;
	private final int SAVE_MONEY = 11;
	private final int LOAD_MONEY = 12;
	private final int QUIT = 13;
	
	public static final Scanner sc = new Scanner(System.in);
	private String brandName;
	private UserManager um;
	private AccountManager am;
	
	public Atm(String brandName) {
		this.brandName = brandName;
	}
	
	private void init() {
		this.um = UserManager.getInstance();
		this.am = AccountManager.getInstance();
	}
	
	private void printAlldata() {
		for(User user: um.getList())
			System.out.println(user);
	}
	
	public void run() {
		// 회원관리 계좌관리 뱅킹서비스 파일처리
		init();
		while(true) {
			printMenu();
			int select = inputNum(this.sc.next());
			if(select == JOIN) um.joinUser();
			else if(select == LEAVE) um.leaveUser();
			else if(select == LOGIN) um.loginUser();
			else if(select == LOGOUT) um.logoutUser();
			else if(select == CEATE_ACC) am.createAcc();
			else if(select == DELETE_ACC) am.deleteAcc();
			else if(select == VIEW_BALANCE) userManager.viewBalance();
			else if(select == INPUT_MONEY) am.inputMoney();
			else if(select == OUT_MONEY) am.outMoney();
			else if(select == MOVE_MONEY) am.moveMoney();
			else if(select == SAVE_MONEY) am.saveMoney();
			else if(select == LOAD_MONEY) am.loadMoney();
			else if(select == QUIT) break;
		}
	}
	
	private void printMenu() {
		System.out.println("===== "+this.brandName+" ATM"+" =====");
		System.out.println("[1] 회원관리");
		System.out.println("[2] 계좌관리");
		System.out.println("[3] 뱅킹서비스");
		System.out.println("[4] 파일처리");
		System.out.println("[5] 계좌개설");
		System.out.println("[6] 계좌철회");
		System.out.println("[7] 계좌조회");
		System.out.println("[8] 입 금");
		System.out.println("[9] 출 금");
		System.out.println("[10] 이 체");
		System.out.println("[11] 저 장");
		System.out.println("[12] 로 드");
		System.out.println("[13] 종료");
		System.out.print("이용하실 서비스를 입력해주세요: ");
	}
	
	private int inputNum(String msg) {
		System.out.println(msg+" : ");
		
		String input = this.sc.next();
		
		int result = -1;
		try {
			result = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("정수만 입력 가능합니다.");
		}
		return result;
	}
	
	private String inputId() {
		System.out.print("아이디를 입력해주세요 :");
		return this.sc.next();
	}
	
	private String inputUserName() {
		System.out.println("사용자 이름을 입력해주세요 :");
		return this.sc.next();
	}
	
	private boolean idDuplCheck(String id) {
		if(this.am.getList().isEmpty()) {
			for(int i=0; i<this.am.getList().size(); i++) {
				if(this.am.getList().get(i).equals(id))
					return true;
			}
		}
		return false;
	}
	
	private void addUser(String id) {
		
	}
}

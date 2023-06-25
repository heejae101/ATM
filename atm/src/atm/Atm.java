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
	private final int SAVE_FILE = 11;
	private final int LOAD_FILE = 12;
	private final int QUIT = 13;
	
	public static final Scanner sc = new Scanner(System.in);
	private String brandName;
	private UserManager userManager;
	private AccountManager accountManager;
	private FileManager fileManager;
	
	/**
	 * Atm 사용자의 로그인 상태를 알수있다. 기본값은 -1
	 * Integer타입의 변수명 로그에 userCode를 저장한다. 
	 */
	private int log;
	
	/**
	 * Atm 시스템의 기본 생성자
	 * @param Atm 시스템의 이름을 가져온다.
	 */
	public Atm(String brandName) {
		this.log = -1;
		this.brandName = brandName;
		this.userManager = UserManager.getInstance();
		this.accountManager = AccountManager.getInstance();
		this.fileManager = FileManager.getInstance();
	}
	
	/**
	 * userManager 객체에서 list를 clone하여 회원 정보를 출력한다.
	 * ArrayList를 clone하면 주소값만 주기 때문에 원본을 수정하면 똑같이 수정이 된다.
	 */
	private void printAlldata() {
		for(User user: userManager.getList())
			System.out.println(user);
	}
	
	/**
	 * 메인 실행 메서드 
	 * ATM 시스템의 기본적인 로직을 보여준다.
	 */
	public void run() {
		while(true) {
			printMenu();
			printAlldata();
			System.out.println(this.log);
			int select = inputNum("메뉴");
			if(select == JOIN && this.log == -1)
				userManager.joinUser();
			else if(select == LEAVE && this.log != -1) 
				this.log = userManager.leaveUser(this.log);
			else if(select == LOGIN && this.log == -1) 
				this.log = userManager.loginUser(this.log);
			else if(select == LOGOUT && this.log != -1) 
				this.log = userManager.logoutUser();
			else if(select == CEATE_ACC && this.log != -1) 
				accountManager.createAcc(userManager.getUserByUserCode(this.log));
			else if(select == DELETE_ACC && this.log != -1) 
				accountManager.deleteAcc(this.log,userManager.getUserByUserCode(this.log));
			else if(select == VIEW_BALANCE && this.log != -1) 
				accountManager.viewBalance(this.log);
			else if(select == INPUT_MONEY && this.log != -1) 
				accountManager.inputMoney(this.log);
			else if(select == OUT_MONEY && this.log != -1) 
				accountManager.outMoney(this.log);
			else if(select == MOVE_MONEY && this.log != -1) 
				accountManager.moveMoney(this.log);
			else if(select == SAVE_FILE) 
				fileManager.saveFile(userManager.getList(), accountManager.getList());
			else if(select == LOAD_FILE) 
				fileManager.loadFile();
			else if(select == QUIT) break;
		}
	}
	
	/**
	 * 사용자가 이용하기 쉽게 기능들을 출력한다.
	 * 로그인 시 인사말과 함께 회원의 이름을 출력한다.
	 */
	private void printMenu() {
		System.out.println("===== "+this.brandName+" ATM"+" =====");
		System.out.print("[1] 회원가입\t");
		System.out.println("[2] 회원탈퇴");
		System.out.print("[3] 로 그 인\t");
		System.out.println("[4] 로그아웃");
		System.out.print("[5] 계좌개설\t");
		System.out.println("[6] 계좌철회");
		System.out.print("[7] 계좌조회\t");
		System.out.println("[8] 입   금");
		System.out.print("[9] 출   금\t");
		System.out.println("[10] 이  체");
		System.out.print("[11] 저  장\t");
		System.out.println("[12] 로  드");
		System.out.println("[13] 종  료");
		if(this.log != -1) {
			printUserState();
		}
	}
	
	/**
	 * 로그인 상태를 출력해 준다.
	 */
	private void printUserState() {
		String username = null;
		ArrayList<User> list = userManager.getList();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getUserCode() == this.log)
				username = list.get(i).getName();
		}
		System.out.printf("\n환영합니다. %s님\n무엇을 도와드릴까요?\n",username);
	}
	
	/**
	 * 사용자에게 입력받아야하는 값이 있을때 사용한다.
	 * @param 사용자에게 입력받을 때 출력할 도움말을 넣는다.
	 * @return 사용자에게 입력 받은 값이 정수일때 입력받은 값을 반환하고 아니면 -1을 반환한다.
	 */
	public static int inputNum(String msg) {
		
		int result = -1;
		while(true) {
			System.out.print(msg+" : ");
			String input = sc.next();
			try {
				result = Integer.parseInt(input);
				break;
			} catch (Exception e) {
				System.err.println("정수만 입력 가능합니다.");
			}
		}
		return result;
	}
}

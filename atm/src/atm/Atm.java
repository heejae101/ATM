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
	private UserManager um;
	private AccountManager am;
	private FileManager fm;
	
	private int log;
	
	public Atm(String brandName) {
		this.log = -1;
		this.brandName = brandName;
		this.um = UserManager.getInstance();
		this.am = AccountManager.getInstance();
		this.fm = FileManager.getInstance();
	}
	
	//������ �ּҰ��� ������ �ȴ�
	private void printAlldata() {
		for(User user: um.getList())
			System.out.println(user);
	}
	
	public void run() {
		// ȸ������ ���°��� ��ŷ���� ����ó��
		while(true) {
			printMenu();
			int select = inputNum("�޴�");
			if(select == JOIN) um.joinUser();
			else if(select == LEAVE) this.log = um.leaveUser(this.log);
			else if(select == LOGIN) this.log = um.loginUser(this.log);
			else if(select == LOGOUT && this.log != -1) this.log = um.logoutUser();
			else if(select == CEATE_ACC && this.log != -1) am.createAcc(um.getUserByUserCode(this.log));
			else if(select == DELETE_ACC && this.log != -1) am.deleteAcc(um.getUserByUserCode(this.log));
			else if(select == VIEW_BALANCE) am.viewBalance(um.getUserByUserCode(this.log));
			else if(select == INPUT_MONEY) am.inputMoney(um.getUserByUserCode(this.log));
//			else if(select == OUT_MONEY) am.outMoney();
//			else if(select == MOVE_MONEY) am.moveMoney();
//			else if(select == SAVE_MONEY) am.saveMoney();
//			else if(select == LOAD_MONEY) am.loadMoney();
			else if(select == QUIT) break;
		}
	}
	
	private void printMenu() {
		System.out.println("===== "+this.brandName+" ATM"+" =====");
		System.out.println("[1] ȸ������");
		System.out.println("[2] ȸ��Ż��");
		System.out.println("[3] �α���");
		System.out.println("[4] �α׾ƿ�");
		System.out.println("[5] ���°���");
		System.out.println("[6] ����öȸ");
		System.out.println("[7] ������ȸ");
		System.out.println("[8] �� ��");
		System.out.println("[9] �� ��");
		System.out.println("[10] �� ü");
		System.out.println("[11] �� ��");
		System.out.println("[12] �� ��");
		System.out.println("[13] ����");
		if(this.log != -1) {
			System.out.printf("%s�� �ȳ��ϼ���. �α��� �����Դϴ�.\n",um.getList().get(this.log).getName());
		}
	}
	
	public static int inputNum(String msg) {
		System.out.print(msg+" : ");
		String input = sc.next();
		
		int result = -1;
		try {
			result = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("������ �Է� �����մϴ�.");
		}
		return result;
	}
}

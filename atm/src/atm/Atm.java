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
		// ȸ������ ���°��� ��ŷ���� ����ó��
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
		System.out.println("[1] ȸ������");
		System.out.println("[2] ���°���");
		System.out.println("[3] ��ŷ����");
		System.out.println("[4] ����ó��");
		System.out.println("[5] ���°���");
		System.out.println("[6] ����öȸ");
		System.out.println("[7] ������ȸ");
		System.out.println("[8] �� ��");
		System.out.println("[9] �� ��");
		System.out.println("[10] �� ü");
		System.out.println("[11] �� ��");
		System.out.println("[12] �� ��");
		System.out.println("[13] ����");
		System.out.print("�̿��Ͻ� ���񽺸� �Է����ּ���: ");
	}
	
	private int inputNum(String msg) {
		System.out.println(msg+" : ");
		
		String input = this.sc.next();
		
		int result = -1;
		try {
			result = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("������ �Է� �����մϴ�.");
		}
		return result;
	}
	
	private String inputId() {
		System.out.print("���̵� �Է����ּ��� :");
		return this.sc.next();
	}
	
	private String inputUserName() {
		System.out.println("����� �̸��� �Է����ּ��� :");
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

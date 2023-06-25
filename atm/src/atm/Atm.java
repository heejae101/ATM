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
	 * Atm ������� �α��� ���¸� �˼��ִ�. �⺻���� -1
	 * IntegerŸ���� ������ �α׿� userCode�� �����Ѵ�. 
	 */
	private int log;
	
	/**
	 * Atm �ý����� �⺻ ������
	 * @param Atm �ý����� �̸��� �����´�.
	 */
	public Atm(String brandName) {
		this.log = -1;
		this.brandName = brandName;
		this.userManager = UserManager.getInstance();
		this.accountManager = AccountManager.getInstance();
		this.fileManager = FileManager.getInstance();
	}
	
	/**
	 * userManager ��ü���� list�� clone�Ͽ� ȸ�� ������ ����Ѵ�.
	 * ArrayList�� clone�ϸ� �ּҰ��� �ֱ� ������ ������ �����ϸ� �Ȱ��� ������ �ȴ�.
	 */
	private void printAlldata() {
		for(User user: userManager.getList())
			System.out.println(user);
	}
	
	/**
	 * ���� ���� �޼��� 
	 * ATM �ý����� �⺻���� ������ �����ش�.
	 */
	public void run() {
		while(true) {
			printMenu();
			printAlldata();
			System.out.println(this.log);
			int select = inputNum("�޴�");
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
	 * ����ڰ� �̿��ϱ� ���� ��ɵ��� ����Ѵ�.
	 * �α��� �� �λ縻�� �Բ� ȸ���� �̸��� ����Ѵ�.
	 */
	private void printMenu() {
		System.out.println("===== "+this.brandName+" ATM"+" =====");
		System.out.print("[1] ȸ������\t");
		System.out.println("[2] ȸ��Ż��");
		System.out.print("[3] �� �� ��\t");
		System.out.println("[4] �α׾ƿ�");
		System.out.print("[5] ���°���\t");
		System.out.println("[6] ����öȸ");
		System.out.print("[7] ������ȸ\t");
		System.out.println("[8] ��   ��");
		System.out.print("[9] ��   ��\t");
		System.out.println("[10] ��  ü");
		System.out.print("[11] ��  ��\t");
		System.out.println("[12] ��  ��");
		System.out.println("[13] ��  ��");
		if(this.log != -1) {
			printUserState();
		}
	}
	
	/**
	 * �α��� ���¸� ����� �ش�.
	 */
	private void printUserState() {
		String username = null;
		ArrayList<User> list = userManager.getList();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getUserCode() == this.log)
				username = list.get(i).getName();
		}
		System.out.printf("\nȯ���մϴ�. %s��\n������ ���͵帱���?\n",username);
	}
	
	/**
	 * ����ڿ��� �Է¹޾ƾ��ϴ� ���� ������ ����Ѵ�.
	 * @param ����ڿ��� �Է¹��� �� ����� ������ �ִ´�.
	 * @return ����ڿ��� �Է� ���� ���� �����϶� �Է¹��� ���� ��ȯ�ϰ� �ƴϸ� -1�� ��ȯ�Ѵ�.
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
				System.err.println("������ �Է� �����մϴ�.");
			}
		}
		return result;
	}
}

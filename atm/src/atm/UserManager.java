package atm;

import java.util.ArrayList;

public class UserManager {
	private AccountManager accManager = AccountManager.getInstance();
	private ArrayList<User> list = new ArrayList<User>();
	
	/**
	 *  Design Pattern ���� ����
	 *  ���� �ν��Ͻ��� ����� �ִ� ����
	 *  1. �����ڸ� �����.
	 *  2. Ŭ���� ���ο��� ���� �ν��Ͻ��� �������ش�.
	 *  3. �ܺο��� ���� �ν��Ͻ��� ������ �� �ֵ��� getter�� ����
	 */
	private UserManager() {}
	
	public static UserManager instance = new UserManager();
	
	public static UserManager getInstance() {
		return instance;
	}
	
	public ArrayList<User> getList(){
		return (ArrayList<User>) this.list.clone();
	}
	
	/**
	 *  duplId �޼���
	 *  @return
	 *  
	 * 
	 */
	private boolean duplId(String id){
		boolean dupl = false;
		for(User user : this.list) {
			if(user.getId().equals(id))
				dupl = true;
		}
		return dupl;
	}
	
	public User getUserByUserCode(int log) {
		for(User user : this.list) {
			if(user.getUserCode() == log)
				return user;
		}
		return null;
	}
	
	private int gerateRandomCode() {
		int code = 0;
		while(true) {
			code = (int) (Math.random() * 9000) + 1000;
			boolean dupl = false;
			for(User user : this.list) {
				if(user.getUserCode() == code) dupl = true;
			}
			if(!dupl) break;
		}
			
		return code;
	}
	
	private int getUserCodebyUserlist(String id) {
		for(User user : this.list) {
			if(user.getId().equals(id))
				return user.getUserCode();
		}
		return -1;
	}
	
	/*
	 * @param log ��, userCode�� �����´�.
	 * @return ����ڰ� �Է��� ��й�ȣ�� ����� ������ ��ġ�� true �ƴϸ� false ��ȯ
	 */
	private boolean checkPassWord(int log) {
		System.out.print("password: ");
		String inputPassword = Atm.sc.next();
		String password = getUserByUserCode(log).getPassword();
		
		if(password.equals(inputPassword)) {
			return true;
		}
		return false;
	}
	
	public void joinUser() {
		// ���� ���� �ڵ� 4�ڸ� ����
		int userCode = gerateRandomCode();
		
		// ������ ���� �̸� ���̵� ��й�ȣ
		System.out.print("name: ");
		String name = Atm.sc.next();		
		System.out.print("id: ");
		String id = Atm.sc.next();
		System.out.print("password: ");
		String password = Atm.sc.next();
		
		if(!duplId(id)) {
			User user = new User(userCode, name, id, password);
			accManager.createAcc(user);
			this.list.add(user);
			System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
		}else {
			System.err.println("�ߺ��Ǵ� ���̵� �Դϴ�. �ٽ��Է����ּ���.");
		}
	}
	
	public int leaveUser(int log) {
		if(checkPassWord(log)) {
			int userIdex = list.indexOf(getUserByUserCode(log));
			this.list.remove(userIdex);
			accManager.getList().remove(userIdex);
			log = -1;
		}
		return log;
	}
	
	public int loginUser(int log) {
		System.out.print("id: ");
		String id = Atm.sc.next();
			
		if(duplId(id)) {
			log = getUserCodebyUserlist(id);
			if(!checkPassWord(log)) log = -1;
		}else {
			System.err.println("���� ���̵� �Դϴ�.");
		}
	
		return log;
	}
	
	public int logoutUser() {
		return -1;
	}
	
}

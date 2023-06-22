package atm;

import java.util.ArrayList;

public class UserManager {
	// Design Pattern ���� ����
	// ���� �ν��Ͻ��� ����� �ִ� ����~
	
	//1. �����ڸ� �����.
	//2. Ŭ���� ���ο��� ���� �ν��Ͻ��� �������ش�.
	//3. �ܺο��� ���� �ν��Ͻ��� ������ �� �ֵ��� getter�� ����
//	��ī��Ʈ ���� ȸ������ ���� 
	private ArrayList<User> list = new ArrayList<User>();
	
	private UserManager() {}
	
	public static UserManager instance = new UserManager();
	
	public static UserManager getInstance() {
		return instance;
	}
	
	
	public ArrayList<User> getList(){
		return (ArrayList<User>) this.list.clone();
	}
	
	private boolean duplId(String id){
		boolean dupl = false;
		for(User user : this.list) {
			if(user.getId().equals(id))
				dupl = true;
		}
		return dupl;
	}
	
	/* idIndex �޼���
	 * �ߺ� üũ�� �ߺ��Ǹ� index�� �ƴϸ� -1 ���
	 * duplId �޼��忡�� for each�� ��� for������ ã�Ƶ� ��
	 */
	private int idIndex(String id) {
		int index = -1;
		for(int i=0; i<this.list.size(); i++) {
			if(this.list.get(i).getId().equals(id)) {
				index = i;
			}
		}
		return index;
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
	
	/* checkPassWord �޼���
	 * �ߺ� üũ�� �ߺ��Ǹ� true �ƴϸ� false ���
	 */
	private boolean checkPassWord(int log) {
		System.out.print("password: ");
		String inputPassword = Atm.sc.next();
		
		String password = this.list.get(log).getPassword();
		if(password.equals(inputPassword)) {
			return true;
		}
		return false;
	}
	
	public void joinUser() {
		int userCode = gerateRandomCode();
		System.out.print("name: ");
		String name = Atm.sc.next();		
		System.out.print("id: ");
		String id = Atm.sc.next();
		System.out.print("password: ");
		String password = Atm.sc.next();
		
		System.out.println();
		
		if(!duplId(id)) {
			User user = new User(userCode, name, id, password);
			this.list.add(user);
			System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
		}else {
			System.err.println("�ߺ��Ǵ� ���̵� �Դϴ�.");
		}
	}
	
	public int leaveUser(int log) {
		if(log != -1) {
			if(checkPassWord(log)) {
				this.list.remove(log);
				log = -1;
			}
		}else {
			System.err.println("�α��� �� �̿����ּ���.");
		}
		return log;
	}
	
	public int loginUser(int log) {
		if(log == -1) {
			System.out.print("id: ");
			String id = Atm.sc.next();
			
			if(duplId(id)) {
				log = idIndex(id);
				System.out.println(log);
				if(!checkPassWord(log)) log = -1;
			}
			else {
				System.err.println("�ߺ��Ǵ� ���̵� �Դϴ�.");
			}
			
		}else {
			System.err.println("���� �α��� ���� �Դϴ�.");
		}
		return log;
	}
	
	public int logoutUser() {
		int result = 0;
		
		return result;
	}
	
	
}

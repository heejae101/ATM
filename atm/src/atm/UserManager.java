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
	
	public void joinUser() {
		int userCode = gerateRandomCode();
		System.out.println("name: ");
		String name = Atm.sc.next();		
		System.out.println("id: ");
		String id = Atm.sc.next();
		System.out.println("password: ");
		String password = Atm.sc.next();
		
		if(duplId(id)) {
			User user = new User(userCode, name, id, password);
			this.list.add(user);
			System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
		}else {
			System.err.println("�ߺ��Ǵ� ���̵� �Դϴ�.");
		}
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
	
	private int gerateRandomCode() {
		int code = 0;
		boolean dupl = false;
		while (true) {
			code = (int)
			for(User user : this.list) {
				if(user.getUserCode() == code)
					dupl = true;
			}
			if(!dupl)
				break;
		}
			
		return code;
	}
}

package atm;

import java.util.ArrayList;

public class UserManager {
	// Design Pattern 설계 패턴
	// 단일 인스턴스만 만들수 있는 패턴~
	
	//1. 생성자를 숨긴다.
	//2. 클래스 내부에서 단일 인스턴스를 생성해준다.
	//3. 외부에서 단일 인스턴스를 참조할 수 있도록 getter를 제공
//	어카운트 파일 회원관리 숙제 
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
			System.out.println("회원가입이 완료되었습니다.");
		}else {
			System.err.println("중복되는 아이디 입니다.");
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

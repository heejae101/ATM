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
	
	/* idIndex 메서드
	 * 중복 체크후 중복되면 index값 아니면 -1 출력
	 * duplId 메서드에서 for each문 대신 for문으로 찾아도 됨
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
	
	/* checkPassWord 메서드
	 * 중복 체크후 중복되면 true 아니면 false 출력
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
			System.out.println("회원가입이 완료되었습니다.");
		}else {
			System.err.println("중복되는 아이디 입니다.");
		}
	}
	
	public int leaveUser(int log) {
		if(log != -1) {
			if(checkPassWord(log)) {
				this.list.remove(log);
				log = -1;
			}
		}else {
			System.err.println("로그인 후 이용해주세요.");
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
				System.err.println("중복되는 아이디 입니다.");
			}
			
		}else {
			System.err.println("현재 로그인 상태 입니다.");
		}
		return log;
	}
	
	public int logoutUser() {
		int result = 0;
		
		return result;
	}
	
	
}

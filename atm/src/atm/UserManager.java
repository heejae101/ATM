package atm;

import java.util.ArrayList;

public class UserManager {
	private AccountManager accManager = AccountManager.getInstance();
	private ArrayList<User> list = new ArrayList<User>();
	
	/**
	 *  Design Pattern 설계 패턴
	 *  단일 인스턴스만 만들수 있는 패턴
	 *  1. 생성자를 숨긴다.
	 *  2. 클래스 내부에서 단일 인스턴스를 생성해준다.
	 *  3. 외부에서 단일 인스턴스를 참조할 수 있도록 getter를 제공
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
	 *  duplId 메서드
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
	 * @param log 즉, userCode를 가져온다.
	 * @return 사용자가 입력한 비밀번호가 사용자 정보와 일치시 true 아니면 false 반환
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
		// 랜덤 유저 코드 4자리 생성
		int userCode = gerateRandomCode();
		
		// 유저의 정보 이름 아이디 비밀번호
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
			System.out.println("회원가입이 완료되었습니다.");
		}else {
			System.err.println("중복되는 아이디 입니다. 다시입력해주세요.");
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
			System.err.println("없는 아이디 입니다.");
		}
	
		return log;
	}
	
	public int logoutUser() {
		return -1;
	}
	
}

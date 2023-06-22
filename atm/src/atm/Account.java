package atm;

public class Account {
	
	private int userCode;
	private int accNumber;
	private int accPassword;
	private int money;
	
	public Account(int userCode, int accNumber, int accPassword){
		this.userCode = userCode;
		this.accNumber = accNumber;
		this.accPassword = accPassword;
	}
	
	public Account(int userCode, int accNumber, int accPassword, int money) {
		this.userCode = userCode;
		this.accNumber = accNumber;
		this.accPassword = accPassword;
		this.money = money;
	}
	
	public int getAccNumber() {
		return accNumber;
	}
	
	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}
	
	public int getAccPassword() {
		return accPassword;
	}

	public int getUserCode() {
		return this.userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public void setAccPassword(int accPassword) {
		this.accPassword = accPassword;
	}

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		return String.format("%d(%d) : %d",this.accNumber,this.accPassword,this.money);
	}
	
}

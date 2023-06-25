package atm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	private File file1;
	private File file2;
	private FileWriter fw1;
	private FileWriter fw2;
	private FileReader fr1;
	private FileReader fr2;
	private BufferedReader br1;
	private BufferedReader br2;
	
	private final String userFile = "UserFile.txt";
	private final String accountFile = "AccountFile.txt";
	
	private FileManager(){}
	
	public static FileManager instance = new FileManager();
	
	public static FileManager getInstance() {
		return instance;
	}
	
	public void saveFile(ArrayList<User> user,  ArrayList<Account> account){
		// 유저 정보 userCode/name/age/id/password/
		// 계정 정보 userCode/accNumber/accPassword/money
		String str1 = "";
		String str2 = "";
		
		if(0 < user.size() && 0 < account.size()) {
			try {
				this.fw1 = new FileWriter(this.userFile);
				this.fw2 = new FileWriter(this.accountFile);
				
				for(int i=0; i<user.size(); i++) {
					str1 += user.get(i).getUserCode()+"/";
					str1 += user.get(i).getName()+"/";
					str1 += user.get(i).getAge()+"/";
					str1 += user.get(i).getId()+"/";
					str1 += user.get(i).getPassword();
					if(i == user.size()-1) {
						str1 += "\n";
					}
				}
				
				for(int i=0; i<account.size(); i++) {
					str2 += account.get(i).getUserCode()+"/";
					str2 += account.get(i).getAccNumber()+"/";
					str2 += account.get(i).getAccPassword()+"/";
					str2 += account.get(i).getMoney();
					if(i == account.size()-1) {
						str2 += "\n";
					}
				}
				
				fw1.write(str1);
				fw2.write(str2);
				fw1.close();
				fw2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadFile() {
		this.file1 = new File(userFile);
		this.file2 = new File(accountFile);
		if(file1.exists() && file2.exists()) {
			try {
				this.fr1 = new FileReader(userFile);
				this.br1 = new BufferedReader(fr1);
				
				this.fr2 = new FileReader(accountFile);
				this.br2 = new BufferedReader(fr2);
				
				
				
				
				
				fr1.close();
				br1.close();
				
				fr2.close();
				br2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("파일이 없습니다.");
		}
		
	}
}

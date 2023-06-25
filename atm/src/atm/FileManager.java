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
		// 유저 정보 userCode/name/id/password/age
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
					str1 += user.get(i).getId()+"/";
					str1 += user.get(i).getPassword()+"/";
					str1 += user.get(i).getAge();
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
				System.out.println("파일 저장 성공!");
			} catch (IOException e) {
				System.out.println("파일 저장 실패!");
			}
		}
	}
	
	public void loadFile(ArrayList<User> user,  ArrayList<Account> account) {
		this.file1 = new File(userFile);
		this.file2 = new File(accountFile);
		if(file1.exists() && file2.exists()) {
			try {
				
				// 유저 정보 userCode/name/id/password/age
				// 계정 정보 userCode/accNumber/accPassword/money
				this.fr1 = new FileReader(userFile);
				this.br1 = new BufferedReader(fr1);
				
				this.fr2 = new FileReader(accountFile);
				this.br2 = new BufferedReader(fr2);
				
				account.clear();
				user.clear();
				
				while(br2.ready()) {
					String[] str = br2.readLine().split("/");
					Account account1 = new Account(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]),Integer.parseInt(str[3]));
					account.add(account1);
				}
				
				while(br1.ready()) {
					String[] str = br1.readLine().split("/");
					User user1 = new User(Integer.parseInt(str[0]),str[1],str[2],str[3],Integer.parseInt(str[4]));
					user.add(user1);
				}
				
				for(int i=0; i<user.size(); i++) {
					for(int j=0; j<account.size(); j++) {
						if(user.get(i).getUserCode() == account.get(j).getUserCode()) {
							user.get(i).addAccs(account.get(j));
						}
					}
				}
				
				fr1.close();
				br1.close();
				
				fr2.close();
				br2.close();
				System.out.println("파일 읽기 성공!");
			} catch (IOException e) {
				System.out.println("파일 읽기 실패!");
			}
		}else {
			System.out.println("파일이 없습니다.");
		}
		
	}
}

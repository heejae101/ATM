package atm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	private File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	private FileManager(){
		
	}
	
	public static FileManager instance = new FileManager();
	
	public static FileManager getInstance() {
		return instance;
	}
	
}

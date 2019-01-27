import java.util.Scanner;

public class InputStream {
	
	private Scanner scanner = new Scanner(System.in);
	
	
	public void closeInputStream(){
		
		this.scanner.close();
		
	}
	
	public Scanner getScanner(){
		
		return this.scanner;
	}

}

import java.util.Scanner;

public class InputStream {
	
	private static Scanner scanner = new Scanner(System.in);
	
	
	public static void closeInputStream(){
		
		InputStream.scanner.close();
		
	}
	
	public static Scanner getScanner(){
		
		return InputStream.scanner;
	}

}

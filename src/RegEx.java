import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
	
	private Pattern pattern;
	private Matcher matcher;
	private static final String HOUR_PATTERN = "^[0-9]{2}:[0-9]{2}$";
	
	public RegEx(){
		pattern = Pattern.compile(HOUR_PATTERN);
		
	}
	
	public boolean validateHour(final String username){
		
		matcher = pattern.matcher(username);
		return matcher.matches();
	}

}

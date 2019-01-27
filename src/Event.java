import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Event {
	private char type;
	private char marker; 
	private Calendar timeOfEvent;
	private String description;
	int year = 2019;

	
	public Event() {
		
		this.type = 'e';
		this.marker = 'e';
		this.timeOfEvent = null;
		this.description = null;
		
	}

	public Event(char type, char marker, Calendar timeOfEvent, String description) {
		this.type = type;
		this.marker = marker;
		this.timeOfEvent = timeOfEvent;
		this.description = description;
	}
	
	public void setTime() {

		int month = this.setMonth();
		int date = this.setDate(month);
		String hour = this.setHour();
		String[] hourAndMin = hour.split(":");
		
		Calendar timeOfEvent = new GregorianCalendar();
		if(month==-1||date==-1){
			throw new IllegalArgumentException();
		}
		timeOfEvent.set(this.year,month, date, Integer.valueOf(hourAndMin[0]),Integer.valueOf(hourAndMin[1]) );
		this.timeOfEvent=timeOfEvent;
		
	}
	public int setMonth(){
		//1-12
		int month;
		boolean validMonth = false;
		InputStream inStream = new InputStream();
		while (!validMonth) {
			try {
				System.out.println("Enter month(1-12)");
				month = inStream.getScanner().nextInt();
				inStream.getScanner().nextLine();
				if (this.validMonth(month)) {
					validMonth = true;
					return month-1;
					
				} else {
					throw new IllegalArgumentException();
				}
			}catch(InputMismatchException im){
				validMonth = false;
				inStream.getScanner().nextLine();
				System.out.println("IVALID MONTH");
			}catch (IllegalArgumentException e) {
				validMonth = false;
				inStream.getScanner().nextLine();
				System.out.println("IVALID MONTH");
			}
		}
		return -1;
		
	}
	public int setDate(int month){
		// 1-31/1,3,5,7,8,10,12 | 1-30/2,4,6,9,11
		int date;
		boolean validDate= false;

		InputStream inStream = new InputStream();
		while (!validDate) {
			try {
				System.out.println("Enter date");
				date = inStream.getScanner().nextInt();
				inStream.getScanner().nextLine();
				if (this.validDate(date, month)) {
					validDate = true;
					return date;
				}
			} catch (InputMismatchException e) {
				System.out.println("Wrong date!!!");
				inStream.getScanner().nextLine();
				validDate = false;
			}

			catch (IllegalArgumentException e) {
				System.out.println("INVALID DATE");
				inStream.getScanner().nextLine();
			}
		}
		return -1;
	}
	
	public String setHour(){
		//0-23||0-59
		boolean validHour = false;
		String hour;
		InputStream inStream = new InputStream();
		RegEx regEx = new RegEx();
		
		while (!validHour) {
			try {
				System.out.println("Enter hour (HH:MM)");
				hour = inStream.getScanner().nextLine();
				//edit regEx
				validHour = regEx.validateHour(hour);
				if (validHour) {
				return hour;
				}else{
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException e) {
				System.out.println("INVALID HOUR FORMAT HH:MM");
			}
		}
		return "Error with setHour";
	}

	protected char getType() {
		return type;
	}

	protected void setType() {
		boolean check = false;
		InputStream inStream = new InputStream();
		
		while (!check) {
			try {
				Scanner sc = inStream.getScanner();
				System.out.println("Enter what type is the event");
				System.out.println("For MEETING m or M");
				System.out.println("For TASK t or T");
				char ch = sc.next().charAt(0);
				System.out.println(ch);
				if (this.validType(ch)) {
					this.type = ch;
					check = true;
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Wrong type try again");
			}
		}
	}

	protected char getMarker() {
		return marker;
	}

	protected void setMarker() {
		boolean check = false;
		char ch;
		InputStream inStream = new InputStream();
		Scanner sc = inStream.getScanner();
		while (!check) {
			try {
				System.out.println("1 for public");
				System.out.println("2 for private");
				System.out.println("3 for confidential");
				ch = sc.next().charAt(0);
				if (ch == '1' || ch == '2' || ch == '3') {
					this.marker = ch;
					check = true;
				} else {
					System.out.println("Wront marker");
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Wrong input Try again");
				System.out.println();
			}
		}

	}

	protected Calendar getTimeOfEvent() {
		return timeOfEvent;
	}

	protected void setTimeOfEvent() {
		int month = this.setMonth();
		int date = this.setDate(month);
		String hour = this.setHour();
		InputStream inStream = new InputStream();
		Scanner sc = inStream.getScanner();
		RegEx regEx = new RegEx();
		try {
			System.out.println("Enter time of the Event |month|date|hour|minutes");
			System.out.println("Enter month(1-12)");
			month = sc.nextInt();
			if (month <= 12 && month >= 1) {
				System.out.println("Month valid");
			} else {
				System.out.println("Wrong month");
				throw new IllegalArgumentException();
			}
			System.out.println("Enter date");
			date = sc.nextInt();
			sc.nextLine();
			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				if (date <= 31 && date >= 1) {
					System.out.println("Date valid");
				} else {
					System.out.println("Wrong date");
					throw new IllegalArgumentException();
				}
			} else if (month == 2) {
				if (date <= 28 && date >= 1) {
					System.out.println("Date valid");
				} else {
					System.out.println("Wrong date");
					throw new IllegalArgumentException();
				}

			} else {
				if (date <= 30 && date >= 1) {
					System.out.println("Date valid");
				} else {
					System.out.println("Wrong date");
					throw new IllegalArgumentException();
				}
			}
			System.out.println("Enter hour (HH:MM)");
			hour = sc.nextLine();
			boolean validHour = regEx.validateHour(hour);
			if (validHour) {
				String[] splitHour = hour.split(":");
				this.timeOfEvent.set(this.year, month -1, date, Integer.parseInt(splitHour[0]),
						Integer.parseInt(splitHour[1]));
			} else {
				System.out.println("Wrong hour format");
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			System.out.println("WRONG DATE FORMAT TRY AGAIN !!!");
		} catch (InputMismatchException e) {
			System.out.println("Wrong date!!!");
		}
	}

	public boolean validType(char ch) {
		if (ch == 'M' || ch == 'T' || ch == 'm' || ch == 't') {
			return true;
		} else {
			System.out.println("Wrong type");
			throw new IllegalArgumentException();
		}
	}

	public boolean validMarker(char ch) throws IllegalArgumentException {
		if (ch == '1' || ch == '2' || ch == '3') {
			return true;
		} else {
			System.out.println("Wront marker");
			throw new IllegalArgumentException();
		}
	}

	public boolean validMonth(int month) throws IllegalArgumentException {
		if (month <= 12 && month >= 1) {
			System.out.println("Month valid");
			return true;
		} else {
			System.out.println("Wrong month");
			throw new IllegalArgumentException();
		}
	}

	public boolean validDate(int date, int month) throws IllegalArgumentException {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			if (date <= 31 && date >= 1) {
				System.out.println("Date valid");
				return true;
			} else {
				System.out.println("Wrong date");
				throw new IllegalArgumentException();
			}
		} else if (month == 2) {
			if (date <= 28 && date >= 1) {
				System.out.println("Date valid");
				return true;
			} else {
				System.out.println("Wrong date");
				throw new IllegalArgumentException();
			}
		}
		if (date <= 30 && date >= 1) {
			System.out.println("Date valid");
			return true;
		} else {
			System.out.println("Wrong date");
			throw new IllegalArgumentException();
		}
	}

	protected String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		String marker = "EMPTY MARKER";
		String type = "EMPTY TYPE";
		if (this.marker == '1') {
			marker = "Public";
		} else if (this.marker == '2') {
			marker = "Private";
		} else if (this.marker == '3') {
			marker = "Confidential";
		} else {
			System.out.println("wrong marker");
		}
		if (this.type == 'M' || this.type == 'm') {
			type = "Meeting";
		} else {
			type = "Task";
		}
		String ready = "|" + type + "|" + marker + "|" + this.timeOfEvent.get(Calendar.DATE) + "|"
				+ ((this.timeOfEvent.get(Calendar.MONTH) + 1)) + "|" + this.timeOfEvent.get(Calendar.HOUR_OF_DAY) + ":"
				+ this.timeOfEvent.get(Calendar.MINUTE) + "|" + this.description + "|";
		return ready;

	}

	@Override
	public int hashCode() {
		final int num = 3;
		int result = 7;
		int i = 0;
		if (this.type == 'm' || this.type == 'M') {
			i = 1234567;
		} else if (this.type == 't' || this.type == 'T') {
			i = 7;
		}
		result = num * result + ((timeOfEvent == null) ? 0
				: this.getTimeOfEvent().get(Calendar.MONTH) + this.getTimeOfEvent().get(Calendar.DATE)
						+ this.getTimeOfEvent().get(Calendar.HOUR_OF_DAY) + this.getTimeOfEvent().get(Calendar.MINUTE));
		return result * i;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (timeOfEvent == null) {
			if (other.timeOfEvent != null)
				return false;
		} else if (!timeOfEvent.equals(other.timeOfEvent))
			return false;
		return true;
	}

}

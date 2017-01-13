import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class Event {
	private char type; // M = meeting,T = task
	private char marker; // Public - 1,Private -2 ,Confidential -3
	private Calendar timeOfEvent;
	private String description;
	static int year = 2017;

	// SimpleDateFormat ft =
	// new SimpleDateFormat ("E yyyy.dd.MM 'at' HH:mm");
	public Event() {

		Scanner sc = new Scanner(System.in);
		this.setType();
		this.setMarker();
		System.out.println("Enter time of the Event |month|date|hour|minutes");
		this.setTime();
		System.out.println("Enter description");
		this.description = sc.nextLine();

	}

	public void setTime() {
		int zoneSf = 0;
		boolean validHour = false;
		int month;
		int date;
		boolean validMonth = false;
		boolean validDate = false;
		String hour;
		Scanner sc = new Scanner(System.in);
		RegEx regEx = new RegEx();
		/*
		 * String[] ids = TimeZone.getAvailableIDs(2 * 60 * 60 * 1000); if
		 * (ids.length == 0) {
		 * System.out.println("Dont have availble time zones"); System.exit(0);
		 * } SimpleTimeZone zone = new SimpleTimeZone(2 * 60 * 60 * 1000,
		 * ids[37]);
		 */
		Calendar timeOfEvent = new GregorianCalendar();

		while (!validMonth) {
			try {
				System.out.println("Enter month(1-12)");
				month = sc.nextInt();
				if (Event.validMonth(month)) {
					validMonth = true;
					while (!validDate) {
						try {
							System.out.println("Enter date");
							date = sc.nextInt();
							sc.nextLine();
							if (Event.validDate(date, month)) {
								validDate = true;
								while (!validHour) {
									try {
										System.out.println("Enter hour (HH:MM)");
										hour = sc.nextLine();
										validHour = regEx.validateHour(hour);
										if (validHour) {
											String[] splitHour = hour.split(":");
											timeOfEvent.set(Event.year, month - 1, date, Integer.parseInt(splitHour[0]),
													Integer.parseInt(splitHour[1]));
											this.timeOfEvent = timeOfEvent;
										}
									} catch (IllegalArgumentException e) {
										System.out.println("INVALID HOUR FORMAT HH:MM");
									}
								}
							}
						} catch (InputMismatchException e) {
							System.out.println("Wrong date!!!");
							sc.nextLine();
							validDate = false;
						}

						catch (IllegalArgumentException e) {
							System.out.println("INVALID DATE");
						}
					}
				} else {
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException e) {
				System.out.println("IVALID MONTH");
			}
		}
	}

	public Event(char type, char marker, Calendar timeOfEvent, String description) {
		this.type = type;
		this.marker = marker;
		this.timeOfEvent = timeOfEvent;
		this.description = description;
	}

	protected char getType() {
		return type;
	}

	protected void setType() {
		boolean check = false;
		while (!check) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter what type is the event");
				System.out.println("For MEETING m or M");
				System.out.println("For TASK t or T");
				char ch = sc.next().charAt(0);
				System.out.println(ch);
				if (Event.validType(ch)) {
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
		Scanner sc = new Scanner(System.in);
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
		int month;
		int date;
		String hour;
		boolean validHour = false;
		Scanner sc = new Scanner(System.in);
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
			validHour = regEx.validateHour(hour);
			if (validHour) {
				String[] splitHour = hour.split(":");
				this.timeOfEvent.set(Event.year, month - 1, date, Integer.parseInt(splitHour[0]),
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

	public static boolean validType(char ch) {
		if (ch == 'M' || ch == 'T' || ch == 'm' || ch == 't') {
			return true;
		} else {
			System.out.println("Wrong type");
			throw new IllegalArgumentException();
		}
	}

	public static boolean validMarker(char ch) throws IllegalArgumentException {
		if (ch == '1' || ch == '2' || ch == '3') {
			return true;
		} else {
			System.out.println("Wront marker");
			throw new IllegalArgumentException();
		}
	}

	public static boolean validMonth(int month) throws IllegalArgumentException {
		if (month <= 12 && month >= 1) {
			System.out.println("Month valid");
			return true;
		} else {
			System.out.println("Wrong month");
			throw new IllegalArgumentException();
		}
	}

	public static boolean validDate(int date, int month) throws IllegalArgumentException {
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
				+ (this.timeOfEvent.get(Calendar.MONTH) + 1) + "|" + this.timeOfEvent.get(Calendar.HOUR_OF_DAY) + ":"
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

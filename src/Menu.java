import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

//HashMap
public class Menu {

	public static void main(String[] args) {
		int choice;
		int maxId = 0;
		boolean checked = false;
		boolean in = false;
		Scanner sc = InputStream.getScanner();
		Menu.printIntro();
		HashMap<Integer, Event> hashMap = null;
		
		FileOperate.createFile();
		hashMap = FileOperate.loadEventsMappedXML(FileOperate.file);

		maxId = hashMap.size();
		System.out.println(maxId);
		
		while (true) {
			try {
				if (in) {
					sc.nextLine();
					choice = sc.nextInt();
					sc.nextLine();
					in = false;
				} else {
					choice = sc.nextInt();
					sc.nextLine();
				}
				if (choice >= 1 && choice <= 6) {

					switch (choice) {
					case 1:
						System.out.println("1");
						checked = true;

						EventsDisplay.showEvents(hashMap);
						Menu.printIntro();

						break;

					case 2:
						System.out.println("Event adding");
						System.out.println();
						Event event = new Event();
						System.out.println("This is your event");
						System.out.println(event);
						System.out.println("");
						System.out.println("Do you want to add this 2 for NO | Any number for YES");
						try {
							choice = sc.nextInt();
							sc.nextLine();
							if (choice == 2) {
								Menu.printIntro();
								break;
							}
						} catch (InputMismatchException e) {
							System.out.println("Enter number");

						}
						hashMap.put(++maxId, event);
						System.out.println("Event added");
						Menu.printIntro();
						break;

					case 3:
						System.out.println("3");
						if (checked == true) {
							System.out.println(maxId);
							hashMap = EventsOperate.editEvent(hashMap, sc, maxId);
							break;
						} else {
							System.out.println("-------------------------------------------------------");
							System.out.println("-------------------------------------------------------");
							System.out.println("Choose 1 to show event and check your ID to edit event");
							System.out.println("-------------------------------------------------------");
							System.out.println("-------------------------------------------------------");
							System.out.println();
							break;
						}
					case 4:
						System.out.println("4");
						if (checked == true) {
							
							hashMap = EventsOperate.removeEvent(hashMap, sc, maxId);
							System.out.println("REMOVED");
						} else {
							System.out.println("-------------------------------------------------------");
							System.out.println("-------------------------------------------------------");
							System.out.println("Choose 1 to show event and check your ID to edit/remove event");
							System.out.println("-------------------------------------------------------");
							System.out.println("-------------------------------------------------------");
							System.out.println();
							break;
						}
						System.out.println();
						Menu.printIntro();
						break;

					case 5:
						System.out.println("5");
						int date;
						int month;
						System.out.println("Enter 1 for DayView | 2 for WeekView | 3 for MonthView");
						choice = sc.nextInt();
						if (choice >= 0 && choice <= 3) {

							HashMap<Integer, Event> dayMap = null;

							if (choice == 1) {

								System.out.println("Enter month");
								month = sc.nextInt();
								sc.nextLine();
								System.out.println("Enter date");
								date = sc.nextInt();
								sc.nextLine();
								dayMap = new HashMap<Integer, Event>(hashMap);
								EventsDisplay.showDayTableView(dayMap, month, date);
								System.out.println("");
								System.out.println();
								dayMap.clear();
								Menu.printIntro();
								break;
							} else if (choice == 2) {
								System.out.println("Enter month");
								month = sc.nextInt();
								sc.nextLine();
								System.out.println("Enter date");
								date = sc.nextInt();
								sc.nextLine();

								dayMap = new HashMap<Integer, Event>(hashMap);
								EventsDisplay.showWeekTableView(hashMap, month, date);
								System.out.println("");
								System.out.println();
								dayMap.clear();
								Menu.printIntro();
								break;
							} else if (choice == 3) {
								System.out.println("Enter month");
								month = sc.nextInt();
								sc.nextLine();
								dayMap = new HashMap<Integer, Event>(hashMap);
								EventsDisplay.showMonthTableView(dayMap, month);
								System.out.println("");
								System.out.println();
								dayMap.clear();
								Menu.printIntro();
								break;
							}
						} else {
							System.out.println("Invalid value");
							throw new IllegalArgumentException();
						}
						break;

					case 6:
						Event[] eventArr = new Event[hashMap.size()];
						int i = 0;
						for (Map.Entry<Integer, Event> e : hashMap.entrySet()) {
							if (e != null) {
								eventArr[i] = e.getValue();
								i++;
							}
						}
						//FileOperate.loadEventsMappedXML(FileOperate.file);
						eventArr = EventsOperate.sortEvents(eventArr);
						FileOperate.reWriteFileXML(eventArr);
						InputStream.closeInputStream();
						hashMap.clear();
						System.out.println("Exit sucess");
						System.exit(6);

					}
				} else {
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException e) {
				System.out.println("INVALID INPUTss");
				System.out.println("PLEASE TRY AGAIN");
				System.out.println();
				Menu.printIntro();
			} catch (InputMismatchException im) {
				System.out.println("INVALID INPUT");
				System.out.println("Try please again!");
				System.out.println();
				in = true;
				Menu.printIntro();
			} catch (Exception ex) {
				System.out.println("Unknown Error 123");
			}
		}

	}

	public static void printIntro() {

		System.out.println("Hello to DayPlanner aplication.");
		System.out.println("You can add ,edit and display your every day events.");
		System.out.println("Enter 1 to SHOW EVENTS IN LIST");
		System.out.println("Enter 2 to ADD EVENT");
		System.out.println("Enter 3 to EDIT ALREADY ADDED EVENT");
		System.out.println("Enter 4 to DELETE EVENT");
		System.out.println("Enter 5 to SHOW EVENTS IN TABLE");
		System.out.println("Enter 6 to EXIT");

	}

}

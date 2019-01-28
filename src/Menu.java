import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		
		Menu m = new Menu();
		InputStream inStream = new InputStream();
		Scanner sc = inStream.getScanner();
		
		EventsDisplay eventDisplay = new EventsDisplay();
		EventsOperator eventOperator = new EventsOperator();
		

		FileOperator fo = new FileOperator();
		fo.createFile();

		HashMap<Integer, Event> hashMap = null;
		hashMap = fo.loadEventsMappedXML();
		
		int maxId = hashMap.size();
		int choice;
		boolean checked = false;
		boolean in = false;
		
		while (true) {
			try {
				m.printIntro();
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
						
						eventDisplay.showEvents(hashMap);
						
						break;

					case 2:
						
						System.out.println("Event adding");
						System.out.println();
						
						Event event = new Event();
						event.makeEvent();
						
						System.out.println("This is your event");
						System.out.println(event);
						System.out.println("");
						System.out.println("Do you want to add this ?");
						System.out.println("Any key - YES | 2 for NO");
						try {
							choice = sc.nextInt();
							sc.nextLine();
							if (choice == 2) {
								m.printIntro();
								break;
							}
						} catch (InputMismatchException e) {
							System.out.println("Enter number");

						}
						hashMap.put(++maxId, event);
						System.out.println("Event added");
						break;

					case 3:
						
						System.out.println("3");
						if (checked == true) {
							
							hashMap = eventOperator.editEvent(hashMap, sc, maxId);
							break;
						} else {
							
							m.check();
							break;
						}
					case 4:
						
						System.out.println("4");
						if (checked == true) {
							
							hashMap = eventOperator.removeEvent(hashMap, sc, maxId);
							System.out.println("REMOVED");
						} else {
							
							m.check();
							break;
						}
						
						System.out.println();
						break;

					case 5:
						
						System.out.println("5");
						System.out.println("Enter 1 for DayView | 2 for WeekView | 3 for MonthView");
						choice = sc.nextInt();
						if (choice >= 0 && choice <= 3) {

							if (choice == 1) {

								eventDisplay.showDayTableView(hashMap);
								System.out.println("");
								System.out.println();
								
								break;
								
							} else if (choice == 2) {

								eventDisplay.showWeekTableView(hashMap);
								System.out.println("");
								System.out.println();
								
								break;
								
							} else if (choice == 3) {
								
								eventDisplay.showMonthTableView(hashMap);
								System.out.println("");
								System.out.println();
								
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

						eventArr = eventOperator.sortEvents(eventArr);
						fo.reWriteFileXML(eventArr);
						inStream.closeInputStream();
						hashMap.clear();
						System.out.println("Exit sucess");
						System.exit(6);

					}
				} else {
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException e) {
				System.out.println("INVALID INPUT ERROR 1");
				System.out.println("TRY AGAIN");
				System.out.println();
				m.printIntro();
			} catch (InputMismatchException im) {
				System.out.println("INVALID INPUT ERROR 2");
				System.out.println("Try again!");
				System.out.println();
				in = true;
				m.printIntro();
			}catch (Exception ex) {
				System.out.println("Unknown Error 123");
			}
		}

	}
	
	public void check(){
		
		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
		System.out.println("Choose 1 to show event and check your ID to edit event");
		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
		
		System.out.println();
	}
	public void printIntro() {

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

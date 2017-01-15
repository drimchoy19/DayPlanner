import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EventsOperate {

	public static String eventToWrite(Event event) {

		String ready = "*" + event.getType() + "*" + event.getMarker() + "*"
				+ event.getTimeOfEvent().get(Calendar.MONTH) + "*" + event.getTimeOfEvent().get(Calendar.DATE) + "*"
				+ event.getTimeOfEvent().get(Calendar.HOUR_OF_DAY) + "*" + event.getTimeOfEvent().get(Calendar.MINUTE)
				+ "*" + event.getDescription();// +"*";
		
		return ready;

	}

	

	public static Event[] sortEvents(Event[] arr) {
		boolean haveSwap = false;
		Event[] events = new Event[arr.length];
		events=arr;
		Event tmp;
		do {
			haveSwap = false;
			for (int i = 0; i < events.length - 1; i++) {
				if (events[i].getTimeOfEvent().after(events[i + 1].getTimeOfEvent())) {
					tmp = events[i];
					events[i] = events[i + 1];
					events[i + 1] = tmp;
					haveSwap = true;
					// System.out.println("SWAP");
					// a=2 b=1 tmp
					// tmp=a a=b b=tmp
				} else {
					// System.out.println("Already sorted");
				}
			}
		} while (haveSwap);

		return events;
	}

	public static HashMap<Integer,Event> removeEvent(HashMap<Integer,Event> hashMap, Scanner sc, int maxId) {
		int eventToEdit;
		System.out.println("Do you know your ID of the event you want to remove");
		System.out.println("| 1 for YES || 2 for NO |");
			int edit = sc.nextInt();
			sc.nextLine();
		if (edit == 2) {
			System.out.println();
			System.out.println("Check your ID");
			System.out.println();
		}else if (edit == 1) {
			// while(!chosedEvent){
			// try(
			System.out.println("Enter ID of the event you want to remove");
			eventToEdit = sc.nextInt();
			sc.nextLine();
			if (eventToEdit >= 0 && eventToEdit <= maxId) {
				
				hashMap.remove(eventToEdit);
				
			} else {
				System.out.println("Wrong event ID");
				throw new IllegalArgumentException();
			}
			// }catch
			// }
		} return hashMap;
	}

	public static HashMap<Integer,Event> editEvent(HashMap<Integer,Event> hashMap, Scanner sc, int maxId) {

		System.out.println("What you want to edit ?");
		System.out.println("1 for type");
		System.out.println("2 for marker");
		System.out.println("3 for Date");
		System.out.println("4 for Description");
		System.out.println("5 Dont know event ID");
		int edit = sc.nextInt();
		sc.nextLine();
		// eventId=1;
		if (edit <= 4 && edit >= 1) {
			System.out.println("Enter event ID to edit event with this ID");
			int eventToEdit = sc.nextInt();
			sc.nextLine();
			System.out.println(eventToEdit);
			if (eventToEdit >= 0 && eventToEdit <= maxId) {
				// sc.nextInt za ID
				// vzima eventa i nadolu set kakvoto trqbva
				if (edit == 1) {
					System.out.println("Enter new event Type");
					hashMap.get(eventToEdit).setType();
	

				} else if (edit == 2) {
					System.out.println("Enter new event Marker");
					hashMap.get(eventToEdit).setMarker();

				} else if (edit == 3) {
					System.out.println("Enter new event Date");
					hashMap.get(eventToEdit).setTimeOfEvent();

				} else if (edit == 4) {
					System.out.println("Enter new event Description");
					String newDescription = sc.nextLine();
					hashMap.get(eventToEdit).setDescription(newDescription);

				}
			} else {
				System.out.println("Invalid event ID");
				throw new IllegalArgumentException();
			}
		} else if (edit == 5) {
			System.out.println("Choose 1 to see events with ID");
		} else {
			System.out.println("Wrong edit choice!");
			throw new IllegalArgumentException();
		}
			
			System.out.println();
			Menu.printIntro();
			return hashMap;

	}
	public static int countEventsFromHash(HashMap<Integer,Event> hashMap) {
		int count=0;
		for(Map.Entry<Integer,Event> e : hashMap.entrySet()){
			if(e!=null){
				count++;
			}
		}
		return count;
	}

}

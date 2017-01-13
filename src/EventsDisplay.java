import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class EventsDisplay {
	
	public static void showEvents(HashMap<Integer,Event> hashMap){
		
		EventsDisplay.firstRow();
		
		for (Map.Entry<Integer, Event> e : hashMap.entrySet()) {
			String type = "ERROR";
			String marker = "ERROR";
			if (e.getValue().getMarker() == '1') {
				marker = "Public";
			} else if (e.getValue().getMarker() == '2') {
				marker = "Private";
			} else if (e.getValue().getMarker() == '3') {
				marker = "Confidential";
			} else {
				System.out.println("wrong marker");
			}
			if (e.getValue().getType() == 'M' || e.getValue().getType() == 'm') {
				type = "***Meeting***";
			} else {
				type = "---Task---";
			}
			if (e.getKey().intValue() >= 10) {
				System.out.println("|" + e.getKey().intValue() + "|" + marker + "|" + type + "|"
						+ ((e.getValue().getTimeOfEvent().get(Calendar.MONTH)) + 1) + "|"
						+ e.getValue().getTimeOfEvent().get(Calendar.DATE) + "|"
						+ e.getValue().getTimeOfEvent().get(Calendar.HOUR_OF_DAY) + ":"
						+ e.getValue().getTimeOfEvent().get(Calendar.MINUTE) + "|" + e.getValue().getDescription() + "|");

			} else {
				System.out.println("|" + e.getKey().intValue()  + " |" + type + "|" + marker + "|"
						+ ((e.getValue().getTimeOfEvent().get(Calendar.MONTH))+1)  + "|"
						+ e.getValue().getTimeOfEvent().get(Calendar.DATE)+ "|"
						+ e.getValue().getTimeOfEvent().get(Calendar.HOUR_OF_DAY) + ":"
						+ e.getValue().getTimeOfEvent().get(Calendar.MINUTE) + "|" +e.getValue().getDescription() + "|");
			}

		}
	}
		
		public static void showDayTableView(HashMap<Integer,Event> hashMap,int month,int date){
			int count=0;
			month=month-1;
			//boolean removed = false;
			int[] forRemove = new int[hashMap.size()];
			Calendar check1 = new GregorianCalendar();
			check1.set(Event.year, month, ((date)-1), 23, 59);
			Calendar check2 = new GregorianCalendar();
			check2.set(Event.year, month, ((date)+1), 00, 00);
			//HashMap<Integer,Event> dayMap = new HashMap<Integer,Event>();
			for(Map.Entry<Integer, Event> e : hashMap.entrySet()){
				
				if(e.getValue().getTimeOfEvent().after(check2)
						||e.getValue().getTimeOfEvent().before(check1)){
					forRemove[count]=e.getKey();
					count++;
					//System.out.println(forRemove[count]);
				}
			}
			for(int i=0;i<forRemove.length;i++){
				//System.out.println(forRemove[i]);
				hashMap.remove(forRemove[i]);
			}
			EventsDisplay.printTable2(hashMap);
			//pravi den  1
			//proverqva dali e sled 1 i predi 3
			//pravi den 3 
		}
		
		public static void showWeekTableView(HashMap<Integer,Event> hashMap,int month,int date){
			
				int count=0;
				month=month-1;
				boolean removed = false;
				int[] forRemove = new int[hashMap.size()];
				Calendar check1 = new GregorianCalendar();
				check1.set(Event.year, month, ((date)-1), 23, 59);
				Calendar check2 = new GregorianCalendar();
				check2.set(Event.year, month, ((date)+7), 00, 00);
				//HashMap<Integer,Event> dayMap = new HashMap<Integer,Event>();
				for(Map.Entry<Integer, Event> e : hashMap.entrySet()){

					if(e.getValue().getTimeOfEvent().after(check2)
							||e.getValue().getTimeOfEvent().before(check1)){
						forRemove[count]=e.getKey();
						count++;
						//System.out.println(forRemove[count]);
					}
				}
				for(int i=0;i<forRemove.length;i++){
					//System.out.println(forRemove[i]);
					hashMap.remove(forRemove[i]);
				}
				EventsDisplay.printTable2(hashMap);
				//pravi den  1
				//proverqva dali e sled 1 i predi 3
				//pravi den 3 
			
		}

		public static void showMonthTableView(HashMap<Integer,Event> hashMap,int month){
	
			int count=0;
			month=month-1;
			boolean removed = false;
			int[] forRemove = new int[hashMap.size()];
			Calendar check1 = new GregorianCalendar();
			check1.set(Event.year, ((month)-1),31, 23, 59);
			Calendar check2 = new GregorianCalendar();
			check2.set(Event.year, ((month)+1), 0, 00, 00);
			//HashMap<Integer,Event> dayMap = new HashMap<Integer,Event>();
			for(Map.Entry<Integer, Event> e : hashMap.entrySet()){
				
				if(e.getValue().getTimeOfEvent().after(check2)
						||e.getValue().getTimeOfEvent().before(check1)){
					forRemove[count]=e.getKey();
					count++;
					//System.out.println(forRemove[count]);
				}
			}
			for(int i=0;i<forRemove.length;i++){
				//System.out.println(forRemove[i]);
				hashMap.remove(forRemove[i]);
			}
			EventsDisplay.printTable2(hashMap);
			//pravi den  1
			//proverqva dali e sled 1 i predi 3
			//pravi den 3 
		
			
		}
		public static void firstRow(){
			System.out.println("|" + "ID" + "|" + " TYPE   " + "|" + "MARKER " + "|" + "MONTH" + "|"
					+ "DATE" + "|" + " HOUR " + "|DESCRIPTION|");
		}
		
		public static void printTable2(HashMap<Integer,Event> hashMap){
			int n=21;
			int i=0;
			int counterDate = 0;
			int counterHour = 0;
			int counterType = 0;
			int counterMarker = 0;
			int counterDescription = 0;
			int counter = 0;
			String date;
			String time;
			String type;
			String marker;
			String description;
			while(counter<=hashMap.size()){
				System.out.println(String.join("", Collections.nCopies(n*6+12, "-")));
				if(counter==hashMap.size()){
					break;
				}
			for(Map.Entry<Integer, Event> e : hashMap.entrySet()){
				if(e.getKey()==counterDate+7){
					break;
				}
				if(e.getKey()>counterDate){
				date = "Date-"+e.getValue().getTimeOfEvent().get(Calendar.DATE);
				
				System.out.print("|" +date+ String.join("", Collections.nCopies(n - date.length(), " ")) + "|");
			}
				
			}
			System.out.println();
			counterDate+=7;
			for(Map.Entry<Integer, Event> e : hashMap.entrySet()){
				if(e.getKey()==counterHour+7){
					break;
				}
				if(e.getKey()>counterHour){
				time = "Time-"+ e.getValue().getTimeOfEvent().get(Calendar.HOUR_OF_DAY) + ":" 
						+ e.getValue().getTimeOfEvent().get(Calendar.MINUTE);
				
				System.out.print("|" +time+ String.join("", Collections.nCopies(n - time.length(), " ")) + "|");
				}
			}
			System.out.println();
			counterHour+=7;
			/*for(Map.Entry<Integer, Event> e : hashMap.entrySet()){
				if(e.getKey()==counterDate+7){
					System.out.println();
					break;
				}
				if(e.getKey()>counterDate){
				date = "Date-"+e.getValue().getTimeOfEvent().get(Calendar.DATE);
				
				System.out.print("|" +date+ String.join("", Collections.nCopies(n - date.length(), " ")) + "|");
			}*/
			for(Map.Entry<Integer, Event> e : hashMap.entrySet()){
				if(e.getKey()==counterType+7){
					break;
				}
				if(e.getKey()>counterType){
				if (e.getValue().getType() == 'M' || e.getValue().getType() == 'm') {
					type = "Type-Meeting";
				} else {
					type = "Type-Task";
				}
				
				System.out.print("|" +type+ String.join("", Collections.nCopies(n - type.length(), " ")) + "|");
				
				}
			}

			System.out.println("");
			counterType+=7;	
			for(Map.Entry<Integer, Event> e : hashMap.entrySet()){
				if(e.getKey()==counterMarker+7){
					break;
				}
				if(e.getKey()>counterMarker){
				if (e.getValue().getMarker() == '1') {
					marker = "Marker-Public";
				} else if (e.getValue().getMarker() == '2') {
					marker = "Marker-Private";
				} else if (e.getValue().getMarker() == '3') {
					marker = "Marker-Confidential";
				}else{
					marker="ERROR";
				}
				 System.out.print("|" +marker+ String.join("", Collections.nCopies(n - marker.length(), " ")) + "|");
			}
			}
			System.out.println("");
			counterMarker+=7;
			for(Map.Entry<Integer, Event> e : hashMap.entrySet()){
				if(e.getKey()==counterDescription+7){
					break;
				}
				if(e.getKey()>counterDescription){
				description = "Info-" + e.getValue().getDescription();
				System.out.print("|" + description 
						+ String.join("", Collections.nCopies(n - description.length(), " ")) + "|");
			}
			}
			System.out.println("");
			counterDescription+=7;
			counter+=7;
			System.out.println();
		}
			System.out.println(String.join("", Collections.nCopies(n*6+12, "-")));
		}
			
		
		public static void printTable(HashMap<Integer,Event> hashMap){
	
			//hashMap.get(1).getTimeOfEvent().get(Calendar.DATE);
		int n=21;
		String date;
		String time;// = new String[hashMap.size()-1];
		String type;// = new String[hashMap.size()-1];
		String marker;// = new String[hashMap.size()-1];
		String description;// = new String[hashMap.size()-1];

		for(Map.Entry<Integer, Event> e : hashMap.entrySet()){
			
			date = "Date-"+e.getValue().getTimeOfEvent().get(Calendar.DATE);
			
			System.out.print("|" +date+ String.join("", Collections.nCopies(n - date.length(), " ")) + "|");
		}
		System.out.println();
		for(Map.Entry<Integer, Event> e : hashMap.entrySet()){
			
					time = "Time-"+e.getValue().getTimeOfEvent().get(Calendar.HOUR_OF_DAY) + ":" 
					+ e.getValue().getTimeOfEvent().get(Calendar.MINUTE);
			
			System.out.print("|" +time+ String.join("", Collections.nCopies(n - time.length(), " ")) + "|");
			
		}
		System.out.println();
		for(Map.Entry<Integer, Event> e : hashMap.entrySet()){
			
			if (e.getValue().getType() == 'M' || e.getValue().getType() == 'm') {
				type = "Type-Meeting";
			} else {
				type = "Type-Task";
			}
	
	       System.out.print("|" +type+ String.join("", Collections.nCopies(n - type.length(), " ")) + "|");
	
		}
		System.out.println();
		for(Map.Entry<Integer, Event> e : hashMap.entrySet()){
			if (e.getValue().getMarker() == '1') {
				marker = "Marker-Public";
			} else if (e.getValue().getMarker() == '2') {
				marker = "Marker-Private";
			} else if (e.getValue().getMarker() == '3') {
				marker = "Marker-Confidential";
			}else{
				marker="ERROR";
			}
			 System.out.print("|" +marker+ String.join("", Collections.nCopies(n - marker.length(), " ")) + "|");
		}
		System.out.println();
		for(Map.Entry<Integer, Event> e : hashMap.entrySet()){
			description = "Info-" + e.getValue().getDescription();
			System.out.print("|" + description 
					+ String.join("", Collections.nCopies(n - description.length(), " ")) + "|");
		}
		System.out.println();
		
	}
}



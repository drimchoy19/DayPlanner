
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

public class FileOperate {

	static File file;

	public FileOperate() {
		File log = new File("DayPlanner.txt");
		try {
			if (log.exists() == false) {
				System.out.println("We had to make a new file.");
				log.createNewFile();
				FileOperate.file = log;
				FileWriter fw = new FileWriter(log);
				fw.write("*t*1*1*1*00*00*TestEvent");
				fw.close();
			} else {
				System.out.println("We already have this file.");
				FileOperate.file = log;
			}
		} catch (IOException e) {
			System.out.println("Can't create file!");
			
		}
	}

	public void writeInFile(Event event) {
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(FileOperate.file, true));
			out.append(EventsOperate.eventToWrite(event));
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("Can't make FileWriter");
			e.printStackTrace();
		}	

	}
	public static void reWriteFile(Event[] events){
		String ready="";
		try{
		FileWriter fileWriter = new FileWriter(FileOperate.file.getAbsolutePath());
		BufferedWriter bWriter = new BufferedWriter(fileWriter);
		for(int i=0;i<events.length;i++){
			ready=ready+EventsOperate.eventToWrite(events[i]);
		}
		bWriter.write(ready);
		bWriter.flush();
		bWriter.close();
		bWriter.close();
		}catch(IOException e){
			System.out.println("Cant REWRITE FILE!");
		}
	}
	public static ArrayList<Event> loadEventsArrayed(File file){
		ArrayList<Event> aList = new ArrayList<Event>();
		Event[] events = EventsOperate.readEvents(file);
		Event[] eventsSorted = EventsOperate.sortEvents(events);
		for(Event a : eventsSorted){
			aList.add(a);
		}
		System.out.println("Events added in ArrayList");
		Collections.reverse(aList);
		return aList;
	}
	
	public static HashMap<Integer,Event> loadEventsMapped(File file){
		HashMap<Integer,Event> hashMap = new HashMap<Integer,Event>();
		Event[] events = EventsOperate.readEvents(file);
		int id = 1;
		for(Event e : events){
			hashMap.put(id, e);
			id++;
		}
		return hashMap;
	}

}

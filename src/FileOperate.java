
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileOperate {

	static File file;

	public FileOperate() {
		/*File log = new File("DayPlanner.txt");
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

		}*/
	}
	
	public static void createFile(){
		File log = new File("DayPlanner.xml");
		try {
			if (log.exists() == false) {
				System.out.println("We had to make a new file.");
				log.createNewFile();
				FileOperate.file = log;
				
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				// root elements
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("events");
				doc.appendChild(rootElement);
				
				Element event = doc.createElement("event");
				rootElement.appendChild(event);

				Attr attr = doc.createAttribute("id");
				attr.setValue(String.valueOf(0));
				event.setAttributeNode(attr);


				Element type = doc.createElement("type");
				type.appendChild(doc.createTextNode("test"));
				event.appendChild(type);

				Element marker = doc.createElement("marker");
				marker.appendChild(doc.createTextNode("test"));
				event.appendChild(marker);

				Element month = doc.createElement("month");
				month.appendChild(doc.createTextNode("test"));
				event.appendChild(month);

				Element date = doc.createElement("date");
				date.appendChild(doc.createTextNode("test"));
				event.appendChild(date);
				
				Element hour = doc.createElement("hour");
				hour.appendChild(doc.createTextNode("test"));
				event.appendChild(hour);
				
				Element minutes = doc.createElement("minutes");
				minutes.appendChild(doc.createTextNode("test"));
				event.appendChild(minutes);
				
				Element description = doc.createElement("Description");
				description.appendChild(doc.createTextNode("test"));
				event.appendChild(description);

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("DayPlanner.xml"));

				transformer.transform(source, result);

				System.out.println("File saved!");
			} else {
				System.out.println("We already have this file.");
				FileOperate.file = log;
			}
		} catch (IOException e) {
			System.out.println("Can't create file!");
		}catch (Exception ex){
			System.out.println("Error 11");
		}
	}

	/*
	 * public void writeInFile(Event event) { PrintWriter out; try { out = new
	 * PrintWriter(new FileWriter(FileOperate.file, true));
	 * out.append(EventsOperate.eventToWrite(event)); out.flush(); out.close();
	 * } catch (IOException e) { System.out.println("Can't make FileWriter");
	 * e.printStackTrace(); } }
	 */
	public static void reWriteFile(Event[] events) {
		String ready = "";
		try {
			FileWriter fileWriter = new FileWriter(FileOperate.file.getAbsolutePath());
			BufferedWriter bWriter = new BufferedWriter(fileWriter);
			for (int i = 0; i < events.length; i++) {
				ready = ready + EventsOperate.eventToWrite(events[i]);
			}
			bWriter.write(ready);
			bWriter.flush();
			bWriter.close();
			bWriter.close();
		} catch (IOException e) {
			System.out.println("Cant REWRITE FILE!");
		}
	}
	
	public static void reWriteFileXML(Event[] events){


			  try {

				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				// root elements
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("events");
				doc.appendChild(rootElement);
				
				
				
				for(int i=0;i<events.length;i++){
					
					Element event = doc.createElement("event");
					rootElement.appendChild(event);

					Attr attr = doc.createAttribute("id");
					attr.setValue(String.valueOf(i+1));
					event.setAttributeNode(attr);


					Element type = doc.createElement("type");
					type.appendChild(doc.createTextNode(String.valueOf(events[i].getType())));
					event.appendChild(type);

					Element marker = doc.createElement("marker");
					marker.appendChild(doc.createTextNode(String.valueOf(events[i].getMarker())));
					event.appendChild(marker);

					Element month = doc.createElement("month");
					month.appendChild(doc.createTextNode(String.valueOf(events[i].getTimeOfEvent().get(Calendar.MONTH))));
					event.appendChild(month);

					Element date = doc.createElement("date");
					date.appendChild(doc.createTextNode(String.valueOf(events[i].getTimeOfEvent().get(Calendar.DATE))));
					event.appendChild(date);
					
					Element hour = doc.createElement("hour");
					hour.appendChild(doc.createTextNode(String.valueOf(events[i].getTimeOfEvent().get(Calendar.HOUR_OF_DAY))));
					event.appendChild(hour);
					
					Element minutes = doc.createElement("minutes");
					minutes.appendChild(doc.createTextNode(String.valueOf(events[i].getTimeOfEvent().get(Calendar.MINUTE))));
					event.appendChild(minutes);
					
					Element description = doc.createElement("Description");
					description.appendChild(doc.createTextNode(events[i].getDescription()));
					event.appendChild(description);
					
				}
				
				

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File("DayPlanner.xml"));

				transformer.transform(source, result);

				System.out.println("File saved!");

			  } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			  } catch (TransformerException tfe) {
				tfe.printStackTrace();
			  }
		
	}
	
	public static HashMap<Integer,Event> loadEventsMappedXML(File file){
		HashMap<Integer,Event> hashMap = new HashMap<Integer,Event>();
		char type;
		char marker;
		int month;
		int date;
		String hour;
		String minutes;
		String description;
		Calendar timeOfEvent = new GregorianCalendar();
		 try {	
			 File inputFile = FileOperate.file;
	         DocumentBuilderFactory dbFactory 
	            = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         
	         NodeList nList = doc.getElementsByTagName("event");
	         
	         Event[] events = new Event[nList.getLength()];
	         for (int temp = 1; temp < nList.getLength(); temp++) {
		            Node nNode = nList.item(temp);
		  
		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		               Element eElement = (Element) nNode;
		               type = eElement.getElementsByTagName("type").item(0).getTextContent().charAt(0);
		               marker = eElement.getElementsByTagName("marker").item(0).getTextContent().charAt(0);
		               month = Integer.valueOf(eElement.getElementsByTagName("month").item(0).getTextContent());
		               date = Integer.valueOf(eElement.getElementsByTagName("date").item(0).getTextContent());
		               hour = eElement.getElementsByTagName("hour").item(0).getTextContent();
		               minutes = eElement.getElementsByTagName("minutes").item(0).getTextContent();
		               description = eElement.getElementsByTagName("Description").item(0).getTextContent();
		               timeOfEvent.set(Event.year, month, date, Integer.valueOf(hour), Integer.valueOf(minutes));
		               events[temp] = new Event(type,marker,timeOfEvent,description);
		               hashMap.put(Integer.valueOf(eElement.getAttribute("id")),events[temp]);
		            
		            }
		            
		         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		System.out.println("LOADED");
		return hashMap;
	}

	public static HashMap<Integer, Event> loadEventsMapped(File file) {
		HashMap<Integer, Event> hashMap = new HashMap<Integer, Event>();
		Event[] events = FileOperate.readEvents(file);
		int id = 1;
		for (Event e : events) {
			hashMap.put(id, e);
			id++;
		}
		return hashMap;
	}
	
	/*public static Event[] readEvent(File file){
		
		
		return null;
	}*/

	public static Event[] readEvents(File file) {
		String input;
		try {
			FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			if (fileInputStream.read() == -1) {
				System.out.println("EMPTY FILE");
				throw new IOException();
			}
			while ((input = bufferedReader.readLine()) != null) {
				String[] splitted = input.split("\\*");
				Event[] events = new Event[splitted.length / 7];
				int a = 0;
				for (int i = 0; i < events.length; i++) {
					Calendar calendar = new GregorianCalendar();
					// char type, char marker, Calendar timeOfEvent, String
					// description
					// *M* | 2* | 0*12*12*30* | Proba123
					calendar.set(Event.year, Integer.parseInt(splitted[a + 2]), Integer.parseInt(splitted[a + 3]),
							Integer.parseInt(splitted[a + 4]), Integer.parseInt(splitted[a + 5]));
					events[i] = new Event(splitted[a].charAt(0), splitted[a + 1].charAt(0), calendar, splitted[a + 6]);

					a += 7;
				}
				bufferedReader.close();
				return events;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Can't open file input stream");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Dont support UTF-8");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO exception");
			e.printStackTrace();
		} finally {
			// zatvarq potocite
		}
		return null;
	}

	public static int countEvents(File file) {
		Event[] events = FileOperate.readEvents(FileOperate.file);
		System.out.println(events.length);
		return events.length;
	}

}

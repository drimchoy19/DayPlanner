
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
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

	public static void createFile() {
		File log = new File("DayPlanner.xml");
		try {
			if (log.exists() == false) {
				System.out.println("We had to make a new file.");
				log.createNewFile();
				FileOperate.file = log;

				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("events");
				doc.appendChild(rootElement);

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
		} catch (Exception ex) {
			System.out.println("Error 11");
		}
	}

	public static void reWriteFileXML(Event[] events) {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("events");
			doc.appendChild(rootElement);

			for (int i = 0; i < events.length; i++) {

				Element event = doc.createElement("event");
				rootElement.appendChild(event);

				Attr attr = doc.createAttribute("id");
				attr.setValue(String.valueOf(i));
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
				hour.appendChild(
						doc.createTextNode(String.valueOf(events[i].getTimeOfEvent().get(Calendar.HOUR_OF_DAY))));
				event.appendChild(hour);

				Element minutes = doc.createElement("minutes");
				minutes.appendChild(
						doc.createTextNode(String.valueOf(events[i].getTimeOfEvent().get(Calendar.MINUTE))));
				event.appendChild(minutes);

				Element description = doc.createElement("Description");
				description.appendChild(doc.createTextNode(events[i].getDescription()));
				event.appendChild(description);

			}
			
			

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(("DayPlanner.xml"));
			//result = new StreamResult(System.out);
			
			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}

	public static HashMap<Integer, Event> loadEventsMappedXML(File file) {
		HashMap<Integer, Event> hashMap = new HashMap<Integer, Event>();
		char type;
		char marker;
		int month;
		int date;
		String hour;
		String minutes;
		String description;
		int count = 0;
		
		Calendar timeOfEvent;
		try {
			File inputFile = FileOperate.file;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("event");

			Event[] events = new Event[nList.getLength()];
			for (int temp = 0; temp < nList.getLength(); temp++) {
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
					timeOfEvent = new GregorianCalendar();
					timeOfEvent.set(Event.year, month, date, Integer.valueOf(hour), Integer.valueOf(minutes));
					//System.out.println(timeOfEvent.get(Calendar.MONTH));
					events[temp] = new Event(type, marker, timeOfEvent, description);
					//System.out.println(events[temp]);
					hashMap.put(++count, events[temp]);
					//System.out.println(events[temp]);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("LOADED");
		return hashMap;
	}


}

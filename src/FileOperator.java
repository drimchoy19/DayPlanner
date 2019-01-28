
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

public class FileOperator {

	private File file;

	protected File getFile() {
		return file;
	}

	protected void setFile(File file) {
		this.file = file;
	}
	
	public void createFile() {
		File log = new File("DayPlanner.xml");
		try {
			if (log.exists() == false) {
				System.out.println("We had to make a new file.");
				log.createNewFile();
				this.file = log;

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
				this.file = log;
			}
		} catch (IOException e) {
			System.out.println("Can't create file!");
		} catch (Exception ex) {
			System.out.println("Error 11");
		}
	}

	public void reWriteFileXML(Event[] events) {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("events");
			doc.appendChild(rootElement);
			
			Element event,type,marker,
				month,date,hour,minutes,description;
			Attr attr;
			for (int i = 0; i < events.length; i++) {

				event = doc.createElement("event");
				rootElement.appendChild(event);

				attr = doc.createAttribute("id");
				attr.setValue(String.valueOf(i));
				event.setAttributeNode(attr);

				type = doc.createElement("type");
				type.appendChild(doc.createTextNode(String.valueOf(events[i].getType())));
				event.appendChild(type);

				marker = doc.createElement("marker");
				marker.appendChild(doc.createTextNode(String.valueOf(events[i].getMarker())));
				event.appendChild(marker);

				month = doc.createElement("month");
				month.appendChild(doc.createTextNode(String.valueOf(events[i].getTimeOfEvent().get(Calendar.MONTH))));
				event.appendChild(month);

				date = doc.createElement("date");
				date.appendChild(doc.createTextNode(String.valueOf(events[i].getTimeOfEvent().get(Calendar.DATE))));
				event.appendChild(date);

				hour = doc.createElement("hour");
				hour.appendChild(
						doc.createTextNode(String.valueOf(events[i].getTimeOfEvent().get(Calendar.HOUR_OF_DAY))));
				event.appendChild(hour);

				minutes = doc.createElement("minutes");
				minutes.appendChild(
						doc.createTextNode(String.valueOf(events[i].getTimeOfEvent().get(Calendar.MINUTE))));
				event.appendChild(minutes);

				description = doc.createElement("Description");
				description.appendChild(doc.createTextNode(events[i].getDescription()));
				event.appendChild(description);

			}
			
			

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(("DayPlanner.xml"));
			
			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}

	public HashMap<Integer, Event> loadEventsMappedXML() {
		
		HashMap<Integer, Event> hashMap = new HashMap<Integer, Event>();
		
		try {
			
			File inputFile = this.file;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("event");

			//Event[] events = new Event[nList.getLength()];
			//Calendar timeOfEvent = new GregorianCalendar();
			
			int count = 0;
			char type,marker;
			int month,date;
			String hour,minutes,description;
			
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
					
					Calendar c = new GregorianCalendar(2019, month, date, Integer.valueOf(hour),
							Integer.valueOf(minutes));
					
					hashMap.put(++count, new Event(type, marker, c, description));
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("LOADED");
		return hashMap;
	}

}

package xmldemo;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;



public class MyXMLHandler extends DefaultHandler {

	private int nspaties = 0;
	
	private void writeSpaces(int n)
	{
		for (int i =0;i<n;i++)
			System.out.print(' ');
	}

	private void parseDocument(String bestand) {

		// Maak een parser factory (standaard)
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			// Maak een nieuwe parser
			SAXParser sp = spf.newSAXParser();
			// parse het bestand 
			sp.parse(bestand, this);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	// Bij event: een startelement gevonden:
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		writeSpaces(nspaties);
		System.out.println("start: " + qName);
		if (attributes.getLength() > 0)
		{
			for (int i = 0; i < attributes.getLength();i++ )
			{
			   writeSpaces(nspaties);	
			   System.out.println("   attr. " + attributes.getQName(i) + ": " + attributes.getValue(i));
			}
		}
		nspaties += 3;
	}

	// Bij event: character data gevonden
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String s = new String(ch, start, length);
		if (!s.trim().equals(""))
		{
		   writeSpaces(nspaties);
		   System.out.println("chars: " + s.trim());
	    }
	}

	// Bij event: een sluitelement gevonden:
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		nspaties -= 3;
		writeSpaces(nspaties);
		System.out.println("end: " + qName);
	}

	public static void main(String[] args) {
		MyXMLHandler spe = new MyXMLHandler();
//		spe.runParsing("verlanglijstjes.xml");
        spe.parseDocument("employees.xml");
	}
}

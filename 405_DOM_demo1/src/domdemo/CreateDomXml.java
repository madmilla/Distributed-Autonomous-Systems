package domdemo;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

class CreateDomXml {
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			Element root = doc.createElement("Personnel");
			// all it to the xml tree
			doc.appendChild(root);

			// create a comment
			Comment c = doc.createComment("Personeelsoverzicht");
			// add in the root element
			root.appendChild(c);

			// create child element
			Element e = doc.createElement("Employee");
			// Add the atribute to the child
			e.setAttribute("type", "permanent");

			root.appendChild(e);

			Element child = doc.createElement("Name");
			Text tn = doc.createTextNode("Seagull Cullin");
			child.appendChild(tn);
			e.appendChild(child);

			child = doc.createElement("Id");
			tn = doc.createTextNode("3674");
			child.appendChild(tn);
			e.appendChild(child);

			child = doc.createElement("Age");
			tn = doc.createTextNode("34");
			child.appendChild(tn);
			e.appendChild(child);

			TransformerFactory tf = TransformerFactory.newInstance();

			Transformer t = tf.newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
					"3");

			DOMSource ds = new DOMSource(doc);
			Result dest = new StreamResult(System.out);
			// Result dest = new StreamResult(new File("vb.xml"));
			t.transform(ds, dest);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
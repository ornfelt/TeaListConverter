package tealistfileconverter.converters;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tealistfileconverter.Tea;

/**
 * Converts UTF-8 files containing tea information from and to 
 * XML (eXtensible Markup Language).  
 * 
 * @author Thomas Ejnefjäll
 */
public class TeaListConverterXml extends TeaListConverter {

	/* Constants regarding tea */
	private static final String TEA = "tea",
							   TEALIST = "tealist",	
							   CATEGORY = "category",
							   NAME = "name",
							   PRICE = "price",
							   DESCRIPTION = "description";	
	
	@Override
	protected List<Tea> readAndParse(String fileName) throws IOException {
		List<Tea> teaList = new ArrayList<Tea>();
		try
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fileName);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName(TEA);

			for(int i = 0; i < nodeList.getLength(); i++) {

				Tea tea = new Tea();
				Node node = nodeList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;

					tea.category = getTagValue(CATEGORY, element);
					tea.name = getTagValue(NAME, element);
					tea.price = Integer.valueOf(getTagValue(PRICE, element));
					tea.description = getTagValue(DESCRIPTION, element);	 
				}
				teaList.add(tea);
			}
		}
		catch(Exception e) {
			throw new IOException("Input file (" + fileName + ") not correct format");
		}
		return teaList;
	}
	@Override
	protected void parseAndWrite(List<Tea> teaList, String fileName) throws Exception {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(TEALIST);
		doc.appendChild(rootElement);

		for(Tea tea : teaList) {
			Element teaElement = doc.createElement(TEA);
			rootElement.appendChild(teaElement);

			Element categoryElement = doc.createElement(CATEGORY);
			categoryElement.appendChild(doc.createTextNode(tea.category));
			teaElement.appendChild(categoryElement);

			Element nameElement = doc.createElement(NAME);
			nameElement.appendChild(doc.createTextNode(tea.name));
			teaElement.appendChild(nameElement);

			Element priceElement = doc.createElement(PRICE);
			priceElement.appendChild(doc.createTextNode("" + tea.price));
			teaElement.appendChild(priceElement);

			Element descriptionElement = doc.createElement(DESCRIPTION);
			descriptionElement.appendChild(doc.createTextNode(tea.description));
			teaElement.appendChild(descriptionElement);
		}			

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result;

		result = new StreamResult(new File(fileName));
		transformer.transform(source, result);				
	}
	/**
	 * Get a value from an element.
	 *  
	 * @param tag The tag we are looking for.
	 * @param element The current element.
	 * @return The value of the tag.
	 */
	private String getTagValue(String tag, Element element) {
		NodeList nlList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}
}
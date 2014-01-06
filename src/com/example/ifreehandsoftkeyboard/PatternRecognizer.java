package com.example.ifreehandsoftkeyboard;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class PatternRecognizer {
	static String mappingXML = "MappingPattern.xml";
	static Document dom;
	//static String char1 = null;
	//static String char2 = null;

	public static void parseXmlFile(InputStream is) {
		// get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			dom = db.parse(is);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private static String getTextValue(Element ele, String tagName,
			boolean shifted) {
		String textVal = null;
		if (shifted)
			tagName = "S" + tagName;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}
		return textVal;
	}

	public static String translateToCharacter(String pattern, boolean shifted) {
		return getTextValue(dom.getDocumentElement(), pattern, shifted);
	}
}

package com.smeup.jxlsxml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.smeup.jxlsexample.Pen;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DomParser {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		List<Pen> pens = new ArrayList<Pen>();
		int srN;
		String name;
		String desc;
		File inputFile = new File("src/main/resources/xml/pen_list.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		System.out.println("Root element:" + doc.getDocumentElement().getNodeName());
	
		//Creo una lista di tutti gli elementi "pen"
		NodeList nList = doc.getElementsByTagName("pen");
		System.out.println("----");
		
		//Per ciascun elemento di questa lista prenderò gli elementi che lo compongono, segnalandone i nomi
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			System.out.println("\nElement: "+ nNode.getNodeName());
			
			if(nNode.getNodeType()==Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				srN=Integer.parseInt(eElement.getAttribute("srNo"));
				System.out.println("Pen ID: "+ srN);
				name=eElement.getElementsByTagName("name").item(0).getTextContent();
				System.out.println("Name: " + name);
				desc=eElement.getElementsByTagName("desc").item(0).getTextContent();
				System.out.println("Description: "+ desc);
				
				pens.add(new Pen(srN, name, desc));
			}
		}
		
		//Control ache abbe messy i data el vetoer
		System.out.println("\n\nElementi del vettore:");
		for(int i=0; i<pens.size(); i++) {
			System.out.println(pens.get(i).toString());
		}
	}

}

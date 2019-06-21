package com.smeup.jxlsxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;

import com.smeup.jxlsexample.Pen;

public class ExcelTemplateFromXML {

	public static void main(String[] args) throws FileNotFoundException {
		List<Pen> pens=generateSamplePenData();
		//input 1: XML
		FileInputStream is = new FileInputStream(new File("src/main/resources/excel/penxml_template.xlsx"));
		
		try (OutputStream os = new FileOutputStream("src/main/resources/excel/penxml_output.xlsx")) {
			Transformer transformer = TransformerFactory.createTransformer(is, os);
			FileInputStream x = new FileInputStream(new File("src/main/resources/xml/pen.xml"));
			AreaBuilder areaBuilder = new XmlAreaBuilder(x, transformer);
			List<Area> xlsAreaList = areaBuilder.build();
			Area xlsArea = xlsAreaList.get(0);
			Context context = new Context();
			context.putVar("pens", pens);
			xlsArea.applyAt(new CellRef("Result!A1"), context);
			transformer.write();
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	private static List<Pen> generateSamplePenData() {
		List<Pen> pens=new ArrayList<Pen>();
		pens.add(new Pen("Holy", 2535));
		return pens;
	}

}

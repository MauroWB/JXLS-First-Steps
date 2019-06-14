package com.smeup.jxlsexample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicGrid {

	static Logger logger = LoggerFactory.getLogger(DynamicGrid.class);

	public static void main(String[] args) throws Exception {

		logger.info("Running Dynamic Grid demo");
		List<PenAdv> pens = generateSamplePenData();
		execute(pens);
		
	}
	
	private static void execute(List<PenAdv> pens) throws IOException {
		InputStream is = DynamicGrid.class.getResourceAsStream("src/main/resources/excel/pengrid_template.xlsx");
		OutputStream os = new FileOutputStream("src/main/resources/excel/pengrid_output.xlsx");
		Context context = new Context();
		context.putVar("headers", Arrays.asList("Nome", "Descrizione"));
		context.putVar("data", pens);
		JxlsHelper.getInstance().processGridTemplateAtCell(is,  os, context, "name,description", "Sheet2!A1");
	}
	
	public static List<PenAdv> generateSamplePenData() throws ParseException {
		List<PenAdv> pens = new ArrayList<>();
		pens.add(new PenAdv("Bic", (float) 1.2, "A"));
		pens.add(new PenAdv("Bic", (float) 1.2, "La seconda Bic"));
		pens.add(new PenAdv("Stylus", (float) 2.5, "B"));
		pens.add(new PenAdv("Multi", (float) 3.4, "C"));
		return pens;

	}
}

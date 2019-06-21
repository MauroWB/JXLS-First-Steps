package com.smeup.jxlsexample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvancedPenDemo {

	static Logger logger = LoggerFactory.getLogger(AdvancedPenDemo.class);

	public static void main(String[] args) throws IOException, ParseException {

		logger.info("Running Object Collection demo");
		List<PenAdv> pens = generateSamplePenData();
		
		String tempPath = "src/main/resources/excel/penadv_template.xlsx";
		FileInputStream is = new FileInputStream(new File(tempPath));
		try (OutputStream os = new FileOutputStream("src/main/resources/excel/penadv_output.xlsx")) {
			Context context = new Context();
			context.putVar("pens", pens);
			JxlsHelper.getInstance().processTemplate(is, os, context);
		}

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

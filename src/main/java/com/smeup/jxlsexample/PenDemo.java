package com.smeup.jxlsexample;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PenDemo {
  private static Logger logger = LoggerFactory.getLogger(PenDemo.class);
	 
    public static void main(String[] args) throws ParseException, IOException, Exception {
    	logger.info("Running Object Collection demo");
        List<Pen> pens = generateSamplePenData();
        //se uso un workaround a getResourceAsStream non mi dà "Cannot load XLS Transformer"
        //try(InputStream is = ObjectCollectionDemo.class.getResourceAsStream("object_collection_template.xls")) {
        String tempPath = "src/main/resources/excel/simplepen_template.xlsx";
        FileInputStream is = new FileInputStream(new File(tempPath));
            try (OutputStream os = new FileOutputStream("src/main/resources/excel/simplepen_output.xlsx")) {
                Context context = new Context();
                context.putVar("pens", pens);
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
    }

    public static List<Pen> generateSamplePenData() throws ParseException {
        List<Pen> pens = new ArrayList<>();
        pens.add( new Pen ("Bic", 1));
        pens.add(new Pen("Stylus", 2));
        pens.add(new Pen("Multi", 3));
        return pens;
    }
}

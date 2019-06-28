package com.smeup.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import com.smeup.jxlsexample.PenAdv;

public class DynamicGridDemo {

	public static void main(String[] args) throws IOException {
		List<PenAdv> pens = new ArrayList<PenAdv>();
		pens.add(new PenAdv("Bic", (float) 2.13, "Test1"));
		pens.add(new PenAdv("Multi", (float) 3.29, "Test2"));
		
		System.out.println("Inizio elaborazione...");
		InputStream in = new FileInputStream("src/main/resources/excel/dgrid_template.xlsx");
		OutputStream out = new FileOutputStream("src/main/resources/excel/dgrid_output.xlsx");
		Context context = new Context();
		context.putVar("headers", Arrays.asList("Name", "Cost", "Description"));
		context.putVar("data", pens);
		//JxlsHelper.getInstance().processTemplate(in, out, context);
		JxlsHelper.getInstance().processGridTemplateAtCell(in, out, context, "name,cost,desc", "Sheet1!A1");
		in.close();
		out.close();
		System.out.println("Fine elaborazione.");
	}

}

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

public class Formulas {

	public static void main(String[] args) throws IOException {
		Integer[] nums = { 1, 3, 5, 20, 30, 80 };
		List<Object> master = new ArrayList<Object>();
		String s = "2";
		master.add("1");
		master.add(2);
		master.add(s);
		
		//Se funziona, su Excel la stringa "s" di master verrà tradotta a intero
		for (int i=0; i<master.size(); i++) {
			Object obj = new Object();
			obj=master.get(i);
			master.set(i, obj);
		}

		InputStream in = new FileInputStream("src/main/resources/excel/formula_template.xlsx");
		OutputStream out = new FileOutputStream("src/main/resources/excel/formula_output.xlsx");
		Context context = new Context();
		context.putVar("nums", Arrays.asList(nums));
		context.putVar("master", master);
		JxlsHelper.getInstance().processTemplate(in, out, context);
		in.close();
		out.close();
	}

}

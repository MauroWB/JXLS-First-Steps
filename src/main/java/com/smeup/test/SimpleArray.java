package com.smeup.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

public class SimpleArray {

	public static void main(String[] args) throws IOException {
		String[] strings = {"T0", "T1"};
		Integer i[] = {1, 2, 3};
		Iterable<Integer> it = Arrays.asList(i);
		Iterable<String> st = Arrays.asList(strings);
		
		System.out.println("Inizio...");
		InputStream in = new FileInputStream("src/main/resources/excel/array_template.xlsx");
		OutputStream out = new FileOutputStream("src/main/resources/excel/array_output.xlsx");
		Context context = new Context();
		context.putVar("strings", st);
		context.putVar("int", it);
		JxlsHelper.getInstance().processTemplate(in, out, context);
		in.close();
		out.close();
		System.out.println("Fine.");
	}

}

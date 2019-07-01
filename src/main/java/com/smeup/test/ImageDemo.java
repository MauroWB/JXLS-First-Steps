package com.smeup.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.jxls.util.Util;

public class ImageDemo {

	public static void main(String[] args) throws Exception {
		InputStream in = new FileInputStream("src/main/resources/excel/img_template.xlsx");
		OutputStream out = new FileOutputStream("src/main/resources/excel/img_output.xlsx");
		
		
		InputStream imgInputStream = new FileInputStream("src/main/resources/img/logo-smeup.png");
		byte[] imageBytes = Util.toByteArray(imgInputStream);
		Context context = new Context();
		context.putVar("image", imageBytes);
		
		
		JxlsHelper.getInstance().processTemplate(in, out, context);
		in.close();
		imgInputStream.close();
		out.close();
	}

}

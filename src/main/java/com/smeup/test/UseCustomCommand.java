package com.smeup.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;

import com.smeup.commands.ColorDemoCommand;
import com.smeup.commands.TestCustomCommand;


public class UseCustomCommand {

	public static void main(String[] args) throws IOException {
		final InputStream in = new FileInputStream("src/main/resources/excel/command_template.xlsx");
		final OutputStream out = new FileOutputStream("src/main/resources/excel/command_output.xlsx");
		
		Transformer transformer = TransformerFactory.createTransformer(in, out);
		AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
		XlsCommentAreaBuilder.addCommandMapping("smeuptest", TestCustomCommand.class);
		XlsCommentAreaBuilder.addCommandMapping("color", ColorDemoCommand.class);
		List<Area> xlsAreaList = areaBuilder.build();
		Area xlsArea = xlsAreaList.get(0);
		Context context = new Context();
		context.putVar("x", 5);
		xlsArea.applyAt(new CellRef("Sheet1!A1"), context);
		transformer.write();
		in.close();
		out.close();
		System.out.println("Fine.");
	}

}

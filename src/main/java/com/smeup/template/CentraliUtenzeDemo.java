package com.smeup.template;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

public class CentraliUtenzeDemo {

	public static void main(String[] args) throws IOException {
		List<Centrale> centrali = new ArrayList<>();
		centrali.add(new Centrale("Centrale TN", 10099));
		centrali.add(new Centrale("Centrale BT", 10022));
		
		List<Utenza> utenze = new ArrayList<>();
		utenze.add(new Utenza ("ProvaUtenze1", 20099));
		utenze.add(new Utenza ("ProvaUtenze2", 20227));
		
		List<Object> master = new ArrayList<>();
		master.add(new Centrale("Centrale TN", 10999));
		master.add(new Utenza ("ProvaUtenze1", 20099));
		master.add(new Centrale("Centrale BT", 10022));
		master.add(new Utenza ("ProvaUtenze2", 20227));
		
		Context context = new Context();
		context.putVar("centrali", centrali);
		context.putVar("utenze", utenze);
		context.putVar("master", master);
		context.putVar("centrale1", new Centrale("Centrale Test1", 299));
		context.putVar("centrale2", new Centrale("Centrale Test2", 302));
		context.putVar("utenza1", new Utenza("UtenzaTest1", 266));
		context.putVar("utenza2", new Utenza("UtenzaTest2", 267));

		InputStream in = new FileInputStream("src/main/resources/excel/official/centrdemo_template.xlsx");
		OutputStream out = new FileOutputStream("src/main/resources/excel/official/centrdemo_output.xlsx");
		JxlsHelper.getInstance().setProcessFormulas(false).processTemplate(in, out, context);
		
		out.close();
		in.close();
		// Non c'è bisogno dell'elaborazione in poi se si disabilita il jxls formula processor.
		// Prendo il risultato di Jxls
		/*InputStream inF = new FileInputStream("src/main/resources/excel/official/centrdemo_output.xlsx");
		OutputStream outF = new FileOutputStream("src/main/resources/excel/official/centrdemo_foutput.xlsx");
		Workbook wb = WorkbookFactory.create(inF);
		
		//Trasformo la stringa in formula
		for (Sheet s : wb)
			for (Row r : s)
				for (Cell c : r) {
					if (c.getCellType() == CellType.STRING && c.getStringCellValue().startsWith("_SUM(")) {
						System.out.println("Trovato a "+c.getAddress());
						String string = c.getStringCellValue();
						System.out.println(string);
						string = string.replace("_", "");
						System.out.println(string);
						c.setCellValue(string);
						c.setCellType(CellType.FORMULA);
						c.setCellFormula(string);
					}
				}
		
		wb.write(outF);
		inF.close();
		outF.close();*/
		
		System.out.println("Fine.");
	}

}

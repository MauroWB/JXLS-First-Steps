package com.smeup.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.jxls.area.XlsArea;
import org.jxls.command.EachCommand;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.jxls.util.TransformerFactory;

import com.smeup.jxlsexample.SimpleAreaListener;
import com.smeup.jxlsexample.Student;

public class IfConditionDemo {

	public static ArrayList<Student> fill(ArrayList<Student> students) {
		students.add(new Student("Alberto", "Fenici", "03/12/1996", 1, 16));
		students.add(new Student("Giovanni", "Ramo", "05/02/1997", 2, 20));
		return students;
	}

	public static void execute() throws IOException {

		System.out.println("Inizio...");
		ArrayList<Student> students = new ArrayList<Student>();
		fill(students);

		InputStream in = new FileInputStream("src/main/resources/excel/student_template.xlsx");
		OutputStream out = new FileOutputStream("src/main/resources/excel/student_output.xlsx");
		Transformer transformer = TransformerFactory.createTransformer(in, out);
		System.out.println("Transformer creato...");
		XlsArea xlsArea = new XlsArea("Template!A1:D5", transformer);
		xlsArea.addAreaListener(new SimpleAreaListener(xlsArea));
		System.out.println("XlsArea: " + xlsArea.getAreaRef());
		Context context = new Context();
		context.putVar("students", students);
		transformer.setFormula(new CellRef("Template!B2"), "TODAY()");
		System.out.println(transformer.getCellData(new CellRef("Template!A1")));
		JxlsHelper.getInstance().processTemplate(context, transformer);

		System.out.println("Fine.");
	}

	public static void main(String[] args) throws IOException {
		execute();
	}

}

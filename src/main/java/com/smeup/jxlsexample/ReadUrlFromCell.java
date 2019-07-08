package com.smeup.jxlsexample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellAddress;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.jxls.util.Util;

public class ReadUrlFromCell {

	// Gli passi context, trova url, inserisce img nel context, ritorna context?
	public static Context readUrl(Workbook wb) throws IOException {
		Context context = new Context();
		CreationHelper factory = wb.getCreationHelper();
		OutputStream tempo = new FileOutputStream("src/main/resources/excel/img/readimg_tempo.xlsx");
		int i = 1; // Contatore immagini inserite
		for (Sheet s : wb) {
			CellAddress lastCell = new CellAddress(0,0);
			Drawing<?> drawing = s.createDrawingPatriarch();
			for (Row r : s)
				for (Cell c : r) {
					if (c.getCellType() == CellType.STRING && c.getStringCellValue().startsWith("http")) {
						try {
							InputStream input = new URL(c.getStringCellValue()).openStream();
							// Se l'url non è valido lancia un'Exception quindi non viene fatto tutto il
							// resto
							byte[] urlBytes = Util.toByteArray(input);
							context.putVar("imageFromUrl" + i, urlBytes);

							System.out.println("URL alla cella " + c.getAddress() + " inserita nel context");

							// Estensione dell'immagine da URL
							String url = c.getStringCellValue();
							StringBuffer extension = new StringBuffer();
							for (int cont = (url.lastIndexOf(".") + 1); cont < url.length(); cont++) {
								extension.append(url.charAt(cont));
							}
							System.out.println("Buffer: " + extension.toString().toUpperCase());

							// Inserimento commento
							ClientAnchor anchor = factory.createClientAnchor();
							Comment comment = drawing.createCellComment(anchor);
							CellAddress lastImgCell = new CellAddress(c.getRowIndex()+3, c.getColumnIndex()+2);
							comment.setString(factory.createRichTextString("jx:image(lastCell='" + lastImgCell
								+ "' src='imageFromUrl" + i 
								+ "' imageType='" + extension.toString().toUpperCase() + "')"));
							c.setCellComment(comment);
							
							// lastCell si riferisce a lastImgCell perché in questo esempio avrà valori 
							// sempre maggiori alla cella corrente c
							if (lastCell.getRow() < lastImgCell.getRow()) {
								int temp = lastCell.getColumn();
								lastCell = new CellAddress(lastImgCell.getRow(), temp);
							}
							
							if (lastCell.getColumn() < lastImgCell.getColumn()) {
								int temp = lastCell.getRow();
								lastCell = new CellAddress(temp, lastImgCell.getColumn());
							}

							i++;

						} catch (Exception e) {
							System.out.println("URL non valido alla cella " + c.getAddress());
							e.printStackTrace();
						}
					}
				}
			//Applico il commento jx:area alla cella d'origine
			Cell ori = s.getRow(0).getCell(0);
			ClientAnchor anchor = factory.createClientAnchor();
			Comment comment = drawing.createCellComment(anchor);
			comment.setString(factory.createRichTextString("jx:area(lastCell='" + lastCell + "')"));
			ori.setCellComment(comment);
		}
		wb.write(tempo);
		wb.close();
		return context;
	}

	public static void main(String[] args) throws IOException {
		InputStream in = new FileInputStream("src/main/resources/excel/img/readimg_template.xlsx");
		OutputStream out = new FileOutputStream("src/main/resources/excel/img/readimg_output.xlsx");
		
		Workbook wb = WorkbookFactory.create(in);
		Context context = readUrl(wb);
		InputStream temp = new FileInputStream("src/main/resources/excel/img/readimg_tempo.xlsx");
		JxlsHelper.getInstance().processTemplate(temp, out, context);
		
		temp.close();
		in.close();
		out.close();
		System.out.println("Fine elaborazione.");
	}

}

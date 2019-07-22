package com.smeup.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.junit.jupiter.api.Test;

class ShiftRows {

	@Test
	public void test1() throws EncryptedDocumentException, IOException {
		final InputStream in = new FileInputStream("src/main/resources/test/shift_template.xlsx");
		assertNotEquals(in, null);
		Workbook wb = WorkbookFactory.create(in);
		in.close();

		final OutputStream out = new FileOutputStream("src/main/resources/test/shift_output.xlsx");

		Sheet s = wb.getSheetAt(0);
		assertEquals(s.getRow(0).getCell(0).getStringCellValue(), "Shift Demo");
		assertNotEquals(wb.getSheetAt(0), null);
		s.getRow(0).setZeroHeight(true);

		// Importante patch
		s.shiftRows(0, 3, 2);

		final int nFirstDstRow = 0 + 2;
		final int nLastDstRow = 3 + 2;

		for (int nRow = nFirstDstRow; nRow <= nLastDstRow; ++nRow) {
			final XSSFRow row = (XSSFRow) s.getRow(nRow);
			if (row != null) {
				String msg = "Row[rownum=" + row.getRowNum()
						+ "] contains cell(s) included in a multi-cell array formula. "
						+ "You cannot change part of an array.";
				for (Cell c : row) {
					((XSSFCell) c).updateCellReferencesForShifting(msg);
				}
			}
		}

		// Fine patch

		/*
		 * s.removeRow(s.getRow(0)); s.shiftRows(1, s.getLastRowNum(), -1);
		 * assertEquals(s.getRow(1).getCell(0).getStringCellValue(), "Test");
		 */
		wb.write(out);
		out.close();
		wb.close();
	}

}

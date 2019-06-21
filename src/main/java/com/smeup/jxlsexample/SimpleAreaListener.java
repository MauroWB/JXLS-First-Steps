package com.smeup.jxlsexample;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jxls.area.XlsArea;
import org.jxls.common.AreaListener;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.poi.PoiTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SimpleAreaListener implements AreaListener {
	static Logger logger = LoggerFactory.getLogger(SimpleAreaListener.class);
	
	XlsArea area;
	PoiTransformer transformer;
	private final CellRef avgCell = new CellRef("D4");
	
	public SimpleAreaListener(XlsArea area) {
        this.area = area;
        transformer = (PoiTransformer) area.getTransformer();
    }
	
	@Override
	public void beforeApplyAtCell(CellRef cellRef, Context context) {
		
	}

	@Override
	public void afterApplyAtCell(CellRef cellRef, Context context) {
		
	}

	@Override
	public void beforeTransformCell(CellRef srcCell, CellRef targetCell, Context context) {
		
	}

	@Override
	public void afterTransformCell(CellRef srcCell, CellRef targetCell, Context context) {
		System.out.println("Sono nell'afterTransformCell");
		if(avgCell.equals(srcCell)) {
			Student student = (Student) context.getVar("student");
			if (student.getAvg() < 18) { 
				logger.info("Lo studente " + student.getName() + " ha la media bassa");
				highlight(targetCell); //cella rossa
			}
		}
	}
	
	public void highlight (CellRef cellRef) {
		Workbook workbook = transformer.getWorkbook();
		Sheet sheet = workbook.getSheet(cellRef.getSheetName());
		Cell cell = sheet.getRow(cellRef.getRow()).getCell(cellRef.getCol());
		CellStyle cellStyle = cell.getCellStyle();
		CellStyle newCellStyle = workbook.createCellStyle();
		newCellStyle.setFillBackgroundColor( cellStyle.getFillBackgroundColor());
        newCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        cell.setCellStyle(newCellStyle);
	}

}

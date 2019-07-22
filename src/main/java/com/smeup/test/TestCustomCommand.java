package com.smeup.test;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jxls.area.Area;
import org.jxls.command.AbstractCommand;
import org.jxls.command.Command;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.common.Size;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.Util;

/**
 * Riempie le celle di una Jx Area con la stringa "Test".
 * Utilizzo: jx:smeupTest(lastCell="" condition="")
 *
 * @author JohnSmith
 *
 */

public class TestCustomCommand extends AbstractCommand {

	String condition;
	Area area;

	public String getName() {
		return "smeupTest";
	}

	// cellRef è dove starà il commento?

	public Size applyAt(CellRef cellRef, Context context) {
		Size resultSize = area.applyAt(cellRef, context);
		if (resultSize.equals(Size.ZERO_SIZE))
			return resultSize;
		PoiTransformer transformer = (PoiTransformer) area.getTransformer();
		Workbook wb = transformer.getWorkbook();
		Sheet s = wb.getSheet(cellRef.getSheetName());
		int startRow = cellRef.getRow();
		int startCol = cellRef.getCol();
		int endRow = cellRef.getRow() + resultSize.getHeight() - 1;
		int endCol = cellRef.getCol() + resultSize.getWidth() - 1;
		int cont = 0;
		if (condition != null && condition.trim().length() > 0) {
			boolean doIt = Util.isConditionTrue(getTransformationConfig().getExpressionEvaluator(), condition, context);
			if (doIt) {
				while (startRow <= endRow) {
					startCol = cellRef.getCol();
					while (startCol <= endCol) {
						if (s.getRow(startRow) == null)
							s.createRow(startRow);
						if (s.getRow(startRow).getCell(startCol) == null)
							s.getRow(startRow).createCell(startCol);
						s.getRow(startRow).getCell(startCol).setCellValue("Iterazione numero " + cont);
						startCol++;
						cont++;
					}
					startRow++;
				}
			}
		}
		return resultSize;
	}

	@Override
	public Command addArea(Area area) {
		super.addArea(area);
		this.area = area;
		return this;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
}

package com.smeup.test;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smeup.jxlsexample.PenDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PenFunctionDemo {
	static Logger logger = LoggerFactory.getLogger(PenDemo.class);

	public static void main(String[] args) throws ParseException, IOException, Exception {
		logger.info("Running Custom Function Demo");
		// se uso un workaround a getResourceAsStream non mi dà "Cannot load XLS
		// Transformer"
		// try(InputStream is =
		// ObjectCollectionDemo.class.getResourceAsStream("object_collection_template.xls"))
		// {
		String tempPath = "src/sample/jexl_custom_function_template.xlsx";
		FileInputStream is = new FileInputStream(new File(tempPath));

		// JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)
		// transformer.getTransformationConfig().getExpressionEvaluator();
		// Map<String, Object> functionMap = new HashMap<>();
		// functionMap.put("demo", new JexlCustomFunctionDemo());

		OutputStream os = new FileOutputStream("src/sample/pen_output.xlsx");
		Transformer transformer = TransformerFactory.createTransformer(is, os);
		AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
		List<Area> xlsAreaList = areaBuilder.build();
		Area xlsArea = xlsAreaList.get(0);
		Context context = new Context();
		context.putVar("x", 18);
		context.putVar("y", 17);
		JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig()
				.getExpressionEvaluator();
		Map<String, Object> functionMap = new HashMap<>();
		functionMap.put("demo", new PenFunctionDemo());
		JexlEngine customJexlEngine = new JexlBuilder().namespaces(functionMap).create();
		evaluator.setJexlEngine(customJexlEngine);
		xlsArea.applyAt(new CellRef("Sheet1!A1"), context);
		transformer.write();
		is.close();
		os.close();
	}

	public String check(Integer z) {
		if (z >= 18) {
			return "Maggiore";
		}
		return "Minore";
	}

	public Integer sum(Integer x, Integer y) {
		return x + y;
	}
}
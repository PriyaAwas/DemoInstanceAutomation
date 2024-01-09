package sew.ai.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sew.ai.helpers.exceptions.FrameworkExceptions;
import sew.ai.helpers.exceptions.InvalidPathExcelException;
import sew.ai.helpers.props.Constants;
import sew.ai.helpers.props.FilePaths;
import sew.ai.helpers.reporters.ExtentLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public final class ExcelUtils {
	private static final Logger log = LogManager.getLogger(ExcelUtils.class);

	private ExcelUtils() {
	}

	public static List<Map<String, String>> getTestDetails(String sheetName) {
		List<Map<String, String>> list;
		try (FileInputStream fis = new FileInputStream(FilePaths.TEST_RESOURCE_PATH + Constants.EXCEL_FILENAME)) {
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int lastRowNum = sheet.getLastRowNum();
			int lastColNum = sheet.getRow(0).getLastCellNum();
			Map<String, String> map;
			list = new ArrayList<>();
			for (int i = 1; i <= lastRowNum; i++) {
				log.info("Test Runner excel row at: " + i);
				map = new HashMap<>();
				for (int j = 0; j < lastColNum; j++) {
					// log.info("Test Runner excel col at: " + j);
					String key = sheet.getRow(0).getCell(j).getStringCellValue();
					String value = sheet.getRow(i).getCell(j).getStringCellValue();
					map.put(key, value);
				}
				list.add(map);
			}
		} catch (InvalidPathExcelException e) {
			e.printStackTrace();
			throw new FrameworkExceptions("Excel file you trying to read is not found.");
		} catch (IOException e) {
			throw new FrameworkExceptions("Some io exception happened  while reading excel file.");
		}
		return list;
	}

	public static FileInputStream fis = null;
	public static Workbook workbook = null;

	public static Workbook openExcelFile(String fileName) {
		try {
			fis = new FileInputStream(fileName);
			if (fileName.toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(fis);
			} else if (fileName.toLowerCase().endsWith("xls")) {
				workbook = new HSSFWorkbook(fis);
			}
		} catch (Exception f) {
			f.printStackTrace();
		}
		return workbook;
	}

	public static Sheet sheet = null;

	public static Sheet getSheetName(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		return sheet;
	}

	public static Sheet getSheetName(int index) {
		Sheet sheet = workbook.getSheetAt(index);
		return sheet;
	}

	public static Sheet getSheetName(Workbook workbook, int index) {
		Sheet sheet = workbook.getSheetAt(index);
		return sheet;
	}

	public static int getRowCount() {
		int rowsCount = sheet.getLastRowNum();
		return rowsCount;
	}

	public static int getRowCount(Sheet sheet) {
		int rowsCount = sheet.getLastRowNum();
		return rowsCount;
	}

	public static String getCellValue(int row, int col) {
		String value = "";
		sheet.getRow(row).getCell(col);
		if (sheet.getRow(row).getCell(col).getCellType() == CellType.STRING) {
			if (sheet.getRow(row).getCell(col).getStringCellValue().equalsIgnoreCase("NULL")) {
				return value = "";
			}
			return sheet.getRow(row).getCell(col).getStringCellValue();
		} else {
			double cellValues = sheet.getRow(row).getCell(col).getNumericCellValue();
			return Double.toString(cellValues);
		}
	}

	public static String getCellValue(Sheet sheet, int row, int col) {
		String value = "";
		sheet.getRow(row).getCell(col);
		if (sheet.getRow(row).getCell(col).getCellType() == CellType.STRING) {
			if (sheet.getRow(row).getCell(col).getStringCellValue().equalsIgnoreCase("NULL")) {
				return value = "";
			}
			return sheet.getRow(row).getCell(col).getStringCellValue();
		} else {
			double cellValues = sheet.getRow(row).getCell(col).getNumericCellValue();
			return Double.toString(cellValues);
		}
	}

	public static int getColumnCount() {
		int columnsCount = sheet.getRow(0).getLastCellNum();
		return columnsCount;
	}

	public static int getColumnCount(Sheet sheet) {
		int columnsCount = sheet.getRow(0).getLastCellNum();
		return columnsCount;
	}

	public static String getCellStringValueWithOutSpecialChar(int row, int col) {
		return sheet.getRow(row).getCell(col).getStringCellValue().replaceAll("[-+.^:$]", "");
	}

	public static String getCellStringValue(int row, int col) {
		DataFormatter formatter = new DataFormatter(); // creating formatter
		// using the default locale
		Cell cell = sheet.getRow(row).getCell(col);
		String cellValues = formatter.formatCellValue(cell);
		return cellValues;
	}

	public static String getCellStringValue(Sheet sheet, int row, int col) {
		DataFormatter formatter = new DataFormatter(); // creating formatter
		// using the default locale
		Cell cell = sheet.getRow(row).getCell(col);
		String cellValues = formatter.formatCellValue(cell);
		return cellValues;
	}

	// for Getting value by the Col name
	public static String getValueByColName(int row, String colName) {
		Map objMapExcel = new LinkedHashMap();
		int rowCount = getRowCount();
		int colCount = sheet.getRow(rowCount).getLastCellNum();
		for (int i = 0; i < colCount; i++) {
			Cell cell = sheet.getRow(2).getCell(i);
			String stringCellValue = cell.getStringCellValue();
			objMapExcel.put(stringCellValue, i);
		}
		return sheet.getRow(row).getCell((int) objMapExcel.get(colName)).getStringCellValue();
	}

	public static String getValueByColName1(Sheet sheet, int row, String colName) {
		Map objMapExcel = new LinkedHashMap();
		int rowCount = getRowCount(sheet);
		int colCount = sheet.getRow(rowCount).getLastCellNum();
		for (int i = 0; i < colCount; i++) {
			Cell cell = sheet.getRow(0).getCell(i);
			String stringCellValue = cell.getStringCellValue();
			objMapExcel.put(stringCellValue, i);
		}
		return sheet.getRow(row).getCell((int) objMapExcel.get(colName)).getStringCellValue();
	}

	// Added by Priya
	public static String getValueByColName(Sheet sheet, int row, String colName) {
		Map<String, Integer> objMapExcel = new LinkedHashMap<>();
		// int rowCount = getRowCount(sheet);
		int rowNum = sheet.getFirstRowNum();

		int colCount = sheet.getRow(rowNum).getLastCellNum();
		for (int i = 0; i < colCount; i++) {
			Cell cell = sheet.getRow(rowNum).getCell(i);

			if (cell.getCellType() == CellType.STRING) {
				String stringCellValue = cell.getStringCellValue();
				objMapExcel.put(stringCellValue, i);

			} else if (cell.getCellType() == CellType.BOOLEAN) {
				boolean booleanCellValue = cell.getBooleanCellValue();
				String stringCellValue = Boolean.toString(booleanCellValue);
				objMapExcel.put(stringCellValue, i);
			}
		}
		return sheet.getRow(row).getCell((int) objMapExcel.get(colName)).getStringCellValue();
	}

	public String getTestPageName(int row) {
		String cellValue = sheet.getRow(row).getCell(0).getStringCellValue();
		return cellValue;
	}

	public String getTestCaseName(int row) {
		String cellValue = sheet.getRow(row).getCell(1).getStringCellValue();
		return cellValue;
	}

	public Boolean getTestCaseExecutionStatus(int row) {
		Boolean cellValue = sheet.getRow(row).getCell(2).getBooleanCellValue();
		return cellValue;
	}

	public static String getCellValueAnalytics(Sheet sheet, int row, int col) {
		String value = "";
		sheet.getRow(row).getCell(col);
		if (sheet.getRow(row).getCell(col).getCellType() == CellType.STRING) {
			if (sheet.getRow(row).getCell(col).getStringCellValue().equalsIgnoreCase("NULL")) {
				return value = "";
			}
			return sheet.getRow(row).getCell(col).getStringCellValue();
		} else {
			double cellValues = sheet.getRow(row).getCell(col).getNumericCellValue();
			return Double.toString(cellValues);
		}
	}

	public static String getCellValueCsr(int row, int col) {
		String value = "";
		sheet.getRow(row).getCell(col);
		if (sheet.getRow(row).getCell(col).getCellType() == CellType.STRING) {
			if (sheet.getRow(row).getCell(col).getStringCellValue().equalsIgnoreCase("NULL")) {
				return value = "";
			}
			if (sheet.getRow(row).getCell(col).getStringCellValue().equalsIgnoreCase("Active")) {
				return value = "1";
			}
			if (sheet.getRow(row).getCell(col).getStringCellValue().equalsIgnoreCase("Inactive")) {
				return value = "2";
			}
			if (sheet.getRow(row).getCell(col).getStringCellValue().equalsIgnoreCase("Pending Activation")) {
				return value = "0";
			}
			if (sheet.getRow(row).getCell(col).getStringCellValue().equalsIgnoreCase("Locked")) {
				return value = "4";
			}
			if (sheet.getRow(row).getCell(col).getStringCellValue().equalsIgnoreCase("Temp Locked")) {
				return value = "5";
			}
			return sheet.getRow(row).getCell(col).getStringCellValue();
		} else {
			double cellValues = sheet.getRow(row).getCell(col).getNumericCellValue();
			return Double.toString(cellValues);
		}
	}

	public static double getDoubleCellValue(int row, int col) {
		double cellValues = 0;
		sheet.getRow(row).getCell(col);
		if (sheet.getRow(row).getCell(col).getCellType() == CellType.STRING) {
			cellValues = sheet.getRow(row).getCell(col).getNumericCellValue();
		}
		return cellValues;
	}

	public String filterTestCases() {
		String testsFiltered = null;
		try {
			for (int i = 1; i < getRowCount() + 1; i++) {
				if (getTestCaseExecutionStatus(i) == true) {
					testsFiltered = getTestCaseName(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testsFiltered;
	}

	public static void closeConnectionWithExcel() {
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getFileExtension(File file) {
		String extension = "";
		try {
			if (file != null && file.exists()) {
				String name = file.getName();
				extension = name.substring(name.lastIndexOf("."));
			}
		} catch (Exception e) {
			extension = "";
		}
		return extension;
	}

	public static Double removeTrailingZerosAfterDecimal(Double input) {
		double output = 0;
		DecimalFormat formatter = new DecimalFormat("0.####");
		output = Double.parseDouble(formatter.format(input));
		return output;
	}

	public static String removeTrailingZerosAfterDecimal(String input) {
		String output = null;
		DecimalFormat formatter = new DecimalFormat("0.####");
		output = formatter.format(input);
		return output;
	}

	public static void setValueExcelNoti(String filePath, ArrayList<String> value) {
		try {
			FileInputStream inputStream = new FileInputStream(new File(filePath));
			Workbook workbook = WorkbookFactory.create(inputStream);
			sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getLastRowNum();
			Row row = sheet.createRow(++rowCount);
			for (int i = 0; i < value.size(); i++) {
				Cell cell = row.createCell(i);
				if (i == 1) {
					cell.setCellValue(value.get(i));
				}
				cell.setCellValue(value.get(i));
			}
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(filePath);
			workbook.write(outputStream);
			ExtentLogger.logInfo("written in excel sucess");
			workbook.close();
			outputStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void updateStatus(String excelLocation, String value, int rowNum) throws IOException {
		FileInputStream fis = new FileInputStream(excelLocation);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum);
		}
		int columnIndex = 8; // Assuming the column index is fixed
		Cell cell = row.getCell(columnIndex);
		if (cell == null) {
			cell = row.createCell(columnIndex);
		}
		cell.setCellValue(value);
		System.out.println("Column updated");
		FileOutputStream fos = new FileOutputStream(excelLocation);
		workbook.write(fos);
		workbook.close();
		fos.close();
	}

}
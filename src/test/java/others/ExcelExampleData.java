package others;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExampleData {

	public static void main(String[] args) {
		XSSFWorkbook ExcelWBook;
		XSSFSheet ExcelWSheet;
		XSSFCell ExcelCell;

		String path = System.getProperty("user.dir") + "//src//test//resources//testdata//ExampleData.xlsx";
		String sheetName = "Sheet1";

		try {
			FileInputStream ExcelFile = new FileInputStream(path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			ExcelCell = ExcelWSheet.getRow(1).getCell(0);

			String cellData = ExcelCell.getStringCellValue();
			System.out.println("Cell Data value is : " + cellData);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

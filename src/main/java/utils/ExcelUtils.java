package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {
    private static Workbook workbook;
    private static Sheet sheet;

    public static void loadExcel(String filePath,String sheetName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet= workbook.getSheet(sheetName);
    }

    public static String getCellValue(int row,int column){
        Cell cell = sheet.getRow(row).getCell(column);
        if (cell.getCellType()== CellType.STRING){
            return cell.getStringCellValue();
        }
        else if (cell.getCellType()==CellType.NUMERIC){
            return String.valueOf((int) cell.getNumericCellValue());
        }
        return " ";
    }

    public static int getTotalRows(){
        return sheet.getPhysicalNumberOfRows();
    }


    public static void closeworkBook() throws IOException {
        workbook.close();
    }
}

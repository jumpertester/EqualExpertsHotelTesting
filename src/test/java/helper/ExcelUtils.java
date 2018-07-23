package helper;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by cwarren on 13/07/2018.
 */
public class ExcelUtils {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    public static void main(String args[]) throws IOException{
        String filePath = System.getProperty("user.dir")+"\\src\\test\\data\\CreateBookingTestData.xlsx";
        FileInputStream ExcelFile = new FileInputStream(filePath);

        ExcelWBook = new XSSFWorkbook(ExcelFile);

        ExcelFile.close();

    }

    public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {
        String[][] tabArray = null;
        FileInputStream ExcelFile = new FileInputStream(FilePath);
        try {
            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);

            int startRow = 1;
            int startCol = 1;
            int ci, cj;
            int totalRows = ExcelWSheet.getLastRowNum();

            // you can write a function as well to get Column count
            int totalCols = 6;
            tabArray = new String[totalRows][totalCols];
            ci = 0;
            for (int i = startRow; i <= totalRows; i++, ci++) {
                cj = 0;
                for (int j = startCol; j <= totalCols; j++, cj++) {
                    tabArray[ci][cj] = getCellData(i, j);
                    System.out.println(tabArray[ci][cj]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        } finally {
            ExcelFile.close();
        }
        return (tabArray);
    }

    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
                Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
                int dataType = Cell.getCellType();
                if (dataType == 3) {
                    return "";
                } else {
                    String CellData = Cell.getStringCellValue();
                    return CellData;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                throw (e);
            }
        }
}

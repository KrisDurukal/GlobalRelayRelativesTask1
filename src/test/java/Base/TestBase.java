package Base;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestBase {
    static File file = new File("src/test_file.xlsx");

    //Opening reading file
    FileInputStream inputStream = new FileInputStream(file);

    //Loading into the class
    static XSSFWorkbook workbook;

    static {
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    //Passing worksheet and name
    public static XSSFSheet worksheet = workbook.getSheet("simple_test_file");

    //checking how many rows have been used in the file, can work at any file regardless the row number.
    public static  int activeRows = worksheet.getPhysicalNumberOfRows();

    public TestBase() throws IOException, InvalidFormatException {
    }
}

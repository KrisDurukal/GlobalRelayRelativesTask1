package Tests;

import Base.TestBase;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserValidationTests extends TestBase
{
    public UserValidationTests() throws IOException, InvalidFormatException {
    }

    @Test
    public void maxCharacter()
    {
        //Checking that there can be maximum 256 characters in each fields.
        for (int row = 0; row<activeRows; row++)
        {
            Assert.assertTrue(worksheet.getRow(row).getCell(row).getStringCellValue().length() < 256);
        }
    }

    @Test
    public void mandatoryCells()
    {
        List<String> first_name = new ArrayList<>();
        List<String> last_name = new ArrayList<>();
        for (int row = 1; row<activeRows; row++)
        {
            //Checking if firstName and lastName contains at least one character.
            Assert.assertTrue(worksheet.getRow(row).getCell(0).getStringCellValue().length()>1);
            Assert.assertTrue(worksheet.getRow(row).getCell(1).getStringCellValue().length()>1);
            first_name.add(worksheet.getRow(row).getCell(0).getStringCellValue());
            last_name.add(worksheet.getRow(row).getCell(1).getStringCellValue());
        }

        StringBuilder firstN = new StringBuilder();
        for (String s : first_name) {
            firstN.append(s);
        }
        String names = firstN.toString();

        StringBuilder lastN = new StringBuilder();
        for (String s : first_name) {
            lastN.append(s);
        }
        String lastNames = lastN.toString();

        //Checking if firstName and lastName contains only alpha characters.
        Assert.assertTrue(names.trim().matches("[A-Za-z0-9]+"));
        Assert.assertTrue(lastNames.trim().matches("[A-Za-z0-9]+"));
    }


    @Test
    public void emailFormatCheck()
    {
        List<String> email = new ArrayList<>();
        for (int row = 1; row<activeRows; row++)
        {
            email.add(worksheet.getRow(row).getCell(9).getStringCellValue());
        }
        for (int row = 0; row<activeRows-1; row++)
        {
            //Checking if email addresses contains '@' and '.com' for an appropriate email type.
            Assert.assertTrue(email.get(row).contains("@"));
            Assert.assertTrue(email.get(row).contains("com"));
        }
    }
}

package MainProject;
import Base.TestBase;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RelatedPeople extends TestBase
{

    public RelatedPeople() throws IOException, InvalidFormatException {}

    public static void main(String[] args) throws IOException, InvalidFormatException
    {
        //These are my array lists. I've get all my data to these lists to work on them from the excelsheet.
        List<String> first_name = new ArrayList<>();
        List<String> last_name = new ArrayList<>();
        List<String> company_name = new ArrayList<>();
        List<String> address = new ArrayList<>();
        List<String> city = new ArrayList<>();
        List<String> province = new ArrayList<>();
        List<String> postal = new ArrayList<>();
        List<String> phone1 = new ArrayList<>();
        List<String> phone2 = new ArrayList<>();
        List<String> email = new ArrayList<>();
        List<String> web = new ArrayList<>();

        for (int row = 1; row<activeRows; row++)
        {
            first_name.add(worksheet.getRow(row).getCell(0).getStringCellValue());
            last_name.add(worksheet.getRow(row).getCell(1).getStringCellValue());
            company_name.add(worksheet.getRow(row).getCell(2).getStringCellValue());
            address.add(worksheet.getRow(row).getCell(3).getStringCellValue());
            city.add(worksheet.getRow(row).getCell(4).getStringCellValue());
            province.add(worksheet.getRow(row).getCell(5).getStringCellValue());
            postal.add(worksheet.getRow(row).getCell(6).getStringCellValue());
            phone1.add(worksheet.getRow(row).getCell(7).getStringCellValue());
            phone2.add(worksheet.getRow(row).getCell(8).getStringCellValue());
            email.add(worksheet.getRow(row).getCell(9).getStringCellValue());
            web.add(worksheet.getRow(row).getCell(10).getStringCellValue());
        }

        Set<String> relatives = null;
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < first_name.size(); i++)
        {
            relatives = findRelatives(i, first_name, last_name);
            //I've converted my list to a Stringbuilder to wrote it to the output file easily.
            output.append(first_name.get(i)+ " is related to "+relatives.toString().substring(1,relatives.toString().length()-1)+"\n");
        }

        FileOutputStream out;
        PrintStream outputText;

        try
        {
            // I created a new file output stream to get my output as a separated text file.
            out = new FileOutputStream("RelativesResultOutput.txt");

            // I connected print stream to the output stream.
            outputText = new PrintStream( out );

            outputText.append (output);

            outputText.close();
        }
        catch (Exception e) {}
    }
    //Here is my constructor to find my relative ones.
    private static Set<String> findRelatives(int index, List<String> first_name, List<String> last_name)
    {
        Set<String> relativeNames = new HashSet<String>();

        // Created an Array to split last name lists fot size one or two.
        String[] lastNamesToLookUp = last_name.get(index).split("-");
        //Checking all the lastNames if there is any same one.
        for (String surname : lastNamesToLookUp)
        {
            for (int i = 0; i < last_name.size(); i++)
            {
                if (i == index)
                {
                    //if index is not the same exact word it is checking the splitted second part.
                    continue;
                }
                String[] innerSplit = last_name.get(index).split("-");
                for (String surnameInner : innerSplit)
                {
                    if (surname.equals(surnameInner))
                    {
                        relativeNames.add(first_name.get(i));

                    }
                }
            }

        }
        return relativeNames;
    }
}





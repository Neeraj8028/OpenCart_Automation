package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

//	DataProvider 1
	
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {

        String path = ".\\testData\\OpenCart_LoginData.xlsx"; // Taking data from XL file 

        ExcelUtility xlutil = new ExcelUtility(path); // Creating an object for ExcelUtility

        int totalRows = xlutil.getRowCount("Sheet1");
        int totalCols = xlutil.getCellCount("Sheet1", 1);

        String[][] loginData = new String[totalRows][totalCols]; // Creating a 2D array

        for (int i = 0; i < totalRows; i++) { // Fixed index range
            for (int j = 0; j < totalCols; j++) {
                loginData[i][j] = xlutil.getCellData("Sheet1", i + 1, j); // Sheet row index starts from 1
            }
        }
        return loginData;
    }
    
//	DataProvider 2
    
//	DataProvider 3
    
//	DataProvider 4
}

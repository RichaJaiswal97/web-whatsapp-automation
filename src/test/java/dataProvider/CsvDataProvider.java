package dataProvider;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import org.testng.annotations.DataProvider;

public class CsvDataProvider {

 //   HSSFDataFormatter hdf = new HSSFDataFormatter();


    @DataProvider (name = "CSV")

    public static Object[] getExcelData() throws IOException {
        HSSFDataFormatter hdf = new HSSFDataFormatter();
        FileInputStream fileName = new FileInputStream("./src/main/resources/data.xlsx");
        Workbook excel = new XSSFWorkbook(fileName);
        Sheet sheetNo = excel.getSheetAt(0);
        int rowCount = sheetNo.getPhysicalNumberOfRows();
        int columnCount = sheetNo.getRow(0).getLastCellNum();
        Object[][] data = new Object[rowCount - 1][columnCount];
        for (int i = 0; i < rowCount; i++) {
            Row row = sheetNo.getRow(i + 1);
            if (row != null) {
                for (int k = 0; k < columnCount; k++) {
                    Cell cellValue = row.getCell(k);

                    data[i][k] = hdf.formatCellValue(cellValue);
                }
            }
        }

        return data;

    }
}

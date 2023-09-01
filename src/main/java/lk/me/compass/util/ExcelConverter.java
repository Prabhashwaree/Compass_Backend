package lk.me.compass.util;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ExcelConverter {

    //convert excel file into java DTO
    public static void readExcelFile(MultipartFile file) throws EncryptedDocumentException, IOException  {
    
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        int lastcell=sheet.getLastRowNum();
    
        for(int i=1;i<=lastcell;i++){
            System.out.println(sheet.getRow(i).getCell(0).getStringCellValue());
            System.out.println(sheet.getRow(i).getCell(1).getNumericCellValue());
            System.out.println(sheet.getRow(i).getCell(2).getStringCellValue());
        }
    }
    
}
 
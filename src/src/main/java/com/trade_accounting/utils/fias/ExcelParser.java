package src.main.java.com.trade_accounting.utils.fias;

import com.trade_accounting.models.dto.fias.FiasAddressModelDto;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for parsing xls by initializing application
 */
@Data
public class ExcelParser {

    public static List<FiasAddressModelDto> getModelsForDb() {
        List<FiasAddressModelDto> dtoList = new ArrayList<>();
        InputStream inputStream;
        HSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream("DbAdress.xls");
            workBook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workBook.getSheet("БД Адресов");
        Iterator<Row> it = sheet.iterator();
        Row row = it.next(); //пропускаем строку - заголовок
        while (it.hasNext()) {
            row = it.next();
            FiasAddressModelDto dto = FiasAddressModelDto.builder()
                    .aolevel(row.getCell(0).getStringCellValue())
                    .formalname(row.getCell(1).getStringCellValue())
                    .shortname(row.getCell(2).getStringCellValue())
                    .aoguid(row.getCell(3).getStringCellValue())
                    .parentguid(row.getCell(4).getStringCellValue())
                    .build();
            if (row.getCell(4) == null) {
                dto.setParentguid(" ");
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
}

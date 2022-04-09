package uz.davrbank.stoplist.utils;

import com.google.common.base.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.davrbank.stoplist.dao.dto.StopListDto;
import uz.davrbank.stoplist.exception.FileException;
import uz.davrbank.stoplist.exception.handler.ApiErrorMessages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Component
public class ExcelHelper {
    public static final String mimeType = "xlsx";

    public List<StopListDto> excelToList(MultipartFile files) {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        List<StopListDto> list = new LinkedList<>();
        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(files.getInputStream());
        } catch (IOException e) {
            throw new FileException(String.format(ApiErrorMessages.INTERNAL_SERVER_ERROR + "%s", e.getMessage()));
        }
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 5; index <= worksheet.getPhysicalNumberOfRows(); index++) {
            if (worksheet.getRow(index) == null)
                break;
            XSSFRow row = worksheet.getRow(index);

            StopListDto dto = new StopListDto();

            dto.setDocType("Национальный");

            dto.setDocNumber(getCellValue(row, 0));
            dto.setCyrLastname(getCellValue(row, 1));
            dto.setCyrFirstname(getCellValue(row, 2));
            dto.setCyrPatronymic(getCellValue(row, 3));
            dto.setLatLastname(getCellValue(row, 4));
            dto.setLatFirstname(getCellValue(row, 5));
            dto.setLatPatronymic(getCellValue(row, 6));
            dto.setBirthDate(getCellDateValue(row, 7));
            dto.setBirthPlace(getCellValue(row, 8));
            dto.setPSeries(getCellValue(row, 9));
            dto.setPNumber(getCellValue(row, 10));
            dto.setPIssueDate(getCellDateValue(row, 11));
            dto.setRegAddress(getCellValue(row, 12));
            dto.setNationality(getCellValue(row, 13));
            dto.setStir(getCellValue(row, 14));
            dto.setWorkplaceInfo(getCellValue(row, 15));
            dto.setYattRegDate(getCellDateValue(row, 16));
            dto.setYattRegNumber(getCellValue(row, 17));
            dto.setYattActivityType(getCellValue(row, 18));

            list.add(dto);
        }
        return list;
    }

    private String getCellValue(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();

        Cell cell = row.getCell(cellNo);

        return formatter.formatCellValue(cell);
    }

    private String getCellDateValue(Row row, int cellNo) {
        Cell cell = row.getCell(cellNo);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        DataFormatter formatter = new DataFormatter();

        String str = formatter.formatCellValue(cell);

        if (Strings.isNullOrEmpty(str))
            return "";

        if (cell.getCellType().equals(CellType.STRING)){
            if (str.contains(","))
                return str.replaceAll(",", ".");
            return formatter.formatCellValue(cell);
        }

        return sdf.format(cell.getDateCellValue());
    }
}

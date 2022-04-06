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
import uz.davrbank.stoplist.dao.model.StopListEntity;
import uz.davrbank.stoplist.exception.FileException;
import uz.davrbank.stoplist.exception.handler.ApiErrorMessages;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Component
public class ExcelHelper {
    public static final String mimeType = "xlsx";

    public List<StopListEntity> excelToList(MultipartFile files) {
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        List<StopListEntity> list = new LinkedList<>();
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

//            if (Strings.isNullOrEmpty(getCellValue(row, 0)))
//                break;

            StopListEntity entity = new StopListEntity();
            entity.setDocNumber(getCellValue(row, 0));
            entity.setCyrLastname(getCellValue(row, 1));
            entity.setCyrFirstname(getCellValue(row, 2));
            entity.setCyrPatronymic(getCellValue(row, 3));
            entity.setLatLastname(getCellValue(row, 4));
            entity.setLatFirstname(getCellValue(row, 5));
            entity.setLatPatronymic(getCellValue(row, 6));
            entity.setBirthDate(getCellDateValue(row, 7));
            entity.setBirthPlace(getCellValue(row, 8));
            entity.setPSeries(getCellValue(row, 9));
            entity.setPNumber(getCellValue(row, 10));
            entity.setPIssueDate(getCellDateValue(row, 11));
            entity.setRegAddress(getCellValue(row, 12));
            entity.setStir(getCellValue(row, 13));
            entity.setWorkplaceInfo(getCellValue(row, 14));
            entity.setYttRegDate(getCellDateValue(row, 15));
            entity.setYttRegNumber(getCellValue(row, 16));
            entity.setYttActivityType(getCellValue(row, 17));

            list.add(entity);
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

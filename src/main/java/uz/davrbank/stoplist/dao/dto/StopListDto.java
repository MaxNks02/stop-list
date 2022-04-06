package uz.davrbank.stoplist.dao.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StopListDto extends BaseDto{
    String docNumber;
    // Name info cyrillic
    String cyrLastname;
    String cyrFirstname;
    String cyrPatronymic;
    // Name info latin
    String latLastname;
    String latFirstname;
    String latPatronymic;
    // Birth info
    String birthDate;
    String birthPlace;
    // Passport info
    String pSeries;
    String pNumber;
    String pIssueDate;
    // Registered or residential address
    String regAddress;
    // STIR
    String stir;
    // Work place info
    String workplaceInfo;
    // ЯТТ сифатида фаолият кўрсатса
    String yttRegDate;
    String yttRegNumber;
    String yttActivityType;
}

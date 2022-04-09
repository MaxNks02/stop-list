package uz.davrbank.stoplist.dao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StopListDto extends BaseDto{
    @JsonProperty("doc_number")
    String docNumber;
    @JsonProperty("doc_type")
    String docType;
    // Name info cyrillic
    @JsonProperty("cyr_lastname")
    String cyrLastname;
    @JsonProperty("cyr_firstname")
    String cyrFirstname;
    @JsonProperty("cyr_patronymic")
    String cyrPatronymic;
    // Name info latin
    @JsonProperty("lat_lastname")
    String latLastname;
    @JsonProperty("lat_firstname")
    String latFirstname;
    @JsonProperty("lat_patronymic")
    String latPatronymic;
    // Birth info
    @JsonProperty("birth_date")
    String birthDate;
    @JsonProperty("birth_place")
    String birthPlace;
    // Passport info
    @JsonProperty("p_series")
    String pSeries;
    @JsonProperty("p_number")
    String pNumber;
    @JsonProperty("p_issue_date")
    String pIssueDate;
    // Registered or residential address
    @JsonProperty("reg_address")
    String regAddress;
    // Nationality
    @JsonProperty("nationality")
    String nationality;
    // STIR
    @JsonProperty("STIR")
    String stir;
    // Work place info
    @JsonProperty("workplace_info")
    String workplaceInfo;
    // ЯТТ сифатида фаолият кўрсатса
    @JsonProperty("yatt_reg_date")
    String yattRegDate;
    @JsonProperty("yatt_reg_number")
    String yattRegNumber;
    @JsonProperty("yatt_activity_type")
    String yattActivityType;
}

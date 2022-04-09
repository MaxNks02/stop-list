package uz.davrbank.stoplist.dao.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stop_list")
public class StopListEntity extends BaseEntity{
    String docNumber;
    String docType;
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
    // Nationality
    String nationality;
    // STIR
    String stir;
    // Work place info
    String workplaceInfo;
    // ЯТТ сифатида фаолият кўрсатса
    String yattRegDate;
    String yattRegNumber;
    String yattActivityType;
}

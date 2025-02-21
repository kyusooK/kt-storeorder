package ktstoreorder.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "OrderPage_table")
@Data
public class OrderPage {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String orderInfo;
    private String requestInfo;
    private String orderStatus;
    private String pickUpLocation;
}

package ktstoreorder.external;

import java.util.Date;
import lombok.Data;

@Data
public class Menu {

    private Long id;
    private String menuName;
    private Long price;
    private String menuInfo;
    private String ingredients;
}

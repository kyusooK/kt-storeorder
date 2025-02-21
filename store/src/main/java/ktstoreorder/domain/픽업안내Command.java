package ktstoreorder.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class 픽업안내Command {

    private FoodStatus foodStatus;
}

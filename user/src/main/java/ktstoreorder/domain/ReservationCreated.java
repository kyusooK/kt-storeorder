package ktstoreorder.domain;

import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class ReservationCreated extends AbstractEvent {

    private String taskId;
    private String userId;
    private String title;
    private String description;
    private Date dueDate;
}

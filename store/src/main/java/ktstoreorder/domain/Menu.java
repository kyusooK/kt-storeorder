package ktstoreorder.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import ktstoreorder.StoreApplication;
import ktstoreorder.domain.MenuRegistered;
import lombok.Data;

@Entity
@Table(name = "Menu_table")
@Data
//<<< DDD / Aggregate Root
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String menuName;

    private Long price;

    private String menuInfo;

    private String ingredients;

    @PostPersist
    public void onPostPersist() {
        MenuRegistered menuRegistered = new MenuRegistered(this);
        menuRegistered.publishAfterCommit();
    }

    public static MenuRepository repository() {
        MenuRepository menuRepository = StoreApplication.applicationContext.getBean(
            MenuRepository.class
        );
        return menuRepository;
    }
}
//>>> DDD / Aggregate Root

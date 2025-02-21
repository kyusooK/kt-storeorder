package ktstoreorder.external;

import java.util.Date;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "store", url = "${api.url.store}")
public interface MenuService {
    @GetMapping(path = "/menus/{id}")
    public List<Menu> getMenu(@RequestParam("ids") List<Long> ids);
}

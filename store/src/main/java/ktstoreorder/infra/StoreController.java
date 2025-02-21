package ktstoreorder.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import ktstoreorder.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/stores")
@Transactional
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @RequestMapping(
        value = "/stores/{id}/cook",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Store cook(
        @PathVariable(value = "id") Long id,
        @RequestBody CookCommand cookCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /store/cook  called #####");
        Optional<Store> optionalStore = storeRepository.findById(id);

        optionalStore.orElseThrow(() -> new Exception("No Entity Found"));
        Store store = optionalStore.get();
        store.cook(cookCommand);

        storeRepository.save(store);
        return store;
    }

    @RequestMapping(
        value = "/stores/{id}/픽업안내",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Store notifyPickup(
        @PathVariable(value = "id") Long id,
        @RequestBody NotifyPickupCommand notifyPickupCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /store/notifyPickup  called #####");
        Optional<Store> optionalStore = storeRepository.findById(id);

        optionalStore.orElseThrow(() -> new Exception("No Entity Found"));
        Store store = optionalStore.get();
        store.notifyPickup(notifyPickupCommand);

        storeRepository.save(store);
        return store;
    }

    @RequestMapping(
        value = "/stores/{id}/approvestore",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Store approveStore(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /store/approveStore  called #####");
        Optional<Store> optionalStore = storeRepository.findById(id);

        optionalStore.orElseThrow(() -> new Exception("No Entity Found"));
        Store store = optionalStore.get();
        store.approveStore();

        storeRepository.save(store);
        return store;
    }
}
//>>> Clean Arch / Inbound Adaptor

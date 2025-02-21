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
// @RequestMapping(value="/stroes")
@Transactional
public class StroeController {

    @Autowired
    StroeRepository stroeRepository;

    @RequestMapping(
        value = "/stroes/{id}/조리",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Stroe cook(
        @PathVariable(value = "id") Long id,
        @RequestBody CookCommand cookCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /stroe/cook  called #####");
        Optional<Stroe> optionalStroe = stroeRepository.findById(id);

        optionalStroe.orElseThrow(() -> new Exception("No Entity Found"));
        Stroe stroe = optionalStroe.get();
        stroe.cook(cookCommand);

        stroeRepository.save(stroe);
        return stroe;
    }

    @RequestMapping(
        value = "/stroes/{id}/픽업안내",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Stroe notifyPickup(
        @PathVariable(value = "id") Long id,
        @RequestBody NotifyPickupCommand notifyPickupCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /stroe/notifyPickup  called #####");
        Optional<Stroe> optionalStroe = stroeRepository.findById(id);

        optionalStroe.orElseThrow(() -> new Exception("No Entity Found"));
        Stroe stroe = optionalStroe.get();
        stroe.notifyPickup(notifyPickupCommand);

        stroeRepository.save(stroe);
        return stroe;
    }

    @RequestMapping(
        value = "/stroes/{id}/approvestore",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Stroe approveStore(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /stroe/approveStore  called #####");
        Optional<Stroe> optionalStroe = stroeRepository.findById(id);

        optionalStroe.orElseThrow(() -> new Exception("No Entity Found"));
        Stroe stroe = optionalStroe.get();
        stroe.approveStore();

        stroeRepository.save(stroe);
        return stroe;
    }
}
//>>> Clean Arch / Inbound Adaptor

package ktstoreorder.infra;

import ktstoreorder.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class StoreHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Store>> {

    @Override
    public EntityModel<Store> process(EntityModel<Store> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/cook")
                .withRel("cook")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/픽업안내")
                .withRel("픽업안내")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/approvestore")
                .withRel("approvestore")
        );

        return model;
    }
}

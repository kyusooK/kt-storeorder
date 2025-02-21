package ktstoreorder.infra;

import ktstoreorder.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class PromotionHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Promotion>> {

    @Override
    public EntityModel<Promotion> process(EntityModel<Promotion> model) {
        return model;
    }
}

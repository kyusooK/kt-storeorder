package ktstoreorder.domain;

import ktstoreorder.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "promotions",
    path = "promotions"
)
public interface PromotionRepository
    extends PagingAndSortingRepository<Promotion, Long> {}

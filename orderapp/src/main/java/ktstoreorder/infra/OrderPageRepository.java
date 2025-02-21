package ktstoreorder.infra;

import java.util.List;
import ktstoreorder.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "orderPages",
    path = "orderPages"
)
public interface OrderPageRepository
    extends PagingAndSortingRepository<OrderPage, Long> {}

package ktstoreorder.domain;

import java.util.Date;
import java.util.List;
import ktstoreorder.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "stores", path = "stores")
public interface StoreRepository
    extends PagingAndSortingRepository<Store, Long> {}

package ktstoreorder.domain;

import ktstoreorder.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "coupons", path = "coupons")
public interface CouponRepository
    extends PagingAndSortingRepository<Coupon, Long> {}

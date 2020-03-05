package com.carousell.marketplace.repository;

import com.carousell.marketplace.dao.Listing;
import org.hibernate.annotations.SortComparator;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author faizanmalik
 * creation date 3/4/20
 */
@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

//    @Query(
//        name = "SELECT l.* " +
//            "FROM   listing l " +
//            "       JOIN category c " +
//            "         ON c.id = l.category_id " +
//            "GROUP  BY l.category_id " +
//            "ORDER  BY count(l.category_id) DESC " +
//            "LIMIT  1",
//        nativeQuery = true
//    )
//    Listing lookTopCategory();

    List<Listing> findByCategoryId(Long categoryId, Sort sort);
}

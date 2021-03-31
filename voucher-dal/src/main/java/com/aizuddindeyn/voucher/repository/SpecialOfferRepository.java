/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.repository;

import com.aizuddindeyn.voucher.dao.SpecialOffer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author aizuddindeyn
 */
@Repository
public interface SpecialOfferRepository extends CrudRepository<SpecialOffer, Long> {

    @Query("SELECT a FROM SpecialOffer a WHERE a.uniqueId = :uniqueId")
    SpecialOffer findByUniqueId(@Param("uniqueId") String uniqueId);
}

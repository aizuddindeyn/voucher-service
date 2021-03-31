/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.repository;

import com.aizuddindeyn.voucher.dao.Recipient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author aizuddindeyn
 */
@Repository
public interface RecipientRepository extends CrudRepository<Recipient, Long> {

    @Query("SELECT a FROM Recipient a WHERE a.email = :email")
    Recipient findByEmail(@Param("email") String email);
}

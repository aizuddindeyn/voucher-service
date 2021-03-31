/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.repository;

import com.aizuddindeyn.voucher.dao.Recipient;
import com.aizuddindeyn.voucher.dao.VoucherCode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author aizuddindeyn
 */
@Repository
public interface VoucherCodeRepository extends CrudRepository<VoucherCode, Long> {

    @Query("SELECT a FROM VoucherCode a WHERE a.recipient.id = :#{#recipient.id} AND a.status = :status")
    List<VoucherCode> findByRecipientAndStatus(@Param("recipient") Recipient recipient,
                                               @Param("status") VoucherCode.Status status);

    @Query("SELECT a FROM VoucherCode a WHERE a.code = :code")
    VoucherCode findByCode(@Param("code") String code);
}

/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.repository;

import com.aizuddindeyn.voucher.dao.VoucherBatch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author aizuddindeyn
 */
@Repository
public interface VoucherBatchRepository extends CrudRepository<VoucherBatch, Long> {

    @Query("SELECT a FROM VoucherBatch a WHERE a.status = :status")
    List<VoucherBatch> findByStatus(@Param("status") VoucherBatch.Status status);
}

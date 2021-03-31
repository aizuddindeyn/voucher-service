/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author aizuddindeyn
 */
@Entity
@Table(name = "voucher_batch", indexes = {
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_created_time", columnList = "created_time"),
        @Index(name = "idx_updated_time", columnList = "updated_time")
})
@Data
public class VoucherBatch implements Serializable {

    private static final long serialVersionUID = 8692432646467207896L;

    public enum Status {
        NEW,
        COMPLETED,
        ;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "extend_info")
    private String extendInfo;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
}

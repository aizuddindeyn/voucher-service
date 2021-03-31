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
@Table(name = "special_offer", indexes = {
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_created_time", columnList = "created_time"),
        @Index(name = "idx_updated_time", columnList = "updated_time")
})
@Data
public class SpecialOffer implements Serializable {

    private static final long serialVersionUID = 945397090756877651L;

    public enum Status {
        ACTIVE,
        INACTIVE,
        ;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "unique_id")
    private String uniqueId;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "code_length")
    private Integer codeLength;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "expiry")
    private LocalDateTime expiry;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
}

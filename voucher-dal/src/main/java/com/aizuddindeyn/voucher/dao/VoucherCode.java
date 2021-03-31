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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author aizuddindeyn
 */
@Entity
@Table(name = "voucher_code", indexes = {
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_created_time", columnList = "created_time"),
        @Index(name = "idx_redeem_time", columnList = "redeem_time")
})
@Data
public class VoucherCode implements Serializable {

    private static final long serialVersionUID = -1632455176843446423L;

    public enum Status {
        VALID,
        USED,
        INVALID,
        ;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "special_offer_id")
    private SpecialOffer specialOffer;

    @Column(name = "expired_time")
    private LocalDateTime expiredTime;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "redeem_time")
    private LocalDateTime redeemTime;
}

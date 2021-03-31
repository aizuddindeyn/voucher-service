-- Recipient table
CREATE TABLE IF NOT EXISTS recipient (
    id bigint(20) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name varchar(200) NOT NULL,
    status varchar(20) NOT NULL,
    email varchar(200) NOT NULL,
    created_time datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time datetime(3) NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT UK_email UNIQUE (email)
);

CREATE INDEX IF NOT EXISTS idx_status ON recipient(status);
CREATE INDEX IF NOT EXISTS idx_created_time ON recipient(created_time);
CREATE INDEX IF NOT EXISTS idx_updated_time ON recipient(updated_time);

-- Special Offer table
CREATE TABLE IF NOT EXISTS special_offer (
    id bigint(20) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    unique_id varchar(36) NOT NULL,
    prefix varchar(4) NOT NULL,
    name varchar(200) NOT NULL,
    status varchar(10) NOT NULL,
    code_length int NULL DEFAULT 8,
    discount int NOT NULL,
    expiry datetime(3) NOT NULL,
    created_time datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time datetime(3) NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT UK_unique_id UNIQUE (unique_id)
);

CREATE INDEX IF NOT EXISTS idx_status ON special_offer(status);
CREATE INDEX IF NOT EXISTS idx_created_time ON special_offer(created_time);
CREATE INDEX IF NOT EXISTS idx_updated_time ON special_offer(updated_time);

-- Voucher code table
CREATE TABLE IF NOT EXISTS voucher_code (
    id bigint(20) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    code varchar(54) NOT NULL,
    status varchar(10) NOT NULL,
    recipient_id bigint(20) NOT NULL,
    special_offer_id bigint(20) NOT NULL,
    expired_time datetime(3) NOT NULL,
    created_time datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    redeem_time datetime(3) NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT UK_code UNIQUE (code),
    CONSTRAINT FK_recipient FOREIGN KEY (recipient_id) REFERENCES recipient(id),
    CONSTRAINT FK_special_offer FOREIGN KEY (special_offer_id) REFERENCES special_offer(id)
);

CREATE INDEX IF NOT EXISTS idx_status ON voucher_code(status);
CREATE INDEX IF NOT EXISTS idx_created_time ON voucher_code(created_time);
CREATE INDEX IF NOT EXISTS idx_redeem_time ON voucher_code(redeem_time);

-- Voucher batch create
CREATE TABLE IF NOT EXISTS voucher_batch (
    id bigint(20) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    status varchar(10) NOT NULL,
    extend_info varchar(2048) NOT NULL,
    created_time datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time datetime(3) NULL ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_status ON voucher_batch(status);
CREATE INDEX IF NOT EXISTS idx_created_time ON voucher_batch(created_time);
CREATE INDEX IF NOT EXISTS idx_updated_time ON voucher_batch(updated_time);
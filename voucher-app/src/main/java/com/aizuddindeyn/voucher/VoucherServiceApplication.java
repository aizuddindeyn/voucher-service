/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher;

import com.aizuddindeyn.voucher.util.GeneratorUtil;
import com.aizuddindeyn.voucher.util.LogUtil;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author aizuddindeyn
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.aizuddindeyn.voucher"})
public class VoucherServiceApplication {

    public static void main(String[] args) {
        MDC.put(LogUtil.DEBUG_ID, GeneratorUtil.generateDebugId());
        SpringApplication.run(VoucherServiceApplication.class, args);
    }
}

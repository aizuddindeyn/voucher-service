/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.batch;

import com.aizuddindeyn.voucher.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author aizuddindeyn
 */
@Component
public class VoucherBatchJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherBatchJob.class);

    private ScheduledExecutorService executorService;

    private final VoucherBatchTask task;

    @Value("${batch.voucher.initial.delay.minute:5}")
    private Long batchInitialDelay;

    @Value("${batch.voucher.task.interval.minute:1}")
    private Long batchTaskInterval;

    @Autowired
    public VoucherBatchJob(VoucherBatchTask task) {
        this.task = task;
    }

    @PostConstruct
    private void init() {
        LogUtil.logInfo(LOGGER, "Starting voucher batch");
        executorService = Executors.newSingleThreadScheduledExecutor(r -> new Thread(r, "voucher-batch-"));
        executorService.scheduleWithFixedDelay(task, batchInitialDelay, batchTaskInterval, TimeUnit.MINUTES);
    }

    @PreDestroy
    private void close() {
        LogUtil.logInfo(LOGGER, "Shutting down voucher batch");
        executorService.shutdown();
    }
}

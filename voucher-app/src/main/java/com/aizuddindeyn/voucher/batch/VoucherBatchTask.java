/*
 * Owned by aizuddindeyn
 * Visit https://github.com/aizuddindeyn
 */
package com.aizuddindeyn.voucher.batch;

import com.aizuddindeyn.voucher.dao.Recipient;
import com.aizuddindeyn.voucher.dao.SpecialOffer;
import com.aizuddindeyn.voucher.dao.VoucherBatch;
import com.aizuddindeyn.voucher.dao.VoucherCode;
import com.aizuddindeyn.voucher.enums.Fields;
import com.aizuddindeyn.voucher.resolver.MapperResolver;
import com.aizuddindeyn.voucher.service.batch.BatchService;
import com.aizuddindeyn.voucher.util.GeneratorUtil;
import com.aizuddindeyn.voucher.util.LogUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import static com.aizuddindeyn.voucher.util.GeneratorUtil.generateVoucherCode;

/**
 * @author aizuddindeyn
 */
@Component
public class VoucherBatchTask extends TimerTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoucherBatchTask.class);

    private final BatchService batchService;

    @Autowired
    public VoucherBatchTask(BatchService batchService) {
        this.batchService = batchService;
    }

    @Override
    public void run() {
        MDC.put(LogUtil.DEBUG_ID, GeneratorUtil.generateDebugId());
        LogUtil.logInfo(LOGGER, "Executing VoucherBatchTask at {}", LocalDateTime.now().toString());

        List<VoucherBatch> batches = batchService.findPendingVoucherBatch();
        for (VoucherBatch batch : batches) {
            try {
                Map<String, Object> extendInfo = MapperResolver.getInstance().convertValue(batch.getExtendInfo(),
                        new TypeReference<Map<String, Object>>() {
                        });
                Recipient recipient = (Recipient) extendInfo.get(Fields.RECIPIENT.getKey());
                SpecialOffer specialOffer = (SpecialOffer) extendInfo.get(Fields.SPECIAL_OFFER.getKey());

                VoucherCode voucher = new VoucherCode();
                voucher.setCode(generateVoucherCode(specialOffer.getPrefix(), specialOffer.getCodeLength()));
                voucher.setStatus(VoucherCode.Status.VALID);
                voucher.setRecipient(recipient);
                voucher.setSpecialOffer(specialOffer);
                voucher.setExpiredTime(specialOffer.getExpiry());
                voucher.setCreatedTime(LocalDateTime.now());

                batch.setStatus(VoucherBatch.Status.COMPLETED);
                batch.setUpdatedTime(LocalDateTime.now());

                batchService.save(voucher, batch);

            } catch (Exception ex) {
                LogUtil.logError(LOGGER, "VoucherBatchTask - Error processing voucher_batch: {}",
                        batch.toString());
            }
        }

        MDC.remove(LogUtil.DEBUG_ID);
    }
}

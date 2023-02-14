package org.egov.stock.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.egov.stock.service.StockReconciliationService;
import org.egov.stock.web.models.StockReconciliation;
import org.egov.stock.web.models.StockReconciliationBulkRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class StockReconciliationConsumer {

    private final StockReconciliationService service;
    private final ObjectMapper objectMapper;

    public StockReconciliationConsumer(StockReconciliationService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${stock.reconciliation.consumer.bulk.create.topic}")
    public List<StockReconciliation> bulkCreate(Map<String, Object> consumerRecord,
                                  @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        try {
            StockReconciliationBulkRequest request = objectMapper.convertValue(consumerRecord, StockReconciliationBulkRequest.class);
            return service.create(request, true);
        } catch (Exception exception) {
            log.error("error in stock reconciliation consumer bulk create", exception);
            return Collections.emptyList();
        }
    }

    @KafkaListener(topics = "${stock.reconciliation.consumer.bulk.update.topic}")
    public List<StockReconciliation> bulkUpdate(Map<String, Object> consumerRecord,
                                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        try {
            StockReconciliationBulkRequest request = objectMapper.convertValue(consumerRecord, StockReconciliationBulkRequest.class);
            return service.update(request, true);
        } catch (Exception exception) {
            log.error("error in stock reconciliation consumer bulk update", exception);
            return Collections.emptyList();
        }
    }

    @KafkaListener(topics = "${stock.reconciliation.consumer.bulk.delete.topic}")
    public List<StockReconciliation> bulkDelete(Map<String, Object> consumerRecord,
                                                @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        try {
            StockReconciliationBulkRequest request = objectMapper.convertValue(consumerRecord, StockReconciliationBulkRequest.class);
            return service.delete(request, true);
        } catch (Exception exception) {
            log.error("error in stock reconciliation consumer bulk delete", exception);
            return Collections.emptyList();
        }
    }
}

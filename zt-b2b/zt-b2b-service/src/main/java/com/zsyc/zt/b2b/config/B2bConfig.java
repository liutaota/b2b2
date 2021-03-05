package com.zsyc.zt.b2b.config;

import com.zsyc.framework.util.SnowFlakeIdWorker;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lcs on 2019-05-05.
 */
@Configuration
@ConfigurationProperties("zsyc.zt.b2b.id-worker")
@Data
public class B2bConfig {
    /**
     * data center id
     */
    private long dataCenterId = 0;
    /**
     * machine id
     */
    private long machineId = 0;

    @Bean("orderIdWorker")
    public SnowFlakeIdWorker orderIdWorker() {
        return new SnowFlakeIdWorker(this.dataCenterId, this.machineId) {
            @Override
            public String genNo() {
                return String.format("ZT_%s", super.nextId());
            }
        };
    }

    @Bean("refundReturnWorker")
    public SnowFlakeIdWorker RefundReturnWorker() {
        return new SnowFlakeIdWorker(this.dataCenterId, this.machineId) {
            @Override
            public String genNo() {
                return String.format("TH_%s", super.nextId());
            }
        };
    }

    @Bean("recDocReturnWorker")
    public SnowFlakeIdWorker recDocReturnWorker() {
        return new SnowFlakeIdWorker(this.dataCenterId, this.machineId) {
            @Override
            public String genNo() {
                return String.format("TK_%s", super.nextId());
            }
        };
    }

    @Bean("payWorker")
    public SnowFlakeIdWorker payWorker() {
        return new SnowFlakeIdWorker(this.dataCenterId, this.machineId) {
            @Override
            public String genNo() {
                return String.format("PAY_%s", super.nextId());
            }
        };
    }

    @Bean("dayBillWorker")
    public SnowFlakeIdWorker dayBillWorker() {
        return new SnowFlakeIdWorker(this.dataCenterId, this.machineId) {
            @Override
            public String genNo() {
                return String.format("DAY_%s", super.nextId());
            }
        };
    }

    @Bean("payOrderIdWorker")
    public SnowFlakeIdWorker payOrderIdWorker() {
        return new SnowFlakeIdWorker(this.dataCenterId, this.machineId);
    }

    @Bean("cashWorker")
    public SnowFlakeIdWorker cashWorker() {
        return new SnowFlakeIdWorker(this.dataCenterId, this.machineId) {
            @Override
            public String genNo() {
                return String.format("CASH_%s", super.nextId());
            }
        };
    }
}

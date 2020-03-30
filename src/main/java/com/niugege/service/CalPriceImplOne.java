package com.niugege.service;

import com.ggj.ecommerce.bizspi.annotation.BizSpi;
import com.ggj.ecommerce.bizspi.api.SpiConfig;
import com.google.common.collect.Lists;
import com.niugege.domain.InDTO;
import com.niugege.domain.OutDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@BizSpi(desc = "第一个实现")
public class CalPriceImplOne implements CalPrice {

    private static SpiConfig spiConfig = SpiConfig.create("calPriceImplOne", true, 99);

    @Override
    public boolean condition(InDTO context) {
        if (context.getId() == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<OutDTO> invoke(InDTO context) {
        log.error("第一个被执行了，哈哈哈");

        OutDTO result = new OutDTO();
        result.setName("1111");

        return Lists.newArrayList(result);
    }

    @Override
    public SpiConfig config(InDTO context) {
        return spiConfig;
    }
}

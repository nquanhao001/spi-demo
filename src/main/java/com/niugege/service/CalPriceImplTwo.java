package com.niugege.service;

import com.google.common.collect.Lists;
import com.niugege.domain.InDTO;
import com.niugege.domain.OutDTO;
import com.niugege.spi.annotation.BizSpi;
import com.niugege.spi.api.SpiConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@BizSpi(desc = "第2个实现")
public class CalPriceImplTwo implements CalPrice {

    private static SpiConfig spiConfig = SpiConfig.create("calPriceImplTwo", true, 99);

    @Override
    public boolean condition(InDTO context) {
        if (context.getId() == 2){
            return true;
        }
        return false;
    }

    @Override
    public List<OutDTO> invoke(InDTO context) {
        log.error("第2个被执行了，哈哈哈");

        OutDTO result = new OutDTO();
        result.setName("22222");

        return Lists.newArrayList(result);
    }

    @Override
    public SpiConfig config(InDTO context) {
        return spiConfig;
    }
}

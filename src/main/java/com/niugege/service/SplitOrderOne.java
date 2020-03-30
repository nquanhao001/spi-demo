package com.niugege.service;

import com.ggj.ecommerce.bizspi.annotation.BizSpi;
import com.ggj.ecommerce.bizspi.api.SpiConfig;
import com.niugege.domain.InDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@BizSpi(desc = "拆单的实现")
public class SplitOrderOne implements SplitOrder {

    private static SpiConfig spiConfig = SpiConfig.create("splitOrderOne", true, 99);

    @Override
    public SpiConfig config(InDTO context) {
        return spiConfig;
    }

    @Override
    public boolean condition(InDTO context) {
        return false;
    }

    @Override
    public List<Void> invoke(InDTO context) {
        return null;
    }
}

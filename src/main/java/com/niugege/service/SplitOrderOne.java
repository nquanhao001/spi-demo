package com.niugege.service;

import com.niugege.domain.InDTO;
import com.niugege.spi.annotation.BizSpi;
import com.niugege.spi.api.SpiConfig;
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

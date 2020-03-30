package com.niugege.service;

import com.niugege.domain.InDTO;
import com.niugege.spi.annotation.SpiFunctionPoint;
import com.niugege.spi.api.IBaseSpi;

@SpiFunctionPoint(spiBeanName = SplitOrder.BEAN)
public interface SplitOrder extends IBaseSpi<InDTO, Void> {

    String BEAN = "splitOrder";
}

package com.niugege.service;

import com.ggj.ecommerce.bizspi.annotation.SpiFunctionPoint;
import com.ggj.ecommerce.bizspi.api.IBaseSpi;
import com.niugege.domain.InDTO;

@SpiFunctionPoint(spiBeanName = SplitOrder.BEAN)
public interface SplitOrder extends IBaseSpi<InDTO, Void> {

    String BEAN = "splitOrder";
}

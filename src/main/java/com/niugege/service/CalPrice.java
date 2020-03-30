package com.niugege.service;

import com.ggj.ecommerce.bizspi.annotation.SpiFunctionPoint;
import com.ggj.ecommerce.bizspi.api.IBaseSpi;
import com.niugege.domain.InDTO;
import com.niugege.domain.OutDTO;

@SpiFunctionPoint(spiBeanName = CalPrice.BEAN)
public interface CalPrice extends IBaseSpi<InDTO, OutDTO> {

    String BEAN = "calPrice";
}

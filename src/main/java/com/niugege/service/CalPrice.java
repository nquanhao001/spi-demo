package com.niugege.service;


import com.niugege.domain.InDTO;
import com.niugege.domain.OutDTO;
import com.niugege.spi.annotation.SpiFunctionPoint;
import com.niugege.spi.api.IBaseSpi;

@SpiFunctionPoint(spiBeanName = CalPrice.BEAN)
public interface CalPrice extends IBaseSpi<InDTO, OutDTO> {

    String BEAN = "calPrice";
}

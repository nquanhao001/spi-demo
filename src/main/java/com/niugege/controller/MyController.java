package com.niugege.controller;

import com.alibaba.fastjson.JSON;
import com.niugege.domain.InDTO;
import com.niugege.domain.OutDTO;
import com.niugege.service.CalPrice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MyController {

    @Resource
    private CalPrice calPrice;

    @RequestMapping("/test/{id}")
    public String test(@PathVariable Integer id){
        InDTO inDTO = new InDTO();

        inDTO.setId(id);
        List<OutDTO> resultList = calPrice.invoke(inDTO);

        return JSON.toJSONString(resultList.get(0));
    }
}

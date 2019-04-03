package com.garvey.property.controller;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.service.FundService;
import com.garvey.property.util.IpfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author GarveyWong
 * @date 2019/4/3
 */
@Controller
@RequestMapping("/fund")
@NeededAuthority(authorities = {Authority.BASIC_READ})
public class FundController {
    @Autowired
    private IpfsUtil ipfsUtil;

    @Autowired
    private FundService fundService;


}

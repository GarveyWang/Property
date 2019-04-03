package com.garvey.property.service;

import com.garvey.property.dao.ExpenseGroupMapper;
import com.garvey.property.dao.ExpenseItemPropMapper;
import com.garvey.property.dao.IncomeGroupMapper;
import com.garvey.property.dao.IncomeItemPropMapper;
import com.garvey.property.util.Web3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GarveyWong
 * @date 2019/4/3
 */
@Service
public class FundService {
    @Autowired
    private Web3Util web3Util;

    @Autowired
    private ExpenseGroupMapper expenseGroupMapper;

    @Autowired
    private ExpenseItemPropMapper expenseItemPropMapper;

    @Autowired
    private IncomeGroupMapper incomeGroupMapper;

    @Autowired
    private IncomeItemPropMapper incomeItemPropMapper;


}

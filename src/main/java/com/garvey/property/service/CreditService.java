package com.garvey.property.service;

import com.garvey.property.dao.CreditLogMapper;
import com.garvey.property.dao.CreditMapper;
import com.garvey.property.model.Credit;
import com.garvey.property.model.CreditLog;
import com.garvey.property.model.User;
import com.garvey.property.util.Web3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GarveyWong
 * @date 2019/5/2
 */
@Service
public class CreditService {
    @Autowired
    private CreditMapper creditMapper;

    @Autowired
    private CreditLogMapper creditLogMapper;

    @Autowired
    private Web3Util web3Util;

    public Credit getCredit(String userId, Credentials credentials) {
        Credit credit = creditMapper.selectByPrimaryKey(userId);
        if (credit == null){
            credit = new Credit(userId, 0L);
            creditMapper.insert(credit);
        }
        User user = web3Util.getUser(credentials, userId);
        if (user != null) {
            credit.setUserName(user.getNickName());
        }
        return credit;
    }

    public List<Credit> getAllCredits(Credentials credentials) {
        List<Credit> credits = creditMapper.getAllCredits();
        List<Credit> result = new ArrayList<>(credits.size());
        for (Credit credit : credits) {
            User user = web3Util.getUser(credentials, credit.getUserId());
            if (user != null) {
                credit.setUserName(user.getNickName());
                result.add(credit);
            }
        }
        return result;
    }

    public List<CreditLog> getCreditLogs(Credentials credentials, String userId) {
        List<CreditLog> creditLogs = creditLogMapper.getCreditLogsByUserId(userId);
        User user = web3Util.getUser(credentials, userId);
        if (user != null) {
            for (CreditLog creditLog: creditLogs){
                creditLog.setUserName(user.getNickName());
            }
        }
        return creditLogs;
    }

    public void updateCredit(CreditLog creditLog) {
        creditLogMapper.insert(creditLog);
        Credit credit = creditMapper.selectByPrimaryKey(creditLog.getUserId());
        if (credit == null) {
            credit = new Credit(creditLog.getUserId(), creditLog.getCreditChange());
            creditMapper.insert(credit);
        } else {
            credit.setCredit(credit.getCredit() + creditLog.getCreditChange());
            creditMapper.updateByPrimaryKey(credit);
        }
    }

    public void insertCredit(Credit credit) {
        creditMapper.insert(credit);
    }
}

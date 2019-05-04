package com.garvey.property.service;

import com.garvey.property.model.Log;
import com.garvey.property.util.Web3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GarveyWong
 * @date 2019/4/3
 */
@Service
public class LogService {
    @Autowired
    private Web3Util web3Util;

    public List<Log> getLogs(Credentials credentials) {
        int logCount = web3Util.getLogCount(credentials);
        List<Log> logs = new ArrayList<>(logCount);
        for (int i = logCount - 1; i >= 0; --i) {
            Log log = web3Util.getLog(i, credentials);
            logs.add(log);
        }
        return logs;
    }

    public void writeLog(Log log, Credentials credentials){
        web3Util.writeLog(log.toString(), credentials);
    }
}

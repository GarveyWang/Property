package com.garvey.property.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garvey.property.constant.BasicConst;
import com.garvey.property.model.Motion;
import com.garvey.property.util.Web3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GarveyWong
 * @date 2019/4/5
 */
@Service
public class MotionService {
    @Autowired
    private Web3Util web3Util;

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Motion> getMotions(Credentials credentials, int pageNum) {
        List<Motion> motions = new ArrayList<>(BasicConst.MOTION_PAGE_SIZE);
        int lastIdx = web3Util.getMotionCount(credentials) - 1;
        int fromIdx = lastIdx - (pageNum - 1) * BasicConst.MOTION_PAGE_SIZE;
        int endIdx = fromIdx - BasicConst.MOTION_PAGE_SIZE;
        endIdx = (endIdx > -1) ? endIdx : -1;
        for (int i = fromIdx; i > endIdx; --i) {
            Motion motion = web3Util.getMotion(i, credentials);
            if (motion != null) {
                motions.add(motion);
            }
        }
        return motions;
    }

    public void addMotion(Motion motion, Credentials credentials){
        try {
            String optionJson = objectMapper.writeValueAsString(motion.getOptions());
            motion.setOptionsJson(optionJson);
            web3Util.addMotion(motion, credentials);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public long getMotionTotalPageCount(Credentials credentials) {
        long totalMotionCount = web3Util.getMotionCount(credentials);
        if (totalMotionCount == 0) {
            return 1;
        }
        if (totalMotionCount % BasicConst.MOTION_PAGE_SIZE == 0) {
            return totalMotionCount / BasicConst.MOTION_PAGE_SIZE;
        }
        return totalMotionCount / BasicConst.MOTION_PAGE_SIZE + 1;
    }
}

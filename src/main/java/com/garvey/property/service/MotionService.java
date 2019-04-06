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
                wrapMotion(credentials, motion);
                motions.add(motion);
            }
        }
        return motions;
    }

    public List<Motion> getMyMotions(Credentials credentials, int pageNum) {
        List<Motion> motions = new ArrayList<>(BasicConst.MOTION_PAGE_SIZE);
        int lastIdx = web3Util.getMotionCount(credentials) - 1;
        int fromIdx = lastIdx - (pageNum - 1) * BasicConst.MOTION_PAGE_SIZE;
        int endIdx = fromIdx - BasicConst.MOTION_PAGE_SIZE;
        endIdx = (endIdx > -1) ? endIdx : -1;
        for (int i = fromIdx; i > endIdx; --i) {
            Motion motion = web3Util.getMotion(i, credentials);
            if (motion != null && motion.getAuthorHash().equals(credentials.getAddress())) {
                wrapMotion(credentials, motion);
                motions.add(motion);
            }
        }
        return motions;
    }

    private void wrapMotion(Credentials credentials, Motion motion) {
        boolean hasVoted = web3Util.hasVoted(motion.getIdx(), credentials);
        motion.setHasVoted(hasVoted);

        int voteCount = web3Util.getVoteCount(motion.getIdx(), credentials);
        int[] votedOptionIndexes = new int[voteCount];
        for (int j = 0; j < voteCount; ++j) {
            votedOptionIndexes[j] = web3Util.getVoteIndex(motion.getIdx(), j, credentials);
        }
        motion.setVotedOptionIndexes(votedOptionIndexes);
    }

    public void addMotion(Motion motion, Credentials credentials) {
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
        return getTotalPageCount(totalMotionCount);
    }

    public long getMyMotionTotalPageCount(Credentials credentials) {
        long totalMotionCount = 0;
        for (int i = web3Util.getMotionCount(credentials) - 1; i >= 0; --i) {
            Motion motion = web3Util.getMotion(i, credentials);
            if (motion != null && motion.getAuthorHash().equals(credentials.getAddress())) {
                ++totalMotionCount;
            }
        }
        return getTotalPageCount(totalMotionCount);
    }

    private long getTotalPageCount(long totalMotionCount) {
        if (totalMotionCount == 0) {
            return 1;
        }
        if (totalMotionCount % BasicConst.MOTION_PAGE_SIZE == 0) {
            return totalMotionCount / BasicConst.MOTION_PAGE_SIZE;
        }
        return totalMotionCount / BasicConst.MOTION_PAGE_SIZE + 1;
    }

    public void vote(int motionIdx, int[] optionIndexes, Credentials credentials) {
        for (int i = 0; i < optionIndexes.length; ++i) {
            boolean finished = (i == (optionIndexes.length - 1));
            web3Util.voteOption(motionIdx, optionIndexes[i], finished, credentials);
        }
    }
}

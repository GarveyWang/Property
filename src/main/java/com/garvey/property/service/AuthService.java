package com.garvey.property.service;

import com.garvey.property.constant.BasicConst;
import com.garvey.property.model.AuthOperation;
import com.garvey.property.util.Web3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GarveyWong
 * @date 2019/4/29
 */
@Service
public class AuthService {
    @Autowired
    private Web3Util web3Util;

    public List<AuthOperation> getAuthApplications(Credentials credentials) {
        int applicationCount = web3Util.getAuthApplicationCount(credentials);
        List<AuthOperation> authApplications = new ArrayList<>(applicationCount);
        for (int i = applicationCount - 1; i >= 0; --i) {
            AuthOperation authOperation = web3Util.getAuthApplication(i, credentials);
            if (authOperation != null) {
                int myVote = web3Util.getAuthApplicationVote(i, credentials);
                authOperation.setMyVote(myVote);
                String targetNickName = web3Util.getUser(credentials, authOperation.getTargetAddr()).getNickName();
                authOperation.setTargetNickName(targetNickName);
                authApplications.add(authOperation);
            }
        }
        return authApplications;
    }

    public List<AuthOperation> getAuthCancellations(Credentials credentials) {
        int cancellationsCount = web3Util.getAuthCancellationCount(credentials);
        List<AuthOperation> authCancellations = new ArrayList<>(cancellationsCount);
        for (int i = cancellationsCount - 1; i >= 0; --i) {
            AuthOperation authOperation = web3Util.getAuthCancellation(i, credentials);
            if (authOperation != null) {
                int myVote = web3Util.getAuthCancellationVote(i, credentials);
                authOperation.setMyVote(myVote);
                String targetNickName = web3Util.getUser(credentials, authOperation.getTargetAddr()).getNickName();
                authOperation.setTargetNickName(targetNickName);
                authCancellations.add(authOperation);
            }
        }
        return authCancellations;
    }

    public void applyAuth(Credentials credentials, int authority) {
        web3Util.addAuthApplication(authority, credentials);
    }

    public void agreeAuthApplication(Credentials credentials, long idx) {
        web3Util.agreeAuthApplication(idx, credentials);
    }

    public void disagreeAuthApplication(Credentials credentials, long idx) {
        web3Util.disagreeAuthApplication(idx, credentials);
    }

    public void cancelAuth(Credentials credentials, int authority, String targetAddress) {
        web3Util.addAuthCancellation(authority, targetAddress, credentials);
    }

    public void agreeAuthCancellation(Credentials credentials, long idx) {
        web3Util.agreeAuthCancellation(idx, credentials);
    }

    public void disagreeAuthCancellation(Credentials credentials, long idx) {
        web3Util.disagreeAuthCancellation(idx, credentials);
    }

    public int getSettledRatio(Credentials credentials) {
        return web3Util.getSettledRatio(credentials);
    }

    public void setSettledRatio(int settledRatio, Credentials credentials) {
        web3Util.setSettledRatio(settledRatio, credentials);
    }

    public int getSettledMinCount(Credentials credentials) {
        return web3Util.getSettledMinCount(credentials);
    }

    public void setSettledMinCount(int settledMinCount, Credentials credentials) {
        web3Util.setSettledMinCount(settledMinCount, credentials);
    }
}

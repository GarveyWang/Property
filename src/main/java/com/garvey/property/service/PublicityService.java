package com.garvey.property.service;

import com.garvey.property.constant.BasicConst;
import com.garvey.property.dao.PublicityInfoPropMapper;
import com.garvey.property.model.PublicityInfo;
import com.garvey.property.model.PublicityInfoProp;
import com.garvey.property.model.User;
import com.garvey.property.util.Web3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GarveyWong
 * @date 2019/4/1
 */
@Service
public class PublicityService {
    @Autowired
    private Web3Util web3Util;

    @Autowired
    private PublicityInfoPropMapper infoPropMapper;

    public void addPublicityInfo(PublicityInfo publicityInfo, User user) {
        web3Util.addPublicityInfo(publicityInfo, user.getCredentials());
    }

    public int getPublicityInfoTotalPage(Credentials credentials) {
        int count = getPublicityInfoCount(credentials);
        if (count == 0) {
            return 1;
        }
        if (count % BasicConst.PAGE_SIZE == 0) {
            return count / BasicConst.PAGE_SIZE;
        }
        return count / BasicConst.PAGE_SIZE + 1;
    }

    public int getPublicityInfoCount(Credentials credentials) {
        return web3Util.getPublicityInfoCount(credentials);
    }

    public List<PublicityInfo> getPublicityInfoList(Credentials credentials, int pageNum) {
        List<PublicityInfo> publicityInfoList = new ArrayList<>(BasicConst.PAGE_SIZE);
        int lastIdx = web3Util.getPublicityInfoCount(credentials) - 1;
        int fromIdx = lastIdx - (pageNum - 1) * BasicConst.PAGE_SIZE;
        int endIdx = fromIdx - BasicConst.PAGE_SIZE;
        endIdx = (endIdx > -1) ? endIdx : -1;
        for (int i = fromIdx; i > endIdx; --i) {
            PublicityInfo info = web3Util.getPublicityInfo(i, credentials);
            if (info != null) {
                info.setIdx(i);
                setProp(info);
                publicityInfoList.add(info);
            }
        }
        return publicityInfoList;
    }

    public int updatePublicityInfoProp(PublicityInfoProp prop) {
        return infoPropMapper.updateByPrimaryKeyWithBLOBs(prop);
    }

    private void setProp(PublicityInfo info) {
        PublicityInfoProp prop = infoPropMapper.selectByPrimaryKey(info.getIdx());
        if (prop == null) {
            prop = new PublicityInfoProp();
            prop.setIdx(info.getIdx());
            prop.setVisible(new Byte("1"));
            infoPropMapper.insert(prop);
        }
        info.setProp(prop);
    }
}

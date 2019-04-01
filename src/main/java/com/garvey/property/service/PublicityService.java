package com.garvey.property.service;

import com.garvey.property.dao.PublicityInfoPropMapper;
import com.garvey.property.model.PublicityInfo;
import com.garvey.property.model.PublicityInfoProp;
import com.garvey.property.model.User;
import com.garvey.property.util.Web3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addPublicityInfo(PublicityInfo publicityInfo, User user){
        web3Util.addPublicityInfo(publicityInfo, user.getCredentials());
    }

    public List<PublicityInfo> getPublicityInfoList(User user){
        List<PublicityInfo> ethInfoList = web3Util.getPublicityInfoList(user.getCredentials());
        for (PublicityInfo info: ethInfoList){
            PublicityInfoProp prop = infoPropMapper.selectByPrimaryKey(info.getIdx());
            if (prop == null){
                prop = new PublicityInfoProp();
                prop.setIdx(info.getIdx());
                prop.setVisible(new Byte("1"));
                infoPropMapper.insert(prop);
            }
            info.setProp(prop);
        }
        return ethInfoList;
    }

    public int updatePublicityInfoProp(PublicityInfoProp prop){
        return infoPropMapper.updateByPrimaryKey(prop);
    }
}

package com.garvey.property.service;

import com.garvey.property.contract.PropertyContract;
import com.garvey.property.model.PublicityInfo;
import com.garvey.property.model.User;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.exceptions.ContractCallException;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.*;

/**
 * @author GarveyWong
 * @date 2019/3/31
 */
@Service
public class Web3Service {
    private static String gethAddress;
    private static String contractAddress;
    private static Web3j web3j;
    private static ContractGasProvider gasProvider;
    private static int maxRetryTimes;

    private ThreadLocal<PropertyContract> contractThreadLocal;

    static {
        contractAddress = "0x9783103008a00b9e1f2b08d1d2e62de33bdb6d28";
        gethAddress = "http://localhost:8545";
        web3j = Web3j.build(new HttpService(gethAddress));
        gasProvider = new DefaultGasProvider();
        maxRetryTimes = 200;
    }

    public User getUser(Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            //可能是web3j的问题，查询有时会返回空结果异常，没有规律，所以多次重试获取
            while (retryTimes < maxRetryTimes) {
                try {
                    Tuple4<String, String, String, BigInteger> tuple = contract.getUser(credentials.getAddress()).send();
                    if (tuple.getValue4().intValue() == 0) {
                        return null;
                    }
                    User user = new User(tuple.getValue1(), tuple.getValue2(), tuple.getValue3(), tuple.getValue4().intValue());
                    user.setCredentials(credentials);
                    System.out.println("【getUser】重试次数：" + retryTimes);
                    return user;
                } catch (IndexOutOfBoundsException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            System.out.println("【getUser】未找到");
        }
        return null;
    }

    public List<PublicityInfo> getPublicityInfos(Credentials credentials) {
        int count = getPublicityInfoCount(credentials);
        List<PublicityInfo> publicityInfoList = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            PublicityInfo info = getPublicityInfo(i, credentials);
            if (info != null) {
                publicityInfoList.add(info);
            }
        }
        return publicityInfoList;
    }

    public int getPublicityInfoCount(Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger count = contract.getPublicityInfoCount().send();
                    System.out.println("【getPublicityInfo】重试次数：" + retryTimes);
                    return count.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            System.out.println("【getPublicityInfoCount】未找到");
        }
        return -1;
    }

    public PublicityInfo getPublicityInfo(long idx, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    Tuple7<String, String, String, String, String, BigInteger, BigInteger> tuple = contract.getPublicityInfo(BigInteger.valueOf(idx)).send();
                    if (tuple.getValue7().intValue() == 0) {
                        return null;
                    }
                    String[] fileHashes = tuple.getValue3().split(":");
                    String[] fileNames = tuple.getValue4().split(":");
                    Map<String, String> attachments = new HashMap<>(fileHashes.length);
                    for (int i = 0; i < fileHashes.length; ++i) {
                        if (!fileHashes[i].isEmpty()) {
                            attachments.put(fileHashes[i], fileNames[i]);
                        }
                    }
                    PublicityInfo publicityInfo = new PublicityInfo(tuple.getValue1(), tuple.getValue2(), attachments, tuple.getValue5(), tuple.getValue6().longValue(), tuple.getValue7().intValue());
                    System.out.println("【getPublicityInfo】重试次数：" + retryTimes);
                    return publicityInfo;
                } catch (IndexOutOfBoundsException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            System.out.println("【getPublicityInfo】未找到");
        }
        return null;
    }

    public void addPublicityInfo(PublicityInfo publicityInfo, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            StringBuilder fileHashes = new StringBuilder();
            StringBuilder fileNames = new StringBuilder();
            if (publicityInfo.getAttachments() != null && !publicityInfo.getAttachments().isEmpty()) {
                for (String fileHash: publicityInfo.getAttachments().keySet()){
                    fileHashes.append(fileHash).append(":");
                    fileNames.append(publicityInfo.getAttachments().get(fileHash));
                }
            }
            try {
                contract.addPublicityInfo(publicityInfo.getTitle(), publicityInfo.getContent(), fileHashes.toString(), fileNames.toString(), BigInteger.valueOf(System.currentTimeMillis())).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private PropertyContract getPropertyContract(Credentials credentials) {
        if (contractThreadLocal == null) {
            contractThreadLocal = new ThreadLocal<>();
        }
        if (contractThreadLocal.get() == null) {
            PropertyContract contract = PropertyContract.load(contractAddress, web3j, credentials, gasProvider);
            contractThreadLocal.set(contract);
            return contract;
        }
        return contractThreadLocal.get();
    }

    public static void main(String[] args) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("202cb962ac59075b964b07152d234b70", "E:\\project\\propertySystem\\UTC--2019-03-31T13-50-28.490000000Z--7db8747fd75184fe290420ebd967324eb4ca2de2.json");
        PropertyContract contract = PropertyContract.load(contractAddress, web3j, credentials, gasProvider);

        if (contract != null) {

            //System.out.println("count:" + contract.getPublicityInfoCount().send().intValue());
            //contract.addPublicityInfo("你好", "看看", "1234,5678", BigInteger.valueOf(System.currentTimeMillis())).send();
//            System.out.println("count:" + contract.getPublicityInfoCount().send().intValue());
            int retryTimes = 0;
            while (retryTimes < 200) {
                try {
//                    Tuple4<String, String, String, BigInteger> tuple = contract.getUser(credentials.getAddress()).send();
//                    User user = new User(tuple.getValue1(), tuple.getValue2(), tuple.getValue3(), tuple.getValue4().intValue());
//                    user.setCredentials(credentials);
//                    System.out.println("【getUser】重试次数：" + retryTimes);
//                    System.out.println(contract.getPublicityInfo(BigInteger.valueOf(0)).send());
                    System.out.println(contract.getPublicityInfo(BigInteger.valueOf(9)).send());
                    break;
                } catch (IndexOutOfBoundsException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("重试次数" + retryTimes);
        }
    }

}

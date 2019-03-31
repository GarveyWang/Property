package com.garvey.property.util;

import com.garvey.property.contract.PropertyContract;
import com.garvey.property.model.User;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @author GarveyWong
 * @date 2019/3/31
 */
@Service
public class Web3Util {
    private static String gethAddress;
    private static String contractAddress;
    private static Web3j web3j;
    private static ContractGasProvider gasProvider;

    private ThreadLocal<PropertyContract> contractThreadLocal;

    static {
        contractAddress = "0xbf4d5a842be08ab38a38e1697297c5130237dc95";
        gethAddress = "http://localhost:8545";
        web3j = Web3j.build(new HttpService(gethAddress));
        gasProvider = new DefaultGasProvider();
    }

    public User getUser(Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract!=null){
            int retryTimes = 0;
            while (retryTimes < 200){
                try {
                    Tuple4<String, String, String, BigInteger> tuple = contract.getUser(credentials.getAddress()).send();
                    if (tuple.getValue4().intValue() == 0){
                        return null;
                    }
                    User user = new User(tuple.getValue1(), tuple.getValue2(), tuple.getValue3(), tuple.getValue4().intValue());
                    user.setCredentials(credentials);
                    System.out.println("【getUser】重试次数：" + retryTimes);
                    return user;
                }catch (IndexOutOfBoundsException e){
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            System.out.println("未找到");
        }
        return null;
    }

    private PropertyContract getPropertyContract(Credentials credentials) {
        if (contractThreadLocal == null){
            contractThreadLocal = new ThreadLocal<>();
        }
        if (contractThreadLocal.get() == null){
            PropertyContract contract = PropertyContract.load(contractAddress, web3j, credentials, gasProvider);
            contractThreadLocal.set(contract);
            return contract;
        }
        return contractThreadLocal.get();
    }

    public static void main(String[] args) throws IOException, CipherException {
        Credentials credentials = WalletUtils.loadCredentials("202cb962ac59075b964b07152d234b70", "E:\\project\\propertySystem\\UTC--2019-03-31T11-08-33.779000000Z--4532eead8799f2d6c2537b1ff1767fabd3b7f732.json");
        PropertyContract contract = PropertyContract.load("0xbf4d5a842be08ab38a38e1697297c5130237dc95", web3j, credentials, gasProvider);

        if (contract!=null){
            int retryTimes = 0;
            while (retryTimes < 200){
                try {
                    Tuple4<String, String, String, BigInteger> tuple = contract.getUser(credentials.getAddress()).send();
                    User user = new User(tuple.getValue1(), tuple.getValue2(), tuple.getValue3(), tuple.getValue4().intValue());
                    user.setCredentials(credentials);
                    System.out.println("【getUser】重试次数：" + retryTimes);
                    break;
                }catch (IndexOutOfBoundsException e){
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("重试次数"+retryTimes);
        }
    }

}

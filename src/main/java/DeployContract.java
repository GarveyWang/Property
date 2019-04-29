import com.garvey.property.contract.PropertyContract;
import org.apache.ibatis.transaction.TransactionException;
import org.springframework.util.DigestUtils;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.exceptions.ContractCallException;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.Optional;

/**
 * @author GarveyWong
 * @date 2019/3/28
 */
public class DeployContract {

    public static void main(String[] args) throws Exception {
//        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
//        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
//        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
//        System.out.println(clientVersion);

        String password = "123";
        String md5Psw = DigestUtils.md5DigestAsHex(password.getBytes()).toLowerCase();
        String encryptPsw = DigestUtils.md5DigestAsHex(md5Psw.getBytes()).toLowerCase();

        String file = WalletUtils.generateLightNewWalletFile(md5Psw, null);
        Credentials credentials = WalletUtils.loadCredentials(md5Psw, file);
        System.out.println("【init】Credentials账号:" + credentials.getAddress());
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        EthCoinbase coinbase = web3j.ethCoinbase().send();
        EthGetTransactionCount transactionCount = web3j.ethGetTransactionCount(coinbase.getAddress(), DefaultBlockParameterName.LATEST).send();
        Transaction transaction = Transaction.createEtherTransaction(coinbase.getAddress(), transactionCount.getTransactionCount(), BigInteger.valueOf(20_000_000_000L), BigInteger.valueOf(21_000), credentials.getAddress(), BigInteger.valueOf(90_000_000_000_000_000_00L));
        web3j.ethSendTransaction(transaction).send();
        EthGetBalance balance = web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        System.out.println("【init】Balance: " + balance.getBalance().longValue());

        StaticGasProvider gasProvider = new StaticGasProvider(BigInteger.valueOf(22000000000L), BigInteger.valueOf(6000000L));
        PropertyContract contract = PropertyContract.deploy(web3j, credentials, gasProvider, encryptPsw, "5f93f983524def3dca464469d2cf9f3e", "nick").send();
        Optional<TransactionReceipt> tr = contract.getTransactionReceipt();
        System.out.println("合同addr:" + contract.getContractAddress());

        if (tr.isPresent()) {
            System.out.println("【createContract】Transaction receipt");
        }

//        Tuple4<String, String, String, BigInteger> userTuple = contract.getUserByAddr(credentials.getAddress()).sendAsync().get();
//        System.out.println(userTuple);

//        PropertyContract loadContract = PropertyContract.load(contract.getContractAddress(), web3j, credentials, new DefaultGasProvider());

//        int i = 0;
//        long from = System.currentTimeMillis();
//        while (true) {
//            try {
//                //System.out.println("test: " + contract.getNickName(credentials.getAddress()).send());
//                Tuple4<String, String, String, BigInteger> userTuple = loadContract.getUserByAddr(credentials.getAddress()).send();
//                System.out.println(userTuple);
//                break;
//            } catch (ContractCallException e) {
//                ++i;
//                System.out.println("ContractCallException 重试" + i);
//            } catch (IndexOutOfBoundsException e) {
//                ++i;
//                System.out.println("IndexOutOfBoundsException 重试" + i);
//            }
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("用时"+ (end-from));
    }
}

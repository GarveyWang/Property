package com.garvey.property.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garvey.property.contract.PropertyContract;
import com.garvey.property.model.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import org.web3j.abi.datatypes.Bool;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCoinbase;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tuples.generated.Tuple8;
import org.web3j.tx.exceptions.ContractCallException;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

/**
 * @author GarveyWong
 * @date 2019/3/31
 */
@Component
public class Web3Util {
    private static String gethAddress;
    private static String contractAddress;
    private static Web3j web3j;
    private static ContractGasProvider gasProvider;
    private static int maxRetryTimes;
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        contractAddress = "0xf128489a28ad37134c55e538c1a011107f50db27";
        gethAddress = "http://localhost:8545";
        web3j = Web3j.build(new HttpService(gethAddress));
        gasProvider = new DefaultGasProvider();
        maxRetryTimes = 200;
    }

    public void test(Credentials credentials){
        PropertyContract contract = getPropertyContract(credentials);
    }

    @Cacheable(value = "user", key = "#addr", unless = "#result == null")
    public User getUser(Credentials credentials, String addr) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            //可能是web3j的问题，查询有时会返回空结果异常，没有规律，所以多次重试获取
            while (retryTimes < maxRetryTimes) {
                try {
                    Tuple5<String, String, String, String, BigInteger> tuple = contract.getUserByAddr(addr).send();
                    if (tuple.getValue5().intValue() == 0) {
                        return null;
                    }
                    User user = new User(tuple.getValue1(), tuple.getValue2(), tuple.getValue3(), tuple.getValue4(), tuple.getValue5().intValue());
                    user.setCredentials(credentials);
                    System.out.println("【getUserByAddr】重试次数：" + retryTimes);
                    return user;
                } catch (IndexOutOfBoundsException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            System.out.println("【getUserByAddr】未找到");
        }
        return null;
    }

    @Cacheable(value = "user", key = "#encryptedPhone", unless = "#result == null")
    public User getUserByPhone(Credentials credentials, String encryptedPhone) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    Tuple5<String, String, String, String, BigInteger> tuple = contract.getUserByPhone(encryptedPhone).send();
                    if (tuple.getValue5().intValue() == 0) {
                        return null;
                    }
                    User user = new User(tuple.getValue1(), tuple.getValue2(), tuple.getValue3(), tuple.getValue4(), tuple.getValue5().intValue());
                    user.setCredentials(credentials);
                    System.out.println("【getUserByPhone】重试次数：" + retryTimes);
                    return user;
                } catch (IndexOutOfBoundsException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            System.out.println("【getUserByPhone】未找到");
        }
        return null;
    }

    @Cacheable(value = "userCount", key = "")
    public int getUserCount(Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger count = contract.getUserCount().send();
                    System.out.println("【getUserCount】重试次数：" + retryTimes);
                    return count.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            System.out.println("【getUserCount】未找到");
        }
        return -1;
    }

    @Cacheable(value = "userByIndex", key = "#index", unless = "#result == null")
    public User getUserByIndex(Credentials credentials, int index) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    Tuple5<String, String, String, String, BigInteger> tuple = contract.getUserByIndex(BigInteger.valueOf(index)).send();
                    if (tuple.getValue5().intValue() == 0) {
                        return null;
                    }
                    User user = new User(tuple.getValue1(), tuple.getValue2(), tuple.getValue3(), tuple.getValue4(), tuple.getValue5().intValue());
                    user.setCredentials(credentials);
                    System.out.println("【getUserByIndex】重试次数：" + retryTimes);
                    return user;
                } catch (IndexOutOfBoundsException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            System.out.println("【getUserByIndex】未找到");
        }
        return null;
    }

    @CacheEvict(value = "userCount", allEntries = true)
    public boolean addProprietorAccount(int code, User user){
        try {
            recharge(user.getCredentials(), 25_000_000_000_000_000_00L);
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        PropertyContract contract = getPropertyContract(user.getCredentials());
        if (contract != null) {
            try {
                contract.registerProprietor(BigInteger.valueOf(code), user.getEncryptedPwd(), user.getEncryptedPhone(), user.getNickName()).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @CacheEvict(value = "userCount", allEntries = true)
    public boolean addPropertyAccount(int code, User user){
        try {
            recharge(user.getCredentials(), 25_000_000_000_000_000_00L);
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

        PropertyContract contract = getPropertyContract(user.getCredentials());
        if (contract != null) {
            try {
                contract.registerProperty(BigInteger.valueOf(code), user.getEncryptedPwd(), user.getEncryptedPhone(), user.getNickName()).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void recharge(Credentials credentials, long rechargeValue) throws IOException {
        EthCoinbase coinbase = web3j.ethCoinbase().send();
        EthGetTransactionCount transactionCount = web3j.ethGetTransactionCount(coinbase.getAddress(), DefaultBlockParameterName.LATEST).send();
        Transaction transaction = Transaction.createEtherTransaction(coinbase.getAddress(), transactionCount.getTransactionCount(), BigInteger.valueOf(20_000_000_000L), BigInteger.valueOf(21_000), credentials.getAddress(), BigInteger.valueOf(rechargeValue));
        web3j.ethSendTransaction(transaction).send();
    }

    @Cacheable(value = "publicityInfoCount", key = "")
    public int getPublicityInfoCount(Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger count = contract.getPublicityInfoCount().send();
                    System.out.println("【getPublicityInfoCount】重试次数：" + retryTimes);
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

    @Cacheable(value = "publicityInfo", key = "#idx", unless = "#result == null")
    public PublicityInfo getPublicityInfo(long idx, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    Tuple6<String, String, String, String, String, BigInteger> tuple = contract.getPublicityInfo(BigInteger.valueOf(idx)).send();
                    if (tuple.getValue6().longValue() == 0) {
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
                    User author = getUser(credentials, tuple.getValue5());
                    PublicityInfo publicityInfo = new PublicityInfo(idx, tuple.getValue1(), tuple.getValue2(), attachments, tuple.getValue5(), author.getNickName(), tuple.getValue6().longValue());
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

    @CacheEvict(value = "publicityInfoCount", allEntries = true)
    public void addPublicityInfo(PublicityInfo publicityInfo, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            StringBuilder fileHashes = new StringBuilder();
            StringBuilder fileNames = new StringBuilder();
            if (publicityInfo.getAttachments() != null && !publicityInfo.getAttachments().isEmpty()) {
                for (String fileHash : publicityInfo.getAttachments().keySet()) {
                    fileHashes.append(fileHash).append(":");
                    fileNames.append(publicityInfo.getAttachments().get(fileHash)).append(":");
                }
            }
            try {
                contract.addPublicityInfo(publicityInfo.getTitle(), publicityInfo.getContent(), fileHashes.toString(), fileNames.toString(), BigInteger.valueOf(System.currentTimeMillis())).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Cacheable(value = "expenseItemCount", key = "")
    public int getExpenseItemCount(Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger count = contract.getExpenseItemCount().send();
                    System.out.println("【getExpenseItemCount】重试次数：" + retryTimes);
                    return count.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            System.out.println("【getExpenseItemCount】未找到");
        }
        return -1;
    }

    @Cacheable(value = "expenseItem", key = "#idx", unless = "#result == null")
    public ExpenseItem getExpenseItem(long idx, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    Tuple7<String, String, BigInteger, String, String, String, BigInteger> tuple = contract.getExpenseItem(BigInteger.valueOf(idx)).send();
                    if (tuple.getValue7().longValue() == 0) {
                        return null;
                    }

                    ExpenseItem expenseItem = new ExpenseItem();
                    expenseItem.setIdx(idx);
                    //Payee
                    expenseItem.setPayee(tuple.getValue1());
                    if (expenseItem.getPayee().startsWith("0x")) {
                        User payee = getUser(credentials, expenseItem.getPayee());
                        if (payee != null) {
                            expenseItem.setPayeeName(payee.getNickName());
                        }
                    }
                    //Recorder
                    expenseItem.setRecorderHash(tuple.getValue2());
                    User recorder = getUser(credentials, expenseItem.getRecorderHash());
                    expenseItem.setRecorderName(recorder.getNickName());

                    expenseItem.setAmountInCents(tuple.getValue3().longValue());
                    expenseItem.setDesc(tuple.getValue4());

                    //Attachments
                    String[] fileHashes = tuple.getValue5().split(":");
                    String[] fileNames = tuple.getValue6().split(":");
                    Map<String, String> attachments = new HashMap<>(fileHashes.length);
                    for (int i = 0; i < fileHashes.length; ++i) {
                        if (!fileHashes[i].isEmpty()) {
                            attachments.put(fileHashes[i], fileNames[i]);
                        }
                    }
                    expenseItem.setAttachments(attachments);

                    expenseItem.setTimestamp(tuple.getValue7().longValue());

                    System.out.println("【getExpenseItem】重试次数：" + retryTimes);
                    return expenseItem;
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

    @CacheEvict(value = "expenseItemCount", allEntries = true)
    public void addExpenseItem(ExpenseItem expenseItem, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            StringBuilder fileHashes = new StringBuilder();
            StringBuilder fileNames = new StringBuilder();
            if (expenseItem.getAttachments() != null && !expenseItem.getAttachments().isEmpty()) {
                for (String fileHash : expenseItem.getAttachments().keySet()) {
                    fileHashes.append(fileHash).append(":");
                    fileNames.append(expenseItem.getAttachments().get(fileHash)).append(":");
                }
            }
            try {
                contract.addExpenseItem(expenseItem.getPayee(),
                        BigInteger.valueOf(expenseItem.getAmountInCents()),
                        expenseItem.getDesc(),
                        fileHashes.toString(),
                        fileNames.toString(),
                        BigInteger.valueOf(System.currentTimeMillis())
                ).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Cacheable(value = "incomeItemCount", key = "")
    public int getIncomeItemCount(Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger count = contract.getIncomeItemCount().send();
                    System.out.println("【getIncomeItemCount】重试次数：" + retryTimes);
                    return count.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            System.out.println("【getIncomeItemCount】未找到");
        }
        return -1;
    }

    @Cacheable(value = "incomeItem", key = "#idx", unless = "#result == null")
    public IncomeItem getIncomeItem(long idx, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    Tuple7<String, String, BigInteger, String, String, String, BigInteger> tuple = contract.getIncomeItem(BigInteger.valueOf(idx)).send();
                    if (tuple.getValue7().longValue() == 0) {
                        return null;
                    }

                    IncomeItem incomeItem = new IncomeItem();
                    incomeItem.setIdx(idx);
                    //Payee
                    incomeItem.setPayer(tuple.getValue1());
                    if (incomeItem.getPayer().startsWith("0x")) {
                        User payer = getUser(credentials, incomeItem.getPayer());
                        if (payer != null) {
                            incomeItem.setPayerName(payer.getNickName());
                        }
                    }
                    //Recorder
                    incomeItem.setRecorderHash(tuple.getValue2());
                    User recorder = getUser(credentials, incomeItem.getRecorderHash());
                    incomeItem.setRecorderName(recorder.getNickName());

                    incomeItem.setAmountInCents(tuple.getValue3().longValue());
                    incomeItem.setDesc(tuple.getValue4());

                    //Attachments
                    String[] fileHashes = tuple.getValue5().split(":");
                    String[] fileNames = tuple.getValue6().split(":");
                    Map<String, String> attachments = new HashMap<>(fileHashes.length);
                    for (int i = 0; i < fileHashes.length; ++i) {
                        if (!fileHashes[i].isEmpty()) {
                            attachments.put(fileHashes[i], fileNames[i]);
                        }
                    }
                    incomeItem.setAttachments(attachments);

                    incomeItem.setTimestamp(tuple.getValue7().longValue());

                    System.out.println("【getIncomeItem】重试次数：" + retryTimes);
                    return incomeItem;
                } catch (IndexOutOfBoundsException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            System.out.println("【getIncomeItem】未找到");
        }
        return null;
    }

    @CacheEvict(value = "incomeItemCount", allEntries = true)
    public void addIncomeItem(IncomeItem incomeItem, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            StringBuilder fileHashes = new StringBuilder();
            StringBuilder fileNames = new StringBuilder();
            if (incomeItem.getAttachments() != null && !incomeItem.getAttachments().isEmpty()) {
                for (String fileHash : incomeItem.getAttachments().keySet()) {
                    fileHashes.append(fileHash).append(":");
                    fileNames.append(incomeItem.getAttachments().get(fileHash)).append(":");
                }
            }
            try {
                contract.addIncomeItem(incomeItem.getPayer(),
                        BigInteger.valueOf(incomeItem.getAmountInCents()),
                        incomeItem.getDesc(),
                        fileHashes.toString(),
                        fileNames.toString(),
                        BigInteger.valueOf(System.currentTimeMillis())
                ).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Cacheable(value = "motionCount", key = "")
    public int getMotionCount(Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger count = contract.getMotionCount().send();
                    System.out.println("【getMotionCount】重试次数：" + retryTimes);
                    return count.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            System.out.println("【getMotionCount】未找到");
        }
        return -1;
    }

    @Cacheable(value = "motion", key = "#motionIdx", unless = "#result == null")
    public Motion getMotion(long motionIdx, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    Tuple8<String, String, String, String, String, BigInteger, Boolean, String> tuple = contract.getMotion(BigInteger.valueOf(motionIdx)).send();
                    if (tuple.getValue6().longValue() == 0) {
                        return null;
                    }
                    Motion motion = new Motion();
                    motion.setIdx(motionIdx);

                    motion.setTitle(tuple.getValue1());
                    motion.setContent(tuple.getValue2());

                    //Attachments
                    String[] fileHashes = tuple.getValue3().split(":");
                    String[] fileNames = tuple.getValue4().split(":");
                    Map<String, String> attachments = new HashMap<>(fileHashes.length);
                    for (int i = 0; i < fileHashes.length; ++i) {
                        if (!fileHashes[i].isEmpty()) {
                            attachments.put(fileHashes[i], fileNames[i]);
                        }
                    }
                    motion.setAttachments(attachments);

                    motion.setAuthorHash(tuple.getValue5());
                    User author = getUser(credentials, motion.getAuthorHash());
                    motion.setAuthorName(author.getNickName());

                    motion.setTimestamp(tuple.getValue6().longValue());
                    motion.setMultipleVote(tuple.getValue7());

                    motion.setOptionsJson(tuple.getValue8());
                    String[] options = objectMapper.readValue(motion.getOptionsJson(), new TypeReference<String[]>() {
                    });
                    motion.setOptions(options);

                    int[] votes = new int[options.length];
                    for (int i = 0; i < votes.length; ++i) {
                        votes[i] = getTotalVote(motionIdx, i, credentials);
                    }
                    motion.setTotalVotes(votes);

                    System.out.println("【getMotion】重试次数：" + retryTimes);
                    return motion;
                } catch (IndexOutOfBoundsException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            System.out.println("【getMotion】未找到");
        }
        return null;
    }

    @Cacheable(value = "totalVote", key = "T(String).valueOf(#motionIdx).concat('-').concat(#voteIdx)")
    public int getTotalVote(long motionIdx, long voteIdx, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger vote = contract.getTotalVote(BigInteger.valueOf(motionIdx), BigInteger.valueOf(voteIdx)).send();
                    System.out.println("【getVote】重试次数：" + retryTimes);
                    return vote.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
            System.out.println("【getVote】未找到");
        }
        return 0;
    }

    @Cacheable(value = "voteCount", key = "T(String).valueOf(#motionIdx).concat('-').concat(#credentials.address)")
    public int getVoteCount(long motionIdx, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger voteCount = contract.getVoteCount(BigInteger.valueOf(motionIdx)).send();
                    System.out.println("【getVoteCount】重试次数：" + retryTimes);
                    return voteCount.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
            System.out.println("【getVoteCount】未找到");
        }
        return 0;
    }

    @Cacheable(value = "voteIndex", key = "T(String).valueOf(#motionIdx).concat('-').concat(#idx).concat('-').concat(#credentials.address)")
    public int getVoteIndex(long motionIdx, long idx, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger voteIndex = contract.getVoteIndex(BigInteger.valueOf(motionIdx), BigInteger.valueOf(idx)).send();
                    System.out.println("【getVoteIndex】重试次数：" + retryTimes);
                    return voteIndex.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
            System.out.println("【getVoteIndex】未找到");
        }
        return 0;
    }

    @CacheEvict(value = "motionCount", allEntries = true)
    public void addMotion(Motion motion, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            StringBuilder fileHashes = new StringBuilder();
            StringBuilder fileNames = new StringBuilder();
            if (motion.getAttachments() != null && !motion.getAttachments().isEmpty()) {
                for (String fileHash : motion.getAttachments().keySet()) {
                    fileHashes.append(fileHash).append(":");
                    fileNames.append(motion.getAttachments().get(fileHash)).append(":");
                }
            }
            try {
                contract.addMotion(motion.getTitle(),
                        motion.getContent(),
                        fileHashes.toString(),
                        fileNames.toString(),
                        BigInteger.valueOf(System.currentTimeMillis()),
                        motion.isMultipleVote(),
                        motion.getOptionsJson(),
                        BigInteger.valueOf(motion.getOptions().length)
                ).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Caching(evict = {
            @CacheEvict(value = "totalVote", key = "T(String).valueOf(#motionIdx).concat('-').concat(#voteIdx)"),
            @CacheEvict(value = "hasVoted", key = "T(String).valueOf(#motionIdx).concat('-').concat(#credentials.address)"),
            @CacheEvict(value = "motion", key = "#motionIdx"),
            @CacheEvict(value = "voteCount", key = "T(String).valueOf(#motionIdx).concat('-').concat(#credentials.address)")
    })
    public void voteOption(long motionIdx, long voteIdx, boolean finished, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            try {
                contract.voteOption(
                        BigInteger.valueOf(motionIdx),
                        BigInteger.valueOf(voteIdx),
                        finished
                ).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Cacheable(value = "hasVoted", key = "T(String).valueOf(#motionIdx).concat('-').concat(#credentials.address)")
    public boolean hasVoted(long motionIdx, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    Boolean hasVote = contract.hasVoted(BigInteger.valueOf(motionIdx)).send();
                    System.out.println("【hasVoteFinished】重试次数：" + retryTimes);
                    return hasVote;
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            System.out.println("【hasVoteFinished】未找到");
        }
        return false;
    }

    public void addPropertyActiveCode(String encryptedPhone, int code, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            try {
                contract.addPropertyRegistryCode(encryptedPhone, BigInteger.valueOf(code)).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addProprietorActiveCode(String encryptedPhone, int code, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            try {
                contract.addProprietorRegistryCode(encryptedPhone, BigInteger.valueOf(code)).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean hasProprietorRegistryCode(String encryptedPhone, Credentials credentials){
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    Boolean hasCode = contract.hasProprietorRegistryCode(encryptedPhone).send();
                    System.out.println("【hasProprietorRegistryCode】重试次数：" + retryTimes);
                    return hasCode;
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            System.out.println("【hasProprietorRegistryCode】未找到");
        }
        return false;
    }

    public boolean hasPropertyRegistryCode(String encryptedPhone, Credentials credentials){
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    Boolean hasCode = contract.hasPropertyRegistryCode(encryptedPhone).send();
                    System.out.println("【hasPropertyRegistryCode】重试次数：" + retryTimes);
                    return hasCode;
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            System.out.println("【hasPropertyRegistryCode】未找到");
        }
        return false;
    }

    @Cacheable(value = "authApplicationCount", key = "")
    public int getAuthApplicationCount(Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger count = contract.getAuthApplicationCount().send();
                    System.out.println("【getAuthApplicationCount】重试次数：" + retryTimes);
                    return count.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            System.out.println("【getAuthApplicationCount】未找到");
        }
        return -1;
    }

    @Cacheable(value = "authApplication", key = "#idx", unless = "#result == null")
    public AuthOperation getAuthApplication(long idx, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    Tuple5<Boolean, BigInteger, String, BigInteger, BigInteger> tuple = contract.getAuthApplication(BigInteger.valueOf(idx)).send();

                    AuthOperation authOperation = new AuthOperation();
                    authOperation.setIdx(idx);
                    authOperation.setProcessing(tuple.getValue1());
                    authOperation.setAuthority(tuple.getValue2().intValue());
                    authOperation.setTargetAddr(tuple.getValue3());
                    authOperation.setApprovalCount(tuple.getValue4().intValue());
                    authOperation.setDisapprovalCount(tuple.getValue5().intValue());

                    System.out.println("【getAuthApplication】重试次数：" + retryTimes);
                    return authOperation;
                } catch (IndexOutOfBoundsException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            System.out.println("【getAuthApplication】未找到");
        }
        return null;
    }

    @Cacheable(value = "authApplicationVote", key = "T(String).valueOf(#idx).concat('-').concat(#credentials.address)", unless = "#result == -1")
    public int getAuthApplicationVote(long idx, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger vote = contract.getAuthApplicationVote(BigInteger.valueOf(idx)).send();
                    System.out.println("【getAuthApplicationVote】重试次数：" + retryTimes);
                    return vote.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            System.out.println("【getAuthApplicationVote】未找到");
        }
        return -1;
    }

    @CacheEvict(value = "authApplicationCount", allEntries = true)
    public void addAuthApplication(int authority, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            try {
                contract.addAuthApplication(
                        BigInteger.valueOf(authority)
                ).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Caching(evict = {
            @CacheEvict(value = "user", key = "#targetAddress"),
            @CacheEvict(value = "user", key = "#encryptedPhone"),
            @CacheEvict(value = "userByIndex", allEntries = true),
            @CacheEvict(value = "authApplicationVote", key = "T(String).valueOf(#idx).concat('-').concat(#credentials.address)"),
            @CacheEvict(value = "authApplication", key = "#idx")
    })
    public void agreeAuthApplication(long idx, String targetAddress, String encryptedPhone, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            if (contract != null) {
                try {
                    contract.agreeAuthApplication(
                            BigInteger.valueOf(idx)
                    ).send();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Caching(evict = {
            @CacheEvict(value = "user", key = "#targetAddress"),
            @CacheEvict(value = "user", key = "#encryptedPhone"),
            @CacheEvict(value = "userByIndex", allEntries = true),
            @CacheEvict(value = "authApplicationVote", key = "T(String).valueOf(#idx).concat('-').concat(#credentials.address)"),
            @CacheEvict(value = "authApplication", key = "#idx")
    })
    public void disagreeAuthApplication(long idx, String targetAddress, String encryptedPhone, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            if (contract != null) {
                try {
                    contract.disagreeAuthApplication(
                            BigInteger.valueOf(idx)
                    ).send();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Cacheable(value = "authCancellationCount", key = "")
    public int getAuthCancellationCount(Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger count = contract.getAuthCancellationCount().send();
                    System.out.println("【getAuthCancellationCount】重试次数：" + retryTimes);
                    return count.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            System.out.println("【getAuthCancellationCount】未找到");
        }
        return -1;
    }

    @Cacheable(value = "authCancellation", key = "#idx", unless = "#result == null")
    public AuthOperation getAuthCancellation(long idx, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    Tuple5<Boolean, BigInteger, String, BigInteger, BigInteger> tuple = contract.getAuthCancellation(BigInteger.valueOf(idx)).send();

                    AuthOperation authOperation = new AuthOperation();
                    authOperation.setIdx(idx);
                    authOperation.setProcessing(tuple.getValue1());
                    authOperation.setAuthority(tuple.getValue2().intValue());
                    authOperation.setTargetAddr(tuple.getValue3());
                    authOperation.setApprovalCount(tuple.getValue4().intValue());
                    authOperation.setDisapprovalCount(tuple.getValue5().intValue());

                    System.out.println("【getAuthCancellation】重试次数：" + retryTimes);
                    return authOperation;
                } catch (IndexOutOfBoundsException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            System.out.println("【getAuthCancellation】未找到");
        }
        return null;
    }

    @Cacheable(value = "authCancellationVote", key = "T(String).valueOf(#idx).concat('-').concat(#credentials.address)", unless = "#result == -1")
    public int getAuthCancellationVote(long idx, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger vote = contract.getAuthCancellationVote(BigInteger.valueOf(idx)).send();
                    System.out.println("【getAuthCancellationVote】重试次数：" + retryTimes);
                    return vote.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            System.out.println("【getAuthCancellationVote】未找到");
        }
        return -1;
    }

    @CacheEvict(value = "authCancellationCount", allEntries = true)
    public void addAuthCancellation(int authority, String targetAddress, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            try {
                contract.addAuthCancellation(
                        BigInteger.valueOf(authority),
                        targetAddress
                ).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Caching(evict = {
            @CacheEvict(value = "user", key = "#targetAddress"),
            @CacheEvict(value = "user", key = "#encryptedPhone"),
            @CacheEvict(value = "userByIndex", allEntries = true),
            @CacheEvict(value = "authCancellationVote", key = "T(String).valueOf(#idx).concat('-').concat(#credentials.address)"),
            @CacheEvict(value = "authCancellation", key = "#idx")
    })
    public void agreeAuthCancellation(long idx, String targetAddress, String encryptedPhone, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            if (contract != null) {
                try {
                    contract.agreeAuthCancellation(
                            BigInteger.valueOf(idx)
                    ).send();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Caching(evict = {
            @CacheEvict(value = "user", key = "#targetAddress"),
            @CacheEvict(value = "user", key = "#encryptedPhone"),
            @CacheEvict(value = "userByIndex", allEntries = true),
            @CacheEvict(value = "authCancellationVote", key = "T(String).valueOf(#idx).concat('-').concat(#credentials.address)"),
            @CacheEvict(value = "authCancellation", key = "#idx")
    })
    public void disagreeAuthCancellation(long idx, String targetAddress, String encryptedPhone, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            if (contract != null) {
                try {
                    contract.disagreeAuthCancellation(
                            BigInteger.valueOf(idx)
                    ).send();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Cacheable(value = "settledRatio", key = "")
    public int getSettledRatio(Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger settledRatio = contract.getSettledRatio().send();
                    System.out.println("【getSettledRatio】重试次数：" + retryTimes);
                    return settledRatio.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            System.out.println("【getSettledRatio】未找到");
        }
        return -1;
    }

    @CacheEvict(value = "settledRatio", allEntries = true)
    public void setSettledRatio(int settledRatio, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            try {
                contract.setSettledRatio(
                        BigInteger.valueOf(settledRatio)
                ).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Cacheable(value = "settledMinCount", key = "")
    public int getSettledMinCount(Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            int retryTimes = 0;
            while (retryTimes < maxRetryTimes) {
                try {
                    BigInteger settledMinCount = contract.getSettledMinCount().send();
                    System.out.println("【getSettledMinCount】重试次数：" + retryTimes);
                    return settledMinCount.intValue();
                } catch (ContractCallException e) {
                    ++retryTimes;
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
            }
            System.out.println("【getSettledMinCount】未找到");
        }
        return -1;
    }

    @CacheEvict(value = "settledMinCount", allEntries = true)
    public void setSettledMinCount(int settledMinCount, Credentials credentials) {
        PropertyContract contract = getPropertyContract(credentials);
        if (contract != null) {
            try {
                contract.setSettledMinCount(
                        BigInteger.valueOf(settledMinCount)
                ).send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private PropertyContract getPropertyContract(Credentials credentials) {
        return PropertyContract.load(contractAddress, web3j, credentials, gasProvider);
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
//                    Tuple4<String, String, String, BigInteger> tuple = contract.getUserByAddr(credentials.getAddress()).send();
//                    User user = new User(tuple.getValue1(), tuple.getValue2(), tuple.getValue3(), tuple.getValue4().intValue());
//                    user.setCredentials(credentials);
//                    System.out.println("【getUserByAddr】重试次数：" + retryTimes);
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

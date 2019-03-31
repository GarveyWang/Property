package com.garvey.property.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.2.0.
 */
public class PropertyContract extends Contract {
    private static final String BINARY = "60806040523480156200001157600080fd5b506040516200107538038062001075833981016040908152815160208301519183015190830192918201910162000056838383610fff6401000000006200005f810204565b50505062000209565b6200006962000137565b5060408051608081018252858152602080820186905281830185905261ffff84166060830152600160a060020a0333166000908152808252929092208151805192938493620000bc928492019062000164565b506020828101518051620000d7926001850192019062000164565b5060408201518051620000f591600284019160209091019062000164565b50606091909101516003918201805461ffff191661ffff909216919091179055805463ffffffff19811663ffffffff9182166001019091161790555050505050565b608060405190810160405280606081526020016060815260200160608152602001600061ffff1681525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620001a757805160ff1916838001178555620001d7565b82800160010185558215620001d7579182015b82811115620001d7578251825591602001919060010190620001ba565b50620001e5929150620001e9565b5090565b6200020691905b80821115620001e55760008155600101620001f0565b90565b610e5c80620002196000396000f3006080604052600436106100ad5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416625843b481146100b257806311174a291461014857806336a41f4b146101765780634a12fc2f146102695780635fabcaaf146102c95780636f77926b1461030157806389040e5914610470578063eace61b214610491578063ead0327d14610570578063f18f8fa814610470578063f420471414610591575b600080fd5b3480156100be57600080fd5b506100d3600160a060020a03600435166105f1565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561010d5781810151838201526020016100f5565b50505050905090810190601f16801561013a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561015457600080fd5b5061015d6106ba565b6040805163ffffffff9092168252519081900360200190f35b34801561018257600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261025595833562ffffff1695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506106c79650505050505050565b604080519115158252519081900360200190f35b34801561027557600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526102559436949293602493928401919081908401838280828437509497505050923562ffffff1693506107cd92505050565b3480156102d557600080fd5b506102ea600160a060020a036004351661087f565b6040805161ffff9092168252519081900360200190f35b34801561030d57600080fd5b50610322600160a060020a03600435166108a1565b6040805161ffff83166060820152608080825286519082015285519091829160208084019284019160a08501918a019080838360005b83811015610370578181015183820152602001610358565b50505050905090810190601f16801561039d5780820380516001836020036101000a031916815260200191505b50848103835287518152875160209182019189019080838360005b838110156103d05781810151838201526020016103b8565b50505050905090810190601f1680156103fd5780820380516001836020036101000a031916815260200191505b50848103825286518152865160209182019188019080838360005b83811015610430578181015183820152602001610418565b50505050905090810190601f16801561045d5780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390f35b34801561047c57600080fd5b506100d3600160a060020a0360043516610a92565b34801561049d57600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261025595833562ffffff1695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750949750610b049650505050505050565b34801561057c57600080fd5b506100d3600160a060020a0360043516610bca565b34801561059d57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526102559436949293602493928401919081908401838280828437509497505050923562ffffff169350610c4292505050565b606060008083600160a060020a0316600160a060020a031681526020019081526020016000206001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106ae5780601f10610683576101008083540402835291602001916106ae565b820191906000526020600020905b81548152906001019060200180831161069157829003601f168201915b50505050509050919050565b60035463ffffffff165b90565b600062ffffff85161580159061074957508462ffffff166001846040518082805190602001908083835b602083106107105780518252601f1990920191602091820191016106f1565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092205462ffffff16929092149150505b156107c55761075b8484846027610c9b565b6001836040518082805190602001908083835b6020831061078d5780518252601f19909201916020918201910161076e565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220805462ffffff1916905550505b949350505050565b33600160a060020a03166000908152602081905260408120600301546101ff1681101561087557816002846040518082805190602001908083835b602083106108275780518252601f199092019160209182019101610808565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220805462ffffff191662ffffff949094169390931790925550600191506108799050565b5060005b92915050565b600160a060020a031660009081526020819052604090206003015461ffff1690565b600160a060020a038116600090815260208181526040808320600381015481548351601f600260001960018581161561010002919091019094168190049182018890048802830188019096528082526060978897889791969586958601949286019361ffff909116929186919083018282801561095f5780601f106109345761010080835404028352916020019161095f565b820191906000526020600020905b81548152906001019060200180831161094257829003601f168201915b5050865460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959950889450925084019050828280156109ed5780601f106109c2576101008083540402835291602001916109ed565b820191906000526020600020905b8154815290600101906020018083116109d057829003601f168201915b5050855460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815295985087945092508401905082828015610a7b5780601f10610a5057610100808354040283529160200191610a7b565b820191906000526020600020905b815481529060010190602001808311610a5e57829003601f168201915b505050505091509450945094509450509193509193565b600160a060020a0381166000908152602081815260409182902080548351601f60026000196101006001861615020190931692909204918201849004840281018401909452808452606093928301828280156106ae5780601f10610683576101008083540402835291602001916106ae565b600062ffffff851615801590610b8657508462ffffff166002846040518082805190602001908083835b60208310610b4d5780518252601f199092019160209182019101610b2e565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092205462ffffff16929092149150505b156107c557610b998484846103ff610c9b565b6002836040518082805190602001908083836020831061078d5780518252601f19909201916020918201910161076e565b600160a060020a038116600090815260208181526040918290206002908101805484516001821615610100026000190190911692909204601f810184900484028301840190945283825260609391929091908301828280156106ae5780601f10610683576101008083540402835291602001916106ae565b33600160a060020a03166000908152602081905260408120600301546101ff168110156108755781600184604051808280519060200190808383602083106108275780518252601f199092019160209182019101610808565b610ca3610d6b565b5060408051608081018252858152602080820186905281830185905261ffff84166060830152600160a060020a0333166000908152808252929092208151805192938493610cf49284920190610d98565b506020828101518051610d0d9260018501920190610d98565b5060408201518051610d29916002840191602090910190610d98565b50606091909101516003918201805461ffff191661ffff909216919091179055805463ffffffff19811663ffffffff9182166001019091161790555050505050565b608060405190810160405280606081526020016060815260200160608152602001600061ffff1681525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610dd957805160ff1916838001178555610e06565b82800160010185558215610e06579182015b82811115610e06578251825591602001919060010190610deb565b50610e12929150610e16565b5090565b6106c491905b80821115610e125760008155600101610e1c5600a165627a7a723058201b205f38f4109b7005e19cbc5da21de14087f8652b7951b620518ebab43225600029";

    public static final String FUNC_GETENCRYPTEDPHONE = "getEncryptedPhone";

    public static final String FUNC_GETVOTERCOUNT = "getVoterCount";

    public static final String FUNC_REGISTERPROPERIETOR = "registerProperietor";

    public static final String FUNC_ADDPROPERTYREGISTRYCODEMAP = "addPropertyRegistryCodeMap";

    public static final String FUNC_GETAUTHORITY = "getAuthority";

    public static final String FUNC_GETUSER = "getUser";

    public static final String FUNC_GETENCRYPTEDPWD = "getEncryptedPwd";

    public static final String FUNC_REGISTERPROPERTY = "registerProperty";

    public static final String FUNC_GETNICKNAME = "getNickName";

    public static final String FUNC_MYTEST = "myTest";

    public static final String FUNC_ADDPROPERIETORREGISTRYCODE = "addProperietorRegistryCode";

    @Deprecated
    protected PropertyContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PropertyContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PropertyContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PropertyContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> getEncryptedPhone(String _addr) {
        final Function function = new Function(FUNC_GETENCRYPTEDPHONE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getVoterCount() {
        final Function function = new Function(FUNC_GETVOTERCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> registerProperietor(BigInteger _code, String _encryptedPwd, String _encryptedPhone, String _nickName) {
        final Function function = new Function(
                FUNC_REGISTERPROPERIETOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint24(_code), 
                new org.web3j.abi.datatypes.Utf8String(_encryptedPwd), 
                new org.web3j.abi.datatypes.Utf8String(_encryptedPhone), 
                new org.web3j.abi.datatypes.Utf8String(_nickName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addPropertyRegistryCodeMap(String _encryptedPhone, BigInteger code) {
        final Function function = new Function(
                FUNC_ADDPROPERTYREGISTRYCODEMAP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_encryptedPhone), 
                new org.web3j.abi.datatypes.generated.Uint24(code)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getAuthority(String _addr) {
        final Function function = new Function(FUNC_GETAUTHORITY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple4<String, String, String, BigInteger>> getUser(String _addr) {
        final Function function = new Function(FUNC_GETUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint16>() {}));
        return new RemoteCall<Tuple4<String, String, String, BigInteger>>(
                new Callable<Tuple4<String, String, String, BigInteger>>() {
                    @Override
                    public Tuple4<String, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<String> getEncryptedPwd(String _addr) {
        final Function function = new Function(FUNC_GETENCRYPTEDPWD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> registerProperty(BigInteger _code, String _encryptedPwd, String _encryptedPhone, String _nickName) {
        final Function function = new Function(
                FUNC_REGISTERPROPERTY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint24(_code), 
                new org.web3j.abi.datatypes.Utf8String(_encryptedPwd), 
                new org.web3j.abi.datatypes.Utf8String(_encryptedPhone), 
                new org.web3j.abi.datatypes.Utf8String(_nickName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getNickName(String _addr) {
        final Function function = new Function(FUNC_GETNICKNAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> myTest(String _addr) {
        final Function function = new Function(FUNC_MYTEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> addProperietorRegistryCode(String _encryptedPhone, BigInteger code) {
        final Function function = new Function(
                FUNC_ADDPROPERIETORREGISTRYCODE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_encryptedPhone), 
                new org.web3j.abi.datatypes.generated.Uint24(code)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static PropertyContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new PropertyContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PropertyContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PropertyContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PropertyContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new PropertyContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PropertyContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PropertyContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PropertyContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _encryptedPwd, String _encryptedPhone, String _nickName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_encryptedPwd), 
                new org.web3j.abi.datatypes.Utf8String(_encryptedPhone), 
                new org.web3j.abi.datatypes.Utf8String(_nickName)));
        return deployRemoteCall(PropertyContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<PropertyContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _encryptedPwd, String _encryptedPhone, String _nickName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_encryptedPwd), 
                new org.web3j.abi.datatypes.Utf8String(_encryptedPhone), 
                new org.web3j.abi.datatypes.Utf8String(_nickName)));
        return deployRemoteCall(PropertyContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PropertyContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _encryptedPwd, String _encryptedPhone, String _nickName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_encryptedPwd), 
                new org.web3j.abi.datatypes.Utf8String(_encryptedPhone), 
                new org.web3j.abi.datatypes.Utf8String(_nickName)));
        return deployRemoteCall(PropertyContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<PropertyContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _encryptedPwd, String _encryptedPhone, String _nickName) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_encryptedPwd), 
                new org.web3j.abi.datatypes.Utf8String(_encryptedPhone), 
                new org.web3j.abi.datatypes.Utf8String(_nickName)));
        return deployRemoteCall(PropertyContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}

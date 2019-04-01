package com.garvey.property.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple7;
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
    private static final String BINARY = "60806040523480156200001157600080fd5b506040516200189b3803806200189b833981016040908152815160208301519183015190830192918201910162000056838383610fff6401000000006200005f810204565b5050506200020b565b6200006962000139565b5060408051608081018252858152602080820186905281830185905261ffff84166060830152600160a060020a0333166000908152808252929092208151805192938493620000bc928492019062000166565b506020828101518051620000d7926001850192019062000166565b5060408201518051620000f591600284019160209091019062000166565b50606091909101516003909101805461ffff191661ffff90921691909117905550506004805463ffffffff19811663ffffffff918216600101909116179055505050565b608060405190810160405280606081526020016060815260200160608152602001600061ffff1681525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620001a957805160ff1916838001178555620001d9565b82800160010185558215620001d9579182015b82811115620001d9578251825591602001919060010190620001bc565b50620001e7929150620001eb565b5090565b6200020891905b80821115620001e75760008155600101620001f2565b90565b611680806200021b6000396000f3006080604052600436106100aa5763ffffffff60e060020a6000350416625843b481146100af5780630c2982931461014557806311174a291461027657806336a41f4b146102a45780634a12fc2f146103975780635fabcaaf146103f75780636f77926b1461042f578063848a19d31461059e57806388864949146105b357806389040e59146107a2578063eace61b2146107c3578063ead0327d146108a2578063f4204714146108c3575b600080fd5b3480156100bb57600080fd5b506100d0600160a060020a0360043516610923565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561010a5781810151838201526020016100f2565b50505050905090810190601f1680156101375780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561015157600080fd5b506040805160206004803580820135601f810184900484028501840190955284845261026494369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497505050923567ffffffffffffffff1693506109ec92505050565b60408051918252519081900360200190f35b34801561028257600080fd5b5061028b610bcc565b6040805163ffffffff9092168252519081900360200190f35b3480156102b057600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261038395833562ffffff1695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750949750610bd99650505050505050565b604080519115158252519081900360200190f35b3480156103a357600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526103839436949293602493928401919081908401838280828437509497505050923562ffffff169350610cdf92505050565b34801561040357600080fd5b50610418600160a060020a0360043516610d91565b6040805161ffff9092168252519081900360200190f35b34801561043b57600080fd5b50610450600160a060020a0360043516610db3565b6040805161ffff83166060820152608080825286519082015285519091829160208084019284019160a08501918a019080838360005b8381101561049e578181015183820152602001610486565b50505050905090810190601f1680156104cb5780820380516001836020036101000a031916815260200191505b50848103835287518152875160209182019189019080838360005b838110156104fe5781810151838201526020016104e6565b50505050905090810190601f16801561052b5780820380516001836020036101000a031916815260200191505b50848103825286518152865160209182019188019080838360005b8381101561055e578181015183820152602001610546565b50505050905090810190601f16801561058b5780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390f35b3480156105aa57600080fd5b50610264610fa4565b3480156105bf57600080fd5b506105cb600435610faa565b60408051600160a060020a038516608082015267ffffffffffffffff841660a082015260ff831660c082015260e08082528951908201528851909182916020808401928401916060850191610100860191908e019080838360005b8381101561063e578181015183820152602001610626565b50505050905090810190601f16801561066b5780820380516001836020036101000a031916815260200191505b5085810384528b5181528b516020918201918d019080838360005b8381101561069e578181015183820152602001610686565b50505050905090810190601f1680156106cb5780820380516001836020036101000a031916815260200191505b5085810383528a5181528a516020918201918c019080838360005b838110156106fe5781810151838201526020016106e6565b50505050905090810190601f16801561072b5780820380516001836020036101000a031916815260200191505b5085810382528951815289516020918201918b019080838360005b8381101561075e578181015183820152602001610746565b50505050905090810190601f16801561078b5780820380516001836020036101000a031916815260200191505b509b50505050505050505050505060405180910390f35b3480156107ae57600080fd5b506100d0600160a060020a0360043516611279565b3480156107cf57600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261038395833562ffffff1695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506112eb9650505050505050565b3480156108ae57600080fd5b506100d0600160a060020a03600435166113b1565b3480156108cf57600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526103839436949293602493928401919081908401838280828437509497505050923562ffffff16935061142992505050565b606060008083600160a060020a0316600160a060020a031681526020019081526020016000206001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109e05780601f106109b5576101008083540402835291602001916109e0565b820191906000526020600020905b8154815290600101906020018083116109c357829003601f168201915b50505050509050919050565b60006109f6611554565b33600160a060020a0316600090815260208190526040812060030154607f161115610bc257506040805160e081018252878152602080820188905291810186905260608101859052600160a060020a033316608082015267ffffffffffffffff841660a0820152600160c0820181905260038054918201808255600091909152825180519394919385936005027fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b0192610ab492849291019061158f565b506020828101518051610acd926001850192019061158f565b5060408201518051610ae991600284019160209091019061158f565b5060608201518051610b0591600384019160209091019061158f565b5060808201516004909101805460a084015160c09094015160ff1660e060020a027fffffff00ffffffffffffffffffffffffffffffffffffffffffffffffffffffff67ffffffffffffffff90951674010000000000000000000000000000000000000000027fffffffff0000000000000000ffffffffffffffffffffffffffffffffffffffff600160a060020a0390951673ffffffffffffffffffffffffffffffffffffffff199093169290921793909316179290921617905591505b5095945050505050565b60045463ffffffff165b90565b600062ffffff851615801590610c5b57508462ffffff166001846040518082805190602001908083835b60208310610c225780518252601f199092019160209182019101610c03565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092205462ffffff16929092149150505b15610cd757610c6d8484846027611482565b6001836040518082805190602001908083835b60208310610c9f5780518252601f199092019160209182019101610c80565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220805462ffffff1916905550505b949350505050565b33600160a060020a03166000908152602081905260408120600301546101ff16811015610d8757816002846040518082805190602001908083835b60208310610d395780518252601f199092019160209182019101610d1a565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220805462ffffff191662ffffff94909416939093179092555060019150610d8b9050565b5060005b92915050565b600160a060020a031660009081526020819052604090206003015461ffff1690565b600160a060020a038116600090815260208181526040808320600381015481548351601f600260001960018581161561010002919091019094168190049182018890048802830188019096528082526060978897889791969586958601949286019361ffff9091169291869190830182828015610e715780601f10610e4657610100808354040283529160200191610e71565b820191906000526020600020905b815481529060010190602001808311610e5457829003601f168201915b5050865460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815295995088945092508401905082828015610eff5780601f10610ed457610100808354040283529160200191610eff565b820191906000526020600020905b815481529060010190602001808311610ee257829003601f168201915b5050855460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815295985087945092508401905082828015610f8d5780601f10610f6257610100808354040283529160200191610f8d565b820191906000526020600020905b815481529060010190602001808311610f7057829003601f168201915b505050505091509450945094509450509193509193565b60035490565b606080606080600080600080600389815481101515610fc557fe5b600091825260209182902060059190910201600481015481546040805160026101006001808616159190910260001901909416819004601f81018890048802830188019093528282529496508695928601948601936003870193600160a060020a0382169374010000000000000000000000000000000000000000830467ffffffffffffffff169360e060020a90930460ff1692918991908301828280156110ae5780601f10611083576101008083540402835291602001916110ae565b820191906000526020600020905b81548152906001019060200180831161109157829003601f168201915b5050895460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959c508b94509250840190508282801561113c5780601f106111115761010080835404028352916020019161113c565b820191906000526020600020905b81548152906001019060200180831161111f57829003601f168201915b5050885460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959b508a9450925084019050828280156111ca5780601f1061119f576101008083540402835291602001916111ca565b820191906000526020600020905b8154815290600101906020018083116111ad57829003601f168201915b5050875460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959a50899450925084019050828280156112585780601f1061122d57610100808354040283529160200191611258565b820191906000526020600020905b81548152906001019060200180831161123b57829003601f168201915b50505050509350975097509750975097509750975050919395979092949650565b600160a060020a0381166000908152602081815260409182902080548351601f60026000196101006001861615020190931692909204918201849004840281018401909452808452606093928301828280156109e05780601f106109b5576101008083540402835291602001916109e0565b600062ffffff85161580159061136d57508462ffffff166002846040518082805190602001908083835b602083106113345780518252601f199092019160209182019101611315565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092205462ffffff16929092149150505b15610cd7576113808484846103ff611482565b60028360405180828051906020019080838360208310610c9f5780518252601f199092019160209182019101610c80565b600160a060020a038116600090815260208181526040918290206002908101805484516001821615610100026000190190911692909204601f810184900484028301840190945283825260609391929091908301828280156109e05780601f106109b5576101008083540402835291602001916109e0565b33600160a060020a03166000908152602081905260408120600301546101ff16811015610d87578160018460405180828051906020019080838360208310610d395780518252601f199092019160209182019101610d1a565b61148a61160d565b5060408051608081018252858152602080820186905281830185905261ffff84166060830152600160a060020a03331660009081528082529290922081518051929384936114db928492019061158f565b5060208281015180516114f4926001850192019061158f565b506040820151805161151091600284019160209091019061158f565b50606091909101516003909101805461ffff191661ffff90921691909117905550506004805463ffffffff19811663ffffffff918216600101909116179055505050565b6040805160e0810182526060808252602082018190529181018290528181019190915260006080820181905260a0820181905260c082015290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106115d057805160ff19168380011785556115fd565b828001600101855582156115fd579182015b828111156115fd5782518255916020019190600101906115e2565b5061160992915061163a565b5090565b608060405190810160405280606081526020016060815260200160608152602001600061ffff1681525090565b610bd691905b8082111561160957600081556001016116405600a165627a7a7230582056f93096d7664a75cdd0c6fb328795548194109a74963564c263ba4cf86dda800029";

    public static final String FUNC_GETENCRYPTEDPHONE = "getEncryptedPhone";

    public static final String FUNC_ADDPUBLICITYINFO = "addPublicityInfo";

    public static final String FUNC_GETVOTERCOUNT = "getVoterCount";

    public static final String FUNC_REGISTERPROPERIETOR = "registerProperietor";

    public static final String FUNC_ADDPROPERTYREGISTRYCODEMAP = "addPropertyRegistryCodeMap";

    public static final String FUNC_GETAUTHORITY = "getAuthority";

    public static final String FUNC_GETUSER = "getUser";

    public static final String FUNC_GETPUBLICITYINFOCOUNT = "getPublicityInfoCount";

    public static final String FUNC_GETPUBLICITYINFO = "getPublicityInfo";

    public static final String FUNC_GETENCRYPTEDPWD = "getEncryptedPwd";

    public static final String FUNC_REGISTERPROPERTY = "registerProperty";

    public static final String FUNC_GETNICKNAME = "getNickName";

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

    public RemoteCall<TransactionReceipt> addPublicityInfo(String _title, String _content, String _fileHashes, String _fileNames, BigInteger _timestamp) {
        final Function function = new Function(
                FUNC_ADDPUBLICITYINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_content), 
                new org.web3j.abi.datatypes.Utf8String(_fileHashes), 
                new org.web3j.abi.datatypes.Utf8String(_fileNames), 
                new org.web3j.abi.datatypes.generated.Uint64(_timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteCall<BigInteger> getPublicityInfoCount() {
        final Function function = new Function(FUNC_GETPUBLICITYINFOCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple7<String, String, String, String, String, BigInteger, BigInteger>> getPublicityInfo(BigInteger _idx) {
        final Function function = new Function(FUNC_GETPUBLICITYINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_idx)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint64>() {}, new TypeReference<Uint8>() {}));
        return new RemoteCall<Tuple7<String, String, String, String, String, BigInteger, BigInteger>>(
                new Callable<Tuple7<String, String, String, String, String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple7<String, String, String, String, String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, String, String, String, String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
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

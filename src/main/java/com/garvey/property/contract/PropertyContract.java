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
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple6;
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
    private static final String BINARY = "60806040523480156200001157600080fd5b506040516200186e3803806200186e833981016040908152815160208301519183015190830192918201910162000056838383610fff6401000000006200005f810204565b5050506200020b565b6200006962000139565b5060408051608081018252858152602080820186905281830185905261ffff84166060830152600160a060020a0333166000908152808252929092208151805192938493620000bc928492019062000166565b506020828101518051620000d7926001850192019062000166565b5060408201518051620000f591600284019160209091019062000166565b50606091909101516003909101805461ffff191661ffff90921691909117905550506004805463ffffffff19811663ffffffff918216600101909116179055505050565b608060405190810160405280606081526020016060815260200160608152602001600061ffff1681525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620001a957805160ff1916838001178555620001d9565b82800160010185558215620001d9579182015b82811115620001d9578251825591602001919060010190620001bc565b50620001e7929150620001eb565b5090565b6200020891905b80821115620001e75760008155600101620001f2565b90565b611653806200021b6000396000f3006080604052600436106100c35763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416625843b481146100c85780630c2982931461015e57806311174a291461028f57806336a41f4b146102bd5780634a12fc2f146103b05780635fabcaaf146104105780636f77926b14610448578063848a19d3146105b757806388864949146105cc57806389040e59146107b0578063eace61b2146107d1578063ead0327d146108b0578063f4204714146108d1575b600080fd5b3480156100d457600080fd5b506100e9600160a060020a0360043516610931565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561012357818101518382015260200161010b565b50505050905090810190601f1680156101505780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561016a57600080fd5b506040805160206004803580820135601f810184900484028501840190955284845261027d94369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497505050923567ffffffffffffffff1693506109fa92505050565b60408051918252519081900360200190f35b34801561029b57600080fd5b506102a4610ba1565b6040805163ffffffff9092168252519081900360200190f35b3480156102c957600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261039c95833562ffffff1695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750949750610bae9650505050505050565b604080519115158252519081900360200190f35b3480156103bc57600080fd5b506040805160206004803580820135601f810184900484028501840190955284845261039c9436949293602493928401919081908401838280828437509497505050923562ffffff169350610cb492505050565b34801561041c57600080fd5b50610431600160a060020a0360043516610d66565b6040805161ffff9092168252519081900360200190f35b34801561045457600080fd5b50610469600160a060020a0360043516610d88565b6040805161ffff83166060820152608080825286519082015285519091829160208084019284019160a08501918a019080838360005b838110156104b757818101518382015260200161049f565b50505050905090810190601f1680156104e45780820380516001836020036101000a031916815260200191505b50848103835287518152875160209182019189019080838360005b838110156105175781810151838201526020016104ff565b50505050905090810190601f1680156105445780820380516001836020036101000a031916815260200191505b50848103825286518152865160209182019188019080838360005b8381101561057757818101518382015260200161055f565b50505050905090810190601f1680156105a45780820380516001836020036101000a031916815260200191505b5097505050505050505060405180910390f35b3480156105c357600080fd5b5061027d610f79565b3480156105d857600080fd5b506105e4600435610f7f565b60408051600160a060020a038416608082015267ffffffffffffffff831660a082015260c0808252885190820152875190918291602080840192840191606085019160e0860191908d019080838360005b8381101561064d578181015183820152602001610635565b50505050905090810190601f16801561067a5780820380516001836020036101000a031916815260200191505b5085810384528a5181528a516020918201918c019080838360005b838110156106ad578181015183820152602001610695565b50505050905090810190601f1680156106da5780820380516001836020036101000a031916815260200191505b5085810383528951815289516020918201918b019080838360005b8381101561070d5781810151838201526020016106f5565b50505050905090810190601f16801561073a5780820380516001836020036101000a031916815260200191505b5085810382528851815288516020918201918a019080838360005b8381101561076d578181015183820152602001610755565b50505050905090810190601f16801561079a5780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390f35b3480156107bc57600080fd5b506100e9600160a060020a036004351661123d565b3480156107dd57600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261039c95833562ffffff1695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506112af9650505050505050565b3480156108bc57600080fd5b506100e9600160a060020a0360043516611375565b3480156108dd57600080fd5b506040805160206004803580820135601f810184900484028501840190955284845261039c9436949293602493928401919081908401838280828437509497505050923562ffffff1693506113ed92505050565b606060008083600160a060020a0316600160a060020a031681526020019081526020016000206001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109ee5780601f106109c3576101008083540402835291602001916109ee565b820191906000526020600020905b8154815290600101906020018083116109d157829003601f168201915b50505050509050919050565b6000610a04611518565b600160a060020a0333166000908152602081905260408120600301546080161115610b9757506040805160c081018252878152602080820188905291810186905260608101859052600160a060020a033316608082015267ffffffffffffffff841660a082015260038054600181018083556000929092528251805193949293859360059093027fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b0192610abc928492910190611562565b506020828101518051610ad59260018501920190611562565b5060408201518051610af1916002840191602090910190611562565b5060608201518051610b0d916003840191602090910190611562565b5060808201516004909101805460a09093015167ffffffffffffffff1674010000000000000000000000000000000000000000027fffffffff0000000000000000ffffffffffffffffffffffffffffffffffffffff600160a060020a0390931673ffffffffffffffffffffffffffffffffffffffff19909416939093179190911691909117905591505b5095945050505050565b60045463ffffffff165b90565b600062ffffff851615801590610c3057508462ffffff166001846040518082805190602001908083835b60208310610bf75780518252601f199092019160209182019101610bd8565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092205462ffffff16929092149150505b15610cac57610c428484846027611446565b6001836040518082805190602001908083835b60208310610c745780518252601f199092019160209182019101610c55565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220805462ffffff1916905550505b949350505050565b600160a060020a03331660009081526020819052604081206003015461020016811015610d5c57816002846040518082805190602001908083835b60208310610d0e5780518252601f199092019160209182019101610cef565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220805462ffffff191662ffffff94909416939093179092555060019150610d609050565b5060005b92915050565b600160a060020a031660009081526020819052604090206003015461ffff1690565b600160a060020a038116600090815260208181526040808320600381015481548351601f600260001960018581161561010002919091019094168190049182018890048802830188019096528082526060978897889791969586958601949286019361ffff9091169291869190830182828015610e465780601f10610e1b57610100808354040283529160200191610e46565b820191906000526020600020905b815481529060010190602001808311610e2957829003601f168201915b5050865460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815295995088945092508401905082828015610ed45780601f10610ea957610100808354040283529160200191610ed4565b820191906000526020600020905b815481529060010190602001808311610eb757829003601f168201915b5050855460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815295985087945092508401905082828015610f625780601f10610f3757610100808354040283529160200191610f62565b820191906000526020600020905b815481529060010190602001808311610f4557829003601f168201915b505050505091509450945094509450509193509193565b60035490565b6060806060806000806000600388815481101515610f9957fe5b600091825260209182902060059190910201600481015481546040805160026101006001808616159190910260001901909416819004601f81018890048802830188019093528282529496508695928601948601936003870193600160a060020a038216937401000000000000000000000000000000000000000090920467ffffffffffffffff16929188918301828280156110765780601f1061104b57610100808354040283529160200191611076565b820191906000526020600020905b81548152906001019060200180831161105957829003601f168201915b5050885460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959b508a9450925084019050828280156111045780601f106110d957610100808354040283529160200191611104565b820191906000526020600020905b8154815290600101906020018083116110e757829003601f168201915b5050875460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959a50899450925084019050828280156111925780601f1061116757610100808354040283529160200191611192565b820191906000526020600020905b81548152906001019060200180831161117557829003601f168201915b5050865460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959950889450925084019050828280156112205780601f106111f557610100808354040283529160200191611220565b820191906000526020600020905b81548152906001019060200180831161120357829003601f168201915b505050505092509650965096509650965096505091939550919395565b600160a060020a0381166000908152602081815260409182902080548351601f60026000196101006001861615020190931692909204918201849004840281018401909452808452606093928301828280156109ee5780601f106109c3576101008083540402835291602001916109ee565b600062ffffff85161580159061133157508462ffffff166002846040518082805190602001908083835b602083106112f85780518252601f1990920191602091820191016112d9565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092205462ffffff16929092149150505b15610cac576113448484846103ff611446565b60028360405180828051906020019080838360208310610c745780518252601f199092019160209182019101610c55565b600160a060020a038116600090815260208181526040918290206002908101805484516001821615610100026000190190911692909204601f810184900484028301840190945283825260609391929091908301828280156109ee5780601f106109c3576101008083540402835291602001916109ee565b600160a060020a03331660009081526020819052604081206003015461020016811015610d5c578160018460405180828051906020019080838360208310610d0e5780518252601f199092019160209182019101610cef565b61144e6115e0565b5060408051608081018252858152602080820186905281830185905261ffff84166060830152600160a060020a033316600090815280825292909220815180519293849361149f9284920190611562565b5060208281015180516114b89260018501920190611562565b50604082015180516114d4916002840191602090910190611562565b50606091909101516003909101805461ffff191661ffff90921691909117905550506004805463ffffffff19811663ffffffff918216600101909116179055505050565b60c060405190810160405280606081526020016060815260200160608152602001606081526020016000600160a060020a03168152602001600067ffffffffffffffff1681525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106115a357805160ff19168380011785556115d0565b828001600101855582156115d0579182015b828111156115d05782518255916020019190600101906115b5565b506115dc92915061160d565b5090565b608060405190810160405280606081526020016060815260200160608152602001600061ffff1681525090565b610bab91905b808211156115dc57600081556001016116135600a165627a7a72305820fdc54fcc98ee0a38c695361da6f03ed54dd7597235f80998c31f9714bd056fdc0029";

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

    public RemoteCall<Tuple6<String, String, String, String, String, BigInteger>> getPublicityInfo(BigInteger _idx) {
        final Function function = new Function(FUNC_GETPUBLICITYINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_idx)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint64>() {}));
        return new RemoteCall<Tuple6<String, String, String, String, String, BigInteger>>(
                new Callable<Tuple6<String, String, String, String, String, BigInteger>>() {
                    @Override
                    public Tuple6<String, String, String, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<String, String, String, String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
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

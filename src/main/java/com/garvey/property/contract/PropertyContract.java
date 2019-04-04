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
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple6;
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
    private static final String BINARY = "60806040523480156200001157600080fd5b506040516200257138038062002571833981016040908152815160208301519183015190830192918201910162000056838383610fff6401000000006200005f810204565b50505062000293565b62000069620001c1565b5060408051608081018252858152602080820186905281830185905261ffff84166060830152600160a060020a0333166000908152808252929092208151805192938493620000bc9284920190620001ee565b506020828101518051620000d79260018501920190620001ee565b5060408201518051620000f5916002840191602090910190620001ee565b5060608201518160030160006101000a81548161ffff021916908361ffff160217905550905050336001856040518082805190602001908083835b60208310620001515780518252601f19909201916020918201910162000130565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092208054600160a060020a031916600160a060020a03949094169390931790925550506002805463ffffffff19811663ffffffff9182166001019091161790555050505050565b608060405190810160405280606081526020016060815260200160608152602001600061ffff1681525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200023157805160ff191683800117855562000261565b8280016001018555821562000261579182015b828111156200026157825182559160200191906001019062000244565b506200026f92915062000273565b5090565b6200029091905b808211156200026f57600081556001016200027a565b90565b6122ce80620002a36000396000f3006080604052600436106101105763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416625843b481146101155780630c298293146101ab57806311174a29146102dc57806336a41f4b1461030a5780635fabcaaf146103fd5780635fce88fe1461043557806365ec1a8b146105645780637a81bfac146105c45780637eea39e3146105d9578063848a19d314610708578063888649491461071d57806389040e5914610901578063b086f1b814610922578063b3c37de214610937578063c53eb5b914610b48578063eace61b214610b60578063ead0327d14610c3f578063eada609d14610c60578063f16e4e4614610e25578063f420471414610e46575b600080fd5b34801561012157600080fd5b50610136600160a060020a0360043516610ea6565b6040805160208082528351818301528351919283929083019185019080838360005b83811015610170578181015183820152602001610158565b50505050905090810190601f16801561019d5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156101b757600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526102ca94369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497505050923567ffffffffffffffff169350610f6f92505050565b60408051918252519081900360200190f35b3480156102e857600080fd5b506102f1611110565b6040805163ffffffff9092168252519081900360200190f35b34801561031657600080fd5b5060408051602060046024803582810135601f81018590048502860185019096528585526103e995833562ffffff1695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375094975061111d9650505050505050565b604080519115158252519081900360200190f35b34801561040957600080fd5b5061041e600160a060020a0360043516611223565b6040805161ffff9092168252519081900360200190f35b34801561044157600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526102ca94369492936024939284019190819084018382808284375050604080516020601f818a01358b0180359182018390048302840183018552818452989b67ffffffffffffffff8b35169b909a90999401975091955091820193509150819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497505050923567ffffffffffffffff16935061124592505050565b34801561057057600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526103e99436949293602493928401919081908401838280828437509497505050923562ffffff16935061142092505050565b3480156105d057600080fd5b506102ca6114d2565b3480156105e557600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526102ca94369492936024939284019190819084018382808284375050604080516020601f818a01358b0180359182018390048302840183018552818452989b67ffffffffffffffff8b35169b909a90999401975091955091820193509150819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497505050923567ffffffffffffffff1693506114d892505050565b34801561071457600080fd5b506102ca6115aa565b34801561072957600080fd5b506107356004356115b0565b60408051600160a060020a038416608082015267ffffffffffffffff831660a082015260c0808252885190820152875190918291602080840192840191606085019160e0860191908d019080838360005b8381101561079e578181015183820152602001610786565b50505050905090810190601f1680156107cb5780820380516001836020036101000a031916815260200191505b5085810384528a5181528a516020918201918c019080838360005b838110156107fe5781810151838201526020016107e6565b50505050905090810190601f16801561082b5780820380516001836020036101000a031916815260200191505b5085810383528951815289516020918201918b019080838360005b8381101561085e578181015183820152602001610846565b50505050905090810190601f16801561088b5780820380516001836020036101000a031916815260200191505b5085810382528851815288516020918201918a019080838360005b838110156108be5781810151838201526020016108a6565b50505050905090810190601f1680156108eb5780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390f35b34801561090d57600080fd5b50610136600160a060020a036004351661186e565b34801561092e57600080fd5b506102ca6118e0565b34801561094357600080fd5b5061094f6004356118e6565b604051808060200188600160a060020a0316600160a060020a031681526020018767ffffffffffffffff1667ffffffffffffffff1681526020018060200180602001806020018667ffffffffffffffff1667ffffffffffffffff16815260200185810385528c818151815260200191508051906020019080838360005b838110156109e45781810151838201526020016109cc565b50505050905090810190601f168015610a115780820380516001836020036101000a031916815260200191505b5085810384528951815289516020918201918b019080838360005b83811015610a44578181015183820152602001610a2c565b50505050905090810190601f168015610a715780820380516001836020036101000a031916815260200191505b5085810383528851815288516020918201918a019080838360005b83811015610aa4578181015183820152602001610a8c565b50505050905090810190601f168015610ad15780820380516001836020036101000a031916815260200191505b50858103825287518152875160209182019189019080838360005b83811015610b04578181015183820152602001610aec565b50505050905090810190601f168015610b315780820380516001836020036101000a031916815260200191505b509b50505050505050505050505060405180910390f35b348015610b5457600080fd5b5061094f600435611bb4565b348015610b6c57600080fd5b5060408051602060046024803582810135601f81018590048502860185019096528585526103e995833562ffffff1695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750949750611bd09650505050505050565b348015610c4b57600080fd5b50610136600160a060020a0360043516611c96565b348015610c6c57600080fd5b506040805160206004803580820135601f8101849004840285018401909552848452610cb9943694929360249392840191908190840183828082843750949750611d0e9650505050505050565b6040518086600160a060020a0316600160a060020a031681526020018060200180602001806020018561ffff1661ffff168152602001848103845288818151815260200191508051906020019080838360005b83811015610d24578181015183820152602001610d0c565b50505050905090810190601f168015610d515780820380516001836020036101000a031916815260200191505b50848103835287518152875160209182019189019080838360005b83811015610d84578181015183820152602001610d6c565b50505050905090810190601f168015610db15780820380516001836020036101000a031916815260200191505b50848103825286518152865160209182019188019080838360005b83811015610de4578181015183820152602001610dcc565b50505050905090810190601f168015610e115780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390f35b348015610e3157600080fd5b50610cb9600160a060020a0360043516611da1565b348015610e5257600080fd5b506040805160206004803580820135601f81018490048402850184019095528484526103e99436949293602493928401919081908401838280828437509497505050923562ffffff169350611f9a92505050565b606060008083600160a060020a0316600160a060020a031681526020019081526020016000206001018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610f635780601f10610f3857610100808354040283529160200191610f63565b820191906000526020600020905b815481529060010190602001808311610f4657829003601f168201915b50505050509050919050565b6000610f79612158565b600160a060020a033316600090815260208190526040812060030154608016111561110657506040805160c081018252878152602080820188905291810186905260608101859052600160a060020a033316608082015267ffffffffffffffff841660a08201526005805460018101808355600083905283518051949591948694939093027f036b6384b5eca791c62761152d0c79bb0604c104a5fb6f4eb0703f3154bb3db0019261102e92849201906121a2565b50602082810151805161104792600185019201906121a2565b50604082015180516110639160028401916020909101906121a2565b506060820151805161107f9160038401916020909101906121a2565b5060808201516004909101805460a09093015167ffffffffffffffff1674010000000000000000000000000000000000000000027bffffffffffffffff000000000000000000000000000000000000000019600160a060020a0390931673ffffffffffffffffffffffffffffffffffffffff19909416939093179190911691909117905591505b5095945050505050565b60025463ffffffff165b90565b600062ffffff85161580159061119f57508462ffffff166003846040518082805190602001908083835b602083106111665780518252601f199092019160209182019101611147565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092205462ffffff16929092149150505b1561121b576111b18484846027611ff3565b6003836040518082805190602001908083835b602083106111e35780518252601f1990920191602091820191016111c4565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220805462ffffff1916905550505b949350505050565b600160a060020a031660009081526020819052604090206003015461ffff1690565b600061124f612220565b600160a060020a03331660009081526020819052604081206003015461010016111561141557506040805160e081018252888152600160a060020a03331660208083019190915267ffffffffffffffff808a1693830193909352606082018890526080820187905260a0820186905291841660c08201526006805460018101808355600083905283518051949591948694939093027ff652222313e28459528d920b65115c16c04f3efc82aaedc97be59f3f377c0d3f019261131492849201906121a2565b50602082810151600183018054604086015167ffffffffffffffff1674010000000000000000000000000000000000000000027bffffffffffffffff000000000000000000000000000000000000000019600160a060020a0390941673ffffffffffffffffffffffffffffffffffffffff199092169190911792909216919091179055606083015180516113ae92600285019201906121a2565b50608082015180516113ca9160038401916020909101906121a2565b5060a082015180516113e69160048401916020909101906121a2565b5060c091909101516005909101805467ffffffffffffffff191667ffffffffffffffff90921691909117905591505b509695505050505050565b600160a060020a033316600090815260208190526040812060030154610200168110156114c857816004846040518082805190602001908083835b6020831061147a5780518252601f19909201916020918201910161145b565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220805462ffffff191662ffffff949094169390931790925550600191506114cc9050565b5060005b92915050565b60065490565b60006114e2612220565b600160a060020a03331660009081526020819052604081206003015461010016111561141557506040805160e081018252888152600160a060020a03331660208083019190915267ffffffffffffffff808a1693830193909352606082018890526080820187905260a0820186905291841660c082015260078054600181018083556000929092528251805193949293859360069093027fa66cc928b5edb82af9bd49922954155ab7b0942694bea4ce44661d9a8736c68801926113149284929101906121a2565b60055490565b60608060608060008060006005888154811015156115ca57fe5b600091825260209182902060059190910201600481015481546040805160026101006001808616159190910260001901909416819004601f81018890048802830188019093528282529496508695928601948601936003870193600160a060020a038216937401000000000000000000000000000000000000000090920467ffffffffffffffff16929188918301828280156116a75780601f1061167c576101008083540402835291602001916116a7565b820191906000526020600020905b81548152906001019060200180831161168a57829003601f168201915b5050885460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959b508a9450925084019050828280156117355780601f1061170a57610100808354040283529160200191611735565b820191906000526020600020905b81548152906001019060200180831161171857829003601f168201915b5050875460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959a50899450925084019050828280156117c35780601f10611798576101008083540402835291602001916117c3565b820191906000526020600020905b8154815290600101906020018083116117a657829003601f168201915b5050865460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959950889450925084019050828280156118515780601f1061182657610100808354040283529160200191611851565b820191906000526020600020905b81548152906001019060200180831161183457829003601f168201915b505050505092509650965096509650965096505091939550919395565b600160a060020a0381166000908152602081815260409182902080548351601f6002600019610100600186161502019093169290920491820184900484028101840190945280845260609392830182828015610f635780601f10610f3857610100808354040283529160200191610f63565b60075490565b6060600080606080606060008060068981548110151561190257fe5b60009182526020918290206001600690920201818101546005820154825460408051601f60026000199885161561010002989098019093168790049283018890048802810188019091528181529396508695600160a060020a0384169567ffffffffffffffff7401000000000000000000000000000000000000000090950485169590880194600389019460048a01949116928991908301828280156119e95780601f106119be576101008083540402835291602001916119e9565b820191906000526020600020905b8154815290600101906020018083116119cc57829003601f168201915b5050875460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959c5089945092508401905082828015611a775780601f10611a4c57610100808354040283529160200191611a77565b820191906000526020600020905b815481529060010190602001808311611a5a57829003601f168201915b5050865460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815295995088945092508401905082828015611b055780601f10611ada57610100808354040283529160200191611b05565b820191906000526020600020905b815481529060010190602001808311611ae857829003601f168201915b5050855460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815295985087945092508401905082828015611b935780601f10611b6857610100808354040283529160200191611b93565b820191906000526020600020905b815481529060010190602001808311611b7657829003601f168201915b50505050509150975097509750975097509750975050919395979092949650565b6060600080606080606060008060078981548110151561190257fe5b600062ffffff851615801590611c5257508462ffffff166004846040518082805190602001908083835b60208310611c195780518252601f199092019160209182019101611bfa565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092205462ffffff16929092149150505b1561121b57611c658484846103ff611ff3565b600483604051808280519060200190808383602083106111e35780518252601f1990920191602091820191016111c4565b600160a060020a038116600090815260208181526040918290206002908101805484516001821615610100026000190190911692909204601f81018490048402830184019094528382526060939192909190830182828015610f635780601f10610f3857610100808354040283529160200191610f63565b600060608060606000806001876040518082805190602001908083835b60208310611d4a5780518252601f199092019160209182019101611d2b565b51815160209384036101000a6000190180199092169116179052920194855250604051938490030190922054600160a060020a03169250611d8e9150829050611da1565b939b929a50909850965090945092505050565b600160a060020a038116600090815260208181526040808320600381015481548351601f60026000196001858116156101000291909101909416819004918201889004880283018801909652808252606096879687968a9690958c958795918601949386019361ffff9093169290918691830182828015611e635780601f10611e3857610100808354040283529160200191611e63565b820191906000526020600020905b815481529060010190602001808311611e4657829003601f168201915b5050865460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815295995088945092508401905082828015611ef15780601f10611ec657610100808354040283529160200191611ef1565b820191906000526020600020905b815481529060010190602001808311611ed457829003601f168201915b5050855460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815295985087945092508401905082828015611f7f5780601f10611f5457610100808354040283529160200191611f7f565b820191906000526020600020905b815481529060010190602001808311611f6257829003601f168201915b50505050509150955095509550955095505091939590929450565b600160a060020a033316600090815260208190526040812060030154610200168110156114c857816003846040518082805190602001908083836020831061147a5780518252601f19909201916020918201910161145b565b611ffb61225b565b5060408051608081018252858152602080820186905281830185905261ffff84166060830152600160a060020a033316600090815280825292909220815180519293849361204c92849201906121a2565b50602082810151805161206592600185019201906121a2565b50604082015180516120819160028401916020909101906121a2565b5060608201518160030160006101000a81548161ffff021916908361ffff160217905550905050336001856040518082805190602001908083835b602083106120db5780518252601f1990920191602091820191016120bc565b51815160209384036101000a60001901801990921691161790529201948552506040519384900301909220805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03949094169390931790925550506002805463ffffffff19811663ffffffff9182166001019091161790555050505050565b60c060405190810160405280606081526020016060815260200160608152602001606081526020016000600160a060020a03168152602001600067ffffffffffffffff1681525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106121e357805160ff1916838001178555612210565b82800160010185558215612210579182015b828111156122105782518255916020019190600101906121f5565b5061221c929150612288565b5090565b6040805160e08101825260608082526000602083018190529282018390528082018190526080820181905260a082015260c081019190915290565b608060405190810160405280606081526020016060815260200160608152602001600061ffff1681525090565b61111a91905b8082111561221c576000815560010161228e5600a165627a7a723058203a587a9c17a93bad4078b9c4754c35d1a1b1b0a1c42179e5c569e9fa425746010029";

    public static final String FUNC_GETENCRYPTEDPHONE = "getEncryptedPhone";

    public static final String FUNC_ADDPUBLICITYINFO = "addPublicityInfo";

    public static final String FUNC_GETVOTERCOUNT = "getVoterCount";

    public static final String FUNC_REGISTERPROPERIETOR = "registerProperietor";

    public static final String FUNC_GETAUTHORITY = "getAuthority";

    public static final String FUNC_ADDINCOMEITEM = "addIncomeItem";

    public static final String FUNC_ADDPROPERTYREGISTRYCODE = "addPropertyRegistryCode";

    public static final String FUNC_GETINCOMEITEMCOUNT = "getIncomeItemCount";

    public static final String FUNC_ADDEXPENSEITEM = "addExpenseItem";

    public static final String FUNC_GETPUBLICITYINFOCOUNT = "getPublicityInfoCount";

    public static final String FUNC_GETPUBLICITYINFO = "getPublicityInfo";

    public static final String FUNC_GETENCRYPTEDPWD = "getEncryptedPwd";

    public static final String FUNC_GETEXPENSEITEMCOUNT = "getExpenseItemCount";

    public static final String FUNC_GETINCOMEITEM = "getIncomeItem";

    public static final String FUNC_GETEXPENSEITEM = "getExpenseItem";

    public static final String FUNC_REGISTERPROPERTY = "registerProperty";

    public static final String FUNC_GETNICKNAME = "getNickName";

    public static final String FUNC_GETUSERBYPHONE = "getUserByPhone";

    public static final String FUNC_GETUSERBYADDR = "getUserByAddr";

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

    public RemoteCall<BigInteger> getAuthority(String _addr) {
        final Function function = new Function(FUNC_GETAUTHORITY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addIncomeItem(String _payer, BigInteger _amountInCents, String _desc, String _fileHashes, String _fileNames, BigInteger _timestamp) {
        final Function function = new Function(
                FUNC_ADDINCOMEITEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_payer), 
                new org.web3j.abi.datatypes.generated.Uint64(_amountInCents), 
                new org.web3j.abi.datatypes.Utf8String(_desc), 
                new org.web3j.abi.datatypes.Utf8String(_fileHashes), 
                new org.web3j.abi.datatypes.Utf8String(_fileNames), 
                new org.web3j.abi.datatypes.generated.Uint64(_timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addPropertyRegistryCode(String _encryptedPhone, BigInteger code) {
        final Function function = new Function(
                FUNC_ADDPROPERTYREGISTRYCODE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_encryptedPhone), 
                new org.web3j.abi.datatypes.generated.Uint24(code)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getIncomeItemCount() {
        final Function function = new Function(FUNC_GETINCOMEITEMCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addExpenseItem(String _payee, BigInteger _amountInCents, String _desc, String _fileHashes, String _fileNames, BigInteger _timestamp) {
        final Function function = new Function(
                FUNC_ADDEXPENSEITEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_payee), 
                new org.web3j.abi.datatypes.generated.Uint64(_amountInCents), 
                new org.web3j.abi.datatypes.Utf8String(_desc), 
                new org.web3j.abi.datatypes.Utf8String(_fileHashes), 
                new org.web3j.abi.datatypes.Utf8String(_fileNames), 
                new org.web3j.abi.datatypes.generated.Uint64(_timestamp)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteCall<BigInteger> getExpenseItemCount() {
        final Function function = new Function(FUNC_GETEXPENSEITEMCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple7<String, String, BigInteger, String, String, String, BigInteger>> getIncomeItem(BigInteger _idx) {
        final Function function = new Function(FUNC_GETINCOMEITEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_idx)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint64>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint64>() {}));
        return new RemoteCall<Tuple7<String, String, BigInteger, String, String, String, BigInteger>>(
                new Callable<Tuple7<String, String, BigInteger, String, String, String, BigInteger>>() {
                    @Override
                    public Tuple7<String, String, BigInteger, String, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, String, BigInteger, String, String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<Tuple7<String, String, BigInteger, String, String, String, BigInteger>> getExpenseItem(BigInteger _idx) {
        final Function function = new Function(FUNC_GETEXPENSEITEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_idx)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint64>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint64>() {}));
        return new RemoteCall<Tuple7<String, String, BigInteger, String, String, String, BigInteger>>(
                new Callable<Tuple7<String, String, BigInteger, String, String, String, BigInteger>>() {
                    @Override
                    public Tuple7<String, String, BigInteger, String, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<String, String, BigInteger, String, String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (String) results.get(5).getValue(), 
                                (BigInteger) results.get(6).getValue());
                    }
                });
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

    public RemoteCall<Tuple5<String, String, String, String, BigInteger>> getUserByPhone(String _encryptedPhone) {
        final Function function = new Function(FUNC_GETUSERBYPHONE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_encryptedPhone)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint16>() {}));
        return new RemoteCall<Tuple5<String, String, String, String, BigInteger>>(
                new Callable<Tuple5<String, String, String, String, BigInteger>>() {
                    @Override
                    public Tuple5<String, String, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<Tuple5<String, String, String, String, BigInteger>> getUserByAddr(String _addr) {
        final Function function = new Function(FUNC_GETUSERBYADDR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint16>() {}));
        return new RemoteCall<Tuple5<String, String, String, String, BigInteger>>(
                new Callable<Tuple5<String, String, String, String, BigInteger>>() {
                    @Override
                    public Tuple5<String, String, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
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

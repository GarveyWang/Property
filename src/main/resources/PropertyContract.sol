pragma solidity ^0.4.21;

contract PropertyContract {
    //用户
    struct User{
        string encryptedPwd;
        string encryptedPhone;
        string nickName;
        uint16 authority;
    }
    mapping(address => User) userMap;
    mapping(string => address) phoneAddrMap;
    address[] userAddrList;
    
    constructor (string _encryptedPwd, string _encryptedPhone, string _nickName) public {
        registerUser(_encryptedPwd, _encryptedPhone, _nickName, 4095);
    }
    
    function getUserByIndex(uint256 _idx) public constant returns(address, string, string, string, uint16){
        address addr = userAddrList[_idx];
        return getUserByAddr(addr);
    }
    
    function getUserByPhone(string _encryptedPhone) public constant returns(address, string, string, string, uint16){
        address addr = phoneAddrMap[_encryptedPhone];
        return getUserByAddr(addr);
    }
    
    function getUserByAddr(address _addr) public constant returns(address, string, string, string, uint16){
        User storage user = userMap[_addr];
        return (_addr, user.encryptedPwd, user.encryptedPhone ,user.nickName, user.authority);
    }
    
    function getAuthority(address _addr) public view returns (uint16) {
        return userMap[_addr].authority;
    }
    function getEncryptedPwd(address _addr) public constant returns (string) {
        return userMap[_addr].encryptedPwd;
    }
    function getEncryptedPhone(address _addr) public view returns (string) {
        return userMap[_addr].encryptedPhone;
    }
    function getNickName(address _addr) public view returns (string) {
        return userMap[_addr].nickName;
    }
    
    function getUserCount() public view returns (uint256){
        return userAddrList.length;
    }
    
    //注册 
    mapping(string => uint24) proprietorRegistryCodeMap;
    mapping(string => uint24) propertyRegistryCodeMap;
    
    function hasProprietorRegistryCode(string _encryptedPhone) public view returns (bool) {
        return proprietorRegistryCodeMap[_encryptedPhone] == 0;
    }
    
    function hasPropertyRegistryCode(string _encryptedPhone) public view returns (bool) {
        return propertyRegistryCodeMap[_encryptedPhone] == 0;
    }
    
    function addProprietorRegistryCode(string _encryptedPhone, uint24 code) public{
        if(userMap[msg.sender].authority & 512 > 0 && phoneAddrMap[_encryptedPhone] == address(0)){
            proprietorRegistryCodeMap[_encryptedPhone] = code;
        }
    }
    
    function addPropertyRegistryCode(string _encryptedPhone, uint24 code) public{
        if(userMap[msg.sender].authority & 1024 > 0 && phoneAddrMap[_encryptedPhone] == address(0)){
            propertyRegistryCodeMap[_encryptedPhone] = code;
        }
    }
    
    function registerProprietor(uint24 _code, string _encryptedPwd, string _encryptedPhone, string _nickName) public returns (bool){
        if(_code != 0 && proprietorRegistryCodeMap[_encryptedPhone] == _code){
            registerUser(_encryptedPwd, _encryptedPhone, _nickName, 39);
        }
    }
    
    function registerProperty(uint24 _code, string _encryptedPwd, string _encryptedPhone, string _nickName) public returns (bool){
        if(_code != 0 && propertyRegistryCodeMap[_encryptedPhone] == _code){
            registerUser(_encryptedPwd, _encryptedPhone, _nickName, 1023);
        }
    }
    
    function registerUser(string _encryptedPwd, string _encryptedPhone, string _nickName, uint16 _authority) internal {
        User memory user = User(_encryptedPwd, _encryptedPhone, _nickName, _authority);
        userMap[msg.sender] = user;
        phoneAddrMap[_encryptedPhone] = msg.sender;
        userAddrList.push(msg.sender);
    }
    
    //公告 
    struct PublicityInfo{
        string title;
        string content;
        string fileHashes;
        string fileNames;
        address author;
        uint64 timestamp;
    }
    PublicityInfo[] publicityInfoList;
    
    function getPublicityInfoCount() public view returns(uint256){
        return publicityInfoList.length;
    }
    
    function addPublicityInfo(string _title, string _content, string _fileHashes, string _fileNames, uint64 _timestamp) public returns(uint256) {
        if(userMap[msg.sender].authority & 128 > 0){
            PublicityInfo memory publicityInfo = PublicityInfo(_title, _content, _fileHashes, _fileNames, msg.sender, _timestamp);
            return publicityInfoList.push(publicityInfo);
        }
    }
    
    function getPublicityInfo(uint256 _idx) public view returns(string, string, string, string, address, uint64){
        PublicityInfo storage info = publicityInfoList[_idx];
        return (info.title, info.content, info.fileHashes, info.fileNames, info.author, info.timestamp);
    }
    
    //收入 
    struct IncomeItem{
        string payer;
        address recorder;
        uint64 amountInCents;
        string desc;
        string fileHashes;
        string fileNames;
        uint64 timestamp;
    }
    IncomeItem[] incomeList;
    
    function getIncomeItemCount() public view returns(uint256){
        return incomeList.length;
    } 
    
    function addIncomeItem(string _payer, uint64 _amountInCents, string _desc, string _fileHashes, string _fileNames, uint64 _timestamp) public returns(uint256){
        if(userMap[msg.sender].authority & 256 > 0){
            IncomeItem memory incomeItem = IncomeItem(_payer, msg.sender, _amountInCents, _desc, _fileHashes, _fileNames, _timestamp);
            return incomeList.push(incomeItem);
        }
    }
    
    function getIncomeItem(uint256 _idx) public view returns(string, address, uint64, string, string, string, uint64){
        IncomeItem storage income = incomeList[_idx];
        return (income.payer, income.recorder, income.amountInCents, income.desc, income.fileHashes, income.fileNames, income.timestamp);
    }
    
    //支出
    struct ExpenseItem{
        string payee;
        address recorder;
        uint64 amountInCents;
        string desc;
        string fileHashes;
        string fileNames;
        uint64 timestamp;
    }
    ExpenseItem[] expenseList;
    
    function getExpenseItemCount() public view returns(uint256){
        return expenseList.length;
    } 
    
    function addExpenseItem(string _payee, uint64 _amountInCents, string _desc, string _fileHashes, string _fileNames, uint64 _timestamp) public returns(uint256){
        if(userMap[msg.sender].authority & 256 > 0){
            ExpenseItem memory expenseItem = ExpenseItem(_payee, msg.sender, _amountInCents, _desc, _fileHashes, _fileNames, _timestamp);
            return expenseList.push(expenseItem);
        }
    }
    
    function getExpenseItem(uint256 _idx) public view returns(string, address, uint64, string, string, string, uint64){
        ExpenseItem storage expense = expenseList[_idx];
        return (expense.payee, expense.recorder, expense.amountInCents, expense.desc, expense.fileHashes, expense.fileNames, expense.timestamp);
    }
    
    //提案
    struct Motion{
        string title;
        string content;
        string fileHashes;
        string fileNames;
        address author;
        uint64 timestamp;
        bool multipleVote;
        string options;
        uint32[] totalVotes;
        mapping(address => uint16[]) voteMap;
        mapping(address => bool) voteFinishMap;
    }
    Motion[] motionList;
    
    function getMotionCount() public view returns(uint256){
        return motionList.length;
    }
    
    function addMotion(string _title, string _content, string _fileHashes, string _fileNames, uint64 _timestamp, bool _multipleVote, string _options, uint64 _length) public returns(uint256){
        if(userMap[msg.sender].authority & 16 > 0){
            uint32[] memory votes = new uint32[](_length);
            Motion memory motion = Motion(_title, _content, _fileHashes, _fileNames, msg.sender,  _timestamp, _multipleVote, _options, votes);
            return motionList.push(motion);
        }
    }
    
    function getMotion(uint256 _motionIdx) public view returns(string, string, string, string, address, uint64, bool, string){
        Motion storage motion = motionList[_motionIdx];
        return (motion.title, motion.content, motion.fileHashes, motion.fileNames, motion.author, motion.timestamp, motion.multipleVote, motion.options);
    }
    
    function voteOption(uint256 _motionIdx, uint16 _optionIdx, bool finished) public{
        if(userMap[msg.sender].authority & 32 > 0) {
            Motion storage motion = motionList[_motionIdx];
            if(motion.timestamp > 0 && !motion.voteFinishMap[msg.sender]){
                uint256 voteCount = motion.voteMap[msg.sender].length;
                if(voteCount == 0){
                    motion.voteMap[msg.sender].push(_optionIdx);
                    motion.totalVotes[_optionIdx] += 1;
                    if(motion.multipleVote){
                        motion.voteFinishMap[msg.sender] = finished;
                    }else{
                        motion.voteFinishMap[msg.sender] = true;
                    }
                } else if(motion.multipleVote && motion.voteMap[msg.sender][voteCount-1] < _optionIdx) {
                    motion.voteMap[msg.sender].push(_optionIdx);
                    motion.totalVotes[_optionIdx] += 1;
                    motion.voteFinishMap[msg.sender] = finished;
                }
            }
        }
    }
    
    function getVoteCount(uint256 _motionIdx) public view returns(uint256){
        Motion storage motion = motionList[_motionIdx];
        return motion.voteMap[msg.sender].length;
    }
    
    function getVoteIndex(uint256 _motionIdx, uint256 idx) public view returns(uint16){
        Motion storage motion = motionList[_motionIdx];
        if(motion.timestamp > 0){
            return motion.voteMap[msg.sender][idx];
        }
    }
    
    function getTotalVote(uint256 _motionIdx, uint16 _optionIdx) public view returns(uint32){
        Motion storage motion = motionList[_motionIdx];
        return motion.totalVotes[_optionIdx];
    }
    
    function hasVoted(uint256 _motionIdx) public view returns(bool){
        Motion storage motion = motionList[_motionIdx];
        return motion.voteFinishMap[msg.sender];
    }
    
    //权限提案
    struct AuthMotion{
        bool settled;
        uint16 authority;
        bool turnOn;
        string reason;
        address fromAddr;
        address targetAddr;
        uint32 approvalCount;
        uint32 disapprovalCount;
        uint64 timestamp;
        mapping(address => uint8) voteMap;
    }
    AuthMotion[] authMotionList;
    uint8 settledRatio = 50;
    uint32 settledMinCount = 3;
    
    function getSettledRatio() public view returns(uint8){
        return settledRatio;
    }
    
    function setSettledRatio(uint8 _settledRatio) public {
        if(userMap[msg.sender].authority & 2048 > 0 && _settledRatio <= 100){
            settledRatio = _settledRatio;
        }
    }
    
    function getSettledMinCount() public view returns(uint32){
        return settledMinCount;
    }
    
    function setSettledMinCount(uint32 _settledMinCount) public {
        if(userMap[msg.sender].authority & 2048 > 0){
            settledMinCount = _settledMinCount;
        }
    }
    
    function getAuthMotionCount() public view returns(uint256){
        return authMotionList.length;
    }
   
    
    function getAuthMotionVote(uint256 _authMotionIdx) public view returns(uint8){
        AuthMotion storage authMotion = authMotionList[_authMotionIdx];
        return authMotion.voteMap[msg.sender];
    }
}
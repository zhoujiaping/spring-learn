import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class EciqsCreditApplyReq implements Serializable{
    private static final long serialVersionUID = -1984959064893996018L;

    /**
     * 申请流水号
     */
    @JSONField(name="apply_no")
    private String applyNo; 
    
    /**
     * 产品名称: 6000-经营贷
     */
    private String product;
    
    /**
     * 手机号
     */
    @JSONField(name="account_mobile")
    private String accountMobile;
    
    /**
     * 姓名
     */
    @JSONField(name="account_name")
    private String accountName;
    
    /**
     * 证件类型:
     * 0:身份证；1:户口簿；2:护照；3:军官证；4:士兵证；5:港澳居民来往内地通行证；6:台湾同胞来往内地通行证；
     * 7:临时身份证；8:外国人居留证；9:警官证；A:香港身份证；B:澳门身份证；C:台湾身份证；X:其他证据。
     * 目前默认为 0:身份证.
     */
    @JSONField(name="query_cert_type")
    private String queryCertType;
    
    /**
     * 证件号码
     */
    @JSONField(name="id_number")
    private String idNumber;
    
    /**
     * 客户渠道: 1:白名单  2:其他  (默认为1)
     */
    @JSONField(name="cust_channel")
    private String custChannel;
    
    /**
     * 企业名称
     */
    @JSONField(name="enterprise_name")
    private String enterpriseName;
    
    /**
     * 企业证件类型(1-统一社会信用代码，2-企业注册号)
     */
    @JSONField(name="enterprise_code_type")
    private String enterpriseCodeType;
    
    /**
     * 企业性质
     */
    @JSONField(name = "enterprise_nature")
    private String enterpriseNature;
    
    /**
     * 统一社会信用代码或企业注册号
     */
    @JSONField(name="enterprise_code")
    private String enterpriseCode;
    
    /**
     * 审批场景: ps: 1:开通 2:提款 3:人工审核0:其他  (默认为1)
     */
    @JSONField(name="approve_env")
    private String approveEnv;
    
    /**
     * 外部数据查询方式: ps 1:实时 0:非实时  默认为1
     */
    @JSONField(name="is_realtime")
    private String isRealtime;
    
    /**
     * 查询原因: ps: 01:贷款审批 02:贷后管理 03:信用卡审批 05:异议核查 08:担保资格审查 16:公积金提取复核 19特约商户实名审查特约商户实名审查，默认为01
     */
    @JSONField(name="query_reason")
    private String queryReason;
    
    /**
     * 设备Id
     */
    @JSONField(name="device_id")
    private String deviceId;
    
    /**
     * IP地址
     */
    @JSONField(name="ip_address")
    private String ipAddress;
    
    /**
     * 同盾设备指纹移动端
     */
    @JSONField(name="black_box")
    private String blackBox;
    
    /**
     * 同盾设备ID
     */
    @JSONField(name="token_id")
    private String tokenId;
    
    /**
     * 白骑士设备指纹tokenKey
     */
    @JSONField(name="token_key")
    private String tokenKey;
    
    /**
     * 应用平台类型：h5，web，ios，android
     */
    private String platform;
    
    /**
     * 月结账号
     */
    @JSONField(name="monthly_acct_num")
    private String monthlyAcctNum;
    
    /**
     * 顺手付会员号
     */
    @JSONField(name="member_no")
    private String memberNo;
    
    /**
     * 申请时间(yyyy-MM-dd HH:mm:ss)
     */
    @JSONField(name="event_occur_time")
    private String eventOccurTime;

    /**
     * 不知道什么 字段
     */
    @JSONField(name = "autho_file_url")
    private String authoFileUrl;
     
    /**
     * 金融机构实名认证记录索引编号_人脸识别订单号
     *
     */
    @JSONField(name = "ten_pay_auth_index")
    private String tenPayAuthIndex;
    
    /**
     * 金融机构在线授权时间         格式：yyyy-MM-dd HH:mm:ss
     */
    @JSONField(name = "ten_pay_auth_time")
    private String tenPayAuthTime;
    
    /**
     * 金融机构人脸识别时间  格式：yyyy-MM-dd HH:mm:ss
     */
    @JSONField(name = "ten_pay_veri_face_time")
    private String tenPayVeriFaceTime;
    
    /**
     * 重新授信标志(0-非重新授信，1-重新授信, 2-补偿首次授信, 3-补偿重新授信)
     */
    @JSONField(name = "again_credit_flag")
    private String againCreditFlag;
    
    /**
     * 当前是否贷款逾期(0-否，1-是)
     */
    @JSONField(name = "overdue")
    private String overdue;
    
    /**
     * 近两年单笔借据累计逾期次数(最大)
     */
    @JSONField(name = "total_overdue_year2")
    private String totalOverdueYear2;
    
    /**
     * 近6个月单笔借据累计逾期次数
     */
    @JSONField(name = "total_overdue_month6")
    private String totalOverdueMonth6;
    
    /**
     * 近1年单笔借据最长逾期天数
     */
    @JSONField(name = "overdue_days_year1")
    private String overdueDaysYear1;
    
    
    /**
     * 联系人关系：
     * father-父亲，
     * mother-母亲，
     * spouse-配偶，
     * child-子女，
     * other_relative-其他亲属，
     * friend-朋友，
     * coworker-同事，
     * others-其他
     */
    @JSONField(name = "contact1_relation")
    private String contact1Relation;
    
    /**
     * 联系人姓名
     */
    @JSONField(name = "contact1_name")
    private String contact1Name;
    
    /**
     * 联系人手机号
     */
    @JSONField(name = "contact1_mobile")
    private String contact1Mobile;
    
    /**
     * 客户群体划分，1：月结及默认用户，2：便捷COD用户，3：高频散单用户 4:仓储用户
     */
    @JSONField(name = "customer_partition")
    private Integer  customerPartition;
    
    /**
     * 客户职级,0：未知，1:企业法人，2：最大股东
     */
    @JSONField(name = "customer_rank")
    private Integer customerRank;
    
    @JSONField(name = "risk_label")
    private String riskLabel;
    
    /**
     * 授信类型
     * 0、SX0:正常授信
     * 1、SX1：授信有效期（1年）到期，额度失效，需重新授信；
     * 2、SX2：授信三个月内未首次提款，额度失效，需重新授信；
     * 3、SX3：授信拒绝，1个月后可重新授信；
     * 4、SX4：提款时人脸识别连续三次拒绝，额度失效，1个月后可重新授信；
     * 5、SX5：提款白骑士拒绝，额度失效，1个月后可重新授信。
     */
    @JSONField(name = "credit_type")
    private String creditType;
    
    @JSONField(name = "bairong_gid")
    private String baiRongGid;

    //顺车购授信专有字段begin
    /**
     * "授信升级标识：
     * 1:新授信用户（默认）2:升级用户"
     */
    @JSONField(name = "update_flag")
    private String updateFlag = "1";
    /**
     * "是否钢铁侠
     * 0:否 1:是(默认)"  注意：由于经营贷这边都不是钢铁侠，如果按接口文档来，默认为是，那么之前的代码都需要修改。
     * 所以，我们将默认值改为否，这样不用修改原来的代码。
     */
    @JSONField(name = "is_iron_man")
    private String isIronMan = "0";

    /**
     * 申请时GPS所在地（县级市）
     */
    @JSONField(name = "gps_city")
    private String gpsCity = "";
    //顺车购授信专有字段end


    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getIsIronMan() {
        return isIronMan;
    }

    public void setIsIronMan(String isIronMan) {
        this.isIronMan = isIronMan;
    }

    public String getGpsCity() {
        return gpsCity;
    }

    public void setGpsCity(String gpsCity) {
        this.gpsCity = gpsCity;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAccountMobile() {
        return accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getQueryCertType() {
        return queryCertType;
    }

    public void setQueryCertType(String queryCertType) {
        this.queryCertType = queryCertType;
    }

    public String getCustChannel() {
        return custChannel;
    }

    public void setCustChannel(String custChannel) {
        this.custChannel = custChannel;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getApproveEnv() {
        return approveEnv;
    }

    public void setApproveEnv(String approveEnv) {
        this.approveEnv = approveEnv;
    }

    public String getQueryReason() {
        return queryReason;
    }

    public void setQueryReason(String queryReason) {
        this.queryReason = queryReason;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getBlackBox() {
        return blackBox;
    }

    public void setBlackBox(String blackBox) {
        this.blackBox = blackBox;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getMonthlyAcctNum() {
        return monthlyAcctNum;
    }

    public void setMonthlyAcctNum(String monthlyAcctNum) {
        this.monthlyAcctNum = monthlyAcctNum;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getEventOccurTime() {
        return eventOccurTime;
    }

    public void setEventOccurTime(String eventOccurTime) {
        this.eventOccurTime = eventOccurTime;
    }

    public String getIsRealtime() {
        return isRealtime;
    }

    public void setIsRealtime(String isRealtime) {
        this.isRealtime = isRealtime;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEnterpriseCodeType() {
        return enterpriseCodeType;
    }

    public void setEnterpriseCodeType(String enterpriseCodeType) {
        this.enterpriseCodeType = enterpriseCodeType;
    }

    public String getAuthoFileUrl() {
        return authoFileUrl;
    }

    public void setAuthoFileUrl(String authoFileUrl) {
        this.authoFileUrl = authoFileUrl;
    }

    public String getEnterpriseNature() {
        return enterpriseNature;
    }

    public void setEnterpriseNature(String enterpriseNature) {
        this.enterpriseNature = enterpriseNature;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getAgainCreditFlag() {
        return againCreditFlag;
    }

    public void setAgainCreditFlag(String againCreditFlag) {
        this.againCreditFlag = againCreditFlag;
    }

    public String getOverdue() {
        return overdue;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
    }

    public String getTotalOverdueYear2() {
        return totalOverdueYear2;
    }

    public void setTotalOverdueYear2(String totalOverdueYear2) {
        this.totalOverdueYear2 = totalOverdueYear2;
    }

    public String getTotalOverdueMonth6() {
        return totalOverdueMonth6;
    }

    public void setTotalOverdueMonth6(String totalOverdueMonth6) {
        this.totalOverdueMonth6 = totalOverdueMonth6;
    }

    public String getOverdueDaysYear1() {
        return overdueDaysYear1;
    }

    public void setOverdueDaysYear1(String overdueDaysYear1) {
        this.overdueDaysYear1 = overdueDaysYear1;
    }

    public String getTenPayAuthIndex() {
        return tenPayAuthIndex;
    }

    public void setTenPayAuthIndex(String tenPayAuthIndex) {
        this.tenPayAuthIndex = tenPayAuthIndex;
    }

    public String getTenPayAuthTime() {
        return tenPayAuthTime;
    }

    public void setTenPayAuthTime(String tenPayAuthTime) {
        this.tenPayAuthTime = tenPayAuthTime;
    }

    public String getTenPayVeriFaceTime() {
        return tenPayVeriFaceTime;
    }

    public void setTenPayVeriFaceTime(String tenPayVeriFaceTime) {
        this.tenPayVeriFaceTime = tenPayVeriFaceTime;
    }

    public String getContact1Relation() {
        return contact1Relation;
    }

    public void setContact1Relation(String contact1Relation) {
        this.contact1Relation = contact1Relation;
    }

    public String getContact1Name() {
        return contact1Name;
    }

    public void setContact1Name(String contact1Name) {
        this.contact1Name = contact1Name;
    }

    public String getContact1Mobile() {
        return contact1Mobile;
    }

    public void setContact1Mobile(String contact1Mobile) {
        this.contact1Mobile = contact1Mobile;
    }

    public Integer getCustomerPartition() {
        return customerPartition;
    }

    public void setCustomerPartition(Integer customerPartition) {
        this.customerPartition = customerPartition;
    }

    public Integer getCustomerRank() {
        return customerRank;
    }

    public void setCustomerRank(Integer customerRank) {
        this.customerRank = customerRank;
    }

    public String getRiskLabel() {
        return riskLabel;
    }

    public void setRiskLabel(String riskLabel) {
        this.riskLabel = riskLabel;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getBaiRongGid() {
        return baiRongGid;
    }

    public void setBaiRongGid(String baiRongGid) {
        this.baiRongGid = baiRongGid;
    }
    
}

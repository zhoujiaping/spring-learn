import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 信用管理平台返回结果
 * @author 01374739
 *
 */
public class EciqsCreditApplyResultDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6358342141957458750L;

	/**
	 * 业务流水号
	 */
	@JSONField(name = "apply_no")
	private String applyNo;
	
	/**
	 * 身份证号
	 */
	@JSONField(name = "id_number")
	private String idNumber;
	
	/**
	 * 1:通过；2:拒绝
	 */
	@JSONField(name = "approve_rlt")
	private String approveRlt;
	
	/**
	 * 拒绝码
	 */
	@JSONField(name = "refuse_code")
	private String refuseCode;
	
	/**
	 * 拒绝原因
	 */
	@JSONField(name = "refuse_reason")
	private String refuseReason;
	
	/**
	 * 额度
	 */
	private BigDecimal limit;
	
	/**
	 * 等级
	 */
	@JSONField(name = "cust_level")
	private String custLevel;
	
	/**
	 * 决策返回时间
	 */
	@JSONField(name = "decision_time")
	private String decisionTime;

	/**
     * 华夏流水号
     */
    @JSONField(name = "xy_credit_query_no")
    private String  xyCreditQueryNo;
    
    /**
     * 授信过期时间(yyyy-MM-dd HH:mm:ss)
     */
    @JSONField(name = "credit_expired_at")
    private String creditExpiredAt;
    
    /**
     * 利率
     */
    @JSONField(name = "interest_rate")
    private String interestRate;
    
    /**
     * 合同列表
     */
    @JSONField(name = "contract_List")
    private String contractList;



	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getApproveRlt() {
		return approveRlt;
	}

	public void setApproveRlt(String approveRlt) {
		this.approveRlt = approveRlt;
	}

	public String getRefuseCode() {
		return refuseCode;
	}

	public void setRefuseCode(String refuseCode) {
		this.refuseCode = refuseCode;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public BigDecimal getLimit() {
		return limit;
	}

	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}

	public String getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}

	public String getDecisionTime() {
		return decisionTime;
	}

	public void setDecisionTime(String decisionTime) {
		this.decisionTime = decisionTime;
	}

    public String getXyCreditQueryNo() {
        return xyCreditQueryNo;
    }

    public void setXyCreditQueryNo(String xyCreditQueryNo) {
        this.xyCreditQueryNo = xyCreditQueryNo;
    }

    public String getCreditExpiredAt() {
        return creditExpiredAt;
    }

    public void setCreditExpiredAt(String creditExpiredAt) {
        this.creditExpiredAt = creditExpiredAt;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getContractList() {
        return contractList;
    }

    public void setContractList(String contractList) {
        this.contractList = contractList;
    }
}
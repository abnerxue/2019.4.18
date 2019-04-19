package ck.po;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import ck.dto.MedcineDTO;
import lombok.Data;

/**
 * @Author: alex
 * @Description:
 * @Date: 2019/2/14 15:56
 * @Modified:
 **/
@Data
public class Prescription implements Serializable
{
	private static final long serialVersionUID = 5329483631210385436L;
	private int state; // 初审状态ID，t_audit_state外键
	private String prescriptionNumber; // 处方号
	private String problemId; // t_audit_rules外键
	private String prescriptionReason; // 用药理由
	private String visitNumber; // 就诊号
	private Timestamp submmitTime; // 提交时间
	private String auditPharmacist; // 审核药师
	private Timestamp auditTime; // 审核时间
	private String comfirmPharmacist; // 确认药师
	private Timestamp comfirmTime; // 确认时间
	private int type; // 处方药品类型，t_type1_dictionary外键
	private int auditCenter; // 审方中心， t_audit_center外键
	private int medicalUnit; // 就医单位名称，t_unit_name外键
	private String denyReason; // 打回原因
	private String denyOwner; // 打回对象
	private String denyLevel; // 打回等级
	private String doctorName; // 医生签字
	private String departmentName; // 科室名称
	private int isEmergency; // 是否急诊科室
	private Patient patientInfo; // 患者信息
	private double totalFee; // 合计金额
	private String prescriptionDate; // 处方日期
	private List<Diagnosis> diagnosis; // 诊断
	private List<MedcineDTO> medcineItems; // 药品信息
	private int reviewState; // 复审结果，t_audit_level外键
	private int isOverTime; // 是否超时
}

package ck.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class PrescriptionDTO implements Serializable
{
	private static final long serialVersionUID = 4831136115978433978L;
	private int state; // 初审状态ID，t_audit_state外键
	private String prescriptionNumber; // 处方号
	private String prescriptionContent;// 处方内容
	private String prescriptionReason; // 用药理由
	private String visitNumber; // 就诊号
	private Date submmitTime; // 提交时间
	private String auditPharmacist; // 审核药师
	private Date auditTime; // 审核时间
	private String comfirmPharmacist; // 确认药师
	private Date comfirmTime; // 确认时间
	private int type; // 处方药品类型，t_type1_dictionary外键
	private String auditCenter; // 审方中心， t_audit_center外键
	private String medicalUnit; // 就医单位名称，t_unit_name外键
	private String denyReason; // 打回原因
	private String denyOwner; // 打回对象
	private String denyLevel; // 打回等级
	private String doctorName; // 医生签字
	private String departmentName; // 科室名称
	private Boolean isEmergency; // 是否急诊科室
	private PatientDTO patientInfo; // 患者信息
	private double totalFee; // 合计金额
	private Date prescriptionDate; // 处方日期
	private List<DiagnosisDTO> diagnosis; // 诊断
	private List<MedcineDTO> medcineItems; // 药品信息
}

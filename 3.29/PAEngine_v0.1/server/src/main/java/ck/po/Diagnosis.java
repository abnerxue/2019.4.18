package ck.po;

import java.io.Serializable;

import lombok.Data;

//诊断表
@Data
public class Diagnosis implements Serializable
{
	private static final long serialVersionUID = 4663688599065900804L;
	private int id;
	private String diagnosisName;
	private String icd10;
}
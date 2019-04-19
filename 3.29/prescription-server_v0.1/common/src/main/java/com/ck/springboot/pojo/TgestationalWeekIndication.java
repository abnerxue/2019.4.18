package com.ck.springboot.pojo;

import lombok.Data;
/**
 * 孕周适应症
 * @author Administrator
 *
 */
@Data
public class TgestationalWeekIndication {
	private int id;
	private int diagnosisId;
	private int medcineId;
	
	private int alltime;
	private int _0_12_week;
	private int _12_childbirth;
	private int _27_childbirth;
	private int _34_childbirth;
	
	
}

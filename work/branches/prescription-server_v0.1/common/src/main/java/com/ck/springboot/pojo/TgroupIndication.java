package com.ck.springboot.pojo;

import lombok.Data;

/**
 * 人群适应症
 * @author Administrator
 *
 */
@Data
public class TgroupIndication {
	private int id;
	private int diagnosisId;
	private int medcineId;
	
	private int male;//男
	private int female;//女
	
}

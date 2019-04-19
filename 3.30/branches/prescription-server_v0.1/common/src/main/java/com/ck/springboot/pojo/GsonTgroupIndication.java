package com.ck.springboot.pojo;

import java.util.List;

import lombok.Data;

@Data
public class GsonTgroupIndication {
	private int diagnosisId;
	List<TgroupIndication> groupList;
}

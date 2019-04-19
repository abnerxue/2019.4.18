package com.ck.springboot.utils;

import java.util.List;

import lombok.Data;

/**
 * gson映射实体
 * @author Administrator
 *
 */
@Data
public class GsonLabelMedicine {
	private int labelId;
	private List<Integer> medicineId;
}

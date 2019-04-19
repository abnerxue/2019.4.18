package com.ck.springboot.pojo;

import java.util.List;

import lombok.Data;
/**
 * Gson映射实体
 * @author Administrator
 *
 */
@Data
public class GsonZyyCheckLimit {
	private int liverKidneyDicId;//肝肾id
	private String description;//肝肾描述
	private List<ZyyCheckLimt> checkLimit;
}

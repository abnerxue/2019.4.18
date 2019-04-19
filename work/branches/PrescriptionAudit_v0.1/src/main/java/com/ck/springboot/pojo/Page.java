package com.ck.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Page {

	private int pageNo;
	private int pageSize;
	private int totalPage;
	private int totalCount;

}

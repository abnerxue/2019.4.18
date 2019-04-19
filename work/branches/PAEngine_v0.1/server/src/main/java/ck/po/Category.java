package ck.po;

import java.io.Serializable;

import lombok.Data;

//类别表
@Data
public class Category implements Serializable
{
	private static final long serialVersionUID = 1762472439099605471L;
	private int id;
	private String name; // 类别名
	private int pid; //父ID
}

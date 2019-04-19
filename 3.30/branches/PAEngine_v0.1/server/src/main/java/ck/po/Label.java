package ck.po;

import java.io.Serializable;

import lombok.Data;

//标签表
@Data
public class Label implements Serializable
{
	private static final long serialVersionUID = 44079689943468834L;
	private int id;
	private String Name;//标签名
}

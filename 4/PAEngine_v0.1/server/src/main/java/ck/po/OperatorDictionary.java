package ck.po;

import java.io.Serializable;

import lombok.Data;

//运营商字典表
@Data
public class OperatorDictionary implements Serializable
{
	private static final long serialVersionUID = 241971794777381375L;
	private int id;
	private String operator;//运营商

}

package ck.po;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuditRule implements Serializable
{
	private static final long serialVersionUID = -5039672709876838107L;
	private int id;
	private String code;
	private String name;
	private int action;
	private Boolean enable;

}

package ck.po;

import java.io.Serializable;

import lombok.Data;

//药品标签关系表
@Data
public class lblMedcine implements Serializable
{
	private static final long serialVersionUID = 905460504157374746L;
	private int id;
	private int mecineId;//药品ID
	private int labelId;//标签ID
}

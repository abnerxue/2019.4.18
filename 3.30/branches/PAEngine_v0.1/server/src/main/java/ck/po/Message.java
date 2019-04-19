package ck.po;

import java.io.Serializable;

import lombok.Data;

@Data
public class Message implements Serializable
{
	private static final long serialVersionUID = -3591055946835616960L;
	private String titile;
	private String info;

	public Message(String titile, String info)
	{
		super();
		this.titile = titile;
		this.info = info;
	}

}

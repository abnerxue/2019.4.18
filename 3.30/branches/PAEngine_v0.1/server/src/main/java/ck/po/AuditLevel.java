package ck.po;

import java.io.Serializable;

import lombok.Data;

//审核等级
@Data
public class AuditLevel implements Serializable{
	private static final long serialVersionUID = -8564816848705932887L;
	private Integer id;
    private String desp;
}

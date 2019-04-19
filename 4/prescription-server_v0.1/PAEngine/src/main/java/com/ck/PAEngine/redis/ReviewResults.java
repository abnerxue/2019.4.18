package com.ck.PAEngine.redis;

import java.io.Serializable;

import lombok.Data;

/**
 * <h3>预处理结果实体类</h3>
 * <h3>专门用作读取redis数据，读取数据时类路径必须跟存储时一致</h3>
 * @author LIUJUNWU
 * @version 1.0.0
 */
@Data
public class ReviewResults implements Serializable
{
	private String reactionDescription;//描述
	private int auditLevelId;//审核等级ID
	private int type;//类型(1=配伍禁忌 ,2=相互作用,3=重复用药)
}

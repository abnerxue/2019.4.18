package com.ck.springboot.pojo;

import lombok.Data;

/**  
* <p>Description:相互作用-标签对标签 </p>  
* @author 胡高翔  
* @date 2019年3月23日  
*/  
@Data
public class CompatibilityLdL {
    private Integer id;
    
    private Integer labelPairId; 

    private Integer labelId;

    private String reactionDescription;
    
    private Integer auditLevelId;
    
    private Integer type;

}
package com.ck.springboot.pojo;

import lombok.Data;

/**  
* <p>Description:相互作用-药对药 </p>  
* @author 胡高翔  
* @date 2019年3月23日  
*/  
@Data
public class CompatibilityMdM {
    private Integer id;
    
    private Integer medcineId; 

    private Integer medcinePairId;

    private String reactionDescription;
    
    private Integer auditLevelId;
    
    private Integer type;

}
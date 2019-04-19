package com.ck.springboot.pojo;

import lombok.Data;

/**  
* <p>Description: 相互作用-药对类</p>  
* @author 胡高翔  
* @date 2019年3月23日  
*/  
@Data
public class CompatibilityMdC {
    private Integer id;
    
    private Integer medcineId; 

    private Integer categoryId;

    private String reactionDescription;
    
    private Integer auditLevelId;
    
    private Integer type;

}
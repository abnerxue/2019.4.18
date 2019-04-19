/**
 * 
 */
package com.ck.springboot.pojo;

import lombok.Data;

/**  
* <p>Description: </p>  
* @author 胡高翔  
* @date 2019年3月30日  
*/
@Data
public class ApplicableMedicine {
    private Integer id;
    
    private Integer diagnosisId;

    private Integer medcineId;
    
    private String medcineName;
}

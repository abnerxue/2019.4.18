/**
 * 
 */
package com.ck.springboot.pojo.report;

import lombok.Data;

/**  
* <p>Description: </p>  
* @author 胡高翔  
* @date 2019年3月28日  
*/
@Data
public class reportSummary {
    private Integer id;

    private Integer state;
    
    private Integer number;
    
    private String percentage;

}

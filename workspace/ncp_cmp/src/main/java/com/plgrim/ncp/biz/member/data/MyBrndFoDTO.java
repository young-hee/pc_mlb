package com.plgrim.ncp.biz.member.data;




import com.plgrim.ncp.base.abstracts.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("myBrndFoDTO")
public class MyBrndFoDTO extends AbstractDTO {


    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    private String mbrNo;
    
    private String empYn;
    
    /*
     * ---------------------------------------------------------------------
     * Constructors.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * public & interface method.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

}

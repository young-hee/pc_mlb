package com.plgrim.ncp.biz.member.data;




import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIdCntc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.biz.member.result.MypageOnlineMemberGradeInfoResult;
import com.plgrim.ncp.framework.page.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mypageFoDTO")
public class MypageMemberInfoDTO extends AbstractDTO {


    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

  
    private Mbr mbr;                    				// 회원 발급 쿠폰
    
    private String applyYear ;							// 적용년도
    
    private String selFromYear ;						// 선정년( from)
    
    private String selToYear ;							// 선정월( to)
    
    private String selFromMonth ;						// 선정년( from)
    
    private String selToMonth ;							// 선정월( to)

    private MypageOnlineMemberGradeInfoResult vipGrade;	// 온라인회원 등급정보
    
  
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

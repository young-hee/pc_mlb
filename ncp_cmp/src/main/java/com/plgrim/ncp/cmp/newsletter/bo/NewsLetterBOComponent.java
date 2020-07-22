package com.plgrim.ncp.cmp.newsletter.bo;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.biz.member.data.MemberBoDTO;
import com.plgrim.ncp.biz.newsletter.data.NewsLetterDTO;
import com.plgrim.ncp.biz.newsletter.data.NewsLetterResultDTO;
import com.plgrim.ncp.framework.data.SystemPK;

/**
 * newsletter command interface
 */
public interface NewsLetterBOComponent {

	/**
     * newsletter 목록 조회.
     * BO
     * @param systemPK [설명]
     * @param dto      [설명]
     * @param loginId  [설명]
     * @return the newsletter list
     * @ the exception
     * @since 
     */
    public NewsLetterResultDTO getNewsLetterList(SystemPK systemPK, NewsLetterDTO dto, String loginId);
    
    /**
     * newsletter 목록 excel.
     * BO
     * @param systemPK [설명]
     * @param dto      [설명]
     * @param loginId  [설명]
     * @return the newsletter list
     * @ the exception
     * @since 
     */
    public List<Map<String, Object>> getNewsLetterListExcel(SystemPK systemPK, NewsLetterDTO dto, String loginId);
    
    /**
     * newsletter delete.
     * BO    
     * @param dto      [설명]    
     * @return void
     * @ the exception
     * @since 
     */
    public void deleteNewsLetter(NewsLetterDTO dto);

}

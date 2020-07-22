package com.plgrim.ncp.cmp.newsletter.fo;

import com.plgrim.ncp.biz.newsletter.data.NewsLetterDTO;

/**
 * newsletter command interface
 */
public interface NewsLetterFOComponent {
	
    
    /**
     * newsletter delete.
     * BO    
     * @param dto      [설명]    
     * @return void
     * @ the exception
     * @since 
     */
    public void deleteNewsLetter(NewsLetterDTO dto);
    
    /**
     * newsletter insert.
     * BO    
     * @param dto      [설명]    
     * @return void
     * @ the exception
     * @since 
     */
    public void insertNewsLetter(NewsLetterDTO dto);


}

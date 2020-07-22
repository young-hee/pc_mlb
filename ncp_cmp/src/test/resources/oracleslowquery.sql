SELECT no
			  ,b.ord_no AS ordNo
			  ,CASE WHEN b.aff_com_ord_no IS NULL THEN '자사' ELSE com.com_nm END AS mcomOrAff
			  ,sm.mall_nm AS mallNm
			  ,la.cd_nm AS langNm
			  ,b.aff_com_ord_no AS affComOrdNo
			  ,b.aff_com_id AS saleAff
			  ,b.ord_dt AS ordDt
			  ,b.resve_ord_dlivy_prearnge_date AS resveOrdDlivyPrearngeDate
			  ,fn_masking(
				   'PHON'
				  ,   CASE WHEN b.cstmr_mobil_nation_no IS NULL THEN '' ELSE b.cstmr_mobil_nation_no || '-' END
				   || b.cstmr_mobil_area_no
				   || '-'
				   || b.cstmr_mobil_tlof_no
				   || '-'
				   || b.cstmr_mobil_tlof_wthn_no
				  ,''
				  ,?)
				   AS pchMoNo
			  ,b.com_id
			  ,b.brnd_id
			  ,b.god_no
			  ,b.erp_god_no
			  ,b.god_nm
			  ,b.itm_nm
			  ,gt.cd_nm AS godTp
			  ,ot.cd_nm AS ordTp
			  ,os.cd_nm AS ordStat
			  ,dc.cd_nm AS dlvCom
			  ,CASE
				   WHEN b.dlivy_drct_cncl_qty > 0
				   THEN
					   'Y'
				   WHEN (SELECT COUNT(*)
						   FROM lgs_rtrvl_drct_god rg
						  WHERE b.ord_no = rg.ord_no
							AND b.dlivy_drct_god_no = rg.dlivy_drct_god_no
							AND rg.rtrvl_stat_cd  <>  'RTRVL_WTHDRAW') > 0
				   THEN
					   'Y'
				   ELSE
					   'N'
			   END
				   AS clmYn
			  ,b.rtl_prc
			  ,b.sale_amt
			  ,b.stdr_crncy_amt AS ordAmt
			  ,b.ord_qty
			  ,b.mbr_no
			  ,fn_masking('ID',mbr.mbr_id,'',?) AS pchId
			  ,fn_masking('FLNM',b.cstmr_nm,'',?)  AS pchNm
			  ,fn_masking('FLNM',b.addrse_nm,'',?)  AS addrseNm
			  ,fn_masking(
				   'PHON'
				  ,   CASE WHEN b.addrse_mobil_nation_no IS NULL THEN '' ELSE b.addrse_mobil_nation_no || '-' END
				   || b.addrse_mobil_area_no
				   || '-'
				   || b.addrse_mobil_tlof_no
				   || '-'
				   || b.addrse_mobil_tlof_wthn_no
				  ,''
				  ,?)
				   AS addrseMoNo
			  ,b.dlv_pcupsp_turn
			  ,b.ord_god_turn
			  ,dv.cd_nm AS dvcNm /*디바이스채널*/
			  ,sb.brnd_nm AS brndNm
			  ,CASE WHEN b.crncy_cd = 'KRW' THEN NULL ELSE b.pay_exchg_rt_crncy_amt END ordAmtEx
			  ,CASE WHEN b.crncy_cd = 'KRW' THEN NULL ELSE '$' || ser.exchg_rt_crncy_amt || '=' || ser.stdr_crncy_amt END AS exchgInfo
		  FROM (
				
					  
		 
		SELECT b.*
		FROM   (SELECT ROWNUM NO ,a.*
    		FROM   (
	 	 
	 
					
							SELECT od.ord_no
								,od.aff_com_ord_no
								,od.aff_com_id
								,od.mall_id
								,od.lang_cd
								,od.ord_dt
								,od.resve_ord_dlivy_prearnge_date
								,od.cstmr_mobil_nation_no
								,od.cstmr_mobil_area_no
								,od.cstmr_mobil_tlof_no
								,od.cstmr_mobil_tlof_wthn_no
								,og.com_id
								,og.brnd_id
								,og.god_no
								,og.erp_god_no
								,og.god_nm
								,og.itm_nm
								,og.god_tp_cd
								,od.ord_tp_cd
								,od.ord_stat_cd
								,lv.dlv_com_cd
								,lg.dlivy_drct_cncl_qty
								,lg.dlivy_drct_god_no
								,og.rtl_prc
								,og.sale_amt
								,og.stdr_crncy_amt
								,og.ord_qty
								,od.mbr_no
								,od.cstmr_nm
								,lp.addrse_nm
								,lp.addrse_mobil_nation_no
								,lp.addrse_mobil_area_no
								,lp.addrse_mobil_tlof_no
								,lp.addrse_mobil_tlof_wthn_no
								,lv.dlv_pcupsp_turn
								,og.ord_god_turn
								,od.dvc_cd
								/* ncp 3차로 추가*/
								,od.crncy_cd
								,og.pay_exchg_rt_crncy_amt
								,od.exchg_rt_apl_beg_dt
							FROM ord od
								 JOIN ord_god og ON od.ord_no = og.ord_no
								 JOIN god g ON g.god_no = og.god_no
								 JOIN lgs_dlivy_drct_god lg
		                             ON og.ord_no = lg.ord_no AND og.ord_god_turn = lg.ord_god_turn
								 JOIN
								 lgs_dlv lv
									 ON lg.ord_no = lv.ord_no
									AND lg.dlv_pcupsp_turn = lv.dlv_pcupsp_turn
									AND lg.dlv_turn = lv.dlv_turn
								 JOIN
								 lgs_dlvsp lp
									 ON lv.ord_no = lp.ord_no
									AND lv.dlv_pcupsp_turn = lp.dlv_pcupsp_turn
						   WHERE 1 = 1
							   
                  AND NOT EXISTS (
                               SELECT 1
                                 FROM ORD_CLM_GOD_CNNC cc
                                 WHERE cc.ord_no = og.ord_no
                                  AND  cc.ord_god_turn = og.ord_god_turn
                                  AND  cc.GOD_CNNC_TP_CD = 'DLIVY_GOD_CNNC' 
                  )
                  AND lp.dlv_pcupsp_sect_cd = 'ORD_DLVSP'
                  
                   
                  
                  
                  
                     AND od.mall_id =?
                  
                   
                    AND od.lang_cd =?
                  
                   
                         
                  
                       
                          
                                  
                   
                         
                    
                         
                     
                                      
                                         
                                           
 
                   
                    AND ord_dt BETWEEN  TO_DATE(?,'yyyy-mm-dd') AND TO_DATE(?,'yyyy-mm-dd')+1-(1/24/60/60)
                  
                                         
                           
                  
				                  
    
						ORDER BY od.ord_no DESC
				
					  
		 
			) a 
    			WHERE  ROWNUM <= :10 ) b      
			WHERE  NO >= ?
	 	 
	 
			   ) b	
			   JOIN sys_mall sm ON b.mall_id = sm.mall_id
			   JOIN mv_sys_cd la ON b.lang_cd = la.cd AND la.lang_cd ='KOR'  AND la.upper_cd='LANG'
			   JOIN mv_sys_cd gt ON b.god_tp_cd = gt.cd AND gt.lang_cd ='KOR'  AND gt.upper_cd='GOD_TP'
			   JOIN mv_sys_cd ot ON b.ord_tp_cd = ot.cd AND ot.lang_cd ='KOR'  AND ot.upper_cd='ORD_TP'
			   JOIN mv_sys_cd os ON b.ord_stat_cd = os.cd AND os.lang_cd ='KOR'  AND os.upper_cd='ORD_STAT' 
			   JOIN mv_sys_cd dv ON b.dvc_cd = dv.cd AND  dv.lang_cd = 'KOR' AND dv.upper_cd = 'DVC'
			   JOIN sys_brnd sb ON b.brnd_id = sb.brnd_id
			   LEFT OUTER JOIN sys_exchg_rt ser ON ser.crncy_cd = b.crncy_cd AND ser.exchg_rt_apl_beg_dt = b.exchg_rt_apl_beg_dt
			   LEFT OUTER JOIN mv_sys_cd dc ON b.dlv_com_cd = dc.cd AND dc.lang_cd ='KOR'  AND dc.upper_cd='DLV_COM'
			   LEFT OUTER JOIN com com ON b.aff_com_id = com.com_id
			   LEFT OUTER JOIN mbr mbr ON b.mbr_no = mbr.mbr_no
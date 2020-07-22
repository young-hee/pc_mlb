package com.plgrim.ncp.commons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.sys.SysCd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysCdLang;
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpCdCdCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysGrpCdExtend;
import com.plgrim.ncp.commons.data.FormSysCodeDTO;
import com.plgrim.ncp.commons.repository.SysCodeRepository;
import com.plgrim.ncp.commons.result.SysCodeResult;

@Service
public class SysCodeService extends AbstractService {

	@Autowired
	SysCodeRepository sysCodeRepository;
	
	/**
	 * 공통그룹코드 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysCodeResult> selectListCdGrp( FormSysCodeDTO paramData) throws Exception {
		
		if( "3".equals( paramData.getSearchField() ) || "4".equals( paramData.getSearchField() ) ){
			//공통코드명, 공통코드로 검색시 공통그룹코드를 조회한다.
			List<String> listGrpCd = sysCodeRepository.selectGrpCds(paramData);
			
			if( listGrpCd.size() == 0 ){
			
					return null;
			}
			
			paramData.setSearchListGrpCd( listGrpCd );
			
		}
		
		return sysCodeRepository.selectListCdGrp(paramData);
	}

	/**
	 * 공통그룹코드 상세코드 목록 조회
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public List<SysCodeResult> selectListCdGrpDetail( FormSysCodeDTO paramData) throws Exception {
		return sysCodeRepository.selectListCdGrpDetail(paramData);
	}

	/**
	 * SysCd 코드 사용 유/무
	 * @param grpCd
	 * @return
	 * @throws Exception
	 */
	public boolean isUseFromSysCd ( String cd ) throws Exception {
		return sysCodeRepository.isUseFromSysCd(cd);
	}
	
	/**
	 * 공통그룹코드를 등록한다.
	 * @param paramData
	 * @throws Exception
	 */
	public void insertSysGrpCd ( SysGrpCdExtend paramData ) throws Exception{
		
		if( isUseFromSysCd( paramData.getGrpCd())){
			throw new Exception("사용하고 있는 공통그룹코드입니다.");
		}

		//공통코드 등록
		this.mergeSysCd( paramData.getGrpCd(), paramData.getGrpNm());
		
		if( !"".equals(paramData.getEngGrpNm()) ){
			//영문명 등록
			this.mergeSysCdLangEng(paramData.getGrpCd(), paramData.getEngGrpNm());
		}
		
		if( !"".equals(paramData.getChiGrpNm()) ){
			//중국어 등록
			this.mergeSysCdLangChi(paramData.getGrpCd(), paramData.getChiGrpNm());
		}
		
		//공통그룹코드 등록
		sysCodeRepository.insertSysGrpCd(paramData);
		
		//mview refresh
		sysCodeRepository.refreshMview();
	}
	
	/**
	 * 공통그룹코드를 수정한다.
	 * @param paramData
	 * @throws Exception
	 */
	public void updateSysGrpCd ( SysGrpCdExtend paramData ) throws Exception{
		
		SysGrpCdExtend rowData = sysCodeRepository.selectRowGrpCd( paramData.getGrpCd());
		
		if( rowData != null){
			
			//코드명칭 및 설명 변경시
			if( paramData.getGrpNm() != null && paramData.getCdDscr() != null){
				if( !paramData.getGrpNm().equals( rowData.getGrpNm()) || !paramData.getCdDscr().equals( rowData.getCdDscr()) ){
					this.mergeSysCd( paramData.getGrpCd(), paramData.getGrpNm());
				}
			}
			
			//영문명칭 변경시
			//if( paramData.getEngGrpNm() != null && !"".equals(paramData.getEngGrpNm())){
				if( !paramData.getEngGrpNm().equals(rowData.getEngGrpNm()) ){
					this.mergeSysCdLangEng(paramData.getGrpCd(), paramData.getEngGrpNm());
				}
			//}
			
			//중국명칭 변경시
			//if( paramData.getChiGrpNm() != null && !"".equals(paramData.getChiGrpNm())){
				if( !paramData.getEngGrpNm().equals(rowData.getChiGrpNm()) ){
					this.mergeSysCdLangChi(paramData.getGrpCd(), paramData.getChiGrpNm());
				}
			//}
			
			//공통그룹코드 변경
			sysCodeRepository.updateSysGrpCd(paramData);
			
			//mview refresh
			sysCodeRepository.refreshMview();
		}
	}
	
	
	/**
	 * 공통그룹 상세코드 등록.
	 * @param paramData
	 * @throws Exception
	 */
	public void insertSysGrpCdDetail ( SysGrpCdCdCnncExtend paramData ) throws Exception{
		
		if( isUseFromSysCd( paramData.getCd())){
			throw new Exception("사용하고 있는 공통코드입니다.");
		}

		//공통코드 등록
		this.mergeSysCd( paramData.getCd(), paramData.getCdNm());
		
		if( !"".equals(paramData.getEngCdNm()) ){
			//영문명 등록
			this.mergeSysCdLangEng(paramData.getCd(), paramData.getEngCdNm());
		}
		
		if( !"".equals(paramData.getChiCdNm()) ){
			//중국어 등록
			this.mergeSysCdLangChi(paramData.getCd(), paramData.getChiCdNm());
		}
		
		//공통그룹코드 등록
		sysCodeRepository.insertSysGrpCdCdCnnc(paramData);
		
		//mview refresh
		sysCodeRepository.refreshMview();
	}
	
	/**
	 * 공통그룹 상세코드 수정
	 * @param paramData
	 * @throws Exception
	 */
	public void updateSysGrpCdDetail ( SysGrpCdCdCnncExtend paramData ) throws Exception{
		
		SysGrpCdCdCnncExtend rowData = sysCodeRepository.selectRowCdGrpDetail( paramData.getGrpCd() , paramData.getCd());
		
		if( rowData != null){
			
			//코드명칭 및 설명 변경시
			if( paramData.getCdNm() != null && paramData.getCdDscr() != null){
				if( !paramData.getCdNm().equals( rowData.getCdNm()) || !paramData.getCdDscr().equals( rowData.getCdDscr()) ){
					this.mergeSysCd( paramData.getCd(), paramData.getCdNm());
				}
			}
			
			//영문명칭 변경시
			if( paramData.getEngCdNm() != null && !"".equals(paramData.getEngCdNm())){
				if( !paramData.getEngCdNm().equals(rowData.getEngCdNm()) ){
					this.mergeSysCdLangEng(paramData.getCd(), paramData.getEngCdNm());
				}
			}
			
			//중국명칭 변경시
			if( paramData.getChiCdNm() != null && !"".equals(paramData.getChiCdNm())){
				if( !paramData.getChiCdNm().equals(rowData.getChiCdNm()) ){
					this.mergeSysCdLangChi(paramData.getCd(), paramData.getChiCdNm());
				}
			}
			
			//공통그룹코드 변경
			sysCodeRepository.updateSysGrpCdCdCnnc(paramData);
			
			//mview refresh
			sysCodeRepository.refreshMview();
		}
		
		
	}
	
	/*###################################################################################################
	 * 
	 * PRIMARY Function
	 ###################################################################################################*/
	
	/**
	 * 중국어 코드명 등록/수정 처리
	 * @param paramData
	 * @throws Exception
	 */
	private void mergeSysCdLangChi ( String cd, String cdNm ) throws Exception{
		
		SysCdLang sysCdLang = new SysCdLang();
		sysCdLang.setCd( cd  );
		sysCdLang.setLangCd("CHI");
		sysCdLang.setLangbyCdNm( cdNm);
		sysCodeRepository.mergeSysCdLang(sysCdLang);
	}
	
	/**
	 * 영어 코드명 등록/수정 처리
	 * @param cd
	 * @param cdNm
	 * @throws Exception
	 */
	private void mergeSysCdLangEng ( String cd, String cdNm ) throws Exception{
		
		SysCdLang sysCdLang = new SysCdLang();
		sysCdLang.setCd( cd  );
		sysCdLang.setLangCd("ENG");
		sysCdLang.setLangbyCdNm( cdNm);
		sysCodeRepository.mergeSysCdLang(sysCdLang);
	}
	/**
	 * base 공통코드 등록/수정 처리
	 * @param paramData
	 * @throws Exception
	 */
	private void mergeSysCd (String cd, String cdNm ) throws Exception{
		
		SysCd sysCd = new SysCd();
		sysCd.setCd( cd );
		sysCd.setCdNm( cdNm );
//		sysCd.setCdDscr( cdDscr);
		
		sysCodeRepository.mergeSysCd(sysCd);
	}
	
}

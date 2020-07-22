package com.plgrim.ncp.commons.auth;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

//import lombok.Data;


import com.plgrim.ncp.base.entities.datasource1.sys.*;
import org.springframework.beans.factory.annotation.Value;

import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.commons.result.AuthResult;
import com.plgrim.ncp.commons.result.SysAdminBukmkResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

import lombok.EqualsAndHashCode;

/**
 * BO 사용자 상세 정보.
 */
//@Data
@EqualsAndHashCode(of="sysAdmin")
public class BOSecurityUserDetail implements Serializable{
	
	/**
	 * UID
	 */
    private static final long serialVersionUID = -2343476483916844524L;
	
    //==========================================================================
    // BO용 변수
    //==========================================================================

    /*관리자 엔티티*/
    SysAdmin sysAdmin;
    
    Com com ;
    SysShop sysShop;

    /* 기타 파라미터 맵*/
    Map<String, String> parameterMap;
    
    /*MD 관리 브랜드정보*/
    List<SysBrnd> mdBrndList;
    
    /*
     * 권한 접근 가능 브랜드 목록 정보 ( null이면 모두 가능 )
     */
    List<SysBrnd> authBrndList;
    
    /*
     * 권한 접근 가능 po 브랜드 목록 정보 ( null이면 접근 불가 )
     */
    List<SysBrnd> poAuthBrndList;

    /*
     * 권한 접근 가능 몰 목록 정보
     */
    List<SysMall> authMallList;

    //권한 - <메뉴URL,기능> 정보
    Map<String, Object> authUrlAct;
    
    //권한 - <메뉴URL,기능> 정보  ( 동적 메뉴  )
    List<AuthResult> dynamicAuthUrlAct;
    
    //권한 - <메뉴URL,기능> 정보 (full url 권한체크 메뉴) 
    List<AuthResult> authCheckUrlAct;

    /*
     * 접근한 site_id
     */
    String accessSiteId;
    
    
    /**
     * 로그인 시간
     */
    java.util.Date loginDt;
    
    /**
     * 권한그룹별 top 메뉴 리스트
     */
    List<SysMenuExtend> topMenuList; 
    
    /**
     * CS 개인정보 동의여부 
     */
    String stplatAgrYn;
    
    /**
     * 운영자 소속 업체 유형
     */
    String admComTpCd;    
    /**
     * 운영자 소속 업체명
     */
    String admComNm;       
    
    /**
     * 즐겨찾기 목록 
     */
    List<SysAdminBukmkResult> bookmarkList;
    
    //==========================================================================
    // PO용 변수
    //==========================================================================
    /*관리자 엔티티*/
    SysAdmin poSysAdmin;

    Com poCom ;
    SysShop poSysShop;

    /* 기타 파라미터 맵*/
    Map<String, String> poParameterMap;

    /*MD 관리 브랜드정보*/
    List<SysBrnd> poMdBrndList;

    /*
     * 권한 접근 가능 브랜드 목록 정보 ( null이면 모두 가능 )
     */
    List<SysBrnd> poAuthBrndList2;

    /*
     * 권한 접근 가능 po 브랜드 목록 정보 ( null이면 접근 불가 )
     */
    List<SysBrnd> poPoAuthBrndList2;

    //권한 - <메뉴URL,기능> 정보
    Map<String, Object> poAuthUrlAct;

    //권한 - <메뉴URL,기능> 정보 ( 동적 메뉴  )
    List<AuthResult> poDynamicAuthUrlAct;
    
    //권한 - <메뉴URL,기능> 정보 (full url 권한체크 메뉴) 
    List<AuthResult> poAuthCheckUrlAct;

    /*
     * 접근한 site_id
     */
    String poAccessSiteId;


    /**
     * 로그인 시간
     */
    java.util.Date poLoginDt;

    /**
     * 권한그룹별 top 메뉴 리스트
     */
    List<SysMenuExtend> poTopMenuList;

    /**
     * CS 개인정보 동의여부
     */
    String poStplatAgrYn;

    /**
     * 운영자 소속 업체 유형
     */
    String poAdmComTpCd;
    /**
     * 운영자 소속 업체명
     */
    String poAdmComNm;

    /**
     * 즐겨찾기 목록
     */
    List<SysAdminBukmkResult> poBookmarkList;

    String poManualUri;

    /**
     * 아래의 Getter 는 기존 bo / po 의 호환성 때문에 생성 (po요청인데 bo값 대입 방지)
     * po 요청은 별도 po prefix 붙은 getter 대입된다.
     */
    public SysAdmin getSysAdmin() {
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? sysAdmin : poSysAdmin );
    }

    public Com getCom(){
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? com : poCom );
    }

    public SysShop getSysShop(){
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? sysShop : poSysShop );
    }

    public Map<String, String> getParameterMap(){
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? parameterMap : poParameterMap );
    }

    public List<SysBrnd> getMdBrndList() {
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? mdBrndList : poMdBrndList );
    }

    public List<SysBrnd> getAuthBrndList() {
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? authBrndList : poAuthBrndList2 );
    }

    public List<SysBrnd> getPoAuthBrndList() {
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? poAuthBrndList : poPoAuthBrndList2 );
    }

    public List<SysMall> getAuthMallList() {
        return authMallList;
    }

    public Map<String, Object> getAuthUrlAct() {
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? authUrlAct : poAuthUrlAct );
    }

    public List<AuthResult> getDynamicAuthUrlAct() {
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? dynamicAuthUrlAct : poDynamicAuthUrlAct );
    }
    
    public List<AuthResult> getAuthCheckUrlAct() {
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? authCheckUrlAct : poAuthCheckUrlAct );
    }

    public String getAccessSiteId() {
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? accessSiteId : poAccessSiteId );
    }

    public java.util.Date getLoginDt() {
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? loginDt : poLoginDt );
    }

    public List<SysMenuExtend> getTopMenuList() {
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? topMenuList : poTopMenuList );
    }

    public String getStplatAgrYn() {
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? stplatAgrYn : poStplatAgrYn );
    }

    public String getAdmComTpCd() {
    	debugGetterLog();
    	return ( BOSecurityUtil.isBoSite() ? admComTpCd : poAdmComTpCd );
    }

    public String getAdmComNm() {
    	debugSetterLog();
    	return ( BOSecurityUtil.isBoSite() ? admComNm : poAdmComNm );
    }

    public List<SysAdminBukmkResult> getBookmarkList() {
    	debugSetterLog();
    	return ( BOSecurityUtil.isBoSite() ? bookmarkList : poBookmarkList );
    }

    public void setSysAdmin(SysAdmin data) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.sysAdmin = data;
    	}else{
    		this.poSysAdmin = data;
    	}
    }

    public void setCom(Com data){
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.com = data;
    	}else{
    		this.poCom = data;
    	}
    }

    public void setSysShop(SysShop data){
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.sysShop = data;
    	}else{
    		this.poSysShop = data;
    	}
    }

    public void setParameterMap(Map<String, String> lists){
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.parameterMap = lists;
    	}else{
    		this.poParameterMap = lists;
    	}
    }

    public void setMdBrndList(List<SysBrnd> lists) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.mdBrndList = lists;
    	}else{
    		this.poMdBrndList = lists;
    	}
    }

    public void setAuthBrndList(List<SysBrnd> lists) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.authBrndList = lists;
    	}else{
    		this.poAuthBrndList2 = lists;
    	}
    }

    public void setPoAuthBrndList(List<SysBrnd> lists) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.poAuthBrndList = lists;
    	}else{
    		this.poPoAuthBrndList2 = lists;
    	}
    }

    public void setAuthMallList(List<SysMall> authMallList) {
        this.authMallList = authMallList;
    }

    public void setAuthUrlAct(Map<String, Object> lists) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.authUrlAct = lists;
    	}else{
    		this.poAuthUrlAct = lists;
    	}
    }

    public void setDynamicAuthUrlAct(List<AuthResult> lists) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.dynamicAuthUrlAct = lists;
    	}else{
    		this.poDynamicAuthUrlAct = lists;
    	}
    }
    
    public void setAuthCheckUrlAct(List<AuthResult> lists) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.authCheckUrlAct = lists;
    	}else{
    		this.poAuthCheckUrlAct = lists;
    	}
    }

    public void setAccessSiteId(String data) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.accessSiteId = data;
    	}else{
    		this.poAccessSiteId = data;
    	}
    }

    public void setLoginDt(java.util.Date data) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.loginDt = data;
    	}else{
    		this.poLoginDt = data;
    	}
    }

    public void setTopMenuList(List<SysMenuExtend> lists) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.topMenuList = lists;
    	}else{
    		this.poTopMenuList = lists;
    	}
    }

    public void setStplatAgrYn(String data) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.stplatAgrYn = data;
    	}else{
    		this.poStplatAgrYn = data;
    	}
    }

    public void setAdmComTpCd(String data) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.admComTpCd = data;
    	}else{
    		this.poAdmComTpCd = data;
    	}
    }

    public void setAdmComNm(String data) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.admComNm = data;
    	}else{
    		this.poAdmComNm = data;
    	}
    }

    public void setBookmarkList(List<SysAdminBukmkResult> lists) {
    	debugSetterLog();
    	if(BOSecurityUtil.isBoSite()){
    		this.bookmarkList = lists;
    	}else{
    		this.poBookmarkList = lists;
    	}
    }

    public void debugGetterLog() {
    	//if(BOSecurityUtil.isPoSite()){
    	//}
    }

    public void debugSetterLog() {
    	//if(BOSecurityUtil.isPoSite()){
    	//}
    }

    //--------------------------------------------------------------------------

    public SysAdmin getPoSysAdmin() {
    	return poSysAdmin;
    }

    public Com getPoCom(){
    	return poCom;
    }

    public SysShop getPoSysShop(){
    	return poSysShop;
    }

    public Map<String, String> getPoParameterMap(){
    	return poParameterMap;
    }

    public List<SysBrnd> getPoMdBrndList() {
    	return poMdBrndList;
    }

    public List<SysBrnd> getPoAuthBrndList2() {
    	return poAuthBrndList2;
    }

    public List<SysBrnd> getPoPoAuthBrndList2() {
    	return poPoAuthBrndList2;
    }

    public Map<String, Object> getPoAuthUrlAct() {
    	return poAuthUrlAct;
    }

    public List<AuthResult> getPoDynamicAuthUrlAct() {
    	return poDynamicAuthUrlAct;
    }
    
    public List<AuthResult> getPoAuthCheckUrlAct() {
    	return poAuthCheckUrlAct;
    }

    public String getPoAccessSiteId() {
    	return poAccessSiteId;
    }

    public java.util.Date getPoLoginDt() {
    	return poLoginDt;
    }

    public List<SysMenuExtend> getPoTopMenuList() {
    	return poTopMenuList;
    }

    public String getPoStplatAgrYn() {
    	return poStplatAgrYn;
    }

    public String getPoAdmComTpCd() {
    	return poAdmComTpCd;
    }

    public String getPoAdmComNm() {
    	return poAdmComNm;
    }

    public List<SysAdminBukmkResult> getPoBookmarkList() {
    	return poBookmarkList;
    }

    public String getPoManualUri() {
    	return poManualUri;
    }

    //--------------------------------------------------------------------------

    public void setPoSysAdmin(SysAdmin data) {
    	this.poSysAdmin = data;
    }

    public void setPoCom(Com data){
    	this.poCom = data;
    }

    public void setPoSysShop(SysShop data){
    	this.poSysShop = data;
    }

    public void setPoParameterMap(Map<String, String> lists){
    	this.poParameterMap = lists;
    }

    public void setPoMdBrndList(List<SysBrnd> lists) {
    	this.poMdBrndList = lists;
    }

    public void setPoAuthBrndList2(List<SysBrnd> lists) {
    	this.poAuthBrndList2 = lists;
    }

    public void setPoPoAuthBrndList2(List<SysBrnd> lists) {
    	this.poPoAuthBrndList2 = lists;
    }

    public void setPoAuthUrlAct(Map<String, Object> lists) {
    	this.poAuthUrlAct = lists;
    }

    public void setPoDynamicAuthUrlAct(List<AuthResult> lists) {
    	this.poDynamicAuthUrlAct = lists;
    }
    
    public void setPoAuthCheckUrlAct(List<AuthResult> lists) {
    	this.poAuthCheckUrlAct = lists;
    }

    public void setPoAccessSiteId(String data) {
    	this.poAccessSiteId = data;
    }

    public void setPoLoginDt(java.util.Date data) {
    	this.poLoginDt = data;
    }

    public void setPoTopMenuList(List<SysMenuExtend> lists) {
    	this.poTopMenuList = lists;
    }

    public void setPoStplatAgrYn(String data) {
    	this.poStplatAgrYn = data;
    }

    public void setPoAdmComTpCd(String data) {
    	this.poAdmComTpCd = data;
    }

    public void setPoAdmComNm(String data) {
    	this.poAdmComNm = data;
    }

    public void setPoBookmarkList(List<SysAdminBukmkResult> lists) {
    	this.poBookmarkList = lists;
    }

    public void setPoManualUri(String uri) {
    	this.poManualUri = uri;
    }
}

package com.plgrim.ncp.commons.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource3.clm.ClmBnkAcctNoEncrtKey;
import com.plgrim.ncp.base.entities.datasource3.com.ComBnkAcctNoEncrtKey;
import com.plgrim.ncp.base.entities.datasource3.mbr.MbrCookieEncrtKey;
import com.plgrim.ncp.base.repository.clm.ClmBnkAcctNoEncrtKeyRepository;
import com.plgrim.ncp.base.repository.com.ComBnkAcctNoEncrtKeyRepository;
import com.plgrim.ncp.base.repository.mbr.MbrCookieEncrtKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "transactionManager3")
public class SysCommonService extends AbstractService {

	@Autowired
	ClmBnkAcctNoEncrtKeyRepository clmBnkAcctNoEncrtKeyRepository;
	
	@Autowired
	ComBnkAcctNoEncrtKeyRepository comBnkAcctNoEncrtKeyRepository;

	@Autowired
	MbrCookieEncrtKeyRepository mbrCookieEncrtKeyRepository;

	public ClmBnkAcctNoEncrtKey getPrivateKeyForCryption(ClmBnkAcctNoEncrtKey key) throws Exception {
	    return clmBnkAcctNoEncrtKeyRepository.selectClmBnkAcctNoEncrtKey(key);
    }

	public void setPrivateKeyForCryption(ClmBnkAcctNoEncrtKey key) throws Exception{
		clmBnkAcctNoEncrtKeyRepository.insertClmBnkAcctNoEncrtKey(key);
    }
	
	public void putPrivateKeyForCryption(ClmBnkAcctNoEncrtKey key) throws Exception{
		clmBnkAcctNoEncrtKeyRepository.updateClmBnkAcctNoEncrtKey(key);
    }
	
	
//	public ClmBnkAcctNoEncrtKey getPrivateKeyForCryptionFrgnrId(ClmBnkAcctNoEncrtKey key) throws Exception {
//	    return clmBnkAcctNoEncrtKeyRepository.selectFrgnrIdEncrtKey(key);
//    }
//
//	public void setPrivateKeyForCryptionFrgnrId(ClmBnkAcctNoEncrtKey key) throws Exception{
//		clmBnkAcctNoEncrtKeyRepository.insertFrgnrIdEncrtKey(key);
//    }
//
//	public void putPrivateKeyForCryptionFrgnrId(ClmBnkAcctNoEncrtKey key) throws Exception{
//		clmBnkAcctNoEncrtKeyRepository.updateFrgnrIdEncrtKey(key);
//    }

	public MbrCookieEncrtKey selectMbrCookieEncrtKey(MbrCookieEncrtKey mbrCookieEncrtKey) throws Exception{
		return mbrCookieEncrtKeyRepository.selectMbrCookieEncrtKey(mbrCookieEncrtKey);
	}
	
	public ComBnkAcctNoEncrtKey selectComBnkAcctNoEncrtKey(ComBnkAcctNoEncrtKey comBnkAcctNoEncrtKey) throws Exception {
	    return comBnkAcctNoEncrtKeyRepository.selectComBnkAcctNoEncrtKey(comBnkAcctNoEncrtKey);
    }

	public void insertComBnkAcctNoEncrtKey(ComBnkAcctNoEncrtKey comBnkAcctNoEncrtKey) throws Exception {
		comBnkAcctNoEncrtKeyRepository.insertComBnkAcctNoEncrtKey(comBnkAcctNoEncrtKey);
    }

}

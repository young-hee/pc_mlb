package com.plgrim.ncp.cmp.member.fo.auth;

import com.plgrim.ncp.base.entities.datasource3.mbr.MbrCookieEncrtKey;
import com.plgrim.ncp.base.repository.mbr.MbrCookieEncrtKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.framework.security.KeyProvider;

/**
 * Created by Yoon on 2016-05-23.
 */
@Component
public class SecurityKeyProvider implements KeyProvider {

    @Autowired
    MbrCookieEncrtKeyRepository mbrCookieEncrtKeyRepository;

    @Override
    public String getKey() throws Exception {
        MbrCookieEncrtKey mbrCookieEncrtKey = mbrCookieEncrtKeyRepository.selectMbrCookieEncrtKey(new MbrCookieEncrtKey());
        return mbrCookieEncrtKey.getEncrtPsnlKey();
    }

}

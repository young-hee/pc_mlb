package com.plgrim.ncp.biz.callcenter.service;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltTrans;
import com.plgrim.ncp.base.repository.cso.CsoCnsltTransRepository;
import com.plgrim.ncp.biz.callcenter.data.CounselTransferDTO;
import com.plgrim.ncp.biz.callcenter.repository.CounselTransferRepository;
import com.plgrim.ncp.biz.callcenter.repository.CsoCnsltTransEntityRepository;
import com.plgrim.ncp.biz.callcenter.repository.CsoCnsltTransHistEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2015-04-11.
 */

@Service
public class CounselTransferService {


    @Autowired
    private CounselTransferRepository counselTransferRepository;

    @Autowired
    private CsoCnsltTransEntityRepository csoCnsltTransEntityRepository;

    /*@Autowired
    private CsoCnsltTransHistEntityRepository csoCnsltTransHistEntityRepository;*/


    public void insertCounselTransfer(CounselTransferDTO counselTransferDTO) throws Exception{
        //업무 이관 등록
        csoCnsltTransEntityRepository.insertCsoCnsltTrans(counselTransferDTO.getCsoCnsltTrans());
    }

    public void insertCounselTransferHist(CounselTransferDTO counselTransferDTO) throws Exception{
        // 업무 이관 이력 등록
        //csoCnsltTransHistEntityRepository.insertCsoCnsltTransHist(counselTransferDTO.getCsoCnsltTransHist());
    }

    public void updateCounselTransfer(CsoCnsltTrans csoCnsltTrans) {
        counselTransferRepository.updateCounselTransfer(csoCnsltTrans);
    }
}

package com.plgrim.ncp.commons.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.commons.data.DspRevDTO;
import com.plgrim.ncp.commons.features.FeatureUtils;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.utils.RemoteAddrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.togglz.core.Feature;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Repository
public class DisplayRevRepository extends AbstractRepository {

    @Value("${ncp_base.valid.ip.row}")
    private String  validIpLow;

    @Value("${ncp_base.valid.ip.high}")
    private String  validIpHigh;

    /**
     * AB Test - user에게 보여줄 개정순번 조회
     * @param searchDTO
     * @return
     */
    public DspRevDTO getRevSnResult(DspRevDTO dspCnrScFrDTO) throws Exception {
        //AB Test Step1. 공통 Framework로부터 가능한 feature list 획득
        Set<Feature> featureList = FeatureUtils.featureWithABTest();
        List<String> revSnList = new ArrayList<String>();

        for(Feature o: featureList){
            String[] arrName = o.name().split("_");

            if(dspCnrScFrDTO.getTmplatScKey().equals(arrName[2])){
                boolean isActive = FeatureUtils.isActive(o.name());
                log.debug("AB Test >>>>> Ctgry No : {}, revSn : {}, isActive : {}", arrName[2], arrName[4], isActive);

                if(isActive){
                    revSnList.add(arrName[4]);
                }
            }
        }

        String dvcSectCd = getIdGenService().getDevice(ContextService.getCurrentRequest());  //device 구분
        if("MOBILE_WEB".equals(dvcSectCd) || "MOBILE_APP".equals(dvcSectCd)){
            dvcSectCd = "MOBILE";
        }

        String revSnSet = StringUtils.join(revSnList.toArray(), ",");

        //AB Test Step2. Procedure를 호출하여 개정번호(REV_SN) 획득
        DspRevDTO dspRevDTO = new DspRevDTO();
        dspRevDTO.setPgeSect(dspCnrScFrDTO.getTmplatScKey());	//전시페이지 구분값(카테고리번호, 기획전번호, 이벤트번호..)
        dspRevDTO.setDspBrndId(dspCnrScFrDTO.getTmplatKeyBrndId());	//전시 브랜드 아이디
        dspRevDTO.setRevSnSet(revSnSet);
        dspRevDTO.setDvcSectCd(dvcSectCd);

//        getSession1().selectOne("com.plgrim.ncp.commons.rev.selectPcGetRevSn", dspRevDTO);

//        log.debug("AB Test >>>>> feature list size : {}, pageSect : {}, dspBrndId : {}, revSnSet : {}, result revSn : {}, abtestSn : {}", featureList.size(), dspCnrScFrDTO.getTmplatScKey(), dspCnrScFrDTO.getTmplatKeyBrndId(), revSnSet, dspRevDTO.getDspRevCpst().getRevSn(), dspRevDTO.getAbTestSn());

        return dspRevDTO;
    }

    /**
     * AB Test 미리보기시 IP 체크(일반사용자의 미리보기 접근 제어)
     * @return
     * @throws Exception
     */
    public boolean isPsbPreviewIP() throws Exception {
        boolean result = false;

        // 도곡 IP 대역(71.51.0.0 ~ 71.80.255.255)
        long ipLow = ipToLong(InetAddress.getByName(validIpLow));
        long ipHigh = ipToLong(InetAddress.getByName(validIpHigh));

        String strReqIpAddr = RemoteAddrUtil.getRemoteAddr(ContextService.getCurrentRequest());
        long reqIpAddr = ipToLong(InetAddress.getByName(strReqIpAddr));

        if(reqIpAddr >= ipLow && reqIpAddr <= ipHigh){
            result = true;
        }

        log.debug("AB Test >>>>> reqAddr : {}, ipLow : {}, ipHigh : {}, reqIP : {}, result : {}", strReqIpAddr, ipLow, ipHigh, reqIpAddr, result);

        return result;
    }

    /**
     * IP Address를 Long형으로 반환
     * @param ip
     * @return
     */
    private long ipToLong(InetAddress ip) {
        byte[] octets = ip.getAddress();
        long result = 0;
        for(byte o: octets){
            result <<= 8;
            result |= o & 0xff;
        }
        return result;
    }
}

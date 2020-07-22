package com.plgrim.ncp.biz.display.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspConttImg;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspImgUploadDTO extends AbstractDTO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
    private String loginId;
    /** 전시 컨텐츠 이미지 엔티티. */
    private DspConttImg dspConttImg = new DspConttImg();
    
    /** 첨부 파일. */
    private List<MultipartFile> uploadImage;

    /** 이벤트번호 (정기이벤트용 이미지경로) */
    private String evtNo;
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
    @Override
    public void setLoginId(String loginId) {

        this.loginId = loginId;

        dspConttImg.setRegtrId(loginId);
        dspConttImg.setUdterId(loginId);

    }
}

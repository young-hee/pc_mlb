package com.plgrim.ncp.commons.result;

import lombok.Data;

import java.io.Serializable;

/**
 * CKEditor Upload Result
 */
@Data
public class CKEditorUploadResult {
    @Data
    public class CKEditorUploadError implements Serializable {
        private String message;
    }

    private int uploaded;
    private String fileName;
    private String url;
    private CKEditorUploadError error;

    public void setUploadError(String errorMessage) {
        this.uploaded = 0;
        this.url = null;
        this.fileName = null;
        this.error = new CKEditorUploadError();
        this.error.message = errorMessage;
    }
}

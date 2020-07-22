package com.plgrim.ncp.framework.interceptor.data;


import com.plgrim.ncp.base.abstracts.AbstractEntity;
import com.plgrim.ncp.framework.annotation.MaskingFormat;
import lombok.Data;

@Data
public class Holdee extends AbstractEntity {

    private static final long serialVersionUID = 634426591484699595L;

    @MaskingFormat(regexPattern = "^(.{2})(.*{1,})", replacePattern = "$1******")
    protected String holdeeName;

}

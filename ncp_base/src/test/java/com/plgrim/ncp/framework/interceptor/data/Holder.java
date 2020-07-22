package com.plgrim.ncp.framework.interceptor.data;

import com.plgrim.ncp.framework.annotation.MaskingFormat;
import lombok.Data;

import java.util.List;

@Data
public class Holder  {

    private static final long serialVersionUID = 634426591484699595L;

    @MaskingFormat(regexPattern = "^(.{2})(.*{1,})", replacePattern = "$1******")
    protected String holderName;

    protected Holdee holdee;

    protected List<Holdee> holdees;

}

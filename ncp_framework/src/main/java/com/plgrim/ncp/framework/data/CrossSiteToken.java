package com.plgrim.ncp.framework.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrossSiteToken {
    String mbrId;
    Long lastAccessTimestamp;

    public boolean isExpired(long now, long expireMilis) {
        if (lastAccessTimestamp == null) {
            return true;
        }

        return (now - lastAccessTimestamp) > expireMilis;
    }

    public void updateLastAccessTimestamp(long now) {
        lastAccessTimestamp = now;
    }
}
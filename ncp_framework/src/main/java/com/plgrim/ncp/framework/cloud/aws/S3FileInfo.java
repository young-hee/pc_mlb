package com.plgrim.ncp.framework.cloud.aws;

import lombok.Data;

/**
 * entity for S3File information
 *
 * @author Park Chulhui <charles@plgrim.com>
 */
@Data
public class S3FileInfo {
    private String bucketName;
    private String path;
}

package com.plgrim.ncp.framework.cloud.aws;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CloseIgnoreBufferedInputStream extends BufferedInputStream {


    public CloseIgnoreBufferedInputStream(InputStream in) {
        super(in);
    }

    public CloseIgnoreBufferedInputStream(InputStream in, int size) {
        super(in, size);
    }


    @Override
    public void close() throws IOException {

    }
}

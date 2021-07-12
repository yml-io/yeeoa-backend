package com.yeeoa.extract;

import com.yeeoa.bean.Asset;

import java.io.IOException;

// base interface for create class to process variety asset file user upload
public interface ExtractBase {
    void setContent(Asset asset);
    Object doProcess() throws IOException;
}

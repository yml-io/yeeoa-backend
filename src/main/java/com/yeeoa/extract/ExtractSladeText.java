package com.yeeoa.extract;

import com.yeeoa.bean.Asset;
//import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.xslf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ExtractSladeText implements ExtractBase{
    private static final Logger logger = LoggerFactory.getLogger(ExtractSladeText.class);

    private Asset asset;

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    @Override
    public void setContent(Asset asset) {
        setAsset(asset);
    }

    @Override
    public Object doProcess() throws IOException {
        File fileObj = new File(getAsset().getUrl());
        InputStream fileInputStream = new FileInputStream(fileObj);
        XMLSlideShow ppt = new XMLSlideShow(fileInputStream);
        StringBuilder sb = new StringBuilder();

        // iterate over all shape in this ppt file
        for (XSLFSlide slide : ppt.getSlides()) {
            for (XSLFShape sh : slide.getShapes()) {
                if (sh instanceof XSLFTextShape) {
                    XSLFTextShape shape = (XSLFTextShape) sh;
                    sb.append(shape.getText());
                    // work with a shape that can hold text
                }
                // shapes's anchor which defines the position of this shape in the slide
//                if (sh instanceof PlaceableShape) {
//                    java.awt.geom.Rectangle2D anchor = ((PlaceableShape)sh).getAnchor();
//                }
//                if (sh instanceof XSLFConnectorShape) {
//                    XSLFConnectorShape line = (XSLFConnectorShape) sh;
//                    // work with Line
//                } else if (sh instanceof XSLFTextShape) {
//                    XSLFTextShape shape = (XSLFTextShape) sh;
//                    logger.info(shape.getText());
//                    // work with a shape that can hold text
//                } else if (sh instanceof XSLFPictureShape) {
//                    XSLFPictureShape shape = (XSLFPictureShape) sh;
//                    // work with Picture
//                }
            }
        }
        return sb.toString();
    }
}

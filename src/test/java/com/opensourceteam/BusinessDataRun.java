package com.opensourceteam;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 开发人:刘文
 * 日期:  2017/7/20.
 * 功能描述:
 */
public class BusinessDataRun {

    Logger LOG_BUSINESS_1_LOG = LoggerFactory.getLogger("BUSINESS_1_LOG");
    Logger logger = LoggerFactory.getLogger(BusinessDataRun.class);
    @Test
    public void log(){
        logger.info("这是info信息");
        logger.debug("debug");
        logger.error("error信息");
        LOG_BUSINESS_1_LOG.info("LOG_BUSINESS_1_LOG info");
    }
}

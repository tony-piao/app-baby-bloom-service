package com.babybloom.web.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtility {
    private static final String BUSINESS_LOG_NAME = "spes.businessLog";
    private static final String ERROR_LOG_NAME = "spes.errorLog";

    /**
     * 业务日志类
     *
     * @return
     */
    public static Logger businessLog() {
        return LogManager.getLogger(BUSINESS_LOG_NAME);
    }

    /**
     * 错误日志类
     *
     * @return
     */
    public static Logger errorLog() {
        return LogManager.getLogger(ERROR_LOG_NAME);
    }
}

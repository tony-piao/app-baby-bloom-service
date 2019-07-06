package com.babybloom.web.service;

import com.babybloom.web.constant.BusinessErrorId;
import com.babybloom.web.exception.ApplicationException;
import com.babybloom.web.utility.LogUtility;
import com.babybloom.web.utility.StringUtility;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ServiceBase implements BusinessErrorId {

	@Autowired
	protected IdService idService;

	protected Logger log = LogUtility.businessLog();

	protected Logger errorLog = LogUtility.errorLog();
	/**
	 * 检查参数是否是null
	 * 
	 * @param param
	 *            参数值
	 * @param name
	 *            参数名
	 * @throws
	 */
	protected void checkParamNotNull(String param, String name) {
		if (StringUtility.isNullOrEmpty(param)) {
			throw new ApplicationException(ERROR_PARAMETER, name);
		}
	}

	/**
	 * 检查参数是否是null
	 * 
	 * @param param
	 *            参数值
	 * @param name
	 *            参数名
	 * @throws
	 */
	protected void checkParamNotNull(Object param, String name) throws ApplicationException {
		if (param == null) {
			throw new ApplicationException(ERROR_PARAMETER, name);
		}
	}

	/**
	 * 属性copy
	 * 
	 * @param src
	 * @param des
	 */
	protected void copyProperties(Object src, Object des) {
		org.springframework.beans.BeanUtils.copyProperties(src, des);
	}

	/**
	 * 基于snowflake算法生成long型id
	 * 
	 * @param
	 * @return
	 */
	protected long getSnowflakeNextId() {
		return idService.nextId();
	}

	protected int getOffset(int numberPerPage, int currentPage) {
		return numberPerPage * (currentPage - 1);
	}
}

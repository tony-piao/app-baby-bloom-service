package com.babybloom.web.task;

import com.babybloom.web.utility.LogUtility;

/**
 * 定时器父类
 *
 */
public abstract class TaskBase {

	public final void execute() {
		try {
			doTask();
		} catch (Exception e) {
			LogUtility.businessLog().warn("TASK ERROR-->" + e.getMessage());
		}
	}

	protected abstract void doTask() throws Exception;
}

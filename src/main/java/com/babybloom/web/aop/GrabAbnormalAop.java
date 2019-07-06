package com.babybloom.web.aop;

import com.babybloom.web.constant.CommonErrorId;
import com.babybloom.web.exception.ApplicationException;
import com.babybloom.web.utility.JsonUtility;
import com.babybloom.web.utility.LogUtility;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

/**
 * 异常日志切面
 *
 * @author zz
 */
@Aspect
@Component
public class GrabAbnormalAop {

    @Pointcut("@annotation(com.babybloom.web.annotation.GrabAbnormal)")
    public void annotation() {
    }

    ;

    /**
     * 统一的Service异常处理
     *
     * @param joinPoint
     * @param ex
     * @throws ApplicationException
     */
    @AfterThrowing(pointcut = "annotation()", throwing = "ex")
    public void afterThrow(JoinPoint joinPoint, Exception ex) throws ApplicationException {
        if (ex instanceof ApplicationException) { // 业务异常
            ApplicationException aplEx = (ApplicationException) ex;
            recordBussinessLog(joinPoint, aplEx);
            throw aplEx;
        } else if (ex instanceof DataAccessException) { // 数据库异常
            recordDatabaseLog(joinPoint, (DataAccessException) ex);
            throw new ApplicationException(CommonErrorId.ERROR_DB_EXCEPTION, ex);
        } else { // 其他异常
            recordExceptionLog(joinPoint, ex);
            throw new ApplicationException(CommonErrorId.ERROR_UNKNOWN, ex);
        }
    }

    /**
     * 记录业务异常日志信息(只记录最顶层的异常堆栈信息)
     *
     * @param joinPoint
     * @param ex
     */
    private void recordBussinessLog(JoinPoint joinPoint, ApplicationException ex) {
        StringBuilder strBuilder = new StringBuilder();
        StackTraceElement stackTraceElement = ex.getStackTrace()[0];
        // 记录调用的方法和参数
        strBuilder.append("APL ERROR-->");
        strBuilder.append("ErrorId= ");
        strBuilder.append(ex.getErrorId());
        strBuilder.append("-->");
        buildInvokErrorStr(strBuilder, joinPoint);
        strBuilder.append("-->");
        buildStackErrorStr(strBuilder, stackTraceElement);
        strBuilder.append("-->");
        strBuilder.append("ErrorMSG= ");
        strBuilder.append(ex.getMessage());
        LogUtility.businessLog().warn(strBuilder.toString());
    }

    /**
     * 记录数据库异常日志信息
     *
     * @param joinPoint
     * @param ex
     */
    private void recordDatabaseLog(JoinPoint joinPoint, DataAccessException ex) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Database ERROR-->");
        // 记录调用的方法和参数
        buildInvokErrorStr(strBuilder, joinPoint);
        strBuilder.append("-->");
        strBuilder.append("ErrorMSG= ");
        strBuilder.append(ex.getMessage());
        LogUtility.errorLog().error(strBuilder.toString());
    }

    /**
     * 记录其他异常日志信息(只记录最顶层的异常堆栈信息)
     *
     * @param joinPoint
     * @param ex
     */
    private void recordExceptionLog(JoinPoint joinPoint, Exception ex) {
        StringBuilder strBuilder = new StringBuilder();
        StackTraceElement stackTraceElement = ex.getStackTrace()[0];
        strBuilder.append("UNKNOWN ERROR-->");
        // 记录出错引起的类
        strBuilder.append("CauseBy:");
        strBuilder.append(ex.getClass().getName());
        strBuilder.append("-->");

        // 记录调用的方法和参数
        buildInvokErrorStr(strBuilder, joinPoint);
        strBuilder.append("-->");
        buildStackErrorStr(strBuilder, stackTraceElement);
        strBuilder.append("-->");
        strBuilder.append("ErrorMSG= ");
        // 单独处理空指针异常
        strBuilder.append(ex.getMessage());
        LogUtility.errorLog().error(strBuilder.toString());
    }

    /**
     * 获取调用方法名称
     *
     * @param joinPoint
     * @return
     */
    private String getInvokMethodName(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        Signature signature = joinPoint.getSignature();
        return className + "." + signature.getName();
    }

    /**
     * 获取调用方法的传入参数
     *
     * @param joinPoint
     * @return
     */
    private String getInvokParamJSONStr(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        return JsonUtility.obj2Json(args);
    }

    /**
     * 拼接调用方法的方法名和参数
     *
     * @param strBuilder
     * @param joinPoint
     */
    private void buildInvokErrorStr(StringBuilder strBuilder, JoinPoint joinPoint) {
        strBuilder.append("Method:");
        strBuilder.append(getInvokMethodName(joinPoint));
        strBuilder.append("-->");
        strBuilder.append("Param:");
        strBuilder.append(getInvokParamJSONStr(joinPoint));
    }

    /**
     * 拼接顶级的异常栈信息
     *
     * @param strBuilder
     * @param stackTraceElement
     */
    private void buildStackErrorStr(StringBuilder strBuilder, StackTraceElement stack) {
        // 异常的顶级堆栈信息
        strBuilder.append("ErrorClass:");
        strBuilder.append(stack.getClassName());
        strBuilder.append("-->");
        strBuilder.append("ErrorLine:");
        strBuilder.append(stack.getLineNumber());
        strBuilder.append("-->");
        strBuilder.append("ErrorMethod:");
        strBuilder.append(stack.getMethodName());
    }
}

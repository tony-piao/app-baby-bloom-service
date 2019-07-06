package com.babybloom.web.annotation;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Transactional(value = "gaiaTransactionManager", isolation = Isolation.READ_COMMITTED,
				propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public @interface ApplicationTransaction {
}

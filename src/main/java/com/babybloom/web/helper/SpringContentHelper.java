package com.babybloom.web.helper;

import org.springframework.context.ApplicationContext;

public final class SpringContentHelper {
    private static volatile ApplicationContext ctx;

    public static void initCtx(ApplicationContext appCtx){
        if(ctx == null){
            synchronized (SpringContentHelper.class){
                if(ctx == null){
                    ctx = appCtx;
                }
            }
        }
    }

    public ApplicationContext getCtx(){
        return ctx;
    }

    private SpringContentHelper() {
    }
}

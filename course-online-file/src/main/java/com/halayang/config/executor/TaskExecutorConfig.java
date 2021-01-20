package com.halayang.config.executor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/19 20:41           1.0                        线程池配置
 * @program course-online
 * @description 线程池配置
 * @create 2021/1/19 20:41
 */
public class TaskExecutorConfig {
    /**
     * ThreadPoolTaskExecutor对象
     */
    private static final ThreadPoolTaskExecutor TASK_EXECUTOR = new ThreadPoolTaskExecutor();
    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 0;
    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 16;
    /**
     * 队列容量
     */
    private static final int QUEUE_CAPACITY = 0;
    /**
     * 维持时间，单位 秒
     */
    private static final int KEEP_ALIVE_SECONDS = 180;

    /*
     * 初始化线程池
     */
    static {
        TASK_EXECUTOR.setCorePoolSize(CORE_POOL_SIZE);
        TASK_EXECUTOR.setMaxPoolSize(MAX_POOL_SIZE);
        TASK_EXECUTOR.setQueueCapacity(QUEUE_CAPACITY);
        TASK_EXECUTOR.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        TASK_EXECUTOR.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        TASK_EXECUTOR.initialize();
    }

    private TaskExecutorConfig() {
    }

    /**
     * 获得 ThreadPoolTaskExecutor 对象
     *
     * @return org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
     * @author YangYuDi
     * @date 2021/1/18 9:20
     */
    public static ThreadPoolTaskExecutor getTaskExecutor() {
        return TASK_EXECUTOR;
    }

    /**
     * 获取线程池剩余线程数
     *
     * @return int
     * @author YangYuDi
     * @date 2021/1/18 9:46
     */
    public static int getResidueCount() {
        return TASK_EXECUTOR.getMaxPoolSize() - TASK_EXECUTOR.getActiveCount();
    }

}
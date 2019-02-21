package com.baizhi.cmfz;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@MapperScan("com.baizhi.cmfz.dao")
public class AppRun {

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class,args);
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public ThreadPoolExecutor getThreadPoolExecutor(){

        /**
         * IO密集型任务  （常出现于线程中：数据库数据交互、文件上传下载、网络数据传输等等 能够体现多核处理器的优势）
         * CPU密集型任务 (常出现于线程中：复杂算法 能体现CPU版本的优势）
         *
         */
        int corePoolSize = Runtime.getRuntime().availableProcessors();

        /**
         * public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,
         *                           TimeUnit unit,BlockingQueue<Runnable> workQueue)
         * corePoolSize用于指定核心线程数量
         * maximumPoolSize指定最大线程数
         * keepAliveTime和TimeUnit指定线程空闲后的最大存活时间
         * workQueue则是线程池的缓冲队列,还未执行的线程会在队列中等待
         * 监控队列长度，确保队列有界
         * 不当的线程池大小会使得处理速度变慢，稳定性下降，并且导致内存泄露。如果配置的线程过少，则队列会持续变大，消耗过多内存。
         * 而过多的线程又会 由于频繁的上下文切换导致整个系统的速度变缓——殊途而同归。队列的长度至关重要，它必须得是有界的，这样如果线程池不堪重负了它可以暂时拒绝掉新的请求。
         * ExecutorService 默认的实现是一个无界的 LinkedBlockingQueue。
         */
        ThreadPoolExecutor executor  = new ThreadPoolExecutor(corePoolSize, corePoolSize+1, 10l, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1000));

        return executor;
    }
}

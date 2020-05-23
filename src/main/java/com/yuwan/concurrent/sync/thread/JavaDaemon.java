package com.yuwan.concurrent.sync.thread;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
@Slf4j
public class JavaDaemon {
    public static void main(String[] args) {
        log.info("获取线程管理");
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        log.info("获取线程信息");
        ThreadInfo[] threadInfos = mxBean.dumpAllThreads(false, false);
        for (int i = 0; i < threadInfos.length; i++) {
            ThreadInfo threadInfo = threadInfos[i];
            log.info("线程id={}|名称={}",threadInfo.getThreadId(),threadInfo.getThreadName());
        }

    }
}

package com.bensonzc.scanner;

import com.bensonzc.scanner.constants.PostTypeEnum;
import com.bensonzc.scanner.processor.AcToysProcessor;
import com.bensonzc.scanner.scheduler.DelayQueueScheduler;
import com.bensonzc.scanner.system.ScannerConfig;
import com.bensonzc.scanner.system.ScannerInit;
import com.google.common.collect.Lists;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangchen on 16/5/17.
 */
public class Main {

    public static void main(String[] args) {
        ScannerInit scannerInit = new ScannerInit();
        scannerInit.init();

        DelayQueueScheduler scheduler = new DelayQueueScheduler(10, TimeUnit.SECONDS);
        Spider spider = Spider.create(new AcToysProcessor(Lists.newArrayList("战国"), PostTypeEnum.FAST_TRADE, PostTypeEnum.SALE))
                .scheduler(scheduler);
//                .addUrl(ScannerConfig.url)
                        //开启1个线程抓取
//                .thread(1)
                        //启动爬虫
//                .run();
        scheduler.push(new Request(ScannerConfig.url), spider);
        spider.run();
    }
}

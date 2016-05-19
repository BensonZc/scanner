package com.bensonzc.scanner;

import com.bensonzc.scanner.constants.PostTypeEnum;
import com.bensonzc.scanner.processor.AcToysProcessor;
import com.google.common.collect.Lists;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

/**
 * Created by zhangchen on 16/5/17.
 */
public class Main {

    public static void main(String[] args) {
        Spider.create(new AcToysProcessor(PostTypeEnum.SALE, Lists.newArrayList("战国")))
                //从"https://github.com/code4craft"开始抓
                .addUrl("http://bbs.actoys.net/thread.php?fid-259-page-1.html")
                        //开启5个线程抓取
//                .addPipeline(new ConsolePipeline())
                .thread(1)
                        //启动爬虫
                .run();
    }
}

package com.bensonzc.scanner;

import com.bensonzc.scanner.processor.AcToysProcessor;
import us.codecraft.webmagic.Spider;

/**
 * Created by zhangchen on 16/5/17.
 */
public class Main {

    public static void main(String[] args) {
        Spider.create(new AcToysProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("http://bbs.actoys.net/thread.php?fid-259-page-1.html")
                        //开启5个线程抓取
                .thread(5)
                        //启动爬虫
                .run();
    }
}

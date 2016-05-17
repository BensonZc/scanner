package com.bensonzc.scanner.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by zhangchen on 16/5/17.
 */
public class AcToysProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        page.getHtml().$(".subject_t").links();
    }

    @Override
    public Site getSite() {
        return site;
    }
}

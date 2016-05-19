package com.bensonzc.scanner.processor;

import com.bensonzc.scanner.constants.Constants;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by zhangchen on 16/5/17.
 */
public abstract class AbstractProcessor implements PageProcessor {
    private List<String> keyWords;
    private Site site = Site.me().setRetryTimes(Constants.RETRY_TIMES).setSleepTime(Constants.SLEEP_TIME);

    public AbstractProcessor(List<String> keyWords){
        this.keyWords = keyWords;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }
}

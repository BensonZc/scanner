package com.bensonzc.scanner.processor;

import com.bensonzc.scanner.constants.Constants;
import com.bensonzc.scanner.constants.PostTypeEnum;
import com.bensonzc.scanner.system.ScannerConfig;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhangchen on 16/5/17.
 */
public class AcToysProcessor extends AbstractProcessor {

    /**
     * 帖子类型：求购，出售，快速交易
     */
    private PostTypeEnum[] postTypes;
    /**
     * postTypeXpath：帖子类型的xpath
     * postTitleXpath：帖子标题的xpath
     * postContentXpath：帖子内容的xpath
     */
    private final static String postTypeXpath = "//h1[@class='read_h1']/a/text()";
    private final static String postTitleXpath = "//h1[@class='read_h1']/text()";
    private final static String postContentXpath = "//div[@id='read_tpc']/text()";
    private final static String hotPostsXpath = "//tr[@class='tr3']/td[@class='icon']/a[@title='热门主题']";
    private final static String openPostsXpath = "//tr[@class='tr3']/td[@class='icon']/a[@title='开放主题']";

    private Set<String> posts = new HashSet<String>();

    public AcToysProcessor(List<String> keyWords, PostTypeEnum... postTypes){
        super(keyWords);
        this.postTypes = postTypes;
    }

    @Override
    public void process(Page page) {
        if(page.getUrl().toString().equals(ScannerConfig.url)){
            System.out.println("---------------start page---------------" + System.currentTimeMillis());
            List<String> hotPosts = page.getHtml().xpath(hotPostsXpath).all();
            List<String> openPosts = page.getHtml().xpath(openPostsXpath).all();
            posts.addAll(hotPosts);
            posts.addAll(openPosts);

            page.addTargetRequests(new ArrayList<String>(posts));
            return;
        }
        String type = page.getHtml().xpath(postTypeXpath).toString();
        String postTitle = page.getHtml().xpath(postTitleXpath).toString();
        String postContent = page.getHtml().xpath(postContentXpath).toString();
        boolean isPostTypeMatch = false;
        for(PostTypeEnum postType : postTypes){
            if(postType.getAlias().equals(type)){
                isPostTypeMatch = true;
            }
        }
        if(isPostTypeMatch || postTitle.contains(Constants.SALE)){
            //满足类型的帖子
            if(isMatchPost(postTitle, postContent)){
                page.putField("url", page.getUrl());
            }
        }
    }

    private boolean isMatchPost(String postTitle, String postContent){
        if(StringUtils.isBlank(postTitle) || StringUtils.isBlank(postContent)){
            return false;
        }
        for(String keyWord : getKeyWords()){
            if(postTitle.contains(keyWord) || postContent.contains(keyWord)){
                return true;
            }
        }
        return false;
    }
}

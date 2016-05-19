package com.bensonzc.scanner.processor;

import com.bensonzc.scanner.constants.PostTypeEnum;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * Created by zhangchen on 16/5/17.
 */
public class AcToysProcessor extends AbstractProcessor {

    private PostTypeEnum postType;

    public AcToysProcessor(PostTypeEnum postType, List<String> keyWords){
        super(keyWords);
        this.postType = postType;
    }

    @Override
    public void process(Page page) {
        String type = page.getHtml().xpath("//h1[@class='read_h1']/a/text()").toString();
        String postTitle = page.getHtml().xpath("//h1[@class='read_h1']/text()").toString();
        String postContent = page.getHtml().xpath("//div[@id='read_tpc']/text()").toString();
        System.out.println("type-------" + type);
//        System.out.println("post content-------" + postContent);
        if(StringUtils.isBlank(type) || postType.getAlias().equals(type)){
//            //满足类型的帖子
            System.out.println("post title-------" + postTitle);
            if(isMatchPost(postTitle, postContent)){
                System.out.println("满足的url----------" + page.getUrl());
            }
        }
        page.addTargetRequests(page.getHtml().$(".subject_t").links().all());
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

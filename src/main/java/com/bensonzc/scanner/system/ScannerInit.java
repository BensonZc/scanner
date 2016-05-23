package com.bensonzc.scanner.system;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zhangchen on 16/5/23.
 */
public class ScannerInit {
    Logger logger = Logger.getLogger(ScannerInit.class);

    public void init(){
        Properties prop = new Properties();// 属性集合对象
        InputStream fis = null;// 属性文件输入流
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream("scanner.properties"); //new FileInputStream("/scanner.properties");
            prop.load(fis);// 将属性文件流装载到Properties对象中
            fis.close();// 关闭流
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        ScannerConfig.url = prop.getProperty("url");
    }

}

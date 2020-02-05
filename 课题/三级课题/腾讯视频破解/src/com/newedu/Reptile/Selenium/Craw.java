package com.newedu.Reptile.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Craw extends Thread{
    public static void main(String[] args) {
        new Craw("http://i.mooc.chaoxing.com/space/index?t=1540441958804");
    }
    private static String link=null;
    public Craw(String name){
        link=name;
        init();
    }
    private void init() {
        System.setProperty("webdriver.chrome.driver", "selenium/chromedriver.exe");
        // 驱动 chrome浏览器的程序

        WebDriver driver = new ChromeDriver();//谷歌
        // 驱动启动起来

        ChromeOptions optinos = new ChromeOptions();
        optinos.addArguments("headless");//隐藏界面

//        WebDriver driver = new ChromeDriver(optinos);
//        driver.get(link);

        //WebDriverWait wait = new WebDriverWait(driver, 10);

        String url=link;
        driver.get(url);//网址

//        driver.findElement(By.cssSelector("#mod_main_nav > div." +
//                "main_nav.main_nav_1 >" +
//                " div.main_nav_block.main_nav_blo" +
//                "ck_1 > a.nav_link.grey.t4")).click();//点击按钮
//        //driver.manage().window().maximize();//全屏
//        driver.findElement(By.cssSelector("#keywords")).sendKeys("lol");//点击按钮
//
//        driver.findElement(By.cssSelector("#searchForm > button")).click();//点击按钮

    }
    private void sleeps(int time){
        try {
            sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

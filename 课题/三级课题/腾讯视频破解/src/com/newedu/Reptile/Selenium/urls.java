package com.newedu.Reptile.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class urls {
private String url;
    public urls(String url) {
        this.url = url;
    }
    public String getnext(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions optinos = new ChromeOptions();
        optinos.addArguments("headless");
        WebDriver driver = new ChromeDriver(optinos);
        driver.get(url);
     return driver.findElement(By.cssSelector("body > div:nth-child(3) > div.site_container.container_detail_top > div > div > div > div._playsrc > div.video_btn > span > a")).getAttribute("href");
    }
}

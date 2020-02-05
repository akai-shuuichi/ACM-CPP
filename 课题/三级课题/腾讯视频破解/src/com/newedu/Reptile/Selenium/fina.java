package com.newedu.Reptile.Selenium;
import com.newedu.Reptile.Mysql.DataMysql;
import com.newedu.Reptile.Mysql.SysUsers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class fina {
    public List<SysUsers> rep(String info) {
        DataMysql sql=new DataMysql();
        List<SysUsers> sys=new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", "selenium/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Scanner scanner=new Scanner(System.in);
        System.out.println(123123);
        String url="https://v.qq.com/x/search/?q="+info;
        driver.get(url);
        List<WebElement> elements = driver.findElements(By.cssSelector("body > div.search_container > div.wrapper > div:nth-" +
                "child(1) > div.result_series.result_intention > div.mod_pages.mod_pages_small > a.page_next"));
        if(elements.size()==0){
            if(driver.findElements(By.cssSelector("body > div.search_container > div.wrapp" +
                    "er > div:nth-child(1) > div.result_item.result_item_v > div._playlist" +
                    " > div > div > div > div:nth-child(13) > a")).size()!=0){
                sys=getmore.getmo(driver);
                return sys;//捕捉带集
            }
            else{
                SysUsers sy=new SysUsers();
                sy.setLink(driver.findElement(By.cssSelector("body > div.search_container > div.wrapper > div:n" +
                        "th-child(1) > div.result_item.result_item_v > div._playlist > " +
                        "div > a.btn_primary.btn_primary_lg")).getAttribute("href"));
                String[] res = driver.findElement(By.cssSelector("body > div.search_con" +
                        "tainer > div.wrapper > div:nth-child(1) > div:nth" +
                        "-child(2) > div._infos > div > h2")).getText().split(" ");
                sy.setName(res[0]);
                sy.setType(res[1]);
                sy.setNumber(res[2]);
                sy.setSpare("nothing");
                try {
                    sql.insert(sy);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sys.add(sy);
                return sys;
            }
        }
        else{
            Vector<String []> res=new Vector<>();
            Vector<String> URLS=new Vector<>();
            while (true) {
                Vector<String> tp=new Vector<>();
                List<WebElement> sub = driver.findElements(By.className("list_item"));
                if (sub==null)break;
                for(int i=0;i<sub.size()-19;i++){
                    String[] split = sub.get(i).getText().split("\n");
                    res.add(split);
                    URLS.add(sub.get(i).findElement(By.className("figure")).getAttribute("href"));
                }
                WebElement element = driver.findElement(By.cssSelector("body > div.search_container > div.wrapper > " +
                        "div:nth-child(1) > div.result_series.result_intention > div.mod_pages.mod_pages_small > a.page_next"));
                if (element.getAttribute("class").indexOf("disabled")!=-1) break;
                element.click();
            }
            for(int i=0;i<res.size();i++){
                SysUsers sy=new SysUsers();
                System.out.println(res.get(i)[0]+res.get(i)[1]+URLS.get(i));
                if(res.get(i).length==3){
                    sy.setName(res.get(i)[1]);
                    sy.setType(res.get(i)[2]);
                    sy.setNumber(res.get(i)[0]);
                }
                if(res.get(i).length==2){
                    sy.setName(res.get(i)[0]);
                    sy.setType(res.get(i)[1]);
                    sy.setNumber("更新至一集");
                }
                sy.setLink(URLS.get(i));
                sy.setSpare("need urls");
                try {
                    sql.insert(sy);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sys.add(sy);
            }
        }
        return sys;
    }
}

package com.newedu.Reptile.Selenium;
import com.newedu.Reptile.Mysql.DataMysql;
import com.newedu.Reptile.Mysql.SysUsers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class getmore {

  static List<SysUsers> getmo(WebDriver driver) {
    DataMysql sql=new DataMysql();
    List<SysUsers>  sys=new ArrayList<>();
    driver.findElement(By.cssSelector("body > div.search_container > div.wrapper > div:nth-ch" +
            "ild(1) > div.result_item.result_item_v > div._playl" +
            "ist > div > div > div > div:nth-child(13) > a")).click();
    WebElement element = driver.findElement(By.cssSelector("body > div.search_container > div.wr" +
            "apper > div:nth-child(1) > div.result_item.resul" +
            "t_item_v > div._playlist > div > div > div"));
    List<WebElement> elements = element.findElements(By.className("item"));
    int i = 0;
    String text = driver.findElement(By.cssSelector("body > div.search_contain" +
            "er > div.wrapper > div:nth-child(1) > div.result_item.res" +
            "ult_item_v > div._infos > div > h2")).getText();
    Vector<String[]> vector=new Vector<>();
    for (i = 0; i < elements.size(); i++) {
      String href=elements.get(i).findElement(By.cssSelector("a")).getAttribute("href");
      if (href!=null&&elements.get(i).findElement(By.cssSelector("a")).getAttribute("href").charAt(0)!='j'){
        String tp[]={"第"+elements.get(i).findElement(By.cssSelector("a")).getText()+"集",href};
        vector.add(tp);
      }
    }

    for( i=0;i<vector.size();i++){
      SysUsers sy=new SysUsers();
      System.out.println(vector.get(i)[0]+vector.get(i)[1]);
      sy.setName(text.split(" ")[0]);
      sy.setType(text.split(" ")[1]+text.split(" ")[2]);
      sy.setNumber(vector.get(i)[0]);
      sy.setLink(vector.get(i)[1]);
      sy.setSpare("nothing");
      try {
        sql.insert(sy);
      } catch (Exception e) {
        e.printStackTrace();
      }
     sys.add(sy);
    }
    return sys;
  }
}
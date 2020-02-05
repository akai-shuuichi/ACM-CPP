package com.newedu.Reptile.Music;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class QQ extends Thread{
    Player player;
    public String sss;
    public QQ(String s){
        sss=s;
    }
    public void stopnu(){
        player.close();//关闭
    }

    public void run(){
        try {
            BufferedInputStream bis=new BufferedInputStream(new FileInputStream("music\\"+sss+".mp3"));
            player = new Player(bis);
            player.play();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
    }
}

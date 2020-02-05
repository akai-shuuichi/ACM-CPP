import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

import java.beans.Encoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

class Play0 extends Thread{
        Player player;
        String music;

public Play0(String file) {
        this.music = file;
        }
public void run() {
        try {
                BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
                 player = new Player(buffer);
                System.out.println("shit");
                player.play();

while (true){

        System.out.println(player.getPosition());
        Thread.sleep(1000);
        }
        } catch (Exception e) {
        e.printStackTrace();
        }finally {

        }
        }
 int gett(){
        return player.getPosition();

        }

        }


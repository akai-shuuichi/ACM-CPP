import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tp {

    private static final int MIN_PROGRESS = 0;
    private static final int MAX_PROGRESS = 300;

    private static int currentProgress = MIN_PROGRESS;

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(250, 250);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        // 创建一个进度条
        final JProgressBar progressBar = new JProgressBar();

        // 设置进度的 最小值 和 最大值
        progressBar.setMinimum(MIN_PROGRESS);
        progressBar.setMaximum(MAX_PROGRESS);

        // 设置当前进度值
        progressBar.setValue(currentProgress);

        // 绘制百分比文本（进度条中间显示的百分数）
        progressBar.setStringPainted(true);


        progressBar.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("当前进度值: " + progressBar.getValue() + "; " +
                        "进度百分比: " + progressBar.getPercentComplete());
            }
        });


        panel.add(progressBar);


        jf.setContentPane(panel);
        jf.setVisible(true);
        Play0 play0 = new Play0("123.mp3");
        play0.start();
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentProgress++;
                if (currentProgress > MAX_PROGRESS) {
                    currentProgress = MIN_PROGRESS;
                }
                progressBar.setValue(currentProgress);
                progressBar.setString(currentProgress+"/"+MAX_PROGRESS);
            }

        }).start();
    }


}

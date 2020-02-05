import javax.swing.*;



    public class myframe extends JFrame {
        static {
            //静态代码块
        }
        myframe(){
            this.setTitle("你啥都没填");
            this.setSize(500,500);
            this.setLocationRelativeTo(null);//居中
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        };
        myframe(String text,int high,int weidth){
            this.setTitle(text);
            this.setSize(high,weidth);
            this.setLocationRelativeTo(null);//居中
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        public void canuse(){
            this.setVisible(true);
        }


}





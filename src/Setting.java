import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Setting {
    public static Font settingFont = new Font("Arial", Font.BOLD, 20);
    public static void Setting(){
        Font font1 = new Font("Arial", Font.BOLD, 30);
        JFrame jFrame = new JFrame();
        jFrame.setBounds(0,50,800,650);
        jFrame.setLayout(null);
        jFrame.getContentPane().setBackground(new Color(230, 240, 255));
        JLabel player1 = new JLabel("Player1");
        player1.setBounds(100, 50, 400, 50);
        player1.setFont(font1);
        jFrame.add(player1);

// Move up
        JLabel labelUp = new JLabel("Move Up");
        labelUp.setBounds(50, 150, 200, 50);
        labelUp.setFont(settingFont);
        jFrame.add(labelUp);

        JButton buttonUp = new JButton(ASCIIturnchar(Main.MoveUp));
        buttonUp.setBounds(200, 150, 100, 50);
        buttonUp.setBackground(new Color(64, 64, 64));
        buttonUp.setForeground(Color.WHITE);
        jFrame.add(buttonUp);
        listenForKeys(buttonUp,1);
// Move down
        JLabel labelDown = new JLabel("Move down");
        labelDown.setBounds(50, 250, 200, 50);
        labelDown.setFont(settingFont);
        jFrame.add(labelDown);

        JButton buttonDown = new JButton(ASCIIturnchar(Main.MoveDown));
        buttonDown.setBounds(200, 250, 100, 50);
        buttonDown.setBackground(new Color(64, 64, 64));
        buttonDown.setForeground(Color.WHITE);
        jFrame.add(buttonDown);
        listenForKeys(buttonDown,2);
// Move left
        JLabel labelLeft = new JLabel("Move Left");
        labelLeft.setBounds(50, 350, 200, 50);
        labelLeft.setFont(settingFont);
        jFrame.add(labelLeft);

        JButton buttonLeft = new JButton(ASCIIturnchar(Main.MoveLeft));
        buttonLeft.setBounds(200, 350, 100, 50);
        buttonLeft.setBackground(new Color(64, 64, 64));
        buttonLeft.setForeground(Color.WHITE);
        jFrame.add(buttonLeft);
        listenForKeys(buttonLeft,3);
// Move right
        JLabel labelRight = new JLabel("Move Right");
        labelRight.setBounds(50, 450, 200, 50);
        labelRight.setFont(settingFont);
        jFrame.add(labelRight);

        JButton buttonRight = new JButton(ASCIIturnchar(Main.MoveRight));
        buttonRight.setBounds(200, 450, 100, 50);
        buttonRight.setBackground(new Color(64, 64, 64));
        buttonRight.setForeground(Color.WHITE);
        jFrame.add(buttonRight);
        listenForKeys(buttonRight,4);
//Shoot
        JLabel labelShoot = new JLabel("Shoot");
        labelShoot.setBounds(50, 550, 200, 50);
        labelShoot.setFont(settingFont);
        jFrame.add(labelShoot);

        JButton buttonShoot = new JButton(ASCIIturnchar(DoubleSnake.player1shoot));
        buttonShoot.setBounds(200, 550, 100, 50);
        buttonShoot.setBackground(new Color(64, 64, 64));
        buttonShoot.setForeground(Color.WHITE);
        jFrame.add(buttonShoot);


        JPanel panel = new JPanel();
        panel.setBounds(400, 0, 400, 650);
        panel.setBackground(new Color(46, 49, 49));
        panel.setLayout(null);

        JLabel Player2 = new JLabel("Player2");
        Player2.setBounds(100,50,400,50);
        Player2.setFont(font1);
        Player2.setForeground(Color.WHITE);
        panel.add(Player2);

        JLabel jLabel4 = new JLabel("Move_UP");
        jLabel4.setBounds(50,150,200,50);
        jLabel4.setFont(settingFont);
        jLabel4.setForeground(Color.WHITE);
        panel.add(jLabel4);

        JButton jButton4 = new JButton(ASCIIturnchar(DoubleSnake.player2Up));
        jButton4.setBounds(200,150,100,50);
        jButton4.setBackground(new Color(44, 62, 80));
        jButton4.setForeground(Color.WHITE);
        panel.add(jButton4);

        JLabel jLabel5 = new JLabel("Move_Down");
        jLabel5.setBounds(50,250,200,50);
        jLabel5.setFont(settingFont);
        jLabel5.setForeground(Color.WHITE);
        panel.add(jLabel5);

        JButton jButton5 = new JButton(ASCIIturnchar(DoubleSnake.player2Down));
        jButton5.setBounds(200,250,100,50);
        jButton5.setBackground(new Color(44, 62, 80));
        jButton5.setForeground(Color.WHITE);
        panel.add(jButton5);

        JLabel jLabel6 = new JLabel("Move_Left");
        jLabel6.setBounds(50,350,200,50);
        jLabel6.setFont(settingFont);
        jLabel6.setForeground(Color.WHITE);
        panel.add(jLabel6);

        JButton jButton6 = new JButton(ASCIIturnchar(DoubleSnake.player2left));
        jButton6.setBounds(200,350,100,50);
        jButton6.setBackground(new Color(44, 62, 80));
        jButton6.setForeground(Color.WHITE);
        panel.add(jButton6);

        JLabel jLabel7 = new JLabel("Move_Right");
        jLabel7.setBounds(50,450,200,50);
        jLabel7.setFont(settingFont);
        jLabel7.setForeground(Color.WHITE);
        panel.add(jLabel7);

        JButton jButton7 = new JButton(ASCIIturnchar(DoubleSnake.player2right));
        jButton7.setBounds(200,450,100,50);
        jButton7.setBackground(new Color(44, 62, 80));
        jButton7.setForeground(Color.WHITE);
        panel.add(jButton7);

        JLabel jLabel8 = new JLabel("Shoot");
        jLabel8.setBounds(50,550,200,50);
        jLabel8.setFont(settingFont);
        jLabel8.setForeground(Color.WHITE);
        panel.add(jLabel8);

        JButton jButton8 = new JButton(ASCIIturnchar(DoubleSnake.player2shoot));
        jButton8.setBounds(200,550,100,50);
        jButton8.setBackground(new Color(44, 62, 80));
        jButton8.setForeground(Color.WHITE);
        panel.add(jButton8);

        jFrame.add(panel);
        listenForKeys(jButton4,5);
        listenForKeys(jButton5,6);
        listenForKeys(jButton6,7);
        listenForKeys(jButton7,8);
        listenForKeys(buttonShoot,9);
        listenForKeys(jButton8,10);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    public static   void listenForKeys(JButton button,int num1) {
        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                switch (num1){
                    case 1:
                        Main.MoveUp = keyCode;
                        System.out.println(keyCode);
                        break;
                    case 2:
                        Main.MoveDown = keyCode;
                        break;
                    case 3:
                        Main.MoveLeft = keyCode;
                        break;
                    case 4:
                        Main.MoveRight = keyCode;
                        break;
                    case 5:
                        DoubleSnake.player2Up = keyCode;
                        break;
                    case 6:
                        DoubleSnake.player2Down = keyCode;
                        break;
                    case 7:
                        DoubleSnake.player2left = keyCode;
                        break;
                    case 8:
                        DoubleSnake.player2right = keyCode;
                        break;
                    case 9:
                        DoubleSnake.player1shoot = keyCode;
                        break;
                    case 10:
                        DoubleSnake.player2shoot = keyCode;
                        break;
                    default:
                }
                button.setText(ASCIIturnchar(keyCode));
            }
        });
    }
    public static String ASCIIturnchar(int num){
        if (num >= 65 && num <= 90) {
            char let = (char) num;
            return String.valueOf(let) ;
        }
        else if(num>=96) {
            num = num -96;
            String s=String.valueOf(num);
            return String.valueOf(num);
        }else if(num==38) {   //使用UTF-8不同字符集可能失效
            String upArrow = "\u2191";
            return upArrow;
        }else if(num==40){
            String downArrow = "\u2193";
            return downArrow;
        }else if(num == 37){
            String leftArrow = "\u2190";
            return leftArrow;
        }else if(num == 39){
            String rightArrow = "\u2192";
            return rightArrow;
        }
        String a="undifind";
        return a;
    }
}

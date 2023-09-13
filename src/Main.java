import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.Random;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.util.concurrent.*;

/**
 * @author fanker Yifan Qiu 21012688
 */

//Main table and single-player

public class Main extends JPanel {
    public Music music;
    public static final int ROWS = 80;
    public static final int COLS = 55;
    public static int LeftX=0;
    public static int LeftY=0;
    public static int rows;
    public static int cols;
    private static Move worm;
    private static Node food;
    public static final int CELL_SIZE = 10;
    public static ImageIcon bodyImage;
    public static ImageIcon foodImage;
    public static ImageIcon headImage;
    public boolean iswin = false;
    public JFrame jf = new JFrame("Snake");
    public ScheduledExecutorService executorService;
    public static int MoveDown = 83;
    public static int MoveUp = 87;
    public static int MoveLeft = 65;
    public static int MoveRight = 68;
    private boolean isProcessingKey = false;
    public static JLabel length;
    public Font font = new Font("Arial", Font.BOLD, 20);
    public Main() {
        this.setVisible(true);
        foodImage = new ImageIcon("resource/apple.png");
        bodyImage = new ImageIcon("resource/dot.png");
        headImage = new ImageIcon("resource/head.png");
        this.setBackground(Color.black);
        this.setBounds(0,50,800,550);
        //action();
        setVisible(true);

    }

    public static void main(String[] args) {
        new Main().screen();
    }

    public void screen() {
        rows = ROWS;
        cols =COLS;
        jf.setLayout(null);
        jf.setResizable(false);
        jf.setBounds(500, 180, 810, 640);
        jf.getContentPane().setBackground(Color.WHITE);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel jLabel = new JLabel("S N A K E");
        jLabel.setForeground(Color.CYAN);
        Font fonts = new Font("Arial", Font.BOLD, 40);
        jLabel.setFont(fonts);
        jLabel.setBounds(310, 100, 250, 30);
        JButton button1 = new JButton("Single player");
        JButton button2 = new JButton("Two - players");
        JButton button3 = new JButton("Setting");
        JButton button4 = new JButton("About");
        button1.setBounds(300, 200, 200, 30);
        button2.setBounds(300, 260, 200, 30);
        button3.setBounds(300, 320, 200, 30);
        button4.setBounds(300, 380, 200, 30);
        button1.setFont(font);
        button2.setFont(font);
        button3.setFont(font);
        button4.setFont(font);
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(50, 10, 100, 30);
         exitButton.addActionListener(e -> {
             music.stop();
             jf.dispose();
             new Main().screen();
         });
        exitButton.setFont(font);
        exitButton.setVisible(false);


        length = new JLabel("The goal is :" + 0);
        length.setFont(font);
        length.setBounds(580,10,200,30);
        length.setVisible(false);
        jf.add(jLabel);
        jf.add(button1);
        jf.add(button2);
        jf.add(button3);
        jf.add(button4);
        jf.add(length);
        jf.add(exitButton);
        button1.addActionListener(e -> {
            //加入音频
            music = new Music("resource/singlePlayer.wav",true);
            music.start();
            //调用游戏函数
            missButton(jLabel, button1, button2, button3, button4);
            worm = new Move(1);
            food = createFood();
            this.setVisible(true);
            jf.add(this);
            this.action();
            length.setVisible(true);
            exitButton.setVisible(true);
        });
        button2.addActionListener(e -> new DoubleSnake());
        button3.addActionListener(e -> {
           Setting.Setting();
        });
        button4.addActionListener(e -> {
            new about();
            System.out.println(Main.LeftX);
        });
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void missButton(JLabel label, JButton button1, JButton button2, JButton button3, JButton button4) {
        label.setVisible(false);
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);
        button4.setVisible(false);
    }

      //single player game

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(foodImage.getImage(), CELL_SIZE * food.getX(), CELL_SIZE * food.getY(), null);
        //画蛇
        int[][] cells = worm.getCells();
        for (int i = 0; i < cells.length; i++) {
            int[] cell = cells[i];
            if (i == 0) {
                g.drawImage(headImage.getImage(), CELL_SIZE * cell[0], CELL_SIZE * cell[1], null);
            } else {
                g.drawImage(bodyImage.getImage(), CELL_SIZE * cell[0], CELL_SIZE * cell[1], null);
            }
        }
    }

    private void action() {
           //the use of this function: "executorService" followed on a website called CSDN
        executorService = Executors.newSingleThreadScheduledExecutor();
        /*
         * 参数1:要执行的任务
         * 参数2:多长时间后执行任务
         * 参数1:执行任务的时间间隔
         * 参数4:延迟参数的时间单位
         */
        executorService.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        //爬行控制
                        // System.out.println("aaa");
                        if (worm.hit() || iswin) {
                            //System.out.println("you lose");
                            executorService.shutdown();
                            try {
                                if (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                                    executorService.shutdownNow();
                                    // executorService停止
                                }
                                boolean isShutdown = executorService.isShutdown();
                                System.out.println("isShutdown: " + isShutdown);displaygoal(iswin);
                                System.out.println("di2ci");
                            } catch (InterruptedException e) {
                                executorService.shutdownNow();

                            }
                        } else {
                            boolean eat = worm.creep(food);
                            if (eat) {
                                worm.goal++;
                                length.setText("The goal is :" + worm.goal);
                                if(worm.goal == 20){
                                    iswin =true;
                                }
                                food = createFood();
                            }
                        }
                        repaint();
                    }
                },
                0,
                100,
                TimeUnit.MILLISECONDS);
        this.requestFocus();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isProcessingKey) {
                    return ;  // 如果正在处理按键事件，则不进行后面事件
                }
                isProcessingKey = true;  // 标记当前正在处理按键事件

                int KeyText = e.getKeyCode();
                if (KeyText == MoveUp) {
                    creepTo(Move.UP);
                } else if (KeyText == MoveDown) {
                    creepTo(Move.DOWN);
                } else if (KeyText == MoveLeft) {
                    creepTo(Move.LEFT);
                } else if (KeyText == MoveRight) {
                    creepTo(Move.RIGHT);
                }
                isProcessingKey = false;  // 标记当前处理完毕
            }
        });
    }
    private void creepTo(int direction) {
        if (worm.hit(direction)) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                    executorService.shutdownNow();
                    // 尝试立即停止executorService
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow(); // 尝试立即停止executorService
            }
        } else {
            boolean eat = worm.creep(direction, food);
            if (eat) {
                createFood();
            }
        }
        repaint();
    }

    public static Node createFood() {
        int x;
        int y;
        Random r = new Random();
        do {
            x =3+ r.nextInt(ROWS-6);
            y =3+ r.nextInt(COLS-6);
        } while (worm.contains(x, y));
        return new Node(x, y);
    }
   public void displaygoal(boolean iswin){
       JDialog jDialog =new JDialog();
       jDialog.setTitle("Failed");
       jDialog.setLayout(null);
       JLabel jLabel = new JLabel();
       if(!iswin){
           jLabel.setText("You are dead at "+(worm.goal));
       }
       else {
           jLabel.setText("You are win at 20");
       }
       jDialog.setBounds(800,460,400,200);
       jLabel.setBounds(100,50,220,30);
       Font font=new Font("Arial",Font.BOLD,20);
       jLabel.setFont(font);
       JButton cancelBut = new JButton("Return Main");
       cancelBut.setBounds(120,100,150,30);
       jDialog.add(jLabel);
       jDialog.add(cancelBut);
       jDialog.setVisible(true);
       cancelBut.addActionListener(e -> {
           music.stop();
           jDialog.dispose();
           jf.dispose();
           new Main().screen();
       });
   }

}

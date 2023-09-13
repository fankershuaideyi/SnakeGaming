import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author fanker Yifan Qiu 21012688
 */
//Two-player it create a new frame screen

public class DoubleSnake extends JPanel{
    public Music doubleMusic;
    public static int player2Down = 40;
    public static int player2Up =38;
    public static int player2left = 37;
    public static int player2right = 39;
    public static int player1shoot = 74;
    public static int player2shoot = 97;
    private static Move Snake1;
    private static Move Snake2;
    private static Node food;
    public static ImageIcon bodyImage;
    public static ImageIcon foodImage;
    public static ImageIcon headImage;
    public static ImageIcon bodyImage2;
    public static ImageIcon bulletImage;
    public static ImageIcon barrierImage = new ImageIcon("resource/barrier.png");
    public boolean isdisplay = false;
    public int gameTime;
    public static JLabel sn1Goal;
    public static JLabel sn2Goal;
    public static int finalGoal = 8;
    private int time=0;
    public static final int CELL_SIZE = 10;
    public boolean isdisposed =false;
    public JDialog jDialog;
    public JLabel jLabel;
    public JFrame jFrame =new JFrame("Double Battle");
    public final ArrayList<Fire> bullets = new ArrayList<>();
    public final ArrayList<Fire> bullets2 = new ArrayList<>();
    public DoubleSnake(){
        doubleMusic = new Music("resource/doublePlayers.wav",true);
        doubleMusic.start();
        jDialog =new JDialog();
        jDialog.setTitle("Failed");
        jDialog.setLayout(null);
        jLabel = new JLabel();
        jDialog.setBounds(800,460,400,200);
        jLabel.setBounds(100,50,220,30);
        Font font=new Font("Arial",Font.BOLD,20);
        jLabel.setFont(font);
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(50, 10, 100, 30);
        exitButton.addActionListener(e -> {
            doubleMusic.stop();
            jFrame.dispose();
            new Main().screen();
        });
        exitButton.setFont(font);
        exitButton.setVisible(true);
        jFrame.add(exitButton);

        JButton cancelBut = new JButton("Return Main");
        cancelBut.setBounds(120,100,150,30);
        jDialog.add(jLabel);
        jDialog.add(cancelBut);
        jDialog.setVisible(false);
        cancelBut.addActionListener(e -> {
            doubleMusic.stop();
            jFrame.dispose();
            jDialog.dispose();
        });

       Main.rows =Main.ROWS;
       Main.cols =Main.COLS;
       Main.LeftY = 0;
       Main.LeftX = 0;
        Snake1 = new Move(1);
        Snake2 = new Move(50);
        jFrame.setLayout(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setBounds(500, 180, 810, 640);
        jFrame.getContentPane().setBackground(Color.CYAN);
        Font fontsn=new Font("Arial",Font.PLAIN ,15);
        Snake1.setHitBgem("resource/p1Hit.wav");
        Snake2.setHitBgem("resource/p2Hit.wav");
        sn1Goal = new JLabel("The snake1 goal is :" + 0);
        sn1Goal.setFont(fontsn);
        sn1Goal.setBounds(550,5,200,15);
        sn1Goal.setForeground(Color.red);
        sn1Goal.setVisible(true);
        jFrame.add(sn1Goal);
        sn2Goal = new JLabel("The snake2 goal is :" + 0);
        sn2Goal.setFont(fontsn);
        sn2Goal.setBounds(550,25,200,15);
        sn2Goal.setForeground(Color.red);
        sn2Goal.setVisible(true);
        jFrame.add(sn2Goal);
        bodyImage2 = new ImageIcon("resource/dot2.png");
        foodImage = new ImageIcon("resource/apple.png");
        bodyImage = new ImageIcon("resource/dot.png");
        headImage = new ImageIcon("resource/head.png");
        bulletImage = new ImageIcon("resource/bullet.png");
        food =createFood();
       this.setBounds(0,50,800,550);
       this.setBackground(Color.black);
       this.setVisible(true);
       jFrame.add(this);
       this.doubleAction();
       jFrame.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
               super.windowClosing(e);
               isdisplay = true;
               jFrame.dispose();
           }
       });
       //控制窗口在windows上面关闭：按叉号
        Music music1Beg= new Music("resource/p1Beg.wav",false);
        music1Beg.start();
        Music music2Beg = new Music("resource/p2Beg.wav",false);
        music2Beg.start();
        //双方开始各放一句话

    }
    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.setColor(Color.gray);
        g.fillRect(0,0,barrierImage.getIconWidth()*time,Main.cols*CELL_SIZE);
        //左边界
        g.fillRect(0,0,Main.rows*CELL_SIZE,barrierImage.getIconHeight()*time);
        // 上边界
        g.fillRect(0,Main.cols*CELL_SIZE,810,barrierImage.getIconHeight()*time);
        //下边界
        g.fillRect(Main.rows*CELL_SIZE,0,810,Main.cols*CELL_SIZE);
        //右边界
        g.drawImage(foodImage.getImage(), Main.CELL_SIZE * food.getX(), Main.CELL_SIZE * food.getY(), null);
        //画蛇
        int[][] cells1 = Snake1.getCells();
        int[][] cells2 = Snake2.getCells();
        for (int i = 0; i < cells1.length; i++) {
            int[] cell = cells1[i];
            if (i == 0) {
                g.drawImage(headImage.getImage(), CELL_SIZE * cell[0], CELL_SIZE * cell[1], null);
            } else {
                g.drawImage(bodyImage.getImage(), CELL_SIZE * cell[0], CELL_SIZE * cell[1], null);
            }
        }
        for(int i =0;i<cells2.length;i++){
            int[] cell = cells2[i];
            if (i == 0) {
                g.drawImage(headImage.getImage(), CELL_SIZE * cell[0], CELL_SIZE * cell[1], null);
            } else {
                g.drawImage(bodyImage2.getImage(), CELL_SIZE * cell[0], CELL_SIZE * cell[1], null);
            }
        }


        if(!bullets.isEmpty()){
            for(int i = 0;i<bullets.size();i++){
                Fire fire = bullets.get(i);
                g.drawImage(bulletImage.getImage(),fire.fire.getX()*CELL_SIZE,fire.fire.getY()*CELL_SIZE,null);
            }
        }
        if(!bullets2.isEmpty()){
            for(int i = 0;i<bullets2.size();i++){
                Fire fire = bullets2.get(i);
                g.drawImage(bulletImage.getImage(),fire.fire.getX()*CELL_SIZE,fire.fire.getY()*CELL_SIZE,null);
            }
        }
    }
    public void doubleAction(){
        ScheduledExecutorService  executorService = new ScheduledThreadPoolExecutor(1);
        executorService.scheduleWithFixedDelay(new TimerTask() {
            @Override
            public void run() {
                gameTime++;
                if(gameTime>150){Main.rows--;Main.cols--;Main.LeftX++;Main.LeftY++;time++;gameTime=gameTime-100;food = createFood();
                }
                boolean snake1False = Snake1.hit() || Snake1.doubleHit(Snake2.getCells()) || Snake1.bulletHit(bullets2) || Snake2.isIswin();
                boolean snake2False =Snake2.hit() || Snake2.doubleHit(Snake1.getCells()) || Snake2.bulletHit(bullets) || Snake1.isIswin();
                if(snake1False || snake2False || isdisposed){
                        executorService.shutdown();
                        try {
                            if(!executorService.awaitTermination(400,TimeUnit.MILLISECONDS)){
                                      displayGoal(snake1False,snake2False,isdisplay);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }else {boolean eat1 =Snake1.creep(food);boolean eat2 =Snake2.creep(food);
                    if(eat1||eat2){
                        food =createFood();
                          if(eat1){
                              Snake1.goal++;
                              sn1Goal.setText("The snake1 goal is :"+Snake1.goal);
                              if(Snake1.goal == finalGoal){
                                  Snake1.updateiswin();
                              }
                          } else{
                            Snake2.goal++;
                            sn2Goal.setText("The snake2 goal is :"+Snake2.goal);
                              if(Snake2.goal == finalGoal){
                                  Snake2.updateiswin();
                              }
                          }
                    }
                    updateBullet(bullets);
                    updateBullet(bullets2);
                    repaint();
                }
            }
        },0,100, TimeUnit.MILLISECONDS
        );
        this.requestFocus();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyText = e.getKeyCode();
                if(keyText == Main.MoveUp){
                    doublecreepTo(Move.UP,Snake1);
                }else if(keyText ==Main.MoveDown){
                    doublecreepTo(Move.DOWN,Snake1);
                }else if(keyText ==Main.MoveLeft){
                    doublecreepTo(Move.LEFT,Snake1);
                }else if(keyText ==Main.MoveRight){
                    doublecreepTo(Move.RIGHT,Snake1);
                }else if(keyText ==player2Up){
                    doublecreepTo(Move.UP,Snake2);
                }else if(keyText ==player2Down){
                    doublecreepTo(Move.DOWN,Snake2);
                }else if(keyText ==player2left){
                    doublecreepTo(Move.LEFT,Snake2);
                }else if(keyText ==player2right){
                    doublecreepTo(Move.RIGHT,Snake2);
                }else if(keyText == player1shoot){
                    if(Snake1.numberOfShoots>0){
                        Fire fire = new Fire();
                        fire.fire.setX(Snake1.getX());
                        fire.fire.setY(Snake1.getY());
                        fire.setDirectionbullet(Snake1.getCurrentDirection());
                        Music musicShot = new Music("resource/p1Shot.wav",false);
                        musicShot.start();
                        bullets.add(fire);
                        Snake1.numberOfShoots--;
                    }
                }else if(keyText == player2shoot){
                    if(Snake2.numberOfShoots>0){
                        Fire fire = new Fire();
                        fire.fire.setX(Snake2.getX());
                        fire.fire.setY(Snake2.getY());
                        fire.setDirectionbullet(Snake2.getCurrentDirection());
                        Music musicShot = new Music("resource/p2Shot.wav",false);
                        musicShot.start();
                        bullets2.add(fire);
                        Snake2.numberOfShoots--;
                    }
                }
            }
        });
    }

    public void displayGoal(boolean snake1False,boolean snake2False,boolean display){
        jDialog.setVisible(true);
        if(display){
            jDialog.setVisible(false);   doubleMusic.stop();
        }
        else if(snake1False && snake2False){
            jLabel.setText("        They draw");
        }
        else if(snake1False){
            jLabel.setText("The winner is Snake2");
        } else if(snake2False){
            jLabel.setText("The winner is Snake1");
        }
       Main.LeftY = 0;
        Main.LeftX = 0;

    }
    private void doublecreepTo(int direction,Move cells) {
        boolean snake1Win = Snake1.hit() ||Snake1.doubleHit(Snake2.getCells()) ;
        boolean snake2Win =Snake2.hit()||Snake2.doubleHit(Snake1.getCells()) ;
        if (snake1Win || snake2Win) {
            displayGoal(snake1Win,snake2Win,isdisplay);
        } else {
            boolean eat = cells.creep(direction, food);
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
            x =Main.LeftX+3 + r.nextInt(Main.rows-Main.LeftX-3);
            y =Main.LeftY+3 + r.nextInt(Main.cols-Main.LeftY-3);
            System.out.println(x + " "+y);
        } while (Snake1.contains(x, y)||Snake2.contains(x,y));
        return new Node(x, y);
    }
    public void removeBullet(Fire fire,ArrayList<Fire> bullets) {
            bullets.remove(fire);
    }
    public void updateBullet(ArrayList<Fire> bullets){
        if(bullets.size() == 0){
            return;
        }
        for(int i = 0 ; i < bullets.size();i++){
            Fire fire = bullets.get(i);
            bullets.get(i).move();
           if(fire.getOutScreen() || fire.getIsfirst()){
              removeBullet(bullets.get(i),bullets);
            }
        }
    }

}

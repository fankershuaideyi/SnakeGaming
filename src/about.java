import javax.swing.*;
import java.awt.*;

/**
 * @author fanker Yifan Qiu 21012688
 */
public class about extends JFrame{
    public about(){
        this.setTitle("About");
        this.setBounds(650, 250, 500, 500);
        this.setLayout(null);
        init();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    void init(){
        JLabel singlePlayer = new JLabel("Single Player");
        this.getContentPane().setBackground(Color.WHITE);
        Font font = new Font("Flanker",Font.BOLD,20);
        singlePlayer.setFont(font);
        singlePlayer.setBounds(175,20,150,30);
        Font font1 = new Font("Arial", Font.BOLD, 16);
        JLabel singleIntroduce = new JLabel("<html>The single player mode involves collecting the required 20 apples across a wide expanse of land where the only enemies are the border and yourself, silently completing the 20 goal. </html>");
        singleIntroduce.setBounds(120,20,300,200);
        singleIntroduce.setFont(font1);

        JLabel doublePlayer = new JLabel("Double Players");
        doublePlayer.setFont(font);
        doublePlayer.setBounds(175,220,150,30);
        JLabel doubleIntroduce = new JLabel("<html>In two-player mode, I have added a shooting system and a poisonous circle that grows and shrinks over 30s, which cannot be touched by the snake. Each snake has four chances to survive being hit by a bullet, but touching the circle and the opponent's body will kill them outright, with an initial four bullets and one additional bullet capacity for eating an apple. Eat eight apples or kill a team member to win, so use your strategy to win in the ever-changing game environment! </html>");
        doubleIntroduce.setBounds(100,240,350,180);
        this.add(singlePlayer);
        this.add(singleIntroduce);
        this.add(doublePlayer);
        this.add(doubleIntroduce);

    }

    public void paintComponent(Graphics g){
        g.setColor(Color.green);
        g.drawString("kjagqdiuaghfikakf,2",200,300);
    }
}

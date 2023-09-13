import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author fanker Yifan Qiu 21012688
 */
//Snake and some function eg: hit or eat

public class Move {
    public static final int DEFAULT_LENGTH = 2;
    public static final int UP = 1;
    public static final int DOWN = -1;
    public static final int LEFT = 2;
    public static final int RIGHT = -2;
    public int goal = 0;
    public boolean iswin = false;
    private int[][] cells;
    private int currentDirection;
    public int life = 4;
    public int numberOfShoots = 4;
    public String hitBgem;
    public Move(int j) {
        cells = new int[DEFAULT_LENGTH][2];
        for (int i = 0; i < cells.length; i++) {
            cells[i][0] = i+j;
            cells[i][1] = 0;
        }
        currentDirection = DOWN;
    }
    private int[] createHead(int direction) {
        int[] head = {cells[0][0], cells[0][1]};
        switch (direction) {
            case DOWN:
                head[1]++;
                break;
            case UP:
                head[1]--;
                break;
            case LEFT:
                head[0]--;
                break;
            case RIGHT:
                head[0]++;
                break;
            default:
                break;
        }
        return head;
    }

    public boolean contains(int x, int y) {
        for (int[] cell : cells) {
            if (cell[0] == x && cell[1] == y) {
                return true;
            }
        }
        return false;
    }

    public boolean creep(int direction, Node food) {
        if (currentDirection + direction == 0) {
            return false;
        }
        currentDirection = direction;
        int[] head = createHead(direction);
        boolean eat = head[0] == food.getX() && head[1] == food.getY();
            eat = eat || cells[cells.length-1][0] ==food.getX() && cells[cells.length-1][1] ==food.getY();
            //吃的判定：头和尾和食物有过碰撞
        if (eat) {
            cells = Arrays.copyOf(cells, cells.length + 1);
            numberOfShoots++;
        }

        for (int i = cells.length - 1; i >= 1; i--) {
            cells[i] = cells[i - 1];
        }
        cells[0] = head;
        return eat;
    }

    public boolean hit() {
        return hit(currentDirection);
    }

    public boolean doubleHit(int[][] otherCells) {
        for (int[] otherCell : otherCells) {
            if (cells[0][0] == otherCell[0] && cells[0][1] == otherCell[1]) {
                return true;
            }
        }
        return false;
    }
    public boolean bulletHit(ArrayList<Fire> bullets){
        for(int i  = 0 ; i<bullets.size();i++){
            Fire fire = bullets.get(i);
           for(int j = 0;j<cells.length;j++){
               if(Math.abs(fire.fire.getX()-cells[j][0])<=1 && fire.fire.getY() == cells[j][1] || fire.fire.getX() == cells[j][0] &&Math.abs(fire.fire.getY()-cells[j][1])<=1){
                   life  = life-1;
                    bullets.get(i).setIsFirst();
                    Music ishit = new Music(hitBgem,false);
                    ishit.start();
                   return false;
               }
           }
        }
        if(life==0){
            System.out.println(life);
            return true;
        }
        return false;
    }

    public boolean hit(int direction) {
        if (currentDirection + direction == 0) {
            return false;
        }

        int[] head = createHead(direction);

        if (head[0] < Main.LeftX || head[0] >= Main.rows || head[1] < Main.LeftY || head[1] >= Main.cols) {
            return true;
        }

        for (int i = 0; i < cells.length - 1; i++) {
            int[] cell = cells[i];

            if (cell[0] == head[0] && cell[1] == head[1]) {
                return true;
            }
        }
        return false;
    }


    public int[][] getCells() {
        return Arrays.copyOf(cells, cells.length);
    }
    @Override
    public String toString(){
        return Arrays.toString(cells);
    }
    public boolean creep(Node food) {
        return creep(currentDirection,food);
    }
    public int getX(){
        return cells[0][0];
    }
    public int getY(){
        return cells[0][1];
    }
    public int getCurrentDirection(){
        return currentDirection;
    }
    public void updateiswin(){ this.iswin = true; }
    public boolean isIswin(){return iswin;}
    public void setHitBgem(String hitBgem){
        this.hitBgem = hitBgem;
    }
}
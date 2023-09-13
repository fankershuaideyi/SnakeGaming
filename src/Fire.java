/**
 * @author fanker Yifan Qiu 21012688
 */
//doubleSnake's bullet of shoot function's

public class Fire {
   public Node fire=new Node();
   public int speed = 3;
   public int directioBbullet;
   public boolean isOutScreen = false;
   public boolean isfirst = false;
   public  Fire(){
   }
   public void setDirectionbullet(int directionbullet){
      this.directioBbullet =directionbullet;
   }
   public void move(){
       switch (directioBbullet){
           case Move.DOWN :
                   int a = fire.getY()+speed;
                   fire.setY(a);
                   if(a>50){
                       isOutScreen =true;
                   }
               break;
           case Move.UP:
                    fire.setY(fire.getY()-speed);
                    if(fire.getY()<0){
                        isOutScreen = true;
                    }
                break;
            case  Move.RIGHT:
                     int c = fire.getX()+speed;
                     fire.setX(c);
                     if(c>80){
                         isOutScreen = true;
                     }
                break;
            case Move.LEFT:
                    int d = fire.getX()-speed;
                    fire.setX(d);
                    if(d<0){
                        isOutScreen =true;
                    }
               break;
           default:
       }
   }
   public void setIsFirst(){
       this.isfirst = true;
   }
   public boolean getIsfirst(){
       return isfirst;
   }
   public boolean getOutScreen(){
       return isOutScreen;
   }
}

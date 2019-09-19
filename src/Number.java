import java.awt.*;

public class Number {
    private int number=0;
    private Color color;
    public Number(){
        this(0);
    }
    public Number(int number){
        this.number=number;
    }
    public int getNumber(){
        return number;
    }
    public void setNumber(int number){
        this.number=number;
    }
    public Color getColor(){
        switch (number){
            case 2 : color=new Color(0x85913D); break;
            case 4 : color=new Color(0x917E0C); break;
            case 8 : color=new Color(0x91551E); break;
            case 16 : color=new Color(0x91446B); break;
            case 32 : color=new Color(0x914229); break;
            case 64 : color=new Color(0x5E9091); break;
            case 128 : color=new Color(0x1B9132); break;
            case 256 : color=new Color(0x6B7E91); break;
            case 512 : color=new Color(0x91492B); break;
            case 1024 : color=new Color(0x918D8A); break;
            case 2048 : color=new Color(0x910054); break;

        }
        return color;
    }
}

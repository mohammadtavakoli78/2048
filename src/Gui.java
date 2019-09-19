import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gui extends JFrame {

    private int score;
    private Number[] numbers;
    private JPanel panel;
    private JPanel highScore;
    private JPanel[] jpanels;
    private JLabel labelText;

    public Gui(){

        super();

        score=0;
        panel = new JPanel();
        highScore = new JPanel();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        setSize(600,700);
        setLocation((int)width/2-300,(int)height/2-350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        panel.setSize(550,500);
        panel.setLocation(16,100);
        panel.setLayout(new GridLayout(4,4,10,10));
        panel.setBackground(new Color(0x8C917C));

        highScore.setLocation(25,0);
        highScore.setSize(550,100);
        highScore.setLayout(new BorderLayout());
        JLabel label=new JLabel("score");
        label.setSize(200,80);
        label.setFont(new Font("Arial",Font.BOLD,25));
        JTextArea textArea=new JTextArea();
        textArea.setLayout(null);
        textArea.setSize(400,80);
        textArea.setEditable(false);
        textArea.setLocation(100,10);
        labelText=new JLabel();
        labelText.setSize(300,60);
        labelText.setLocation(170,10);
        labelText.setFont(new Font("Arial",Font.BOLD,35));
        labelText.setText(Integer.toString(score));
        textArea.add(labelText);
        textArea.setBackground(new Color(64, 140, 117));
        highScore.add(textArea);
        highScore.add(label);

        Random random=new Random();
        int randomSelect1;
        int randomSelect2;
        int random1;
        int random2;
        randomSelect1=(1+random.nextInt(2))*2;
        randomSelect2=(1+random.nextInt(2))*2;
        if((randomSelect1==randomSelect2) && (randomSelect1==4)){
            randomSelect2=2;
        }
        random1=random.nextInt(16);
        random2=random.nextInt(16);
        if(random1==random2){
            if(random1==0){
                random2=1+random.nextInt(15);
            }
            else if(random1==15){
                random2=random.nextInt(15);
            }
            else{
                do {
                    random2=random.nextInt(16);
                }
                while (random1==random2);
            }
        }

        numbers=new Number[16];
        for(int i=0; i<=15; ++i){
            numbers[i]=new Number();
        }

        jpanels=new JPanel[16];
        for(int i=0; i<=15; ++i){
            jpanels[i]=new JPanel();
            if(i==random1){
                if(randomSelect1==2){
                    JLabel labelNew=new JLabel();
                    labelNew.setSize(100,100);
                    labelNew.setText(Integer.toString(randomSelect1));
                    labelNew.setFont(new Font("Airal",Font.BOLD,70));
                    jpanels[i].add(labelNew);
                    numbers[i].setNumber(2);
                    jpanels[i].setBackground(numbers[i].getColor());
                }
                else if(randomSelect1==4){
                    JLabel labelNew=new JLabel();
                    labelNew.setSize(100,100);
                    labelNew.setText(Integer.toString(randomSelect1));
                    labelNew.setFont(new Font("Airal",Font.BOLD,70));
                    jpanels[i].add(labelNew);
                    numbers[i].setNumber(4);
                    jpanels[i].setBackground(numbers[i].getColor());
                }
            }
            if(i==random2){
                if(randomSelect2==2){
                    JLabel labelNew=new JLabel();
                    labelNew.setSize(100,100);
                    labelNew.setText(Integer.toString(randomSelect2));
                    labelNew.setFont(new Font("Airal",Font.BOLD,70));
                    jpanels[i].add(labelNew);
                    numbers[i].setNumber(2);
                    jpanels[i].setBackground(numbers[i].getColor());
                }
                else if(randomSelect2==4){
                    JLabel labelNew=new JLabel();
                    labelNew.setSize(100,100);
                    labelNew.setText(Integer.toString(randomSelect2));
                    labelNew.setFont(new Font("Airal",Font.BOLD,70));
                    jpanels[i].add(labelNew);
                    numbers[i].setNumber(4);
                    jpanels[i].setBackground(numbers[i].getColor());
                }
            }
            else if(i!=random1 && i!=random2){
                jpanels[i].setBackground(Color.gray);
            }
            panel.add(jpanels[i]);
        }

        setFocusable(true);
        int[] arr=new int[16];
        addKeyListener(new KeyListener() {
            int counterGameOver=0;
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                for(int i=0; i<=15; ++i){
                    arr[i]=numbers[i].getNumber();
                }
                int number=0;
                int sameNumber=0;
                int move=0;
                if(e.getKeyCode()==37){
                    for(int i=0; i<=3; ++i){
                        for(int j=1; j<=3; ++j){
                            number=0;
                            sameNumber=0;
                            move=0;
                            if(numbers[j+(i*4)].getNumber()!=0) {
                                number=j-1;
                                while(numbers[number+(i*4)].getNumber()==0){
                                    ++move;
                                    --number;
                                    if(number<0)
                                        break;
                                }
                                number=j-1;
                                while(numbers[number+(i*4)].getNumber()==0){
                                    if(number==0)
                                        break;
                                    --number;
                                }
                                while(numbers[number+(i*4)].getNumber()==numbers[j+(i*4)].getNumber()){
                                    ++sameNumber;
                                    --number;
                                    if(number<0)
                                        break;
                                }
                                if(j==1){
                                    if(move==1){
                                        numbers[i*4].setNumber(numbers[1+(i*4)].getNumber());
                                        numbers[1+(i*4)].setNumber(0);
                                        jpanels[1+(i*4)].removeAll();
                                        jpanels[1+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i*4].getNumber()));
                                        jpanels[i*4].add(labelNew);
                                        jpanels[i*4].setBackground(numbers[i*4].getColor());
                                    }
                                    if(move==0 && sameNumber==1){
                                        numbers[i*4].setNumber(numbers[1+(i*4)].getNumber()*2);
                                        jpanels[1+(i*4)].removeAll();
                                        jpanels[i*4].removeAll();
                                        numbers[1+(i*4)].setNumber(0);
                                        score+=numbers[i*4].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[1+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i*4].getNumber()));
                                        jpanels[i*4].add(labelNew);
                                        jpanels[i*4].setBackground(numbers[i*4].getColor());
                                    }
                                }
                                if(j==2){
                                    if(move==2){
                                        numbers[i*4].setNumber(numbers[2+(i*4)].getNumber());
                                        numbers[2+(i*4)].setNumber(0);
                                        jpanels[2+(i*4)].removeAll();
                                        jpanels[2+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i*4].getNumber()));
                                        jpanels[i*4].add(labelNew);
                                        jpanels[i*4].setBackground(numbers[i*4].getColor());
                                    }
                                    if(move==1 && sameNumber==0){
                                        numbers[1+(i*4)].setNumber(numbers[2+(i*4)].getNumber());
                                        numbers[2+(i*4)].setNumber(0);
                                        jpanels[2+(i*4)].removeAll();
                                        jpanels[2+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[1+(i*4)].getNumber()));
                                        jpanels[1+(i*4)].add(labelNew);
                                        jpanels[1+(i*4)].setBackground(numbers[1+(i*4)].getColor());
                                    }
                                    if(move==1 && sameNumber==1){
                                        numbers[i*4].setNumber(numbers[2+(i*4)].getNumber()*2);
                                        numbers[2+(i*4)].setNumber(0);
                                        jpanels[2+(i*4)].removeAll();
                                        jpanels[i*4].removeAll();
                                        score+=numbers[i*4].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[2+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i*4].getNumber()));
                                        jpanels[i*4].add(labelNew);
                                        jpanels[i*4].setBackground(numbers[i*4].getColor());
                                    }
                                    if(move==0 && sameNumber==1){
                                        numbers[1+(i*4)].setNumber(numbers[2+(i*4)].getNumber()*2);
                                        numbers[2+(i*4)].setNumber(0);
                                        jpanels[2+(i*4)].removeAll();
                                        jpanels[1+(i*4)].removeAll();
                                        score+=numbers[1+(i*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[2+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[1+(i*4)].getNumber()));
                                        jpanels[1+(i*4)].add(labelNew);
                                        jpanels[1+(i*4)].setBackground(numbers[1+(i*4)].getColor());
                                    }
                                }
                                if(j==3){
                                    if(move==3){
                                        numbers[i*4].setNumber(numbers[3+(i*4)].getNumber());
                                        numbers[3+(i*4)].setNumber(0);
                                        jpanels[3+(i*4)].removeAll();
                                        jpanels[3+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i*4].getNumber()));
                                        jpanels[i*4].add(labelNew);
                                        jpanels[i*4].setBackground(numbers[i*4].getColor());
                                    }
                                    if(move==2 && sameNumber==0){
                                        numbers[1+(i*4)].setNumber(numbers[3+(i*4)].getNumber());
                                        numbers[3+(i*4)].setNumber(0);
                                        jpanels[3+(i*4)].removeAll();
                                        jpanels[3+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[1+(i*4)].getNumber()));
                                        jpanels[1+(i*4)].add(labelNew);
                                        jpanels[1+(i*4)].setBackground(numbers[1+(i*4)].getColor());
                                    }
                                    if(move==2 && sameNumber==1){
                                        numbers[i*4].setNumber(numbers[3+(i*4)].getNumber()*2);
                                        jpanels[3+(i*4)].removeAll();
                                        jpanels[i*4].removeAll();
                                        numbers[3+(i*4)].setNumber(0);
                                        score+=numbers[i*4].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[3+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i*4].getNumber()));
                                        jpanels[i*4].add(labelNew);
                                        jpanels[i*4].setBackground(numbers[i*4].getColor());
                                    }
                                    if(move==1 && sameNumber==0){
                                        numbers[2+(i*4)].setNumber(numbers[3+(i*4)].getNumber());
                                        numbers[3+(i*4)].setNumber(0);
                                        jpanels[3+(i*4)].removeAll();
                                        jpanels[3+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[2+(i*4)].getNumber()));
                                        jpanels[2+(i*4)].add(labelNew);
                                        jpanels[2+(i*4)].setBackground(numbers[2+(i*4)].getColor());
                                    }
                                    if(move==1 && sameNumber==1){
                                        numbers[1+(i*4)].setNumber(numbers[3+(i*4)].getNumber()*2);
                                        jpanels[3+(i*4)].removeAll();
                                        jpanels[1+(i*4)].removeAll();
                                        numbers[3+(i*4)].setNumber(0);
                                        score+=numbers[1+(i*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[3+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[1+(i*4)].getNumber()));
                                        jpanels[1+(i*4)].add(labelNew);
                                        jpanels[1+(i*4)].setBackground(numbers[1+(i*4)].getColor());
                                    }
                                    if(move==0 && sameNumber==1){
                                        numbers[2+(i*4)].setNumber(numbers[3+(i*4)].getNumber()*2);
                                        jpanels[3+(i*4)].removeAll();
                                        jpanels[2+(i*4)].removeAll();
                                        numbers[3+(i*4)].setNumber(0);
                                        score+=numbers[2+(i*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[3+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[2+(i*4)].getNumber()));
                                        jpanels[2+(i*4)].add(labelNew);
                                        jpanels[2+(i*4)].setBackground(numbers[2+(i*4)].getColor());
                                    }
                                }
                            }
                        }
                    }
                }
                else if(e.getKeyCode()==38){
                    for(int i=0; i<=3; ++i){
                        for(int j=1; j<=3; ++j){
                            number=0;
                            sameNumber=0;
                            move=0;
                            if(numbers[i+(j*4)].getNumber()!=0) {
                                number=j-1;
                                while(numbers[i+(number*4)].getNumber()==0){
                                    ++move;
                                    --number;
                                    if(number<0)
                                        break;
                                }
                                number=j-1;
                                while(numbers[i+((number)*4)].getNumber()==0){
                                    if(number==0)
                                        break;
                                    --number;
                                }
                                while(numbers[i+(number*4)].getNumber()==numbers[i+(j*4)].getNumber()){
                                    ++sameNumber;
                                    --number;
                                    if(number<0)
                                        break;
                                }
                                if(j==1){
                                    if(move==1){
                                        numbers[i].setNumber(numbers[i+(j*4)].getNumber());
                                        numbers[i+(j*4)].setNumber(0);
                                        jpanels[i+(j*4)].removeAll();
                                        jpanels[i+(j*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i].getNumber()));
                                        jpanels[i].add(labelNew);
                                        jpanels[i].setBackground(numbers[i].getColor());
                                    }
                                    if(move==0 && sameNumber==1){
                                        numbers[i].setNumber(numbers[i+(j*4)].getNumber()*2);
                                        jpanels[i+(j*4)].removeAll();
                                        jpanels[i].removeAll();
                                        numbers[i+(j*4)].setNumber(0);
                                        score+=numbers[i].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i+(j*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i].getNumber()));
                                        jpanels[i].add(labelNew);
                                        jpanels[i].setBackground(numbers[i].getColor());
                                    }
                                }
                                if(j==2){
                                    if(move==2){
                                        numbers[i].setNumber(numbers[i+(j*4)].getNumber());
                                        numbers[i+(j*4)].setNumber(0);
                                        jpanels[i+(j*4)].removeAll();
                                        jpanels[i+(j*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i].getNumber()));
                                        jpanels[i].add(labelNew);
                                        jpanels[i].setBackground(numbers[i].getColor());
                                    }
                                    if(move==1 && sameNumber==0){
                                        numbers[i+(1*4)].setNumber(numbers[i+(j*4)].getNumber());
                                        numbers[i+(j*4)].setNumber(0);
                                        jpanels[i+(j*4)].removeAll();
                                        jpanels[i+(j*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+(1*4)].getNumber()));
                                        jpanels[i+(1*4)].add(labelNew);
                                        jpanels[i+(1*4)].setBackground(numbers[i+(1*4)].getColor());
                                    }
                                    if(move==1 && sameNumber==1){
                                        numbers[i].setNumber(numbers[i+(j*4)].getNumber()*2);
                                        numbers[i+(j*4)].setNumber(0);
                                        jpanels[i+(j*4)].removeAll();
                                        jpanels[i].removeAll();
                                        score+=numbers[i].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i+(j*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i].getNumber()));
                                        jpanels[i].add(labelNew);
                                        jpanels[i].setBackground(numbers[i].getColor());
                                    }
                                    if(move==0 && sameNumber==1){
                                        numbers[i+(1*4)].setNumber(numbers[i+(j*4)].getNumber()*2);
                                        numbers[i+(j*4)].setNumber(0);
                                        jpanels[i+(j*4)].removeAll();
                                        jpanels[i+(1*4)].removeAll();
                                        score+=numbers[i+(1*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i+(j*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+(1*4)].getNumber()));
                                        jpanels[i+(1*4)].add(labelNew);
                                        jpanels[i+(1*4)].setBackground(numbers[i+(1*4)].getColor());
                                    }
                                }
                                if(j==3){
                                    if(move==3){
                                        numbers[i].setNumber(numbers[i+(j*4)].getNumber());
                                        numbers[i+(j*4)].setNumber(0);
                                        jpanels[i+(j*4)].removeAll();
                                        jpanels[i+(j*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i].getNumber()));
                                        jpanels[i].add(labelNew);
                                        jpanels[i].setBackground(numbers[i].getColor());
                                    }
                                    if(move==2 && sameNumber==0){
                                        numbers[i+(1*4)].setNumber(numbers[i+(j*4)].getNumber());
                                        numbers[i+(j*4)].setNumber(0);
                                        jpanels[i+(j*4)].removeAll();
                                        jpanels[i+(j*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+(1*4)].getNumber()));
                                        jpanels[i+(1*4)].add(labelNew);
                                        jpanels[i+(1*4)].setBackground(numbers[i+(1*4)].getColor());
                                    }
                                    if(move==2 && sameNumber==1){
                                        numbers[i].setNumber(numbers[i+(j*4)].getNumber()*2);
                                        jpanels[i+(j*4)].removeAll();
                                        jpanels[i].removeAll();
                                        numbers[i+(j*4)].setNumber(0);
                                        score+=numbers[i].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i+(j*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i].getNumber()));
                                        jpanels[i].add(labelNew);
                                        jpanels[i].setBackground(numbers[i].getColor());
                                    }
                                    if(move==1 && sameNumber==0){
                                        numbers[i+(2*4)].setNumber(numbers[i+(j*4)].getNumber());
                                        numbers[i+(j*4)].setNumber(0);
                                        jpanels[i+(j*4)].removeAll();
                                        jpanels[i+(j*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+(2*4)].getNumber()));
                                        jpanels[i+(2*4)].add(labelNew);
                                        jpanels[i+(2*4)].setBackground(numbers[i+(2*4)].getColor());
                                    }
                                    if(move==1 && sameNumber==1){
                                        numbers[i+(1*4)].setNumber(numbers[i+(j*4)].getNumber()*2);
                                        jpanels[i+(j*4)].removeAll();
                                        jpanels[i+(1*4)].removeAll();
                                        numbers[i+(j*4)].setNumber(0);
                                        score+=numbers[i+(1*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i+(j*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+(1*4)].getNumber()));
                                        jpanels[i+(1*4)].add(labelNew);
                                        jpanels[i+(1*4)].setBackground(numbers[i+(1*4)].getColor());
                                    }
                                    if(move==0 && sameNumber==1){
                                        numbers[i+(2*4)].setNumber(numbers[i+(j*4)].getNumber()*2);
                                        jpanels[i+(j*4)].removeAll();
                                        jpanels[i+(2*4)].removeAll();
                                        numbers[i+(j*4)].setNumber(0);
                                        score+=numbers[i+(2*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i+(j*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+(2*4)].getNumber()));
                                        jpanels[i+(2*4)].add(labelNew);
                                        jpanels[i+(2*4)].setBackground(numbers[i+(2*4)].getColor());
                                    }
                                }
                            }
                        }
                    }
                }
                else if(e.getKeyCode()==39){
                    for(int i=0; i<=3; ++i){
                        for(int j=2; j>=0; --j){
                            number=0;
                            sameNumber=0;
                            move=0;
                            if(numbers[j+(i*4)].getNumber()!=0) {
                                number=j+1;
                                while(numbers[number+(i*4)].getNumber()==0){
                                    ++move;
                                    ++number;
                                    if(number>3)
                                        break;
                                }
                                number=j+1;
                                while(numbers[number+(i*4)].getNumber()==0){
                                    if(number==3)
                                        break;
                                    ++number;
                                }
                                while(numbers[number+(i*4)].getNumber()==numbers[j+(i*4)].getNumber()){
                                    ++sameNumber;
                                    ++number;
                                    if(number>3)
                                        break;
                                }
                                if(j==2){
                                    if(move==1){
                                        numbers[3+(i*4)].setNumber(numbers[2+(i*4)].getNumber());
                                        numbers[2+(i*4)].setNumber(0);
                                        jpanels[2+(i*4)].removeAll();
                                        jpanels[2+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[3+(i*4)].getNumber()));
                                        jpanels[3+(i*4)].add(labelNew);
                                        jpanels[3+(i*4)].setBackground(numbers[3+(i*4)].getColor());
                                    }
                                    if(move==0 && sameNumber==1){
                                        numbers[3+(i*4)].setNumber(numbers[2+(i*4)].getNumber()*2);
                                        jpanels[2+(i*4)].removeAll();
                                        jpanels[3+(i*4)].removeAll();
                                        numbers[2+(i*4)].setNumber(0);
                                        score+=numbers[3+(i*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[2+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[3+(i*4)].getNumber()));
                                        jpanels[3+(i*4)].add(labelNew);
                                        jpanels[3+(i*4)].setBackground(numbers[3+(i*4)].getColor());
                                    }
                                }
                                if(j==1){
                                    if(move==2){
                                        numbers[3+(i*4)].setNumber(numbers[1+(i*4)].getNumber());
                                        numbers[1+(i*4)].setNumber(0);
                                        jpanels[1+(i*4)].removeAll();
                                        jpanels[1+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[3+(i*4)].getNumber()));
                                        jpanels[3+(i*4)].add(labelNew);
                                        jpanels[3+(i*4)].setBackground(numbers[3+(i*4)].getColor());
                                    }
                                    if(move==1 && sameNumber==0){
                                        numbers[2+(i*4)].setNumber(numbers[1+(i*4)].getNumber());
                                        numbers[1+(i*4)].setNumber(0);
                                        jpanels[1+(i*4)].removeAll();
                                        jpanels[1+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[2+(i*4)].getNumber()));
                                        jpanels[2+(i*4)].add(labelNew);
                                        jpanels[2+(i*4)].setBackground(numbers[2+(i*4)].getColor());
                                    }
                                    if(move==1 && sameNumber==1){
                                        numbers[3+(i*4)].setNumber(numbers[1+(i*4)].getNumber()*2);
                                        numbers[1+(i*4)].setNumber(0);
                                        jpanels[1+(i*4)].removeAll();
                                        jpanels[3+(i*4)].removeAll();
                                        score+=numbers[3+(i*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[1+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[3+(i*4)].getNumber()));
                                        jpanels[3+(i*4)].add(labelNew);
                                        jpanels[3+(i*4)].setBackground(numbers[3+(i*4)].getColor());
                                    }
                                    if(move==0 && sameNumber==1){
                                        numbers[2+(i*4)].setNumber(numbers[1+(i*4)].getNumber()*2);
                                        numbers[1+(i*4)].setNumber(0);
                                        jpanels[1+(i*4)].removeAll();
                                        jpanels[2+(i*4)].removeAll();
                                        score+=numbers[2+(i*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[1+(i*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[2+(i*4)].getNumber()));
                                        jpanels[2+(i*4)].add(labelNew);
                                        jpanels[2+(i*4)].setBackground(numbers[2+(i*4)].getColor());
                                    }
                                }
                                if(j==0){
                                    if(move==3){
                                        numbers[3+(i*4)].setNumber(numbers[i*4].getNumber());
                                        numbers[i*4].setNumber(0);
                                        jpanels[i*4].removeAll();
                                        jpanels[i*4].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[3+(i*4)].getNumber()));
                                        jpanels[3+(i*4)].add(labelNew);
                                        jpanels[3+(i*4)].setBackground(numbers[3+(i*4)].getColor());
                                    }
                                    if(move==2 && sameNumber==0){
                                        numbers[2+(i*4)].setNumber(numbers[i*4].getNumber());
                                        numbers[i*4].setNumber(0);
                                        jpanels[i*4].removeAll();
                                        jpanels[i*4].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[2+(i*4)].getNumber()));
                                        jpanels[2+(i*4)].add(labelNew);
                                        jpanels[2+(i*4)].setBackground(numbers[2+(i*4)].getColor());
                                    }
                                    if(move==2 && sameNumber==1){
                                        numbers[3+(i*4)].setNumber(numbers[i*4].getNumber()*2);
                                        jpanels[i*4].removeAll();
                                        jpanels[3+(i*4)].removeAll();
                                        numbers[i*4].setNumber(0);
                                        score+=numbers[3+(i*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i*4].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[3+(i*4)].getNumber()));
                                        jpanels[3+(i*4)].add(labelNew);
                                        jpanels[3+(i*4)].setBackground(numbers[3+(i*4)].getColor());
                                    }
                                    if(move==1 && sameNumber==0){
                                        numbers[1+(i*4)].setNumber(numbers[i*4].getNumber());
                                        numbers[i*4].setNumber(0);
                                        jpanels[i*4].removeAll();
                                        jpanels[i*4].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[1+(i*4)].getNumber()));
                                        jpanels[1+(i*4)].add(labelNew);
                                        jpanels[1+(i*4)].setBackground(numbers[1+(i*4)].getColor());
                                    }
                                    if(move==1 && sameNumber==1){
                                        numbers[2+(i*4)].setNumber(numbers[i*4].getNumber()*2);
                                        jpanels[i*4].removeAll();
                                        jpanels[2+(i*4)].removeAll();
                                        numbers[i*4].setNumber(0);
                                        score+=numbers[2+(i*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i*4].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[2+(i*4)].getNumber()));
                                        jpanels[2+(i*4)].add(labelNew);
                                        jpanels[2+(i*4)].setBackground(numbers[2+(i*4)].getColor());
                                    }
                                    if(move==0 && sameNumber==1){
                                        numbers[1+(i*4)].setNumber(numbers[i*4].getNumber()*2);
                                        jpanels[i*4].removeAll();
                                        jpanels[1+(i*4)].removeAll();
                                        numbers[i*4].setNumber(0);
                                        score+=numbers[1+(i*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i*4].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[1+(i*4)].getNumber()));
                                        jpanels[1+(i*4)].add(labelNew);
                                        jpanels[1+(i*4)].setBackground(numbers[1+(i*4)].getColor());
                                    }
                                }
                            }
                        }
                    }
                }
                else if(e.getKeyCode()==40){
                    for(int i=0; i<=3; ++i){
                        for(int j=2; j>=0; --j){
                            number=0;
                            sameNumber=0;
                            move=0;
                            if(numbers[i+(j*4)].getNumber()!=0) {
                                number=j+1;
                                while(numbers[i+(number*4)].getNumber()==0){
                                    ++move;
                                    ++number;
                                    if(number>3)
                                        break;
                                }
                                number=j+1;
                                while(numbers[i+((number)*4)].getNumber()==0){
                                    if(number==3)
                                        break;
                                    ++number;
                                }
                                while(numbers[i+(number*4)].getNumber()==numbers[i+(j*4)].getNumber()){
                                    ++sameNumber;
                                    ++number;
                                    if(number>3)
                                        break;
                                }
                                if(j==2){
                                    if(move==1){
                                        numbers[i+12].setNumber(numbers[i+(2*4)].getNumber());
                                        numbers[i+(2*4)].setNumber(0);
                                        jpanels[i+(2*4)].removeAll();
                                        jpanels[i+(2*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+12].getNumber()));
                                        jpanels[i+12].add(labelNew);
                                        jpanels[i+12].setBackground(numbers[i+12].getColor());
                                    }
                                    if(move==0 && sameNumber==1){
                                        numbers[i+12].setNumber(numbers[i+(2*4)].getNumber()*2);
                                        jpanels[i+(2*4)].removeAll();
                                        jpanels[i+12].removeAll();
                                        numbers[i+(2*4)].setNumber(0);
                                        score+=numbers[i].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i+(2*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+12].getNumber()));
                                        jpanels[i+12].add(labelNew);
                                        jpanels[i+12].setBackground(numbers[i+12].getColor());
                                    }
                                }
                                if(j==1){
                                    if(move==2){
                                        numbers[i+12].setNumber(numbers[i+(1*4)].getNumber());
                                        numbers[i+(1*4)].setNumber(0);
                                        jpanels[i+(1*4)].removeAll();
                                        jpanels[i+(1*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+12].getNumber()));
                                        jpanels[i+12].add(labelNew);
                                        jpanels[i+12].setBackground(numbers[i+12].getColor());
                                    }
                                    if(move==1 && sameNumber==0){
                                        numbers[i+(2*4)].setNumber(numbers[i+(1*4)].getNumber());
                                        numbers[i+(1*4)].setNumber(0);
                                        jpanels[i+(1*4)].removeAll();
                                        jpanels[i+(1*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+(2*4)].getNumber()));
                                        jpanels[i+(2*4)].add(labelNew);
                                        jpanels[i+(2*4)].setBackground(numbers[i+(2*4)].getColor());
                                    }
                                    if(move==1 && sameNumber==1){
                                        numbers[i+12].setNumber(numbers[i+(1*4)].getNumber()*2);
                                        numbers[i+(1*4)].setNumber(0);
                                        jpanels[i+(1*4)].removeAll();
                                        jpanels[i+12].removeAll();
                                        score+=numbers[i+12].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i+(1*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+12].getNumber()));
                                        jpanels[i+12].add(labelNew);
                                        jpanels[i+12].setBackground(numbers[i+12].getColor());
                                    }
                                    if(move==0 && sameNumber==1){
                                        numbers[i+(2*4)].setNumber(numbers[i+(1*4)].getNumber()*2);
                                        numbers[i+(1*4)].setNumber(0);
                                        jpanels[i+(1*4)].removeAll();
                                        jpanels[i+(2*4)].removeAll();
                                        score+=numbers[i+(2*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i+(1*4)].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+(2*4)].getNumber()));
                                        jpanels[i+(2*4)].add(labelNew);
                                        jpanels[i+(2*4)].setBackground(numbers[i+(2*4)].getColor());
                                    }
                                }
                                if(j==0){
                                    if(move==3){
                                        numbers[i+12].setNumber(numbers[i].getNumber());
                                        numbers[i].setNumber(0);
                                        jpanels[i].removeAll();
                                        jpanels[i].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+12].getNumber()));
                                        jpanels[i+12].add(labelNew);
                                        jpanels[i+12].setBackground(numbers[i+12].getColor());
                                    }
                                    if(move==2 && sameNumber==0){
                                        numbers[i+(2*4)].setNumber(numbers[i].getNumber());
                                        numbers[i].setNumber(0);
                                        jpanels[i].removeAll();
                                        jpanels[i].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+(2*4)].getNumber()));
                                        jpanels[i+(2*4)].add(labelNew);
                                        jpanels[i+(2*4)].setBackground(numbers[i+(2*4)].getColor());
                                    }
                                    if(move==2 && sameNumber==1){
                                        numbers[i+12].setNumber(numbers[i].getNumber()*2);
                                        jpanels[i].removeAll();
                                        jpanels[i+12].removeAll();
                                        numbers[i].setNumber(0);
                                        score+=numbers[i+12].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+12].getNumber()));
                                        jpanels[i+12].add(labelNew);
                                        jpanels[i+12].setBackground(numbers[i+12].getColor());
                                    }
                                    if(move==1 && sameNumber==0){
                                        numbers[i+(1*4)].setNumber(numbers[i].getNumber());
                                        numbers[i].setNumber(0);
                                        jpanels[i].removeAll();
                                        jpanels[i].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+(1*4)].getNumber()));
                                        jpanels[i+(1*4)].add(labelNew);
                                        jpanels[i+(1*4)].setBackground(numbers[i+(1*4)].getColor());
                                    }
                                    if(move==1 && sameNumber==1){
                                        numbers[i+(2*4)].setNumber(numbers[i].getNumber()*2);
                                        jpanels[i].removeAll();
                                        jpanels[i+(2*4)].removeAll();
                                        numbers[i].setNumber(0);
                                        score+=numbers[i+(2*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+(2*4)].getNumber()));
                                        jpanels[i+(2*4)].add(labelNew);
                                        jpanels[i+(2*4)].setBackground(numbers[i+(2*4)].getColor());
                                    }
                                    if(move==0 && sameNumber==1){
                                        numbers[i+(1*4)].setNumber(numbers[i].getNumber()*2);
                                        jpanels[i].removeAll();
                                        jpanels[i+(1*4)].removeAll();
                                        numbers[i].setNumber(0);
                                        score+=numbers[i+(1*4)].getNumber();
                                        labelText.setText(Integer.toString(score));
                                        jpanels[i].setBackground(Color.gray);
                                        JLabel labelNew=new JLabel();
                                        labelNew.setSize(100,100);
                                        labelNew.setLocation(45,8);
                                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                                        labelNew.setText(Integer.toString(numbers[i+(1*4)].getNumber()));
                                        jpanels[i+(1*4)].add(labelNew);
                                        jpanels[i+(1*4)].setBackground(numbers[i+(1*4)].getColor());
                                    }
                                }
                            }
                        }
                    }
                }
                int counter=0;
                for(int i=0; i<=15; ++i){
                    if(arr[i]==numbers[i].getNumber()){
                        ++counter;
                    }
                }
                int counter1=0;
                for(int i=0; i<=15; ++i){
                    if(numbers[i].getNumber()!=0){
                        ++counter1;
                    }
                }
                if(counter1!=16 && counter!=16){
                    int random3;
                    do {
                        random3=random.nextInt(16);
                    }
                    while(numbers[random3].getNumber()!=0);
                    int random4;
                    random4=(1+random.nextInt(2))*2;
                    if(random4==2){
                        JLabel labelNew=new JLabel();
                        labelNew.setSize(100,100);
                        label.setLayout(new BorderLayout());
                        labelNew.setText(Integer.toString(random4));
                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                        jpanels[random3].add(labelNew,BorderLayout.CENTER);
                        numbers[random3].setNumber(2);
                        jpanels[random3].setBackground(numbers[random3].getColor());
                    }
                    else if(random4==4){
                        JLabel labelNew=new JLabel();
                        labelNew.setSize(100,100);
                        label.setLayout(new BorderLayout());
                        labelNew.setText(Integer.toString(random4));
                        labelNew.setFont(new Font("Airal",Font.BOLD,70));
                        jpanels[random3].add(labelNew,BorderLayout.CENTER);
                        numbers[random3].setNumber(4);
                        jpanels[random3].setBackground(numbers[random3].getColor());
                    }
                }
                if(counter==16 && counter1==16){
                    ++counterGameOver;
                    if(counterGameOver==4){
                        dispose();
                        JFrame f=new JFrame();
                        f.setSize(600,700);
                        f.setLocation(630,200);
                        f.setLayout(null);
                        f.setDefaultCloseOperation(3);
                        f.setAlwaysOnTop(true);
                        JLabel l=new JLabel();
                        l.setText("Game Over");
                        l.setSize(200,100);
                        l.setLocation(240,250);
                        l.setFont(new Font("Arial",Font.BOLD,30));
                        f.add(l);
                        f.setVisible(true);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        add(highScore);
        add(panel);

        setVisible(true);

    }

    public static void main(String[] args) {
        Gui gui=new Gui();
    }
}
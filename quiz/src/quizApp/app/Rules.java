package quizApp.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JFrame implements ActionListener {

    JButton start, back;
    String name ;
    Rules(String name){
        this.name = name;
        JLabel heading = new JLabel("Welcome "+name+ " to QUIZ TEST");
        heading.setBounds(150,100,700,30);
        heading.setFont(new Font("Times New Roman", Font.BOLD,28));
        heading.setForeground(new Color(22,39,94));
        add(heading);

        JLabel rules = new JLabel();
        rules.setBounds(70,150,700,300);
        rules.setFont(new Font("Tahoma", Font.PLAIN,16));
        rules.setForeground(new Color(22,34,95));
        rules.setText
                (
                "<html>"+
                        "1. Participation in this quiz is only for person who knows the java prgramming language. "+"<br><br>"+
                        "2. There are total 10 questions. " + "<br><br>" +
                        "3. You have 15 seconds to answer each questions. " + "<br><br> "+
                        "4. No cell Phones or other secondary devices in the room or test area. "+ "<br><br>"+
                        "5. No talking. "+ "<br><br>"+
                        "6. No one else can be in the room with you. "+ "<br><br>"+
                        "<html>"
                );
        add(rules);

        back = new JButton("Back");
        back.setBounds(300,500,100,30);
        back.setBackground(new Color(199,30,20));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        start = new JButton("Start");
        start.setBounds(450,500,100,30);
        start.setBackground(new Color(16,95,20));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        add(start);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/back.png"));
        Image i = i1.getImage().getScaledInstance(800,650,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i);
        JLabel image = new JLabel(i2);
        image.setBounds(0,0,800,650);
        add(image);


        setSize(800,650);
        setLocation(350,50);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == start)
        {
            setVisible(false);
            new quiz(name);
        }
        else
        {
            setVisible(false);
            new login();
        }
    }

    public static void main(String[] args) {
        new Rules("User");
    }

}

package mydemo;

import af.swing.GamePage;
import af.swing.PanelMain;
import af.swing.PhotoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFrame extends JFrame {
    JLabel titleLabel = new JLabel();
    JLabel timeLabel = new JLabel("00:00:00");
    JLabel nameLabel = new JLabel();
    JTextField textField = new JTextField(20);
    //    JTextField passWord = new JTextField(20);
    JCheckBox agreeField = new JCheckBox("同意此协议");
    JComboBox<String> colorField = new JComboBox<>();
    //   JButton button = new JButton("开始");
    JButton button2 = new JButton("开始游戏");
    PhotoView p = new PhotoView();
    public MyFrame(String title){
        super(title);
        // 创建面板(容器)   里面添加按钮等控件 swing按钮JButton
        JPanel panelMain = new JPanel();
        this.setContentPane(panelMain);
        this.setLayout(null);
        //
        p.setImage(new File("data/p1.jpg"));
        p.setBackgroundColor(Color.BLUE);

        panelMain.add(p);
        panelMain.add(colorField);
        panelMain.add(textField);
        panelMain.add(nameLabel);
        panelMain.add(timeLabel);
        panelMain.add(titleLabel);
        //      PanelMain.add(passWord);
        panelMain.add(agreeField);

        MainLayout();

        //   p.setPreferredSize(new Dimension(500,400));

        //    p.setImage(new File("/res/p1.jpg"));
        textField.setText("请输入你想要的名字");
        titleLabel.setText("G N game");
        titleLabel.setFont(new Font("黑体",Font.BOLD,40));
//        passWord.setText("请输入密码");

        colorField.addItem("烈焰红");
        colorField.addItem("胖次白");
        colorField.addItem("基佬紫");

        panelMain.add(button2);


        //未勾选用户同意 确定按钮不能使用
        agreeField.setSelected(false);
        button2.setEnabled(false);

        //匿名内部类复选框事件处理
        agreeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(agreeField.isSelected())
                    button2.setEnabled(true);
                else
                    button2.setEnabled(false);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubmitAcount();  //提交用户自定义名称

                GamePage gamePage = new GamePage();
                panelMain.setVisible(false);
                MyFrame.this.setContentPane(gamePage);

                /****Game Start**/

            }
        });
        //PanelMain.add(button);
//监听器事件
        //     ActionListener listener = new MyActionListener();
        //  匿名类写法
        /*button.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //显示时间
          //      ShowTime();
            }
        });
        */
        panelMain.add(timeLabel);
        timeLabel.setBounds(900,700,50,23);

    }
    public void MainLayout(){
        titleLabel.setBounds(400,450,300,100);
        nameLabel.setText("取个名字吧：");
        nameLabel.setFont(new Font("宋体",Font.BOLD,20));
        nameLabel.setBounds(300,550,150,23);
        p.setBounds(0,0,1200,400);
        textField.setBounds(450,550,200,23);
        //      passWord.setBounds(450,500,200,23);
        agreeField.setBounds(400,585,100,50);
        colorField.setBounds(450,700,70,23);
        //button.setBounds(450,650,60,23);
        button2.setBounds(500,600,100,23);
        //   agreeField.setBounds(650);
    }
    public void SubmitAcount(){
        String usr = textField.getText();
//      String pwd = passWord.getText();
        System.out.println(usr);
        //  System.out.println(pwd);
    }
    public void ShowTime(){
        SimpleDateFormat sjg = new SimpleDateFormat("HH:mm:ss");
        String TimeStr = sjg.format(new Date());

        System.out.println(TimeStr);

        timeLabel.setText(TimeStr); //内部类  外部对象 省略
        // MyFrame.this.timeLabel.setText(TimeStr);
    }
    // 监听器 内部类写法
    /*
    private class MyActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("按钮点击成功");


            SimpleDateFormat sjg = new SimpleDateFormat("HH:mm:ss");
            String TimeStr = sjg.format(new Date());

            System.out.println(TimeStr);

            timeLabel.setText(TimeStr); //内部类  外部对象 省略
            // MyFrame.this.timeLabel.setText(TimeStr);
        }
    }*/


}

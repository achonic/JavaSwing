package mydemo;

import javax.swing.*;
import java.awt.*;

public class test1{
    public static void main(String[] args){
        //多态  子类的实例具有父类的所有特征
        JFrame frame = new MyFrame("Java Game");

        // 创建窗口+窗口标题
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 关闭窗口时 退出程序
        frame.setSize(1200,800);

        frame.setVisible(true);
    }
}
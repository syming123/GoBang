package SocketLink;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnlinkWarning extends JFrame
{
    public UnlinkWarning()
    {
        super("连接错误");
        setVisible(true);
        setBounds(600,300,300,150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("确认");
        JLabel label = new JLabel("因网络错误或对方退出，已断开连接。");

        Box vBox = Box.createVerticalBox();
        vBox.add(Box.createVerticalStrut(30));
        Box h1 = Box.createHorizontalBox();
        vBox.add(h1);
        vBox.add(Box.createVerticalStrut(30));
        Box h2 = Box.createHorizontalBox();
        vBox.add(h2);
        add(vBox);

        h1.add(label);
        h2.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}

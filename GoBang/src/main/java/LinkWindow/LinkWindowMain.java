package LinkWindow;

import PlayWindow.PlayInfo;
import PlayWindow.PlayWindowMain;
import SocketLink.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LinkWindowMain extends JFrame
{
    Linker linker;
    public LinkWindowMain()
    {
        super("五子棋");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500,250,500,300);
        setResizable(false);
        setVisible(true);

        Box vBox = Box.createVerticalBox();
        add(vBox);
        vBox.add(Box.createVerticalStrut(30));

        Box titleBox = Box.createHorizontalBox();
        vBox.add(titleBox);
        JLabel titleText = new JLabel("五子棋");
        titleBox.add(titleText);
        titleText.setFont(new Font(Font.SERIF,Font.PLAIN,30));
        vBox.add(Box.createVerticalStrut(20));

        Box stateBox = Box.createHorizontalBox();
        vBox.add(stateBox);
        JLabel stateText = new JLabel("请输入其他用户的IP和端口:");
        stateBox.add(stateText);
        vBox.add(Box.createVerticalStrut(10));

        Box importIPBox = Box.createHorizontalBox();
        vBox.add(importIPBox);
        JLabel importIPText = new JLabel("IP:     ");
        JTextField inputIPField = new JTextField();
        importIPBox.add(importIPText);
        importIPBox.add(inputIPField);
        importIPBox.setPreferredSize(new Dimension(148,30));
        importIPBox.setMinimumSize(new Dimension(148,30));
        importIPBox.setMaximumSize(new Dimension(148,30));
        vBox.add(Box.createVerticalStrut(10));

        Box importPortBox = Box.createHorizontalBox();
        vBox.add(importPortBox);
        JLabel importPortText = new JLabel("Port:  ");
        JTextField inputPortField = new JTextField();
        importPortBox.add(importPortText);
        importPortBox.add(inputPortField);
        importPortBox.setPreferredSize(new Dimension(150,30));
        importPortBox.setMinimumSize(new Dimension(150,30));
        importPortBox.setMaximumSize(new Dimension(150,30));
        vBox.add(Box.createVerticalStrut(10));

        Box buttonBox = Box.createHorizontalBox();
        vBox.add(buttonBox);
        JButton linkButton = new JButton("开始连接");
        JButton waitButton = new JButton("等待连接");
        JButton settingButton = new JButton("我的信息");
        JButton exitButton = new JButton("退出游戏");
        buttonBox.add(linkButton);
        buttonBox.add(Box.createHorizontalStrut(30));
        buttonBox.add(waitButton);
        buttonBox.add(Box.createHorizontalStrut(30));
        buttonBox.add(settingButton);
        buttonBox.add(Box.createHorizontalStrut(30));
        buttonBox.add(exitButton);
        vBox.add(Box.createVerticalStrut(50));

        JButton newWindow = new JButton();

        validate();
        repaint();


        linkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkThread linkThread = new LinkThread(inputIPField.getText(),Integer.parseInt(inputPortField.getText()),stateText,newWindow);
                linkThread.start();

            }
        });
        waitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WaitThread waitThread = new WaitThread(newWindow);
                waitThread.start();

                linkButton.setEnabled(false);
                waitButton.setEnabled(false);
                stateText.setText("正在等待对手连接...");
                stateText.setForeground(new Color(0x008500));
                inputIPField.setEditable(false);
                inputPortField.setEditable(false);
            }
        });
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingWindow settingWindow = new SettingWindow();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(linker != null)
                {
                    JSONObject exitJS = new JSONObject();
                    exitJS.put("name","exit");
                    linker.writeJSON(exitJS);
                    linker.close();
                }
                System.exit(0);
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
                super.windowClosed(e);
            }
        });

        newWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayWindowMain playWindow = new PlayWindowMain();
                setVisible(false);
            }
        });
    }
}

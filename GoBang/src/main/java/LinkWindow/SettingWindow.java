package LinkWindow;


import SocketLink.KeyData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

public class SettingWindow extends JFrame
{
    public SettingWindow()
    {
        super("我的信息");
        setBounds(550,300,400,200);
        setResizable(false);
        setVisible(true);

        Box vBox = Box.createVerticalBox();
        Box h1 = Box.createHorizontalBox();
        Box h2 = Box.createHorizontalBox();
        Box h3 = Box.createHorizontalBox();
        add(vBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(h1);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(h2);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(h3);
        vBox.add(Box.createVerticalStrut(20));

        JLabel t1 = new JLabel("  ip:");
        JLabel t2 = new JLabel("port:");
        TextField tf1 = new TextField();
        tf1.setPreferredSize(new Dimension(150,20));
        tf1.setMinimumSize(new Dimension(150,20));
        tf1.setMaximumSize(new Dimension(150,20));
        TextField tf2 = new TextField();
        tf2.setPreferredSize(new Dimension(150,20));
        tf2.setMinimumSize(new Dimension(150,20));
        tf2.setMaximumSize(new Dimension(150,20));
        h1.add(t1);
        h1.add(Box.createHorizontalStrut(15));
        h1.add(tf1);
        h2.add(t2);
        h2.add(Box.createHorizontalStrut(10));
        h2.add(tf2);

        JButton okButton = new JButton("确认");
        JButton cancelButton = new JButton("取消");
        h3.add(okButton);
        h3.add(Box.createHorizontalStrut(30));
        h3.add(cancelButton);

        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        }catch (Exception e){e.printStackTrace();}
        KeyData.setIPAddress(ip);
        tf1.setText(KeyData.getIPAddress());
        tf1.setEditable(false);
        tf2.setText(""+KeyData.getPort());

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String ipStr = tf1.getText();
                    String portStr = tf2.getText();
                    KeyData.setIPAddress(ipStr);
                    KeyData.setPort(Integer.parseInt(portStr));
                    dispose();
                }catch (Exception ex){
                    ex.printStackTrace();
                    dispose();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

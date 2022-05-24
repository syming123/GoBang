package PlayWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoBoard extends JPanel
{
    public InfoBoard()
    {
        super();
        setBorder(new EmptyBorder(100,10,10,10));
        //setBackground(Color.blue);

        Box vbox = Box.createVerticalBox();
        add(vbox);

        JLabel roundText = new JLabel("第 0 回合");
        roundText.setFont(new Font(Font.SERIF,Font.PLAIN,30));
        //roundText.setForeground(Color.red);
        Box roundBox = Box.createHorizontalBox();
        //roundBox.add(Box.createHorizontalStrut(0));
        roundBox.add(roundText);
        vbox.add(roundBox);

        JLabel whoText = new JLabel("轮到 你 了");
        whoText.setFont(new Font(Font.SERIF,Font.PLAIN,30));
        vbox.add(Box.createVerticalStrut(30));
        Box whoBox = Box.createHorizontalBox();
        //whoBox.add(Box.createHorizontalStrut(0));
        whoBox.add(whoText);
        vbox.add(whoBox);

        InfoButton exitButton = new InfoButton("退出游戏");
        vbox.add(Box.createVerticalStrut(80));
        Box backBox = Box.createHorizontalBox();
        //backBox.add(Box.createHorizontalStrut(0));
        vbox.add(backBox);
        vbox.add(Box.createVerticalStrut(50));
        Box exitBox = Box.createHorizontalBox();
        //exitBox.add(Box.createHorizontalStrut(0));
        exitBox.add(exitButton);
        vbox.add(exitBox);

        PlayInfo.putLabel(roundText,whoText);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public class InfoButton extends JButton
    {
        public InfoButton()
        {
            super();
            init();
        }
        public InfoButton(String str)
        {
            super(str);
            init();
        }
        private void init()
        {
            setBorder(new EmptyBorder(10,10,10,10));
            //setBackground(new Color(0xD9C8C8));
            setPreferredSize(new Dimension(100,50));
            setMinimumSize(new Dimension(100,50));
            setMaximumSize(new Dimension(100,50));
            setFont(new Font(Font.SERIF,Font.PLAIN,20));
        }
    }
}

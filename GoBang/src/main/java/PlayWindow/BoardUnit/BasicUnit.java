package PlayWindow.BoardUnit;

import PlayWindow.PlayInfo;
import PlayWindow.PlayThread;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BasicUnit extends JButton
{
    int i,j;
    private  int mouseIn = 0;
    public int chess = 0; //1 black; 2 white;
    private void init()
    {
        setPreferredSize(new Dimension(40,40));
        setMinimumSize(new Dimension(40,40));
        setMaximumSize(new Dimension(40,40));

        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(new Color(0x0000000, true)));

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setOpaque(false);
        //setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                mouseIn = 1;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                mouseIn = 0;
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(PlayInfo.getWhoAmI() == PlayInfo.getWho() && PlayInfo.getChess(i,j) == 0)
                {
                    chess = PlayInfo.getWhoAmI();
                    PlayInfo.addChess(i,j,PlayInfo.getWhoAmI());
                    PlayThread playThread = new PlayThread(i,j);
                    playThread.start();
                }
            }
        });
    }
    public BasicUnit()
    {
        super();
        init();
        i = 1;
        j = 1;
    }
    public BasicUnit(int i,int j)
    {
        super();
        init();
        this.i = i;
        this.j = j;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);

        //棋盘
        if(i == 0)
        {
            g.fillRect(19,19,2,21);
        }
        else if(i == 14)
        {
            g.fillRect(19,0,2,21);
        }
        else
        {
            g.fillRect(19,0,2,40);
        }
        if(j == 0)
        {
            g.fillRect(19,19,21,2);
        }
        else if(j == 14)
        {
            g.fillRect(0,19,21,2);
        }
        else
        {
            g.fillRect(0,19,40,2);
        }

        if(i==7&&j==7 || (i==3||i==11)&&(j==3||j==11))
        {
            g.setColor(new Color(0xC23535));
            g.fillOval(16, 16, 8, 8);
        }

        //棋子
        if(chess == 1)
        {
            g.setColor(Color.black);
            g.fillOval(5,5,30,30);
        }
        else if(chess == 2)
        {
            g.setColor(Color.white);
            g.fillOval(5,5,30,30);
        }

        //鼠标悬停效果
        if(mouseIn == 1)
        {
            g.setColor(new Color(0x6BA2A2A2, true));
            g.fillOval(5,5 ,30,30);
        }

    }
}

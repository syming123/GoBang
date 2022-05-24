package PlayWindow;

import PlayWindow.BoardUnit.BasicUnit;
import SocketLink.Linker;

import javax.swing.*;
import java.awt.*;

public class PlayInfo
{
    public static BasicUnit[][] unitBoard = new BasicUnit[15][15];

    private static JLabel turnLabel = new JLabel();
    private static JLabel whoLabel = new JLabel();

    private static final int[][] chessBoard = new int[15][15];
    private static int whoAmI = 0;
    private static int who = 1;
    private static int turn = 0;


    //初始化
    public static void init()
    {
        for (int i = 0; i < 15; i++)
        {
            for (int j = 0; j < 15; j++)
            {
                chessBoard[i][j] = 0;
            }
        }
        who = 1;
        turn = 0;
    }

    //1表示我是黑棋，2代表我是白棋
    public static int getWhoAmI() {
        return whoAmI;
    }

    //设置：1表示我是黑棋，2代表我是白棋
    public static void setWhoAmI(int whoAmI) {
        PlayInfo.whoAmI = whoAmI;
    }

    //获取该谁下棋，1为黑，2为白
    public static int getWho() {
        return who;
    }

    //获取当前是第几回合
    public static int getTurn() {
        return turn;
    }

    //换该下棋者
    public static void whoChange(){
        who = who%2 + 1;
    }

    //回合+1
    public static void turnAdd(){
        turn++;
    }

    //下棋，参数为棋盘坐标x和y，棋子颜色，1为黑，2为白
    public static void addChess(int x,int y,int color)
    {
        chessBoard[x][y] = color;
    }

    //查看棋盘上(x,y)坐标的棋子，1为黑色，2为白色，0为无棋子
    public static int getChess(int x,int y)
    {
        return chessBoard[x][y];
    }

    //控制台打印棋盘内容
    public static void printBoard()
    {
        for (int i = 0; i < 15; i++)
        {
            for (int j = 0; j < 15; j++)
            {
                System.out.print(chessBoard[i][j]+" ");
            }
            System.out.println();
        }
    }

    //检查谁赢了，1为黑棋赢，2为白棋赢，0为暂时没人赢
    public static int checkWin()
    {
        //横向
        for (int i = 0; i < 15; i++)
        {
            int res = checkIfAbove5(chessBoard[i]);
            if(res > 0) return res;
        }
        //纵向
        for (int i = 0; i < 15; i++)
        {
            int[] newArr = new int[15];
            for (int j = 0; j < 15; j++)
            {
                newArr[j] = chessBoard[j][i];
            }
            int res = checkIfAbove5(newArr);
            if (res > 0) return res;
        }
        //左上到右下方向斜向 右上部分
        for (int i = 0; i < 15; i++)
        {
            int[] newArr = new int[i+1];
            for (int j = 0; j <= i; j++)
            {
                newArr[j] = chessBoard[j][14-i+j];
            }
            int res = checkIfAbove5(newArr);
            if (res > 0) return res;
        }
        //左上到右下方向斜向 左下部分
        for (int i = 0; i < 15; i++)
        {
            int[] newArr = new int[i+1];
            for (int j = 0; j <= i; j++)
            {
                newArr[j] = chessBoard[14-i+j][j];
            }
            int res = checkIfAbove5(newArr);
            if (res > 0) return res;
        }
        //右上到左下方向斜向 左上部分
        for (int i = 0; i < 15; i++)
        {
            int[] newArr = new int[i+1];
            for (int j = 0; j <= i; j++)
            {
                newArr[j] = chessBoard[j][i-j];
            }
            int res = checkIfAbove5(newArr);
            if (res > 0) return res;
        }
        //右上到左下方向斜向 右下部分
        for (int i = 0; i < 15; i++)
        {
            int[] newArr = new int[15-i];
            for (int j = i; j < 15; j++)
            {
                newArr[j-i] = chessBoard[j][i+14-j];
            }
            int res = checkIfAbove5(newArr);
            if (res > 0) return res;
        }
        return 0;
    }

    private static int checkIfAbove5(int[] ipt)
    {
        if(ipt.length < 5) return 0;
        int f_color = 0;
        int f_count = 0;
        for (int i = 0; i < ipt.length; i++)
        {
            if(ipt[i] != 0 && ipt[i] == f_color)
            {
                f_count++;
                if(f_count >= 5) return f_color;
            }
            else if (ipt[i] != 0 && ipt[i] != f_color)
            {
                f_color = ipt[i];
                f_count = 1;
            }
            else
            {
                f_color = 0;
                f_count = 0;
            }
        }
        return 0;
    }

    //put label
    public static void putLabel(JLabel turnLabel,JLabel whoLabel)
    {
        PlayInfo.turnLabel = turnLabel;
        PlayInfo.whoLabel = whoLabel;
    }

    public static void flushLabel()
    {
        if(turn > 0)
        {
            turnLabel.setText("第 " + (turn - 1) + " 回合");
        }
        if(getWhoAmI() == getWho())
        {
            whoLabel.setText("轮到 你 了");
        }
        else
        {
            whoLabel.setText("轮到 对手 了");
        }
    }

    public static void winLabel(int whoWin)
    {
        if(whoWin == whoAmI)
        {
            whoLabel.setText("恭喜你，你赢了");
            whoLabel.setForeground(new Color(0x008500));
        }
        else
        {
            whoLabel.setText("很遗憾，你输了");
            whoLabel.setForeground(new Color(0xC23535));
        }
        who = 0;
    }
}

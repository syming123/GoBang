package PlayWindow;

import SocketLink.KeyData;
import SocketLink.Linker;
import com.alibaba.fastjson.JSONObject;

import java.awt.*;

public class PlayThread extends Thread
{
    int x,y;
    public PlayThread(){super();}
    public PlayThread(int x,int y){super();this.x = x;this.y = y;}

    @Override
    public void run() {
        super.run();

        JSONObject sendJS = new JSONObject();
        JSONObject recJS;
        Linker linker = KeyData.getLinker();


        //下棋(x,y)
        if(PlayInfo.getTurn() != 0)
        {
            PlayInfo.unitBoard[x][y].chess = PlayInfo.getWhoAmI();
            PlayInfo.unitBoard[x][y].repaint();
            PlayInfo.addChess(x, y, PlayInfo.getWhoAmI());
        }

        int ifWin = PlayInfo.checkWin();

        if(ifWin != 0)
        {
            if(ifWin == 1) PlayInfo.turnAdd();
            PlayInfo.flushLabel();
            //有人赢了
            PlayInfo.winLabel(ifWin);
            //send win
            sendJS.put("name","win");
            sendJS.put("who",ifWin);
            sendJS.put("locationX",x);
            sendJS.put("locationY",y);
            sendJS.put("color",PlayInfo.getWhoAmI());
            linker.writeJSON(sendJS);
            if(PlayInfo.getWhoAmI() == 2) linker.close();
        }
        else if(PlayInfo.getTurn() == 0)
        {
            PlayInfo.turnAdd();
            //rec
            recJS = linker.readJSON();
            if(recJS.getString("name").equals("put"))
            {
                int lx = recJS.getIntValue("locationX");
                int ly = recJS.getIntValue("locationY");
                int cl = recJS.getIntValue("color");
                PlayInfo.unitBoard[lx][ly].chess = cl;
                PlayInfo.unitBoard[lx][ly].repaint();
                PlayInfo.addChess(lx,ly,cl);
                PlayInfo.whoChange();
                PlayInfo.turnAdd();
                PlayInfo.flushLabel();
            }
        }
        else
        {
            //snd
            sendJS.put("name","put");
            sendJS.put("locationX",x);
            sendJS.put("locationY",y);
            sendJS.put("color",PlayInfo.getWhoAmI());
            linker.writeJSON(sendJS);
            PlayInfo.whoChange();
            if(PlayInfo.getWhoAmI() == 1) PlayInfo.turnAdd();
            PlayInfo.flushLabel();

            //rec
            recJS = linker.readJSON();
            if(recJS.getString("name").equals("win"))
            {
                //rec win
                int lx = recJS.getIntValue("locationX");
                int ly = recJS.getIntValue("locationY");
                int cl = recJS.getIntValue("color");
                PlayInfo.unitBoard[lx][ly].chess = cl;
                PlayInfo.unitBoard[lx][ly].repaint();
                PlayInfo.addChess(lx,ly,cl);
                int whoWin = recJS.getIntValue("who");
                if(whoWin == 1) PlayInfo.turnAdd();
                PlayInfo.flushLabel();
                PlayInfo.winLabel(whoWin);
                if(PlayInfo.getWhoAmI() == 2) linker.close();
            }
            else
            {
                int lx = recJS.getIntValue("locationX");
                int ly = recJS.getIntValue("locationY");
                int cl = recJS.getIntValue("color");
                PlayInfo.unitBoard[lx][ly].chess = cl;
                PlayInfo.unitBoard[lx][ly].repaint();
                PlayInfo.addChess(lx,ly,cl);
                PlayInfo.whoChange();
                if(PlayInfo.getWhoAmI() == 2) PlayInfo.turnAdd();
                PlayInfo.flushLabel();
            }
        }
    }
}

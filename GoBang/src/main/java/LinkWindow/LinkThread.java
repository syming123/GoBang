package LinkWindow;

import PlayWindow.PlayInfo;
import SocketLink.KeyData;
import SocketLink.Linker;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;

public class LinkThread extends Thread
{
    private JLabel flag;
    JButton newWindow;
    String IP;
    int port;
    public LinkThread()
    {
        super();
    }
    public LinkThread(String IP,int port,JLabel flag,JButton newWindow)
    {
        super();
        this.flag = flag;
        this.newWindow = newWindow;
        this.IP = IP;
        this.port = port;
    }

    @Override
    public void run() {
        super.run();
        /*KeyData.getLinker().write("aaa");
        System.out.println(KeyData.getLinker().read());*/
        try {
            KeyData.createLinker(IP,port);
            PlayInfo.init();
            //link方为黑棋
            PlayInfo.setWhoAmI(1);
            PlayInfo.turnAdd();
        }catch (Exception e) {
            flag.setText("无法连接到该玩家");
        }
        newWindow.doClick();
        PlayInfo.flushLabel();
    }
}

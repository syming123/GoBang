package LinkWindow;

import PlayWindow.PlayInfo;
import PlayWindow.PlayThread;
import SocketLink.KeyData;
import SocketLink.Linker;

import javax.swing.*;

public class WaitThread extends Thread
{
    private final JButton newWindow;
    public WaitThread(JButton newWindow)
    {
        super();
        this.newWindow = newWindow;
    }


    @Override
    public void run() {
        super.run();
        /*String str = KeyData.getLinker().read();
        System.out.println(str);
        KeyData.getLinker().write("bbb");*/
        try {
            KeyData.createLinker(KeyData.getPort());
            PlayInfo.init();
            //wait方为白棋
            PlayInfo.setWhoAmI(2);
        }catch (Exception e){
            e.printStackTrace();
        }
        newWindow.doClick();
        PlayInfo.flushLabel();
        PlayThread playThread = new PlayThread();
        playThread.start();
    }
}


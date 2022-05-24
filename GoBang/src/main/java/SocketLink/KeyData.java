package SocketLink;

import java.net.ServerSocket;
import java.net.Socket;

public class KeyData
{
    private static String IPADDRESS = "localhost";
    private static int PORT = 8888;
    private static Linker linker;



    public static String getIPAddress() {
        return IPADDRESS;
    }
    public static void setIPAddress(String IPADDRESS) {
        KeyData.IPADDRESS = IPADDRESS;
    }

    public static int getPort() {
        return PORT;
    }
    public static void setPort(int PORT) {
        KeyData.PORT = PORT;
    }

    public static Linker getLinker(){return linker;}
    public static void createLinker(String IP,int port) throws Exception
    {
        Socket socket = new Socket(IP,port);
        linker = new Linker(socket);
    }
    public static void createLinker(int port) throws Exception
    {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        linker = new Linker(socket);
    }
}

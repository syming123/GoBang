package PlayWindow;

import SocketLink.Linker;

import javax.swing.*;

public class PlayWindowMain extends JFrame
{
    Linker linker;
    public PlayWindowMain()
    {
        super("对局");


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,1000,700);
        setResizable(false);
        setVisible(true);

        Box mainVBox = Box.createVerticalBox();
        add(mainVBox);

        Box mainHBox = Box.createHorizontalBox();
        mainVBox.add(mainHBox);
        ChessBoard chessBoard = new ChessBoard();
        InfoBoard infoBoard = new InfoBoard();
        mainHBox.add(Box.createHorizontalStrut(50));
        mainHBox.add(chessBoard);
        mainHBox.add(infoBoard);

        validate();
        repaint();
    }
}

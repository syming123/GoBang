package PlayWindow;

import PlayWindow.BoardUnit.BasicUnit;
import PlayWindow.BoardUnit.RoundUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessBoard extends JPanel
{
    public ChessBoard()
    {
        super();
        setPreferredSize(new Dimension(600,600));
        setMinimumSize(new Dimension(600,600));
        setMaximumSize(new Dimension(600,600));
        setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 0));
        setBackground(new Color(0xD7A577));

        BasicUnit[][] unit = new BasicUnit[15][15];

        Box vBox = Box.createVerticalBox();
        add(vBox);

        for (int i = 0; i < 15; i++)
        {
            Box hBox = Box.createHorizontalBox();
            vBox.add(hBox);
            for (int j = 0; j < 15; j++)
            {
                unit[i][j] = new BasicUnit(i, j);
                hBox.add(unit[i][j]);
            }
        }
        PlayInfo.unitBoard = unit;
    }


}

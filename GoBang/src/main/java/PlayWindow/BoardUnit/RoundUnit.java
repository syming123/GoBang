package PlayWindow.BoardUnit;

import java.awt.*;

public class RoundUnit extends BasicUnit
{
    public RoundUnit()
    {
        super();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(0xC23535));
        if(super.chess == 0) {
            g.fillOval(16, 16, 8, 8);
        }
    }
}

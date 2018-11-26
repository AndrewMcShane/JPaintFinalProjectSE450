package view.gui;

import javax.swing.JComponent;
import java.awt.*;

//represents the canvas area that the user is able to draw on.
public class PaintCanvas extends JComponent {

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }
}

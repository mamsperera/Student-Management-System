import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {

    private Color backgroundColor;
    private int cornerRadius;

    public RoundedPanel(int radius) {

    super();

    this.backgroundColor = Color.WHITE;   // Default background
    this.cornerRadius = radius;

    setOpaque(false);
}

    public RoundedPanel(Color bgColor, int radius) {

        super();

        backgroundColor = bgColor;
        cornerRadius = radius;

        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Shadow
        g2.setColor(new Color(0,0,0,35));

        g2.fillRoundRect(
                5,
                5,
                getWidth()-5,
                getHeight()-5,
                cornerRadius,
                cornerRadius);

        // Background
        g2.setColor(backgroundColor);

        g2.fillRoundRect(
                0,
                0,
                getWidth()-5,
                getHeight()-5,
                cornerRadius,
                cornerRadius);

        g2.dispose();

        super.paintComponent(g);
    }
}
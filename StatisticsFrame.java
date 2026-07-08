import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {

    public StatisticsFrame(
            int total,
            double average,
            double highest,
            double lowest) {

        setTitle("Student Statistics");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(20, 20));
        main.setBackground(new Color(245, 247, 250));
        main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ==========================
        // Title
        // ==========================

        JLabel title = new JLabel(
                "STUDENT STATISTICS",
                SwingConstants.CENTER);

        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(new Color(33, 150, 243));

        main.add(title, BorderLayout.NORTH);

        // ==========================
        // Cards
        // ==========================

        JPanel cards = new JPanel(new GridLayout(2, 2, 20, 20));
        cards.setOpaque(false);

        cards.add(createCard(
                "Total Students",
                String.valueOf(total),
                new Color(33, 150, 243)));

        cards.add(createCard(
                "Average",
                String.format("%.2f", average),
                new Color(40, 167, 69)));

        cards.add(createCard(
                "Highest",
                String.format("%.2f", highest),
                new Color(255, 193, 7)));

        cards.add(createCard(
                "Lowest",
                String.format("%.2f", lowest),
                new Color(244, 67, 54)));

        main.add(cards, BorderLayout.CENTER);

        main.add(title, BorderLayout.NORTH);

        add(main);

       
        

       
        
        
    }

    // ==========================
    // Card
    // ==========================

    private JPanel createCard(
            String title,
            String value,
            Color bgcolor) {

        JPanel card = new RoundedPanel(bgcolor, 25);

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblValue = new JLabel(value);
        lblValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 42));
        lblValue.setForeground(Color.WHITE);

        card.add(Box.createVerticalGlue());
        card.add(lblTitle);
        card.add(Box.createVerticalStrut(15));
        card.add(lblValue);
        card.add(Box.createVerticalGlue());

        return card;
    }
}
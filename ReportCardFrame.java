import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ReportCardFrame extends JFrame {

    public ReportCardFrame(int id, String name, int age, double marks) {

        setTitle("Student Report");
        setSize(900,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(20,20));
        main.setBackground(new Color(245,247,250));
        main.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        add(main);

        // ==========================
        // Title
        // ==========================

        JLabel title = new JLabel(
                "STUDENT REPORT",
                SwingConstants.CENTER);

        title.setFont(new Font("Segoe UI",Font.BOLD,30));
        title.setForeground(new Color(33,150,243));

        main.add(title,BorderLayout.NORTH);

        // ==========================
        // Center Panel
        // ==========================

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,15,15,15);
        gbc.anchor = GridBagConstraints.CENTER;

        // ==========================
        // Student Card
        // ==========================

        JPanel studentCard = createCard(
                "Student Information",
                new String[]{
                        "ID : " + id,
                        "Name : " + name,
                        "Age : " + age
                },
                new Color(33,150,243)
        );

        gbc.gridx = 0;
        gbc.gridy = 0;

        center.add(studentCard, gbc);

        // ==========================
        // Academic Card
        // ==========================

        String grade;

        if(marks >= 75)
            grade = "A";
        else if(marks >= 65)
            grade = "B";
        else if(marks >= 55)
            grade = "C";
        else if(marks >= 35)
            grade = "S";
        else
            grade = "F";

        JPanel academicCard = createCard(
                "Academic",
                new String[]{
                        "Marks : " + String.format("%.2f", marks),
                        "Grade : " + grade
                },
                new Color(40,167,69)
        );

        gbc.gridx = 1;
        gbc.gridy = 0;

        center.add(academicCard, gbc);


                // ==========================
        // Performance Card
        // ==========================

        String performance;

        if (marks >= 90) {
            performance = "Excellent Performance !";
        } else if (marks >= 75) {
            performance = "Very Good !";
        } else if (marks >= 65) {
            performance = "Good !";
        } else if (marks >= 50) {
            performance = "Average !";
        } else {
            performance = "Needs Improvement !";
        }

        String topStudent = (marks >= 90)
                ? "Top Student"
                : "Keep Improving !";

        JPanel performanceCard = createCard(
                "Performance",
                new String[]{
                        performance,
                        topStudent
                },
                new Color(244,67,54)
        );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        center.add(performanceCard, gbc);

        // ==========================
        // Add Center Panel
        // ==========================

        main.add(center, BorderLayout.CENTER);

        // ==========================
        // Footer
        // ==========================

        JLabel footer = new JLabel(
                "Report Generated : " + LocalDate.now(),
                SwingConstants.RIGHT
        );

        footer.setFont(new Font("Segoe UI", Font.BOLD, 14));
        footer.setForeground(Color.GRAY);

        main.add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }


        // ==========================
        // Create Card Method
        // ==========================

        private JPanel createCard(
            String title,
            String[] details,
            Color color) {

        JPanel card = new RoundedPanel(color, 25);

        // Card Size
        card.setPreferredSize(new Dimension(350, 190));
        card.setMinimumSize(new Dimension(350, 190));
        card.setMaximumSize(new Dimension(350, 190));

        card.setBackground(color);

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        card.setBorder(
                BorderFactory.createEmptyBorder(
                        20,20,20,20));

        // ==========================
        // Card Title
        // ==========================

        JLabel lblTitle = new JLabel(title);

        lblTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 22));

        lblTitle.setForeground(Color.WHITE);

        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalGlue());

        card.add(lblTitle);

        card.add(Box.createVerticalStrut(18));

        // ==========================
        // Card Details
        // ==========================

        for (String text : details) {

            JLabel lbl = new JLabel(text);

            lbl.setFont(
                    new Font("Segoe UI", Font.BOLD, 18));

            lbl.setForeground(Color.WHITE);

            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);

            card.add(lbl);

            card.add(Box.createVerticalStrut(10));
        }

        card.add(Box.createVerticalGlue());

        return card;
    }

}
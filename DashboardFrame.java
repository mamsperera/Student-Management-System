import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DashboardFrame extends JFrame {

    private String totalStudents = "0";
    private String averageMarks = "0";
    private String highestMarks = "0";
    private String lowestMarks = "0";
    private String topStudent = "-";

    public DashboardFrame() {

        loadData();

        setTitle("Student Analytics Dashboard");
        setSize(950,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel(new BorderLayout(25,25));
        main.setBackground(new Color(240,242,245));
        main.setBorder(
                BorderFactory.createEmptyBorder(
                        25,25,25,25));

        add(main);

        // =========================
        // Title
        // =========================

        JLabel title = new JLabel(
                "STUDENT ANALYTICS DASHBOARD",
                SwingConstants.CENTER);

        title.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        34));

        title.setForeground(
                new Color(25,118,210));

        main.add(title, BorderLayout.NORTH);

        // =========================
        // Dashboard Cards
        // =========================

        JPanel cards = new JPanel(
                new GridLayout(2,2,30,30));

        cards.setOpaque(false);

        cards.add(createCard(
                "Students",
                totalStudents,
                new Color(52,152,219)));

        cards.add(createCard(
                "Average",
                averageMarks,
                new Color(46,204,113)));

        cards.add(createCard(
                "Highest",
                highestMarks,
                new Color(241,196,15)));

        cards.add(createCard(
                "Lowest",
                lowestMarks,
                new Color(231,76,60)));

        main.add(cards, BorderLayout.CENTER);


        // =========================
        // Bottom Panel
        // =========================

        JPanel bottom = new JPanel(new BorderLayout(20,20));
        bottom.setOpaque(false);
   
        // Top Student Icon

        ImageIcon topIcon = new ImageIcon("icons/TopStudent.png");

        Image topImg = topIcon.getImage().getScaledInstance(
             40,
             40,
             Image.SCALE_SMOOTH);

        topIcon = new ImageIcon(topImg);

        // Top Student Label

        JLabel lblTop = new JLabel( "Top Student : " + topStudent,topIcon,JLabel.CENTER);

        lblTop.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblTop.setIconTextGap(12);

        lblTop.setFont(
                new Font("Segoe UI",
                Font.BOLD,
                22));

        lblTop.setForeground(new Color(39,174,96));

        bottom.add(lblTop, BorderLayout.NORTH);
 
        // =========================
        // Button Panel
        // =========================

        JPanel buttonPanel = new JPanel(new GridLayout(1,3,20,20));

        buttonPanel.setOpaque(false);

        // Buttons

        JButton btnBar = new JButton("Bar Chart");
        JButton btnPie = new JButton("Pie Chart");
        JButton btnLine = new JButton("Line Chart");

        // Font

        Font btnFont =
                new Font("Segoe UI",
                Font.BOLD,
                18);

        btnBar.setFont(btnFont);
        btnPie.setFont(btnFont);
        btnLine.setFont(btnFont);

        // Colors

        btnBar.setBackground(new Color(39,174,96));
        btnPie.setBackground(new Color(243,156,18));
        btnLine.setBackground(new Color(52,152,219));

        btnBar.setForeground(Color.WHITE);
        btnPie.setForeground(Color.WHITE);
        btnLine.setForeground(Color.WHITE);

        // Style

        btnBar.setFocusPainted(false);
        btnPie.setFocusPainted(false);
        btnLine.setFocusPainted(false);

        btnBar.setBorderPainted(false);
        btnPie.setBorderPainted(false);
        btnLine.setBorderPainted(false);

        btnBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPie.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLine.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBar.setPreferredSize(new Dimension(220,55));
        btnPie.setPreferredSize(new Dimension(220,55));
        btnLine.setPreferredSize(new Dimension(220,55));

        // Button Actions

        btnBar.addActionListener(e -> new ChartFrame());
        btnPie.addActionListener(e -> new PieChartFrame());
        btnLine.addActionListener(e -> new LineChartFrame());

        // Add Buttons

        buttonPanel.add(btnBar);
        buttonPanel.add(btnPie);
        buttonPanel.add(btnLine);

        bottom.add(buttonPanel, BorderLayout.CENTER);

        main.add(bottom, BorderLayout.SOUTH);

        setVisible(true);

    }



    // ==============================
    // Load Dashboard Data
    // ==============================

    private void loadData() {

    try {

        Connection con = DBConnection.getConnection();

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(

            "SELECT COUNT(*) total, " +
            "AVG(marks) avgMarks, " +
            "MAX(marks) highMarks, " +
            "MIN(marks) lowMarks " +
            "FROM students"

        );

        if (rs.next()) {

            totalStudents = String.valueOf(rs.getInt("total"));

            averageMarks = String.format("%.2f",
                    rs.getDouble("avgMarks"));

            highestMarks = String.valueOf(
                    rs.getDouble("highMarks"));

            lowestMarks = String.valueOf(
                    rs.getDouble("lowMarks"));
        }

        rs.close();

        rs = st.executeQuery(

            "SELECT id,name,marks FROM students " +
            "ORDER BY marks DESC LIMIT 1"

        );

        if (rs.next()) {

            topStudent =
                    "ID : "
                    + rs.getInt("id")
                    + " | "
                    + rs.getString("name")
                    + " ("
                    + rs.getDouble("marks")
                    + ")";
        }

        rs.close();
        st.close();
        con.close();

    } catch (Exception ex) {

        ex.printStackTrace();

    }
}


    // ==============================
    // Dashboard Card
    // ==============================

    private JPanel createCard(
        String title,
        String value,
        Color color) {

    RoundedPanel card =
            new RoundedPanel(color,30);

    card.setLayout(new BorderLayout());

    card.setBorder(
            BorderFactory.createEmptyBorder(
                    20,20,20,20));



    // ==========================
    // Load PNG Icon
    // ==========================

    ImageIcon icon;

    int width = 60;
    int height = 60;

    switch (title) {

        case "Students":

            icon = new ImageIcon(
                    "icons/TotalStudents.png");
                    width = 60;
                    height = 80;

            break;

        case "Average":

            icon = new ImageIcon(
                    "icons/Average.png");

            break;

        case "Highest":

            icon = new ImageIcon(
                    "icons/Highest.png");

            break;

        case "Lowest":

            icon = new ImageIcon(
                    "icons/Lowest.png");

            break;

        default:

            icon = new ImageIcon();

    }


    Image img = icon.getImage().getScaledInstance(
            width,
            height,
            Image.SCALE_SMOOTH);

    icon = new ImageIcon(img);



    // ==========================
    // Top Section
    // ==========================

    JPanel top = new JPanel(
            new FlowLayout(
                    FlowLayout.CENTER,
                    12,
                    5));

    top.setOpaque(false);


    JLabel lblIcon = new JLabel(icon);


    JLabel lblTitle = new JLabel(title);

    lblTitle.setFont(
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    24));

    lblTitle.setForeground(Color.WHITE);


    top.add(lblIcon);
    top.add(lblTitle);



    // ==========================
    // Value
    // ==========================

    JLabel lblValue = new JLabel(
            value,
            SwingConstants.CENTER);

    lblValue.setFont(
            new Font(
                    "Segoe UI",
                    Font.BOLD,
                    48));

    lblValue.setForeground(Color.WHITE);



    // ==========================
    // Add Components
    // ==========================

    card.add(top, BorderLayout.NORTH);

    card.add(lblValue, BorderLayout.CENTER);

    return card;

}
}

        
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RankFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JLabel lblTotal;
    private JLabel lblHighest;

    public RankFrame() {

        setTitle("Student Ranking");
        setSize(900,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ==========================
        // Title
        // ==========================

        JLabel title = new JLabel(
                "STUDENT RANKING",
                SwingConstants.CENTER);

        title.setFont(new Font(
                "Segoe UI",
                Font.BOLD,
                30));

        title.setForeground(
                new Color(33,150,243));

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        20,10,20,10));

        add(title, BorderLayout.NORTH);

        // ==========================
        // Table
        // ==========================

        String[] columns = {

                "Rank",
                "ID",
                "Name",
                "Age",
                "Marks"

        };

        model = new DefaultTableModel(columns,0){

            @Override
            public boolean isCellEditable(
                    int row,
                    int column){

                return false;

            }

        };

        table = new JTable(model);

        table.setRowHeight(38);

        table.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15));

        table.getTableHeader().setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15));

        table.getTableHeader().setBackground(
                new Color(33,150,243));

        table.getTableHeader().setForeground(
                Color.WHITE);

        table.setSelectionBackground(
                new Color(174,214,241));

        table.setGridColor(
                new Color(220,220,220));

        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(1,1));

        // ==========================
        // Center Alignment
        // ==========================

        DefaultTableCellRenderer center =
                new DefaultTableCellRenderer();

        center.setHorizontalAlignment(
                SwingConstants.CENTER);

        for(int i=0;i<table.getColumnCount();i++){

            table.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(center);

        }

        JScrollPane scroll =
                new JScrollPane(table);

        scroll.getViewport().setBackground(
                Color.WHITE);

        add(scroll, BorderLayout.CENTER);

                // ==========================
        // Status Bar
        // ==========================

        JPanel statusPanel = new JPanel(new GridLayout(1,2,20,0));

        statusPanel.setBackground(new Color(245,247,250));

        statusPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,15,10,15));


        // --------------------------
        // Total Students PNG Icon
        // --------------------------

        ImageIcon totalIcon =
                new ImageIcon("icons/TotalStudents.png");

        Image totalImg =
                totalIcon.getImage().getScaledInstance(
                        24,
                        24,
                        Image.SCALE_SMOOTH);

        totalIcon = new ImageIcon(totalImg);


        // --------------------------
        // Highest Marks PNG Icon
        // --------------------------

        ImageIcon highestIcon =
                new ImageIcon("icons/Highest.png");

        Image highestImg =
                highestIcon.getImage().getScaledInstance(
                        24,
                        24,
                        Image.SCALE_SMOOTH);

        highestIcon = new ImageIcon(highestImg);


        // --------------------------
        // Labels
        // --------------------------

        lblTotal = new JLabel(
                "Total Ranked Students : 0",
                totalIcon,
                JLabel.LEFT);

        lblTotal.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15));

        lblTotal.setIconTextGap(10);


        lblHighest = new JLabel(
                "Highest Marks : 0.00",
                highestIcon,
                JLabel.RIGHT);

        lblHighest.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15));

        lblHighest.setHorizontalTextPosition(
                SwingConstants.RIGHT);

        lblHighest.setIconTextGap(10);


        statusPanel.add(lblTotal);
        statusPanel.add(lblHighest);

        add(statusPanel, BorderLayout.SOUTH);


        // ==========================
        // Load Data
        // ==========================

        loadRanking();

        styleTable();

    }

    // ==========================
// Load Ranking
// ==========================

private void loadRanking() {

    model.setRowCount(0);

    try {

        Connection con = DBConnection.getConnection();

        String sql =
                "SELECT * FROM students ORDER BY marks DESC";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        int rank = 1;

        while (rs.next()) {

            ImageIcon rankIcon = null;

            switch (rank) {

                case 1:
                    rankIcon = new ImageIcon("icons/Gold.png");
                    break;

                case 2:
                    rankIcon = new ImageIcon("icons/Silver.png");
                    break;

                case 3:
                    rankIcon = new ImageIcon("icons/Bronze.png");
                    break;
            }

            Object rankValue;

            if(rankIcon != null){

                Image img = rankIcon.getImage().getScaledInstance(
                        24,
                        24,
                        Image.SCALE_SMOOTH);

                rankValue = new ImageIcon(img);

            }else{

                rankValue = String.valueOf(rank);

            }

            model.addRow(new Object[]{

                    rankValue,
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    String.format("%.2f",
                            rs.getDouble("marks"))

            });

            rank++;

        }

        // Update Status Bar

        lblTotal.setText(
                "Total Ranked Students : "
                        + model.getRowCount());

        if(model.getRowCount() > 0){

            lblHighest.setText(
                    "Highest Marks : "
                    + model.getValueAt(0,4));

        }

        rs.close();
        ps.close();
        con.close();

    }

    catch (Exception ex){

        ex.printStackTrace();

        JOptionPane.showMessageDialog(
                this,
                "Unable to load ranking!",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);

    }

    // Column Width

    table.getColumnModel().getColumn(0)
            .setPreferredWidth(70);

    table.getColumnModel().getColumn(1)
            .setPreferredWidth(70);

    table.getColumnModel().getColumn(2)
            .setPreferredWidth(250);

    table.getColumnModel().getColumn(3)
            .setPreferredWidth(80);

    table.getColumnModel().getColumn(4)
            .setPreferredWidth(100);

}

     // ==========================
// Modern Table Renderer
// ==========================

private void styleTable() {

    table.setDefaultRenderer(
            Object.class,
            new DefaultTableCellRenderer() {

        @Override
        public Component getTableCellRendererComponent(

                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            JLabel label =
                    (JLabel) super.getTableCellRendererComponent(
                            table,
                            value,
                            isSelected,
                            hasFocus,
                            row,
                            column);

            label.setHorizontalAlignment(
                    SwingConstants.CENTER);

            // ==========================
            // Rank PNG Icons
            // ==========================

            if(column == 0 && value instanceof ImageIcon){

                label.setIcon((ImageIcon)value);
                label.setText("");

            }else{

                label.setIcon(null);

                if(value != null){
                    label.setText(value.toString());
                }else{
                    label.setText("");
                }

            }

            // ==========================
            // Row Colors
            // ==========================

            if(!isSelected){

                if(row == 0){

                    label.setBackground(
                            new Color(255,248,220));   // Gold

                }

                else if(row == 1){

                    label.setBackground(
                            new Color(240,240,240));   // Silver

                }

                else if(row == 2){

                    label.setBackground(
                            new Color(255,228,196));   // Bronze

                }

                else if(row % 2 == 0){

                    label.setBackground(Color.WHITE);

                }

                else{

                    label.setBackground(
                            new Color(248,249,250));

                }

            }

            label.setOpaque(true);

            return label;

        }

    });

    table.getColumnModel().getColumn(0).setCellRenderer(
    new DefaultTableCellRenderer() {

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            JLabel lbl = new JLabel();
            lbl.setHorizontalAlignment(SwingConstants.CENTER);

            if (value instanceof ImageIcon) {
                lbl.setIcon((ImageIcon) value);
            } else {
                lbl.setText(String.valueOf(value));
            }

            lbl.setOpaque(true);

            if (!isSelected) {
                if (row == 0)
                    lbl.setBackground(new Color(255,248,220));
                else if (row == 1)
                    lbl.setBackground(new Color(240,240,240));
                else if (row == 2)
                    lbl.setBackground(new Color(255,228,196));
                else if (row % 2 == 0)
                    lbl.setBackground(Color.WHITE);
                else
                    lbl.setBackground(new Color(248,249,250));
            }

            return lbl;
        }
    });

}
}
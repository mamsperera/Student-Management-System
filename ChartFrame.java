import javax.swing.*;
import java.awt.Color;
import java.awt.Paint;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartFrame extends JFrame {

    public ChartFrame() {

        setTitle("Student Charts");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT name, marks FROM students ORDER BY marks DESC";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dataset.addValue(
                        rs.getDouble("marks"),
                        "Marks",
                        rs.getString("name"));

            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Create Chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Student Marks",
                "Students",
                "Marks",
                dataset);

        // Plot
        CategoryPlot plot = chart.getCategoryPlot();

        // Custom Renderer
        BarRenderer renderer = new BarRenderer() {

            @Override
            public Paint getItemPaint(int row, int column) {

                Number value = dataset.getValue(row, column);

                if (value.doubleValue() >= 80) {
                    return new Color(46, 204, 113); // Green
                } else if (value.doubleValue() >= 60) {
                    return new Color(241, 196, 15); // Yellow
                } else {
                    return new Color(231, 76, 60); // Red
                }
            }
        };

        plot.setRenderer(renderer);

        renderer.setDefaultToolTipGenerator(
                new StandardCategoryToolTipGenerator());

        // Chart Panel
        ChartPanel panel = new ChartPanel(chart);

        setContentPane(panel);

        setVisible(true);
    }
}
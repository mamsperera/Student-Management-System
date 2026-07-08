import javax.swing.*;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartFrame extends JFrame {

    public LineChartFrame() {

        setTitle("Student Marks Trend");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT name, marks FROM students ORDER BY id";

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

        JFreeChart chart = ChartFactory.createLineChart(
                "Student Marks Trend",
                "Students",
                "Marks",
                dataset);

        CategoryPlot plot = chart.getCategoryPlot();

        LineAndShapeRenderer renderer =
                (LineAndShapeRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, new Color(52, 152, 219)); // Blue
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShapesFilled(0, true);

        ChartPanel panel = new ChartPanel(chart);

        setContentPane(panel);

        setVisible(true);
    }
}

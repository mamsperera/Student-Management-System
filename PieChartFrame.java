import javax.swing.*;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartFrame extends JFrame {

    public PieChartFrame() {

        setTitle("Grade Distribution");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultPieDataset dataset = new DefaultPieDataset();

        int A = 0;
        int B = 0;
        int C = 0;
        int D = 0;
        int F = 0;

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT marks FROM students");

            while (rs.next()) {

                double marks = rs.getDouble("marks");

                if (marks >= 75)
                    A++;
                else if (marks >= 65)
                    B++;
                else if (marks >= 55)
                    C++;
                else if (marks >= 45)
                    D++;
                else
                    F++;
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        dataset.setValue("A Grade", A);
        dataset.setValue("B Grade", B);
        dataset.setValue("C Grade", C);
        dataset.setValue("D Grade", D);
        dataset.setValue("F Grade", F);

        JFreeChart chart = ChartFactory.createPieChart(

                "Student Grade Distribution",

                dataset,

                true,

                true,

                false);

        PiePlot plot = (PiePlot) chart.getPlot();

        plot.setSectionPaint("A Grade", new Color(46, 204, 113));
        plot.setSectionPaint("B Grade", new Color(52, 152, 219));
        plot.setSectionPaint("C Grade", new Color(241, 196, 15));
        plot.setSectionPaint("D Grade", new Color(230, 126, 34));
        plot.setSectionPaint("F Grade", new Color(231, 76, 60));

        ChartPanel panel = new ChartPanel(chart);

        setContentPane(panel);

        setVisible(true);
    }

}
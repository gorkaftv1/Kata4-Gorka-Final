package software.ulpgc.app;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import software.ulpgc.arquitecture.model.Barchart;
import software.ulpgc.arquitecture.view.BarchartDisplay;

import javax.swing.*;
import java.awt.*;

public class JFreeBarcahrtDisplay extends JPanel implements BarchartDisplay {
    private final Component selector;

    public JFreeBarcahrtDisplay(Component selector){
        setLayout(new BorderLayout());
        this.selector = selector;
    }

    @Override
    public void show(Barchart barchart) {
        removeAll();
        add(new ChartPanel(adapt(barchart)));
        add(selector, BorderLayout.SOUTH);
        revalidate();

    }

    private JFreeChart adapt(Barchart barchart) {
        return ChartFactory.createBarChart(barchart.title(), barchart.xAxis(), barchart.yAxis(), datasetOf(barchart));
    }

    private CategoryDataset datasetOf(Barchart barchart) {
        DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
        for(String n : barchart.keySet()) defaultCategoryDataset.addValue(barchart.get(n), "", n);
        return defaultCategoryDataset;
    }
}

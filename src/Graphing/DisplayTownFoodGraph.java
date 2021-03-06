package Graphing;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class DisplayTownFoodGraph extends JFrame {
    //build the chart
    public DisplayTownFoodGraph(String appTitle, ArrayList<Integer> foodPerWeek) {
        super(appTitle);
        // Create Dataset
        CategoryDataset dataset = prepDataset(foodPerWeek);
        //Create chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Food per Week", //Chart Title
                "week", // Category axis
                "Units of food", // Value axis
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );
        CategoryPlot p = chart.getCategoryPlot();
        CategoryAxis axis = p.getDomainAxis();
        Font font = new Font("Dialog", Font.PLAIN, 9);
        axis.setTickLabelFont(font);
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    //prepares and returns a Dataset object using the vector of food stores per week
    //week identified by index
    private CategoryDataset prepDataset(ArrayList<Integer> foodPerWeek) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String week = "";
        String season = ""; //thought this might be useful to display
        //we will have to add extra features for variability of seasons
        for (int i = 0; i < foodPerWeek.size(); i++) {
            week = String.valueOf(i);
            if (i < 52 / 4) {
                season = "winter";
            } else if (i < 52 / 2) {
                season = "spring";
            } else if (i < 52 * 0.75) {
                season = "summer";
            } else {
                season = "fall";
            }

            dataset.addValue(foodPerWeek.get(i), season, week);
        }
        return dataset;
    }
}
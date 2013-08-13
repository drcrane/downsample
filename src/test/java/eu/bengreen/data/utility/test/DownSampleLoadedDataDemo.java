package eu.bengreen.data.utility.test;

import java.io.IOException;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import eu.bengreen.data.utility.DownSampleImpl;

public class DownSampleLoadedDataDemo extends ApplicationFrame {
	private static final long serialVersionUID = -5394212897448426523L;

	public DownSampleLoadedDataDemo(String title) throws IOException {
        super(title);
        Number[][] rawdata = LoadData.loadData("src/test/resources/exampledata1.txt");
        Number[][] rawdata2 = LoadData.loadData("src/test/resources/exampledata2.txt");
        Number[][] downsampled = DownSampleImpl.largestTriangleThreeBucketsTime(rawdata, 500);
        
        long prev = 0;
        for (int i = 0; i < downsampled.length; i++) {
        	System.out.println(": " + downsampled[i][0].longValue() + " " + (downsampled[i][0].longValue() - prev));
        	prev = downsampled[i][0].longValue();
        }
        
        JPanel chartPanel;
    	XYSeries xySeries2 = new XYSeries("raw");
    	XYSeries xySeries3 = new XYSeries("raw no gap");
    	XYSeries xySeries = new XYSeries("downsampled");
    	for (Number[] sample : rawdata) {
    		xySeries2.add(sample[0], sample[1]);
    	}
    	for (Number[] sample : downsampled) {
    		xySeries.add(sample[0], sample[1]);
    	}
    	for (Number[] sample : rawdata2) {
    		xySeries3.add(sample[0], sample[1]);
    	}

    	XYSeriesCollection seriesCollection = new XYSeriesCollection();
    	seriesCollection.addSeries(xySeries2);
    	seriesCollection.addSeries(xySeries);
    	seriesCollection.addSeries(xySeries3);
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Down-sampling Demo",     // chart title
            "X",                      // x axis label
            "Y",                      // y axis label
            seriesCollection,         // data
            PlotOrientation.VERTICAL, 
            true,                     // include legend
            false,                    // tooltips
            false                     // urls
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.getDomainAxis().setLowerMargin(0.0);
        plot.getDomainAxis().setUpperMargin(0.0);
        
        chartPanel = new ChartPanel(chart);
        
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

    public static void main(String[] args) throws IOException {
    	DownSampleLoadedDataDemo demo = new DownSampleLoadedDataDemo("Down-sample demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}

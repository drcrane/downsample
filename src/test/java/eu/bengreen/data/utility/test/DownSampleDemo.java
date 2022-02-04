package eu.bengreen.data.utility.test;

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

/**
* 
*/
public class DownSampleDemo extends ApplicationFrame {
	private static final long serialVersionUID = -5914913374691381410L;

	public DownSampleDemo(String title) {
        super(title);
        Number[][] rawdata = DownSampleImplTest.getRaw();
        Number[][] downsampled = DownSampleImplTest.getDownSampled();
        JPanel chartPanel;
        XYSeries xySeries = new XYSeries("downsampled");
    	for (Number[] sample : downsampled) {
    		xySeries.add(sample[0], sample[1]);
    	}
    	XYSeries xySeries2 = new XYSeries("raw");
    	for (Number[] sample : rawdata) {
    		xySeries2.add(sample[0], sample[1]);
    	}

    	XYSeriesCollection seriesCollection = new XYSeriesCollection();
    	seriesCollection.addSeries(xySeries);
    	seriesCollection.addSeries(xySeries2);
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

    public static void main(String[] args) {
        DownSampleDemo demo = new DownSampleDemo(
                "Down-sample demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

}
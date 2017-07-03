package pracownia.badawcza;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import pracownia.badawcza.model.ErrorDTO;

import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by tomas on 09.06.2017.
 */
public class ChartGenerator {

    public static final int WIDTH = 1366;
    public static final int HEIGHT = 768;

    private XYSeriesCollection dataset = new XYSeriesCollection();
    private JFreeChart chart;

    public ChartGenerator() {

    }

    public void showGraph() {
        final ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));
        final ApplicationFrame frame = new ApplicationFrame("Wykres");
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private JFreeChart createChart() {
        chart = ChartFactory.createScatterPlot(
                "Zmiana wielkości błędu w poszczególnych iteracjach",
                "Krok",
                "Wielkość błędu (algorytm - oryginał)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShape(0, new Ellipse2D.Double(-1, -1, 2, 2));
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesLinesVisible(1, false);
        plot.setRenderer(renderer);
        return chart;
    }

    public void createDataset(List<ErrorDTO> errors, String streamName) {
        final XYSeries errorsChart = new XYSeries(streamName);
        final XYSeries lastBucketSizeSeries = new XYSeries("Wielkość ostatniego kubełka / 2 (max błąd)");

        for (ErrorDTO error : errors) {
            errorsChart.add(error.getStep(), Math.abs(error.getHistogramCount() - error.getOriginalCount()));
            lastBucketSizeSeries.add(error.getStep(), error.getLastBucketSize());
        }

        dataset.addSeries(errorsChart);
        dataset.addSeries(lastBucketSizeSeries);
    }

    public void saveToFile(String fileName) {
        try {
            if (chart != null) {
                ChartUtilities.saveChartAsJPEG(new File(fileName), chart, WIDTH, HEIGHT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

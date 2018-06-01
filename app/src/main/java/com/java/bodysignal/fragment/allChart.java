package com.java.bodysignal.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.java.bodysignal.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


public class allChart extends Fragment {
    private GraphicalView mChartView;
    private View view;
    // 라인그래프의 가로축
    private String[] bar = new String[]{
            "10분", "20분", "30분", "40분", "50분", "60분"
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_allchart, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawChart_bar();
    }

    // 라인그래프
    private void drawChart_bar(){

        int[] x = { 0,1,2,3,4,5 };
        int[] temperature = { 2000,2500,2700,3000,2800,3500,3700,3800};
        int[] pulse = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400 };


        // Creating an  XYSeries for temperature
        XYSeries temperatureSeries = new XYSeries("체온");


        // Creating an  XYSeries for pulse
        XYSeries pulseSeries = new XYSeries("맥박");


        // Adding data to temperature and pulse Series
        for(int i=0;i<x.length;i++){

            temperatureSeries.add(x[i], temperature[i]);
            pulseSeries.add(x[i],pulse[i]);

        }


        // Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();


        // Adding temperature Series to the dataset
        dataset.addSeries(temperatureSeries);


        // Adding pulse Series to dataset
        dataset.addSeries(pulseSeries);


        // Creating XYSeriesRenderer to customize temperatureSeries
        XYSeriesRenderer temperatureRenderer = new XYSeriesRenderer();

        temperatureRenderer.setColor(Color.GREEN);

        temperatureRenderer.setPointStyle(PointStyle.CIRCLE);

        temperatureRenderer.setFillPoints(true);

        temperatureRenderer.setLineWidth(8);

        temperatureRenderer.setDisplayChartValues(true);


        // Creating XYSeriesRenderer to customize pulseSeries
        XYSeriesRenderer pulseRenderer = new XYSeriesRenderer();

        pulseRenderer.setColor(Color.RED);

        pulseRenderer.setPointStyle(PointStyle.CIRCLE);

        pulseRenderer.setFillPoints(true);

        pulseRenderer.setLineWidth(8);

        pulseRenderer.setDisplayChartValues(true);


        // Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();

        multiRenderer.setXLabels(0);

        multiRenderer.setChartTitle("라인그래프");

        multiRenderer.setXTitle("시간");

        multiRenderer.setYTitle("수위");

        multiRenderer.setZoomButtonsVisible(true);

        //Setting background color of the graph to transparent
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);

        //Setting margin color of the graph to transparent
        multiRenderer.setMarginsColor(Color.WHITE);
        multiRenderer.setApplyBackgroundColor(true);


        for(int i=0;i<x.length;i++){

            multiRenderer.addXTextLabel(i+1, bar[i]);

        }


        // Adding temperatureRenderer and pulseRenderer to multipleRenderer

        // Note: The order of adding dataseries to dataset and renderers to multipleRenderer

        // should be same

        multiRenderer.addSeriesRenderer(temperatureRenderer);

        multiRenderer.addSeriesRenderer(pulseRenderer);


        // Creating an intent to plot line chart using dataset and multipleRenderer

        // Intent intent = ChartFactory.getLineChartIntent(getBaseContext(), dataset, multiRenderer);



        // Start Activity

        //startActivity(intent);


        if (mChartView == null) {
            LinearLayout layout = (LinearLayout) view.findViewById(R.id.chart_line);

            mChartView = ChartFactory.getLineChartView(getActivity(), dataset, multiRenderer);

            multiRenderer.setClickEnabled(true);

            multiRenderer.setSelectableBuffer(10);
            layout.addView(mChartView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,

                    LinearLayout.LayoutParams.MATCH_PARENT));

        } else {

            mChartView.repaint();

        }

    }

}
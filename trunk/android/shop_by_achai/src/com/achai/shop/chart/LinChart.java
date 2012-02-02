
/**
 * 下午2:51:13
 */
package com.achai.shop.chart;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.Log;

/**
 * @author Tom_achai
 * @time 下午2:51:13
 */
public class LinChart extends ShopAbstractChart {

	/* (non-Javadoc)
	 * @see com.achai.shop.chart.IChart#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.achai.shop.chart.IChart#getDesc()
	 */
	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.achai.shop.chart.IChart#execute(android.content.Context)
	 */
	@Override
	public Intent execute(Context context) {
		
		 String[] titles = new String[] { "Crete", "Corfu", "Thassos", "Skiathos" };
		    List<double[]> x = new ArrayList<double[]>();
		    for (int i = 0; i < titles.length; i++) {
		      x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		    }
		    List<double[]> values = new ArrayList<double[]>();
		    values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2,
		        13.9 });
		    values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
		    values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
		    values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });
		    int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };
		    PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND,
		        PointStyle.TRIANGLE, PointStyle.SQUARE };
		    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		    int length = renderer.getSeriesRendererCount();
		    for (int i = 0; i < length; i++) {
		      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
		    }
		    setChartSettings(renderer, "Average temperature", "Month", "Temperature", 0.5, 12.5, -10, 40,
		        Color.LTGRAY, Color.LTGRAY);
		    //-------数据设置
		    renderer.setXLabels(12);
		    renderer.setYLabels(10);
		    renderer.setShowGrid(true);
		    renderer.setXLabelsAlign(Align.RIGHT);
		    renderer.setYLabelsAlign(Align.RIGHT);
		    renderer.setZoomButtonsVisible(false);
		    //两个一起才可以有效
		    renderer.setBackgroundColor(Color.BLUE);
		    renderer.setApplyBackgroundColor(true);
//		  刻度设置失败
//		    renderer.setScale(2);
		  //  renderer.setExternalZoomEnabled(false);
		 //   renderer.setPanEnabled(false);
		    //设置是否显示坐标轴线
		   // renderer.setShowAxes(false);
		    //设置面板是否可以移动
		    renderer.setPanEnabled(false, false);
		    //设置是否支持两点缩放
		    renderer.setZoomEnabled(false, false);
		    
		    
//		    renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
//		    renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });

		    Intent intent = ChartFactory.getLineChartIntent(context, buildDataset(titles, x, values),
		        renderer, "Average temperature");
		    return intent;
	}

	@Override
	public GraphicalView chartView(Context context) {
		 String[] titles = new String[] { "2011", "2012" };
		    List<double[]> x = new ArrayList<double[]>();
		    for (int i = 0; i < titles.length; i++) {
		      x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		    }
		    List<double[]> values = new ArrayList<double[]>();
		    values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2,
		        13.9 });
		    values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
		    int[] colors = new int[] { Color.BLUE, Color.GREEN };
		    PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND};
		    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		    setChartSettings(renderer, "游戏阿柴线性图测试", "Month", "Temperature", 0.5, 12.5, -10, 40,
			        Color.LTGRAY, Color.LTGRAY);
		    //设置面板是否可以移动
		    renderer.setPanEnabled(false, false);
		    //设置是否支持两点缩放
		    renderer.setZoomEnabled(false, false);
		    
		    renderer.setXLabelsAlign(Align.CENTER);
		    renderer.setYLabelsAlign(Align.RIGHT);
		    
		    //用于设置合适的坐标轴刻度显示
		    renderer.setXLabels(12);
		    renderer.setYLabels(10);
		    renderer.setChartTitleTextSize(20);
		    
		    
		  int  length = renderer.getSeriesRendererCount();
		  Log.d("chart", ""+length);
		    for (int i = 0; i < length; i++) {
		      XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
		      
		      seriesRenderer.setDisplayChartValues(true);
		   
		    }
		GraphicalView lineView = ChartFactory.getLineChartView(context, buildDataset(titles, x, values), renderer);
		return lineView;
	}

}

package com.achai.shop.chart;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

public class BarChart extends ShopAbstractChart {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "柱状图 测试";
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return "测试 使用 柱状图";
	}

	@Override
	public Intent execute(Context context) {
		// 图例
		String[] titles = new String[] { "2010", "2011" };
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 5230, 7300, 9240, 10540, 7900, 9200, 12030,
				11200, 9500, 10500, 11600, 13500 });
		values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200,
				22030, 21200, 19500, 15500, 12600, 14000 });
		int[] colors = new int[] { Color.RED, Color.BLUE };
		// 1,渲染对象
		XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
		renderer.setOrientation(Orientation.HORIZONTAL);
		// 2 设置图表属性
		setChartSettings(renderer, "柱状图测试", "Month", "sales", 0.5, 12.5, 0,
				24000, Color.GRAY, Color.LTGRAY);
		renderer.setShowGrid(true);
		renderer.setXLabels(1);
		renderer.setYLabels(10);
		renderer.addXTextLabel(1, "Jan");
		renderer.addXTextLabel(3, "Mar");
		renderer.addXTextLabel(5, "May");
		renderer.addXTextLabel(7, "Jul");
		renderer.addXTextLabel(10, "Oct");
		renderer.addXTextLabel(12, "Dec");
		int length = renderer.getSeriesRendererCount();
		// 显示柱状图信息
		// for (int i = 0; i < length; i++) {
		// SimpleSeriesRenderer seriesRenderer = renderer
		// .getSeriesRendererAt(i);
		// seriesRenderer.setDisplayChartValues(true);
		// }
		return ChartFactory.getBarChartIntent(context,
				buildBarDataset(titles, values), renderer, Type.DEFAULT);
	}

	@Override
	public GraphicalView chartView(Context context) {
		// 图例
		String[] titles = new String[] { "拉箱", "手提包", "背囊" };
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 5230, 7300, 9240, 10540, 7900, 9200, 12030,
				11200, 9500, 10500, 11600, 13500 });
		values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200,
				22030, 21200, 19500, 15500, 12600, 14000 });
		values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200,
				22030, 21200, 19500, 15500, 12600, 14000 });
		int[] colors = new int[] { Color.RED, Color.BLUE, Color.CYAN };
		// 1,渲染对象
		XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
		renderer.setOrientation(Orientation.HORIZONTAL);
		// 2 设置图表属性
		setChartSettings(renderer, "柱状图测试", "Month", "sales", 0.5, 12.5, 0,
				24000, Color.RED, Color.BLUE);
		renderer.setShowGrid(true);
		renderer.setXLabels(1);
		// renderer.setYLabels(10);
		renderer.addXTextLabel(1, "Jan");
		renderer.addXTextLabel(2, "二月");

		renderer.addXTextLabel(3, "Mar");
		renderer.addXTextLabel(5, "May");
		renderer.addXTextLabel(7, "Jul");
		renderer.addXTextLabel(10, "Oct");
		renderer.addXTextLabel(12, "Dec");
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setBarSpacing(0.1);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer ser = renderer.getSeriesRendererAt(i);
			ser.setChartValuesTextSize(12);
			ser.setChartValuesTextAlign(Align.RIGHT);
			ser.setChartValuesSpacing(1);

			ser.setDisplayChartValues(true);
		}

		return ChartFactory.getBarChartView(context,
				buildBarDataset(titles, values), renderer, Type.DEFAULT);
	}

}

package com.achai.shop.chart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		
		return null;
	}

	/**
	 * 
	 * @param context
	 * @param titles
	 * @param values
	 * @return
	 */
	public GraphicalView getChartView(Context context, List<String> titles,
			List<List<Double>> values,Map<String, String> settings) {
		// 1,图例颜色根据titles的数量进行填充
		int colorLength = titles.size();

		// 2,渲染图例用颜色
		List<Integer> rendererColor = new ArrayList<Integer>();
		for (int i = 0; i < colorLength; i++) {
			rendererColor.add(BarChartValues.COLORS[i]);
		}

		// 3,创建渲染对象
		XYMultipleSeriesRenderer renderer = buildBarRenderer(rendererColor);


		//4, 设置显示数据
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer ser = renderer.getSeriesRendererAt(i);
			//设置图上的字体大小
			ser.setChartValuesTextSize(14);
			ser.setChartValuesTextAlign(Align.CENTER);
			// ser.setChartValuesSpacing(1);
			ser.setDisplayChartValues(true);
		}
		// 5 设置图表属性
		setBarChartXSetting(renderer, Integer.valueOf(settings.get(BarChartValues.SHOWTYPE)),settings);
		return ChartFactory.getBarChartView(context,
				buildBarDataset(titles, values), renderer, Type.DEFAULT);

	}

	@Override
	public GraphicalView chartView(Context context) {
		
		return null;
	}

}

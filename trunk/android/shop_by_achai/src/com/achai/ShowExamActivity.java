package com.achai;

import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.achai.shop.chart.LinChart;

public class ShowExamActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 1, 构造显示用渲染图
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		// 2,进行显示
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		// 2.1, 构建数据
		Random r = new Random();
		for (int i = 0; i < 2; i++) {
			XYSeries series = new XYSeries("test" + (i + 1));
			// 填充数据
			for (int k = 0; k < 10; k++) {
				// 填x,y值
				series.add(k, 20 + r.nextInt() % 100);
			}
			// 需要绘制的点放进dataset中
			dataset.addSeries(series);
		}
		// 3, 对点的绘制进行设置
		XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
		// 3.1设置颜色
		xyRenderer.setColor(Color.BLUE);
		// 3.2设置点的样式
		xyRenderer.setPointStyle(PointStyle.SQUARE);
		// 3.3, 将要绘制的点添加到坐标绘制中
		renderer.addSeriesRenderer(xyRenderer);
		// 3.4,重复 1~3的步骤绘制第二个系列点
		xyRenderer = new XYSeriesRenderer();
		xyRenderer.setColor(Color.RED);
		xyRenderer.setPointStyle(PointStyle.CIRCLE);
		renderer.addSeriesRenderer(xyRenderer);

		// Intent intent = new LinChart().execute(this);
		Intent intent = ChartFactory
				.getLineChartIntent(this, dataset, renderer);
		startActivity(intent);

	}

	/**
	 * 设置显示数据
	 * 
	 * @return
	 */
	private XYMultipleSeriesDataset getBarDataset() {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		final int nr = 10;
		Random r = new Random();

		// 柱状图的数据
		for (int i = 0; i < 2; i++) {
			CategorySeries series = new CategorySeries("package" + (i + 1));
			// 把数据添加到柱子上
			for (int k = 0; k < nr; k++) {

				series.add(100 + r.nextInt() % 100);
			}
			dataset.addSeries(series.toXYSeries());
		}

		return dataset;
	}

	/**
	 * 取得渲染对象
	 * 
	 * @return
	 */
	private XYMultipleSeriesRenderer getBarDemoRenderer() {
		XYMultipleSeriesRenderer render = new XYMultipleSeriesRenderer();
		// 1,渲染图例
		XYSeriesRenderer r = new XYSeriesRenderer();
		r.setColor(Color.BLUE);
		render.addSeriesRenderer(r);
		// 2,添加到渲染里面

		// 重复---
		XYSeriesRenderer r1 = new XYSeriesRenderer();
		r1.setColor(Color.GREEN);
		render.addSeriesRenderer(r1);
		// ---
		// 3,添加到设置当中
		setChartSettings(render);

		return render;
	}

	/**
	 * 配置显示
	 * 
	 * @param render
	 */
	private void setChartSettings(XYMultipleSeriesRenderer render) {
		render.setChartTitle("游戏阿柴 char demo");

		render.setXTitle("时间");
		render.setYTitle("营业额");
		// 设置刻度区间
		render.setXAxisMin(0.5);
		render.setXAxisMax(10.5);
		render.setYAxisMin(0);
		render.setYAxisMax(210);
	}
}

package com.achai;

import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;

import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;

import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.achai.shop.chart.LinChart;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class BarExActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 1, 构造显示用渲染图
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		// 2,进行显示
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		// 2.1, 创建柱状图数据
		Random r = new Random();
		for (int i = 0; i < 2; i++) {
			// 注意,这里与昨天的XYSeries 有一点不同!!这里使用CategroySeries
			CategorySeries series = new CategorySeries("test" + (i + 1));
			// 填充数据
			for (int k = 0; k < 10; k++) {
				// 直接填入需要显示的数据,即:Y轴的值
				series.add(Math.abs(20 + r.nextInt() % 100));
			}
			// 这里要进行转换
			dataset.addSeries(series.toXYSeries());
		}
		// 3, 对点的绘制进行设置
		XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
		// 3.1设置颜色
		xyRenderer.setColor(Color.BLUE);

		// 3.2设置点的样式
		// xyRenderer.setPointStyle(PointStyle.SQUARE);
		// 3.3, 将要绘制的点添加到坐标绘制中
		renderer.addSeriesRenderer(xyRenderer);
		// 3.4,重复 3.1~3.3的步骤绘制第二组系列点
		xyRenderer = new XYSeriesRenderer();
		xyRenderer.setColor(Color.RED);
		// xyRenderer.setPointStyle(PointStyle.CIRCLE);
		renderer.addSeriesRenderer(xyRenderer);
		// 注意这里不要x,y min 不要相同
		// 这里用一种内置的设置x,y范围的方法
		//顺序是:minX, maxX, minY, maxY
		double[] range = { 0, 10, 1, 200 };
		renderer.setRange(range);
		// 等价于:
		// -------------------
		// renderer.setXAxisMin(0);
		// renderer.setXAxisMax(10);
		// renderer.setYAxisMin(1);
		// renderer.setYAxisMax(200);
		// -------------------
		

		// 设置合适的刻度,在轴上显示的数量是 MAX / labels
		renderer.setXLabels(10);
		renderer.setYLabels(10);

		// 设置x,y轴显示的排列,默认是 Align.CENTER
		renderer.setXLabelsAlign(Align.CENTER);
		renderer.setYLabelsAlign(Align.RIGHT);

		// 设置坐标轴,轴的颜色
		renderer.setAxesColor(Color.RED);
		// 显示网格
		renderer.setShowGrid(true);
		// 设置x,y轴上的刻度的颜色
		renderer.setLabelsColor(Color.BLACK);

		// 设置页边空白的颜色
		renderer.setMarginsColor(Color.CYAN);
		// 设置是否显示,坐标轴的轴,默认为 true
		renderer.setShowAxes(true);

		// 设置条形图之间的距离
		renderer.setBarSpacing(0.1);
		int length = renderer.getSeriesRendererCount();

		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer ssr = renderer.getSeriesRendererAt(i);
			// 不知道作者的居中是怎么计算的,默认是Align.CENTER,但是对于两个以上的条形显示
			// 就画在了最右边
			ssr.setChartValuesTextAlign(Align.RIGHT);
			ssr.setChartValuesTextSize(12);
			ssr.setDisplayChartValues(true);
		}
		// Intent intent = new LinChart().execute(this);
		// Intent intent = ChartFactory
		// .getBarChartIntent(this, dataset, renderer, Type.DEFAULT);
		// startActivity(intent);

		LinearLayout barchart = (LinearLayout) findViewById(R.id.barchart);
		GraphicalView mChartView = ChartFactory.getBarChartView(this, dataset,
				renderer, Type.DEFAULT);

		barchart.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));

		// 这里我偷偷的封装了一个 折线图的显示,用作练习吧!把上一章的例子改为用 view
		LinChart lineChart = new LinChart();
		LinearLayout linechart = (LinearLayout) findViewById(R.id.linechart);
		GraphicalView lineView = lineChart.chartView(this);
		linechart.addView(lineView, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
	}
}

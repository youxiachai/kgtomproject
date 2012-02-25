/**
 * 下午12:10:06
 */
package com.achai.shop.chart;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;

import android.graphics.Color;
import android.graphics.Paint.Align;

/**
 * 用于构造图表的数据集和显示设置
 * 
 * @author Tom_achai
 * @time 下午12:10:06
 */
public abstract class ShopAbstractChart implements IChart {

	/**
	 * 用于构造XY图表的数据集
	 * 
	 * @param titles
	 * @param xValues
	 * @param yValues
	 * @return
	 */
	protected XYMultipleSeriesDataset buildDataset(String[] titles,
			List<double[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		addXYSeries(dataset, titles, xValues, yValues, 0);
		return dataset;

	}

	/**
	 * 用于XY图表返回绘制用的坐标点数据集
	 * 
	 * @param dataset
	 * @param titles
	 * @param xValues
	 * @param yValues
	 * @param scale
	 */
	private void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles,
			List<double[]> xValues, List<double[]> yValues, int scale) {
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			XYSeries series = new XYSeries(titles[i], scale);
			double[] xV = xValues.get(i);
			double[] yV = yValues.get(i);
			int seriesLength = xV.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}

	}

	/**
	 * 构建绘制图表的样式
	 * 
	 * @param colors
	 * @param styles
	 * @return
	 */
	protected XYMultipleSeriesRenderer buildRenderer(int[] colors,
			PointStyle[] styles) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		setRenderer(renderer, colors, styles);
		return renderer;
	}

	/**
	 * 设置XY图表的标题和数据点
	 * 
	 * @param renderer
	 * @param colors
	 * @param styles
	 */
	private void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors,
			PointStyle[] styles) {
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setPointSize(5f);
		renderer.setMargins(new int[] { 20, 30, 15, 20 });
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = new XYSeriesRenderer();
			r.setColor(colors[i]);
			r.setPointStyle(styles[i]);
			renderer.addSeriesRenderer(r);
		}

	}

	/**
	 * 设置图表的 标题 x,y 标题 x,y 的区间 x,y 坐标轴颜色 x,y 坐标标题的颜色
	 * 
	 * @param renderer
	 * @param title
	 * @param xTitle
	 * @param yTitle
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 * @param asexColor
	 * @param labelsColor
	 */
	protected void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int asexColor,
			int labelsColor) {
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setAxesColor(asexColor);
		renderer.setLabelsColor(labelsColor);
	}

	/**
	 * 
	 * xy 时间显示
	 * 
	 * @param titles
	 * @param xValues
	 * @param yValues
	 * @return
	 */
	protected XYMultipleSeriesDataset buildDateDataset(String[] titles,
			List<Date[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			TimeSeries series = new TimeSeries(titles[i]);
			Date[] xV = xValues.get(i);
			double[] yV = yValues.get(i);
			int seriesLength = xV.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}
		return dataset;
	}

	/**
	 * 建立图表类别的数据集
	 * 
	 * @param title
	 * @param values
	 * @return
	 */
	protected CategorySeries buildCategoryDataset(String title, double[] values) {
		CategorySeries series = new CategorySeries(title);
		int k = 0;
		for (double value : values) {
			series.add("Project " + (++k), value);
		}

		return series;
	}

	/**
	 * 创建多种类别的数据集
	 * 
	 * @param title
	 * @param titles
	 * @param values
	 * @return
	 */
	protected MultipleCategorySeries buildMutilpleCategoryDataset(String title,
			List<String[]> titles, List<double[]> values) {
		MultipleCategorySeries series = new MultipleCategorySeries(title);
		int k = 0;
		for (double[] value : values) {
			series.add(2012 + k + "", titles.get(k), value);
			k++;
		}
		return series;
	}

	/**
	 * 创建 类别的 渲染(图例)
	 * 
	 * @param colors
	 * @return
	 */
	protected DefaultRenderer buildCategoryRenderer(int[] colors) {
		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setMargins(new int[] { 20, 30, 15, 0 });
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		return renderer;
	}

	/**
	 * 创建柱状图数据集
	 * 
	 * @param titles
	 * @param values
	 * @return
	 */
	protected XYMultipleSeriesDataset buildBarDataset(List<String> titles,
			List<List<Double>> values) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.size();
		for (int i = 0; i < length; i++) {
			CategorySeries series = new CategorySeries(titles.get(i));
			List<Double> v = values.get(i);
			int seriesLength = v.size();
			for (int k = 0; k < seriesLength; k++) {
				series.add(v.get(k));
			}
			dataset.addSeries(series.toXYSeries());
		}

		return dataset;
	}

	/**
	 * 对多个图例数据进行绘图
	 * 
	 * @param colors
	 * @return
	 */
	protected XYMultipleSeriesRenderer buildBarRenderer(List<Integer> colors) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		int length = colors.size();

		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors.get(i));
			renderer.addSeriesRenderer(r);
		}

		return renderer;
	}

	public void updateChartView() {

	}

	protected void setBarChartXSetting(XYMultipleSeriesRenderer renderer,
			int type,Map<String, String> settings) {
		// 1,图表标题
		renderer.setChartTitle(settings.get(BarChartValues.CHARTTITLE));
		// 2,图表x坐标标题
		renderer.setXTitle(settings.get(BarChartValues.XLABSTITLE));

		renderer.setOrientation(Orientation.HORIZONTAL);
		// 设置面板是否可以移动
		// renderer.setPanEnabled(false, false);
		// 设置是否支持两点缩放
		// renderer.setZoomEnabled(false, false);
		// 设置页边空白的颜色
		renderer.setMarginsColor(Color.CYAN);
		renderer.setXLabelsAlign(Align.CENTER);
		renderer.setYLabelsAlign(Align.RIGHT);
		// 设置坐标轴,轴的颜色
		renderer.setAxesColor(Color.RED);
		// 显示网格
		renderer.setShowGrid(true);
		// 设置x,y轴上的刻度的颜色
		renderer.setLabelsColor(Color.BLACK);
		// 设置y标题的排列
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setBarSpacing(0.1);
		renderer.setYLabels(5);
		renderer.setChartTitleTextSize(20);
		// 用于设置合适的坐标轴刻度显示
		// 处理设置柱状图显示个数
		switch (type) {
		case BarChartValues.WEEK:

			// 3,设置区间
			// 顺序是:minX, maxX, minY, maxY
			double[] weekRange = { 0.5, 7.5, 0, 10000 };
			renderer.setRange(weekRange);
			// 4,设置刻度尺的颜色,和标题颜色
			renderer.setAxesColor(Color.RED);
			renderer.setLabelsColor(Color.BLACK);
			// 5,处理X轴显示
			// 不设置为零会出现绘制双数的情况
			renderer.setXLabels(0);
			for (int i = 1; i < BarChartValues.WEEK + 1; i++) {
				renderer.addXTextLabel(i, BarChartValues.DAYVALUE[i]);

			}
			break;
		case BarChartValues.MONTH:

			// 3,设置区间
			// 顺序是:minX, maxX, minY, maxY
			double[] monthRange = { 0.5, 30.5, 0, 10000 };
			renderer.setRange(monthRange);
			// 4,设置刻度尺的颜色,和标题颜色
			renderer.setAxesColor(Color.RED);
			renderer.setLabelsColor(Color.BLACK);
			// 5,处理X轴显示
			// 设置为每月显示30天
			renderer.setXLabels(BarChartValues.MONTH);
			break;
		case BarChartValues.YEAR:
			// 3,设置区间
			// 顺序是:minX, maxX, minY, maxY
			double[] yearRange = { 0.5, 12.5, 0, 10000 };
			renderer.setRange(yearRange);
			// 4,设置刻度尺的颜色,和标题颜色
			renderer.setAxesColor(Color.RED);
			renderer.setLabelsColor(Color.BLACK);
			// 5,处理X轴显示
			// 设置为每月显示30天
			renderer.setXLabels(BarChartValues.YEAR);
			break;
		default:
			break;
		}

	}
}


/**
 * 下午12:10:06
 */
package com.achai.shop.chart;

import java.util.Date;
import java.util.List;

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

/**
 * 用于构造图表的数据集和显示设置
 * @author Tom_achai
 * @time 下午12:10:06
 */
public abstract class ShopAbstractChart implements IChart{
	
	/**
	 * 用于构造XY图表的数据集
	 * @param titles
	 * @param xValues
	 * @param yValues
	 * @return
	 */
	protected XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues, List<double[]> yValues){
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		addXYSeries(dataset, titles, xValues, yValues, 0);
		return dataset;
		
	}

	/**
	 * 用于XY图表返回绘制用的坐标点数据集
	 * @param dataset
	 * @param titles
	 * @param xValues
	 * @param yValues
	 * @param scale
	 */
	private void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles,
			List<double[]> xValues, List<double[]> yValues, int scale) {
		int length = titles.length;
		for(int i=0; i < length; i++){
			XYSeries series = new XYSeries(titles[i], scale);
			double[] xV = xValues.get(i);
			double[] yV = yValues.get(i);
			int seriesLength = xV.length;
			for(int k=0; k<seriesLength; k++){
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}
		
	}
	
	/**
	 * 构建绘制图表的样式
	 * @param colors
	 * @param styles
	 * @return
	 */
	protected XYMultipleSeriesRenderer buildRenderer(int [] colors, PointStyle[] styles){
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		setRenderer(renderer, colors, styles);
		return renderer;
	}

	/**
	 * 设置XY图表的标题和数据点
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
		renderer.setMargins(new int[]{20, 30, 15, 20});
		int length = colors.length;
		for(int i=0; i < length; i++){
			XYSeriesRenderer r = new XYSeriesRenderer();
			r.setColor(colors[i]);
			r.setPointStyle(styles[i]);
			renderer.addSeriesRenderer(r);
		}
		
	}
	
	/**
	 * 设置图表的
	 * 标题
	 * x,y 标题
	 * x,y 的区间
	 * x,y 坐标轴颜色
	 * x,y 坐标标题的颜色
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
	protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
			String yTitle, double xMin, double xMax, double yMin, double yMax, int asexColor, int labelsColor) {
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
	 * @param titles
	 * @param xValues
	 * @param yValues
	 * @return
	 */
	protected XYMultipleSeriesDataset buildDateDataset(String[] titles, List<Date[]> xValues, List<double []> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.length;
		for(int i = 0; i < length; i++){
			TimeSeries series = new TimeSeries(titles[i]);
			Date[] xV = xValues.get(i);
			double[] yV = yValues.get(i);
			int seriesLength = xV.length;
			for(int k=0; k<seriesLength; k++){
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}
		return dataset;
	}
	
	/**
	 * 建立图表类别的数据集
	 * @param title
	 * @param values
	 * @return
	 */
	protected CategorySeries buildCategoryDataset(String title, double[] values) {
		CategorySeries series = new CategorySeries(title);
		int k =0;
		for(double value : values){
			series.add("Project "+ (++k), value);
		}
		
		return series;
	}
	/**
	 * 创建多种类别的数据集
	 * @param title
	 * @param titles
	 * @param values
	 * @return
	 */
	protected MultipleCategorySeries buildMutilpleCategoryDataset(String title, List<String[]> titles, List<double[]> values){
		MultipleCategorySeries series = new MultipleCategorySeries(title);
		int k = 0;
		for(double[] value : values){
			series.add(2012 + k + "", titles.get(k), value);
			k++;
		}
		return series;
	}
	
	/**创建 类别的 渲染(图例)
	 * @param colors
	 * @return
	 */
	protected DefaultRenderer buildCategoryRenderer(int[] colors) {
		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setMargins(new int[] {20, 30, 15, 0});
		for(int color : colors){
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		return renderer;
	}
	
	/**
	 * 创建柱状图数据集
	 * @param titles
	 * @param values
	 * @return
	 */
	protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.length;
		for(int i=0; i < length; i++){
			CategorySeries series = new CategorySeries(titles[i]);
			double[] v = values.get(i);
			int seriesLength = v.length;
			for(int k=0; k < seriesLength; k++){
				series.add(v[k]);
			}
			dataset.addSeries(series.toXYSeries());
		}
		
		return dataset;
	}
	
	/**
	 * 对多个图例数据进行绘图
	 * @param colors
	 * @return
	 */
	protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors){
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		int length = colors.length;
		
		for(int i = 0; i < length; i++){
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			renderer.addSeriesRenderer(r);
		}
		
		return renderer;
	}
	
	
}

package com.achai.shop.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.model.XYValueSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Application;
import android.app.TabActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TabHost;

import com.achai.R;
import com.achai.shop.chart.BarChart;
import com.achai.shop.chart.BarChartValues;
import com.achai.shop.chart.LinChart;
import com.achai.shop.dao.DatabaseHelper;
import com.achai.shop.model.SalesTest;
import com.achai.shop.model.Statistics;
import com.achai.shop.preferences.ShopPreference;
import com.achai.shop.utils.TabMessageListener;
import com.achai.shop.utils.TabValues;
import com.achai.shop.utils.TabsUtils;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

/**
 * @author Tom_achai
 * @date 2012-1-23
 * @time 下午11:43:19
 */
public class ShopMainActivity extends TabActivity implements IShopTabs {

	private TabHost mainHost;
	private Intent statisticIntent;
	private Intent analysisIntent;
	private Intent recordsIntent;
	private ListView shop_infor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.d("orm", "crateTab");
		shop_infor = (ListView) findViewById(R.id.shop_infor_list);
		initTabs();
		chartView();
		setOptionSet();
		initListenering();

	}

	@Override
	public void initTabs() {
		this.mainHost = getTabHost();
		this.statisticIntent = TabsUtils.setIntentClass(this,
				StatisticsAcitvity.class);
		mainHost.addTab(TabsUtils.buildTabSpec(mainHost, this,
				TabValues.STATISTICS, R.string.statics,
				R.drawable.tab_icon_scan, this.statisticIntent));
		this.analysisIntent = TabsUtils.setIntentClass(this, Analysis.class);
		mainHost.addTab(TabsUtils.buildTabSpec(mainHost, this,
				TabValues.ANALYSIS, R.string.statics, R.drawable.tab_icon_scan,
				this.analysisIntent));
		this.recordsIntent = TabsUtils.setIntentClass(this, Records.class);
		mainHost.addTab(TabsUtils.buildTabSpec(mainHost, this,
				TabValues.RECORDS, R.string.statics, R.drawable.tab_icon_scan,
				this.recordsIntent));

	}

	@Override
	public void initListenering() {
		RadioButton statisticButton = (RadioButton) findViewById(R.id.statistics);
		statisticButton.setOnCheckedChangeListener(new TabMessageListener(
				mainHost, this));
		RadioButton analysisButton = (RadioButton) findViewById(R.id.analysis);
		analysisButton.setOnCheckedChangeListener(new TabMessageListener(
				mainHost, this));
		RadioButton recordButton = (RadioButton) findViewById(R.id.records);
		recordButton.setOnCheckedChangeListener(new TabMessageListener(
				mainHost, this));
	}

	public void getData() {
		try {
			Dao<SalesTest, Integer> saleTestDao = getHelper().getDao(
					SalesTest.class);
			Dao<Statistics, Integer> statDao = getHelper().getDao(
					Statistics.class);
			double cost = 0, sales = 0, profit = 0;
			List<SalesTest> sales21 = saleTestDao
					.queryForEq("date", "2010-2-1");
			for (SalesTest salesTest : sales21) {

				cost += Double.valueOf(salesTest.getFromPrice());
				sales += Double.valueOf(salesTest.getSalePrice());
				profit += Double.valueOf(salesTest.getRequirePrice());

			}
			Statistics st1 = new Statistics("2010-02-01");
			st1.setCost_price(cost);
			st1.setSales_price(sales);
			st1.setProfit_price(profit);
			statDao.create(st1);
			// Log.d("orm", "price21-->"+price21);
			cost = 0;
			sales = 0;
			profit = 0;
			List<SalesTest> sales22 = saleTestDao
					.queryForEq("date", "2010-2-2");
			for (SalesTest salesTest : sales22) {
				cost += Double.valueOf(salesTest.getFromPrice());
				sales += Double.valueOf(salesTest.getSalePrice());
				profit += Double.valueOf(salesTest.getRequirePrice());

			}

			Statistics st2 = new Statistics("2010-02-02");
			st2.setCost_price(cost);
			st2.setSales_price(sales);
			st2.setProfit_price(profit);
			statDao.create(st1);
			// Log.d("orm", "price22-->"+price22);
			List<SalesTest> sales23 = saleTestDao
					.queryForEq("date", "2010-2-3");
			cost = 0;
			sales = 0;
			profit = 0;
			for (SalesTest salesTest : sales23) {
				cost += Double.valueOf(salesTest.getFromPrice());
				sales += Double.valueOf(salesTest.getSalePrice());
				profit += Double.valueOf(salesTest.getRequirePrice());

			}
			Statistics st3 = new Statistics("2010-02-02");
			st3.setCost_price(cost);
			st3.setSales_price(sales);
			st3.setProfit_price(profit);
			statDao.create(st1);
			List<double[]> pricedouble = new ArrayList<double[]>();
			List<Statistics> stat_list = statDao.queryForAll();

			double[] priced = new double[] {
					(double) stat_list.get(0).getCost_price(),
					(double) stat_list.get(1).getCost_price(),
					(double) stat_list.get(2).getCost_price() };

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void chartView() {
		try {
			Dao<SalesTest, Integer> saleTestDao = getHelper().getDao(
					SalesTest.class);
			Dao<Statistics, Integer> statDao = getHelper().getDao(
					Statistics.class);
			// double cost = 0, sales = 0, profit = 0;
			// List<SalesTest> sales21 = saleTestDao
			// .queryForEq("date", "2010-2-1");
			// for (SalesTest salesTest : sales21) {
			//
			// cost += Double.valueOf(salesTest.getFromPrice());
			// sales += Double.valueOf(salesTest.getSalePrice());
			// profit += Double.valueOf(salesTest.getRequirePrice());
			//
			// }
			//
			// Statistics st1 = new Statistics("2010-02-01");
			// st1.setCost_price(cost);
			// st1.setSales_price(sales);
			// st1.setProfit_price(profit);
			// statDao.create(st1);
			// // Log.d("orm", "price21-->"+price21);
			// cost = 0;
			// sales = 0;
			// profit = 0;
			// List<SalesTest> sales22 = saleTestDao
			// .queryForEq("date", "2010-2-2");
			// for (SalesTest salesTest : sales22) {
			// cost += Double.valueOf(salesTest.getFromPrice());
			// sales += Double.valueOf(salesTest.getSalePrice());
			// profit += Double.valueOf(salesTest.getRequirePrice());
			//
			// }
			//
			// Statistics st2 = new Statistics("2010-02-02");
			// st2.setCost_price(cost);
			// st2.setSales_price(sales);
			// st2.setProfit_price(profit);
			// statDao.create(st2);
			// // Log.d("orm", "price22-->"+price22);
			// List<SalesTest> sales23 = saleTestDao
			// .queryForEq("date", "2010-2-3");
			// cost = 0;
			// sales = 0;
			// profit = 0;
			// for (SalesTest salesTest : sales23) {
			// cost += Double.valueOf(salesTest.getFromPrice());
			// sales += Double.valueOf(salesTest.getSalePrice());
			// profit += Double.valueOf(salesTest.getRequirePrice());
			//
			// }
			// Statistics st3 = new Statistics("2010-02-03");
			// st3.setCost_price(cost);
			// st3.setSales_price(sales);
			// st3.setProfit_price(profit);
			// statDao.create(st3);
			// List<double[]> pricedouble = new ArrayList<double[]>();
			List<List<Double>> pricedouble = new ArrayList<List<Double>>();
			List<Statistics> stat_list = statDao.queryForAll();

			double[] costpriced = new double[] {
					(double) stat_list.get(0).getCost_price(),
					(double) stat_list.get(1).getCost_price(),
					(double) stat_list.get(2).getCost_price() };

			double[] salestpriced = new double[] {
					(double) stat_list.get(0).getSales_price(),
					(double) stat_list.get(1).getSales_price(),
					(double) stat_list.get(2).getSales_price() };
			double[] profitpriced = new double[] {
					(double) stat_list.get(0).getProfit_price(),
					(double) stat_list.get(1).getProfit_price(),
					(double) stat_list.get(2).getProfit_price() };
			List<Double> costlist = new ArrayList<Double>();
			costlist.add(stat_list.get(0).getCost_price());
			costlist.add(stat_list.get(1).getCost_price());
			costlist.add(stat_list.get(2).getCost_price());

			pricedouble.add(costlist);
			// pricedouble.add(salestpriced);
			// pricedouble.add(profitpriced);
			BarChart mbarchart = new BarChart();
			// String[] titles = new String[] { "成本","营业额","利润" };
			List<String> titles = new ArrayList<String>();
			titles.add("成本");
			// titles.add("营业额");
			// titles.add("利润");

			Map<String, String> map = new HashMap<String, String>();
			map.put(BarChartValues.CHARTTITLE, "test");
			map.put(BarChartValues.XLABSTITLE, "date");
			map.put(BarChartValues.SHOWTYPE,
					String.valueOf(BarChartValues.WEEK));

			// 多图显示
			// 1.设置显示用的类型
			// 1.1 构造线图 图
			// 1.1.1 颜色设置
			int[] colors = new int[] { Color.GREEN };
			// 1.1.2 点的类型
			PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE };
			XYMultipleSeriesRenderer pointRender = new XYMultipleSeriesRenderer();
			pointRender.setAxisTitleTextSize(16);
			pointRender.setChartTitleTextSize(20);
			pointRender.setLabelsTextSize(15);
			pointRender.setLegendTextSize(15);
			int length = colors.length;
			for (int i = 0; i < length; i++) {
				//线图的数据
				XYSeriesRenderer r = new XYSeriesRenderer();
				r.setColor(colors[i]);
				r.setPointStyle(styles[i]);
				pointRender.addSeriesRenderer(r);
			}
			pointRender.setPointSize(5.5f);
			double[] range = new double[]{0,12,0,50};
			pointRender.setRange(range);
			pointRender.setXLabels(12);
			pointRender.setBarSpacing(0.1);
			// 数据集
			XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

			// 1, lineChart 数据集
			List<double[]> x = new ArrayList<double[]>();

			x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });

			List<double[]> values = new ArrayList<double[]>();
			values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4,
					26.1, 23.6, 20.3, 17.2, 13.9 });
			for (int i = 0; i < 1; i++) {
				XYSeries series = new XYSeries("lineTest", 0);
				double[] xV = x.get(0);
				double[] yV = values.get(0);
				int seriesLength = xV.length;
				for (int k = 0; k < seriesLength; k++) {
					series.add(xV[k], yV[k]);
				}
				dataset.addSeries(series);
			}

			// 2 barChart 数据集
			XYSeries waterSeries = new XYSeries("Water Temperature");
			waterSeries.add(1, 16);
			waterSeries.add(2, 15);
			waterSeries.add(3, 16);
			waterSeries.add(4, 17);
			waterSeries.add(5, 20);
			waterSeries.add(6, 23);
			waterSeries.add(7, 25);
			waterSeries.add(8, 25.5);
			waterSeries.add(9, 26.5);
			waterSeries.add(10, 24);
			waterSeries.add(11, 22);
			waterSeries.add(12, 18);
			XYSeriesRenderer waterRenderer = new XYSeriesRenderer();
			waterRenderer.setColor(Color.argb(250, 0, 210, 250));
			//waterRenderer.set
			pointRender.addSeriesRenderer(0, waterRenderer);
			dataset.addSeries(waterSeries);
			String[] types = new String[] {
					 org.achartengine.chart.BarChart.TYPE,LineChart.TYPE };

//			GraphicalView mBarchart = mbarchart.getChartView(this, titles,
//					pricedouble, map);
			GraphicalView mBarchart = ChartFactory.getCombinedXYChartView(this, dataset, pointRender, types);
			LinearLayout barchart = (LinearLayout) findViewById(R.id.barchart);
			// GraphicalView mChartView = ChartFactory.getBarChartView(this,
			// dataset,
			// renderer, Type.DEFAULT);

			barchart.addView(mBarchart, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.option_menu:
			Intent intent = new Intent().setClass(this, ShopPreference.class);
			startActivityForResult(intent, 0);
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("orm", "回传成功" + requestCode + "resultCode:" + resultCode);
		setOptionSet();
		setActitvityView();

	}

	private void setActitvityView() {
		chartView();
		ComponentName aName = this.getCurrentActivity().getComponentName();
		Log.d("orm", aName.toString());

	}

	private void setOptionSet() {
		Log.d("orm", "返回值");
		SharedPreferences prefs = getSharedPreferences("com.achai_preferences",
				0);
		String option = prefs.getString("店铺成本", "100");
		String hr_cost = prefs.getString(this.getString(R.string.hr_cost), "");
		String others_cost = prefs.getString(
				this.getString(R.string.others_cost), "");
		Log.d("orm", "返回值" + this.getString(R.string.shop_rent) + ":" + option
				+ ":" + hr_cost + ":" + others_cost);
		//更新listview
	
		String [] infor = {option,hr_cost,others_cost};
		shop_infor.setAdapter(new ArrayAdapter<String>(this,R.layout.items,infor));

	}

	// 1.创建一个ORM 的helper
	private DatabaseHelper databaseHelper = null;

	@Override
	protected void onDestroy() {
		// 清除 helper
		super.onDestroy();
		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}

	protected DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(this,
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	public void updateBarChartView(List<String> titles,
			List<List<Double>> values, Map<String, String> settings) {
		BarChart mBarchart = new BarChart();
		GraphicalView mBarchartView = mBarchart.getChartView(this, titles,
				values, settings);

		LinearLayout barchart = (LinearLayout) findViewById(R.id.barchart);
		barchart.removeAllViews();
		barchart.addView(mBarchartView, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	}
}

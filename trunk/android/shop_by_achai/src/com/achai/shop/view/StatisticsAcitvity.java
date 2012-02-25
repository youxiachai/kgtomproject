package com.achai.shop.view;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.achai.R;
import com.achai.shop.chart.BarChartValues;
import com.achai.shop.dao.DatabaseHelper;
import com.achai.shop.model.Statistics;
import com.achai.shop.model.StatisticsMonth;
import com.achai.shop.model.StatisticsYear;
import com.achai.shop.utils.DialogFactory;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class StatisticsAcitvity extends OrmLiteBaseActivity<DatabaseHelper> {

	private Button costButton;
	private Button salesButton;
	private Button profitButton;
	private Map<String, String> settingMap = new HashMap<String, String>();

	ListView statLv;
	private LayoutInflater statLayout = null;
	private View statDialogView = null;

	private Spinner statYear = null;
	private Spinner statMonth = null;
	private Spinner statWeek = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stat_activity);
		costButton = (Button) findViewById(R.id.stat_cost);
		
		
		
		salesButton = (Button) findViewById(R.id.stat_sales);
		profitButton = (Button) findViewById(R.id.stat_profit);
		initListening();
		statLv = (ListView) findViewById(R.id.stat_date);
	//	String[] date_year = getResources().getStringArray(R.array.stat_year);

//		statLv.setAdapter(new ArrayAdapter<String>(this, R.layout.items,
//				date_year));
//		statLv.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				showDialog(1);
//
//			}
//		});
		// setContentView(tv);

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 1:
			statLayout = LayoutInflater.from(StatisticsAcitvity.this);
			statDialogView = statLayout.inflate(R.layout.dialog_statics, null);

			return DialogFactory
					.createStatDialog(this, "选择年月周", statDialogView);
		default:
			break;
		}

		return super.onCreateDialog(id);
	}

	private void initListening() {
		costButton.setOnClickListener(new StatisticsListening(this));
		salesButton.setOnClickListener(new StatisticsListening(this));
		profitButton.setOnClickListener(new StatisticsListening(this));

	}

	public void costChartView() {

		try {
			Dao<Statistics, Integer> statDao = getHelper().getDao(
					Statistics.class);
			// 1,获取统计对象
			List<Statistics> stat_list = statDao.queryForAll();
			// 2,根据查询条件填充值
			double[] costpriced = new double[] {
					(double) stat_list.get(0).getCost_price(),
					(double) stat_list.get(1).getCost_price(),
					(double) stat_list.get(2).getCost_price() };
			// 3,图表用图例和数据
			List<String> titles = new ArrayList<String>();
			titles.add("成本");
			List<double[]> values = new ArrayList<double[]>();
			values.add(costpriced);
			settingMap.put(BarChartValues.CHARTTITLE, "一周成本开支");
			settingMap.put(BarChartValues.XLABSTITLE, "2010-2-1到2010-2-7");
			settingMap.put(BarChartValues.SHOWTYPE,
					String.valueOf(BarChartValues.MONTH));
			ShopMainActivity shopView = (ShopMainActivity) this.getParent();
		//	shopView.updateBarChartView(titles, values, settingMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void salesChartView() {
		try {
			Dao<Statistics, Integer> statDao = getHelper().getDao(
					Statistics.class);
			// 1,获取统计对象
			List<Statistics> stat_list = statDao.queryForAll();
			// 2,根据查询条件填充值
			double[] salespriced = new double[] {
					(double) stat_list.get(0).getSales_price(),
					(double) stat_list.get(1).getSales_price(),
					(double) stat_list.get(2).getSales_price() };
			// 3,图表用图例和数据
			List<String> titles = new ArrayList<String>();
			titles.add("营业额");
			List<double[]> values = new ArrayList<double[]>();
			values.add(salespriced);

			ShopMainActivity shopView = (ShopMainActivity) this.getParent();
			// shopView.updateBarChartView(titles, values);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void profitChartView() {
		try {
			Dao<Statistics, Integer> statDao = getHelper().getDao(
					Statistics.class);
			// 1,获取统计对象
			List<Statistics> stat_list = statDao.queryForAll();
			// 2,根据查询条件填充值
			double[] profitpriced = new double[] {
					(double) stat_list.get(0).getProfit_price(),
					(double) stat_list.get(1).getProfit_price(),
					(double) stat_list.get(2).getProfit_price() };
			// 3,图表用图例和数据
			List<String> titles = new ArrayList<String>();
			titles.add("利润");
			List<double[]> values = new ArrayList<double[]>();
			values.add(profitpriced);

			ShopMainActivity shopView = (ShopMainActivity) this.getParent();
			// shopView.updateBarChartView(titles, values);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void queryWeekResult(String year, String month, String week) {
		// 1,时间处理某年某月第几周
		Calendar queryDate = new GregorianCalendar(Integer.valueOf(year),
				(Integer.valueOf(month) - 1), 1);
		//获取一个月中最多的天数
		int daySum = queryDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		//queryDate.get
		// queryDate.setMinimalDaysInFirstWeek(7);
		// 2,设置时间偏移值
		queryDate.get(Calendar.ZONE_OFFSET);
		// 设置一周开始为星期一
		queryDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		queryDate.add(Calendar.WEEK_OF_MONTH, (Integer.valueOf(week) - 1));

		String startDate = (String) DateFormat.format("yyyy-MM-dd", queryDate);
		
		try {
			Dao<Statistics, Integer> statDao = getHelper().getDao(
					Statistics.class);
			// 1,获取统计对象
			// 增加6天
			// 循环出6天的数,这里做查询,暂时这样,以后改成用long类型做id查询
		//	List<List<Statistics>> stat_list = new ArrayList<List<Statistics>>();
			//从数据库获取的值
			List<Double> costlist = new ArrayList<Double>();

			// statDao
			// queryDate.getTimeInMillis();
			// Statistics stat =
			String tempDate = startDate;
			for (int i = 1; i < 7; i++) {
				List<Statistics> stat_list_1 = statDao.queryForEq("id",
						tempDate);
				queryDate.add(Calendar.DAY_OF_MONTH, 1);
				if(stat_list_1.size() == 0){
					break;
				}
				costlist.add(stat_list_1.get(0).getCost_price());
				tempDate = (String) DateFormat.format("yyyy-MM-dd", queryDate);
			}
			String endDate = tempDate;
		//	 List<Statistics> stat_list = statDao.queryForAll();
			 
			// 2,根据查询条件填充值
		//	List<Double> costlist = new ArrayList<Double>();
		//	costlist.add(stat_list.get(0).getCost_price());
//			double[] costpriced = new double[] {
//					(double) stat_list.get(0).get(0).getCost_price(),
//					(double) stat_list.get(1).get(1).getCost_price(),
//					(double) stat_list.get(2).get(3).getCost_price() };
			// 3,图表用图例和数据
			List<String> titles = new ArrayList<String>();
			titles.add("成本");
			List<List<Double>> values = new ArrayList<List<Double>>();
			values.add(costlist);
			settingMap.put(BarChartValues.CHARTTITLE, "一周成本开支");
			settingMap
					.put(BarChartValues.XLABSTITLE, startDate + "到" + endDate);
			settingMap.put(BarChartValues.SHOWTYPE,
					String.valueOf(BarChartValues.WEEK));
			ShopMainActivity shopView = (ShopMainActivity) this.getParent();
			shopView.updateBarChartView(titles, values, settingMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 一年12个月的成绩
	 * @param year
	 * @param month
	 */
	public void queryMonthResult(String year){
		//1,初始化日历
//		Calendar queryMonthDate = new GregorianCalendar(Integer.valueOf(year),
//				(Integer.valueOf(month) - 1), 1);
		
	}
	
	
	
	/**
	 * 一个月每天的成绩
	 * @param year
	 * @param month
	 */
	public void queryDayResult(String year,String month,int type){
		//1, 初始化日历
		Calendar queryDayDate = new GregorianCalendar(Integer.valueOf(year),(Integer.valueOf(month)-1),1);
		int days = queryDayDate.getActualMaximum(Calendar.DATE);
		String startDate = (String) DateFormat.format("yyyy-MM-dd", queryDayDate);
		
		
		
		
		//从数据库那里获取值
		try {
			Dao<Statistics, Integer> statDao = getHelper().getDao(Statistics.class);
			List<Double> valuelist = new ArrayList<Double>();
			for(int i=1; i < days+1; i++){
				//查询开始
				Statistics stat = statDao.queryForId((int) queryDayDate.getTimeInMillis());
				if(stat == null){
					//进行修正
					break;
				}
				
				switch (type) {
				case 1:
					valuelist.add(stat.getCost_price());
					break;
				case 2:
					valuelist.add(stat.getSales_price());
					break;
				case 3:
					valuelist.add(stat.getProfit_price());
					break;
				default:
					break;
				}
				//增加一天
				queryDayDate.add(Calendar.DAY_OF_MONTH, 1);
				
				
				
			}
			//最后一天
			String endDate = (String) DateFormat.format("yyyy-MM-dd", queryDayDate);
			
			//设置图表的设置
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	/**
	 * 比较历年
	 * @param year
	 */
	public void queryYearResult(String year){
		Calendar queryYear = new GregorianCalendar(Integer.valueOf(year),0,1);
		long yearlong = queryYear.getTimeInMillis();
		
		try {
			Dao<StatisticsYear, Integer> stDao = getHelper().getDao(StatisticsYear.class);
			//time test
			long timeTest =	 1293811200000L;
			
		//	StatisticsYear sy = stDao.queryForId(id)
			//StatisticsYear sy = stDao.queryForId(timeTest);
			List<StatisticsYear> sy =  stDao.queryForEq("id", timeTest);	
			StatisticsYear s0 = sy.get(0);
		//	sy.getCost_price();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

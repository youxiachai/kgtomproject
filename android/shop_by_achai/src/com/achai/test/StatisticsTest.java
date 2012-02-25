package com.achai.test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.widget.TextView;

import com.achai.shop.dao.DatabaseHelper;
import com.achai.shop.model.Statistics;
import com.achai.shop.model.StatisticsMonth;
import com.achai.shop.model.StatisticsYear;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class StatisticsTest extends OrmLiteBaseActivity<DatabaseHelper> {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			
			Dao<Statistics, Integer> statDao = getHelper().getDao(Statistics.class);
			String dCustom = "2011-01-01";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(dCustom);
			long id = date.getTime();
			
			Dao<StatisticsMonth,Integer> statMonth = getHelper().getDao(StatisticsMonth.class);
			StatisticsMonth sm= new StatisticsMonth(id);
			sm.setCost_price(1000);
			sm.setSales_price(500);
			sm.setProfit_price(250);
			statMonth.create(sm);
			
			Dao<StatisticsYear, Integer> statYear = getHelper().getDao(StatisticsYear.class);
			StatisticsYear sy = new StatisticsYear(id);
			sy.setCost_price(10000);
			sy.setSales_price(5000);
			sy.setProfit_price(2500);
			statYear.create(sy);
			TextView tv = new TextView(this);
			tv.setText("ok");
			setContentView(tv);
//			Statistics statistics = new Statistics(date);
//			statistics.setCost_price(2000);
//			statistics.setSales_price(1000);
//			statistics.setProfit_price(500);
//			statDao.create(statistics);
			
//			List<Statistics> list = statDao.queryForAll();
//			for (Statistics statistics2 : list) {
//				Log.d("orm", statistics2.getId()+"");
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	}
}

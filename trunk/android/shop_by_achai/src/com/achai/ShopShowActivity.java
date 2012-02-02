package com.achai;

import java.util.HashMap;
import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.tools.PanListener;
import org.achartengine.tools.ZoomEvent;
import org.achartengine.tools.ZoomListener;

import com.achai.shop.chart.BarChart;
import com.achai.shop.chart.LinChart;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class ShopShowActivity extends Activity {

	private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

	private XYSeries mCurrentSeries;
	private XYSeriesRenderer mCurrentRender;

	private GraphicalView mChartView;
	private LinChart lineChart = new LinChart();
	private BarChart barChart = new BarChart();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
//		ListView lv = (ListView) findViewById(R.id.listView1);
//		ListView lv2 = (ListView) findViewById(R.id.listView2);
		
	
		
//		String strs[] = {"ok","ok!"};
//		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,strs));
//		lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//		lv2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,strs));
//		lv2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//		lv.setOnItemClickListener(new OnItemClickListener() {

//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				
//				CheckedTextView ct = (CheckedTextView) view;
//				if(ct.isChecked()){
//					setTitle(""+position+ true);
//				}else{
//					setTitle(""+position + false);
//				}
//				
//			}
//		});
//		HashMap<"ok", "1"> 

//		mRenderer.setApplyBackgroundColor(true);
//		mRenderer.setBackgroundColor(Color.argb(100, 50, 50, 50));
//		mRenderer.setAxisTitleTextSize(16);
//		mRenderer.setChartTitleTextSize(20);
//		mRenderer.setLabelsTextSize(15);
//		mRenderer.setLegendTextSize(15);
//		mRenderer.setZoomButtonsVisible(true);
//		mRenderer.setZoomEnabled(true, true);
//		mRenderer.setMargins(new int[] { 20, 30, 15, 0 });
//		mRenderer.setZoomButtonsVisible(true);
//		mRenderer.setPointSize(10);
//		
//		//图列值
//		Random r = new Random();
//		for(int i =0; i < 2; i++){
//			//示例显示
//			mCurrentSeries = new XYSeries("package" + (i+2));
//			//数据填充
//			for(int j = 0; j < 10; j++){
//				double xV = 100 + j;
//				double yV = 100 + j;
//				mCurrentSeries.add(xV, yV);
//			}
//			mDataset.addSeries(mCurrentSeries);
//		}
//		mCurrentRender= new XYSeriesRenderer();
//		mCurrentRender.setColor(Color.BLUE);
//		mRenderer.addSeriesRenderer(mCurrentRender);
//		
//		mCurrentRender = new XYSeriesRenderer();
//		mCurrentRender.setColor(Color.GREEN);
//		mRenderer.addSeriesRenderer(mCurrentRender);
	
		

	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(mChartView == null){
			LinearLayout layout = (LinearLayout) findViewById(R.id.barchart);
			mChartView = barChart.chartView(this);
			 
			layout.addView(mChartView, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		
		}else{
			mChartView.repaint();
		}
	}
}

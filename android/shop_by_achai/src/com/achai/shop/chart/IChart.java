
/**
 * 下午12:05:31
 */
package com.achai.shop.chart;

import org.achartengine.GraphicalView;

import android.content.Context;
import android.content.Intent;

/**
 * 用于定义图表的基本元素
 * @author Tom_achai
 * @time 下午12:05:31
 */
public interface IChart {

	/**
	 * 用于定义在listActivity的字段
	 */
	String NAME = "name";
	/**
	 * 用于定义在listActivity的字段
	 */
	String DESC = "desc";
	
	/**返回图表名字
	 * @return
	 */
	String getName();
	
	/**获得图表的描述
	 * @return
	 */
	String getDesc();
	
	/**
	 * 创建内置的显示用图表Intent
	 * @param context
	 * @return
	 */
	Intent execute(Context context);
	
	GraphicalView chartView(Context context);
}


package cn.meitong.parse;

import java.util.List;
import java.util.Map;

/**
 * @author Tom_achai
 * @date 2011-12-1
 * 
 */
public class WeatherBean {
	public String CityName;
	public List<Map<String,Object>> list;
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public String CityDescription;
	public void setCityDescription(String cityDescription) {
		CityDescription = cityDescription;
	}

	public String LiveWeather;
	public void setCityName(String cityName) {
		CityName = cityName;
	}

	public void setLiveWeather(String liveWeather) {
		LiveWeather = liveWeather;
	}
}

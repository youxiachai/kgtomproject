package kg.tom.model;

import java.io.Serializable;

/**
 * @author Tom_achai
 * @date 2011-11-7
 * 
 */
public class Mp3Info implements Serializable {

	// MP3的id
	private Integer id;
	// MP3保存的路径
	private String data;
	// MP3文件名
	private String display_name;
	// MP3文件大小
	private Integer size;
	// MP3文件的标题
	private String title;
	// MP3文件的播放时间
	private Integer duration;
	// 这是歌曲文件
	private Integer is_music;

	// 以下是get/set方法;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getIs_music() {
		return is_music;
	}

	public void setIs_music(Integer is_music) {
		this.is_music = is_music;
	}

}

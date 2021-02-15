package com.dbs.project.domain;

import java.util.List;

public class CrawlerVo {
	
	public List<String> urlsList;
	public String url;
	public String keyword;
	public int numSearchTracking;
	
	public List<String> getUrlsList() {
		return urlsList;
	}
	public void setUrlsList(List<String> urlsList) {
		this.urlsList = urlsList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getNumSearchTracking() {
		return numSearchTracking;
	}
	public void setNumSearchTracking(int numSearchTracking) {
		this.numSearchTracking = numSearchTracking;
	}
	
}

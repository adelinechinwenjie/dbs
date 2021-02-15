package com.dbs.project.services;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dbs.project.domain.CrawlerVo;

@Service
@Component
public class CrawlerService {
	
	private Map<String,String> hashLinks;
	private Map<String,String> linksWithKeywordMap;
	private static final int MAX_NUM_SEARCH = 5;
	private static String errorMsg = "Error with Internet Connection ";
	
	public CrawlerService() {
		hashLinks = new HashMap<String,String>();
		linksWithKeywordMap = new HashMap<String,String>();
	}

	//pass urls and keyword
	//check if this urls contain keyword given
	public Map<String,Boolean> searchWordInUrls(CrawlerVo crawlerVo){
		
		List<String> urlsList = crawlerVo.getUrlsList();
		Map<String,Boolean> results = new HashMap();
		if(urlsList!=null && urlsList.size()>0) {
			urlsList.forEach(url -> {
				//check if urls is valid or not
				crawlerVo.setUrl(url);
				results.put(url,checkInternetConnection(crawlerVo));
			});
		}
		return results;
	}		

	public HashMap<String,String> allUrlsWithinUrlWithKeyword(CrawlerVo crawlerVo) {
		String url = crawlerVo.getUrl();
		String keyword = crawlerVo.getKeyword();
		int numSearchTracking = crawlerVo.getNumSearchTracking();
		
        //check if url has been added
        if (!hashLinks.containsKey(url) && numSearchTracking < MAX_NUM_SEARCH) {
        		try {
                    //add to hashLinks to make sure it has been "checked"
                    hashLinks.put(url,url);
                    //check if the doc has keyword
                    Document doc = Jsoup.connect(url).get();
                    
                    //Parse the HTML to extract links to other URLs
                    Elements linksOnPage = doc.select("a[href]");
                    //Loop all the extracted URLs
                    for (Element page : linksOnPage) {
                    	if(linksWithKeywordMap.size() < MAX_NUM_SEARCH) {
                    		if(doc.text().contains(keyword) && !linksWithKeywordMap.containsKey(url)){
                    			linksWithKeywordMap.put(url, url);
            				}
                    		crawlerVo.setUrl(page.attr("abs:href"));
                    		crawlerVo.setNumSearchTracking(numSearchTracking);
                        	allUrlsWithinUrlWithKeyword(crawlerVo);
                    	}else {
                    		return (HashMap<String, String>) linksWithKeywordMap;
                    	}
                    }
                } catch (IOException e) {
                    System.err.println("Error " + url + " " + e.getMessage());
                }
        	}
		return null;
        
    }

	
	public Boolean checkInternetConnection(CrawlerVo crawlerVo) {
		
		String url = crawlerVo.getUrl();
		String keyword = crawlerVo.getKeyword();
		
		try {
			//check internet connection
			URL urlConnection = new URL(url);
	        URLConnection connection = urlConnection.openConnection();
	        connection.connect();
			
			//connect the document details
	        errorMsg = "Error ";
			Document doc = Jsoup.connect(url).get();
			if(doc.text().contains(keyword)){
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			System.out.println(errorMsg + e.getMessage());
			return false;
		}
	}
}

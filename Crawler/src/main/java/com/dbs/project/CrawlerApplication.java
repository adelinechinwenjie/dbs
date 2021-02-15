package com.dbs.project;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.dbs.project.domain.CrawlerVo;
import com.dbs.project.services.CrawlerService;

@SpringBootApplication
@ComponentScan(basePackages = {"com.dbs.project"})
public class CrawlerApplication {

	public static void main(String[] args) throws IOException{
		
		//CrawlerService crawlerService = new CrawlerService();
		CrawlerVo crawlerVo = new CrawlerVo();
		crawlerVo.setUrl("https://developer.mozilla.org/en-US/docs/Web/API/Document");
		crawlerVo.setKeyword("document");
		crawlerVo.setNumSearchTracking(0);
		CrawlerService crawlerService = new CrawlerService();
		HashMap<String,String> linksWithKeywordMap = crawlerService.allUrlsWithinUrlWithKeyword(crawlerVo);
	    
		for(String key:linksWithKeywordMap.keySet()) {
			System.out.println(linksWithKeywordMap.get(key));
		}
	    
		/*CrawlerVo crawlerVo = new CrawlerVo();
		List<String> urlsList = new ArrayList<>();
		urlsList.add("https://developer.mozilla.org/en-US/docs/Web/API/Document"); //true
		urlsList.add("https://www.nea.gov.sg/wether"); //false
		urlsList.add("httptutorialspoint.com/javascript/javascript_html_dom.htm"); //invalid urls
		crawlerVo.setUrlsList(urlsList);
		crawlerVo.setKeyword("document");*/
		
		
	    /*Map<String,Boolean> result = crawlerService.searchWordInUrls(crawlerVo);
	    if(result!=null) {
	    	for(String key:result.keySet()) {
	    		 System.out.println("Url: " + key + " , Result: " + result.get(key));
	    	}
	    }*/
	    
	}
	
}


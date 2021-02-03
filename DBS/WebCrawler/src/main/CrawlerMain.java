package main;

import java.io.IOException;
import java.util.HashMap;

import service.CrawlerService;

public class CrawlerMain {

	public static void main(String[] args) throws IOException{
		
		CrawlerService crawlerService = new CrawlerService();
		HashMap<String,String> linksWithKeywordMap = crawlerService.allUrlsWithinUrlWithKeyword("https://developer.mozilla.org/en-US/docs/Web/API/Document","document",0);
	    for(String key:linksWithKeywordMap.keySet()) {
			System.out.println(linksWithKeywordMap.get(key));
		}
	    
		/*CrawlerVo crawlerVo = new CrawlerVo();
		List<String> urlsList = new ArrayList<>();
		urlsList.add("https://developer.mozilla.org/en-US/docs/Web/API/Document"); //true
		urlsList.add("https://www.nea.gov.sg/weather"); //false
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

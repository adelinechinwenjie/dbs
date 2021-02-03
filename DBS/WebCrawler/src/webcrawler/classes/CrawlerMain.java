package webcrawler.classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrawlerMain {

	public static void main(String[] args) throws IOException{
		
		Crawler crawler = new Crawler();
		HashMap<String,String> linksWithKeywordMap = crawler.allUrlsWithinUrlWithKeyword("https://developer.mozilla.org/en-US/docs/Web/API/Document","document",0);
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
		
		
	    /*Map<String,Boolean> result = crawler.searchWordInUrls(crawlerVo);
	    if(result!=null) {
	    	for(String key:result.keySet()) {
	    		 System.out.println("Url: " + key + " , Result: " + result.get(key));
	    	}
	    }*/
	    
	}
	
}

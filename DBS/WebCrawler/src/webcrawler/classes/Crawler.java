package webcrawler.classes;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	
	private Map<String,String> hashLinks;
	private Map<String,String> linksWithKeywordMap;
	private static final int MAX_NUM_SEARCH = 5;
	
	public Crawler() {
		hashLinks = new HashMap<String,String>();
		linksWithKeywordMap = new HashMap<String,String>();
	}
	
	//pass urls and keyword
	//check if this urls contain keyword given
	public Map<String,Boolean> searchWordInUrls(CrawlerVo crawlerVo) throws IOException{
		
		List<String> urlsList = crawlerVo.getUrlsList();
		String keyword = crawlerVo.getKeyword();
		
		String errorMsg = "Error with Internet Connection ";
		
		Map<String,Boolean> results = new HashMap();
		//check for urlslist null or not
		if(urlsList!=null && urlsList.size()>0) {
			for(String url:urlsList) {
				//check if urls is valid or not
				try {
					//check internet connection
					URL urlConnection = new URL(url);
			        URLConnection connection = urlConnection.openConnection();
			        connection.connect();
					
					//connect the document details
			        errorMsg = "Error ";
					Document doc = Jsoup.connect(url).get();
					if(doc.text().contains(keyword)){
						results.put(url, true);
					}else {
						results.put(url, false);
					}
				}catch(Exception e) {
					results.put(url, false);
					System.out.println(errorMsg + e.getMessage());
				}
				
			}
		}
		return results;
	}		
	
	
	public HashMap<String,String> allUrlsWithinUrlWithKeyword(String url, String keyword, int numSearchTracking) {
        //check if url has been added
        if (!hashLinks.containsKey(url) && numSearchTracking < MAX_NUM_SEARCH) {
        		try {
                    //add to hashLinks to make sure it has been "checked"
                    hashLinks.put(url,url);
                    //check if the doc has keyword
                    Document doc = Jsoup.connect(url).get();
    				/*if(doc.text().contains(keyword)){
    					numSearchTracking++;
    					System.out.println("PLUS!" + " " + numSearchTracking + " " + url);
    				}*/
                    //Parse the HTML to extract links to other URLs
                    Elements linksOnPage = doc.select("a[href]");
                    //Loop all the extracted URLs
                    for (Element page : linksOnPage) {
                    	if(linksWithKeywordMap.size() < MAX_NUM_SEARCH) {
                    		if(doc.text().contains(keyword) && !linksWithKeywordMap.containsKey(url)){
                    			linksWithKeywordMap.put(url, url);
            				}
                        	allUrlsWithinUrlWithKeyword(page.attr("abs:href"), keyword, numSearchTracking);
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

}

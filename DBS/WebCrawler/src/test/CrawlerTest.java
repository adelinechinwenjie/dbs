package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import webcrawler.classes.Crawler;
import webcrawler.classes.CrawlerVo;

class CrawlerTest {

	@Test
	void test() throws Exception {
		searchIfKeywordExistInUrlsTrue();
		searchIfKeywordExistInUrlsFalse();
		searchIfKeywordExistInUrlsError();
		testWebCrawlerLinksKeyword();
	}
	
	private void searchIfKeywordExistInUrlsTrue() throws Exception {

		CrawlerVo crawlerVo = new CrawlerVo();
		List<String> urlsList = new ArrayList<>();
		urlsList.add("https://developer.mozilla.org/en-US/docs/Web/API/Document"); //true
		crawlerVo.setUrlsList(urlsList);
		crawlerVo.setKeyword("document");
		
		Crawler crawler = new Crawler();
	    Map<String,Boolean> result = crawler.searchWordInUrls(crawlerVo);
	    assertNotNull(result);
		assertTrue(result.get(urlsList.get(0)));

	}
	
	private void searchIfKeywordExistInUrlsFalse() throws Exception {

		CrawlerVo crawlerVo = new CrawlerVo();
		List<String> urlsList = new ArrayList<>();
		urlsList.add("https://www.nea.gov.sg/weather"); //false
		crawlerVo.setUrlsList(urlsList);
		crawlerVo.setKeyword("document");
		
		Crawler crawler = new Crawler();
	    Map<String,Boolean> result = crawler.searchWordInUrls(crawlerVo);
	    assertNotNull(result);
		assertFalse(result.get(urlsList.get(0)));

	}
	
	private void searchIfKeywordExistInUrlsError() throws Exception {

		CrawlerVo crawlerVo = new CrawlerVo();
		List<String> urlsList = new ArrayList<>();
		urlsList.add("httptutorialspoint.com/javascript/javascript_html_dom.htm"); //invalid urls
		crawlerVo.setUrlsList(urlsList);
		crawlerVo.setKeyword("document");
		
		Crawler crawler = new Crawler();
	    Map<String,Boolean> result = crawler.searchWordInUrls(crawlerVo);
	    assertNotNull(result);
		assertFalse(result.get(urlsList.get(0)));

	}
	
	private void testWebCrawlerLinksKeyword() throws Exception {

		Crawler crawler = new Crawler();
	    HashMap<String,String> linksWithKeywordMap = crawler.allUrlsWithinUrlWithKeyword("https://developer.mozilla.org/en-US/docs/Web/API/Document","document",0);
		assertEquals(5, linksWithKeywordMap.size());

	}

}

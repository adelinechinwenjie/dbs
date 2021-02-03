package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import presentation.vo.CrawlerVo;
import service.CrawlerService;

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
		
		CrawlerService crawlerService = new CrawlerService();
	    Map<String,Boolean> result = crawlerService.searchWordInUrls(crawlerVo);
	    assertNotNull(result);
		assertTrue(result.get(urlsList.get(0)));

	}
	
	private void searchIfKeywordExistInUrlsFalse() throws Exception {

		CrawlerVo crawlerVo = new CrawlerVo();
		List<String> urlsList = new ArrayList<>();
		urlsList.add("https://www.nea.gov.sg/weather"); //false
		crawlerVo.setUrlsList(urlsList);
		crawlerVo.setKeyword("document");
		
		CrawlerService crawlerService = new CrawlerService();
	    Map<String,Boolean> result = crawlerService.searchWordInUrls(crawlerVo);
	    assertNotNull(result);
		assertFalse(result.get(urlsList.get(0)));

	}
	
	private void searchIfKeywordExistInUrlsError() throws Exception {

		CrawlerVo crawlerVo = new CrawlerVo();
		List<String> urlsList = new ArrayList<>();
		urlsList.add("httptutorialspoint.com/javascript/javascript_html_dom.htm"); //invalid urls
		crawlerVo.setUrlsList(urlsList);
		crawlerVo.setKeyword("document");
		
		CrawlerService crawlerService = new CrawlerService();
	    Map<String,Boolean> result = crawlerService.searchWordInUrls(crawlerVo);
	    assertNotNull(result);
		assertFalse(result.get(urlsList.get(0)));

	}
	
	private void testWebCrawlerLinksKeyword() throws Exception {

		CrawlerService crawlerService = new CrawlerService();
	    HashMap<String,String> linksWithKeywordMap = crawlerService.allUrlsWithinUrlWithKeyword("https://developer.mozilla.org/en-US/docs/Web/API/Document","document",0);
		assertEquals(5, linksWithKeywordMap.size());

	}

}

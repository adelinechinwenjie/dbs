package Crawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dbs.project.domain.CrawlerVo;
import com.dbs.project.services.CrawlerService;

@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = {"com.dbs.project"})
public class CrawlerTest {
	
	@Autowired
	private CrawlerService crawlerService;

	@Test
	public void test() {
		searchIfKeywordExistInUrlsTrue();
		searchIfKeywordExistInUrlsFalse();
		searchIfKeywordExistInUrlsError();
		testWebCrawlerLinksKeyword();
	}
	
	private void searchIfKeywordExistInUrlsTrue() {

		CrawlerVo crawlerVo = new CrawlerVo();
		List<String> urlsList = new ArrayList<>();
		urlsList.add("https://developer.mozilla.org/en-US/docs/Web/API/Document"); //true
		crawlerVo.setUrlsList(urlsList);
		crawlerVo.setKeyword("document");
		
	    Map<String,Boolean> result = crawlerService.searchWordInUrls(crawlerVo);
	    assertNotNull(result);
		assertTrue(result.get(urlsList.get(0)));

	}

	private void searchIfKeywordExistInUrlsFalse(){

		CrawlerVo crawlerVo = new CrawlerVo();
		List<String> urlsList = new ArrayList<>();
		urlsList.add("https://www.nea.gov.sg/weather"); //false
		crawlerVo.setUrlsList(urlsList);
		crawlerVo.setKeyword("document");
		
	    Map<String,Boolean> result = crawlerService.searchWordInUrls(crawlerVo);
	    assertNotNull(result);
		assertFalse(result.get(urlsList.get(0)));

	}
	
	private void searchIfKeywordExistInUrlsError(){

		CrawlerVo crawlerVo = new CrawlerVo();
		List<String> urlsList = new ArrayList<>();
		urlsList.add("httptutorialspoint.com/javascript/javascript_html_dom.htm"); //invalid urls
		crawlerVo.setUrlsList(urlsList);
		crawlerVo.setKeyword("document");
		
	    Map<String,Boolean> result = crawlerService.searchWordInUrls(crawlerVo);
	    assertNotNull(result);

	}
	
	private void testWebCrawlerLinksKeyword(){

		CrawlerVo crawlerVo = new CrawlerVo();
		crawlerVo.setUrl("https://developer.mozilla.org/en-US/docs/Web/API/Document");
		crawlerVo.setKeyword("document");
		crawlerVo.setNumSearchTracking(0);
	    HashMap<String,String> linksWithKeywordMap = crawlerService.allUrlsWithinUrlWithKeyword(crawlerVo);
		assertEquals(5, linksWithKeywordMap.size());

	}


}

package id.scrap.process;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import id.scrap.model.Item;
import id.scrap.util.Util;
import id.scrap.v1.controller.ScrapController;

@Service
public class Scrap {

	private static final Logger logger = LogManager.getLogger(ScrapController.class);
	private static Gson gson = new GsonBuilder().create();

	@Value("${url.host}")
	private String searchUrl;
	
	@Value("${url.max.page}")
	private int maxPage;

	@Autowired
	Util util;
	
	
	public void generate(String param) {
		List<Item> listItem = new ArrayList<Item>();
		listItem = scrap(param);

		if (listItem.size() > 0) {
			//Print Out Data as Json
//			logger.info("Data : " + gson.toJson(listItem));
			
			//Process Generate Csv
			util.processCsv(listItem);
		}
	}

	private List<Item> scrap(String param) {
		List<Item> listItem = new ArrayList<Item>();

		// Instantiate the client
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		
		try {
			
			//Page iterate
			for (int i = 1; i < maxPage; i++) {
			
				HtmlPage page = client.getPage(searchUrl + "?ob=5&page=" + i + "&q=" + URLEncoder.encode(param, "UTF-8"));
	
				List<HtmlElement> items = page.getByXPath("//div[@class='css-12sieg3']");
				if (!items.isEmpty()) {
	
					// Iterate over all elements
					for (HtmlElement htmlItem : items) {
	
						if (!isNullOrEmpty(htmlItem.asNormalizedText())) {
	
							// Ignore the product using advertisement
							if (!("Ad").equals(htmlItem.asNormalizedText().substring(0, 2))) {
	
								List<HtmlElement> item = htmlItem.getByXPath("//div[@class='css-974ipl']");
	
								// Iterate over all elements
								for (HtmlElement htmlItem2 : item) {
	
									Item dataProduct = new Item();
									dataProduct.setnameOfProduct(
											util.getString(getHtmlElement(htmlItem2, ".//a/div[@class='css-1b6t4dn']")
													.asNormalizedText()));
	//								private String description;
	//								private String imageLink;
									dataProduct.setPrice(
											util.getString(getHtmlElement(htmlItem2, ".//a/div[@class='css-1ksb19c']").asNormalizedText()));
									
									/*
									//Get Rating
									List<HtmlElement> itemRating1 = htmlItem2.getByXPath("//div[@class='css-yaxhi2']");
									
									for (HtmlElement htmlItem3 : itemRating1) {
										List<HtmlElement> itemRating2 = htmlItem3.getByXPath("//div[@class='css-q9wnub']");
										for (HtmlElement htmlItem4 : itemRating2) {
											dataProduct.setRating(
													util.getString(getHtmlElement(htmlItem4, ".//span[@class='css-t70v7i']").asNormalizedText()));
										}
									}
									
									//Get Name of Store
									List<HtmlElement> itemStoreName1 = htmlItem2.getByXPath("//div[@class='css-yaxhi2']");
									for (HtmlElement htmlItem3 : itemStoreName1) {
										List<HtmlElement> itemStoreName2 = htmlItem3.getByXPath("//div[@class='css-1ktbh56']");
										for (HtmlElement htmlItem4 : itemStoreName2) {
											
											List<HtmlElement> itemStoreName3 = htmlItem4.getByXPath("//div[@class='css-1rn0irl']");
											for (HtmlElement htmlItem5 : itemStoreName3) {
											dataProduct.setNameOfStore(
													util.getString(getHtmlElement(htmlItem5, ".//span[@data-testid='']").asNormalizedText()));
											}
										}
									}
									*/
									
									listItem.add(dataProduct);
	
								}
	
							}
	
						}
					}
	
				} else {
					logger.warn("No items found !");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("err : " + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.close();
			}
		}

		return listItem;
	}

	private HtmlElement getHtmlElement(HtmlElement htmlElement, String path) {
		return (HtmlElement) htmlElement.getFirstByXPath(path);
	}

	private static boolean isNullOrEmpty(String param) {
		return (param == null) || (param.equals(""));
	}

	
}

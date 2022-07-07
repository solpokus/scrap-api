package id.scrap.util;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import id.scrap.model.Item;

@Service
public class Util {
	
	private static final Logger logger = LogManager.getLogger(Util.class);
	
	@Value("${csv.header}")
	private String csvHeader;
	
	@Value("${csv.name}")
	private String csvName;
	
	public void processCsv(List<Item> listItem) {
		
		logger.info("============= START processCsv =============");

		List<List<String>> listsX = new ArrayList<List<String>>();
		List<String> listZ = null;

		// Add Header
		listZ = new ArrayList<String>();
		listZ.add(csvHeader);
		listsX.add(listZ);

		for (Item item : listItem) {

			listZ = new ArrayList<String>();
			listZ.add(getString(item.getNameOfProduct()));
			listZ.add(getString(item.getPrice()));
			listZ.add(getString(item.getNameOfStore()));
			listZ.add(getString(item.getRating()));
			listZ.add(getString(item.getImageLink()));
			listsX.add(listZ);
		}

		FileWriter writer = null;
		String csv = CSVWriter.encode(listsX);

		try {
			writer = new FileWriter(csvName);

			CSVUtils.writeLine(writer, Arrays.asList(csv));

			writer.flush();
			writer.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		logger.info("============= FINISH processCsv =============");
	}
	
	public String getString(String string) {
		return string == null ? "" : string;
	}
}

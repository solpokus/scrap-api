package id.scrap.v1.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.scrap.process.Scrap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/v1/scrap")
@Api(description = "APIs Scrap for Scraping data", tags = { "Scrap" })
public class ScrapController {

	private static final Logger logger = LogManager.getLogger(ScrapController.class);

	@Autowired
	Scrap scrap;

	@GetMapping("/generate")
	public HttpEntity generate(
			@ApiParam(value = "param", required = true) @RequestHeader(value = "param", required = false) String param) {
		// TODO Auto-generated method stub
		scrap.generate(param);
		return new ResponseEntity<>("Success Generate CSV from Scraping", HttpStatus.OK);
	}
}

# SCRAP-API

Rest API for Scraping and Generate Data to CSV from web.

## Swagger Doc :
http://localhost:8383/scrap-api/swagger-ui.html#/

## Running command :
mvn clean install spring-boot:run

### Process Scrapping and Generate CSV :

1.  Hit rest api using get on :
http://localhost:8383/scrap-api/v1/scrap/generate?param=[input_param]

2.  Or You can using curl :
curl -X GET "http://localhost:8383/scrap-api/v1/scrap/generate" -H  "accept: */*" -H  "param: handphone"

3. If get response success 200 :
File will be generate in project folder scrap-api, name of file is = file_generate.csv 
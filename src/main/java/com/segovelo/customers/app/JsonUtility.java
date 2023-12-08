package com.segovelo.customers.app;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/** 
* 8 Dec 2023
* @Javadoc TODO 
*
* @author Segovelo  **/

public class JsonUtility {

	public static String fileToJson(String filePath) {
        List<String> csvRows = null;
        try(var reader = Files.lines(Paths.get(filePath))){
            csvRows = reader.collect(Collectors.toList());
        }catch(Exception e){
            e.printStackTrace();
        }
        String json = "";
        if(csvRows != null){

            json = csvToJson(csvRows);
            System.out.println(json);
        
        }
        return json;
	}
	
	public static String csvToJson(List<String> csv){

        //remove empty lines
        //this will affect permanently the list. 
        //be careful if you want to use this list after executing this method
        csv.removeIf(e -> e.trim().isEmpty());

        //csv is empty or have declared only columns
        if(csv.size() <= 1){
            return "{\"customers\": []}";
        }

        //get first line = columns names
        String[] columns = csv.get(0).split(",");

        //get all rows
        StringBuilder json = new StringBuilder("{\"customers\":[\n");
        csv.subList(1, csv.size()) //substring without first row(columns)
            .stream()
            .map(e -> e.split(","))
            .filter(e -> e.length == columns.length) //values size should match with columns size
            .forEach(row -> {

                json.append("\t{\n");

                    for(int i = 0; i < columns.length; i++){
                        json.append("\t\t\"")
                            .append(columns[i])
                            .append("\" : \"")
                            .append(row[i])
                            .append("\",\n"); //comma-1
                    }

                    //replace comma-1 with \n
                    json.replace(json.lastIndexOf(","), json.length(), "\n");

                json.append("\t},\n"); //comma-2

            });

        //remove comma-2
        json.replace(json.lastIndexOf(","), json.length(), "");

        json.append("\n]\n}");
        
        return json.toString();
    }
	
	public static List<Customer> jsonToCustomers(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		List<Customer> customers = new ArrayList<>();
		try {
			customers = mapper.readValue(jsonString, new TypeReference<List<Customer>>(){});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return customers;
	}

	public static Customer jsonToCustomer(String jsonString) {
		ObjectMapper mapper = new ObjectMapper();
		Customer customer = null;
		try {
			customer = mapper.readValue(jsonString, new TypeReference<Customer>(){});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return customer;
	}

}

package com.alprojects;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

@WebService
public class ProductCatalog {

	public List<String> getProductCategories() {
		List<String> catalog = new ArrayList<String>();
		catalog.add("Books");
		catalog.add("Music");
		catalog.add("Movies");

		return catalog;
	}
}

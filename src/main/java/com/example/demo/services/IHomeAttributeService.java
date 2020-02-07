package com.example.demo.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.domain.ListingsAndReviews;
import com.example.demo.json.DetailAttribute;
import com.example.demo.json.HomeAttribute;

public interface IHomeAttributeService {

	List<ListingsAndReviews> getAllListingAndReviewsData();
	
	List<HomeAttribute> listAllAvaliableHome(HttpServletRequest req);
	
	List<HomeAttribute> listHomePriceBetween(HttpServletRequest req,int max,int min);
	
	DetailAttribute getDetailAttribute(HttpServletRequest req,String id);
	
	ListingsAndReviews saveListingAndReviewData(ListingsAndReviews listingsAndReview);
	
	void deleteById(String id);
}

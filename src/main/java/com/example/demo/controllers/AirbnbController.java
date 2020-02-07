package com.example.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ListingsAndReviews;
import com.example.demo.json.DetailAttribute;
import com.example.demo.json.HomeAttribute;
import com.example.demo.services.IHomeAttributeService;

@RestController
@RequestMapping("/airbnb")
public class AirbnbController {

	@Autowired
	private IHomeAttributeService homeAttributeService;
	
	@GetMapping()
	public List<ListingsAndReviews> getListingAndReviewsData(HttpServletRequest req) {
		return homeAttributeService.getAllListingAndReviewsData();
	}
	
	@GetMapping("/homeattribute")
	public List<HomeAttribute> getHomeAttribute(HttpServletRequest req) {
		return homeAttributeService.listAllAvaliableHome(req);
	}

	@GetMapping("/homeattribute/{max}/{min}")
	public List<HomeAttribute> getHomeAttributeByPriceRange(HttpServletRequest req, @PathVariable int max,
			@PathVariable int min) {
		return homeAttributeService.listHomePriceBetween(req, max, min);
	}

	@GetMapping("/detailattribute/{id}")
	public DetailAttribute getHomeAttribute(HttpServletRequest req, @PathVariable String id) {
		return homeAttributeService.getDetailAttribute(req, id);
	}

	@PostMapping
	public ListingsAndReviews saveAirbnbListingAndReview(@RequestBody ListingsAndReviews listingsAndReview) {
		ListingsAndReviews airbnb = homeAttributeService.saveListingAndReviewData(listingsAndReview);
		return airbnb;
	}
}

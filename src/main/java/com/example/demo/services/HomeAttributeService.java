package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ListingsAndReviews;
import com.example.demo.json.DetailAttribute;
import com.example.demo.json.HomeAttribute;
import com.example.demo.repository.AirbnbRepository;

import utilit.SessionUtility;

@Service
public class HomeAttributeService implements IHomeAttributeService {

	@Autowired
	private AirbnbRepository airbnbRepository;
	
	@Autowired
	private DetailAttribute detailAttribute;
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<HomeAttribute> listAllAvaliableHome(HttpServletRequest req) {
		List<HomeAttribute> homeAttributes = new ArrayList<HomeAttribute>();
		List<ListingsAndReviews> listingsAndReviews = null;
		try {
			if(SessionUtility.getHomeInfo(req.getSession()) == null) {
				
				listingsAndReviews = airbnbRepository.findAll();
				SessionUtility.setHomeInfo(req.getSession(), listingsAndReviews);
			}
			for (ListingsAndReviews listingsAndReview : listingsAndReviews) {
				HomeAttribute homeAttribute = new HomeAttribute();
				homeAttribute.setName(listingsAndReview.getName());
				homeAttribute.set_id(listingsAndReview.get_id());
				homeAttribute.setPrice(listingsAndReview.getPrice());
				if (listingsAndReview.getReview_scores() != null
						&& listingsAndReview.getReview_scores().getReview_scores_rating() != null) {
					homeAttribute.setRate(listingsAndReview.getReview_scores().getReview_scores_rating());
				}
				homeAttribute.setImageUrl(listingsAndReview.getImages().getPicture_url());
				homeAttributes.add(homeAttribute);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return homeAttributes;
	}

	@Override
	public DetailAttribute getDetailAttribute(HttpServletRequest req,String id) {
		ListingsAndReviews listingsAndReview = getSelectedListingAndReview(req,id);
		if(listingsAndReview != null) {
			detailAttribute.setHome_id(id);
			detailAttribute.setImage(listingsAndReview.getImages().getPicture_url());
			detailAttribute.setAccomodates(listingsAndReview.getAccommodates());
			detailAttribute.setBathooms(listingsAndReview.getBathrooms());
			detailAttribute.setBedrooms(listingsAndReview.getBedrooms());
			detailAttribute.setBeds(listingsAndReview.getBeds());
			detailAttribute.setSummary(listingsAndReview.getSummary());
		}
		return detailAttribute;
	}

	private ListingsAndReviews getSelectedListingAndReview(HttpServletRequest req,String id) {
		// TODO add home list to session
		List<ListingsAndReviews> listingsAndReviews;
		try {
			listingsAndReviews = SessionUtility.getHomeInfo(req.getSession());
			Optional<ListingsAndReviews> home = listingsAndReviews.stream()
					.filter(homeAttribute -> homeAttribute.get_id().equals(id)).findFirst();
			return home.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<HomeAttribute> listHomePriceBetween(HttpServletRequest req,int max, int min) {
		List<HomeAttribute> homeAttributes = new ArrayList<HomeAttribute>();

		try {
			List<ListingsAndReviews> listingsAndReviews = airbnbRepository.findListingsAndReviewsByPriceRange(max, min);
			for (ListingsAndReviews listingsAndReview : listingsAndReviews) {
				HomeAttribute homeAttribute = new HomeAttribute();
				homeAttribute.setName(listingsAndReview.getName());
				homeAttribute.set_id(listingsAndReview.get_id());
				homeAttribute.setPrice(listingsAndReview.getPrice());
				if (listingsAndReview.getReview_scores() != null
						&& listingsAndReview.getReview_scores().getReview_scores_rating() != null) {
					homeAttribute.setRate(listingsAndReview.getReview_scores().getReview_scores_rating());
				}
				homeAttribute.setImageUrl(listingsAndReview.getImages().getPicture_url());
				homeAttributes.add(homeAttribute);
			}
			return homeAttributes;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ListingsAndReviews saveListingAndReviewData(ListingsAndReviews listingsAndReview) {
		airbnbRepository.save(listingsAndReview);
		return listingsAndReview;
	}

	@Override
	public List<ListingsAndReviews> getAllListingAndReviewsData() {
		return airbnbRepository.findAll();
	}

	@Override
	public void deleteById(String id) {
		airbnbRepository.deleteById(id);
		
	}
	

}

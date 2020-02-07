package utilit;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.domain.ListingsAndReviews;

public class SessionUtility {

	private static final String SESSION_KEY_STARTFORMAT_TO_CLEAR = "airbnb."; // Session key starter to clear session if needed
	private static final String ORDER_SESSION_KEY_STARTFORMAT_TO_CLEAR = "airbnb.order"; // Session order key starter to clear only order data from
																							// session if needed
	private static final String FORMAT_SESSION_KEY_JOURNEY_TEMPORAL = "airbnb.temporal.%s";
	private static final String FORMAT_ORDER_SESSION_KEY_JOURNEY_TEMPORAL = "airbnb.order.temporal.%s";
	
	
	private static void setSessionTemporalAttribute(HttpSession session, String attributeName, Object attributeValue) throws Exception {
		if(session == null) {
			throw new Exception(String.format("Session is null. AttributeName:%s couldnt be saved.", attributeName));
		}
		if(attributeName == null) {
			throw new Exception("Session attribute coundnt be saved. AttributeName is null.");
		}
		if(attributeValue == null) {
			throw new Exception(String.format("AttributeValue is null. AttributeName:%s couldnt be saved.", attributeName));
		}
		session.setAttribute(String.format(FORMAT_SESSION_KEY_JOURNEY_TEMPORAL, attributeName), attributeValue);
	}

	private static Object getSessionTemporalAttribute(HttpSession session, String attributeName) {
		if(session == null || attributeName == null) {
			return null;
		}
		return session.getAttribute(String.format(FORMAT_SESSION_KEY_JOURNEY_TEMPORAL, attributeName));
	}
	public static void setHomeInfo(HttpSession session, List<ListingsAndReviews> list) throws Exception {
		if(session == null) {
			throw new Exception("Session is curropted.");
		}
		setSessionTemporalAttribute(session, SessionAttributeKey.LISTING_AND_REVIEWS.toString(), list);
	}

	public static List<ListingsAndReviews> getHomeInfo(HttpSession session) throws Exception {
		List<ListingsAndReviews> list = null;
		if(session == null) {
			throw new Exception("Session is curropted.");
		}
		try {
			list = (List<ListingsAndReviews>) getSessionTemporalAttribute(session, SessionAttributeKey.LISTING_AND_REVIEWS.toString());
		} catch(Throwable t) {
			throw new Exception("tariffInfo can not be read from session" + t.getMessage());
		}
		return list;
	}

}

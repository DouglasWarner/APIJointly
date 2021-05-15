package com.douglas.api.jointly.controler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.api.jointly.Utils;
import com.douglas.api.jointly.model.Initiative;
import com.douglas.api.jointly.model.User;
import com.douglas.api.jointly.model.UserJoinInitiative;
import com.douglas.api.jointly.model.UserReviewUser;
import com.douglas.api.jointly.services.InitiativeService;
import com.douglas.api.jointly.services.UserFollowUserService;
import com.douglas.api.jointly.services.UserJoinInitiativeService;
import com.douglas.api.jointly.services.UserReviewUserService;
import com.douglas.api.jointly.services.UserService;

@RestController
@RequestMapping(path="/api")
public class APIControler {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private InitiativeService initiativeService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserJoinInitiativeService joinInitiativeService;
	@Autowired
	private UserFollowUserService followUserService;
	@Autowired
	private UserReviewUserService reviewUserService;
	
	@RequestMapping(value = "/initiatives", method = RequestMethod.GET)
	public APIResponse getListInitiative()
	{
		logger.info("Get list initiative");

		List<Map<String, Object>> list = initiativeService.getList();
		
		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/initiatives/listByName", method = RequestMethod.GET)
	public APIResponse getListByName(@RequestParam("name") String name)
	{
		logger.info(String.format("Get list initiative by name [%s]", name));
		List<Map<String, Object>> list = initiativeService.getListByName(name);

		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/initiatives/initiative", method = RequestMethod.GET)
	public APIResponse getInitiativeById(@RequestParam("id") long id)
	{
		logger.info(String.format("Get initiative by id [%d]", id));
		Initiative initiative = null;
		String messageError = String.format("No initiative found for id [%d]", id);
		
		try {
			initiative = initiativeService.getInitiativeById(id);
			
			return new APIResponse(false, "OK", initiative);
		} catch (EmptyResultDataAccessException e) {
			logger.info(messageError);
			return new APIResponse(true, "ERR : " + messageError, null);
		} catch (Exception e) {
			return new APIResponse(true, "ERR : " + e.getCause(), null);
		}
	}
	
	@RequestMapping(value = "/initiatives/initiative", method = RequestMethod.POST)
	public APIResponse insertInitiative(@RequestParam("name") String name, @RequestParam("targetDate") String targetDate,
									@RequestParam("description") Optional<String> description, @RequestParam("targetArea") String targetArea,
									@RequestParam("location") String location, @RequestParam("imagen") Optional<byte[]> imagen,
									@RequestParam("targetAmount") int targetAmount, @RequestParam("createdBy") String createdBy)
	{
		logger.info(String.format("insert initiative [%s]", name));
		Initiative initiative = new Initiative();
		long result = 0;
		
		try {
			result = initiativeService.insert(name, targetDate, description.orElse(""), 
								targetArea, location, imagen.orElse(null), 
								targetAmount, "A", createdBy, String.valueOf(new Random().nextInt()));
			
			initiative.setId(result);
		} catch (Exception e) {
			String message = String.format("ERR : %s - [id=%d]", e.getCause(), result);
			logger.error(message);
			return new APIResponse(true, message, null);
		}
		
		if(result == 0)
			return new APIResponse(true, "ERR : Cannot insert initiative", null);
		else {
			return new APIResponse(false, "OK", initiativeService.getInitiativeById(initiative.getId()));	
		}
	}
	
	@RequestMapping(value = "/initiatives/initiative", method = RequestMethod.PUT)
	public APIResponse updateInitiative(@RequestParam("name") String name, @RequestParam("targetDate") String targetDate,
									@RequestParam("description") Optional<String> description, @RequestParam("targetArea") String targetArea,
									@RequestParam("location") String location, @RequestParam("imagen") Optional<byte[]> imagen,
									@RequestParam("targetAmount") int targetAmount, @RequestParam("id") long id)
	{
		logger.info(String.format("update initiative [%d]", id));
		int result = 0;
		
		try {
			result = initiativeService.update(name, targetDate, description.orElse(""), 
								targetArea, location, imagen.orElse(null), 
								targetAmount, "A", id);
		} catch (Exception e) {
			String message = String.format("ERR : %d - [id=%d]", e.getCause(), id);
			logger.error(message);
			return new APIResponse(true, message, null);
		}

		if(result == 0)
			return new APIResponse(true, String.format("ERR : Cannot update or not found initiative id [%d]", id), null);
		else {
			return new APIResponse(false, "OK", initiativeService.getInitiativeById(id));	
		}
	}
	
	@RequestMapping(value = "/initiatives/initiative", method = RequestMethod.DELETE)
	public APIResponse deleteInitiative(@RequestParam("id") long id)
	{
		logger.info(String.format("delete initiative [%d]", id));
		int result = 0;
		
		try {
			result = initiativeService.delete(id);
		} catch (Exception e) {
			String message = String.format("ERR : %s - [id=%d]", e.getCause(), id);
			logger.error(message);
			return new APIResponse(true, message, null);
		}
		
		if(result == 0)
			return new APIResponse(true, String.format("ERR : Cannot delete or not found initiative id [%d]", id), null);
		else
			return new APIResponse(false, "OK", null); 
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.GET)
	public APIResponse getUsersJoined()
	{
		logger.info(String.format("get list usersJoined"));
	
		List<Map<String, Object>> list = joinInitiativeService.getUsersJoined();
		
		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/initiatives/usersJoinedByInitiative", method = RequestMethod.GET)
	public APIResponse getUsersJoinedByInitiative(@RequestParam("idInitiative") long idInitiative)
	{
		logger.info(String.format("get list usersJoined [%d]", idInitiative));
		
		List<Map<String, Object>> list = joinInitiativeService.getUsersJoinedByInitiative(idInitiative);
		
		if(list.size() > 0) {
			return new APIResponse(false, "OK", list);
		} else {
			return new APIResponse(true, "ERR : Doesn't exists users joined for initiative ["+idInitiative+"]", null);	
		}
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.POST)
	public APIResponse insertUsersJoined(@RequestParam("date") String date, @RequestParam("idInitiative") long idInitiative, @RequestParam("userEmail") String userEmail,
											@RequestParam("type") int type)
	{
		logger.info(String.format("insert userJoined [%d | %s]", idInitiative, userEmail));
		long result = 0;
		UserJoinInitiative joinInitiative = new UserJoinInitiative(idInitiative, userEmail, date, type);
		Initiative initiative = null;
		
		try {
			initiative = initiativeService.getInitiativeById(idInitiative);
		} catch (Exception e) {
			logger.error("ERR : Initiative doesn't exists");
			return new APIResponse(true, "ERR : Initiative doesn't exists", null);
		}
		
		try {
			result = joinInitiativeService.insert(idInitiative, userEmail, type);
		} catch (Exception e) {
			String message = String.format("ERR : %s - [user=%s; initiative=%d]", e.getCause(), userEmail, idInitiative);
			logger.error(message);
			return new APIResponse(true, message, joinInitiative);
		}
		
		if(result == 0)
			return new APIResponse(true, String.format("ERR : Cannot insert join joinInitiativeService user [%s] | idInitiative [%d]", userEmail, idInitiative), joinInitiative);
		else
			return new APIResponse(false, "OK", joinInitiative);
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.PUT)
	public APIResponse updateUsersJoined(@RequestParam("idInitiative") long idInitiative, 
												@RequestParam("userEmail") String userEmail, @RequestParam("type") int type)
	{
		logger.info(String.format("update userJoined [%d | %s]", idInitiative, userEmail));
		
		int result = 0;
		try {
			result = joinInitiativeService.update(idInitiative, userEmail, type);
			
		} catch (Exception e) {
			String message = String.format("ERR : %d - [id=%d;user=%s]", e.getCause(), idInitiative, userEmail);
			logger.error(message);
			return new APIResponse(true, message, null);
		}
		
		if(result == 0)
			return new APIResponse(true, String.format("ERR : Cannot update or not found joinInitiativeService idInitiative [%d] | user [%s]", idInitiative, userEmail), null);
		else
		{
			try {
				APIResponse apiResponse = new APIResponse(false, "OK", joinInitiativeService.getUserJoinInitiative(idInitiative, userEmail));
				
				return apiResponse;
			} catch (Exception e) {
				return new APIResponse(true, "ERR : " + e.getCause(), null);
			}
		}
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.DELETE)
	public APIResponse deleteUsersJoined(@RequestParam("idInitiative") long idInitiative, 
											@RequestParam("userEmail") String userEmail)
	{
		logger.info(String.format("delete userJoined [%d | %s]", idInitiative, userEmail));
		int result = 0;
		
		try {
			result = joinInitiativeService.delete(idInitiative, userEmail);
		} catch (Exception e) {
			String message = String.format("ERR : %d - [id=%d;user=%s]", e.getCause(), idInitiative, userEmail);
			logger.error(message);
			return new APIResponse(true, message, null);
		}
		
		if(result == 0)
			return new APIResponse(true, String.format("ERR : Cannot delete or not found joinInitiativeService idInitiative [%d] | user [%s]", idInitiative, userEmail), null);
		else
			return new APIResponse(false, "OK", null);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public APIResponse getListUser()
	{
		logger.info("Get list user");
		List<Map<String, Object>> list = userService.getList();
		
		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/users/user", method = RequestMethod.GET)
	public APIResponse getUser(@RequestParam("email") String email)
	{
		logger.info(String.format("Get user by email [%s]", email));
		User user = null;
		String messageErrorEmptyResult = String.format("ERR : No user found for email [%s]", email);
		String messageErrorDuplicateEntry = String.format("ERR : User already exists");
		
		try {
			user = userService.getUser(email);
			return new APIResponse(false, "OK", user);
		} catch (EmptyResultDataAccessException e) {
			logger.info(messageErrorEmptyResult);
			return new APIResponse(true, messageErrorEmptyResult, null);
		} catch (DuplicateKeyException e) {
			logger.info(messageErrorDuplicateEntry);
			return new APIResponse(true, messageErrorDuplicateEntry, null);
		} catch (Exception e) {
			return new APIResponse(true, "ERR : " + e.getCause(), null);
		}
	}
	
	//TODO comprobar insert user de firebase
	@RequestMapping(value = "/users/user", method = RequestMethod.POST)
	public APIResponse insertUser(@RequestParam("email") String email, @RequestParam("password") String password,
						@RequestParam("name") String name, @RequestParam("phone") Optional<String> phone,
						@RequestParam("imagen") Optional<byte[]> imagen, @RequestParam("location") Optional<String> location,
						@RequestParam("description") Optional<String> description)
	{
		logger.info(String.format("insert user %s", email));
		int result = 0;
		User user = new User(email, password, name, phone.orElse(""), imagen.orElse(null), location.orElse(""), description.orElse(""), "");
		
		try {
			result = userService.insert(email, password, name, 
								phone.orElse(""), imagen.orElse(null), location.orElse(""),
								description.orElse(""));
			user.setId(result);
			user.setCreatedAt(LocalDateTime.now().toString());
		} catch (Exception e) {
			String message = String.format("ERR : %s - [userEmail=%s]", e.getCause(), email);
			logger.error(message);
			return new APIResponse(true, message, user);
		}
		
		if(result == 0)
			return new APIResponse(true, String.format("ERR : Cannot insert user %s", email), user);
		else
			return new APIResponse(false, "OK", user);
	}
	
	@RequestMapping(value = "/users/user", method = RequestMethod.PUT)
	public APIResponse updateUser(@RequestParam("email") String email, @RequestParam("password") String password,
						@RequestParam("name") String name, @RequestParam("phone") Optional<String> phone,
						@RequestParam("imagen") Optional<byte[]> imagen, @RequestParam("location") Optional<String> location,
						@RequestParam("description") Optional<String> description, @RequestParam("id") int id)
	{
		logger.info(String.format("update user %s", email));
		
		int result = 0;
		
		try {
			result = userService.update(email, password, name,
								phone.orElse(""), imagen.orElse(null), location.orElse(""),
								description.orElse(""), id);
		} catch (DuplicateKeyException e) {
			String messageErrorDuplicateEntry = String.format("ERR : User already exists - [userEmail=%s]", email);
			logger.error(messageErrorDuplicateEntry);
			return new APIResponse(true, messageErrorDuplicateEntry, null);
		} catch (Exception e) {
			String message = String.format("ERR : %s - [userEmail=%s]", e.getCause(), email);
			logger.error(message);
			return new APIResponse(true, message, null);
		}
		
		if(result == 0)
			return new APIResponse(true, String.format("ERR : Cannot update user [%s]", email), null);
		else
			return new APIResponse(true, "OK", userService.getUser(email));
	}
	
	@RequestMapping(value = "/users/user", method = RequestMethod.DELETE)
	public String deleteUser(@RequestParam("id") int id)
	{
//		logger.info("delete user");
//		
//		int result = userService.delete(id);
//		
//		if(result == 0)
//			return String.format("Cannot delete user %d", id);
//		else
//			return "OK";
		
		return "";
	}
	
	@RequestMapping(value = "/users/initiatives/created", method = RequestMethod.GET)
	public APIResponse initiativesCreatedByUser(@RequestParam("email") String email)
	{
		logger.info(String.format("get initiative created by user [%s]", email));
		
		List<Map<String, Object>> list = userService.getInitiativeCreatedByUser(email);
		
		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/users/initiatives/joined", method = RequestMethod.GET)
	public APIResponse initiativesJoinedByUser(@RequestParam("email") String email, @RequestParam("type") Optional<Integer> type)
	{
		logger.info(String.format("get initiative joined by user [%s]", email));
		
		List<Map<String, Object>> list = joinInitiativeService.getInitiativeJoinedByUser(email, type.orElse(Utils.join));
		
		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/users/followed", method = RequestMethod.GET)
	public APIResponse followedList(@RequestParam("email") String email)
	{
		logger.info(String.format("get followed by user [%s]", email));
		
		List<Map<String, Object>> list = followUserService.getListFollowed(email);

		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/users/followers", method = RequestMethod.GET)
	public APIResponse followersList(@RequestParam("email") String email)
	{
		logger.info(String.format("get followers by user [%s]", email));
		
		List<Map<String, Object>> list = followUserService.getListFollowers(email);
		
		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/users/followed", method = RequestMethod.POST)
	public APIResponse insertFollowed(@RequestParam("userEmail") String userEmail, @RequestParam("userFollowEmail") String userFollowEmail)
	{
		logger.info(String.format("insert followed by user [%s | %s]", userEmail, userFollowEmail));
		
		int result = 0;
		
		try {
			result = followUserService.insert(userEmail, userFollowEmail);
		} catch (Exception e) {
			String message = String.format("ERR : %s - [userEmail=%s; userFollowEmail=%s]", e.getCause(), userEmail, userFollowEmail);
			logger.error(message);
			return new APIResponse(true, message, null);
		}

		if(result == 0)
			return new APIResponse(true, String.format("ERR : Cannot insert follow followUserService userEmail [%s] | userFollowUser [%s]", userEmail, userFollowEmail), null);
		else
		{
			try {
				return new APIResponse(false, "OK", followUserService.getUserFollowUser(userEmail, userFollowEmail));
			} catch (Exception e) {
				return new APIResponse(true, "ERR : " + e.getCause(), null);
			}
		}
	}
	
	@RequestMapping(value = "/users/followed", method = RequestMethod.DELETE)
	public APIResponse deleteFollowed(@RequestParam("userEmail") String userEmail, @RequestParam("userFollowEmail") String userFollowEmail)
	{
		logger.info(String.format("delete followed by user [%s | %s]", userEmail, userFollowEmail));
		
		int result = 0;
		
		try {
			result = followUserService.delete(userEmail, userFollowEmail);
		} catch (Exception e) {
			return new APIResponse(true, "ERR : " + e.getCause(), null);
		}
		
		if(result == 0)
			return new APIResponse(true, String.format("ERR : Cannot delete follow followUserService [%s]", userFollowEmail), null);
		else
			return new APIResponse(false, "OK", null);
	}
	
	@RequestMapping(value = "/users/reviews", method = RequestMethod.GET)
	public APIResponse getReviews(@RequestParam("email") String email)
	{
		logger.info(String.format("get reviews of user [%s]", email));
		
		List<Map<String, Object>> list = reviewUserService.getList(email);
		
		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/users/reviews", method = RequestMethod.POST)
	public APIResponse insertReview(@RequestParam("date") String date, @RequestParam("userEmail") String userEmail, 
									@RequestParam("userReviewEmail") String userReviewEmail, @RequestParam("review") Optional<String> review, 
									@RequestParam("stars") int stars)
	{
		logger.info(String.format("insert review on userReview by user [%s | %s | %s | %d]", userEmail, userReviewEmail, review, stars));
		int result = 0;
		UserReviewUser reviewUser = new UserReviewUser(userEmail, userReviewEmail, date, review.orElse(""), stars);
		
		try {
			result = reviewUserService.insert(userEmail, userReviewEmail, review.orElse(""), stars);
		} catch (Exception e) {
			String message = String.format("ERR : %s - [userEmail=%s; userReviewEmail=%s]", e.getCause(), userEmail, userReviewEmail);
			logger.error(message);
			return new APIResponse(true, message, reviewUser);
		}
		
		if(result == 0)
			return new APIResponse(true, String.format("ERR : Cannot insert review reviewUserService user [%s]", userReviewEmail), reviewUser);
		else
		{
			try {
				return new APIResponse(false, "OK", reviewUserService.getReview(userEmail, userReviewEmail));
			} catch (Exception e) {
				return new APIResponse(true, "ERR : " + e.getCause(), null);
			}
		}
	}
}

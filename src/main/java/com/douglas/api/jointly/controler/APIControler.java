package com.douglas.api.jointly.controler;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.api.jointly.model.Initiative;
import com.douglas.api.jointly.model.User;
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
	public List<Map<String, Object>> getListInitiative()
	{
		logger.info("Get list initiative");
		
		return initiativeService.getList();
	}
	
	@RequestMapping(value = "/initiatives/listByName", method = RequestMethod.GET)
	public List<Map<String, Object>> getListByName(@RequestParam("name") String name)
	{
		logger.info(String.format("Get list initiative by name [%s]", name));
		
		return initiativeService.getListByName(name);
	}
	
	@RequestMapping(value = "/initiatives/initiative", method = RequestMethod.GET)
	public Initiative getInitiativeById(@RequestParam("id") int id)
	{
		logger.info(String.format("Get initiative by id [%d]", id));
		
		try {
			return initiativeService.getInitiativeById(id);
		} catch (EmptyResultDataAccessException e) {
			logger.info(String.format("No initiative found for id [%d]", id));
			return null;
		}
	}
	
	@RequestMapping(value = "/initiatives/initiative", method = RequestMethod.POST)
	public String insertInitiative(@RequestParam("name") String name, @RequestParam("targetDate") String targetDate,
									@RequestParam("description") Optional<String> description, @RequestParam("targetArea") String targetArea,
									@RequestParam("location") String location, @RequestParam("imagen") Optional<byte[]> imagen,
									@RequestParam("targetAmount") int targetAmount, @RequestParam("createdBy") String createdBy)
	{
		logger.info(String.format("insert initiative [%s]", name));
		
		int result = initiativeService.insert(name, targetDate, description.orElse(""), 
								targetArea, location, imagen.orElse(null), 
								targetAmount, "A", createdBy, String.valueOf(new Random().nextInt()));
		
		if(result == 0)
			return String.format("Cannot insert initiative [name=%s, description=%s, targetDate=%s, location=%s]", name, description, targetDate, location);
		else
			return "OK";
	}
	
	@RequestMapping(value = "/initiatives/initiative", method = RequestMethod.PUT)
	public String updateInitiative(@RequestParam("name") String name, @RequestParam("targetDate") String targetDate,
									@RequestParam("description") Optional<String> description, @RequestParam("targetArea") String targetArea,
									@RequestParam("location") String location, @RequestParam("imagen") Optional<byte[]> imagen,
									@RequestParam("targetAmount") int targetAmount, @RequestParam("id") int id)
	{
		logger.info(String.format("update initiative [%d]", id));
		
		int result = initiativeService.update(name, targetDate, description.orElse(""), 
								targetArea, location, imagen.orElse(null), 
								targetAmount, "status", id);

		if(result == 0)
			return String.format("Cannot update or not found initiative id [%d]", id);
		else
			return "OK";
	}
	
	@RequestMapping(value = "/initiatives/initiative", method = RequestMethod.DELETE)
	public void deleteInitiative(@RequestParam("id") int id)
	{
		logger.info(String.format("delete initiative [%d]", id));
		
		initiativeService.delete(id);
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.GET)
	public List<Map<String, Object>> getUsersJoined(@RequestParam("idInitiative") int idInitiative)
	{
		logger.info(String.format("get list usersJoined [%d]", idInitiative));
		
		return joinInitiativeService.getUsersJoined(idInitiative);
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.POST)
	public String insertUsersJoined(@RequestParam("date") String date, @RequestParam("idInitiative") int idInitiative, @RequestParam("userEmail") String userEmail,
											@RequestParam("type") int type)
	{
		logger.info(String.format("insert userJoined [%d | %s]", idInitiative, userEmail));
		
		int result = joinInitiativeService.insert(date, idInitiative, userEmail, type);
		
		if(result == 0)
			return String.format("Cannot insert join joinInitiativeService idInitiative [%d]", idInitiative);
		else
			return "OK";
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.PUT)
	public String updateUsersJoined(@RequestParam("date") String date, @RequestParam("idInitiative") int idInitiative, 
												@RequestParam("userEmail") String userEmail, @RequestParam("type") int type)
	{
		logger.info(String.format("update userJoined [%d | %s]", idInitiative, userEmail));
		
		int result = joinInitiativeService.update(date, idInitiative, userEmail, type);
		
		if(result == 0)
			return String.format("Cannot update or not found joinInitiativeService idInitiative [%d]", idInitiative);
		else
			return "OK";
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.DELETE)
	public void deleteUsersJoined(@RequestParam("date") String date, @RequestParam("idInitiative") int idInitiative, 
											@RequestParam("userEmail") String userEmail)
	{
		logger.info(String.format("delete userJoined [%d | %s]", idInitiative, userEmail));
		
		joinInitiativeService.delete(date, idInitiative, userEmail);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<Map<String, Object>> getListUser()
	{
		logger.info("Get list user");
		
		return userService.getList();
	}
	
	@RequestMapping(value = "/users/user", method = RequestMethod.GET)
	public User getUser(@RequestParam("email") String email)
	{
		logger.info(String.format("Get user by email [%s]", email));
		
		try {
			return userService.getUser(email);
		} catch (EmptyResultDataAccessException e) {
			logger.info(String.format("No user found for email [%s]", email));
			return null;
		}
	}
	
	@RequestMapping(value = "/users/user", method = RequestMethod.POST)
	public String insertUser(@RequestParam("email") String email, @RequestParam("password") String password,
						@RequestParam("name") String name, @RequestParam("phone") Optional<String> phone,
						@RequestParam("imagen") Optional<byte[]> imagen, @RequestParam("location") Optional<String> location,
						@RequestParam("description") Optional<String> description)
	{
		logger.info(String.format("insert user %s", email));

		int result = userService.insert(email, password, name, 
								phone.orElse(""), imagen.orElse(null), location.orElse(""),
								description.orElse(""));
		
		if(result == 0)
			return String.format("Cannot insert user %s", email);
		else
			return "OK";
	}
	
	@RequestMapping(value = "/users/user", method = RequestMethod.PUT)
	public String updateUser(@RequestParam("email") String email, @RequestParam("password") String password,
						@RequestParam("name") String name, @RequestParam("phone") Optional<String> phone,
						@RequestParam("imagen") Optional<byte[]> imagen, @RequestParam("location") Optional<String> location,
						@RequestParam("description") Optional<String> description, @RequestParam("id") int id)
	{
		logger.info(String.format("update user %s", email));
		
		int result = userService.update(email, password, name, 
								phone.orElse(""), imagen.orElse(null), location.orElse(""),
								description.orElse(""), id);
		
		if(result == 0)
			return String.format("Cannot update user %s", email);
		else
			return "OK";
	}
	
	@RequestMapping(value = "/users/user", method = RequestMethod.DELETE)
	public void deleteUser(@RequestParam("id") int id)
	{
		logger.info("delete user");
		
		userService.delete(id);
	}
	
	@RequestMapping(value = "/users/initiatives/created", method = RequestMethod.GET)
	public List<Map<String, Object>> initiativesCreated(@RequestParam("email") String email)
	{
		logger.info(String.format("get initiative created by user [%s]", email));
		
		List<Map<String, Object>> list = userService.getInitiativeCreated(email);
		return list;
	}
	
	@RequestMapping(value = "/users/initiatives/joined", method = RequestMethod.GET)
	public List<Map<String, Object>> initiativesJoined(@RequestParam("email") String email)
	{
		logger.info(String.format("get initiative joined by user [%s]", email));
		
		List<Map<String, Object>> list = joinInitiativeService.getInitiativeJoinedByUser(email);
		return list;
	}
	
	@RequestMapping(value = "/users/followed", method = RequestMethod.GET)
	public List<Map<String, Object>> followedList(@RequestParam("email") String email)
	{
		logger.info(String.format("get followed by user [%s]", email));
		
		List<Map<String, Object>> list = followUserService.getListFollowed(email);
		return list;
	}
	
	@RequestMapping(value = "/users/followers", method = RequestMethod.GET)
	public List<Map<String, Object>> followersList(@RequestParam("email") String email)
	{
		logger.info(String.format("get followers by user [%s]", email));
		
		List<Map<String, Object>> list = followUserService.getListFollowers(email);
		return list;
	}
	
	@RequestMapping(value = "/users/followed", method = RequestMethod.POST)
	public String insertFollowed(@RequestParam("userEmail") String userEmail, @RequestParam("userFollowEmail") String userFollowEmail)
	{
		logger.info(String.format("insert followed by user [%s | %s]", userEmail, userFollowEmail));
		
		int result = followUserService.insert(userEmail, userFollowEmail);

		if(result == 0)
			return String.format("Cannot insert follow followUserService [%s]", userFollowEmail);
		else
			return "OK";
	}
	
	@RequestMapping(value = "/users/followed", method = RequestMethod.DELETE)
	public void deleteFollowed(@RequestParam("userEmail") String userEmail, @RequestParam("userFollowEmail") String userFollowEmail)
	{
		logger.info(String.format("delete followed by user [%s | %s]", userEmail, userFollowEmail));
		
		followUserService.delete(userEmail, userFollowEmail);
	}
	
	@RequestMapping(value = "/users/reviews", method = RequestMethod.GET)
	public List<Map<String, Object>> getReviews(@RequestParam("email") String email)
	{
		logger.info(String.format("get reviews of user [%s]", email));
		
		List<Map<String, Object>> list = reviewUserService.getList(email);
		return list;
	}
	
	@RequestMapping(value = "/users/reviews", method = RequestMethod.POST)
	public String insertReview(@RequestParam("date") String date, @RequestParam("userEmail") String userEmail, 
									@RequestParam("userReviewEmail") String userReviewEmail, @RequestParam("review") Optional<String> review, 
									@RequestParam("stars") int stars)
	{
		logger.info(String.format("insert review on userReview by user [%s | %s | %s | %d]", userEmail, userReviewEmail, review, stars));
		
		int result = reviewUserService.insert(date, userEmail, userReviewEmail, review.orElse(""), stars);

		if(result == 0)
			return String.format("Cannot insert review reviewUserService [%s]", userReviewEmail);
		else
			return "OK";
	}
}

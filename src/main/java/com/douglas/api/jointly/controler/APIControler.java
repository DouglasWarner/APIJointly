package com.douglas.api.jointly.controler;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.api.jointly.model.Initiative;
import com.douglas.api.jointly.model.User;
import com.douglas.api.jointly.model.UserFollowUser;
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
	
	@RequestMapping(value = "/initiatives", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> getListInitiative()
	{
		return initiativeService.getList();
	}
	
	@RequestMapping(value = "/initiatives/listByName", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> getListByName(@RequestParam("name") String name)
	{
		return initiativeService.getListByName(name);
	}
	
	@RequestMapping(value = "/initiatives/initiative", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Initiative getInitiativeById(@RequestParam("id") int id)
	{
		return initiativeService.getInitiativeById(id);
	}
	
	@RequestMapping(value = "/initiatives/initiative", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Initiative insertInitiative(@RequestParam("name") String name, @RequestParam("targetDate") GregorianCalendar targetDate,
									@RequestParam("description") Optional<String> description, @RequestParam("targetArea") String targetArea,
									@RequestParam("location") String location, @RequestParam("imagen") Optional<byte[]> imagen,
									@RequestParam("targetAmount") int targetAmount, @RequestParam("createdBy") String createdBy)
	{
		return initiativeService.insert(name, targetDate, description.orElse(""), 
								targetArea, location, imagen.orElse(null), 
								targetAmount, "0", createdBy, "random");
	}
	
	@RequestMapping(value = "/initiatives/initiative", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Initiative updateInitiative(@RequestParam("name") String name, @RequestParam("targetDate") GregorianCalendar targetDate,
									@RequestParam("description") Optional<String> description, @RequestParam("targetArea") String targetArea,
									@RequestParam("location") String location, @RequestParam("imagen") Optional<byte[]> imagen,
									@RequestParam("targetAmount") int targetAmount, @RequestParam("id") int id)
	{
		return initiativeService.update(name, targetDate, description.orElse(""), 
								targetArea, location, imagen.orElse(null), 
								targetAmount, "status", id);
	}
	
	@RequestMapping(value = "/initiatives/initiative", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteInitiative(@RequestParam("id") int id)
	{
		initiativeService.delete(id);
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> getUsersJoined(@RequestParam("idInitiative") int idInitiative)
	{
		return joinInitiativeService.getUsersJoined(idInitiative);
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserJoinInitiative insertUsersJoined(@RequestParam("date") GregorianCalendar date, @RequestParam("idInitiative") int idInitiative, @RequestParam("userEmail") String userEmail,
											@RequestParam("type") int type)
	{
		return joinInitiativeService.insert(date, idInitiative, userEmail, type);
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserJoinInitiative updateUsersJoined(@RequestParam("date") GregorianCalendar date, @RequestParam("idInitiative") int idInitiative, 
												@RequestParam("userEmail") String userEmail, @RequestParam("type") int type)
	{
		return joinInitiativeService.update(date, idInitiative, userEmail, type);
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUsersJoined(@RequestParam("date") GregorianCalendar date, @RequestParam("idInitiative") int idInitiative, 
											@RequestParam("userEmail") String userEmail)
	{
		joinInitiativeService.delete(date, idInitiative, userEmail);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> getListUser()
	{
		return userService.getList();
	}
	
	@RequestMapping(value = "/users/user", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@RequestParam("email") String email)
	{
		return userService.getUser(email);
	}
	
	@RequestMapping(value = "/users/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User insertUser(@RequestParam("email") String email, @RequestParam("password") String password,
						@RequestParam("name") String name, @RequestParam("phone") Optional<String> phone,
						@RequestParam("imagen") Optional<byte[]> imagen, @RequestParam("location") Optional<String> location,
						@RequestParam("description") Optional<String> description)
	{
		return userService.insert(email, password, name, 
								phone.orElse(""), imagen.orElse(null), location.orElse(""),
								description.orElse(""));
	}
	
	@RequestMapping(value = "/users/user", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@RequestParam("email") String email, @RequestParam("password") String password,
						@RequestParam("name") String name, @RequestParam("phone") Optional<String> phone,
						@RequestParam("imagen") Optional<byte[]> imagen, @RequestParam("location") Optional<String> location,
						@RequestParam("description") Optional<String> description, @RequestParam("id") int id)
	{
		return userService.update(email, password, name, 
								phone.orElse(""), imagen.orElse(null), location.orElse(""),
								description.orElse(""), id);
	}
	
	@RequestMapping(value = "/users/user", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUser(@RequestParam("id") int id)
	{
		userService.delete(id);
	}
	
	@RequestMapping(value = "/users/initiatives/created", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> initiativesCreated(@RequestParam("email") String email)
	{
		List<Map<String, Object>> list = userService.getInitiativeCreated(email);
		return list;
	}
	
	@RequestMapping(value = "/users/initiatives/joined", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> initiativesJoined(@RequestParam("userEmail") String userEmail)
	{
		List<Map<String, Object>> list = joinInitiativeService.getInitiativeJoinedByUser(userEmail);
		return list;
	}
	
	@RequestMapping(value = "/users/followed", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> followedList(@RequestParam("email") String email)
	{
		List<Map<String, Object>> list = followUserService.getListFollowed(email);
		return list;
	}
	
	@RequestMapping(value = "/users/followers", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> followersList(@RequestParam("email") String email)
	{
		List<Map<String, Object>> list = followUserService.getListFollowers(email);
		return list;
	}
	
	@RequestMapping(value = "/users/followed", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserFollowUser insertFollowed(@RequestParam("userEmail") String userEmail, @RequestParam("userFollowEmail") String userFollowEmail)
	{
		UserFollowUser followUser = followUserService.insert(userEmail, userFollowEmail);
		return followUser;
	}
	
	@RequestMapping(value = "/users/followed", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteFollowed(@RequestParam("userEmail") String userEmail, @RequestParam("userFollowEmail") String userFollowEmail)
	{
		followUserService.delete(userEmail, userFollowEmail);
	}
	
	@RequestMapping(value = "/users/reviews/list", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, Object>> getReviews(@RequestParam("email") String email)
	{
		List<Map<String, Object>> list = reviewUserService.getList(email);
		return list;
	}
	
	@RequestMapping(value = "/users/reviews", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserReviewUser insertReview(@RequestParam("date") GregorianCalendar date, @RequestParam("userEmail") String userEmail, 
									@RequestParam("userReviewEmail") String userReviewEmail, @RequestParam("review") Optional<String> review, 
									@RequestParam("stars") int stars)
	{
		UserReviewUser reviewUser = reviewUserService.insert(date, userEmail, userReviewEmail, review.orElse(""), stars);
		return reviewUser;
	}
}

package com.douglas.api.jointly.controler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.douglas.api.jointly.Utils;
import com.douglas.api.jointly.model.Chat;
import com.douglas.api.jointly.model.Initiative;
import com.douglas.api.jointly.model.User;
import com.douglas.api.jointly.model.UserFollowUser;
import com.douglas.api.jointly.model.UserJoinInitiative;
import com.douglas.api.jointly.model.UserReviewUser;
import com.douglas.api.jointly.services.ChatService;
import com.douglas.api.jointly.services.CountriesService;
import com.douglas.api.jointly.services.InitiativeService;
import com.douglas.api.jointly.services.TargetAreaService;
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
	@Autowired
	private TargetAreaService targetAreaService;
	@Autowired
	private CountriesService countriesService;
	@Autowired
	private ChatService chatService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public APIResponse simpleCall() {
		logger.info("Simple call to try connection");
		
		return new APIResponse(false, "OK", null);
	}
	
	@RequestMapping(value = "/initiatives", method = RequestMethod.GET)
	public APIResponse getListInitiative()
	{
		logger.info("Get list initiative");

		List<Map<String, Object>> list = initiativeService.getList();

		list.forEach(x -> x.replace("created_at", ((LocalDateTime)x.get("created_at")).format(Utils.FORMAT2)));
		list.forEach(x -> x.replace("target_date", ((LocalDateTime)x.get("target_date")).format(Utils.FORMAT2)));
		
		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/initiatives/listByName", method = RequestMethod.GET)
	public APIResponse getListByName(@RequestParam("name") String name)
	{
		logger.info(String.format("Get list initiative by name [%s]", name));
		List<Map<String, Object>> list = initiativeService.getListByName(name);		

		list.forEach(x -> x.replace("created_at", ((LocalDateTime)x.get("created_at")).format(Utils.FORMAT2)));
		list.forEach(x -> x.replace("target_date", ((LocalDateTime)x.get("target_date")).format(Utils.FORMAT2)));

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
	public APIResponse insertInitiative(@RequestParam("name") String name, @RequestParam("created_at") String createdAt, @RequestParam("target_date") String targetDate,
									@RequestParam("description") Optional<String> description, @RequestParam("target_area") String targetArea,
									@RequestParam("location") String location, @RequestParam("target_amount") int targetAmount,
									@RequestParam("created_by") String createdBy, @RequestParam("ref_code") String ref_code, Optional<MultipartFile> imagen)
	{
		logger.info(String.format("insert initiative [%s]", name));
		
		Initiative initiative = new Initiative(name, createdAt, targetDate, description.orElse(""), 
				targetArea, location, targetAmount, "A", createdBy, ref_code);
		
		if(imagen.isPresent()) {
			Path file = Paths.get(Utils.PATH_IMAGES);
			String rutaAbsoluta = file.toFile().getAbsolutePath();
			
			byte[] bs;
			try {
				bs = imagen.get().getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta +"//"+ imagen.get().getOriginalFilename());
				Files.write(rutaCompleta, bs);
				initiative.setImagen(imagen.get().getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
				initiative.setImagen("");
			}
		}
		
		long result = 0;
		
		try {
			result = initiativeService.insert(initiative);
			
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
									@RequestParam("location") String location, @RequestParam("imagen") Optional<MultipartFile> imagen,
									@RequestParam("targetAmount") int targetAmount, @RequestParam("id") long id)
	{
		logger.info(String.format("update initiative [%d]", id));
		int result = 0;
		
		Initiative initiative = new Initiative(id, name, targetDate, description.orElse(""), 
				targetArea, location, targetAmount);
		
		if(!imagen.isEmpty()) {
			Path file = Paths.get(Utils.PATH_IMAGES);
			String rutaAbsoluta = file.toFile().getAbsolutePath();
			
			byte[] bs;
			try {
				bs = imagen.get().getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta +"//"+ imagen.get().getOriginalFilename());
				Files.write(rutaCompleta, bs);
				initiative.setImagen(imagen.get().getOriginalFilename());
			} catch (IOException e) {
				initiative.setImagen("");
			}
		}
		
		try {
			result = initiativeService.update(initiative.getName(), initiative.getTargetDate(), initiative.getDescription(), 
											initiative.getTargetArea(), initiative.getLocation(), initiative.getImagen(), 
											initiative.getTargetAmount(), "A", initiative.getId());
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
		
		Initiative imagen = initiativeService.getInitiativeById(id);
		
		try {
			result = initiativeService.delete(id);
		} catch (Exception e) {
			String message = String.format("ERR : %s - [id=%d]", e.getCause(), id);
			logger.error(message);
			return new APIResponse(true, message, null);
		}
		
		if(result == 0)
			return new APIResponse(true, String.format("ERR : Cannot delete or not found initiative id [%d]", id), null);
		else {
			Path file = Paths.get(Utils.PATH_IMAGES);
			String rutaAbsoluta = file.toFile().getAbsolutePath();
			Path rutaCompleta = Paths.get(rutaAbsoluta +"//"+ imagen.getImagen());
			try {
				Files.delete(rutaCompleta);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new APIResponse(false, "OK", null);
		}
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.GET)
	public APIResponse getUsersJoined()
	{
		logger.info(String.format("get list usersJoined"));
	
		List<Map<String, Object>> list = joinInitiativeService.getListUsersJoined();
		
		for (Map<String, Object> map : list) {
			if(map.containsKey("type"))
			{
				if(map.get("type").equals(true))
					map.replace("type", (boolean) (map.get("type")) ? "1" : "0");
				else
					map.replace("type", "0");
			}
		}		

		list.forEach(x -> x.replace("date", ((LocalDateTime)x.get("date")).format(Utils.FORMAT2)));
		
		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/initiatives/usersJoinedByInitiative", method = RequestMethod.GET)
	public APIResponse getUsersJoinedByInitiative(@RequestParam("idInitiative") long idInitiative)
	{
		logger.info(String.format("get list usersJoined [%d]", idInitiative));
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		try {
			list = joinInitiativeService.getListUsersJoinedByInitiative(idInitiative);

			list.forEach(x -> x.replace("created_at", ((LocalDateTime)x.get("created_at")).format(Utils.FORMAT2)));
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new APIResponse(true, "ERR - " + e.getMessage(), null);
		}
		
		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/initiatives/usersJoined", method = RequestMethod.POST)
	public APIResponse insertUsersJoined(@RequestParam("date") String date, @RequestParam("idInitiative") long idInitiative, @RequestParam("userEmail") String userEmail,
											@RequestParam("type") int type)
	{
		logger.info(String.format("insert userJoined [%d | %s]", idInitiative, userEmail));
		long result = 0;
		UserJoinInitiative joinInitiative = new UserJoinInitiative(idInitiative, userEmail, Utils.getFormatStringDate(date), type);
				
		try {
			initiativeService.getInitiativeById(idInitiative);
		} catch (Exception e) {
			logger.error("ERR : Initiative doesn't exists");
			return new APIResponse(true, "ERR : Initiative doesn't exists", null);
		}
		
		try {
			result = joinInitiativeService.insert(idInitiative, userEmail, type, date);
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

		list.forEach(x -> x.replace("created_at", ((LocalDateTime)x.get("created_at")).format(Utils.FORMAT2)));
		
		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/users/user", method = RequestMethod.GET)
	public APIResponse getUser(@RequestParam("email") String email)
	{
		logger.info(String.format("Get user by email [%s]", email));
		User user = null;
		String messageErrorEmptyResult = String.format("ERR : No user found for email [%s]", email);
		
		try {
			user = userService.getUser(email);
			return new APIResponse(false, "OK", user);
		} catch (EmptyResultDataAccessException e) {
			logger.info(messageErrorEmptyResult);
			return new APIResponse(true, messageErrorEmptyResult, null);
		} catch (Exception e) {
			return new APIResponse(true, "ERR : " + e.getCause(), null);
		}
	}
	
	//TODO comprobar insert user de firebase
	@RequestMapping(value = "/users/user", method = RequestMethod.POST)
	public APIResponse insertUser(@RequestParam("email") String email, @RequestParam("password") String password,
						@RequestParam("name") String name, @RequestParam("phone") Optional<String> phone,
						@RequestParam("imagen") Optional<MultipartFile> imagen, @RequestParam("location") Optional<String> location,
						@RequestParam("description") Optional<String> description, @RequestParam("created_at") String created_at)
	{
		logger.info(String.format("insert user %s", email));
		int result = 0;
		User user = new User(email, password, name, phone.orElse(""), null, location.orElse(""), description.orElse(""), created_at);
		String messageErrorDuplicateEntry = String.format("ERR : User already exists");
		
		try {
			result = userService.insert(email, password, name, 
								phone.orElse(""), null, location.orElse(""),
								description.orElse(""), created_at);
			user.setId(result);
		} catch (DuplicateKeyException e) {
			logger.info(messageErrorDuplicateEntry);
			return new APIResponse(true, messageErrorDuplicateEntry, null);
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
						@RequestParam("imagen") Optional<MultipartFile> imagen, @RequestParam("location") Optional<String> location,
						@RequestParam("description") Optional<String> description, @RequestParam("id") int id)
	{
		logger.info(String.format("update user %s", email));
		
		int result = 0;
		
		try {
			result = userService.update(email, password, name,
								phone.orElse(""), null, location.orElse(""),
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
	public APIResponse getInitiativesCreatedByUser(@RequestParam("email") String email)
	{
		logger.info(String.format("get initiative created by user [%s]", email));
		
		List<Map<String, Object>> list = userService.getListInitiativeCreatedByUser(email);
		
		list.forEach(x -> x.replace("created_at", ((LocalDateTime)x.get("created_at")).format(Utils.FORMAT2)));
		list.forEach(x -> x.replace("target_date", ((LocalDateTime)x.get("target_date")).format(Utils.FORMAT2)));
		
		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/users/initiatives/joined", method = RequestMethod.GET)
	public APIResponse getInitiativesJoinedByUser(@RequestParam("email") String email, @RequestParam("type") Optional<Integer> type)
	{
		logger.info(String.format("get initiative joined by user [%s]", email));
		
		List<Map<String, Object>> list = joinInitiativeService.getListInitiativeJoinedByUser(email, type.orElse(Utils.JOIN));

		list.forEach(x -> x.replace("created_at", ((LocalDateTime)x.get("created_at")).format(Utils.FORMAT2)));
		list.forEach(x -> x.replace("target_date", ((LocalDateTime)x.get("target_date")).format(Utils.FORMAT2)));
		
		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/users/follows", method = RequestMethod.GET)
	public APIResponse getListFollows() 
	{
		logger.info(String.format("get list follows"));
		
		List<Map<String, Object>> list = followUserService.getListFollows();

		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/users/followed", method = RequestMethod.GET)
	public APIResponse followedList(@RequestParam("email") String email)
	{
		logger.info(String.format("get followed by user [%s]", email));
		
		List<Map<String, Object>> list = followUserService.getListFollowed(email);

		list.forEach(x -> x.replace("created_at", ((LocalDateTime)x.get("created_at")).format(Utils.FORMAT2)));

		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/users/followers", method = RequestMethod.GET)
	public APIResponse followersList(@RequestParam("email") String email)
	{
		logger.info(String.format("get followers by user [%s]", email));
		
		List<Map<String, Object>> list = followUserService.getListFollowers(email);
		
		list.forEach(x -> x.replace("created_at", ((LocalDateTime)x.get("created_at")).format(Utils.FORMAT2)));
		
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
				return new APIResponse(true, "ERR : " + e.getMessage(), null);
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
	
	/**
	 * Obtiene todos los review del sistema.
	 * Si se le pasa un usuario, se obtiene los de ese usuario
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/users/reviews", method = RequestMethod.GET)
	public APIResponse getReviews(@RequestParam("email") Optional<String> email)
	{
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		if(email.isPresent()) {
			logger.info(String.format("get reviews of user [%s]", email));
		
			list = reviewUserService.getListByUser(email.get());
		} else {
			logger.info(String.format("get list review"));
		
			list = reviewUserService.getListReviews();
		}
				
		list.forEach(x -> x.replace("date", ((LocalDateTime)x.get("date")).format(Utils.FORMAT2)));

		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/users/reviews", method = RequestMethod.POST)
	public APIResponse insertReview(@RequestBody UserReviewUser reviewUser)
	{
		logger.info("insert review on userReview");
		int result = 0;
		UserReviewUser userReviewUser = new UserReviewUser(reviewUser.getUser(), reviewUser.getUserReview(), reviewUser.getDate(),
													reviewUser.getReview(), reviewUser.getStars());
		
		try {
			result = reviewUserService.insert(userReviewUser);
		} catch (Exception e) {
			String message = String.format("ERR : %s ", e.getCause());
			logger.error(message);
			return new APIResponse(true, message, userReviewUser);
		}
		
		if(result == 0)
			return new APIResponse(true, String.format("ERR : Cannot insert review reviewUserService user [%s]", userReviewUser.getUserReview()), userReviewUser);
		else {
			userReviewUser.setIs_deleted(false);
			userReviewUser.setIs_sync(true);
			return new APIResponse(false, "OK", userReviewUser);
		}
	}
	
	@RequestMapping(value = "/targetAreas", method = RequestMethod.GET)
	public APIResponse getListTargetArea() {
		logger.info("GET list target area");
		
		List<Map<String, Object>> list = targetAreaService.getList();

		return new APIResponse(false, "OK", list);		
	}
	
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public APIResponse getListCountries() {
		logger.info("GET list countries");
		
		List<Map<String, Object>> list = countriesService.getList();

		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public APIResponse getListChat() {
		logger.info("GET list chat");
		
		List<Map<String, Object>> list = chatService.getList();

		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/chat/initiative", method = RequestMethod.GET)
	public APIResponse getListChat(@RequestParam("id_initiative") long id_initiative) {
		logger.info("GET list chat by initiative");
		
		List<Map<String, Object>> list = chatService.getListByInitiative(id_initiative);

		return new APIResponse(false, "OK", list);
	}
	
	@RequestMapping(value = "/chat/initiative", method = RequestMethod.POST)
	public APIResponse insertMessageChat(@RequestBody Chat chat) {
		logger.info("INSERT chat message");
		long result = 0;
		
		try {
			result = chatService.insert(chat);
		} catch (Exception e) {
			return new APIResponse(true, "ERR", null);
		}

		try {
			return new APIResponse(false, "OK", chatService.getChatMessage(chat.getIdInitiative(), chat.getDate(), chat.getEmailUser()));
		} catch (Exception e) {
			return new APIResponse(true, "ERR", null);
		}
	}
	
	@RequestMapping(value = "/initiatives/initiative/sync", method = RequestMethod.POST)
	public APIResponse syncInitiative(@RequestBody Initiative... initiatives) {
		
		logger.info("Sync data initiatives");
		
		try {
			for (Initiative initiative : initiatives) {
				if(!initiative.isIs_sync()) {
					
					Initiative tmp = initiativeService.getInitiativeToSync(initiative.getId(), initiative.getCreatedBy());
	
					if(tmp != null) {	// si existe en la tabla
						if(initiative.isIs_deleted()) {
							initiativeService.delete(initiative.getId());	// lo elimina de la tabla
						} else {
							initiativeService.updateSync(initiative);	// lo actualiza
						}
					} else if (!initiative.isIs_deleted()) {			// si no existe en la tabla lo crea
						initiative.setId(0);
						long result = initiativeService.insert(initiative);
						logger.info(result);
					}	
				}
			}
		} catch (Exception e) {
			return new APIResponse(true, "ERR", null);	
		}
		
		return new APIResponse(false, "OK", null);
	}
	
	@RequestMapping(value = "/users/follows/sync", method = RequestMethod.POST)
	public APIResponse syncUserFollow(@RequestBody UserFollowUser... followUsers) {
		
		logger.info("Sync data userFollows");
		
		try {
			for (UserFollowUser followUser: followUsers) {
				if(!followUser.isIs_sync()) {
					
					UserFollowUser tmp = followUserService.getUserFollowUser(followUser.getUser(), followUser.getUser_follow());
	
					if(tmp == null) {	// si no existe en la tabla lo crea
						if(!followUser.isIs_deleted()) {
							long result = followUserService.insert(followUser.getUser(), followUser.getUser_follow());
							logger.info(result);
						}
					} else if(followUser.isIs_deleted()) {	// lo elimina de la tabla
						followUserService.delete(followUser.getUser(), followUser.getUser_follow());
					}					
				} 
			}
		} catch (Exception e) {
			return new APIResponse(true, "ERR", null);	
		}
		
		return new APIResponse(false, "OK", null);
	}
	
	@RequestMapping(value = "/initiatives/userjoin/sync", method = RequestMethod.POST)
	public APIResponse syncUserJoin(@RequestBody UserJoinInitiative... joinInitiatives) {
		
		logger.info("Sync data UserJoinInitiative");
		
		try {
			for (UserJoinInitiative joinInitiative : joinInitiatives) {
				if(!joinInitiative.isIs_sync()) {
					
					UserJoinInitiative tmp = joinInitiativeService.getUserJoinInitiative(joinInitiative.getIdInitiative(), joinInitiative.getUserEmail());
	
					if(tmp != null) {	// si existe en la tabla
						if(joinInitiative.isIs_deleted()) {
							joinInitiativeService.delete(joinInitiative.getIdInitiative(), joinInitiative.getUserEmail());	// lo elimina de la tabla
						} else {
							joinInitiativeService.updateSync(joinInitiative);	// lo actualiza
						}
					} else if (!joinInitiative.isIs_deleted()) {			// si no existe en la tabla lo crea
						long result = joinInitiativeService.insert(joinInitiative);
						logger.info(result);
					}	
				}
			}
		} catch (Exception e) {
			return new APIResponse(true, "ERR", null);	
		}
		
		return new APIResponse(false, "OK", null);
	}
	
	@RequestMapping(value = "/users/userreview/sync", method = RequestMethod.POST)
	public APIResponse syncUserReview(@RequestBody UserReviewUser... reviewUsers) {
		
		logger.info("Sync data UserReviews");
		
		try {
			for (UserReviewUser reviewUser : reviewUsers) {
				if(!reviewUser.isIs_sync()) {
					
					UserReviewUser tmp = reviewUserService.getReview(reviewUser.getUser(), reviewUser.getUserReview(), reviewUser.getDate());
	
					if(tmp != null) {	// si existe en la tabla
						if(reviewUser.isIs_deleted()) {
							reviewUserService.delete(reviewUser.getUser(), reviewUser.getUserReview(), reviewUser.getDate());	// lo elimina de la tabla
						} else {
							reviewUserService.updateSync(reviewUser);	// lo actualiza
						}
					} else if (!reviewUser.isIs_deleted()) {			// si no existe en la tabla lo crea
						long result = reviewUserService.insert(reviewUser);
						logger.info(result);
					}	
				}
			}
		} catch (Exception e) {
			return new APIResponse(true, "ERR", null);	
		}
		
		return new APIResponse(false, "OK", null);
	}
	
	@RequestMapping(value = "/chat/sync", method = RequestMethod.POST)
	public APIResponse syncChat(@RequestBody Chat... chats) {
		
		logger.info("Sync data Chat");
		
		try {
			for (Chat chat : chats) {
				if(!chat.isIs_sync()) {
					
					Chat tmp = chatService.getChatMessage(chat.getIdInitiative(), chat.getDate(), chat.getEmailUser());
	
					if(tmp == null) {
						chatService.insert(chat);
					}
				}
			}
		} catch (Exception e) {
			return new APIResponse(true, "ERR", null);	
		}
		
		return new APIResponse(false, "OK", null);
	}
	
	@RequestMapping(value = "/qr", method = RequestMethod.GET)
	public String getQR() {
		return "mi direccion para descargar qr";
	}
}

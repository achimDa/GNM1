package com.servustech.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.servustech.spring.model.User;
import com.servustech.spring.model.dto.UserModelDTO;
import com.servustech.spring.response.CollectionResponse;
import com.servustech.spring.response.ItemResponse;
import com.servustech.spring.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * List user method
	 *
	 * @param id
	 * @return lists all the users  based on user id
	 */

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CollectionResponse<UserModelDTO> listUser() {
		return new CollectionResponse<>(userService.listUser(), UserModelDTO.HEADERS);
	}

	/**
	 * add user method
	 *
	 * @param user
	 * @return adds a new user in the list and redirects to the user list
	 */

	
	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ItemResponse<UserModelDTO> addUser(@RequestBody final UserModelDTO userModelDTO) {
		userService.save(userModelDTO);
		return new ItemResponse<UserModelDTO>(userModelDTO, UserModelDTO.HEADERS);

	}

	/**
	 * remove user method
	 *
	 * @param id
	 * @return removes an user from the list and redirects to the user list
	 */

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@ResponseBody
	public  void removeUser(@PathVariable("id") final int id) {
		userService.removeUser(id, User.class);

	}

	/**
	 * edit user method
	 *
	 * @param id
	 * @param model
	 * @return edits an user based on id and loads it at the end of the user
	 *         list
	 */
	
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ItemResponse<UserModelDTO> editUser(@RequestBody final UserModelDTO userModelDTO) {
		userService.editUser(userModelDTO);
		return new ItemResponse<UserModelDTO>(userModelDTO, UserModelDTO.HEADERS);
	}

}
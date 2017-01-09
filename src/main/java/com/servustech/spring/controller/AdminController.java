/**
 *
 */
package com.servustech.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author user
 *
 */
@Controller
public class AdminController {

	@RequestMapping(value = "/admin/ping", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public void listCity(final HttpServletRequest request, final HttpServletResponse response) {
		System.out.println("bla bla");
	}
}

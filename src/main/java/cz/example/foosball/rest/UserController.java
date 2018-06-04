package cz.example.foosball.rest;

import cz.example.foosball.config.SimpleUser;
import cz.example.foosball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

	public static final String MAPPING_BASE = "/rest/user";

	@Autowired
	public UserService userService;

	@RequestMapping(MAPPING_BASE)
	public Principal getUser(Authentication authentication) {
		return authentication;
	}

	@RequestMapping(MAPPING_BASE + "/simpleUser")
	public SimpleUser getSimpleUser() {
		if(userService.isOAuth2Authentication()) {
			return userService.getSimpleUser();
		}
		return SimpleUser.principal(userService.getAuthentication());
	}
}

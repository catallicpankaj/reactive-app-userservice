package com.xcomp.sample.graphql.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.xcomp.sample.model.User;
import com.xcomp.sample.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GetUserDetailsByIdQuery implements GraphQLQueryResolver {

	@Autowired
	UserService userService;

	public User getUserDetails(String id, String delayTime) {
		log.info("Getting UserInfo for id-{}, and delayTime used is-{}", id, delayTime);
		return userService.getUserDetails(id, delayTime).block();
	}
}

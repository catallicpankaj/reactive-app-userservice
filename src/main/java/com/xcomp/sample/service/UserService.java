package com.xcomp.sample.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.xcomp.sample.model.AllUsers;
import com.xcomp.sample.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("userService")
public class UserService {

	@Autowired
	@Qualifier("webClientSSL")
	WebClient webClient;

	public Mono<User> getUserDetails(String id, String delayTime) {
		URI getUserUri = UriComponentsBuilder.fromUriString("https://reqres.in/api/users/" + id + "?delay=" + delayTime)
		        .build()
		        .toUri();
		Mono<User> userData = webClient.get().uri(getUserUri).headers(header -> {
			header.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		}).retrieve().bodyToMono(User.class);
		return userData;
	}

	public Mono<AllUsers> getAllUserDetails(String pageNumber, String delayTime) {
		URI getAllUserUri = UriComponentsBuilder
		        .fromUriString("https://reqres.in/api/users?page=" + pageNumber + "&delay=" + delayTime)
		        .build()
		        .toUri();
		Mono<AllUsers> allUsersData = webClient.get().uri(getAllUserUri).headers(header -> {
			header.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		}).retrieve().bodyToMono(AllUsers.class).log();
		return allUsersData;
	}

	public Mono<User> getAdditionalDetails(String id, String delayTime, String pageNumber) {
		return getAllUserDetails(pageNumber, delayTime).flatMap(
		        users -> Flux.fromIterable(users.getData()).filter(itr -> itr.getId().toString().equals(id)).next())
		        .map(t -> getUserDetails(t.getId().toString(), delayTime))
		        .flatMap(mapper -> mapper)
		        .log();
	}

}

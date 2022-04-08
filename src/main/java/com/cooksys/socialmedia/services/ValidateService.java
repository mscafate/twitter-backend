package com.cooksys.socialmedia.services;

public interface ValidateService {

	Boolean hashtagExists(String label);

	Boolean usernameExists(String username);

	Boolean usernameAvailable(String username);

}

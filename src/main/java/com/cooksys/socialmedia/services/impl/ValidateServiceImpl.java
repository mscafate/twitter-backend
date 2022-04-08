package com.cooksys.socialmedia.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.entities.Hashtag;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.repositories.HashtagRepository;
import com.cooksys.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {
	
	private final HashtagRepository hashtagRepository;
	
	private final UserRepository userRepository;
	

	@Override
	public Boolean hashtagExists(String label) {
		//String label2 = '#' + label;
		Optional<Hashtag> existingHashtag = hashtagRepository.findHashtagByLabel(label);
		return existingHashtag.isPresent();
	}

	@Override
	public Boolean usernameExists(String username) {
		Optional<User> findByUsername = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
		return findByUsername.isPresent();
	}

	@Override
	public Boolean usernameAvailable(String username) {
		Optional<User> findByUsername = userRepository.findByCredentialsUsernameAndDeletedFalse(username);
		if(findByUsername.isPresent()) {
			return false;
		} else {
			return true;
		}
	}

}

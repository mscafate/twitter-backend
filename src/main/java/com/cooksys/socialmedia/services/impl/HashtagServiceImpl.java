package com.cooksys.socialmedia.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.dtos.HashtagResponseDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.entities.Hashtag;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.mappers.HashtagMapper;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.repositories.HashtagRepository;
import com.cooksys.socialmedia.services.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

	private final HashtagMapper hashtagMapper;

	private final HashtagRepository hashtagRepository;

	private final TweetMapper tweetMapper;
	
	private final ValidateServiceImpl validateServiceImpl;


	@Override
	public List<HashtagResponseDto> getAllHashtags() {
		List<Hashtag> allHashtags = hashtagRepository.findAll();
		return hashtagMapper.entitiesToDtos(allHashtags);
	}
	
	@Override
	public List<TweetResponseDto> getAllTweetsByHashtag(String label) throws NotFoundException {
		if(validateServiceImpl.hashtagExists(label)) {
		Optional<Hashtag> hashtagByLabel = hashtagRepository.findByLabel(label);
		List<TweetResponseDto> tweetsWithHashtag = new ArrayList<>();
		for (Tweet tweet : hashtagByLabel.get().getTweets()) {
			if(!tweet.isDeleted()) {
				tweetsWithHashtag.add(tweetMapper.entityToDto(tweet));
			}
		}
		List<TweetResponseDto> tweetsReversed = new ArrayList<>();
		for (int i = tweetsWithHashtag.size() - 1; i >= 0; i--) {
			
			tweetsReversed.add(tweetsWithHashtag.get(i));
		}
		return tweetsReversed;
		} else {
			throw new NotFoundException("Hashtag does not exist");
		}
	}

}

package com.cooksys.socialmedia.dtos;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetResponseDto {

	@Id
	@GeneratedValue
	private Long id;
	
	private UserResponseDto author;
	
	private Timestamp posted;

	private String content;
	
	private TweetResponseDto inReplyTo;
	
	private TweetResponseDto repostOf;
	
}

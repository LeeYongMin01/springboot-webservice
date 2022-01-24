package com.spring.study.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.study.domain.posts.Posts;
import com.spring.study.domain.posts.PostsRepository;
import com.spring.study.web.dto.PostsListResponseDto;
import com.spring.study.web.dto.PostsResponseDto;
import com.spring.study.web.dto.PostsSaveRequestDto;
import com.spring.study.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService
{
	private final PostsRepository postsRepository;

	@Transactional
	public Long save(PostsSaveRequestDto requestDto)
	{
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto)
	{
		Posts posts = postsRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("No Object. id=" + id));

		posts.update(requestDto.getTitle(), requestDto.getContent());

		return id;
	}

	public PostsResponseDto findById(Long id)
	{
		Posts entity = postsRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("No Object. id=" + id));

		return new PostsResponseDto(entity);
	}

	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc()
	{
		return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
	}
}

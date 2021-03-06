package com.spring.study.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest
{
	@Autowired
	PostsRepository postsRepository;

	@After
	public void cleanUp()
	{
		postsRepository.deleteAll();
	}

	@Test
	public void save()
	{
		// given
		String title = "title";
		String content = "content";

		postsRepository.save(Posts.builder().title(title).content(content).author("vbn0213@gmail.com").build());

		// when
		List<Posts> postsList = postsRepository.findAll();

		// then

		Posts posts = postsList.get(0);
		assertThat(posts.getTitle()).isEqualTo(title);
		assertThat(posts.getContent()).isEqualTo(content);
	}

	@Test
	public void BaseTimeEntityTest()
	{
		// given
		LocalDateTime now = LocalDateTime.of(2021, 6, 4, 0, 0, 0);
		postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

		// when
		List<Posts> postsList = postsRepository.findAll();

		// then
		Posts posts = postsList.get(0);

		System.out.println(
			">>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

		assertThat(posts.getCreatedDate()).isAfter(now);
		assertThat(posts.getModifiedDate()).isAfter(now);
	}
}

package com.java.teaching.blog.actuator;

import com.java.teaching.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlogPostsCountIndicator implements HealthIndicator {

    private final PostService postService;

    @Override
    public Health health() {
        long count = postService.getCountPosts();
        if (count > 0) {
            return Health.up()
                    .withDetail("message", String.format("Count posts in blog: %s", count))
                    .build();
        }
        return Health.down()
                .status(Status.DOWN)
                .withDetail("message", "List posts in blog is empty!!!")
                .build();
    }
}

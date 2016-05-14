package com.vtapadia.experiments.web;

import com.vtapadia.experiments.domain.User;
import com.vtapadia.experiments.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(path = "{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userId") Long userId) {
        return userRepository.findOne(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(
            @Validated @RequestBody User user
    ) {
        HttpServletRequest curRequest =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        User entity = userRepository.saveAndFlush(user);
        if (entity != null && entity.getId() != null) {
            return ResponseEntity
                    .created(UriComponentsBuilder
                            .fromHttpUrl(curRequest.getRequestURL().toString())
                            .pathSegment(entity.getId().toString())
                            .build().toUri())
                    .build();
        } else {
            return ResponseEntity.badRequest().body("Failed to create user");
        }
    }
}

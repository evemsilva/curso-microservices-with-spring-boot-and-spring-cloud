package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/jpa/users")
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Resource<User> retrieveUser(@PathVariable("id") int id) throws UserNotFoundException {
	User user = userDaoService.findOne(id);
	Resource<User> resource = new Resource<>(user);
	ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
	resource.add(linkTo.withRel("all-users"));
	return resource;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid User user){
        User savedUser = userDaoService.save(user);
	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
	return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) throws UserNotFoundException {
        userDaoService.deleteById(id);
	return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/posts")
    public List<Post> retrieveAllPostsFromUser(@PathVariable("id") int id){
	return userDaoService.findOne(id).getPosts();
    }

}

package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
	return repository.findAll();
    }

    public User save(User user) {
	return repository.save(user);
    }

    public User findOne(int id) {
        return repository.findById(id).orElseGet(() -> {
	    throw new UserNotFoundException(String.format("Usuário com id %d não encontrado", id));
	});
    }

    public void deleteById(int id){
	findOne(id);
	repository.deleteById(id);
    }

}

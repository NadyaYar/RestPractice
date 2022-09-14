package com.example.demo.user.service;

import com.example.demo.user.entity.MyUser;
import com.example.demo.user.exeption.UserExistException;
import com.example.demo.user.exeption.UserNotFoundException;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public MyUser createUser(MyUser user) throws UserExistException {
        if (userRepository.existsById(user.getId())) {
            throw new UserExistException("User with id: " + user.getId() + " already exist");
        }
        return userRepository.save(user);
    }

    public MyUser findById(long id) throws UserNotFoundException {
        isExists(id);
        return userRepository.findById(id).orElse(null);
    }

    public void delete(long id) throws UserNotFoundException {
        isExists(id);
        userRepository.deleteById(id);
    }

    public Iterable<MyUser> getUsers() {
        return userRepository.findAll();
    }

    public MyUser update(MyUser userToUpdate, Long id) throws UserNotFoundException {
        isExists(userToUpdate.getId());
        MyUser user = userRepository.findById(id).orElse(null);

        assert user != null;
        user.setName(userToUpdate.getName());
        user.setAge(userToUpdate.getAge());
        user.setCity(userToUpdate.getCity());

        return user;
    }

     private void isExists(long id) throws UserNotFoundException {
        boolean isExists = userRepository.existsById(id);
        if (!isExists) {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
    }
}



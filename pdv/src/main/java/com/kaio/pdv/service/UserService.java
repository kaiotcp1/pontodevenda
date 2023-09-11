package com.kaio.pdv.service;

import com.kaio.pdv.Entity.User;
import com.kaio.pdv.dto.UserDTO;
import com.kaio.pdv.exception.NoItemException;
import com.kaio.pdv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(user ->
            new UserDTO(user.getId(), user.getName(), user.isEnable())
        ).collect(Collectors.toList());
    }

    public UserDTO save(UserDTO user) {
        User userToSave = new User();
        userToSave.setEnable(user.isEnable());
        userToSave.setName(user.getName());
        userRepository.save(userToSave);
        return new UserDTO(userToSave.getId(), userToSave.getName(), userToSave.isEnable());
    }

    public UserDTO findById(long id) {
        Optional<User> optional = userRepository.findById(id);

        if(optional.isEmpty()) {
            throw new NoItemException("Usuário não encontrado");
        }

         User user = optional.get();
         return new UserDTO(user.getId(), user.getName(), user.isEnable());
    }

    public UserDTO update(UserDTO user) {
        User userToSave = new User();
        userToSave.setEnable(user.isEnable());
        userToSave.setName(user.getName());
        userToSave.setId(user.getId());

        Optional<User> userToEdit = userRepository.findById(user.getId());
        if(userToEdit.isEmpty()) {
            throw new NoItemException("Usuário não encontrado.");
        }

        userRepository.save(userToSave);
        return new UserDTO(userToSave.getId(), userToSave.getName(), userToSave.isEnable());

    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

}

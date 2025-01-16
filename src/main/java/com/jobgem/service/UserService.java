package com.jobgem.service;

import com.jobgem.dto.UserDTO;
import com.jobgem.entity.UserEntity;
import com.jobgem.exception.ResourceNotFoundException;
import com.jobgem.mapper.UserMapper;
import com.jobgem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDTO)
                .toList();
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public UserDTO createUser(UserDTO userDTO) {
        UserEntity user = userMapper.userDTOToUser(userDTO);
        UserEntity savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        UserEntity updatedUser = userRepository.save(user);
        return userMapper.userToUserDTO(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
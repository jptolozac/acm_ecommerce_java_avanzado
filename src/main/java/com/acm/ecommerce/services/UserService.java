package com.acm.ecommerce.services;

import com.acm.ecommerce.dto.UserDTO;
import com.acm.ecommerce.entities.UserEntity;
import com.acm.ecommerce.mapper.UserMapper;
import com.acm.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    public List<UserDTO.UserResponse> findAll() {
        return repository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public UserDTO.UserResponse create(UserDTO.UserRequest userRequest) {
        UserEntity user = userMapper.toEntity(userRequest);
        user = repository.save(user);
        return userMapper.toResponse(user);
    }

    public UserDTO.UserResponse update(Long id, UserDTO.UserRequest userRequest) {
        UserEntity existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id" + id));
        userMapper.updateEntityFromRequest(userRequest, existingUser);
        UserEntity updatedUser = repository.save(existingUser);
        return userMapper.toResponse(updatedUser);
    }

    public void delete(Long id) {repository.deleteById(id);}

    public Optional<UserEntity> findById(Long id) { return repository.findById(id); }
}

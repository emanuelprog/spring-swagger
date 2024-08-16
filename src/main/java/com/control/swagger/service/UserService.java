package com.control.swagger.service;

import com.control.swagger.dto.UserRequestDTO;
import com.control.swagger.dto.UserResponseDTO;
import com.control.swagger.exception.BadRequestException;
import com.control.swagger.exception.NotFoundException;
import com.control.swagger.model.User;
import com.control.swagger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDTO> buscarTodos() {
        return userRepository.findAll().stream().map(UserResponseDTO::fromUser).collect(Collectors.toList());
    }

    public UserResponseDTO criar(UserRequestDTO dto) {
        try {
            User user = userRepository.save(User.fromRequestDTO(dto));
            return UserResponseDTO.fromUser(user);
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível criar o usuário!");
        }
    }

    public UserResponseDTO buscarPorId(Long id) {
        Optional<User> userDB = userRepository.findById(id);

        if (!userDB.isPresent()) {
            throw new NotFoundException("Usuário não encontrado!");
        }

        return UserResponseDTO.fromUser(userDB.get());
    }

    public UserResponseDTO atualizar(Long id, UserRequestDTO dto) {
        Optional<User> userDB = userRepository.findById(id);

        if (!userDB.isPresent()) {
            throw new NotFoundException("Usuário não encontrado!");
        }

        try {
            User userAtualizado = userRepository.save(new User(id, dto.getNome(), dto.getIdade()));
            return UserResponseDTO.fromUser(userAtualizado);
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível atualizar o usuário!");
        }
    }

    public UserResponseDTO deletar(Long id) {
        try {
            userRepository.deleteById(id);
            return null;
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível deletar o usuário!");
        }
    }
}

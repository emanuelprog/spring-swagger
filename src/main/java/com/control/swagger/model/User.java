package com.control.swagger.model;

import com.control.swagger.dto.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private int idade;

    public User(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public static User fromRequestDTO(UserRequestDTO userRequestDTO) {
        return new User(
                userRequestDTO.getNome(),
                userRequestDTO.getIdade()
        );
    }
}

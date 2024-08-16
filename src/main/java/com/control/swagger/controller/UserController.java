package com.control.swagger.controller;

import com.control.swagger.dto.UserRequestDTO;
import com.control.swagger.service.UserService;
import com.control.swagger.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User Management", description = "Operações relacionadas ao gerenciamento de usuários")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de todos os usuários registrados")
    public ResponseEntity<Object> buscarTodosUsuarios() {
        return ResponseUtil.generateResponse("Lista carregada com sucesso!", HttpStatus.OK, userService.buscarTodos());
    }

    @PostMapping
    @Operation(summary = "Cria um novo usuário", description = "Adiciona um novo usuário ao sistema")
    public ResponseEntity<Object> criarUsuario(@RequestBody UserRequestDTO dto) {
        return ResponseUtil.generateResponse("Usuário criado com sucesso!", HttpStatus.CREATED, userService.criar(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário pelo ID", description = "Recupera as informações de um usuário existente no sistema com base no ID fornecido.")
    public ResponseEntity<Object> buscarUsuario(@PathVariable Long id) {
        return ResponseUtil.generateResponse("Usuário encontrado com sucesso!", HttpStatus.OK, userService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuário existente", description = "Modifica as informações de um usuário existente no sistema com base no ID fornecido.")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        return ResponseUtil.generateResponse("Usuário atualizado com sucesso!", HttpStatus.OK, userService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um usuário pelo ID", description = "Remove um usuário existente do sistema com base no ID fornecido.")
    public ResponseEntity<Object> deletarUsuario(@PathVariable Long id) {
        return ResponseUtil.generateResponse("Usuário deletado com sucesso!", HttpStatus.OK, userService.deletar(id));
    }
}
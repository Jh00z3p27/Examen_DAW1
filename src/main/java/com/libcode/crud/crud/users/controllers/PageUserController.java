package com.libcode.crud.crud.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; 
import com.libcode.crud.crud.users.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable; // Importa la clase PathVariable
import org.springframework.ui.Model; // Importa la clase Model
import com.libcode.crud.crud.users.entities.User; // Import the User class
import org.springframework.web.bind.annotation.PostMapping; // Importa la clase PostMapping

@Controller

@RequestMapping("/")

public class PageUserController {

    @Autowired
    private UserRepository UserRepository;

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", UserRepository.findAll());
        return "list-users";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("usuario", new User());
        return "form-user";
    }

    @PostMapping("/nuevo")
    public String guardarUser(@ModelAttribute User user) {
        UserRepository.save(user);
        
        return "redirect:/users";
    }
    
    @GetMapping("/editar/{id}")
    public String formularioEditarUser(Model model, @PathVariable Long id) {
        User usuario = UserRepository.findById(id).get();
        model.addAttribute("usuario", usuario);
        return "form-user";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUser(@PathVariable Long id) {
        UserRepository.delete(new User(id));
        return "redirect:/users";
    } 
}

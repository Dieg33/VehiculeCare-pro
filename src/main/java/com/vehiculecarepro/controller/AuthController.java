package com.vehiculecarepro.controller;

import com.vehiculecarepro.dto.RegisterRequest;
import com.vehiculecarepro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService users;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new RegisterRequest("", "", ""));
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute("user") RegisterRequest req,
                             RedirectAttributes ra) {
        if (!req.password().equals(req.confirmPassword())) {
            ra.addFlashAttribute("error", "Las contraseñas no coinciden");
            return "redirect:/register";
        }
        try {
            // Por defecto, rol USER
            users.register(req.username(), req.password(), "USER");
            ra.addFlashAttribute("success", "Cuenta creada. Ya puedes iniciar sesión.");
            return "redirect:/login";
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("error", ex.getMessage());
            return "redirect:/register";
        }
    }
}

package com.digis01.CAlvarezProgramacionNCapasOctubre2025.DemoController;

import com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML.LoginRequest;
import com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML.LoginResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/")  // ← Raíz de la aplicación
public class LoginController {
    
    public static final String urlBase = "http://localhost:8080";
    
    @GetMapping("/login")
    public String mostrarLogin() {
        return "Login";
    }
    
    @GetMapping  // ← Redirige desde la raíz al login
    public String inicio() {
        return "redirect:/login";
    }
    
    @PostMapping("/login")
    public String Login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        try {
            System.out.println("=== Intentando login ===");
            System.out.println("Username: " + username);
            
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsername(username);
            loginRequest.setPassword(password);
            
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<LoginRequest> body = new HttpEntity<>(loginRequest);
            
            ResponseEntity<LoginResponse> response = restTemplate.postForEntity(
                urlBase + "/api/login",
                body,
                LoginResponse.class
            );
            
            LoginResponse loginResponse = response.getBody();
            
            if (loginResponse != null && loginResponse.isCorrect()) {
                session.setAttribute("token", loginResponse.getToken());
                session.setAttribute("username", loginResponse.getUsername());
                session.setAttribute("idUsuario", loginResponse.getIdUsuario());
                
                System.out.println("✅ Login exitoso - Token guardado en sesión");
                
                redirectAttributes.addFlashAttribute("successMessage", "Bienvenido " + loginResponse.getUsername());
                return "redirect:/usuario";  // ← Redirige al index de usuarios
                
            } else {
                String mensaje = loginResponse != null ? loginResponse.getMensaje() : "Error desconocido";
                redirectAttributes.addFlashAttribute("errorMessage", mensaje);
                return "redirect:/login";
            }
            
        } catch (Exception ex) {
            System.out.println("❌ Error en login:");
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Error al conectar con el servidor: " + ex.getMessage());
            return "redirect:/login";
        }
    }
    
    @GetMapping("/logout")
    public String Logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("successMessage", "Sesión cerrada correctamente");
        return "redirect:/login";
    }
}
package com.digis01.CAlvarezProgramacionNCapasOctubre2025.DemoController;

import com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML.LoginRequest;
import com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML.LoginResponse;
import com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML.Result;
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
@RequestMapping("/")
public class LoginController {

    public static final String urlBase = "http://localhost:8080";

    @GetMapping("/login")
    public String mostrarLogin() {
        return "Login";
    }

    @GetMapping
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
                session.setAttribute("rol", loginResponse.getRol());

                System.out.println(loginResponse.getToken());

                redirectAttributes.addFlashAttribute("successMessage", loginResponse.getUsername());
                return "redirect:/usuario";

            } else {
                String mensaje = loginResponse != null ? loginResponse.getMensaje() : "Error desconocido";
                redirectAttributes.addFlashAttribute("errorMessage", mensaje);
                return "redirect:/login";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String Logout(HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            String username = (String) session.getAttribute("username");
            String token = (String) session.getAttribute("token");

            if (username != null) {
                System.out.println("Token " + (token != null ? "SI" : "No"));

            }

            session.invalidate();
            redirectAttributes.addFlashAttribute("successMessage", "Sesión cerrada correctamente");

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Errro al Cerrar Sesion");
        }

        return "redirect:/login";
    }
}

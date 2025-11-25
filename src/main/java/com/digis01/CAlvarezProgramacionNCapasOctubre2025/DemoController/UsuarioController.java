package com.digis01.CAlvarezProgramacionNCapasOctubre2025.DemoController;

import com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML.Colonia;
import com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML.Direccion;
import com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML.ErrorCarga;
import com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML.Result;
import com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML.Rol;
import com.digis01.CAlvarezProgramacionNCapasOctubre2025.ML.Usuario;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    public static final String urlBase = "http://localhost:8080";

    @GetMapping
    public String UsuarioIndex(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Result<List<Usuario>>> responseEntity = restTemplate.exchange(
                urlBase + "/api/usuario",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Result<List<Usuario>>>() {
        }
        );

        if (responseEntity.getStatusCode().value() == 200) {
            Result result = responseEntity.getBody();
            model.addAttribute("usuarios", result.object);

        } else {
            return "Error";
        }

        return "UsuarioIndex";
    }
    
    
    
    @GetMapping("/detalle/{idUsuario}")
public String DetalleUsuario(@PathVariable("idUsuario") int idUsuario, Model model) {
    try {
        System.out.println("=== Obteniendo usuario con ID: " + idUsuario + " ===");
        
        RestTemplate restTemplate = new RestTemplate();
        
        ResponseEntity<Result<Usuario>> responseEntity = restTemplate.exchange(
            urlBase + "/api/usuario/" + idUsuario,
            HttpMethod.GET,
            HttpEntity.EMPTY,
            new ParameterizedTypeReference<Result<Usuario>>() {}
        );
        
        System.out.println("Status code: " + responseEntity.getStatusCode().value());
        
        if (responseEntity.getStatusCode().value() == 200) {
            Result result = responseEntity.getBody();
            
            System.out.println("Result.correct: " + result.correct);
            
            if (result.correct && result.object != null) {
                Usuario usuario = (Usuario) result.object;
                
                System.out.println("Usuario encontrado: " + usuario.getNombre());
                System.out.println("Direcciones: " + (usuario.getDireccionesJPA() != null ? usuario.getDireccionesJPA().size() : "null"));
                
                model.addAttribute("usuario", usuario);
                
                // Cargar roles para el dropdown
                ResponseEntity<Result<List<Rol>>> rolesResponse = restTemplate.exchange(
                    urlBase + "/api/rol",
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<List<Rol>>>() {}
                );
                
                if (rolesResponse.getBody() != null && rolesResponse.getBody().objects != null) {
                    model.addAttribute("roles", rolesResponse.getBody().objects);
                }
                
                System.out.println("=== Retornando vista UsuarioDetail ===");
                return "UsuarioDetail";
                
            } else {
                System.out.println("=== Usuario no encontrado ===");
                model.addAttribute("errorMessage", "Usuario no encontrado");
                return "redirect:/usuario";
            }
            
        } else {
            System.out.println("=== Error en la respuesta de la API ===");
            model.addAttribute("errorMessage", "Error al consultar la API");
            return "redirect:/usuario";
        }
        
    } catch (Exception ex) {
        System.out.println("=== ERROR en DetalleUsuario ===");
        ex.printStackTrace();
        model.addAttribute("errorMessage", "Error al cargar el detalle: " + ex.getMessage());
        return "redirect:/usuario";
    }
}
    


@PostMapping("/update")
public String Update(@ModelAttribute("usuario") Usuario usuario,
        RedirectAttributes redirectAttributes){
    
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Usuario> body = new HttpEntity<>(usuario);
    
    ResponseEntity<Result<Usuario>> response = restTemplate.exchange(urlBase + "/api/usuario",
            HttpMethod.PUT, 
            body, 
            new ParameterizedTypeReference<Result<Usuario>>() {}
    );
    
    if (response.getBody().correct) {
        redirectAttributes.addFlashAttribute("successMessage", "Usuario Actualizado");
        return "redirect:/usuario";
    }else{
        redirectAttributes.addFlashAttribute("errorMessage", "No se actualizo");
        return "redirect:/usuario/detalle/" + usuario.getIdUsuario();
    }
    
}


    @GetMapping("formulario")
    public String UsuarioForm() {
        return "UsuarioForm";
    }

    @GetMapping("cargaMasiva")
    public String CargaMasiva() {
        return "CargaMasiva";
    }

    @GetMapping("/cargaMasiva/procesar")
    public String CargaMasivaProcesar(HttpSession session) {
        String path = session.getAttribute("archivoCargaMasiva").toString();
        session.removeAttribute("archivoCargaMasiva");

        // Inserción con carga masiva
        return "CargaMasiva";
    }
}
  

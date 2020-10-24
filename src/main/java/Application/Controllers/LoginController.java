package Application.Controllers;

import Application.Entities.Pet;
import Application.Entities.User;
import Application.JWT.JwtProvider;
import Application.Services.PetService;
import Application.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    PetService petService;

    @GetMapping("/login")
    public void getLogin(HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> headerNames = request.getHeaderNames();

        String username = request.getHeader(headerNames.nextElement());
        String password = request.getHeader(headerNames.nextElement());

        User user = userService.getUser(username);

        if (user != null &&
                user.getUsername().equals(username) &&
                user.getPassword().equals(password)) {
            String token = JwtProvider.generateToken(username, password);
            response.setHeader("Authorization", "Bearer " + token);
        }
    }

    @GetMapping("/me")
    public void getUser(HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> headerNames = request.getHeaderNames();
        String username = request.getHeader(headerNames.nextElement());
        String token = request.getHeader(headerNames.nextElement());

        User user = userService.getUser(username);

        if (user != null && JwtProvider.validateToken(token)) {
            response.setHeader("id", String.valueOf(user.getUserId()));
            response.setHeader("username", user.getUsername());
            response.setHeader("password", user.getPassword());
            response.setHeader("role", user.getRole());
            response.setHeader("first_name", user.getFirstName());
            response.setHeader("second_name", user.getSecondName());
            response.setHeader("last_name", user.getLastName());
            response.setHeader("bugs", user.getBugs().toString());

            Pet pet = petService.getPet(user.getUserId());
            if (pet != null) {
                response.setHeader("pet_id", String.valueOf(pet.getPetId()));
                response.setHeader("pet_name", pet.getPetName());
                response.setHeader("pet_rank", String.valueOf(pet.getPetRank()));
                response.setHeader("pet_hat", String.valueOf(pet.getHatId()));
                response.setHeader("pet_jacket", String.valueOf(pet.getJacketId()));
            }
        }
    }
}

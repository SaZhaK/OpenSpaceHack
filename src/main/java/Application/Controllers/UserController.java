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
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PetService petService;

    @GetMapping("/me")
    public void getUser(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getHeader("username");
        String token = request.getHeader("token");

        User user = userService.getUserByUsername(username);

        if (user != null && JwtProvider.validateToken(token)) {
            response.setHeader("id", String.valueOf(user.getUserId()));
            response.setHeader("username", user.getUsername());
            response.setHeader("password", user.getPassword());
            response.setHeader("role", user.getRole());
            response.setHeader("first_name", user.getFirstName());
            response.setHeader("second_name", user.getSecondName());
            response.setHeader("last_name", user.getLastName());
            response.setHeader("money", String.valueOf(user.getMoney()));
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

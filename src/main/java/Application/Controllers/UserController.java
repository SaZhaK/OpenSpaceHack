package Application.Controllers;

import Application.Entities.Pet;
import Application.Entities.User;
import Application.JWT.JwtProvider;
import Application.Services.ItemService;
import Application.Services.PetService;
import Application.Services.UserService;
import io.jsonwebtoken.Claims;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders="Authorization")
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PetService petService;
    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);

        Claims claims = JwtProvider.getAllClaimsFromToken(token);
        String username = claims.getSubject();
        User user = userService.getUserByUsername(username);
        JSONObject response = new JSONObject();

        if (user != null && JwtProvider.validateToken(token)) {
            response.put("id", String.valueOf(user.getUserId()));
            response.put("username", user.getUsername());
            response.put("password", user.getPassword());
            response.put("role", user.getRole());
            response.put("first_name", user.getFirstName());
            response.put("second_name", user.getSecondName());
            response.put("last_name", user.getLastName());
            response.put("money", String.valueOf(user.getMoney()));

            response.put("items", Collections.singleton(itemService.getUserItems(user)));

            Pet pet = petService.getUserPet(user);
            if (pet != null) {
                response.put("pet_id", String.valueOf(pet.getPetId()));
                response.put("pet_name", pet.getPetName());
                response.put("pet_rank", String.valueOf(pet.getPetRank()));
            }
        }

        return response.toString();
    }
}

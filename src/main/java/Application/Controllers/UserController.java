package Application.Controllers;

import Application.Entities.Pet;
import Application.Entities.User;
import Application.JWT.JwtProvider;
import Application.Services.PetService;
import Application.Services.UserService;
import io.jsonwebtoken.Claims;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PetService petService;

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

            Pet pet = petService.getPet(user.getUserId());
            if (pet != null) {
                response.put("pet_id", String.valueOf(pet.getPetId()));
                response.put("pet_name", pet.getPetName());
                response.put("pet_rank", String.valueOf(pet.getPetRank()));
                response.put("pet_hat", String.valueOf(pet.getHatId()));
                response.put("pet_jacket", String.valueOf(pet.getJacketId()));
            }
        }

        return response.toString();
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public String signup(HttpServletRequest request, HttpServletResponse response) {
        int userId = request.getIntHeader("userId");
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String role = request.getHeader("role");
        String firstName = request.getHeader("first_name");
        String secondName = request.getHeader("second_name");
        String lastName = request.getHeader("last_name");
        String petName = request.getHeader("pet_name");

        User user = new User(userId, username, password, role, firstName, secondName, lastName);
        Pet pet = new Pet(userId, petName, 1);

        String token = JwtProvider.generateToken(username, password);
        response.setHeader("Authorization", "Bearer " + token);

        JSONObject result = new JSONObject();
        result.put("id", String.valueOf(user.getUserId()));
        result.put("username", user.getUsername());
        result.put("password", user.getPassword());
        result.put("role", user.getRole());
        result.put("first_name", user.getFirstName());
        result.put("second_name", user.getSecondName());
        result.put("last_name", user.getLastName());
        result.put("money", String.valueOf(user.getMoney()));

        result.put("pet_id", String.valueOf(pet.getPetId()));
        result.put("pet_name", pet.getPetName());
        result.put("pet_rank", String.valueOf(pet.getPetRank()));
        result.put("pet_hat", String.valueOf(pet.getHatId()));
        result.put("pet_jacket", String.valueOf(pet.getJacketId()));

        return result.toString();
    }
}

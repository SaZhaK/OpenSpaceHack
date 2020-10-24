package Application.Controllers;

import Application.Entities.Pet;
import Application.Entities.User;
import Application.JWT.JwtProvider;
import Application.Services.PetService;
import Application.Services.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders="Authorization")
@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    PetService petService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public void getLogin(HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String postLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder data = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            data.append(line);
        }
        JSONObject jsonObject = new JSONObject(data.toString());

        String username = jsonObject.get("username").toString();
        String password = jsonObject.get("password").toString();

        User user = userService.getUserByUsername(username);

        String token = null;
        if (user != null &&
                user.getUsername().equals(username) &&
                user.getPassword().equals(password)) {
            token = JwtProvider.generateToken(username, password);
            response.setHeader("Authorization", "Bearer " + token);
        }

        JSONObject result = new JSONObject();

        if (user != null && JwtProvider.validateToken(token)) {
            result.put("id", String.valueOf(user.getUserId()));
            result.put("username", user.getUsername());
            result.put("password", user.getPassword());
            result.put("role", user.getRole());
            result.put("first_name", user.getFirstName());
            result.put("second_name", user.getSecondName());
            result.put("last_name", user.getLastName());
            result.put("money", String.valueOf(user.getMoney()));

            Pet pet = petService.getPet(user.getUserId());
            if (pet != null) {
                result.put("pet_id", String.valueOf(pet.getPetId()));
                result.put("pet_name", pet.getPetName());
                result.put("pet_rank", String.valueOf(pet.getPetRank()));
                result.put("pet_hat", String.valueOf(pet.getHatId()));
                result.put("pet_jacket", String.valueOf(pet.getJacketId()));
            }
        }

        return result.toString();
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public String signup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder data = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            data.append(line);
        }
        JSONObject jsonObject = new JSONObject(data.toString());

        int userId = Integer.parseInt(jsonObject.get("userId").toString());
        String username = jsonObject.get("userId").toString();
        String password = jsonObject.get("userId").toString();
        String role = jsonObject.get("userId").toString();
        String firstName = jsonObject.get("userId").toString();
        String secondName = jsonObject.get("userId").toString();
        String lastName = jsonObject.get("userId").toString();
        String petName = jsonObject.get("userId").toString();

        User user = new User(userId, username, password, role, firstName, secondName, lastName, 100);
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

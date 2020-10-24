package Application.Controllers;

import Application.Entities.Item;
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
import java.io.IOException;
import java.util.Collections;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
@Controller
public class SkinController {

    @Autowired
    UserService userService;
    @Autowired
    ItemService itemService;
    @Autowired
    PetService petService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String changeSkin(HttpServletRequest request) throws IOException {
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
                response.put("pet_face", pet.getFaceId());
                response.put("pet_body", pet.getBodyId());
                response.put("pet_arm", pet.getArmId());
                response.put("pet_mouth", pet.getMouthId());
                response.put("pet_leg", pet.getLegId());
                response.put("pet_hat", pet.getHatId());
                response.put("pet_backpack", pet.getBackpackId());
            }

            StringBuilder data = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                data.append(line);
            }
            JSONObject jsonObject = new JSONObject(data.toString());

            Item item = itemService.getItemById(Integer.parseInt(jsonObject.get("id").toString()));
            petService.wearItem(pet, item);
        }

        return response.toString();
    }
}

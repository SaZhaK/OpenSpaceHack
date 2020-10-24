package Application.Controllers;

import Application.Entities.Item;
import Application.Entities.User;
import Application.JWT.JwtProvider;
import Application.Services.ItemService;
import Application.Services.UserService;
import io.jsonwebtoken.Claims;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
@Controller
public class ShopController {

    @Autowired
    ItemService itemService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String buy(HttpServletRequest request) throws IOException {
        String token = request.getHeader("Authorization").substring(7);
        JSONObject result = new JSONObject();

        if (JwtProvider.validateToken(token)) {
            Claims claims = JwtProvider.getAllClaimsFromToken(token);
            String username = claims.getSubject();
            User currentUser = userService.getUserByUsername(username);

            StringBuilder data = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                data.append(line);
            }
            JSONObject jsonObject = new JSONObject(data.toString());
            int id = Integer.parseInt(jsonObject.get("id").toString());
            String type = jsonObject.get("type").toString();

            List<Item> items = itemService.getItemsByType(type);
            Item item = null;
            for (Item i : items) {
                if (i.getItemId() == id) {
                    item = i;
                    break;
                }
            }

            itemService.addNewItemToUser(currentUser, item);

            result.put("id", item.getItemId());
            result.put("type", item.getType());
            result.put("cost", item.getCost());
            result.put("name", item.getName());
        }

        return result.toString();
    }
}

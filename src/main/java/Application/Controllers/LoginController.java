package Application.Controllers;

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

        User user = userService.getUserByUsername(username);

        if (user != null &&
                user.getUsername().equals(username) &&
                user.getPassword().equals(password)) {
            String token = JwtProvider.generateToken(username, password);
            response.setHeader("Authorization", "Bearer " + token);
        }
    }
}

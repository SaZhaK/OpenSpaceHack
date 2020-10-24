package Application.Controllers;

import Application.Entities.Bug;
import Application.Entities.User;
import Application.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BugController {

    @Autowired
    UserService userService;

    @PostMapping("/report")
    public void reportBug(HttpServletRequest request, HttpServletResponse response) {
        Bug bug = new Bug();

        String testedSystem = request.getHeader("testedSystem");
        String username = request.getHeader("username");
        String bugName = request.getHeader("bugName");
        String betaVersion = request.getHeader("betaVersion");
        String OSModel = request.getHeader("OSModel");
        String description = request.getHeader("description");

        User user = userService.getUserByUsername(username);

        bug.setTestedSystem(testedSystem);
        bug.setUser(user);
        bug.setBugName(bugName);
        bug.setBetaVersion(betaVersion);
        bug.setOSModel(OSModel);
        bug.setDescription(description);

        //TODO
        //Date date;
        //LocalTime time;
        //byte[] screenshot;
    }
}

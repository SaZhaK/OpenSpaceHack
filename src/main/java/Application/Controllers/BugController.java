package Application.Controllers;

import Application.Entities.Bug;
import Application.Entities.User;
import Application.JWT.JwtProvider;
import Application.Services.BugService;
import Application.Services.UserService;
import io.jsonwebtoken.Claims;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
public class BugController {
    @Autowired
    UserService userService;
    @Autowired
    BugService bugService;

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    @ResponseBody
    public String reportBug(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        StringBuilder data = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            data.append(line);
        }
        JSONObject jsonObject = new JSONObject(data.toString());

        Bug bug = new Bug();
        String testedSystem = jsonObject.get("testedSystem").toString();
        String username = jsonObject.get("username").toString();
        String bugName = jsonObject.get("bug_name").toString();
        String betaVersion = jsonObject.get("beta_version").toString();
        String OSModel = jsonObject.get("os_model").toString();
        String description = jsonObject.get("description").toString();
        String date = jsonObject.get("date").toString();
        String time = jsonObject.get("time").toString();
        String screenshot = jsonObject.get("screenshot").toString();

        User user = userService.getUserByUsername(username);
        user.addMoney();
        userService.updateUserWallet(user);
        userService.addReportedBug2DataBase(bug);

        bug.setTestedSystem(testedSystem);
        bug.setUser(user);
        bug.setBugName(bugName);
        bug.setBetaVersion(betaVersion);
        bug.setOSModel(OSModel);
        bug.setDescription(description);
        bug.setDate(new Date(date));
        bug.setTime(LocalTime.parse(time));
        bug.setScreenshot(screenshot.getBytes());

        return jsonObject.toString();
    }


    @RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getBug(@PathVariable("id") String id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);

        JSONObject result = new JSONObject();

        if (JwtProvider.validateToken(token)) {
            Bug bug = bugService.getBugById(Integer.parseInt(id));

            result.put("bug_name", bug.getBugName());
            result.put("date", bug.getDate().toString());
            result.put("time", bug.getTime().toString());
            result.put("tested_system", bug.getTestedSystem());
            result.put("beta_version", bug.getBetaVersion());
            result.put("os_model", bug.getOSModel());
            result.put("description", bug.getDescription());
            result.put("screenshot", bug.getScreenshot());

            User owner = bug.getUser();
            result.put("username", owner.getUsername());
        }

        return result.toString();
    }

    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public String getBugList(HttpServletRequest request) throws SQLException {
        String token = request.getHeader("Authorization").substring(7);
        String user = request.getParameter("user");
        String verified = request.getParameter("verified");

        JSONObject result = new JSONObject();

        if (JwtProvider.validateToken(token)) {
            Claims claims = JwtProvider.getAllClaimsFromToken(token);
            String username = claims.getSubject();
            User currentUser = userService.getUserByUsername(username);

            Set<Integer> allBugs = bugService.getAllBugs();
            Set<Integer> bugs = new HashSet<>();

            for (Integer id : allBugs) {
                Bug bug = bugService.getBugById(id);
                if (user != null && verified != null && bug.getUser().getUserId() == currentUser.getUserId() && bug.getStatus() == 0) {
                    bugs.add(id);
                } else if (user == null && verified != null && bug.getStatus() == 0) {
                    bugs.add(id);
                } else if (user != null && verified == null && bug.getUser().getUserId() == currentUser.getUserId()) {
                    bugs.add(id);
                } else if (user == null && verified != null) {
                    bugs.add(id);
                }
            }

            result.put("bugs", bugs.toArray());
        }

        return result.toString();
    }
}

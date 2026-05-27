package pg222.fitness.controller;

import pg222.fitness.model.Admin;
import pg222.fitness.model.GymMember;
import pg222.fitness.model.User;
import pg222.fitness.service.MembershipService;
import pg222.fitness.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MembershipService membershipService;

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) throws IOException {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return user.getRole().equals("member") ? "redirect:/member/dashboard" : "redirect:/admin/dashboard";
        }
        return "redirect:/api/users/login?error";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String role) throws IOException {
        User user =  new GymMember(username, password, email) ;
        if (userService.registerUser(user)) {
            return "redirect:/api/users/login";
        }
        return "redirect:/api/users/register?error";
    }
    @GetMapping("/register-admin")
    public String showRegisterAdmin() {
        return "register-admin";
    }

    @PostMapping("/register-admin")
    public String registerAdmin(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String email) throws IOException {
        User admin = new Admin(username, password, email);
        if (userService.registerUser(admin)) {
            return "redirect:/api/users/login";
        }
        return "redirect:/api/users/register-admin?error";
    }


    @GetMapping("/update")
    public String showUpdate() {
        return "update-details";
    }

    @PostMapping("/update")
    public String updateDetails(@RequestParam String email, @RequestParam String password, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        userService.updateUser(user.getUsername(), email, password);
        return user.getRole().equals("member") ? "redirect:/member/dashboard" : "redirect:/admin/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) throws IOException {
        session.invalidate();
        membershipService.  checkAndExpireMemberships();
        return "redirect:/api/users/login";
    }
}
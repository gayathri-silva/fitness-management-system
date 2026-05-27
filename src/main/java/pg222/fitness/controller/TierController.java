package pg222.fitness.controller;

import pg222.fitness.model.Tier;
import pg222.fitness.model.User;
import pg222.fitness.service.RequestService;
import pg222.fitness.service.TierService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class TierController {
    @Autowired
    private TierService tierService;
    @Autowired
    private RequestService requestService;

    @GetMapping("/view-tiers")
    public String displayTiers(Model model) throws IOException {
        model.addAttribute("tiers", tierService.getAllTiers());
        return "display-tiers"; // The name of the Thymeleaf template (display-tiers.html)
    }

    @GetMapping("/upgrade")
    public String showTiers(Model model) throws IOException {
        model.addAttribute("tiers", tierService.getAllTiers());
        return "tier-upgrade";
    }

    @PostMapping("/upgrade")
    public String requestUpgrade(@RequestParam int tierId, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        requestService.createRequest(user.getUsername(), tierId);
        return "redirect:/member/dashboard";
    }

    @GetMapping("/edit-tiers")
    public String showEditTiers(Model model) throws IOException {
        model.addAttribute("tiers", tierService.getAllTiers());
        model.addAttribute("tier", new Tier(0, "", 0.0, ""));
        return "edit-tiers";
    }

    @PostMapping("/edit-tiers")
    public String editTier(@ModelAttribute Tier tier) throws IOException {
        tierService.updateTier(tier);
        return "redirect:/api/edit-tiers";
    }
}
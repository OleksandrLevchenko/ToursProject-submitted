package org.oleksandr.tours;
import java.util.List;

import org.oleksandr.tours.model.Attraction;
import org.oleksandr.tours.model.User;
import org.oleksandr.tours.repo.AttractionRepository;
import org.oleksandr.tours.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {
	@Autowired
	private UserRepository userRepo;

    @Autowired
    private AttractionRepository atRepo;


    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
    	model.addAttribute("user", new User());
    	return "singup_form";
    }
    
    @PostMapping("/process_register")
    public String processRegistration(User user) {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	String encodedPassword = encoder.encode(user.getPassword());
    	user.setPassword(encodedPassword);
    	
    	userRepo.save(user);

    	return "register_success";
    }
    
    @GetMapping("/list_users")
    public String ViewUsersList(Model model) {
    	List<User> listUsers = userRepo.findAll();
    	model.addAttribute("listUsers", listUsers);
    	return "users";
    }

    @GetMapping("/manage_attractions")
    public String manageAttractions(Model model) {
        List<Attraction> listAttractions = atRepo.findAll();
        model.addAttribute("listAttractions", listAttractions);
        return "manage_attractions";
    }

    @GetMapping("/edit_attraction")
    public String editAttraction(@RequestParam(name = "id") String id, Model model) {
        Attraction attraction = atRepo.findById(id);
        model.addAttribute("editAttraction", attraction);
        return "edit_attraction";
    }
}
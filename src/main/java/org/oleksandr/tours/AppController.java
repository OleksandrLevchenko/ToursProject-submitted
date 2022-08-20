package org.oleksandr.tours;

import org.oleksandr.tours.model.Attraction;
import org.oleksandr.tours.model.TourSchedule;
import org.oleksandr.tours.model.User;
import org.oleksandr.tours.repo.AttractionRepository;
import org.oleksandr.tours.repo.TourRepository;
import org.oleksandr.tours.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {
	@Autowired
	private UserRepository userRepo;

    @Autowired
    private AttractionRepository atRepo;

    @Autowired
    private TourRepository tourRepo;

    @GetMapping("")
    public String viewHomePage() {return "index";}

//    U S E R
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

//    A T T R A C T I O N S
    @GetMapping("/manage_attractions")
    public String manageAttractions(Model model) {
        List<Attraction> listAttractions = atRepo.findAll();
        model.addAttribute("listAttractions", listAttractions);
        return "manage_attractions";
    }

    @PostMapping("/save_attraction")
    public String saveAttraction(@ModelAttribute("attraction") Attraction attraction) {
        atRepo.save(attraction);
        return "redirect:/manage_attractions";
    }

    @GetMapping("/edit_attraction/{id}")
    public String editAttraction(@PathVariable(value = "id") long id, Model model) {
        if (id == 0) {
            Attraction attraction = new Attraction();
            model.addAttribute("editAttraction", attraction);
            return "add_attraction";
        }
        Attraction attraction = atRepo.getReferenceById(id);
        model.addAttribute("editAttraction", attraction);
        return "edit_attraction";
    }

    @GetMapping("/delete_attraction/{id}")
    public String deleteAttraction(@PathVariable(value = "id") long id, Model model) {
        Attraction attraction = atRepo.getReferenceById(id);
        //List<TourSchedule> tours = atRepo.findBy();
        atRepo.delete(attraction);
        return "redirect:/manage_attractions";
    }

//    T O U R   S C H E D U L E
    @GetMapping("/manage_tours")
    public String manageTours(Model model) {
        List<TourSchedule> listTours = tourRepo.findAll();
        model.addAttribute("listTours", listTours);
        return "manage_tours";
    }

    @PostMapping("/save_tour")
    public String saveTour(@ModelAttribute("tour") TourSchedule tour) {
        tourRepo.save(tour);
        return "redirect:/manage_tours";
    }

    @GetMapping("/edit_tour/{id}")
    public String editTour(@PathVariable(value = "id") long id, Model model) {
        List<Attraction> listAttractions = atRepo.findAll();
        model.addAttribute("attractionOptions", listAttractions);
        if (id == 0) {
            TourSchedule tour = new TourSchedule();
            model.addAttribute("editTour", tour);
            return "add_tour";
        }
        TourSchedule tour = tourRepo.getReferenceById(id);
        model.addAttribute("editTour", tour);
        return "edit_tour";
    }

    @GetMapping("/delete_tour/{id}")
    public String deleteTour(@PathVariable(value = "id") long id, Model model) {
        TourSchedule tour = tourRepo.getReferenceById(id);
        tourRepo.delete(tour);
        return "redirect:/manage_tours";
    }
}
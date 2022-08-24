package org.oleksandr.tours;

import org.oleksandr.tours.model.*;
import org.oleksandr.tours.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private ReservationRepository resRepo;

    @Autowired
    private RoleRepository roleRepo;

    @GetMapping("")
    public String viewHomePage() {return "index";}

//    U S E R
////////////////////
//    @GetMapping("/login")
//    public String showLogInForm(Model model) {
//
//        return "login";
//    }
////////////////////

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
    
    @GetMapping("/admin/list_users")
    public String ViewUsersList(Model model) {
    	List<User> listUsers = userRepo.findAll();
    	model.addAttribute("listUsers", listUsers);
//////////
        List<Role> listRoles = roleRepo.findAll();
        model.addAttribute("listRoles", listRoles);
//////////
    	return "/admin/users";
    }

//    A T T R A C T I O N S
    @GetMapping("/admin/manage_attractions")
    public String manageAttractions(Model model) {
        List<Attraction> listAttractions = atRepo.findAll();
        model.addAttribute("listAttractions", listAttractions);
        return "/admin/manage_attractions";
    }

    @PostMapping("/admin/save_attraction")
    public String saveAttraction(@ModelAttribute("attraction") Attraction attraction) {
        atRepo.save(attraction);
        return "redirect:/admin/manage_attractions";
    }

    @GetMapping("/admin/edit_attraction/{id}")
    public String editAttraction(@PathVariable(value = "id") long id, Model model) {
        if (id == 0) {
            Attraction attraction = new Attraction();
            model.addAttribute("editAttraction", attraction);
            return "/admin/add_attraction";
        }
        Attraction attraction = atRepo.getReferenceById(id);
        model.addAttribute("editAttraction", attraction);
        return "/admin/edit_attraction";
    }

    @GetMapping("/admin/delete_attraction/{id}")
    public String deleteAttraction(@PathVariable(value = "id") long id, Model model) {
        Attraction attraction = atRepo.getReferenceById(id);
        //List<TourSchedule> tours = atRepo.findBy();
        atRepo.delete(attraction);
        return "redirect:/admin/manage_attractions";
    }

//    T O U R   S C H E D U L E
    @GetMapping("/admin/manage_tours")
    public String manageTours(Model model) {
        List<TourSchedule> listTours = tourRepo.findAll();
        model.addAttribute("listTours", listTours);
        return "/admin/manage_tours";
    }

    @PostMapping("/admin/save_tour")
    public String saveTour(@ModelAttribute("tour") TourSchedule tour) {
        tourRepo.save(tour);
        return "redirect:/admin/manage_tours";
    }

    @GetMapping("admin/edit_tour/{id}")
    public String editTour(@PathVariable(value = "id") long id, Model model) {
        List<Attraction> listAttractions = atRepo.findAll();
        model.addAttribute("attractionOptions", listAttractions);
        if (id == 0) {
            TourSchedule tour = new TourSchedule();
            model.addAttribute("editTour", tour);
            return "/admin/add_tour";
        }
        TourSchedule tour = tourRepo.getReferenceById(id);
        model.addAttribute("editTour", tour);
        return "/admin/edit_tour";
    }

    @GetMapping("/admin/delete_tour/{id}")
    public String deleteTour(@PathVariable(value = "id") long id, Model model) {
        TourSchedule tour = tourRepo.getReferenceById(id);
        tourRepo.delete(tour);
        return "redirect:/admin/manage_tours";
    }

//    R E S E R V A T I O N S
    @GetMapping("/user/manage_reservations")
    public String manageReservation(Model model) {
        List<TourSchedule> listTours = tourRepo.findAll();
        model.addAttribute("listTours", listTours);
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        List<Reservation> listReservations = resRepo.findByUser(user);
        model.addAttribute("listReservations", listReservations);
        return "/user/manage_reservations";
    }

    @GetMapping("/user/save_reservation/{id}")
    public String saveReservation(@PathVariable(value = "id") long id) {
        Reservation reservation = new Reservation();
        TourSchedule tour = tourRepo.getReferenceById(id);
        reservation.setTourSchedule(tour);
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        reservation.setUser(user);
        boolean foundExisting = false;
        List<Reservation> listReservations = resRepo.findByUser(user);
        for (Reservation res : listReservations) {
            if (res.getUser().getId() == user.getId() && res.getTourSchedule().getId() == tour.getId()) {
                foundExisting = true;
                break;
            }
        }
        if (foundExisting == false) resRepo.save(reservation);
        return "redirect:/user/manage_reservations";
    }

    @GetMapping("/user/delete_reservation/{id}")
    public String deleteReservation(@PathVariable(value = "id") long id) {
        Reservation reservation = resRepo.getReferenceById(id);
        resRepo.delete(reservation);
        return "redirect:/user/manage_reservations";
    }
}
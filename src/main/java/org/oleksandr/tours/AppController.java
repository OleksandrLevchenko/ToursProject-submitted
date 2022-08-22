package org.oleksandr.tours;

import org.oleksandr.tours.model.Attraction;
import org.oleksandr.tours.model.Reservation;
import org.oleksandr.tours.model.TourSchedule;
import org.oleksandr.tours.model.User;
import org.oleksandr.tours.repo.AttractionRepository;
import org.oleksandr.tours.repo.ReservationRepository;
import org.oleksandr.tours.repo.TourRepository;
import org.oleksandr.tours.repo.UserRepository;
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

//    R E S E R V A T I O N S
    @GetMapping("/user/manage_reservations")
    public String manageReservation(Model model) {
        List<TourSchedule> listTours = tourRepo.findAll();
        model.addAttribute("listTours", listTours);
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        List<Reservation> listReservations = resRepo.findByUser(user);
        model.addAttribute("listReservations", listReservations);
        return "user/manage_reservations";
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

//    @GetMapping("/edit_reservation/{id}")
//    public String editReservation(@PathVariable(value = "id") long id, Model model) {
//        List<Reservation> listReservations = resRepo.findAll();
//        model.addAttribute("reservationOptions", listReservations);
//        if (id == 0) {
//            Reservation reservation = new Reservation();
//            model.addAttribute("editReservation", reservation);
//            return "user/add_reservation";
//        }
//        Reservation reservation = resRepo.getReferenceById(id);
//        model.addAttribute("editReservation", reservation);
//        return "user/edit_reservation";
//    }

    @GetMapping("/user/delete_reservation/{id}")
    public String deleteReservation(@PathVariable(value = "id") long id) {
        Reservation reservation = resRepo.getReferenceById(id);
        resRepo.delete(reservation);
        return "redirect:/user/manage_reservations";
    }
}
package uz.pdp.task1_lesson10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1_lesson10.entity.Hotel;
import uz.pdp.task1_lesson10.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    // Create
    @PostMapping
    public String add(@RequestBody Hotel hotel) {
        Hotel hotel1 = new Hotel();
        hotel1.setName(hotel.getName());
        hotelRepository.save(hotel1);
        return "Hotel successfully added !";
    }

    // Read
    @GetMapping
    public List<Hotel> get() {
        return hotelRepository.findAll();
    }

    // Update
    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody Hotel hotel) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()) return id + " hotel not found !";
        Hotel hotel1 = optionalHotel.get();
        hotel1.setName(hotel.getName());
        hotelRepository.save(hotel1);
        return "Hotel successfully edited !";
    }

    // Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()) return id + " hotel not found !";
        hotelRepository.deleteById(id);
        return id + " hotel successfully deleted !";
    }
}

package uz.pdp.task1_lesson10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1_lesson10.entity.Hotel;
import uz.pdp.task1_lesson10.entity.Room;
import uz.pdp.task1_lesson10.payload.RoomDto;
import uz.pdp.task1_lesson10.repository.HotelRepository;
import uz.pdp.task1_lesson10.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    // Create
    @PostMapping
    public String add(@RequestBody RoomDto roomDto) {
        Room room = new Room();
        room.setFloor(roomDto.getFloor());
        room.setNumber(roomDto.getNumber());
        room.setSize(roomDto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()) return roomDto.getHotelId() + " hotel not found !";
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "Room successfully added !";
    }

    // Read all
    @GetMapping
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    // Read by hotel id
    @GetMapping("/{id}")
    public Page<Room> getByHotel(@PathVariable Integer id, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return roomRepository.findAllByHotelId(id, pageable);
    }

    // Update
    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()) return id + " room not found !";
        Room room = optionalRoom.get();
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        room.setNumber(roomDto.getNumber());
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()) return roomDto.getHotelId() + " hotel not found !";
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "Room successfully edited !";
    }

    // Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()) return id + " room not found !";
        roomRepository.deleteById(id);
        return id + " room successfully deleted !";
    }
}

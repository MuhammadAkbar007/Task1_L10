package uz.pdp.task1_lesson10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task1_lesson10.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}

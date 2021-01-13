package by.nagula.repository;

import by.nagula.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitiesRepository extends JpaRepository<City, Long> {
    Optional<City> findByCity(String city);

}

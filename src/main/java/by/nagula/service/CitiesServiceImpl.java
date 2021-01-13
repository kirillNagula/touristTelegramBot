package by.nagula.service;

import by.nagula.entity.City;
import by.nagula.repository.CitiesRepository;
import by.nagula.service.exception.CityAlreadyExistException;
import by.nagula.service.exception.CityNotFoundException;
import by.nagula.service.exception.WrongIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesServiceImpl implements CitiesService {
    private final CitiesRepository citiesRepository;

    public CitiesServiceImpl(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    @Override
    public void addCity(City city) {
        if (citiesRepository.findByCity(city.getCity()).isPresent()){
            throw new CityAlreadyExistException("This city is already exist in Db");
        }
        String cityName = city.getCity().toLowerCase();
        city.setCity(cityName);
        citiesRepository.save(city);
    }

    @Override
    public List<City> showAllCities() {
        return citiesRepository.findAll();
    }

    @Override
    public City showCityById(long id) {
        if (id > 0 && citiesRepository.findById(id).isPresent()){
            return citiesRepository.findById(id).get();
        } else {
            throw new CityNotFoundException("No city with this Id");
        }

    }

    @Override
    public City showCityByName(String city) {
        if (citiesRepository.findByCity(city.toLowerCase()).isPresent()){
            return citiesRepository.findByCity(city.toLowerCase()).get();
        } else {
            throw new CityNotFoundException("Нет информации об этом городе, или неверное название");
        }
    }

    @Override
    public void removeById(long id) {
        if (id > 0){
            citiesRepository.deleteById(id);
        } else {
            throw new WrongIdException();
        }
    }

    @Override
    public void editCity(City cityNew) {
        if (citiesRepository.findByCity(cityNew.getCity()).isPresent()){
            City cityByName = citiesRepository.findByCity(cityNew.getCity()).get();
            cityByName.setDescription(cityNew.getDescription());
            citiesRepository.save(cityByName);
        } else {
            throw new CityNotFoundException("No city in Db");
        }
    }
}

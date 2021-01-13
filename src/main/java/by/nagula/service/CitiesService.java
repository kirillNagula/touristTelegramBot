package by.nagula.service;

import by.nagula.entity.City;

import java.util.List;

public interface CitiesService {

    void addCity(City city);
    List<City> showAllCities();
    City showCityById(long id);
    City showCityByName(String city);
    void removeById(long id);
    void editCity(City city);

}

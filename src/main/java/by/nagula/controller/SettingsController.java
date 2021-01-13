package by.nagula.controller;

import by.nagula.entity.City;
import by.nagula.service.CitiesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/home")
public class SettingsController {
    private final CitiesService citiesService;

    public SettingsController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @GetMapping(path = "/all")
    public List<City> cityList(){
        return citiesService.showAllCities();
    }

    @PostMapping(path = "/save")
    public City save(@RequestBody City city){
        citiesService.addCity(city);
        return city;
    }

    @GetMapping(path = "/get/{city}")
    public City getCity(@PathVariable String city){
        return citiesService.showCityByName(city);
    }

    @GetMapping(path = "/delete/{id}")
    public void delete(@PathVariable long id){
        citiesService.removeById(id);
    }

    @PostMapping(path = "/edit")
    public void edit(@RequestBody City city){
        citiesService.editCity(city);
    }

}

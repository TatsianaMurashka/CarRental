package com.htp.controller;

import com.htp.domain.Location;
import com.htp.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/locations")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public String findAll(ModelMap modelMap) {
        modelMap.addAttribute("locations", locationService.findAll());
        return "location/locations";
    }

    @GetMapping("/find/{id}")
    public String findById(@PathVariable("id") Long locationId, ModelMap modelMap) {
        modelMap.addAttribute("location", locationService.findOne(locationId));
        return "location/location";
    }

    @GetMapping("/save/{city}")
    public String saveLocation(@PathVariable("city") String city, ModelMap modelMap) {
        Location location = new Location("", city, "", "", "");
        modelMap.addAttribute("location", locationService.save(location));
        return "location/locations";
    }

    @GetMapping("/update")
    public String updateLocation(@RequestParam("id") Long locationId, @RequestParam("city") String city, ModelMap modelMap) {
        Location location = locationService.findOne(locationId);
        location.setCity(city);
        locationService.update(location);
        return "location/locations";
    }

    @GetMapping("/delete/{id}")
    public String deleteLocation(@PathVariable("id") Long locationId, ModelMap modelMap) {
        modelMap.addAttribute("location", locationService.delete(locationId));
        return "location/locations";
    }

}

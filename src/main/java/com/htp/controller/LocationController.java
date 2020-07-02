package com.htp.controller;

import com.htp.controller.request.LocationCreateRequest;
import com.htp.controller.request.LocationUpdateRequest;
import com.htp.domain.Location;
import com.htp.service.LocationService;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private static final Logger log = Logger.getLogger(LocationController.class);

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @ApiOperation(value = "Get all locations")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading locations"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @GetMapping
    public ResponseEntity<List<Location>> findAll() {
        return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Finding location by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading location"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Location database identifier", example = "1", required = true, dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}")
    public Location findById(@PathVariable("id")Long locationId) {
        return locationService.findOne(locationId);
    }

    @ApiOperation(value = "Search locations by city")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful loading location"),
            @ApiResponse(code = 500, message = "Server error, something wrong"),
            @ApiResponse(code = 502, message = "Wrong location id")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "query", value = "Search query - free text", example = "Minsk", required = true, dataType = "string", paramType = "query")
    })
    @GetMapping("/search")
    public List<Location> searchLocation(@RequestParam("query") String query) {
        return locationService.search(query);
    }

    @ApiOperation(value = "Create location")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful creation location"),
            @ApiResponse(code = 422, message = "Failed location creation properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    public Location create(@Valid @RequestBody LocationCreateRequest createRequest) {
        Location location = new Location();
        location.setCountry(createRequest.getCountry());
        location.setCity(createRequest.getCity());
        location.setStreet(createRequest.getStreet());
        location.setHouse(createRequest.getHouse());
        location.setApartment(createRequest.getApartment());

        return locationService.save(location);
    }

    @ApiOperation(value = "Update location")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful update location"),
            @ApiResponse(code = 422, message = "Failed location update properties validation"),
            @ApiResponse(code = 500, message = "Server error, something wrong"),
            @ApiResponse(code = 502, message = "Wrong location id")
    })
    @PutMapping
    public Location update(@Valid @RequestBody LocationUpdateRequest updateRequest) {
        Location locationToUpdate = locationService.findOne(updateRequest.getId());
        locationToUpdate.setCountry(updateRequest.getCountry());
        locationToUpdate.setCity(updateRequest.getCity());
        locationToUpdate.setStreet(updateRequest.getStreet());
        locationToUpdate.setHouse(updateRequest.getHouse());
        locationToUpdate.setApartment(updateRequest.getApartment());

        return locationService.update(locationToUpdate);
    }

    @ApiOperation(value = "Mark location as deleted")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful delete location"),
            @ApiResponse(code = 500, message = "Server error, something wrong"),
            @ApiResponse(code = 502, message = "Wrong location id")
    })
    @PutMapping("/{id}")
    public Location deleteLocation(@PathVariable("id") Long locationId) {
        Location locationToDelete = locationService.findOne(locationId);
        locationService.delete(locationToDelete.getId());
        return locationService.findOne(locationId);
    }

//    @GetMapping
//    public String findAll(ModelMap modelMap) {
//        modelMap.addAttribute("locations", locationService.findAll());
//        return "location/locations";
//    }
//
//    @GetMapping("/find/{id}")
//    public String findById(@PathVariable("id") Long locationId, ModelMap modelMap) {
//        modelMap.addAttribute("location", locationService.findOne(locationId));
//        return "location/location";
//    }
//
//    @GetMapping("/save/{city}")
//    public String saveLocation(@PathVariable("city") String city, ModelMap modelMap) {
//        Location location = new Location("", city, "", "", "");
//        modelMap.addAttribute("location", locationService.save(location));
//        return "location/locations";
//    }
//
//    @GetMapping("/update")
//    public String updateLocation(@RequestParam("id") Long locationId, @RequestParam("city") String city, ModelMap modelMap) {
//        Location location = locationService.findOne(locationId);
//        location.setCity(city);
//        locationService.update(location);
//        return "location/locations";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteLocation(@PathVariable("id") Long locationId, ModelMap modelMap) {
//        modelMap.addAttribute("location", locationService.delete(locationId));
//        return "location/locations";
//    }

}

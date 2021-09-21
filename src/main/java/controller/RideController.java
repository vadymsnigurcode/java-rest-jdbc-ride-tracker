package controller;

import model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.RideService;

import java.util.List;

@Controller
public class RideController {

    @Autowired
    private RideService rideService;

    @RequestMapping(
            value = "/rides",
            method = RequestMethod.GET)

    public @ResponseBody List<Ride> getRides() {
        return rideService.getRides();
    }

    @RequestMapping(
            value = "/ride",
            method = RequestMethod.PUT)
    public @ResponseBody Ride createRide(@RequestBody Ride ride) {
        return rideService.createRide(ride);
    }

    @RequestMapping(
            value = "/ride",
            method = RequestMethod.POST)
    public @ResponseBody Ride createRideByPost(@RequestBody Ride ride) {
        return rideService.createRide(ride);
    }

    @RequestMapping(
            value = "/ride/{id}",
            method = RequestMethod.GET)
    public @ResponseBody Ride getRideById(
            @PathVariable(value = "id") Integer id) {
        return rideService.getRide(id);
    }

    @RequestMapping(
            value = "/ride/{id}",
            method = RequestMethod.PUT)
    public @ResponseBody Ride updateRideById(
            @RequestBody Ride ride) {
        return rideService.updateRide(ride);
    }
}

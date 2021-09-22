package controller;

import model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repository.util.ServiceError;
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

    @RequestMapping(
            value = "/batch",
            method = RequestMethod.GET)
    public @ResponseBody Object batch() {
        rideService.batch();
        return null;
    }

    @RequestMapping(
            value = "/ride/{id}",
            method = RequestMethod.DELETE)
    public @ResponseBody Object  deleteRideById(
            @PathVariable(value = "id") Integer id) {
        rideService.deleteRide(id);
        return null;
    }

    @RequestMapping(
            value = "/test",
            method = RequestMethod.GET)
    public @ResponseBody Object testException() {
        throw new DataAccessException("Testing exception thrown") {

        };
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ServiceError> handleException(RuntimeException ex) {
        ServiceError error = new ServiceError(HttpStatus.OK.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
}

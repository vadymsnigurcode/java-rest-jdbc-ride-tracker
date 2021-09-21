package service;

import model.Ride;

import java.util.List;

public interface RideService {
    List<Ride> getRides();

    Ride createRide(Ride ride);
}

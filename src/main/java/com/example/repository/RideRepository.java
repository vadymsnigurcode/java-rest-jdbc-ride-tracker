package com.example.repository;

import com.example.model.Ride;

import java.util.List;

public interface RideRepository {
    List<Ride> getRides();

    Ride createRide(Ride ride);

    Ride getRide(Integer id);

    Ride updateRide(Ride ride);

    void updateRides(List<Object[]> pairs);

    void deleteRide(Integer id);
}

package service;

import model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RideRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RideServiceImpl implements RideService {

    @Autowired
    RideRepository rideRepository;

    @Override
    public List<Ride> getRides() {
        return rideRepository.getRides();
    }

    @Override
    public Ride createRide(Ride ride) {
        return rideRepository.createRide(ride);
    }

    @Override
    public Ride getRide(Integer id) {
        return rideRepository.getRide(id);
    }

    @Override
    public Ride updateRide(Ride ride) {
        return rideRepository.updateRide(ride);
    }

    @Override
    public void batch() {
        List<Ride> rides = rideRepository.getRides();
        List<Object[]> pairs = new ArrayList<>();
        for (Ride ride:rides) {
            Object[] tmp = {new Date(), ride.getId()};
            pairs.add(tmp);

        }
        rideRepository.updateRides(pairs);
    }

    @Override
    public void deleteRide(Integer id) {
        rideRepository.deleteRide(id);
    }
}

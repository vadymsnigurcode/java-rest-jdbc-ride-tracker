import model.Ride;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class RestControllerTest {
    @Test(timeout=3000)
    public void testGetRides() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
                "http://localhost:8080/java_rest_jdbc_ride_tracker_war/rides",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ride>>(){}
        );
        List<Ride> rides = ridesResponse.getBody();
        for (Ride ride:rides) {
            System.out.println("Ride name: "+ ride.getName());
        }
    }

    @Test(timeout = 3000)
    public void testCreateRide() {
        RestTemplate restTemplate = new RestTemplate();
        Ride ride = new Ride();
        ride.setName("Bobsled Trail 4");
        ride.setDuration(33);

        restTemplate.put("http://localhost:8080/java_rest_jdbc_ride_tracker_war/ride", ride);
    }

    @Test(timeout = 3000)
    public void testCreateRideUsingPost() {
        RestTemplate restTemplate = new RestTemplate();
        Ride ride = new Ride();
        ride.setName("Yellow Fork Trail");
        ride.setDuration(33);

        ride = restTemplate.postForObject(
                "http://localhost:8080/java_rest_jdbc_ride_tracker_war/ride",
                ride,
                Ride.class);
        System.out.println("Ride: " + ride);
    }

    @Test(timeout=3000)
    public void testGetRidesById() {
        RestTemplate restTemplate = new RestTemplate();
        Ride ride = restTemplate.getForObject(
                "http://localhost:8080/java_rest_jdbc_ride_tracker_war/ride/3",
                Ride.class
        );
        System.out.println("Ride name: "+ ride.getName());
    }

    @Test(timeout=3000)
    public void testUpdateRideById() {
        RestTemplate restTemplate = new RestTemplate();
        Ride ride = restTemplate.getForObject(
                "http://localhost:8080/java_rest_jdbc_ride_tracker_war/ride/3",
                Ride.class
        );
        ride.setDuration(ride.getDuration() + 1);
        restTemplate.put("http://localhost:8080/java_rest_jdbc_ride_tracker_war/ride",
                ride);
        System.out.println("Ride name: "+ ride.getName() + ", ride.duration = " + ride.getDuration());
    }
}

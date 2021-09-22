import com.example.model.Ride;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import com.example.util.ServiceError;

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
        ride.setName("Bobsled Trail 5");
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

    @Test(timeout=3000)
    public void testBatchUpdate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(
                "http://localhost:8080/java_rest_jdbc_ride_tracker_war/batch",
                Object.class
        );
    }

    @Test(timeout=3000)
    public void testDelete() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(
                "http://localhost:8080/java_rest_jdbc_ride_tracker_war/ride/10");
    }

    @Test(timeout=3000)
    public void testException() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(
                "http://localhost:8080/java_rest_jdbc_ride_tracker_war/test",Ride.class);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ServiceError> handle(RuntimeException ex) {
        ServiceError error = new ServiceError(
                HttpStatus.OK.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error,HttpStatus.OK);
    }
}

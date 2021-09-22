package com.example.repository;

import com.example.model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.example.repository.util.RideRowMapperImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Ride> getRides() {
        // variant 1
//        Ride ride = new Ride();
//        ride.setName("Corner Canyon");
//        ride.setDuration(120);
//        List<Ride> rides = new ArrayList<>();
//        rides.add(ride);

        // variant 2
        List<Ride> rides = jdbcTemplate
                .query(
                        "select * from ride",
                        new RideRowMapperImpl());
        return rides;
    }

    @Override
    public Ride createRide(Ride ride) {
        // Variant 1 of the insertion
//        jdbcTemplate.update("insert into ride (name,duration) values(?,?)",ride.getName(),ride.getDuration());

        // Variant 2 of the insertion
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        List<String> columns = new ArrayList<>();
        columns.add("name");
        columns.add("duration");
        insert.setTableName("ride");
        insert.setColumnNames(columns);
        Map<String, Object> data = new HashMap<>();
        data.put("name", ride.getName());
        data.put("duration", ride.getDuration());
        insert.setGeneratedKeyName("id");
        Number key = insert.executeAndReturnKey(data);
        System.out.println(key);
        return null;

        //variant 3
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement ps = connection
//                        .prepareStatement(
//                                "insert into ride (name,duration) values(?,?)",
//                        new String[] {"id"});
//                ps.setString(1, ride.getName());
//                ps.setInt(2, ride.getDuration());
//                return ps;
//            }
//        },keyHolder);
//        Number id = keyHolder.getKey();
//        return getRide(id.intValue());
    }

    @Override
    public Ride getRide(Integer id) {
        Ride ride = jdbcTemplate
                .queryForObject(
                        "select * from ride where id = ?",
                        new RideRowMapperImpl(),
                        id);
        return ride;
    }

    @Override
    public Ride updateRide(Ride ride) {
        jdbcTemplate.update("update ride set name = ?, duration = ?, where id = ?",
                ride.getName(), ride.getDuration(), ride.getId());
        return ride;
    }

    @Override
    public void updateRides(List<Object[]> pairs) {
        jdbcTemplate.batchUpdate(
                "update ride set ride_date = ? where id = ?",
                pairs);

    }

    @Override
    public void deleteRide(Integer id) {
        //jdbcTemplate.update("delete from ride where id = ?", id);
        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id",id);
        namedTemplate.update("delete from ride where id = :id", paramMap);
    }
}

package scm.serenityproject.eventservice.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import scm.serenityproject.eventservice.model.Event;
import scm.serenityproject.eventservice.repository.EventRepository;
import scm.serenityproject.eventservice.utills.mappers.EventMapper;

import java.sql.PreparedStatement;
import java.util.UUID;

public class EventDAO implements EventRepository {

    private JdbcTemplate jdbcTemplate;

    private static final String CREATE_EVENT_QUERY = """
            insert into events.events (event_id, finish, start, status, title, description, address) values(?,?,?,?,?,?,?)
            """;
    private static final String UPDATE_EVENT_QUERY = """
            update events.events set (event_id, finish, start, status, title, description, address) values(?,?,?,?,?,?,?)
            """;
    private static final String DELETE_EVENT_QUERY = """
            delete from events.events where event_id = ?
            """;
    private static final String FIND_EVENT_BY_ID_QUERY = """
            select * from events.events where event_id = ?
            """;
    /*
    private UUID eventId;
    private Timestamp finish;
    private Timestamp start;
    private EventStatus status;
    private String title;
    private String description;
     */
    @Override
    public UUID createEvent(Event event) {
        updateQuery(event, CREATE_EVENT_QUERY);
        return event.getEventId();
    }

    @Override
    public void updateEvent(Event event) {
        updateQuery(event, UPDATE_EVENT_QUERY);
    }

    private void updateQuery(Event event, String updateEventQuery) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(updateEventQuery);
            ps.setString(1, event.getEventId().toString());
            ps.setTimestamp(2, event.getFinish());
            ps.setTimestamp(3, event.getStart());
            ps.setString(4, event.getStatus().toString());
            ps.setString(5, event.getTitle());
            ps.setString(6, event.getDescription());
            ps.setString(7, event.getAddress());
            return ps;
        });
    }

    @Override
    public void deleteEvent(UUID eventId) {
        jdbcTemplate.update(DELETE_EVENT_QUERY, eventId);
    }

    @Override
    public Event findEventById(UUID eventId) {
        return jdbcTemplate.queryForObject(FIND_EVENT_BY_ID_QUERY, new EventMapper(), eventId);
    }
}

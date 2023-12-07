package scm.serenityproject.eventservice.repository;

import scm.serenityproject.eventservice.model.Event;

import java.util.UUID;

public interface EventRepository {

    UUID createEvent(Event event);
    void updateEvent(Event event);
    void deleteEvent(UUID eventId);
    Event findEventById(UUID eventId);
}

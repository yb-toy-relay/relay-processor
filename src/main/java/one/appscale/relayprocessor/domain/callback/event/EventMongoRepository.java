package one.appscale.relayprocessor.domain.callback.event;

import one.appscale.relayschema.domain.event.Event;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventMongoRepository extends MongoRepository<Event, ObjectId> {
}

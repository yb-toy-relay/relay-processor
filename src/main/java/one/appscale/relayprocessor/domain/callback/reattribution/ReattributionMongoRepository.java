package one.appscale.relayprocessor.domain.callback.reattribution;

import one.appscale.relayschema.domain.reattribution.Reattribution;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReattributionMongoRepository extends MongoRepository<Reattribution, ObjectId> {
}

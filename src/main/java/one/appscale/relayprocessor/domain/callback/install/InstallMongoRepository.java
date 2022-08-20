package one.appscale.relayprocessor.domain.callback.install;

import one.appscale.relayschema.domain.install.Install;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InstallMongoRepository extends MongoRepository<Install, ObjectId> {
}

package in.coding.journalApp.Repository;

//import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;

import in.coding.journalApp.Entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {         //<what is is , id_Type>

    User findByUserName(String userName);
}


// controller -- > Service -- > Repository



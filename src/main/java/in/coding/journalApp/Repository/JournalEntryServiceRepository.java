package in.coding.journalApp.Repository;

//import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;

import in.coding.journalApp.Entities.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryServiceRepository extends MongoRepository<JournalEntry, String> {         //<what is is , id_Type>
}


// controller -- > Service -- > Repository
package in.coding.journalApp.Service;

import in.coding.journalApp.Entities.JournalEntry;
import in.coding.journalApp.Entities.User;
import in.coding.journalApp.Repository.JournalEntryServiceRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryServiceRepository journalEntryServiceRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String UserName) {
//        User user = userService.findByUserName(UserName);
        User user = userService.findByUserName(UserName);
        JournalEntry saved = journalEntryServiceRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public void saveEntry(JournalEntry journalEntry) {

        journalEntryServiceRepository.save(journalEntry);
    }

    public void removeEntry(JournalEntry journalEntry) {
        journalEntryServiceRepository.delete(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryServiceRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryServiceRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName) {
//        deleteById();
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryServiceRepository.deleteById(id);
    }

    public void deleteall() {
        journalEntryServiceRepository.deleteAll();
    }
}

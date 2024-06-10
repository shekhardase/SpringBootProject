package in.coding.journalApp.Service;

import in.coding.journalApp.Entities.JournalEntry;
import in.coding.journalApp.Repository.JournalEntryServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryServiceRepository journalEntryServiceRepository;

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryServiceRepository.save(journalEntry);
    }

    public void removeEntry(JournalEntry journalEntry) {
        journalEntryServiceRepository.delete(journalEntry);
    }
}

package in.coding.journalApp.Controller;

import in.coding.journalApp.Entities.JournalEntry;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalApp_Controller {

    private final Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping                                         // if map not provided (url)
    public List<JournalEntry> getAll() {                           // by default if we use get in postman we will redirect here
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean CreateEntry(@RequestBody JournalEntry myEntry) {                      // and for post we will get here
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId) {
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable Long myId) {
        journalEntries.remove(myId);
        return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntryById(@PathVariable Long id, @RequestBody JournalEntry myEntry) {
        return journalEntries.put(id, myEntry);
    }
}

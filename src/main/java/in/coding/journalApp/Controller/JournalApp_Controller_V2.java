package in.coding.journalApp.Controller;

import in.coding.journalApp.Entities.JournalEntry;
import in.coding.journalApp.Service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalApp_Controller_V2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping                                         // if map not provided (url)
    public List<JournalEntry> getAll() {                           // by default if we use get in postman we will redirect here
//        return new ArrayList<>(journalEntries.values());
        return null;
    }


    @PostMapping
    public boolean CreateEntry(@RequestBody JournalEntry myEntry) {                      // and for post we will get here

        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId) {
        return null;
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable Long myId) {

        return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntryById(@PathVariable Long id, @RequestBody JournalEntry myEntry) {
        return null;
    }
}

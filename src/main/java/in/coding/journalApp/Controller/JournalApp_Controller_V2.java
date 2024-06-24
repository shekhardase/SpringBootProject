package in.coding.journalApp.Controller;

import in.coding.journalApp.Entities.JournalEntry;
import in.coding.journalApp.Entities.User;
import in.coding.journalApp.Service.JournalEntryService;
import in.coding.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalApp_Controller_V2 {

    @Autowired
    private JournalEntryService journalEntryService;

//    @Autowired
//    private JournalEntryServiceRepository jp;

    @Autowired
    private UserService userService;

    @GetMapping("{UserName}")                                        // if map not provided (url)
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String UserName) {                           // by default if we use get in postman we will redirect here

        User user = userService.findByUserName(UserName);

        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("{UserName}")
    public ResponseEntity<JournalEntry> CreateEntry(@RequestBody JournalEntry myEntry, @PathVariable String UserName) {                      // and for post we will get here
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry, UserName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/ {UserName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String UserName) {
        journalEntryService.deleteById(myId, UserName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{UserName} /{id}")
    public ResponseEntity<?> updateEntryById
            (@PathVariable ObjectId id,
             @RequestBody JournalEntry newEntry,
             @PathVariable String UserName) {
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/DeleteAll")
    public ResponseEntity<?> deleteall() {
        journalEntryService.deleteall();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

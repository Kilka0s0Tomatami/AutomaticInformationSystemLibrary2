package com.example.auto_information_system.service;

import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auto_information_system.model.BooksOnHands;
import com.example.auto_information_system.model.Checks;
import com.example.auto_information_system.repo.ChecksRepository;


@Service
public class ChecksService {
    
    @Autowired
    private ChecksRepository checksRepository;
    @Autowired
    private BooksOnHandsService booksOnHandsService;
    
    public List<Checks> getAllChecks() {
        return checksRepository.findAll();
    }
    public List<Checks> getChecksByLibCardId(int libCardId) {
        return checksRepository.findByLibCardId(libCardId);
    }
    
    public void saveChecks(Checks card) {
        checksRepository.save(card);
    }

    public Checks getChecksById(Integer id) {
        return checksRepository.findById(id).orElse(null);
    }

    public void openChecks() {
        List<BooksOnHands> newChecks = booksOnHandsService.getOverdueBooksOnHands();
        for (int i = 0; i < newChecks.size(); i++) {
            Checks checks = new Checks(newChecks.get(i).getLibCardId(), newChecks.get(i).getBook_copy_id(), java.sql.Date.valueOf(java.time.LocalDate.now()), null);
            checksRepository.save(checks);
            newChecks.get(i).setBookOnHandStatus(3);
            booksOnHandsService.saveBookOnHands(newChecks.get(i));
        }
    }
    public void closeChecks(int check_id) {
        Checks checks = getChecksById(check_id);
        checks.setCheck_finish_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
        checksRepository.save(checks);
    }
}

package src.main.java.com.trade_accounting.repositories.company;

import com.trade_accounting.models.entity.company.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
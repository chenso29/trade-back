package src.test.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.Contact;
import com.trade_accounting.models.dto.company.ContactDto;
import com.trade_accounting.repositories.company.ContactRepository;
import com.trade_accounting.Stubs.dto.company.ContactDtoStubs;
import com.trade_accounting.Stubs.model.company.ContactModelStubs;
import com.trade_accounting.utils.mapper.company.ContactMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTest {

    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;

    @Spy
    private ContactMapper contactMapper;

    @Test
    void getAll() {
        when(contactRepository.findAll())
                .thenReturn(
                        List.of(
                                ContactModelStubs.getContact(1L),
                                ContactModelStubs.getContact(2L),
                                ContactModelStubs.getContact(3L)
                        ));

        List<ContactDto> contactDtos = contactService.getAll();

        assertEquals(3, contactDtos.size());
    }

    @Test
    void getById() {
        when(contactRepository.getOne(anyLong()))
                .thenReturn(ContactModelStubs.getContact(1L));

        ContactDto contactDto = contactService.getById(1L);

        assertEquals(1, contactDto.getId());
    }

    @Test
    void create() {
        saveOrUpdate();
    }

    @Test
    void update() {
        saveOrUpdate();
    }

    @Test
    void deleteById() {
        contactService.deleteById(anyLong());
        verify(contactRepository).deleteById(anyLong());
    }

    private void saveOrUpdate() {
        when(contactRepository.save(any(Contact.class)))
                .thenReturn(ContactModelStubs.getContact(1L));

        ContactDto contactDto = contactService
                .create(ContactDtoStubs.getDto(1L));

        assertEquals(1, contactDto.getId());
        verify(contactRepository).save(any(Contact.class));
    }
}


package src.test.java.com.trade_accounting.Stubs.dto.company;

import com.trade_accounting.Stubs.model.company.ContactModelStubs;
import com.trade_accounting.models.dto.company.ContactDto;
import com.trade_accounting.utils.mapper.company.ContactMapper;
import org.mapstruct.factory.Mappers;


public class ContactDtoStubs {
    private static final ContactMapper mapper = Mappers.getMapper(ContactMapper.class);

    public static ContactDto getDto(Long id) {
        return mapper.toDto(ContactModelStubs.getContact(id));
    }
}

package src.main.java.com.trade_accounting.utils.mapper.company;

import com.trade_accounting.models.entity.company.Contact;
import com.trade_accounting.models.dto.company.ContactDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    /**
     * @return Contact
     */

    default Contact toModel(ContactDto contactDto) {
        if (contactDto == null) {
            return null;
        }

        return Contact.builder()
                .id(contactDto.getId())
                .fullName(contactDto.getFullName())
                .position(contactDto.getPosition())
                .phone(contactDto.getPhone())
                .email(contactDto.getEmail())
                .comment(contactDto.getComment())
                .build();
    }

    /**
     * @return ContactDto
     */

    default ContactDto toDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        if (contact == null) {
            return null;
        } else {
            contactDto.setId(contact.getId());
            contactDto.setFullName(contact.getFullName());
            contactDto.setPosition(contact.getPosition());
            contactDto.setEmail(contact.getEmail());
            contactDto.setPhone(contact.getPhone());
            contactDto.setComment(contact.getComment());
            return contactDto;
        }
    }

    List<Contact> toListModel(List<ContactDto> contactDtoList);

    List<ContactDto> toListDto(List<Contact> contactList);
}

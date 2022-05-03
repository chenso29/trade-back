package src.test.java.com.trade_accounting.Stubs.model.company;

import com.trade_accounting.models.entity.company.Contact;

public class ContactModelStubs {

    public static Contact getContact(Long id){
        return Contact.builder()
                .id(id)
                .fullName("Contact fullName")
                .position("position")
                .phone("89998887766")
                .email("contact@mail.com")
                .comment("Comment"+id)
                .build();
    }
}

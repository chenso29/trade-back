package src.test.java.com.trade_accounting.utils;

import com.trade_accounting.utils.translator.Translator;
import org.junit.jupiter.api.Test;

public class TranslatorTest {

    @Test
    void testTranslate() {
        Translator translator = new Translator();
        String text = "Hello world!";
        System.out.println("Translated text: " + translator.translate("en", "ru", text));
    }
}

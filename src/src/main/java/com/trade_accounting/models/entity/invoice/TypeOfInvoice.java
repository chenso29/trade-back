package src.main.java.com.trade_accounting.models.entity.invoice;

/**
 * Класс-перечисление типов накладных
 *
 * @param RECEIPT   - приход
 * @param EXPENSE   - расход
 * @param POSTING   - оприходование
 * @param WRITE_OFF - списание
 * @author Sanych
 * @see Invoice#typeOfInvoice
 */
public enum TypeOfInvoice {
    RECEIPT,
    EXPENSE,
    POSTING,
    WRITE_OFF
}

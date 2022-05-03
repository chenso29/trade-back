package src.main.java.com.trade_accounting.services.interfaces.util;

import java.util.List;

public interface AbstractService<T> {

    List<T> getAll();

    T getById(Long id);

    T create(T dto);

    T update(T dto);

    void deleteById(Long id);
}

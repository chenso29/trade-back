package src.main.java.com.trade_accounting.services.interfaces.util;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface SearchableService<MODEL, DTO> {

    List<DTO> search(Specification<MODEL> spec);

    @Transactional
    default List<DTO> executeSearch(JpaSpecificationExecutor<MODEL> executor,
                                    Function<MODEL, DTO> mapper,
                                    Specification<MODEL> spec) {
        return executor.findAll(spec).stream().map(mapper).collect(Collectors.toList());
    }
}

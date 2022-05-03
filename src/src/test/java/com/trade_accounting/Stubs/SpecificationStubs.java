package src.test.java.com.trade_accounting.Stubs;

import com.trade_accounting.models.entity.finance.Prepayout;
import com.trade_accounting.models.entity.production.OrdersOfProduction;
import com.trade_accounting.models.entity.util.OperationsAbstract;
import com.trade_accounting.models.entity.warehouse.BuyersReturn;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.entity.units.Currency;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.finance.PrepaymentReturn;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.production.ProductionTargets;
import com.trade_accounting.models.entity.production.StagesProduction;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationStubs {
    public static Specification<Employee> getEmployeeSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<Prepayout> getPrepayoutSpecificationStub() {
        return (root, query, criteriaBuilder) -> null;
    }

    public static Specification<Company> getCompanySpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<Contract> getContractSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<Currency> getCurrencySpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }
    public static Specification<Product> getProductSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<ProductionTargets> getProductionTargetsSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<BuyersReturn> getBuyersReturnSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<StagesProduction> getStagesProductionSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }
//
    public static Specification<PrepaymentReturn> getPrepaymentReturnSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<OperationsAbstract> getOperationSpecificationStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }

    public static Specification<OrdersOfProduction> getOrdersOfProductionStub() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }
}

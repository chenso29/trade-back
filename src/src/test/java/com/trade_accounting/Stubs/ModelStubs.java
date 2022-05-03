package src.test.java.com.trade_accounting.Stubs;

import com.trade_accounting.models.dto.indicators.AuditDto;
import com.trade_accounting.models.dto.util.ImageDto;
import com.trade_accounting.models.dto.warehouse.ProductDto;
import com.trade_accounting.models.entity.client.Department;
import com.trade_accounting.models.entity.client.Employee;
import com.trade_accounting.models.entity.client.Position;
import com.trade_accounting.models.entity.client.Role;
import com.trade_accounting.models.entity.company.AccessParameters;
import com.trade_accounting.models.entity.company.Address;
import com.trade_accounting.models.entity.company.BankAccount;
import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.entity.company.Contact;
import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.entity.company.Contractor;
import com.trade_accounting.models.entity.company.ContractorGroup;
import com.trade_accounting.models.entity.company.ContractorStatus;
import com.trade_accounting.models.entity.company.LegalDetail;
import com.trade_accounting.models.entity.company.SupplierAccount;
import com.trade_accounting.models.entity.company.TaxSystem;
import com.trade_accounting.models.entity.company.TypeOfContractor;
import com.trade_accounting.models.entity.company.TypeOfPrice;
import com.trade_accounting.models.entity.fias.City;
import com.trade_accounting.models.entity.fias.District;
import com.trade_accounting.models.entity.fias.FiasAddressModel;
import com.trade_accounting.models.entity.fias.Region;
import com.trade_accounting.models.entity.fias.Street;
import com.trade_accounting.models.entity.finance.AgentReports;
import com.trade_accounting.models.entity.finance.BalanceAdjustment;
import com.trade_accounting.models.entity.finance.Correction;
import com.trade_accounting.models.entity.finance.CorrectionProduct;
import com.trade_accounting.models.entity.finance.MutualSettlements;
import com.trade_accounting.models.entity.finance.Payment;
import com.trade_accounting.models.entity.finance.PaymentMethods;
import com.trade_accounting.models.entity.finance.PrepaymentReturn;
import com.trade_accounting.models.entity.finance.Prepayout;
import com.trade_accounting.models.entity.finance.ReturnToSupplier;
import com.trade_accounting.models.entity.finance.TypeOfPayment;
import com.trade_accounting.models.entity.indicators.Audit;
import com.trade_accounting.models.entity.invoice.InvoiceProduct;
import com.trade_accounting.models.entity.invoice.InvoicesStatus;
import com.trade_accounting.models.entity.production.OrdersOfProduction;
import com.trade_accounting.models.entity.production.Production;
import com.trade_accounting.models.entity.production.ProductionTargets;
import com.trade_accounting.models.entity.production.RequestsProductions;
import com.trade_accounting.models.entity.production.StagesProduction;
import com.trade_accounting.models.entity.production.TechnicalCard;
import com.trade_accounting.models.entity.production.TechnicalCardGroup;
import com.trade_accounting.models.entity.production.TechnicalCardProduction;
import com.trade_accounting.models.entity.production.TechnicalOperations;
import com.trade_accounting.models.entity.production.TechnicalProcess;
import com.trade_accounting.models.entity.retail.RetailStore;
import com.trade_accounting.models.entity.units.Currency;
import com.trade_accounting.models.entity.util.File;
import com.trade_accounting.models.entity.util.Image;
import com.trade_accounting.models.entity.util.Project;
import com.trade_accounting.models.entity.util.Task;
import com.trade_accounting.models.entity.util.TaskComment;
import com.trade_accounting.models.entity.warehouse.Acceptance;
import com.trade_accounting.models.entity.warehouse.AcceptanceProduction;
import com.trade_accounting.models.entity.warehouse.AttributeOfCalculationObject;
import com.trade_accounting.models.entity.warehouse.BuyersReturn;
import com.trade_accounting.models.entity.warehouse.Inventarization;
import com.trade_accounting.models.entity.warehouse.InventarizationProduct;
import com.trade_accounting.models.entity.warehouse.Product;
import com.trade_accounting.models.entity.warehouse.Revenue;
import com.trade_accounting.models.entity.warehouse.Warehouse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModelStubs {
    //TODO Вынести заглушки моделей из классов сервисов сюда

    public static AccessParameters getAccessParameters(Long id) {
        return new AccessParameters(id, false, getEmployee(id), getDepartment(id));
    }

    public static AttributeOfCalculationObject getAttributeOfCalculationObject(Long id) {
        return new AttributeOfCalculationObject(id, "name", "00001", true);
    }

    public static Payment getPayment(Long id) {
        return Payment.builder()
                .id(id)
                .company(getCompany(1L))
                .contract(getContract(1L))
                .contractor(getContractor(1L))
                .paymentMethods(PaymentMethods.BANK)
                .number("100")
                .sum(BigDecimal.valueOf(100L))
                .date(LocalDateTime.now())
                .isRecyclebin(id % 2 == 0)
                .typeOfPayment(TypeOfPayment.INCOMING)
                .project(Project.builder()
                        .id(1L)
                        .name("name")
                        .code("code")
                        .description("description")
                        .build())
                .build();
    }

    public static OrdersOfProduction getOrdersOfProduction(Long id) {
        return OrdersOfProduction.builder()
                .id(id)
                .date(LocalDateTime.now())
                .company(getCompany(1L))
                .technicalCard(getTechnicalCard(1L))
                .volume(10)
                .produce(10)
                .plannedProductionDate(LocalDateTime.now())
                .isSent(id % 2 == 0)
                .isPrint(id % 2 == 0)
                .comment("Comment " + id)
                .build();
    }

    public static Company getCompany(Long id) {
        return new Company(
                id, "name",
                "inn", "00001",
                "89040408488", "3420943",
                "email", true, getAddress(1L),
                "commentToAddress", "leader",
                "leaderManagerPos", "signatureOfLider",
                "cheidAcc", "aaaaa", "stamp",
                getLegalDetail(id),
                Stream.of(
                        getBankAccount(1L),
                        getBankAccount(2L),
                        getBankAccount(3L)
                ).collect(Collectors.toList())
        );
    }

    public static Contractor getContractor(Long id) {
        return new Contractor(
                id, "name","shortname",
                "sortNumber",
                "12345678901", "324234234",
                "email", getAddress(1L),
                "commentToAddress", "comment",
                "1234",
                Stream.of(
                        getContact(1L),
                        getContact(2L),
                        getContact(3L)
                ).collect(Collectors.toList()),
                getContractorGroup(id), // getTypeOfContractor(id),
                getTypeOfPrice(id),
                Stream.of(
                        getBankAccount(1L),
                        getBankAccount(2L),
                        getBankAccount(3L)
                ).collect(Collectors.toList()),
                getLegalDetail(id),
                getStatus(id),
                getAccessParameters(id)
        );
    }

    public static Contact getContact(Long id) {
        return Contact.builder().id(id).build();
    }

    public static Contract getContract(Long id) {
            return Contract.builder()
                    .id(id)
                    .number("00000" + id)
                    .contractDate(LocalDate.now())
                    .company(getCompany(1L))
                    .bankAccount(getBankAccount(3L))
                    .contractor(getContractor(1L))
                    .amount(BigDecimal.ONE)
                    .archive(false)
                    .comment("Comment " + id)
                    .legalDetail(getLegalDetail(1L))
                    .build();
    }

    public static Project getProject(Long id) {
        return new Project(
                id, "name",
                "00001", "description"
        );
    }

    public static BankAccount getBankAccount(Long id) {
        return new BankAccount(
                id, "rbic", "bank",
                "address", "correspon",
                "account", true, "00001"
        );
    }

    public static ContractorGroup getContractorGroup(Long id) {
        return new ContractorGroup(id, "name", "00001");
    }

    public static Address getAddress(Long id) {
        return Address.builder()
                .id(id)
                .index("123456")
                .country("Россия")
                .region("region")
                .city("city")
                .house("1")
                .apartment("1")
                .another("")
                .build();
    }

    public static Department getDepartment(Long id) {
        return new Department(id, "name", "00001");
    }

    public static TaxSystem getTaxSystem(Long id) {
        return new TaxSystem(id, "name", "00001");
    }

    public static Position getPosition(Long id) {
        return new Position(id, "name", "00001");
    }



    public static Employee getEmployee(Long id) {
        return new Employee(
                id, "lastName", "firstName",
                "middleName", "00001", "89030450020",
                "123456789012", "descript",
                "email.email@email.com", "pass",
                getDepartment(id), getPosition(id),
                Stream.of(
                        getRole(id),
                        getRole(id + 1),
                        getRole(id + 2)
                ).collect(Collectors.toSet()),
                getImage(id)
        );
    }

    public static Image getImage(Long id) {
        return Image.builder()
                .id(id)
                .imageUrl("url")
                .sortNumber("000" + id)
                .build();
    }

    public static ImageDto getImageDto(Long id) {
        return ImageDto.builder()
                .id(id)
                .content("content".getBytes())
                .fileExtension(".png")
                .sortNumber("000" + id)
                .build();
    }

    public static TypeOfContractor getTypeOfContractor(Long id) {
        return new TypeOfContractor(id, "name", "00001");
    }

    public static TypeOfPrice getTypeOfPrice(Long id) {
        return new TypeOfPrice(id, "name", "00001");
    }

    public static LegalDetail getLegalDetail(Long id) {
        return new LegalDetail(
                id, "lastName",
                "firstNAme", "middleName",
                getAddress(1L), "commentToAddress",
                "32432423", "kpp", "okpo", "ogrn",
                "numberOfCertifacate", LocalDate.now(), getTypeOfContractor(id)
        );
    }

    public static Role getRole(Long id) {
        return new Role(id, "name", "00001");
    }

    public static Revenue getRevenue(Long id) {
        return Revenue.builder()
                .id(id)
                .product(getProduct(id))
                .description("description")
                .itemNumber(32)
                .amountAcceptance(new BigDecimal(4344))
                .acceptance(new Acceptance())
                .acceptanceProduction(new AcceptanceProduction())
                .invoiceProduct(new InvoiceProduct())
                .startOfPeriodAmount(33)
                .startOfPeriodSumOfPrice(43)
                .endOfPeriodAmount(21)
                .endOfPeriodSumOfPrice(18)
                .comingAmount(74)
                .comingSumOfPrice(83)
                .spendingAmount(199)
                .spendingSumOfPrice(288).build();

    }

//    public static Invoice getInvoice(Long id) {
//        return new Invoice(
//                id,
//                LocalDateTime.now(),
//                TypeOfInvoice.RECEIPT,
//                getCompany(id),
//                getContractor(id),
//                getWarehouse(id),
//                Boolean.TRUE,
//                "Комментарий"
//        );
//    }

    public static Currency getCurrency(Long id) {
        return new Currency(id, "rubles", "Russian Rubles", "25", "rub", "1");
    }

    public static Product getProduct(Long id) {
        return Product.builder()
                .id(id)
                .name("Яблоко")
                .weight(BigDecimal.TEN)
                .volume(BigDecimal.TEN)
                .purchasePrice(BigDecimal.ONE)
                .description("Description")
                .archive(false)
                .build();
    }

    public static ProductDto getProductDto(Long id) {
        return ProductDto.builder()
                .id(id)
                .name("Яблоко")
                .weight(BigDecimal.TEN)
                .volume(BigDecimal.TEN)
                .purchasePrice(BigDecimal.ONE)
                .description("Description")
                .archive(false)
                .build();
    }

    public static City getCity(Long id) {
        return City.builder()
                .id(id)
                .name("Petrpopavlovsk")
                .district(getDistrict(id))
                .streets(new ArrayList<>())
                .build();
    }

    public static District getDistrict(Long id) {
        return District.builder()
                .id(id)
                .name("Vasileostrivky")
                .region(getRegion(id))
                .cities(new ArrayList<>())
                .build();
    }

    public static Region getRegion(Long id) {
        return Region.builder()
                .id(id)
                .name("SKO")
                .districts(new ArrayList<>())
                .build();
    }

    public static FiasAddressModel getFiasAddressModel(Long id) {
        return FiasAddressModel.builder()
                .id(id)
                .aoguid("example")
                .aolevel("1")
                .formalname("formalName")
                .parentguid("parentguid")
                .shortname("shortname")
                .build();
    }

    public static ContractorStatus getStatus(Long id) {
        return ContractorStatus.builder()
                .id(id)
                .name("Новый")
                .build();
    }
    public static InvoicesStatus getInvoicesStatus(Long id) {
        return new InvoicesStatus(id,"новый");
    }

    public static Street getStreet(Long id) {
        return Street.builder()
                .id(id)
                .name("Volodarskogo")
                .city(getCity(id))
                .build();
    }

    public static Production getProduction(Long id) {
        return Production.builder()
                .id(id)
                .technicalCard(getTechnicalCard(id))
                .requestsProductions(getRequestsProductions(id))
                .build();
    }

    public static TechnicalCard getTechnicalCard(Long id) {
        return TechnicalCard.builder()
                .id(id)
                .name("name")
                .comment("comment")
                .productionCost("productionCost")
                .technicalCardGroup(getTechnicalCardGroup(id))
                .finalProduction(Stream.of(
                        getTechnicalCardProduction(id),
                        getTechnicalCardProduction(id + 1),
                        getTechnicalCardProduction(id + 2)
                ).collect(Collectors.toList()))
                .materials(Stream.of(
                        getTechnicalCardProduction(id + 3),
                        getTechnicalCardProduction(id + 4),
                        getTechnicalCardProduction(id + 5)
                ).collect(Collectors.toList()))
                .build();
    }

    public static TechnicalCardGroup getTechnicalCardGroup(Long id) {
        return TechnicalCardGroup.builder()
                .id(id)
                .name("name")
                .comment("comment")
                .sortNumber("sortNumber")
                .build();
    }

    public static TechnicalCardProduction getTechnicalCardProduction(Long id) {
        return TechnicalCardProduction.builder()
                .id(id)
                .amount(1L)
                .product(getProduct(id))
                .build();
    }

    public static RequestsProductions getRequestsProductions(Long id) {
        return RequestsProductions.builder()
                .id(id)
                .numberOfTheCertificate("123")
                .dateOfTheCertificate(LocalDate.ofEpochDay(2021 - 06 - 01))
                .technicalCard(getTechnicalCard(id))
                .volume(2)
                .warehouse(getWarehouse(id))
                .build();
    }

    public static Warehouse getWarehouse(Long id) {
        return Warehouse.builder()
                .id(id)
                .name("name")
                .sortNumber("sortNamber")
                .address("address")
                .commentToAddress("commentToAddress")
                .comment("comment")
                .build();
    }

    public static Warehouse getWarehouse() {
        return new Warehouse(
                1L, "Склад 1", "1", "Володарского", "Комментарий 1",
                "Комментарий 2"
        );
    }

    public static ReturnToSupplier getReturnToSupplier(Long id) {
        return ReturnToSupplier.builder()
                .id(id)
                .comment("Комментарий 1")
                .company(getCompany(1L))
                .contract(getContract(1L))
                .contractor(getContractor(1L))
                .warehouse(getWarehouse(1L))
                .date(LocalDateTime.now().toString())
                .isPrint(false)
                .isSend(false)
                .build();
    }

    public static CorrectionProduct getCorrectionProduct(Long id) {
        return new CorrectionProduct(
                id,
                getProduct(id),
                BigDecimal.ONE,
                BigDecimal.ONE
        );
    }

    public static Correction getCorrection(Long id) {
        return new Correction(
                id,
                LocalDateTime.now(),
                getWarehouse(),
                getCompany(id),
                false,
                false,
                false,
                "Комментарий 1",
                List.of(getCorrectionProduct(1L),
                        getCorrectionProduct(2L),
                        getCorrectionProduct(3L)),
                false
        );
    }

    public static InventarizationProduct getInventarizationProduct(Long id) {
        return new InventarizationProduct(
                id,
                getProduct(id),
                BigDecimal.ONE,
                BigDecimal.ONE
        );
    }

    public static Inventarization getInventarization(Long id) {
        return new Inventarization(
//                id,
//                LocalDateTime.now(),
                getWarehouse(id),
//                getCompany(id),
                false,
//                "Комментарий 1",
                List.of(getInventarizationProduct(1L),
                        getInventarizationProduct(2L),
                        getInventarizationProduct(3L))
        );
    }

//    public static AcceptanceProduction getAcceptanceProduction(Long id) {
//        return AcceptanceProduction.builder()
//                .id(id)
//                .product(getProduct(1L))
//                .amount(100L)
//                .build();
//    }

//    public static Acceptance getAcceptance(Long id) {
//        return Acceptance.builder()
//                .id(id)
//                .acceptanceProduction(new ArrayList<>())
//                .contract(getContract(1L))
//                .contractor(getContractor(1L))
//                .comment("Комментарий " + id)
//                .incomingNumber("100")
//                .incomingNumberDate(LocalDate.now())
//                .warehouse(getWarehouse(1L))
//                .project(Project.builder()
//                        .id(1L)
//                        .name("name")
//                        .description("decr")
//                        .code("code")
//                        .build())
//                .build();
//    }

    public static AgentReports getAgentReports(Long id) {
        return AgentReports.builder()
                .id(id)
                .company(getCompany(1L))
                .contractor(getContractor(1L))
                .comitentSum(100L)
                .commentary("Коммент 1")
                .documentType(".doc")
                .number("1")
                .paid(10L)
                .printed(100L)
                .remunirationSum(100L)
                .sent(20L)
                .status("status 1")
                .time(LocalDateTime.now())
                .sum(1000L)
                .build();
    }

    public static SupplierAccount getSupplierAccount(Long id) {
        return SupplierAccount.builder()
                .id(id)
                .warehouse(getWarehouse(1L))
                .contract(getContract(1L))
                .contractor(getContractor(1L))
                .company(getCompany(1L))
                .date(LocalDateTime.now())
                .comment("Комментарий")
                .isSpend(false)
                .build();
    }

    public static BalanceAdjustment getBalanceAdjustment(Long id) {
        return BalanceAdjustment.builder()
                .id(id)
                .date(LocalDateTime.now().toString())
                .company(getCompany(1L))
                .contractor(getContractor(1L))
                .account("Account 1")
                .cashOffice("Cash Office 1")
                .comment("Comment 1")
                .dateChanged(LocalDateTime.now().toString())
                .whoChanged("Who changed 1")
                .build();
    }

    public static Task getTask(Long id) {
        return Task.builder()
                .id(id)
                .description("description")
                .taskEmployee(getEmployee(1L))
                .taskAuthor(getEmployee(1L))
                .creationDateTime(LocalDateTime.now())
                .deadlineDateTime(LocalDateTime.now())
                .completed(false)
                .taskComments(List.of(getTaskComment(1L),
                                    getTaskComment(2L),
                                    getTaskComment(3L)))
                .build();
    }
    public static TaskComment getTaskComment(Long id) {
        return TaskComment.builder()
                .id(id)
                .commentContent("Comment content")
                .publisher(getEmployee(1L))
                .publishedDateTime(LocalDateTime.now())
                .task(null)
                .build();
    }

    public static RetailStore getRetailStore(Long id) {
        return RetailStore.builder()
                .id(1L)
                .activityStatus("Был в сети вчера")
                .defaultTaxationSystem("ОСН")
                .isActive(true)
                .name("Ozon111")
                .orderTaxationSystem("УСН. Доход")
                .revenue(BigDecimal.valueOf(12000))
                .salesInvoicePrefix("SI")
                .company(getCompany(1L))
                .cashiers(List.of(getEmployee(id)))
                .build();
    }

    public static MutualSettlements getMutualSettlements(Long id) {
        return MutualSettlements.builder()
                .id(id)
                .contractor(getContractor(1L))
                .employee(getEmployee(1L))
                .initialBalance(111)
                .income(111)
                .expenses(111)
                .finalBalance(111)
                .build();
    }

    public static ProductionTargets getProductionTargets(Long id) {
        return new ProductionTargets(id, LocalDateTime.now(), getCompany(id), LocalDateTime.now(), getWarehouse(id),
                getWarehouse(id), LocalDateTime.now(), LocalDateTime.now(), false, "owner", "owner",
                false, false, "comment", LocalDateTime.now(), "Name");
    }

    public static File getFile(Long id) {
        return File.builder()
                .id(id)
                .name("name")
                .employee("Employee")
                .placement("Placement")
                .uploadDateTime(LocalDateTime.now())
                .key(UUID.randomUUID().toString())
                .extension(".ext")
                .build();
    }

    public static BuyersReturn getBuyersReturn(Long id) {
        return new BuyersReturn(id, LocalDateTime.now(), getWarehouse(id), getContractor(id), getCompany(id), new BigDecimal(1000), false, false, "comment");
    }

    public static StagesProduction getStagesProduction(Long id) {
        return  new StagesProduction(id, "name", "description", getDepartment(id), getEmployee(id));
    }

    public static PrepaymentReturn getPrepaymentReturn(Long id) {
        return new PrepaymentReturn(id, "", getRetailStore(id), getContractor(id), getCompany(id), new BigDecimal(1000), new BigDecimal(500), true, true, "comment");
    }

    public static Prepayout getPrepayout(Long id) {
        return new Prepayout(id, LocalDateTime.now(), getRetailStore(id), getContractor(id), getCompany(id), new BigDecimal(243), new BigDecimal(323), new BigDecimal(445), new BigDecimal(877), false, false, "comment");
    }
    
    public static TechnicalOperations getTechnicalOperations(Long id) {
        return TechnicalOperations.builder()
                .volume(id.intValue())
                .technicalCard(ModelStubs.getTechnicalCard(1L))
                .warehouse(ModelStubs.getWarehouse(1L))
                .build();
    }
    public static TechnicalProcess getTechnicalProcess(Long id) {
        return TechnicalProcess.builder()
                .id(id)
                .name("name")
                .description("description")
                .stagesProductionSet(Set.of(ModelStubs.getStagesProduction(1L)))
                .isArchived(false)
                .isShared(true)
                .departmentOwner(ModelStubs.getDepartment(1L))
                .employeeOwner(ModelStubs.getEmployee(1L))
                .dateOfChanged(LocalDateTime.of(2014, 9, 19, 14, 5))
                .employeeWhoLastChanged(ModelStubs.getEmployee(1L))
                .build();
    }

    public static Audit getAudit(Long id){
        return Audit.builder()
                .id(id)
                .date(LocalDateTime.now())
                .description("description")
                .employee(ModelStubs.getEmployee(1L))
                .build();
    }

    public static AuditDto getAuditDto(Long id){
        return AuditDto.builder()
                .id(id)
                .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .description("description")
                .employeeId(1L)
                .build();
    }

}


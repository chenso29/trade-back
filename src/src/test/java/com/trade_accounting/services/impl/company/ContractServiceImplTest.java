package src.test.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.Contract;
import com.trade_accounting.models.dto.company.ContractDto;
import com.trade_accounting.repositories.company.BankAccountRepository;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.ContractRepository;
import com.trade_accounting.repositories.company.ContractorRepository;
import com.trade_accounting.repositories.company.LegalDetailRepository;
import com.trade_accounting.repositories.finance.PaymentRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.SpecificationStubs;
import com.trade_accounting.Stubs.dto.company.ContractDtoStubs;
import com.trade_accounting.utils.mapper.company.ContractMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContractServiceImplTest {

    @Mock
    CompanyRepository companyRepository;

    @Mock
    BankAccountRepository bankAccountRepository;

    @Mock
    ContractorRepository contractorRepository;

    @Mock
    LegalDetailRepository legalDetailRepository;

    @Mock
    ContractRepository contractRepository;

    @Mock
    PaymentRepository paymentRepository;

    @Spy
    ContractMapper contractMapper;

    @InjectMocks
    ContractServiceImpl contractService;

    @Test
    void getAllShouldReturnListOfContracts() {
        when(contractRepository.getAll())
                .thenReturn(
                        Stream.of(ModelStubs.getContract(1L),
                                ModelStubs.getContract(2L),
                                ModelStubs.getContract(3L)
                        ).collect(Collectors.toList()));
        List<ContractDto> contractDtoList = contractService.getAll();

        assertNotNull(contractDtoList, "Failure, ContractDtoList should not be null!");
        assertTrue(contractDtoList.size() > 0, "Failure> size of ContractDtoList should be greater than 0");

        for (ContractDto contractDto : contractDtoList) {
            contractDtoIsCorrectlyInitiated(contractDto);
        }
    }

    @Test
    void getAllShouldReturnEmptyListContractDto() {
        when(contractRepository.getAll()).thenReturn(new ArrayList<>());

        List<ContractDto> contractDtoList = contractService.getAll();

        assertNotNull(contractDtoList, "Failure, ContractDtoList should not be null!");
        assertEquals(0, contractDtoList.size(), "Size of ContractDtoList should be 0");
    }

    @Test
    void searchShouldReturnListOfContractsDto() {
        when(contractRepository.findAll(Mockito.<Specification<Contract>>any())).thenReturn(List.of(
                ModelStubs.getContract(1L),
                ModelStubs.getContract(2L),
                ModelStubs.getContract(3L)
        ));
        List<ContractDto> contractDtoList = contractService.search(SpecificationStubs.getContractSpecificationStub());

        assertNotNull(contractDtoList, "Failure, ContractDtoList should not be null!");
        assertTrue(contractDtoList.size() > 0, "Size of ContractDto List should be greater than 0");

        for (ContractDto contractDto : contractDtoList) {
            contractDtoIsCorrectlyInitiated(contractDto);
        }
    }

    @Test
    void getByIdShouldReturnCorrectContractDto() {
        when(contractRepository.getOne(anyLong())).thenReturn(ModelStubs.getContract(1L));

        ContractDto contractDto = contractService.getById(1L);

        contractDtoIsCorrectlyInitiated(contractDto);
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        contractService.create(ContractDtoStubs.getContractDto(1L));

        verify(contractRepository).save(any(Contract.class));
    }

    @Test
    void update_shouldPassInstructionsSuccessfulCreate() {
        contractService.update(ContractDtoStubs.getContractDto(1L));

        verify(contractRepository).save(any(Contract.class));
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        contractService.deleteById(1L);
        verify(contractRepository).deleteById(anyLong());
    }

    void contractDtoIsCorrectlyInitiated(ContractDto contractDto) {
        assertNotNull(contractDto.getId(), "Id should not be null");
        assertNotNull(contractDto.getContractorId(), "Contractor should not be null");
        assertNotNull(contractDto.getCompanyId(), "Company should not be null");
    }
}
package src.test.java.com.trade_accounting.services.impl.company;

import com.trade_accounting.models.entity.company.Company;
import com.trade_accounting.models.dto.company.CompanyDto;
import com.trade_accounting.repositories.company.AddressRepository;
import com.trade_accounting.repositories.company.BankAccountRepository;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.company.LegalDetailRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.SpecificationStubs;
import com.trade_accounting.Stubs.dto.company.CompanyDtoStubs;
import com.trade_accounting.utils.mapper.company.CompanyMapperImpl;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private LegalDetailRepository legalDetailRepository;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private AddressRepository addressRepository;

    @Spy
    private CompanyMapperImpl companyMapper;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    void getAll_shouldReturnListFilledCompanyDto() {
        when(companyRepository.findAll())
                .thenReturn(
                        Stream.of(
                                ModelStubs.getCompany(1L),
                                ModelStubs.getCompany(2L),
                                ModelStubs.getCompany(3L)
                        )
                                .collect(Collectors.toList())
                );
        List<CompanyDto> companies = companyService.getAll();
        assertNotNull(companies, "Failure - expected that list of company not null");
        assertTrue(companies.size() > 0, "failure - expected that size of list of company greater than 0");
        verify(companyRepository).findAll();
    }

    @Test
    void getAll_shouldReturnEmptyListCompanyDto() {
        when(companyRepository.findAll())
                .thenReturn(
                        new ArrayList<>()
                );

        List<CompanyDto> companies = companyService.getAll();

        assertNotNull(companies, "Failure - expected that list of company not null");
        assertEquals(0, companies.size(), "failure - expected that size of list of company equals 0");
        verify(companyRepository).findAll();
    }

    @Test
    void search_shouldReturnListFilledCompanyDto() {
        when(companyRepository.findAll(Mockito.<Specification<Company>>any()))
                .thenReturn(
                        Stream.of(
                                ModelStubs.getCompany(1L),
                                ModelStubs.getCompany(2L),
                                ModelStubs.getCompany(3L)
                        )
                                .collect(Collectors.toList())
                );

        List<CompanyDto> companies = companyService
                .search(SpecificationStubs.getCompanySpecificationStub());

        assertNotNull(companies, "Failure - expected that list of company not null");
        assertTrue(companies.size() > 0, "failure - expected that size of list of company greater than 0");
        verify(companyRepository).findAll(Mockito.<Specification<Company>>any());
        for (CompanyDto companyDto : companies) {
            companyDtoIsCorrectlyInited(companyDto);
        }
    }

    @Test
    void search_shouldReturnEmptyListCompanyDto() {
        when(companyRepository.findAll(Mockito.<Specification<Company>>any()))
                .thenReturn(new ArrayList<>());

        List<CompanyDto> companies = companyService
                .search(SpecificationStubs.getCompanySpecificationStub());

        assertNotNull(companies, "Failure - expected that list of company not null");
        assertEquals(0, companies.size(), "failure - expected that size of list of company greater than 0");
        verify(companyRepository).findAll(Mockito.<Specification<Company>>any());
    }

    @Test
    void getById_shouldReturnFilledCompanyDto() {
        when(companyService.getById(anyLong()))
                .thenReturn(CompanyDtoStubs.getCompanyDto(anyLong()));

        CompanyDto companyDto = companyService.getById(1L);

        companyDtoIsCorrectlyInited(companyDto);
        verify(companyRepository, times(2)).findById(anyLong());
    }

    @Test
    void getByEmail_shouldReturnFilledCompanyDto() {
        when(companyService.getByEmail(anyString()))
                .thenReturn(CompanyDtoStubs.getCompanyDto(1L));

        CompanyDto companyDto = companyService.getByEmail("email");

        companyDtoIsCorrectlyInited(companyDto);
        verify(companyRepository).findCompanyByEmail(anyString());
    }

    @Test
    void create_shouldPassInstructionsSuccessfulCreate() {
        companyService.create(CompanyDtoStubs.getCompanyDto(1L));

        verify(companyRepository).save(any(Company.class));
        verify(addressRepository).getOne(anyLong());
        verify(legalDetailRepository).getOne(anyLong());
        verify(bankAccountRepository, times(3)).getOne(anyLong());
    }

    @Test
    void update_shouldPassInstructionsSuccessfulUpdate() {
        companyService.update(CompanyDtoStubs.getCompanyDto(1L));

        verify(companyRepository).save(any(Company.class));
        verify(addressRepository).getOne(anyLong());
        verify(legalDetailRepository).getOne(anyLong());
        verify(bankAccountRepository, times(3)).getOne(anyLong());
    }

    @Test
    void deleteById_shouldPassInstructionsSuccessfulDelete() {
        companyService.deleteById(1L);
        verify(companyRepository).deleteById(anyLong());
    }

    void companyDtoIsCorrectlyInited(CompanyDto companyDto) {
        assertNotNull(companyDto, "failure - fail in passed companyDto");
        assertNotNull(companyDto.getId(), "failure - fail in field 'id' into companyDto");
    }
}
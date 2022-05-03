package src.test.java.com.trade_accounting.services.impl.util;

import com.trade_accounting.exceptions.BadRequestException;
import com.trade_accounting.exceptions.NotFoundEntityException;
import com.trade_accounting.models.dto.client.DepartmentDto;
import com.trade_accounting.models.dto.client.EmployeeDto;
import com.trade_accounting.models.dto.client.PositionDto;
import com.trade_accounting.models.dto.client.RoleDto;
import com.trade_accounting.models.dto.company.CompanyDto;
import com.trade_accounting.models.dto.util.ImageDto;
import com.trade_accounting.repositories.client.DepartmentRepository;
import com.trade_accounting.repositories.client.PositionRepository;
import com.trade_accounting.repositories.client.RoleRepository;
import com.trade_accounting.repositories.company.BankAccountRepository;
import com.trade_accounting.repositories.company.LegalDetailRepository;
import com.trade_accounting.repositories.util.ImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CheckEntityServiceImplTest {
	
	@InjectMocks
	CheckEntityServiceImpl checkEntityService;
	
	@Mock
	JpaRepository<Entity, Long> repository;
	
	@Mock
	CompanyDto company;
	
	@Mock
	LegalDetailRepository legalDetailRepository;
	
	@Mock
	BankAccountRepository bankAccountRepository;
	
	@Mock
	PositionRepository positionRepository;
	
	@Mock
	PositionDto position;
	
	@Mock
	DepartmentRepository departmentRepository;
	
	@Mock
	DepartmentDto department;
	
	@Mock
	EmployeeDto employee;
	
	@Mock
	ImageRepository imageRepository;
	
	@Mock
	RoleRepository roleRepository;
	
	@Mock
	RoleDto roleDto;
	
	@Mock
	Set<RoleDto> roles;
	


	@Test
	void checkForBadEmployeeCheckDepartment() {
		when(departmentRepository.getById(employee.getDepartmentDtoId())).thenReturn(new DepartmentDto().builder().id(5L).build());
		when(departmentRepository.existsById(5L)).thenReturn(false);
		assertThrows(BadRequestException.class, () -> checkEntityService.checkForBadEmployee(employee));
	}
	@Test
	void checkForBadEmployeeCheckPosition() {
		when(positionRepository.getById(employee.getPositionDtoId())).thenReturn(new PositionDto().builder().id(5L).build());
		when(positionRepository.existsById(5L)).thenReturn(false);
		assertThrows(BadRequestException.class, () -> checkEntityService.checkForBadEmployee(employee));
	}
	@Test
	void checkForBadEmployeeCheckImage() {
		when(imageRepository.getById(employee.getImageDtoId())).thenReturn(new ImageDto().builder().id(5L).build());
		when(imageRepository.existsById(5L)).thenReturn(false);
		assertThrows(BadRequestException.class, () -> checkEntityService.checkForBadEmployee(employee));
	}
	@Test
	void checkForBadEmployeeCheckRoles() {
		RoleDto role = new RoleDto(5L,"aa","aa");
		Set<RoleDto> set = new HashSet<>();
		set.add(role);
		when(roleRepository.getById(5L)).thenReturn(role);
		when(employee.getRoleDtoIds()).thenReturn(new HashSet<>(Arrays.asList(5L)));
		when(roleRepository.existsById(5L)).thenReturn(false);
		assertThrows(BadRequestException.class, () -> checkEntityService.checkForBadEmployee(employee));
	}
	
	
	@Test
	void checkForBadCompany() {
		ArrayList<Long> list = new ArrayList<>();
		list.add(5L);
		when(company.getLegalDetailDtoId()).thenReturn(1L);
		assertThrows(BadRequestException.class, () -> checkEntityService.checkForBadCompany(company));
		when(company.getLegalDetailDtoId()).thenReturn(0L);
		when(company.getBankAccountDtoIds()).thenReturn(list);
		assertThrows(BadRequestException.class, () -> checkEntityService.checkForBadCompany(company));
	}
	
	@Test
	void checkExists() {
		when(repository.existsById(anyLong())).thenReturn(false);
		assertThrows(NotFoundEntityException.class, () -> checkEntityService.checkExists(repository, anyLong()));
	}
}
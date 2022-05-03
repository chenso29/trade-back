package src.test.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.dto.production.TechnicalProcessDto;
import com.trade_accounting.models.entity.production.TechnicalProcess;
import com.trade_accounting.repositories.client.DepartmentRepository;
import com.trade_accounting.repositories.client.EmployeeRepository;
import com.trade_accounting.repositories.production.StagesProductionRepository;
import com.trade_accounting.repositories.production.TechnicalProcessRepository;
import com.trade_accounting.utils.mapper.production.TechnicalProcessMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TechnicalProcessServiceImplTest {
	
	@InjectMocks
	TechnicalProcessServiceImpl technicalProcessService;
	
	@Mock
	TechnicalProcessRepository technicalProcessRepository;
	
	@Mock
	TechnicalProcessMapper technicalProcessMapper;
	
	@Mock
	DepartmentRepository departmentRepository;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Mock
	StagesProductionRepository stagesProductionRepository;
	
	@Test
	void getAll() {
		when(technicalProcessRepository.findAll()).thenReturn(List.of(ModelStubs.getTechnicalProcess(1L), ModelStubs.getTechnicalProcess(2L), ModelStubs.getTechnicalProcess(3L)));
		List<TechnicalProcessDto> technicalOperationsDtoList = technicalProcessService.getAll();
		
		assertEquals(3, technicalOperationsDtoList.size());
		assertNotNull(technicalOperationsDtoList);
	}
	
	@Test
	void getById() {
		when(technicalProcessRepository.getOne(anyLong())).thenReturn(ModelStubs.getTechnicalProcess(1L));
		
		TechnicalProcessDto technicalProcessDto = technicalProcessService.getById(1L);
		System.err.println(technicalProcessDto);
		assertEquals(1, technicalProcessDto.getId());
	}
	
	@Test
	void create() {
		saveOrUpdate();
	}
	
	@Test
	void update() {
		saveOrUpdate();
	}
	
	@Test
	void deleteById() {
		technicalProcessService.deleteById(anyLong());
		verify(technicalProcessRepository).deleteById(anyLong());
	}
	
	@Test
	void search() {
	}
	
	@Test
	void getByName() {
	}
	
	private void saveOrUpdate() {
		when(technicalProcessRepository.save(any(TechnicalProcess.class))).thenReturn(ModelStubs.getTechnicalProcess(1L));
		
		TechnicalProcessDto technicalOperationsDto = technicalProcessService.create(Mappers.getMapper(TechnicalProcessMapper.class).toDto(ModelStubs.getTechnicalProcess(1L)));
		
		assertEquals(1, technicalOperationsDto.getId());
		verify(technicalProcessRepository).save(any(TechnicalProcess.class));
	}
}
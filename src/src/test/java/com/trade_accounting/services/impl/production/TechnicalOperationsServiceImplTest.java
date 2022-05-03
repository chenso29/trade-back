package src.test.java.com.trade_accounting.services.impl.production;

import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.models.dto.production.TechnicalOperationsDto;
import com.trade_accounting.models.entity.production.TechnicalOperations;
import com.trade_accounting.repositories.company.CompanyRepository;
import com.trade_accounting.repositories.production.TechnicalCardRepository;
import com.trade_accounting.repositories.production.TechnicalOperationsRepository;
import com.trade_accounting.repositories.warehouse.WarehouseRepository;
import com.trade_accounting.utils.mapper.production.TechnicalOperationsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TechnicalOperationsServiceImplTest {
	
	@Mock
	private TechnicalOperationsRepository technicalOperationsRepository;
	
	@Mock
	private TechnicalOperationsMapper technicalOperationsMapper;
	
	@Mock
	private TechnicalCardRepository technicalCardRepository;
	
	@Mock
	private WarehouseRepository warehouseRepository;
	
	@Mock
	private CompanyRepository companyRepository;
	
	@InjectMocks
	private TechnicalOperationsServiceImpl technicalOperationsServiceImpl;
	
	@Test
	void getAll() {
		when(technicalOperationsRepository.findAll()).thenReturn(List.of(ModelStubs.getTechnicalOperations(1L), ModelStubs.getTechnicalOperations(2L), ModelStubs.getTechnicalOperations(3L)));
		List<TechnicalOperationsDto> technicalOperationsDtoList = technicalOperationsServiceImpl.getAll();
		assertEquals(3, technicalOperationsDtoList.size());
	}
	
	@Test
	void getById() {
		when(technicalOperationsRepository.getOne(anyLong())).thenReturn(ModelStubs.getTechnicalOperations(1L));
		
		TechnicalOperationsDto technicalOperationsDto = technicalOperationsServiceImpl.getById(1L);
		assertEquals(1, technicalOperationsDto.getId());
	}
	
	@Test
	public void create() {
		saveOrUpdate();
	}
	
	@Test
	public void update() {
		saveOrUpdate();
	}
	
	@Test
	void deleteById() {
		technicalOperationsServiceImpl.deleteById(anyLong());
		verify(technicalOperationsRepository).deleteById(anyLong());
	}
	
	@Test
	void search() {
	
	}
	
	@Test
	void moveToRecyclebin() {
		when(technicalOperationsRepository.getOne(anyLong())).thenReturn(ModelStubs.getTechnicalOperations(1L));
		technicalOperationsServiceImpl.moveToRecyclebin(1);
		assertEquals(true, technicalOperationsRepository.getOne(anyLong()).getIsRecyclebin());
		verify(technicalOperationsRepository).save(ModelStubs.getTechnicalOperations(1L));
	}
	
	@Test
	void restoreFromRecyclebin() {
		when(technicalOperationsRepository.getOne(anyLong())).thenReturn(ModelStubs.getTechnicalOperations(1L));
		technicalOperationsServiceImpl.restoreFromRecyclebin(1);
		assertEquals(false, technicalOperationsRepository.getOne(anyLong()).getIsRecyclebin());
		verify(technicalOperationsRepository).save(ModelStubs.getTechnicalOperations(1L));
	}
	
	private void saveOrUpdate() {
		when(technicalOperationsRepository.save(any(TechnicalOperations.class))).thenReturn(ModelStubs.getTechnicalOperations(1L));
		
		TechnicalOperationsDto technicalOperationsDto = technicalOperationsServiceImpl.create(Mappers.getMapper(TechnicalOperationsMapper.class).toDto(ModelStubs.getTechnicalOperations(1L)));
		
		assertEquals(1, technicalOperationsDto.getId());
		verify(technicalOperationsRepository).save(any(TechnicalOperations.class));
	}
}
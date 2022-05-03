package src.test.java.com.trade_accounting.services.impl.fias;


import com.trade_accounting.models.dto.fias.FiasAddressModelDto;
import com.trade_accounting.models.entity.fias.FiasAddressModel;
import com.trade_accounting.repositories.fias.AddressDbRepository;
import com.trade_accounting.Stubs.ModelStubs;
import com.trade_accounting.Stubs.dto.fias.FiasAddressModelDtoStubs;
import com.trade_accounting.utils.mapper.fias.FiasAddressModelMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FiasDbServiceTest {

    @Mock
    AddressDbRepository repository;

    @Spy
    FiasAddressModelMapper fiasAddressModelMapper;

    @InjectMocks
    FiasDbServiceImpl service;

    @Test
    public void getAllTest() {
        when(repository.findAll()).thenReturn(
                Stream.of(
                        ModelStubs.getFiasAddressModel(1L),
                        ModelStubs.getFiasAddressModel(2L),
                        ModelStubs.getFiasAddressModel(3L)
                ).collect(Collectors.toList())
        );
        List<FiasAddressModelDto> resultList = service.getAll();
        assertNotNull(resultList, "failure - expected that resultList is not null");
        assertEquals(3, resultList.size(), "failure - expected that size is 3");
        assertTrue(resultList.size() > 0, "failure - expected that a size of list greater than 0");

        for (FiasAddressModelDto item : resultList) {
            addressModelDtoIsCorrectlyInited(item);
        }
    }

    @Test
    public void getByIdTest() {
        Optional<FiasAddressModel> addressModelFromRepo = Optional.of(ModelStubs.getFiasAddressModel(1L));
        when(repository.findById(anyLong())).thenReturn(addressModelFromRepo);
        FiasAddressModelDto result = service.getById(1L);
        assertNotNull(result, "failure - expected that a AddressModel not null");
        addressModelDtoIsCorrectlyInited(result);
    }

    @Test
    public void createTest() {
        when(repository.save(any())).thenReturn(ModelStubs.getFiasAddressModel(1L));
        FiasAddressModelDto result = service.create(FiasAddressModelDtoStubs.getFiasAddressModelDto(1L));
        verify(repository).save(any(FiasAddressModel.class));
        assertNotNull(result, "failure - expected that a StreetDto not null");
        assertEquals(result, FiasAddressModelDtoStubs.getFiasAddressModelDto(1L));
        addressModelDtoIsCorrectlyInited(result);
    }

    @Test
    public void updateTest() {
        when(repository.save(any())).thenReturn(ModelStubs.getFiasAddressModel(1L));
        FiasAddressModelDto result = service.update(FiasAddressModelDtoStubs.getFiasAddressModelDto(1L));
        verify(repository).save(any(FiasAddressModel.class));
        assertNotNull(result, "failure - expected that a StreetDto not null");
        assertEquals(result, FiasAddressModelDtoStubs.getFiasAddressModelDto(1L));
        addressModelDtoIsCorrectlyInited(result);
    }

    @Test
    public void deleteByIdTest() {
        service.deleteById(1L);
        verify(repository).deleteById(anyLong());
    }

    @Test
    public void findAllByLevelTest() {
        when(repository.findAllByLevel(anyString())).thenReturn(
                Stream.of(
                        ModelStubs.getFiasAddressModel(1L),
                        ModelStubs.getFiasAddressModel(2L),
                        ModelStubs.getFiasAddressModel(3L)
                ).collect(Collectors.toList())
        );
        List<FiasAddressModelDto> resultList = service.findAllByLevel("HelloWorld");
        assertNotNull(resultList, "failure - expected that resultList is not null");
        assertEquals(3, resultList.size(), "failure - expected that size is 3");
        assertTrue(resultList.size() > 0, "failure - expected that a size of list greater than 0");
        for (FiasAddressModelDto item : resultList) {
            addressModelDtoIsCorrectlyInited(item);
        }
    }

    @Test
    public void findAllByAoguid() {
        when(repository.findAdressesByAoguid(anyString())).thenReturn(
                Stream.of(
                        ModelStubs.getFiasAddressModel(1L),
                        ModelStubs.getFiasAddressModel(2L),
                        ModelStubs.getFiasAddressModel(3L)
                ).collect(Collectors.toList())
        );
        List<FiasAddressModelDto> resultList = service.findAllByAoguid("HelloWorld");
        assertNotNull(resultList, "failure - expected that resultList is not null");
        assertEquals(3, resultList.size(), "failure - expected that size is 3");
        assertTrue(resultList.size() > 0, "failure - expected that a size of list greater than 0");
        for (FiasAddressModelDto item : resultList) {
            addressModelDtoIsCorrectlyInited(item);
        }
    }

    private void addressModelDtoIsCorrectlyInited(FiasAddressModelDto addressModelDto) {
        assertNotNull(addressModelDto, "failure - fail in passed addressModelDto");
        assertNotNull(addressModelDto.getId(), "failure - fail in field 'id' into addressModelDto");
        assertNotNull(addressModelDto.getAoguid(), "failure - fail in field 'aoguid' into addressModelDto");
        assertNotNull(addressModelDto.getAolevel(), "failure - fail in field 'aolevel' into addressModelDto");
        assertNotNull(addressModelDto.getFormalname(), "failure - fail in field 'formalName' into addressModelDto");
        assertNotNull(addressModelDto.getShortname(), "failure - fail in field 'shortName' into addressModelDto");
        assertNotNull(addressModelDto.getParentguid(), "failure - fail in field 'parentguid' into addressModelDto");
    }
}

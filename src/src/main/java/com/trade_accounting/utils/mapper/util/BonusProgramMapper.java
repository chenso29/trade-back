package src.main.java.com.trade_accounting.utils.mapper.util;


import com.trade_accounting.models.entity.util.BonusProgram;
import com.trade_accounting.models.entity.company.ContractorGroup;
import com.trade_accounting.models.dto.util.BonusProgramDto;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

/**
 * @author Ivanov Daniil
 * @version 1.0.0
 */

@Mapper(componentModel = "spring")
public interface BonusProgramMapper {

    /**
     * @return BonusProgram
     */
    default BonusProgram toModel(BonusProgramDto bonusProgramDto) {
        if (bonusProgramDto == null) {
            return null;
        }

        return BonusProgram.builder()
                .id(bonusProgramDto.getId())
                .name(bonusProgramDto.getName())
                .activeStatus(bonusProgramDto.getActiveStatus())
                .allContractors(bonusProgramDto.getAllContractors())
                .accrualRule(bonusProgramDto.getAccrualRule())
                .writeOffRules(bonusProgramDto.getWriteOffRules())
                .maxPaymentPercentage(bonusProgramDto.getMaxPaymentPercentage())
                .numberOfDays(bonusProgramDto.getNumberOfDays())
                .welcomePoints(bonusProgramDto.getWelcomePoints())
                .numberOfPoints(bonusProgramDto.getNumberOfPoints())
                .registrationInBonusProgram(bonusProgramDto.getRegistrationInBonusProgram())
                .firstPurchase(bonusProgramDto.getFirstPurchase())
                .build();
    }

    /**
     * @return BonusProgramDto
     */

    default BonusProgramDto toDto(BonusProgram bonusProgram) {
        BonusProgramDto bonusProgramDto = new BonusProgramDto();
        if (bonusProgram == null) {
            return null;
        } else {
            bonusProgramDto.setId(bonusProgram.getId());
            bonusProgramDto.setName(bonusProgram.getName());
            bonusProgramDto.setActiveStatus(bonusProgram.getActiveStatus());
            bonusProgramDto.setAllContractors(bonusProgram.getAllContractors());
            bonusProgramDto.setContractorGroupIds(
                 bonusProgram.getContractorGroups().stream()
                         .map(ContractorGroup::getId)
                         .collect(Collectors.toList())
            );
            bonusProgramDto.setAccrualRule(bonusProgram.getAccrualRule());
            bonusProgramDto.setWriteOffRules(bonusProgram.getWriteOffRules());
            bonusProgramDto.setMaxPaymentPercentage(bonusProgram.getMaxPaymentPercentage());
            bonusProgramDto.setNumberOfDays(bonusProgram.getNumberOfDays());
            bonusProgramDto.setWelcomePoints(bonusProgram.getWelcomePoints());
            bonusProgramDto.setNumberOfPoints(bonusProgram.getNumberOfPoints());
            bonusProgramDto.setRegistrationInBonusProgram(bonusProgram.getRegistrationInBonusProgram());
            bonusProgramDto.setFirstPurchase(bonusProgram.getFirstPurchase());

            return bonusProgramDto;
        }
    }
}

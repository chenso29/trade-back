package src.main.java.com.trade_accounting.services.impl.finance;

import com.trade_accounting.models.dto.finance.MoneySubCashFlowDto;
import com.trade_accounting.models.dto.finance.PaymentDto;
import com.trade_accounting.services.interfaces.finance.MoneySubCashFlowService;
import com.trade_accounting.services.interfaces.finance.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class MoneySubCashFlowServiceImpl implements MoneySubCashFlowService {

    private final PaymentService paymentService;

    public MoneySubCashFlowServiceImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public List<MoneySubCashFlowDto> getAll() {
        List<MoneySubCashFlowDto> moneySubCashFlowDtoList = new ArrayList<>();
        BigDecimal cashComing;
        BigDecimal cashExpense;
        BigDecimal cashBalance;
        BigDecimal bankComing;
        BigDecimal bankExpense;
        BigDecimal bankBalance;
        BigDecimal finalCashComing;
        BigDecimal finalCashExpense;
        BigDecimal finalCashBalance;
        BigDecimal finalBankComing;
        BigDecimal finalBankExpense;
        BigDecimal finalBankBalance;
        Long count = 0L;

        //Создание начального остатка
        moneySubCashFlowDtoList.add(new MoneySubCashFlowDto(count++,"Начальный остаток",BigDecimal.ZERO,BigDecimal.ZERO
                ,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO));

        //Получение всех платежей, которые имеют статус "Проведён"
        List<PaymentDto> paymentDtos = paymentService.getAll().stream()
                .filter(PaymentDto::isConducted).collect(Collectors.toList());

        //Получение Листа с датами платежей
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<LocalDate> date = paymentDtos.stream().map(c -> LocalDate.parse(c.getTime(), formatter)).distinct().sorted().collect(Collectors.toList());
        //Добавление Платежей внутри одной даты
        for (LocalDate i:date) {
        //Наличные
            List<PaymentDto> cashComingPaymentDto = paymentDtos.stream()
                    .filter(t -> LocalDate.parse(t.getTime(), formatter).equals(i))
                    .filter(a -> a.getPaymentMethods().equals("CASH"))
                    .filter(c -> c.getTypeOfPayment().equals("INCOMING")).collect(Collectors.toList());
            if (cashComingPaymentDto.isEmpty()) {
                cashComing = BigDecimal.ZERO;
            } else {
                cashComing = cashComingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
            }
            List<PaymentDto> cashExpensePaymentDto = paymentDtos.stream()
                    .filter(t -> LocalDate.parse(t.getTime(), formatter).equals(i))
                    .filter(a -> a.getPaymentMethods().equals("CASH"))
                    .filter(c -> c.getTypeOfPayment().equals("OUTGOING")).collect(Collectors.toList());
            if (cashExpensePaymentDto.isEmpty()) {
                cashExpense = BigDecimal.ZERO;
            } else {
                cashExpense = cashExpensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
            }
            cashBalance = cashComing.subtract(cashExpense);

            //Безнал
            List<PaymentDto> bankComingPaymentDto = paymentDtos.stream()
                    .filter(t -> LocalDate.parse(t.getTime(), formatter).equals(i))
                    .filter(a -> a.getPaymentMethods().equals("BANK"))
                    .filter(c -> c.getTypeOfPayment().equals("INCOMING")).collect(Collectors.toList());
            if (bankComingPaymentDto.isEmpty()) {
                bankComing = BigDecimal.ZERO;
            } else {
                bankComing = bankComingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
            }
            List<PaymentDto> bankExpensePaymentDto = paymentDtos.stream()
                    .filter(t -> LocalDate.parse(t.getTime(), formatter).equals(i))
                    .filter(a -> a.getPaymentMethods().equals("BANK"))
                    .filter(c -> c.getTypeOfPayment().equals("OUTGOING")).collect(Collectors.toList());
            if (bankExpensePaymentDto.isEmpty()) {
                bankExpense = BigDecimal.ZERO;
            } else {
                bankExpense = bankExpensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
            }
            bankBalance = bankComing.subtract(bankExpense);

            BigDecimal allComing = cashComing.add(bankComing);
            BigDecimal allExpense = cashExpense.add(bankExpense);
            BigDecimal allBalance = cashBalance.add(bankBalance);

            MoneySubCashFlowDto moneySubCashFlowDto = new MoneySubCashFlowDto(count++,
                    i.toString(),bankComing, bankExpense, bankBalance,
                    cashComing, cashExpense, cashBalance, allComing, allExpense, allBalance);

            moneySubCashFlowDtoList.add(moneySubCashFlowDto);
        }

        //Получение конечного остатка
        //Наличные
        List<PaymentDto> cashComingPaymentDto = paymentDtos.stream()
                .filter(a -> a.getPaymentMethods().equals("CASH"))
                .filter(c -> c.getTypeOfPayment().equals("INCOMING")).collect(Collectors.toList());
        if (cashComingPaymentDto.isEmpty()) {
            finalCashComing = BigDecimal.ZERO;
        } else {
            finalCashComing = cashComingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        List<PaymentDto> cashExpensePaymentDto = paymentDtos.stream()
                .filter(a -> a.getPaymentMethods().equals("CASH"))
                .filter(c -> c.getTypeOfPayment().equals("OUTGOING")).collect(Collectors.toList());
        if (cashExpensePaymentDto.isEmpty()) {
            finalCashExpense = BigDecimal.ZERO;
        } else {
            finalCashExpense = cashExpensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        finalCashBalance = finalCashComing.subtract(finalCashExpense);

        //Безнал
        List<PaymentDto> bankComingPaymentDto = paymentDtos.stream()
                .filter(a -> a.getPaymentMethods().equals("BANK"))
                .filter(c -> c.getTypeOfPayment().equals("INCOMING")).collect(Collectors.toList());
        if (bankComingPaymentDto.isEmpty()) {
            finalBankComing = BigDecimal.ZERO;
        } else {
            finalBankComing = bankComingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        List<PaymentDto> bankExpensePaymentDto = paymentDtos.stream()
                .filter(a -> a.getPaymentMethods().equals("BANK"))
                .filter(c -> c.getTypeOfPayment().equals("OUTGOING")).collect(Collectors.toList());
        if (bankExpensePaymentDto.isEmpty()) {
            finalBankExpense = BigDecimal.ZERO;
        } else {
            finalBankExpense = bankExpensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        finalBankBalance = finalBankComing.subtract(finalBankExpense);

        BigDecimal finalAllComing = finalCashComing.add(finalBankComing);
        BigDecimal finalAllExpense = finalCashExpense.add(finalBankExpense);
        BigDecimal finalAllBalance = finalCashBalance.add(finalBankBalance);

        moneySubCashFlowDtoList.add(new MoneySubCashFlowDto(count,"Конечный остаток",finalBankComing,finalBankExpense,
                finalBankBalance,finalCashComing,finalCashExpense,finalCashBalance,finalAllComing,finalAllExpense,finalAllBalance));

        return moneySubCashFlowDtoList;
    }

    @Override
    public List<MoneySubCashFlowDto> filter(LocalDate startDatePeriod, LocalDate endDatePeriod, Long projectId, Long companyId, Long contractorId) {
        List<MoneySubCashFlowDto> moneySubCashFlowDtoList = new ArrayList<>();
        BigDecimal cashComing;
        BigDecimal cashExpense;
        BigDecimal cashBalance;
        BigDecimal bankComing;
        BigDecimal bankExpense;
        BigDecimal bankBalance;
        BigDecimal finalCashComing;
        BigDecimal finalCashExpense;
        BigDecimal finalCashBalance;
        BigDecimal finalBankComing;
        BigDecimal finalBankExpense;
        BigDecimal finalBankBalance;
        Long count = 0L;

        //Создание начального остатка
        moneySubCashFlowDtoList.add(new MoneySubCashFlowDto(count++,"Начальный остаток",BigDecimal.ZERO,BigDecimal.ZERO
                ,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO));

        //Получение всех платежей, которые имеют статус "Проведён"
        List<PaymentDto> paymentDtos = paymentService.getAll().stream()
                .filter(PaymentDto::isConducted).collect(Collectors.toList());

        paymentDtos = paymentDtos.stream().filter(c -> projectId == null || c.getProjectId() == projectId)
                .filter(c -> companyId == null || c.getCompanyId() == companyId)
                .filter(c -> contractorId == null || c.getContractorId() == contractorId)
                .collect(Collectors.toList());

        //Получение Листа с датами платежей
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<LocalDate> date = paymentDtos.stream().peek(x -> System.out.println(x.getTime())).map(c -> LocalDate.parse(c.getTime(), formatter)).distinct()
                .peek(x -> System.out.println(x.toString() + startDatePeriod + startDatePeriod))
                .filter(t -> startDatePeriod == null || (t.isEqual(startDatePeriod) || t.isAfter(startDatePeriod)))
                .filter(t -> endDatePeriod == null || (t.isEqual(endDatePeriod) || t.isBefore(endDatePeriod)))
                .sorted().collect(Collectors.toList());
        //Добавление Платежей внутри одной даты
        for (LocalDate i:date) {
            //Наличные
            List<PaymentDto> cashComingPaymentDto = paymentDtos.stream()
                    .filter(t -> LocalDate.parse(t.getTime(), formatter).isEqual(i))
                    .filter(a -> a.getPaymentMethods().equals("CASH"))
                    .filter(c -> c.getTypeOfPayment().equals("INCOMING")).collect(Collectors.toList());
            if (cashComingPaymentDto.isEmpty()) {
                cashComing = BigDecimal.ZERO;
            } else {
                cashComing = cashComingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
            }
            List<PaymentDto> cashExpensePaymentDto = paymentDtos.stream()
                    .filter(t -> LocalDate.parse(t.getTime(), formatter).isEqual(i))
                    .filter(a -> a.getPaymentMethods().equals("CASH"))
                    .filter(c -> c.getTypeOfPayment().equals("OUTGOING")).collect(Collectors.toList());
            if (cashExpensePaymentDto.isEmpty()) {
                cashExpense = BigDecimal.ZERO;
            } else {
                cashExpense = cashExpensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
            }
            cashBalance = cashComing.subtract(cashExpense);

            //Безнал
            List<PaymentDto> bankComingPaymentDto = paymentDtos.stream()
                    .filter(t -> LocalDate.parse(t.getTime(), formatter).isEqual(i))
                    .filter(a -> a.getPaymentMethods().equals("BANK"))
                    .filter(c -> c.getTypeOfPayment().equals("INCOMING")).collect(Collectors.toList());
            if (bankComingPaymentDto.isEmpty()) {
                bankComing = BigDecimal.ZERO;
            } else {
                bankComing = bankComingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
            }
            List<PaymentDto> bankExpensePaymentDto = paymentDtos.stream()
                    .filter(t -> LocalDate.parse(t.getTime(), formatter).isEqual(i))
                    .filter(a -> a.getPaymentMethods().equals("BANK"))
                    .filter(c -> c.getTypeOfPayment().equals("OUTGOING")).collect(Collectors.toList());
            if (bankExpensePaymentDto.isEmpty()) {
                bankExpense = BigDecimal.ZERO;
            } else {
                bankExpense = bankExpensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
            }
            bankBalance = bankComing.subtract(bankExpense);

            BigDecimal allComing = cashComing.add(bankComing);
            BigDecimal allExpense = cashExpense.add(bankExpense);
            BigDecimal allBalance = cashBalance.add(bankBalance);

            MoneySubCashFlowDto moneySubCashFlowDto = new MoneySubCashFlowDto(count++,
                    i.toString(),bankComing, bankExpense, bankBalance,
                    cashComing, cashExpense, cashBalance, allComing, allExpense, allBalance);

            moneySubCashFlowDtoList.add(moneySubCashFlowDto);
        }

        //Получение конечного остатка
        //Наличные
        List<PaymentDto> cashComingPaymentDto = paymentDtos.stream()
                .filter(a -> a.getPaymentMethods().equals("CASH"))
                .filter(c -> c.getTypeOfPayment().equals("INCOMING")).collect(Collectors.toList());
        if (cashComingPaymentDto.isEmpty()) {
            finalCashComing = BigDecimal.ZERO;
        } else {
            finalCashComing = cashComingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        List<PaymentDto> cashExpensePaymentDto = paymentDtos.stream()
                .filter(a -> a.getPaymentMethods().equals("CASH"))
                .filter(c -> c.getTypeOfPayment().equals("OUTGOING")).collect(Collectors.toList());
        if (cashExpensePaymentDto.isEmpty()) {
            finalCashExpense = BigDecimal.ZERO;
        } else {
            finalCashExpense = cashExpensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        finalCashBalance = finalCashComing.subtract(finalCashExpense);

        //Безнал
        List<PaymentDto> bankComingPaymentDto = paymentDtos.stream()
                .filter(a -> a.getPaymentMethods().equals("BANK"))
                .filter(c -> c.getTypeOfPayment().equals("INCOMING")).collect(Collectors.toList());
        if (bankComingPaymentDto.isEmpty()) {
            finalBankComing = BigDecimal.ZERO;
        } else {
            finalBankComing = bankComingPaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        List<PaymentDto> bankExpensePaymentDto = paymentDtos.stream()
                .filter(a -> a.getPaymentMethods().equals("BANK"))
                .filter(c -> c.getTypeOfPayment().equals("OUTGOING")).collect(Collectors.toList());
        if (bankExpensePaymentDto.isEmpty()) {
            finalBankExpense = BigDecimal.ZERO;
        } else {
            finalBankExpense = bankExpensePaymentDto.stream().map(PaymentDto::getSum).reduce(BigDecimal::add).get();
        }
        finalBankBalance = finalBankComing.subtract(finalBankExpense);

        BigDecimal finalAllComing = finalCashComing.add(finalBankComing);
        BigDecimal finalAllExpense = finalCashExpense.add(finalBankExpense);
        BigDecimal finalAllBalance = finalCashBalance.add(finalBankBalance);

        moneySubCashFlowDtoList.add(new MoneySubCashFlowDto(count,"Конечный остаток",finalBankComing,finalBankExpense,
                finalBankBalance,finalCashComing,finalCashExpense,finalCashBalance,finalAllComing,finalAllExpense,finalAllBalance));

        return moneySubCashFlowDtoList;
    }
}

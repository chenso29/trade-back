package src.main.java.com.trade_accounting.aspects;


import org.aspectj.lang.annotation.Pointcut;

public abstract class ServiceLayerAspect {
    @Pointcut("within(com.trade_accounting.services..*)")
    public void inServiceLayer() {
    }

    @Pointcut("execution(* getAll*(..))")
    public void getAllExecution() {
    }

    @Pointcut("execution(* getBy*(..))")
    public void getByExecution() {
    }

    @Pointcut("execution(* create(..))")
    public void createExecution() {
    }

    @Pointcut("execution(* update(..))")
    public void updateExecution() {
    }

    @Pointcut("execution(* deleteById(..))")
    public void deleteExecution() {
    }

    @Pointcut("execution(* search*(..))")
    public void searchExecution() {
    }
}

package sci.getenterprisesgia.Model.Entities;

import java.math.BigDecimal;

public class GpsConfiguration {
    private final BigDecimal hundred = new BigDecimal(100).setScale(2);
    
    private Integer enterpriseCode = 0 ;
    private BigDecimal thirdPercent = BigDecimal.ZERO;

    private BigDecimal employersPercent = BigDecimal.ZERO;
    private BigDecimal collaboratorPercent = BigDecimal.ZERO;
    private BigDecimal autonomousPercent = BigDecimal.ZERO;

    public Integer getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(Integer enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public BigDecimal getThirdPercent() {
        return thirdPercent;
    }

    /**
     * Define Value dividing by 100
     */
    public void setThirdPercent(BigDecimal thirdPercent) {
        this.thirdPercent = thirdPercent.setScale(2).divide(hundred);
    }

    public BigDecimal getEmployersPercent() {
        return employersPercent;
    }

    /**
     * Define Value dividing by 100
     */
    public void setEmployersPercent(BigDecimal employersPercent) {
        this.employersPercent = employersPercent.setScale(2).divide(hundred);
    }

    public BigDecimal getCollaboratorPercent() {
        return collaboratorPercent;
    }

    /**
     * Define Value dividing by 100
     */
    public void setCollaboratorPercent(BigDecimal collaboratorPercent) {
        this.collaboratorPercent = collaboratorPercent.setScale(2).divide(hundred);
    }

    public BigDecimal getAutonomousPercent() {
        return autonomousPercent;
    }

    /**
     * Define Value dividing by 100
     */
    public void setAutonomousPercent(BigDecimal autonomousPercent) {
        this.autonomousPercent = autonomousPercent.setScale(2).divide(hundred);
    }

}

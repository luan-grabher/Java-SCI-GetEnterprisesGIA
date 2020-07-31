package sci.getenterprisesgia.Model.Entities;

import java.math.BigDecimal;

public class InssBases {
    private Integer enterpriseCode = 0;
    private Integer quantity = 0 ;
    private String type = "";
    private BigDecimal baseInss = BigDecimal.ZERO;
    private BigDecimal baseInss13 = BigDecimal.ZERO;

    public Integer getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(Integer enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getBaseInss() {
        return baseInss;
    }

    public void setBaseInss(BigDecimal baseInss) {
        this.baseInss = baseInss;
    }

    public BigDecimal getBaseInss13() {
        return baseInss13;
    }

    public void setBaseInss13(BigDecimal baseInss13) {
        this.baseInss13 = baseInss13;
    }
    
    
    
}

package com.wd.doctor.common.bean;

public class DoctorBankBean {
    private String bankCardNumber;
    private String bankName;
    private int bankCardType ;

    @Override
    public String toString() {
        return "DoctorBankBean{" +
                "bankCardNumber='" + bankCardNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankCardType=" + bankCardType +
                '}';
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(int bankCardType) {
        this.bankCardType = bankCardType;
    }

    public DoctorBankBean(String bankCardNumber, String bankName, int bankCardType) {
        this.bankCardNumber = bankCardNumber;
        this.bankName = bankName;
        this.bankCardType = bankCardType;
    }
}

package com.wd.doctor.common.bean;

public class BankCardBean {
    private String bankCardNumber; //银行卡号
    private String bankName;//银行名称
    private  int bankCardType; //银行卡类型1: 借记卡; 2: 信用卡

    @Override
    public String toString() {
        return "BankCardBean{" +
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

    public BankCardBean(String bankCardNumber, String bankName, int bankCardType) {
        this.bankCardNumber = bankCardNumber;
        this.bankName = bankName;
        this.bankCardType = bankCardType;
    }
}

package com.api.systemaccount.transaction.enums;

public enum TransactionType {

        DEPOSIT(1, "DEPOSITO"),
        WITHDRAW(2, "SAQUE");

        private int cod;
        private String desc;

        private TransactionType(int cod, String desc) {
            this.cod = cod;
            this.desc = desc;
        }

        public int getCode() {
            return cod;
        }

        public String getDesc() {
            return desc;
        }
}

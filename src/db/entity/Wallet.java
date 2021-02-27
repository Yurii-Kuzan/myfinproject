package db.entity;

import java.util.Objects;

public class Wallet {
    private int wallet;
    private int addMoney;
    public Wallet(int wallet,int addMoney)
    {
        this.wallet=wallet;
        this.addMoney=addMoney;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getWallet() {
        return wallet;
    }

    public void setAddMoney(int addMoney) {
        this.addMoney = addMoney;
    }

    public int getAddMoney() {
        return addMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet1 = (Wallet) o;
        return wallet == wallet1.wallet &&
                addMoney == wallet1.addMoney;
    }


    @Override
    public int hashCode() {
        return Objects.hash(wallet, addMoney);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "wallet=" + wallet +
                ", addMoney=" + addMoney +
                '}';
    }
}

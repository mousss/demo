package org.boufnichel.demo.services.impl;

import org.boufnichel.demo.model.CoinMachine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.script.ScriptException;

import static org.junit.jupiter.api.Assertions.*;

class CoinMachineServiceImplTest {
    private CoinMachine coinMachine;

    @BeforeEach
    void setup() {
        coinMachine = new CoinMachine();
    }
    @Test
    void should_init_coin_machine() throws ScriptException {
        coinMachine.putMoneyInside("100x50");
        double total = coinMachine.totalMoney();
        assertEquals(5000, total);
    }
    @Test
    void should_init_coin_machine_with_multiple_deposit() throws ScriptException {
        coinMachine.putMoneyInside("100x50");
        coinMachine.putMoneyInside("50x20");
        double total = coinMachine.totalMoney();
        assertEquals(6000, total);
    }

    @Test
    void should_check_exact_money_remaining() throws ScriptException {
        coinMachine.putMoneyInside("100x50");
        coinMachine.putMoneyInside("50x20");
        assertEquals(100, coinMachine.check(50));
        assertEquals(50, coinMachine.check(20));
    }


    @Test
    void should_get_money() throws ScriptException {
        coinMachine.putMoneyInside("100x50");
        coinMachine.putMoneyInside("50x20");
        coinMachine.getMoney(140);
        assertEquals(98, coinMachine.check(50));
        assertEquals(48, coinMachine.check(20));
    }



}
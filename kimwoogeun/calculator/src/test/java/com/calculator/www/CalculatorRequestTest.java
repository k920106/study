package com.calculator.www;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculatorRequestTest {

    @Test
    public void 유효한_숫자를_파싱할_수_있다() {
        // given
        String[] parts = new String[]{"2", "+", "3"};

        // when
        CalculatorRequest calculatorRequest = new CalculatorRequest(parts);

        // then
        assertEquals(2, calculatorRequest.getNum1());
        assertEquals("+", calculatorRequest.getOperator());
        assertEquals(3, calculatorRequest.getNum2());
    }

    @Test
    public void 세자리_숫자가_넘어가는_유효한_숫자를_파싱할_수_있다() {
        // given
        String[] parts = new String[]{"234", "+", "345"};

        // when
        CalculatorRequest calculatorRequest = new CalculatorRequest(parts);

        // then
        assertEquals(234, calculatorRequest.getNum1());
        assertEquals("+", calculatorRequest.getOperator());
        assertEquals(345, calculatorRequest.getNum2());
    }

    @Test
    public void 유효한_길이의_숫자가_들어오지_않으면_에러를_던진다() {
        // given
        String[] parts = new String[]{"234", "+"};

        // when

        // then
        assertThrows(BadRequestException.class, () -> new CalculatorRequest(parts));
    }

    @Test
    public void 유효하지_않은_연산자가_들어오면_에러를_던진다() {
        // given
        String[] parts = new String[]{"2", "x", "3"};

        // when

        // then
        assertThrows(InvalidOperatorException.class, () -> new CalculatorRequest(parts));
    }

    @Test
    public void 유효하지_않은_길이의_연산자가_들어오면_에러를_던진다() {
        // given
        String[] parts = new String[]{"2", "+-", "3"};

        // when

        // then
        assertThrows(InvalidOperatorException.class, () -> new CalculatorRequest(parts));
    }
}

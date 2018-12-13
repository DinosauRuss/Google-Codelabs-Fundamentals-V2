/*
 * Copyright 2018, Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.SimpleCalc;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4.class)
@SmallTest
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     */
    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(1d, 1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

    @Test
    public void addTwoNumbersNegative() {
        double reslutAdd = mCalculator.add(2d, -1d);
        assertThat(reslutAdd, is(equalTo(1d)));
    }

    @Test
    public void addTwoNumbersFloatingPoint() {
        double result = mCalculator.add(1.11d, 1.11f);
        assertThat(result, is(closeTo(2.22, .01)));
    }

    @Test
    public void subTwoNumbers() {
        double result = mCalculator.sub(8d, 6d);
        assertThat(result, is(equalTo(2d)));
    }

    @Test
    public void subTwoNumbersNegative() {
        double result = mCalculator.sub(-8d, 6d);
        assertThat(result, is(equalTo(-14d)));
    }

    @Test
    public void mulTwoNumbers() {
        double result = mCalculator.mul(4d, 5d);
        assertThat(result, is(equalTo(20d)));
    }

    @Test
    public void mulTwoNumbersZero() {
        double result = mCalculator.mul(0d, 5d);
        assertThat(result, is(equalTo(0d)));
    }

    @Test
    public void divTwoNumbers() {
        double result = mCalculator.div(8d, 4d);
        assertThat(result, is(equalTo(2d)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void divTwoNumbersZero() {
        double result = mCalculator.div(8d, 0);
    }


}
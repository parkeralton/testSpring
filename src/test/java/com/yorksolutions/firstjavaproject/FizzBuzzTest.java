package com.yorksolutions.firstjavaproject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {
//Test Driven Design
    @Test
    void itShouldOutputAStringVersionOfTheNumberInput() {
        final String actual = new FizzBuzz().fizzbuzz(4);
        assertEquals("4", actual);
    }

    @Test
    void itShouldOutputFizzWhenTheInputIsAMultipleOf3() {
        final String actual = new FizzBuzz().fizzbuzz(3);
        assertEquals("Fizz", actual);
        final String actual6 = new FizzBuzz().fizzbuzz(6);
        assertEquals("Fizz", actual6);
    }

    @Test
    void itShouldOutputBuzzWhenTheInputIsAMultipleOf5() {
        final String actual = new FizzBuzz().fizzbuzz(5);
        assertEquals("Buzz", actual);
        final String actual10 = new FizzBuzz().fizzbuzz(10);
        assertEquals("Buzz", actual10);
    }

    @Test
    void itShouldOutputBuzzBuzzWhenTheInputIsAMultipleOf3and5() {
        final String actual = new FizzBuzz().fizzbuzz(15);
        assertEquals("FizzBuzz", actual);
        final String actual30 = new FizzBuzz().fizzbuzz(30);
        assertEquals("FizzBuzz", actual30);
    }
}
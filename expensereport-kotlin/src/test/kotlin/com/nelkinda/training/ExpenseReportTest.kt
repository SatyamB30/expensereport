package com.nelkinda.training

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ExpenseReportTest {


    @Test
    fun `should calculate total expense and meal expense for dinner and breakfast`() {
        //Arrange˳
        val expected = "Expenses Fri Feb 10 11:10:59 IST 2023\n" +
                "Dinner\t100\t \n" +
                "Breakfast\t200\t \n" +
                "Meal expenses: 300\n" +
                "Total expenses: 300\n"

        val dinnerExpense = createExpense(ExpenseType.DINNER, 100)
        val lunchExpense = createExpense(ExpenseType.BREAKFAST, 200)
        val expenses = arrayListOf(dinnerExpense, lunchExpense)

        val outputFromConsole = ByteArrayOutputStream()
        val ps = PrintStream(outputFromConsole)
        System.setOut(ps)
//        ACT
        ExpenseReport().printReport(expenses)
        val result = outputFromConsole.toString()
        val actualReportStatement = result.split("\n")
        val expectedReportStatement = expected.split("\n")

        performAssert(actualReportStatement, expectedReportStatement)

    }

    @Test
    fun `should calculate expenses for car rental and for breakfast`() {

        //Arrange
        val carRentalExpense = createExpense(ExpenseType.CAR_RENTAL, 1000)
        val breakfastExpense = createExpense(ExpenseType.BREAKFAST, 200)
        val expenses = arrayListOf(carRentalExpense, breakfastExpense)
        val expected = "Expenses Fri Feb 10 11:10:59 IST 2023\n" +
                "Car Rental\t1000\t \n" +
                "Breakfast\t200\t \n" +
                "Meal expenses: 200\n" +
                "Total expenses: 1200\n"

        val outputFromConsole = ByteArrayOutputStream()
        val ps = PrintStream(outputFromConsole)
        System.setOut(ps)
//        ACT
        ExpenseReport().printReport(expenses)
        val result = outputFromConsole.toString()
        val actualReportStatement = result.split("\n")
        val expectedReportStatement = expected.split("\n")

        performAssert(actualReportStatement, expectedReportStatement)
    }

    @Test
    fun `calculate the proper message dinner greater than threshold`() {
        //Arrange
        val carRentalExpense = createExpense(ExpenseType.CAR_RENTAL, 1000)
        val dinnerExpense = createExpense(ExpenseType.DINNER, 5100)
        val expenses = arrayListOf(carRentalExpense, dinnerExpense)
        val expected = "Expenses Fri Feb 10 11:10:59 IST 2023\n" +
                "Car Rental\t1000\t \n" +
                "Dinner\t5100\tX\n" +
                "Meal expenses: 5100\n" +
                "Total expenses: 6100\n"


        val outputFromConsole = ByteArrayOutputStream()
        val ps = PrintStream(outputFromConsole)
        System.setOut(ps)
//        ACT
        ExpenseReport().printReport(expenses)
        val result = outputFromConsole.toString()
        val actualReportStatement = result.split("\n")
        val expectedReportStatement = expected.split("\n")

        performAssert(actualReportStatement, expectedReportStatement)

    }

    @Test
    fun `It should print X when breakfast when it crosses threshold of 1000`() {
        //Arrange
        val breakfastExpense = createExpense(ExpenseType.BREAKFAST, 51100)
        val expenses = arrayListOf(breakfastExpense)
        val expected = "Expenses Fri Feb 10 11:10:59 IST 2023\n" +
                "Breakfast\t51100\tX\n" +
                "Meal expenses: 51100\n" +
                "Total expenses: 51100\n"


        val outputFromConsole = ByteArrayOutputStream()
        val ps = PrintStream(outputFromConsole)
        System.setOut(ps)
//        ACT
        ExpenseReport().printReport(expenses)
        val result = outputFromConsole.toString()
        val actualReportStatement = result.split("\n")
        val expectedReportStatement = expected.split("\n")

        performAssert(actualReportStatement, expectedReportStatement)

    }

    @Test
    fun `It should print for lunch`() {
        //Arrange
        val lunchExpense = createExpense(ExpenseType.LUNCH, 1000)
        val expenses = arrayListOf(lunchExpense)
        val expected = "Expenses Fri Feb 10 11:10:59 IST 2023\n" +
                "Lunch\t1000\t \n" +
                "Meal expenses: 1000\n" +
                "Total expenses: 1000\n"

        val outputFromConsole = ByteArrayOutputStream()
        val ps = PrintStream(outputFromConsole)
        System.setOut(ps)

//        ACT
        ExpenseReport().printReport(expenses)

        val actual = outputFromConsole.toString()
        val actualReportStatement = actual.split("\n")
        val expectedReportStatement = expected.split("\n")
        println("actual: $actualReportStatement \nexpected $expectedReportStatement")
        performAssert(actualReportStatement, expectedReportStatement)
    }
    @Test
    fun `It should print X for lunch above 2000`() {
        //Arrange
        val lunchExpense = createExpense(ExpenseType.LUNCH, 2100)
        val expenses = arrayListOf(lunchExpense)
        val expected = "Expenses Fri Feb 10 11:10:59 IST 2023\n" +
                "Lunch\t2100\tX\n" +
                "Meal expenses: 2100\n" +
                "Total expenses: 2100\n"

        val outputFromConsole = ByteArrayOutputStream()
        val ps = PrintStream(outputFromConsole)
        System.setOut(ps)

//        ACT
        ExpenseReport().printReport(expenses)

        val actual = outputFromConsole.toString()
        val actualReportStatement = actual.split("\n")
        val expectedReportStatement = expected.split("\n")
        println("actual: $actualReportStatement \nexpected $expectedReportStatement")
        performAssert(actualReportStatement, expectedReportStatement)
    }

    private fun performAssert(
        actualReportStatement: List<String>,
        expectedReportStatement: List<String>
    ) {
        for ((index, _) in expectedReportStatement.withIndex()) {
            if (index == 0) continue
            assertEquals(actualReportStatement[index], expectedReportStatement[index])
        }
    }

    private fun createExpense(expenseType: ExpenseType, amount: Int): Expense {
        val breakfastExpense = Expense()
        breakfastExpense.type = expenseType
        breakfastExpense.amount = amount
        return breakfastExpense
    }
}
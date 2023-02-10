package com.nelkinda.training

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream

class ExpenseReportTest {
    @Test
    fun `should calculate total expense and meal expense for dinner and breakfast`() {
        val expected = "Expenses Fri Feb 10 11:10:59 IST 2023\n" +
                "Dinner\t100\t \n" +
                "Breakfast\t200\t \n" +
                "Meal expenses: 300\n" +
                "Total expenses: 300\n"
        val baos = ByteArrayOutputStream()

        //Setup Data
        val dinnerExpense = Expense()
        dinnerExpense.type = ExpenseType.DINNER
        dinnerExpense.amount = 100

        val lunchExpense = Expense()
        lunchExpense.type = ExpenseType.BREAKFAST
        lunchExpense.amount = 200


        val expenses = arrayListOf(dinnerExpense, lunchExpense)

        ExpenseReport().printReport(expenses)


        val result = baos.toString()
        val actualReportStatement = result.split("\n")
        val expectedReportStatement = expected.split("\n")

        for ((index, _) in actualReportStatement.withIndex()) {
            if(index == 0) continue
            assertEquals(actualReportStatement[index],expectedReportStatement[index])
        }





    }
}
package com.nelkinda.training

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ExpenseReportTest {
    @Test
    fun `should calculate total expense and meal expense`() {

        val expected = "Expenses Fri Feb 10 11:10:59 IST 2023\n" +
                "Dinner\t100\t \n" +
                "Breakfast\t200\t \n" +
                "Meal expenses: 300\n" +
                "Total expenses: 300\n"
        val baos = ByteArrayOutputStream()
        val ps = PrintStream(baos)
        System.setOut(ps)

        //Setup Data


        //CallFunctionTo Print Report Output

        val expense = Expense()
        expense.type = ExpenseType.DINNER
        expense.amount = 100

        val expense1 = Expense()
        expense1.type = ExpenseType.BREAKFAST
        expense1.amount = 200

        val expenses = arrayListOf<Expense>()
        expenses.add(expense)
        expenses.add(expense1)

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
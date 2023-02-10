package com.nelkinda.training

import com.nelkinda.training.model.ExpenseStatement
import java.util.*

enum class ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

class Expense {
    lateinit var type: ExpenseType
    var amount: Int = 0
}

class ExpenseReport() {
    fun printReport(expenses: List<Expense>) {
        println("Expenses ${Date()}")
        val expenseStatement = calculateExpenseReport(expenses)
        println("Meal expenses: ${expenseStatement.mealExpense}")
        println("Total expenses: ${expenseStatement.totalAmount}")
    }

    private fun calculateExpenseReport(expenses: List<Expense>): ExpenseStatement {
        var totalMealExpenses = 0
        var totalExpense = 0
        val expenseStatement = ExpenseStatement()

        for (expense in expenses) {
            if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
                totalMealExpenses += expense.amount
            }
            val expenseName = getExpenseName(expense)
            val mealOverExpensesMarker =
                if (isMealExpenseCrossingThreshold(expense)) "X" else " "

            println(expenseName + "\t" + expense.amount + "\t" + mealOverExpensesMarker)

            totalExpense += expense.amount
        }
        expenseStatement.mealExpense = totalMealExpenses
        expenseStatement.totalAmount = totalExpense
        return expenseStatement
    }

    private fun getExpenseName(expense: Expense) = when (expense.type) {
        ExpenseType.DINNER -> "Dinner"
        ExpenseType.BREAKFAST -> "Breakfast"
        ExpenseType.CAR_RENTAL -> "Car Rental"
    }

    private fun isMealExpenseCrossingThreshold(expense: Expense) =
        expense.type == ExpenseType.DINNER && expense.amount > 5000 || expense.type == ExpenseType.BREAKFAST && expense.amount > 1000
}

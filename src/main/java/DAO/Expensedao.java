package DAO;

import Model.Expense;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Expensedao {
    private Connection connection;

    // Constructor to initialize the database connection
    public Expensedao(Connection connection) {
        this.connection = connection;
    }

    // Method to add an expense to the database
    public boolean addExpense(Expense expense) {
        String query = "INSERT INTO expenses (user_id, amount, description, date, category) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, expense.getUserId());
            statement.setDouble(2, expense.getAmount());
            statement.setString(3, expense.getDescription());
            statement.setString(4, expense.getDate());
            statement.setString(5, expense.getCategory());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;  // Returns true if the expense was added successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to retrieve all expenses for a given user
    public List<Expense> getExpensesByUserId(int userId) {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getInt("id"));
                expense.setUserId(resultSet.getInt("user_id"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setDescription(resultSet.getString("description"));
                expense.setDate(resultSet.getString("date"));
                expense.setCategory(resultSet.getString("category"));

                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    // Method to update an existing expense
    public boolean updateExpense(Expense expense) {
        String query = "UPDATE expenses SET amount = ?, description = ?, date = ?, category = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, expense.getAmount());
            statement.setString(2, expense.getDescription());
            statement.setString(3, expense.getDate());
            statement.setString(4, expense.getCategory());
            statement.setInt(5, expense.getId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;  // Returns true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete an expense by its ID
    public boolean deleteExpense(int expenseId) {
        String query = "DELETE FROM expenses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, expenseId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;  // Returns true if the expense was deleted successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a specific expense by its ID
    public Expense getExpenseById(int expenseId) {
        Expense expense = null;
        String query = "SELECT * FROM expenses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, expenseId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                expense = new Expense();
                expense.setId(resultSet.getInt("id"));
                expense.setUserId(resultSet.getInt("user_id"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setDescription(resultSet.getString("description"));
                expense.setDate(resultSet.getString("date"));
                expense.setCategory(resultSet.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expense;
    }
}

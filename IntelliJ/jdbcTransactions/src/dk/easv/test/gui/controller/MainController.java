package dk.easv.test.gui.controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.easv.test.dal.Person;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;

import static java.sql.Connection.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController implements Initializable {
    @FXML
    private  MFXToggleButton toggleTrans;
    @FXML
    private TextArea text1;
    @FXML
    private TextArea text2;
    @FXML
    private TextField txtBalance1;
    @FXML
    private TextField txtBalance2;
    @FXML
    private TextField txtStartBalance;
    @FXML
    private TextField txtEndBalance;
    @FXML
    private TextField txtDiff;
    @FXML
    private TextField txtPSum;
    @FXML
    private TextField txtP1Failed;
    @FXML
    private TextField txtP2Failed;
    private Person p1 = new Person();
    private Person p2 = new Person();

    private SQLServerDataSource ds = new SQLServerDataSource();

    public MainController() {
        ds.setDatabaseName("cse2024_30_transactions_example");
        ds.setUser("CSe2024B_e_30");
        ds.setPassword("CSe2024bE30!24");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.34"); // EASV-DB4 , New DB Server
        ds.setTrustServerCertificate(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void clickBtn(ActionEvent actionEvent) {
        try {
            updateBalance(1, 1000000, ds.getConnection());
            txtStartBalance.setText(getBalance(1, ds.getConnection()) + "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        txtDiff.clear();
        txtPSum.clear();
        txtBalance1.clear();
        txtBalance2.clear();
        txtP1Failed.clear();
        txtP2Failed.clear();
        txtEndBalance.clear();

        p1.setId(1);
        p1.setName("Peter");
        p1.setBalance(0);

        p2.setId(1);
        p2.setName("Christian");
        p2.setBalance(0);

        txtP1Failed.setText("0");
        txtP2Failed.setText("0");

        Thread t1 = createThread(p1, 100, text1, txtBalance1, txtP1Failed,20, 100);
        Thread t2 = createThread(p2, 100, text2, txtBalance2, txtP2Failed,20, 100);
        t1.start();
        t2.start();

    }

    private Thread createThread(Person p, int amount, TextArea textArea, TextField pBalance, TextField failCounter ,int waitingTimems,int timesToWithdraw) {
        Thread t = new Thread(() -> {
                Random r = new Random();
                for (int i = 0; i < timesToWithdraw; i++) {
                    withdraw(p, amount, textArea, failCounter);
                    Platform.runLater(()->{
                        pBalance.setText(p.getBalance()+"");
                    });

                    try {
                        Thread.sleep(r.nextInt(waitingTimems, waitingTimems+waitingTimems/3));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Platform.runLater(()-> {
                    try {
                        txtEndBalance.setText(getBalance(1, ds.getConnection()) + "");
                        txtDiff.setText((Double.parseDouble(txtStartBalance.getText()) - Double.parseDouble(txtEndBalance.getText())) + "");
                        txtPSum.setText((Double.parseDouble(txtBalance1.getText()) + Double.parseDouble(txtBalance2.getText())) + "");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
        });
        t.setDaemon(true);
        return t;
    }

    private double getBalance(int accountNo, Connection con) throws SQLException {
        double balance = 0;
        try {
            String sql = "SELECT balance FROM Persons WHERE id=?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, accountNo);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                balance = rs.getFloat("balance");
            }
        } catch (SQLServerException e) {
            e.printStackTrace();
        }
        return balance;
    }


    private void updateBalance(int accountNo, double balance, Connection con) throws SQLException {
            String sql = "UPDATE Persons SET balance=? WHERE id=?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1, balance);
            pstmt.setInt(2, accountNo);
            pstmt.executeUpdate();
    }

    public void withdraw(Person p, double amount, TextArea output, TextField failCounter) {
        try (Connection con = ds.getConnection()){
            // START TRANSACTION
            // read the balance FROM DB
            if (toggleTrans.isSelected()){
                con.setAutoCommit(false);
                con.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
            }

            double balance = getBalance(p.getId(), con);
            // calculate new balance MEMORY
            double newBalance = balance - amount; 

            // persistance to DB
            updateBalance(p.getId(), newBalance, con); // set to 900

            // Commit the transaction
            con.commit();

            Platform.runLater(()->{
                        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm:ss"));
                        output.setText("\n" + time + " " + p.toString() + " " + amount + "$" + output.getText() );
                    }
            );
            // set new balance to person
            p.setBalance(p.getBalance()+amount);

            // END TRANSACTION
            // set connection back to normal non-transactional mode
            con.setAutoCommit(true);
            con.setTransactionIsolation(TRANSACTION_NONE);

        }
        catch (SQLException e) {
            System.out.println("Transaction failed, probably deadlock du to isolation levels");
            Platform.runLater(()->{
                output.setText("\nTransaction failed!" + output.getText());
                failCounter.setText((Integer.parseInt(failCounter.getText())+1)+"");
                    }
            );

        }
    }


}

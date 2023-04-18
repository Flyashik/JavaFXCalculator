package com.example.calculatorjava;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.util.Objects;

public class HelloController {

    enum Exp {
        ADD,
        SUB,
        MUL,
        DIV,
        Eq
    }

    private Boolean pressed = false;
    private Exp exp = Exp.ADD;
    @FXML
    private Button btnPlus;

    @FXML
    private Button btnMinus;

    @FXML
    private Button btnMul;

    @FXML
    private Button btnDiv;

    @FXML
    private TextField textFirst;

    @FXML
    private TextField textSecond;

    @FXML
    private Label result;

    @FXML
    private Label sign;

    @FXML
    protected void pushPlus()
    {
        sign.setText("+");
        exp = Exp.ADD;
    }

    @FXML
    protected void pushMinus()
    {
        sign.setText("-");
        exp = Exp.SUB;
    }

    @FXML
    protected void pushMul()
    {
        sign.setText("*");
        exp = Exp.MUL;

        textFirst.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!".0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
                if ((Objects.equals(keyEvent.getCharacter(), ".")) && (textFirst.getText().contains(".") || Objects.equals(textFirst.getText(), ""))) {
                    System.out.print(keyEvent.getCharacter());
                    keyEvent.consume();
                }
                if ((Objects.equals(keyEvent.getCharacter(), "0")) && (Objects.equals(textFirst.getText(), "0"))) {
                    System.out.print(keyEvent.getCharacter());
                    keyEvent.consume();
                }
            }
        });
    }

    @FXML
    protected void pushDiv()
    {
        sign.setText("/");
        exp = Exp.DIV;
    }

    @FXML
    protected void pushEq()
    {
        double res = 0;

        if (textFirst.getText().isEmpty() || textSecond.getText().isEmpty())
        {
            return;
        }

        switch (exp)
        {
            case Eq:
                break;
            case ADD:
                res = Double.parseDouble(textFirst.getText()) + Double.parseDouble(textSecond.getText());
                break;
            case SUB:
                res = Double.parseDouble(textFirst.getText()) - Double.parseDouble(textSecond.getText());
                break;
            case MUL:
                res = Double.parseDouble(textFirst.getText()) * Double.parseDouble(textSecond.getText());
                break;
            case DIV:
                if (Double.parseDouble(textSecond.getText()) == 0)
                {
                    result.setText("Error");
                    return;
                }
                res = Double.parseDouble(textFirst.getText()) / Double.parseDouble(textSecond.getText());
                break;
        }

        if (res == (int)res)
        {
            result.setText((int)res + "");
            return;
        }

        result.setText(res + "");
    }

    @FXML
    protected void startType()
    {
        if (pressed)
        {
            return;
        }
        pressed = true;

        textFirst.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!".0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
                if ((Objects.equals(keyEvent.getCharacter(), ".")) && (textFirst.getText().contains(".") || Objects.equals(textFirst.getText(), ""))) {
                    System.out.print(keyEvent.getCharacter());
                    keyEvent.consume();
                }
                if ((Objects.equals(keyEvent.getCharacter(), "0")) && (Objects.equals(textFirst.getText(), "0"))) {
                    System.out.print(keyEvent.getCharacter());
                    keyEvent.consume();
                }
            }
        });

        textSecond.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent keyEvent) {
                if (!".0123456789".contains(keyEvent.getCharacter())) {
                    keyEvent.consume();
                }
                if ((Objects.equals(keyEvent.getCharacter(), ".")) && (textSecond.getText().contains(".") || Objects.equals(textSecond.getText(), ""))) {
                    System.out.print(keyEvent.getCharacter());
                    keyEvent.consume();
                }
                if ((Objects.equals(keyEvent.getCharacter(), "0")) && (Objects.equals(textSecond.getText(), "0"))) {
                    System.out.print(keyEvent.getCharacter());
                    keyEvent.consume();
                }
            }
        });
    }
}
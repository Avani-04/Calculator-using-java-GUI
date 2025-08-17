 import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator1 extends JFrame implements ActionListener {
    private JTextField display;
    private double num1, num2, result;
    private String operator;
    private boolean isOperatorPressed;

    public SimpleCalculator1() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initializeComponents();
        layoutComponents();
    }

    private void initializeComponents() {
        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 20));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setText("0");
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createLoweredBevelBorder());
        
        // Initialize variables
        num1 = num2 = result = 0;
        operator = "";
        isOperatorPressed = false;
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        
        // Display panel
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayPanel.add(display, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.NORTH);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Button labels in order
        String[] buttonLabels = {
            "C", "CE", "±", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=", ""
        };
        
        // Create and add buttons
        for (int i = 0; i < buttonLabels.length - 1; i++) {
            if (!buttonLabels[i].isEmpty()) {
                JButton button = createButton(buttonLabels[i]);
                buttonPanel.add(button);
            }
        }
        
        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(this);
        button.setFocusable(false);
        
        // Color coding for different button types
        if (text.matches("[0-9]")) {
            button.setBackground(new Color(240, 240, 240));
        } else if (text.matches("[+\\-*/]")) {
            button.setBackground(new Color(255, 165, 0));
            button.setForeground(Color.WHITE);
        } else if (text.equals("=")) {
            button.setBackground(new Color(255, 165, 0));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(new Color(200, 200, 200));
        }
        
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        try {
            if (command.matches("[0-9]")) {
                handleNumber(command);
            } else if (command.equals(".")) {
                handleDecimal();
            } else if (command.matches("[+\\-*/]")) {
                handleOperator(command);
            } else if (command.equals("=")) {
                handleEquals();
            } else if (command.equals("C")) {
                handleClear();
            } else if (command.equals("CE")) {
                handleClearEntry();
            } else if (command.equals("±")) {
                handlePlusMinus();
            }
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    private void handleNumber(String number) {
        if (display.getText().equals("0") || isOperatorPressed) {
            display.setText(number);
            isOperatorPressed = false;
        } else {
            display.setText(display.getText() + number);
        }
    }

    private void handleDecimal() {
        if (isOperatorPressed) {
            display.setText("0.");
            isOperatorPressed = false;
        } else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    private void handleOperator(String op) {
        if (!operator.isEmpty() && !isOperatorPressed) {
            handleEquals();
        }
        
        num1 = Double.parseDouble(display.getText());
        operator = op;
        isOperatorPressed = true;
    }

    private void handleEquals() {
        if (operator.isEmpty()) return;
        
        num2 = Double.parseDouble(display.getText());
        
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    display.setText("Cannot divide by zero");
                    return;
                }
                break;
        }
        
        // Format result to avoid unnecessary decimal places
        if (result == (long) result) {
            display.setText(String.valueOf((long) result));
        } else {
            display.setText(String.valueOf(result));
        }
        
        operator = "";
        isOperatorPressed = true;
    }

    private void handleClear() {
        display.setText("0");
        num1 = num2 = result = 0;
        operator = "";
        isOperatorPressed = false;
    }

    private void handleClearEntry() {
        display.setText("0");
    }

    private void handlePlusMinus() {
        double current = Double.parseDouble(display.getText());
        current = -current;
        
        if (current == (long) current) {
            display.setText(String.valueOf((long) current));
        } else {
            display.setText(String.valueOf(current));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new SimpleCalculator1().setVisible(true);
        });
    }
}
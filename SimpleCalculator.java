import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {
    // Components
    JTextField input1, input2, result;
    JButton add, sub, mul, div, clear,mod;

    public SimpleCalculator() {
        // Frame title
        setTitle("Simple Calculator");
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Layout
        setLayout(new GridLayout(6, 2, 10, 10));

        // Components
        input1 = new JTextField();
        input2 = new JTextField();
        result = new JTextField();
        result.setEditable(false);

        add = new JButton("Add");
        sub = new JButton("Subtract");
        mul = new JButton("Multiply");
        div = new JButton("Divide");
        mod = new JButton("Modulus");
        clear = new JButton("Clear");

        // Action Listeners
        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);
        mod.addActionListener(this);
        clear.addActionListener(this);

        // Add components to frame
        add(new JLabel("Enter number 1:"));
        add(input1);
        add(new JLabel("Enter number 2:"));
        add(input2);
        add(new JLabel("Result:"));
        add(result);
        add(add);
        add(sub);
        add(mul);
        add(div);
        add(mod);
        add(clear);
        

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        double num1 = 0, num2 = 0;
        String s1 = input1.getText();
        String s2 = input2.getText();

        try {
            num1 = Double.parseDouble(s1);
            num2 = Double.parseDouble(s2);
        } catch (NumberFormatException ex) {
            result.setText("Invalid input");
            return;
        }

        if (e.getSource() == add) {
            result.setText(String.valueOf(num1 + num2));
        } else if (e.getSource() == sub) {
            result.setText(String.valueOf(num1 - num2));
        } else if (e.getSource() == mul) {
            result.setText(String.valueOf(num1 * num2));
        } else if (e.getSource() == div) {
            if (num2 != 0)
                result.setText(String.valueOf(num1 / num2));
            else
                result.setText("Cannot divide by zero");
        } else if (e.getSource() == mod) {
            if (num2 != 0)
                result.setText(String.valueOf(num1 % num2));
            else
                result.setText("Cannot divide by zero");
        
        }else if (e.getSource() == clear) {
            input1.setText("");
            input2.setText("");
            result.setText("");
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}

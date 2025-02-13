package cop2805;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;


public class Main extends JFrame {
    private static final JTextField numF1 = new JTextField();
    private static final JTextField numF2 = new JTextField();
    private static final String[] operations = {"Add", "Subtract", "Multiply", "Divide"};
    private static final JComboBox<String> operationBox = new JComboBox<>(operations);
    private final JButton calculate = new JButton("Calculate");
    private static final JLabel result = new JLabel("Result: ");

    public Main(){
        super();
        init();
    }

    //window constructor
    public void init() {
        setDefaultLookAndFeelDecorated(true);
        this.setTitle("Simple Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout( new GridLayout(5, 2));
        this.setSize(340, 200);

        //Two JTextFields to input numbers into and there labels
        this.add(new JLabel("First Number: "));
        this.add(numF1);
        this.add(new JLabel("Second Number: "));
        this.add(numF2);

        //A JComboBox to select the operation
        this.add(new JLabel(""));
        this.add(operationBox);

        //A JButton that reads "Calculate"
        this.add(new JLabel(""));
        this.add(calculate);
        //event listener for the calculate button
        calculate.addActionListener( new MyButtonListener(this) );

        //A JLabel that outputs a result
        this.add(result);

        this.setVisible(true);
    }

    private static void constructGUI(){
        Main newWindow = new Main();
    }

    public static void calculate(){
        //parse the numbers from the JTextFields
        double num1 = Double.parseDouble(numF1.getText());
        double num2 = Double.parseDouble(numF2.getText());

        //get the operation from the JComboBox
        String operation = Objects.requireNonNull(operationBox.getSelectedItem()).toString();

        //perform the operation
        double afterResult = switch (operation) {
            case "Add" -> num1 + num2;
            case "Subtract" -> num1 - num2;
            case "Multiply" -> num1 * num2;
            case "Divide" -> num1 / num2;
            default -> 0;
        };

        result.setText("Result: " + afterResult);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                constructGUI();
            }

        });
        System.out.println(Arrays.toString(args));
    }
}

class MyButtonListener implements ActionListener {
    Main fr;
    public MyButtonListener(Main fr) {
        this.fr = fr;
    }

    public void actionPerformed(ActionEvent eventData) {


        JButton btn = (JButton) eventData.getSource();
        if (btn.getText().equals("Calculate")) {
            Main.calculate();
        }
    }


}
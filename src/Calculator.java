import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Calculator implements ActionListener{
    private final JFrame frame = new JFrame();
    private final JTextField textField = new JTextField();
    private final JButton cDelete = new JButton("c");
    private final JButton ceDelete = new JButton("ce");
    private final JButton delete = new JButton("<-");
    private final JButton plus = new JButton("+");
    private final JButton minus = new JButton("-");
    private final JButton mul = new JButton("*");
    private final JButton div = new JButton("/");
    private final JButton sqrt = new JButton("√");
    private final JButton deg = new JButton("x²");
    private final JButton equal = new JButton("=");
    private final JButton comma = new JButton(",");
    private final JButton plusMinus = new JButton("+/-");
    private final JButton but1 = new JButton("1");
    private final JButton but2 = new JButton("2");
    private final JButton but3 = new JButton("3");
    private final JButton but4 = new JButton("4");
    private final JButton but5 = new JButton("5");
    private final JButton but6 = new JButton("6");
    private final JButton but7 = new JButton("7");
    private final JButton but8 = new JButton("8");
    private final JButton but9 = new JButton("9");
    private final JButton but0 = new JButton("0");

    private double buffer = 0;
    private String operation = "";
    private boolean checkClear = false;
    private boolean checkEqual = false;
    boolean checkdel = false;
    private final Font font = new Font("Arial Black", Font.BOLD, 20);
    public Calculator() {
        frame.setFont(font);
        frame.setSize(294, 310);
        frame.setResizable(false);
        frame.setLocation(600, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setFocusable(true);

        textField.setPreferredSize(new Dimension(262, 50));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEnabled(false);
        textField.setFont(font);
        frame.add(textField);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = Character.toUpperCase(e.getKeyChar());
                if(key<'?'&&key>')') {
                    doSomething(Character.toString(key));
                }
                else{
                    switch (key) {
                        case (KeyEvent.VK_ENTER) -> doSomething("=");
                        case (KeyEvent.VK_BACK_SPACE) -> doSomething("<-");
                        case (KeyEvent.VK_P) -> doSomething("x²");
                        case (KeyEvent.VK_S) -> doSomething("√");
                        case (KeyEvent.VK_C) -> doSomething("c");
                        case (KeyEvent.VK_E) -> doSomething("ce");
                        case (KeyEvent.VK_D) -> doSomething("+/-");
                    }
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        addAllButtons();
        frame.setVisible(true);
    }

    private void addAllButtons() {
        delete.setPreferredSize(new Dimension());
        JButton[] buttons = {cDelete, ceDelete, delete,
                deg, sqrt, div,
                but7, but8, but9, mul,
                but4, but5, but6, minus,
                but1, but2, but3, plus,
                plusMinus, but0, comma, equal};
        for (JButton but : buttons) {
            but.setPreferredSize(new Dimension(62, 30));
            but.addActionListener(this);
            but.setFont(font);
            but.setFocusable(false);
            frame.add(but);
        }
        delete.setPreferredSize(new Dimension(128, 30));
        sqrt.setPreferredSize(new Dimension(95, 30));
        deg.setPreferredSize(new Dimension(95, 30));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doSomething(e.getActionCommand());
    }

    private void doSomething(String key) {
        checkdel =false;
        if(checkClear){
            textField.setText("");
            checkClear = false;
        }
        if(checkEqual&&Character.isDigit(key.charAt(0)))
            textField.setText("");
        checkEqual=false;
        String lastText = textField.getText();
        double number = 0.0;
        if(!lastText.equals(""))
        {
            try{
                number = Double.parseDouble(lastText.replace(',','.'));
            }
            catch(Exception ex){JFrame jFrame = new JFrame(); JOptionPane.showMessageDialog(jFrame, "Некорректное число");}
        }
        else
            checkdel = true;
        switch (key) {
            case ("0"):
                textField.setText(lastText + "0");
                break;
            case ("1"):
                textField.setText(lastText + "1");
                break;
            case ("2"):
                textField.setText(lastText + "2");
                break;
            case ("3"):
                textField.setText(lastText + "3");
                break;
            case ("4"):
                textField.setText(lastText + "4");
                break;
            case ("5"):
                textField.setText(lastText + "5");
                break;
            case ("6"):
                textField.setText(lastText + "6");
                break;
            case ("7"):
                textField.setText(lastText + "7");
                break;
            case ("8"):
                textField.setText(lastText + "8");
                break;
            case ("9"):
                textField.setText(lastText + "9");
                break;
            case ("+/-"):
                if (!lastText.equals("")) {
                    textField.setText(convertToStr(number*(-1)));
                }
                break;
            case (","):
                if (!lastText.contains(",") && !lastText.equals(""))
                    textField.setText(lastText + ",");
                break;
            case ("c"):
                textField.setText("");
                operation = "";
                buffer = 0;
                break;
            case ("ce"):
                textField.setText("");
                break;
            case ("<-"):
                if (!lastText.equals(""))
                    textField.setText(lastText.substring(0, lastText.length() - 1));
                break;
            case ("x²"):
                    if(operation.equals(""))
                    {
                        if (lastText.equals(""))
                            buffer = 0;
                        else
                            buffer = number;
                        operation = "x²";
                        textField.setText("");
                    }
                    else{
                        if(operation.equals("x²")) {
                            if((number == 0.5||number==-0.5)&&buffer<0){
                                JFrame jFrame = new JFrame();
                                JOptionPane.showMessageDialog(jFrame, "Корень от отрицательного числа");
                                break;
                            }
                            checkClear = true;
                            if(number==0)
                                number = 1;
                            buffer = Math.pow(buffer,number);
                            textField.setText(convertToStr(buffer));
                        }
                        else {
                            doSomething(operation);
                            operation = "x²";
                        }
                    }

                break;
            case ("√"):
                if (number >= 0)
                    textField.setText(convertToStr(Math.sqrt(number)));
                else {
                    JFrame jFrame = new JFrame();
                    JOptionPane.showMessageDialog(jFrame, "Корень от отрицательного числа");
                    operation = "";
                    textField.setText(convertToStr(number));
                }
                break;
            case ("+"):
                if(operation.equals(""))
                {
                    if (lastText.equals(""))
                        buffer = 0;
                    else
                        buffer = number;
                    operation = "+";
                    textField.setText("");
                }
                else{
                    if(operation.equals("+")) {
                        checkClear = true;
                        buffer += number;
                        textField.setText(convertToStr(buffer));
                    }
                    else {
                        doSomething(operation);
                        operation = "+";
                    }
                }
                break;
            case("-"):
                if(operation.equals("")) {
                    if (lastText.equals(""))
                        buffer = 0;
                    else
                        buffer = number;
                    operation = "-";
                    textField.setText("");
                }
                else{
                    if(operation.equals("-")) {
                        checkClear = true;
                        buffer -= number;
                        textField.setText(convertToStr(buffer));
                    }
                    else {
                        doSomething(operation);
                        operation = "-";
                    }
                }
                break;
            case("*"):
                if(operation.equals("")) {
                    if (lastText.equals(""))
                        buffer = 0;
                    else
                        buffer = number;
                    operation = "*";
                    textField.setText("");
                }
                else{
                    if(operation.equals("*")) {
                        checkClear = true;
                        if(number==0)
                            number = 1;
                        buffer *= number;
                        textField.setText(convertToStr(buffer));
                    }
                    else {
                        doSomething(operation);
                        operation = "*";
                    }
                }
                break;
            case("/"):
                if(operation.equals("")) {
                    if (lastText.equals(""))
                        buffer = 0;
                    else
                        buffer = number;
                    operation = "/";
                    textField.setText("");
                }
                else{
                    if(operation.equals("/")) {
                        if(checkdel)
                            number =1;
                        if(number!=0) {
                            checkClear = true;
                            buffer /= number;
                            textField.setText(convertToStr(buffer));
                        }
                        else{
                            JFrame jFrame = new JFrame();
                            JOptionPane.showMessageDialog(jFrame, "Деление на 0");
                            operation = "";
                            textField.setText(convertToStr(buffer));
                        }
                    }
                    else {
                        doSomething(operation);
                        operation = "/";
                    }
                }
                break;
            case("="):
                if(lastText.equals("")) {
                    operation = "";
                    textField.setText(convertToStr(buffer));
                    checkClear =false;
                }
                else {
                    if(!operation.equals("")){
                        doSomething(operation);
                        checkClear =false;
                        checkEqual = true;
                        operation = "";
                    }
                }
                break;
        }
    }
    private String convertToStr(double input){
        long res = (long) input;
        if(input == res){
            return Long.toString(res);
        }
        return Double.toString(input).replace('.',',');
    }
}

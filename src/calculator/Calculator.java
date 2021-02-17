package calculator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator extends JFrame {

    private Container con;
    private ImageIcon img = new ImageIcon(getClass().getResource("cal__icon.png"));
    private Font f = new Font("Arial",Font.BOLD,22);
    private JTextField displayFeild = new JTextField();
    Handler handler = new Handler();
    private JButton[] btn;
    private JButton plusbtn, minusbtn, multibtn, subbtn, delbtn, clrbtn, eqlbtn, modbtn, pointbtn,rootbtn;
    private int check = 0;
    Calculator()
    {
        this.setIconImage(img.getImage());
        con = this.getContentPane();
        con.setBackground(Color.ORANGE);
        con.setFont(f);
        con.setLayout(null);

        displayFeild.setBounds(15,5,260,60);
        displayFeild.setFont(f);
        displayFeild.setText("");
        displayFeild.setBackground(Color.WHITE);
        displayFeild.setHorizontalAlignment(JTextField.RIGHT);
        con.add(displayFeild);
        setButtons();
    }
    void setButtons()
    {
        btn = new JButton[10];
        for (int i = 0; i < 10; i++) {
            btn[i] = new JButton(String.valueOf(i));
            btn[i].setText(""+i);
            btn[i].setFont(f);
            btn[i].setBackground(Color.CYAN);
            btn[i].setForeground(Color.ORANGE);
            btn[i].addActionListener(handler);
            con.add(btn[i]);
        }
        int x = 65;
        rootbtn = new JButton("√x");
        rootbtn.setBackground(Color.CYAN);
        rootbtn.setForeground(Color.ORANGE);
        rootbtn.setFont(f);
        rootbtn.setBounds(15,70,x, x );
        rootbtn.addActionListener(handler);

        modbtn = new JButton("%");
        modbtn.setBackground(Color.CYAN);
        modbtn.setForeground(Color.ORANGE);
        modbtn.setFont(f);
        modbtn.setBounds(x+15,70,x, x );
        modbtn.addActionListener(handler);

        delbtn = new JButton("del");
        delbtn.setBackground(Color.CYAN);
        delbtn.setForeground(Color.ORANGE);
        delbtn.setFont(f);
        delbtn.setBounds(x+x+15,70,x, x );
        delbtn.addActionListener(handler);

        clrbtn = new JButton("clr");
        clrbtn.setBackground(Color.CYAN);
        clrbtn.setForeground(Color.ORANGE);
        clrbtn.setFont(f);
        clrbtn.setBounds(x+x+x+15,70,x, x );
        clrbtn.addActionListener(handler);

        btn[7].setBounds(15,x+70,x,x);
        btn[8].setBounds(x+15,x+70,x,x);
        btn[9].setBounds(x+x+15,x+70,x,x);

        multibtn = new JButton("*");
        multibtn.setBounds(x+x+x+15,x+70,x,x);
        multibtn.setBackground(Color.CYAN);
        multibtn.setForeground(Color.ORANGE);
        multibtn.setFont(f);
        multibtn.addActionListener(handler);

        btn[4].setBounds(15,x+x+70,x,x);
        btn[5].setBounds(x+15,x+x+70,x,x);
        btn[6].setBounds(x+x+15,x+x+70,x,x);

        subbtn = new JButton("/");
        subbtn.setBounds(x+x+x+15,x+x+70,x,x);
        subbtn.setBackground(Color.CYAN);
        subbtn.setForeground(Color.ORANGE);
        subbtn.setFont(f);
        subbtn.addActionListener(handler);
        btn[1].setBounds(15,x+x+x+70,x,x);
        btn[2].setBounds(x+15,x+x+x+70,x,x);
        btn[3].setBounds(x+x+15,x+x+x+70,x,x);

        minusbtn = new JButton("-");
        minusbtn.setBounds(x+x+x+15,x+x+x+70,x,x);
        minusbtn.setBackground(Color.CYAN);
        minusbtn.setForeground(Color.ORANGE);
        minusbtn.setFont(f);
        minusbtn.addActionListener(handler);

        pointbtn = new JButton(".");
        btn[0].setBounds(15,x+x+x+x+70,x,x);
        pointbtn.setBounds(x+15,x+x+x+x+70,x,x);
        pointbtn.setBackground(Color.CYAN);
        pointbtn.setForeground(Color.ORANGE);
        pointbtn.setFont(f);
        pointbtn.addActionListener(handler);

        eqlbtn = new JButton("=");
        eqlbtn.setBounds(x+x+15,x+x+x+x+70,x,x);
        eqlbtn.setBackground(Color.CYAN);
        eqlbtn.setForeground(Color.ORANGE);
        eqlbtn.addActionListener(handler);
        eqlbtn.setFont(f);

        plusbtn = new JButton("+");
        plusbtn.setBounds(x+x+x+15,x+x+x+x+70,x,x);
        plusbtn.setBackground(Color.CYAN);
        plusbtn.setForeground(Color.ORANGE);
        plusbtn.addActionListener(handler);
        plusbtn.setFont(f);

        con.add(rootbtn);
        con.add(modbtn);
        con.add(delbtn);
        con.add(clrbtn);
        con.add(pointbtn);
        con.add(eqlbtn);
        con.add(plusbtn);
        con.add(minusbtn);
        con.add(multibtn);
        con.add(subbtn);
    }

    class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = displayFeild.getText();
            if(str.length()<16)
            {
                for (int i = 1; i <= 9; i++) {
                    if(e.getSource() == btn[i])
                    {
                        if(check == 1) //To clear screen after calculation
                        {
                            str = "";
                            check = 0;
                        }
                        str = str.concat(btn[i].getText());
                        displayFeild.setText(str);
                    }
                }
                if(e.getSource() == btn[0])
                {
                    if(!str.isEmpty())
                    {
                        str = str.concat(btn[0].getText());
                        displayFeild.setText(str);
                    }
                }
            }

                if(e.getSource() == rootbtn)
                {
                    double d_str = Float.parseFloat(str);
                    d_str = Math.sqrt(d_str);
                    displayFeild.setText(String.valueOf(d_str));

                }
                else if(e.getSource() == modbtn)
                {
                    float f_str = Float.parseFloat(str);
                    f_str =  f_str/100;
                    displayFeild.setText(String.valueOf(f_str));
                }
                else if(e.getSource() == multibtn)
                {
                    Character ch = str.charAt(str.length()-1);
                    if(ch != '/' && ch != '*' && ch != '+' && ch != '-')
                    {
                        str = str.concat(multibtn.getText());
                        displayFeild.setText(str);
                    }
                }
                else if(e.getSource() == minusbtn)
                {
                    Character ch = str.charAt(str.length()-1);
                    if(ch != '/' && ch != '*' && ch != '+' && ch != '-')
                    {
                        str = str.concat(minusbtn.getText());
                        displayFeild.setText(str);
                    }
                }
                else if(e.getSource() == subbtn)
                {
                    Character ch = str.charAt(str.length()-1);
                    if(ch != '/' && ch != '*' && ch != '+' && ch != '-')
                    {
                        str = str.concat(subbtn.getText());
                        displayFeild.setText(str);
                    }
                }
                else if(e.getSource() == plusbtn)
                {
                    Character ch = str.charAt(str.length()-1);
                    if(ch != '/' && ch != '*' && ch != '+' && ch != '-')
                    {
                        str = str.concat(plusbtn.getText());
                        displayFeild.setText(str);
                    }
                }
                else if(e.getSource() == pointbtn)
                {
                    if(!str.contains("."))
                    {
                        str = str.concat(pointbtn.getText());
                        displayFeild.setText(str);
                    }
                }
                else if(e.getSource() == clrbtn)
                {
                    displayFeild.setText("");
                }
                else if(e.getSource() == delbtn)
                {
                    if(!str.isEmpty())
                    {
                        StringBuffer sb= new StringBuffer(str);
                        sb.deleteCharAt(sb.length()-1);
                        displayFeild.setText(String.valueOf(sb));
                    }

                }
                else if(e.getSource() == eqlbtn)
                {
                    check = 1;
                    int value = evaluate(str);
                    displayFeild.setText(String.valueOf(value));

                }
        }
        /*
        void custom_eval(String str)
        {
            int start = 0;

            for(int i = 0; i<str.length(); i++)
            {
                if(str.charAt(i) == '*' || str.charAt(i) == '-' || str.charAt(i) == '+' || str.charAt(i) = '/')
                {
                    int n = Integer.parseInt(str.substring(start, i));
                }
            }
        } */
        public int evaluate(String expression)
        {
            char[] tokens = expression.toCharArray();

            // Stack for numbers: 'values'
            Stack<Integer> values = new
                    Stack<Integer>();

            // Stack for Operators: 'ops'
            Stack<Character> ops = new
                    Stack<Character>();

            for (int i = 0; i < tokens.length; i++)
            {

                // Current token is a
                // whitespace, skip it
                if (tokens[i] == ' ')
                    continue;

                // Current token is a number,
                // push it to stack for numbers
                if (tokens[i] >= '0' &&
                        tokens[i] <= '9')
                {
                    StringBuffer sbuf = new
                            StringBuffer();

                    // There may be more than one
                    // digits in number
                    while (i < tokens.length &&
                            tokens[i] >= '0' &&
                            tokens[i] <= '9')
                        sbuf.append(tokens[i++]);
                    values.push(Integer.parseInt(sbuf.
                            toString()));

                    // right now the i points to
                    // the character next to the digit,
                    // since the for loop also increases
                    // the i, we would skip one
                    //  token position; we need to
                    // decrease the value of i by 1 to
                    // correct the offset.
                    i--;
                }

                // Current token is an opening brace,
                // push it to 'ops'
                else if (tokens[i] == '(')
                    ops.push(tokens[i]);

                    // Closing brace encountered,
                    // solve entire brace
                else if (tokens[i] == ')')
                {
                    while (ops.peek() != '(')
                        values.push(applyOp(ops.pop(),
                                values.pop(),
                                values.pop()));
                    ops.pop();
                }

                // Current token is an operator.
                else if (tokens[i] == '+' ||
                        tokens[i] == '-' ||
                        tokens[i] == '*' ||
                        tokens[i] == '/')
                {
                    // While top of 'ops' has same
                    // or greater precedence to current
                    // token, which is an operator.
                    // Apply operator on top of 'ops'
                    // to top two elements in values stack
                    while (!ops.empty() &&
                            hasPrecedence(tokens[i],
                                    ops.peek()))
                        values.push(applyOp(ops.pop(),
                                values.pop(),
                                values.pop()));

                    // Push current token to 'ops'.
                    ops.push(tokens[i]);
                }
            }

            // Entire expression has been
            // parsed at this point, apply remaining
            // ops to remaining values
            while (!ops.empty())
                values.push(applyOp(ops.pop(),
                        values.pop(),
                        values.pop()));

            // Top of 'values' contains
            // result, return it
            return values.pop();
        }

        // Returns true if 'op2' has higher
        // or same precedence as 'op1',
        // otherwise returns false.
        public  boolean hasPrecedence(
                char op1, char op2)
        {
            if (op2 == '(' || op2 == ')')
                return false;
            if ((op1 == '*' || op1 == '/') &&
                    (op2 == '+' || op2 == '-'))
                return false;
            else
                return true;
        }

        // A utility method to apply an
        // operator 'op' on operands 'a'
        // and 'b'. Return the result.
        public  int applyOp(char op,
                                  int b, int a)
        {
            switch (op)
            {
                case '+':
                    return a + b;
                case '-':
                    return a - b;
                case '*':
                    return a * b;
                case '/':
                    if (b == 0)
                        throw new
                                UnsupportedOperationException(
                                "Cannot divide by zero");
                    return a / b;
            }
            return 0;
        }
    }
    public static void main(String[] args) {
        Calculator frame = new Calculator();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,300,450);
        frame.setTitle("Calculator");
        frame.setResizable(false);
    }
}

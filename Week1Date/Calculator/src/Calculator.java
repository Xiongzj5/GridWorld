import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Calculator extends JFrame{
	private JButton plus = new JButton("+");
	private JButton minus = new JButton("-");
	private JButton multiple = new JButton("*");
	private JButton divide = new JButton("/");
	private JButton ok = new JButton("OK");
	private JButton equal = new JButton("=");
	private JLabel symbol = new JLabel("",JLabel.CENTER);
	private JLabel result = new JLabel("",JLabel.CENTER);
	private JTextField input1 = new JTextField();
	private JTextField input2 = new JTextField();
	public Calculator(){

		
		getContentPane().setLayout(new GridLayout(2,5));
		getContentPane().add(input1);
		getContentPane().add(symbol);
		getContentPane().add(input2);
		getContentPane().add(equal);
		getContentPane().add(result);
		getContentPane().add(plus);
		getContentPane().add(minus);
		getContentPane().add(multiple);
		getContentPane().add(divide);
		getContentPane().add(ok);
		setSize(300,150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		input1.setHorizontalAlignment(JTextField.CENTER);
		input2.setHorizontalAlignment(JTextField.CENTER);
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String name = ((JButton)e.getSource()).getText();
				symbol.setText(name);
			}
		};
		ActionListener getresult = new ActionListener() {
			public void actionPerformed(ActionEvent x){
				result.setText("");
				String str1 = input1.getText();
				String str2 = input2.getText();
				double num1 = Double.parseDouble(str1);
				double num2 = Double.parseDouble(str2);
				double num3 = 0;
				if(symbol.getText().equals("+")){
					num3 = num2 + num1;
				}
				if(symbol.getText().equals("-")){
					num3 = num1 - num2;
				}
				if(symbol.getText().equals("*")){
					num3 = num2 * num1;
				}
				if(symbol.getText().equals("/")){
					num3 = num1 / num2;
				}
				String str3 = "" + num3;
				result.setText(str3);
			}
		};
		plus.addActionListener(al);
		minus.addActionListener(al);
		multiple.addActionListener(al);
		divide.addActionListener(al);
		equal.addActionListener(getresult);
		ok.addActionListener(getresult);
	}

	
	public static void main(String args[]){
		(new Calculator()).setVisible(true);
	}
}
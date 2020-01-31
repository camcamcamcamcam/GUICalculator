import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Window;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;

public class Calculator {

	private JFrame frame;
	private String equation = "";
	static JToggleButton degrad;
	static String answer = "0";
	static boolean isdeg = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frame.setVisible(true);
					window.frame.setTitle("Calculator");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 340, 280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);
		// Initializing Grid Layout

		TextField textField = new TextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				equation = textField.getText();
				if (equation.charAt(equation.length() - 1) == '=') {
					equation = equation.substring(0, equation.length() - 1);
					if (equation.equals("Full marks")) {
						textField.setText("Hooray!");
						// Easter egg
					} else if (equation.equals("Life the universe and everything")) {
						equation += "=42";
						textField.setText(equation);
					} else {
						equation = equation.replaceAll("\\s", "");
						textField.setText(equation);
						// Gets rid of all the spaces.
						answer = Operations.isolate(textField.getText());
						textField.setText(equation + "=" + answer);
						equation = "";
					}
				}
			}
		});
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridwidth = 5;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		frame.getContentPane().add(textField, gbc_textField);
		// Initializing Text box.

		JButton equals = new JButton("=");
		equals.setForeground(Color.WHITE);
		equals.setBackground(Color.GRAY);
		equals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (equation.equals("")) {
					equation = textField.getText();
					for (int i = 0; i < equation.length(); i++) {
						if (equation.charAt(i) == '=') {
							equation = equation.substring(0, i);
						}
					}
					System.out.println("added: " + equation);
					// Added the ability to repeat the equation when = is
					// pressed again
				}
				if (equation.equals("Full marks")) {
					textField.setText("Hooray!");
					// Easter egg
				} else if (equation.equals("Life the universe and everything")) {
					equation += "=42";
					textField.setText(equation);
				} else {
					equation = equation.replaceAll("\\s", "");
					textField.setText(equation);
					// Gets rid of all the spaces.
					answer = Operations.isolate(textField.getText());
					textField.setText(equation);
					textField.setText(equation + "=" + answer);
					equation = "";
					// resets the equation
				}
			}
		});

		degrad = new JToggleButton("Degrees");
		degrad.setSelected(true);
		degrad.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_degrad = new GridBagConstraints();
		gbc_degrad.fill = GridBagConstraints.BOTH;
		gbc_degrad.gridwidth = 5;
		gbc_degrad.insets = new Insets(0, 0, 5, 5);
		gbc_degrad.gridx = 0;
		gbc_degrad.gridy = 1;
		frame.getContentPane().add(degrad, gbc_degrad);
		degrad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				isdeg = degrad.isSelected();
				System.out.println("State:" + isdeg);
				if (isdeg) {
					degrad.setText("Degrees");
				} else {
					degrad.setText("Radians");
				}
			}
		});

		JButton btnLn = new JButton("ln");
		btnLn.setForeground(Color.WHITE);
		btnLn.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_btnLn = new GridBagConstraints();
		gbc_btnLn.fill = GridBagConstraints.BOTH;
		gbc_btnLn.insets = new Insets(0, 0, 5, 5);
		gbc_btnLn.gridx = 0;
		gbc_btnLn.gridy = 2;
		frame.getContentPane().add(btnLn, gbc_btnLn);
		btnLn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "ln";
				textField.setText(equation);
			}
		});

		JButton btnE = new JButton("e^");
		btnE.setForeground(Color.WHITE);
		btnE.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_btnE = new GridBagConstraints();
		gbc_btnE.fill = GridBagConstraints.BOTH;
		gbc_btnE.insets = new Insets(0, 0, 5, 5);
		gbc_btnE.gridx = 1;
		gbc_btnE.gridy = 2;
		frame.getContentPane().add(btnE, gbc_btnE);
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "e^";
				textField.setText(equation);
			}
		});

		JButton btnSin_1 = new JButton("sin-1");
		btnSin_1.setForeground(Color.WHITE);
		btnSin_1.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_btnSin_1 = new GridBagConstraints();
		gbc_btnSin_1.fill = GridBagConstraints.BOTH;
		gbc_btnSin_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnSin_1.gridx = 2;
		gbc_btnSin_1.gridy = 2;
		frame.getContentPane().add(btnSin_1, gbc_btnSin_1);
		btnSin_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "sin-1";
				textField.setText(equation);
			}
		});

		JButton btnCos_1 = new JButton("cos-1");
		btnCos_1.setForeground(Color.WHITE);
		btnCos_1.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_btnCos_1 = new GridBagConstraints();
		gbc_btnCos_1.fill = GridBagConstraints.BOTH;
		gbc_btnCos_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnCos_1.gridx = 3;
		gbc_btnCos_1.gridy = 2;
		frame.getContentPane().add(btnCos_1, gbc_btnCos_1);
		btnCos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "cos-1";
				textField.setText(equation);
			}
		});

		JButton btnTan_1 = new JButton("tan-1");
		btnTan_1.setForeground(Color.WHITE);
		btnTan_1.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_btnTan_1 = new GridBagConstraints();
		gbc_btnTan_1.fill = GridBagConstraints.BOTH;
		gbc_btnTan_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnTan_1.gridx = 4;
		gbc_btnTan_1.gridy = 2;
		frame.getContentPane().add(btnTan_1, gbc_btnTan_1);
		btnTan_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "tan-1";
				textField.setText(equation);
			}
		});

		JButton btnLog = new JButton("log");
		btnLog.setForeground(Color.WHITE);
		btnLog.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_btnLog = new GridBagConstraints();
		gbc_btnLog.fill = GridBagConstraints.BOTH;
		gbc_btnLog.insets = new Insets(0, 0, 5, 5);
		gbc_btnLog.gridx = 0;
		gbc_btnLog.gridy = 3;
		frame.getContentPane().add(btnLog, gbc_btnLog);
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "log";
				textField.setText(equation);
			}
		});

		JButton btn_10 = new JButton("10^");
		btn_10.setForeground(Color.WHITE);
		btn_10.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_btn_10 = new GridBagConstraints();
		gbc_btn_10.fill = GridBagConstraints.BOTH;
		gbc_btn_10.insets = new Insets(0, 0, 5, 5);
		gbc_btn_10.gridx = 1;
		gbc_btn_10.gridy = 3;
		frame.getContentPane().add(btn_10, gbc_btn_10);
		btn_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "10^";
				textField.setText(equation);
			}
		});

		JButton btnSin = new JButton("sin");
		btnSin.setForeground(Color.WHITE);
		btnSin.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_btnSin = new GridBagConstraints();
		gbc_btnSin.fill = GridBagConstraints.BOTH;
		gbc_btnSin.insets = new Insets(0, 0, 5, 5);
		gbc_btnSin.gridx = 2;
		gbc_btnSin.gridy = 3;
		frame.getContentPane().add(btnSin, gbc_btnSin);
		btnSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "sin";
				textField.setText(equation);
			}
		});

		JButton btnCos = new JButton("cos");
		btnCos.setForeground(Color.WHITE);
		btnCos.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_btnCos = new GridBagConstraints();
		gbc_btnCos.fill = GridBagConstraints.BOTH;
		gbc_btnCos.insets = new Insets(0, 0, 5, 5);
		gbc_btnCos.gridx = 3;
		gbc_btnCos.gridy = 3;
		frame.getContentPane().add(btnCos, gbc_btnCos);
		btnCos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "cos";
				textField.setText(equation);
			}
		});

		JButton btnTan = new JButton("tan");
		btnTan.setForeground(Color.WHITE);
		btnTan.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_btnTan = new GridBagConstraints();
		gbc_btnTan.fill = GridBagConstraints.BOTH;
		gbc_btnTan.insets = new Insets(0, 0, 5, 0);
		gbc_btnTan.gridx = 4;
		gbc_btnTan.gridy = 3;
		frame.getContentPane().add(btnTan, gbc_btnTan);
		btnTan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "tan";
				textField.setText(equation);
			}
		});

		JButton root = new JButton("√");
		root.setForeground(Color.WHITE);
		root.setBackground(Color.GRAY);
		root.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (equation == "") {
					equation += "Ans√";
				} else {
					equation += "√";
				}
				textField.setText(equation);
			}
		});

		JButton modulus = new JButton("%");
		modulus.setForeground(Color.WHITE);
		modulus.setBackground(Color.GRAY);
		modulus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (equation == "") {
					equation += "Ans%";
				} else {
					equation += "%";
				}
				textField.setText(equation);
			}
		});

		JButton factorial = new JButton("!");
		factorial.setForeground(Color.WHITE);
		factorial.setBackground(Color.GRAY);
		factorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (equation == "") {
					equation += "Ans!";
				} else {
					equation += "!";
				}
				textField.setText(equation);
			}
		});

		JButton power = new JButton("^");
		power.setForeground(Color.WHITE);
		power.setBackground(Color.GRAY);
		power.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Adds the answer to the start if operator is pressed first.
				if (equation == "") {
					equation += "Ans^";
				} else {
					equation += "^";
				}
				textField.setText(equation);
			}
		});

		JButton openBracket = new JButton("(");
		openBracket.setForeground(Color.WHITE);
		openBracket.setBackground(Color.GRAY);
		openBracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "(";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_openBracket = new GridBagConstraints();
		gbc_openBracket.fill = GridBagConstraints.BOTH;
		gbc_openBracket.insets = new Insets(0, 0, 5, 5);
		gbc_openBracket.gridx = 0;
		gbc_openBracket.gridy = 4;
		frame.getContentPane().add(openBracket, gbc_openBracket);

		JButton closeBracket = new JButton(")");
		closeBracket.setForeground(Color.WHITE);
		closeBracket.setBackground(Color.GRAY);
		closeBracket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += ")";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_closeBracket = new GridBagConstraints();
		gbc_closeBracket.fill = GridBagConstraints.BOTH;
		gbc_closeBracket.insets = new Insets(0, 0, 5, 5);
		gbc_closeBracket.gridx = 1;
		gbc_closeBracket.gridy = 4;
		frame.getContentPane().add(closeBracket, gbc_closeBracket);

		JButton clear = new JButton("C");
		clear.setForeground(Color.WHITE);
		clear.setBackground(Color.RED);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation = "";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_clear = new GridBagConstraints();
		gbc_clear.fill = GridBagConstraints.BOTH;
		gbc_clear.insets = new Insets(0, 0, 5, 5);
		gbc_clear.gridx = 2;
		gbc_clear.gridy = 4;
		frame.getContentPane().add(clear, gbc_clear);
		GridBagConstraints gbc_power = new GridBagConstraints();
		gbc_power.fill = GridBagConstraints.BOTH;
		gbc_power.insets = new Insets(0, 0, 5, 5);
		gbc_power.gridx = 0;
		gbc_power.gridy = 5;
		frame.getContentPane().add(power, gbc_power);
		GridBagConstraints gbc_factorial = new GridBagConstraints();
		gbc_factorial.fill = GridBagConstraints.BOTH;
		gbc_factorial.insets = new Insets(0, 0, 5, 5);
		gbc_factorial.gridx = 0;
		gbc_factorial.gridy = 6;
		frame.getContentPane().add(factorial, gbc_factorial);
		GridBagConstraints gbc_modulus = new GridBagConstraints();
		gbc_modulus.fill = GridBagConstraints.BOTH;
		gbc_modulus.insets = new Insets(0, 0, 5, 5);
		gbc_modulus.gridx = 0;
		gbc_modulus.gridy = 7;
		frame.getContentPane().add(modulus, gbc_modulus);
		GridBagConstraints gbc_root = new GridBagConstraints();
		gbc_root.fill = GridBagConstraints.BOTH;
		gbc_root.insets = new Insets(0, 0, 0, 5);
		gbc_root.gridx = 0;
		gbc_root.gridy = 8;
		frame.getContentPane().add(root, gbc_root);
		GridBagConstraints gbc_equals = new GridBagConstraints();
		gbc_equals.fill = GridBagConstraints.BOTH;
		gbc_equals.gridx = 4;
		gbc_equals.gridy = 8;
		frame.getContentPane().add(equals, gbc_equals);

		JButton backspace = new JButton("←");
		backspace.setForeground(Color.WHITE);
		backspace.setBackground(Color.BLUE);
		backspace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (equation.length() != 0) {
					equation = equation.substring(0, equation.length() - 1);
					textField.setText(equation);
				}
			}
		});
		GridBagConstraints gbc_backspace = new GridBagConstraints();
		gbc_backspace.fill = GridBagConstraints.BOTH;
		gbc_backspace.insets = new Insets(0, 0, 5, 5);
		gbc_backspace.gridx = 3;
		gbc_backspace.gridy = 4;
		frame.getContentPane().add(backspace, gbc_backspace);

		JButton divide = new JButton("/");
		divide.setForeground(Color.WHITE);
		divide.setBackground(Color.GRAY);
		divide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (equation == "") {
					equation += "Ans/";
				} else {
					equation += "/";
				}
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_divide = new GridBagConstraints();
		gbc_divide.fill = GridBagConstraints.BOTH;
		gbc_divide.insets = new Insets(0, 0, 5, 0);
		gbc_divide.gridx = 4;
		gbc_divide.gridy = 4;
		frame.getContentPane().add(divide, gbc_divide);

		JButton num7 = new JButton("7");
		num7.setBackground(Color.LIGHT_GRAY);
		num7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "7";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_num7 = new GridBagConstraints();
		gbc_num7.fill = GridBagConstraints.BOTH;
		gbc_num7.insets = new Insets(0, 0, 5, 5);
		gbc_num7.gridx = 1;
		gbc_num7.gridy = 5;
		frame.getContentPane().add(num7, gbc_num7);

		JButton num8 = new JButton("8");
		num8.setBackground(Color.LIGHT_GRAY);
		num8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "8";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_num8 = new GridBagConstraints();
		gbc_num8.fill = GridBagConstraints.BOTH;
		gbc_num8.insets = new Insets(0, 0, 5, 5);
		gbc_num8.gridx = 2;
		gbc_num8.gridy = 5;
		frame.getContentPane().add(num8, gbc_num8);

		JButton num9 = new JButton("9");
		num9.setBackground(Color.LIGHT_GRAY);
		num9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "9";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_num9 = new GridBagConstraints();
		gbc_num9.fill = GridBagConstraints.BOTH;
		gbc_num9.insets = new Insets(0, 0, 5, 5);
		gbc_num9.gridx = 3;
		gbc_num9.gridy = 5;
		frame.getContentPane().add(num9, gbc_num9);

		JButton multiply = new JButton("*");
		multiply.setForeground(Color.WHITE);
		multiply.setBackground(Color.GRAY);
		multiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (equation == "") {
					equation += "Ans*";
				} else {
					equation += "*";
				}
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_multiply = new GridBagConstraints();
		gbc_multiply.fill = GridBagConstraints.BOTH;
		gbc_multiply.insets = new Insets(0, 0, 5, 0);
		gbc_multiply.gridx = 4;
		gbc_multiply.gridy = 5;
		frame.getContentPane().add(multiply, gbc_multiply);

		JButton num4 = new JButton("4");
		num4.setBackground(Color.LIGHT_GRAY);
		num4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "4";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_num4 = new GridBagConstraints();
		gbc_num4.fill = GridBagConstraints.BOTH;
		gbc_num4.insets = new Insets(0, 0, 5, 5);
		gbc_num4.gridx = 1;
		gbc_num4.gridy = 6;
		frame.getContentPane().add(num4, gbc_num4);

		JButton num5 = new JButton("5");
		num5.setBackground(Color.LIGHT_GRAY);
		num5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "5";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_num5 = new GridBagConstraints();
		gbc_num5.fill = GridBagConstraints.BOTH;
		gbc_num5.insets = new Insets(0, 0, 5, 5);
		gbc_num5.gridx = 2;
		gbc_num5.gridy = 6;
		frame.getContentPane().add(num5, gbc_num5);

		JButton num6 = new JButton("6");
		num6.setBackground(Color.LIGHT_GRAY);
		num6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "6";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_num6 = new GridBagConstraints();
		gbc_num6.fill = GridBagConstraints.BOTH;
		gbc_num6.insets = new Insets(0, 0, 5, 5);
		gbc_num6.gridx = 3;
		gbc_num6.gridy = 6;
		frame.getContentPane().add(num6, gbc_num6);

		JButton minus = new JButton("-");
		minus.setForeground(Color.WHITE);
		minus.setBackground(Color.GRAY);
		minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "-";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_minus = new GridBagConstraints();
		gbc_minus.fill = GridBagConstraints.BOTH;
		gbc_minus.insets = new Insets(0, 0, 5, 0);
		gbc_minus.gridx = 4;
		gbc_minus.gridy = 6;
		frame.getContentPane().add(minus, gbc_minus);

		JButton num1 = new JButton("1");
		num1.setBackground(Color.LIGHT_GRAY);
		num1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "1";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_num1 = new GridBagConstraints();
		gbc_num1.fill = GridBagConstraints.BOTH;
		gbc_num1.insets = new Insets(0, 0, 5, 5);
		gbc_num1.gridx = 1;
		gbc_num1.gridy = 7;
		frame.getContentPane().add(num1, gbc_num1);

		JButton num2 = new JButton("2");
		num2.setBackground(Color.LIGHT_GRAY);
		num2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "2";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_num2 = new GridBagConstraints();
		gbc_num2.fill = GridBagConstraints.BOTH;
		gbc_num2.insets = new Insets(0, 0, 5, 5);
		gbc_num2.gridx = 2;
		gbc_num2.gridy = 7;
		frame.getContentPane().add(num2, gbc_num2);

		JButton num3 = new JButton("3");
		num3.setBackground(Color.LIGHT_GRAY);
		num3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "3";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_num3 = new GridBagConstraints();
		gbc_num3.fill = GridBagConstraints.BOTH;
		gbc_num3.insets = new Insets(0, 0, 5, 5);
		gbc_num3.gridx = 3;
		gbc_num3.gridy = 7;
		frame.getContentPane().add(num3, gbc_num3);

		JButton plus = new JButton("+");
		plus.setForeground(Color.WHITE);
		plus.setBackground(Color.GRAY);
		plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (equation == "") {
					equation += "Ans+";
				} else {
					equation += "+";
				}
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_plus = new GridBagConstraints();
		gbc_plus.fill = GridBagConstraints.BOTH;
		gbc_plus.insets = new Insets(0, 0, 5, 0);
		gbc_plus.gridx = 4;
		gbc_plus.gridy = 7;
		frame.getContentPane().add(plus, gbc_plus);

		JButton ans = new JButton("Ans");
		ans.setBackground(Color.LIGHT_GRAY);
		ans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "Ans";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_ans = new GridBagConstraints();
		gbc_ans.fill = GridBagConstraints.BOTH;
		gbc_ans.insets = new Insets(0, 0, 0, 5);
		gbc_ans.gridx = 1;
		gbc_ans.gridy = 8;
		frame.getContentPane().add(ans, gbc_ans);

		JButton num0 = new JButton("0");
		num0.setBackground(Color.LIGHT_GRAY);
		num0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += "0";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_num0 = new GridBagConstraints();
		gbc_num0.fill = GridBagConstraints.BOTH;
		gbc_num0.insets = new Insets(0, 0, 0, 5);
		gbc_num0.gridx = 2;
		gbc_num0.gridy = 8;
		frame.getContentPane().add(num0, gbc_num0);

		JButton decimal = new JButton(".");
		decimal.setBackground(Color.LIGHT_GRAY);
		decimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation += ".";
				textField.setText(equation);
			}
		});
		GridBagConstraints gbc_decimal = new GridBagConstraints();
		gbc_decimal.fill = GridBagConstraints.BOTH;
		gbc_decimal.insets = new Insets(0, 0, 0, 5);
		gbc_decimal.gridx = 3;
		gbc_decimal.gridy = 8;
		frame.getContentPane().add(decimal, gbc_decimal);
	}

}

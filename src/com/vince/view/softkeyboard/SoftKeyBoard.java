/**
 * @author 流浪大法师
 * @time 2016-3-13 下午7:37:34
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.view.softkeyboard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class SoftKeyBoard extends JDialog {
	private JWindow   owner = null;
	private JButton  shift = null;
	private JButton  cancel = null;
	private JButton  exit_button = null;
	private JButton  caps_lock = null;
	private String[] alphabet_label_one = {"q","w","e","r","t","y","u","i","o","p","a","s","d"};
	private String[] alphabet_label_one_to_upper_case = {"Q","W","E","R","T","Y","U","I","O","P","A","S","D"};
	private String[] alphabet_label_two = {"f","g","h","j","k","l","z","x","c","v","b","n","m"};
	private String[] alphabet_label_two_to_upper_case = {"F","G","H","J","K","L","Z","X","C","V","B","N","M"};
	private String[] number_label_one = {"`","1","2","3","4","5","6","7","8","9","0"};
	private String[] op_label_one = {",",".","/",";","'","[","]","-","=","\\"};
	private String[] op_label_two = {"<",">","?",":","\"","{","}","_","+","|"};
	private String[] number_label_two = {"~","!","@","#","$","%","^","&","*","(",")"};
	private JButton[] alphabet_one = new JButton[alphabet_label_one.length];
	private JButton[] alphabet_two = new JButton[alphabet_label_two.length];
	private JButton[] number = new JButton[number_label_one.length];
	private JButton[] op = new JButton[op_label_one.length];
	private JPanel panel = null;
	private int button_total_length = 4 + alphabet_label_one.length + alphabet_label_two.length + number_label_one.length + op_label_one.length;
	private int[] alphabet_one_position = {14,57};
	private int[] alphabet_two_position = {14,82};
	private int[] op_position = {42,32};
	private int[] number_position = {14,7};
	private final static int BUTTON_OFFSET = 25;
	private final static int BUTTON_DEFAULT_SIZE = 23;
	private final static Insets DEFAULT_INSETS = new Insets(0, 0, 0, 0);
	private ArrayList<JButton> all_button = new ArrayList<JButton>(button_total_length);
	public SoftKeyBoard(JWindow jWindow) {
		this.owner = jWindow;
		init();
	}
	private void init(){
		initButton(number, number_position, number_label_one);
		initButton(op, op_position, op_label_one);
		initButton(alphabet_one, alphabet_one_position, alphabet_label_one);
		initButton(alphabet_two, alphabet_two_position, alphabet_label_two);
		initOthers();
		initPanel();
		initSoftKeyBoard();
	}
	private void initSoftKeyBoard(){
		Rectangle r = owner.getBounds();
		setBounds(r.x + 120, r.y + r.height -70, 360, 110);
		setUndecorated(true);
		setLayout(null);
		setResizable(false);
		setBackground(Color.blue);
	}
	private void initPanel(){
		panel = new JPanel();
		panel.setSize(360, 110);
		panel.setBackground(new Color(32, 90, 170));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createLineBorder(Color.gray)));
		getContentPane().add(panel);
	}
	private void initButton(JButton[] button,int[] position,String[] str){
		for(int i = 0; i < button.length; i++){
			button[i] = new JButton(str[i]);
			button[i].setBounds(position[0], position[1],BUTTON_DEFAULT_SIZE , BUTTON_DEFAULT_SIZE);
			initSomeInterface(button[i]);
			button[i].setBackground(Color.white);
			add(button[i]);
			all_button.add(button[i]);
			position[0] += BUTTON_OFFSET;
		}
	}
	private void initOthers(){
		/*初始化shift按钮*/
		shift = new JButton("Shift");
		shift.setBounds(3, 32, 37, 23);
		initSomeInterface(shift);
		shift.setBackground(Color.white);
		/*初始化cancel按钮*/
		cancel = new Arrow();
		cancel.setBounds(290, 7, 46,23);
		initSomeInterface(cancel);
		cancel.setBackground(Color.white);
		/*初始化caps_lock按钮*/
		caps_lock = new JButton("Caps lock");
		caps_lock.setBounds(292, 32, 63, 23);
		initSomeInterface(caps_lock);
		caps_lock.setBackground(Color.white);
		/*初始化exit按钮*/
		exit_button = new Exit();
		exit_button.setBounds(345, 5, 10, 10);
		initSomeInterface(exit_button);
		exit_button.setBackground(new Color(32, 90, 170));
		add(shift);add(cancel);add(caps_lock);add(exit_button);
		/*实现shift，cancel，caps_lock，exit_button添加到all_button中*/
		all_button.add(shift);all_button.add(cancel);all_button.add(caps_lock);all_button.add(exit_button);
	}
	private void initSomeInterface(JButton button){
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setMargin(DEFAULT_INSETS);
	}
	/**
	 * @return the shift
	 */
	public JButton getShift() {
		return shift;
	}
	/**
	 * @param shift the shift to set
	 */
	public void setShift(JButton shift) {
		this.shift = shift;
	}
	/**
	 * @return the alphabet_label_one
	 */
	public String[] getAlphabet_label_one() {
		return alphabet_label_one;
	}
	/**
	 * @param alphabet_label_one the alphabet_label_one to set
	 */
	public void setAlphabet_label_one(String[] alphabet_label_one) {
		this.alphabet_label_one = alphabet_label_one;
	}
	/**
	 * @return the alphabet_label_one_to_upper_case
	 */
	public String[] getAlphabet_label_one_to_upper_case() {
		return alphabet_label_one_to_upper_case;
	}
	/**
	 * @param alphabet_label_one_to_upper_case the alphabet_label_one_to_upper_case to set
	 */
	public void setAlphabet_label_one_to_upper_case(
			String[] alphabet_label_one_to_upper_case) {
		this.alphabet_label_one_to_upper_case = alphabet_label_one_to_upper_case;
	}
	/**
	 * @return the number_label_one
	 */
	public String[] getNumber_label_one() {
		return number_label_one;
	}
	/**
	 * @param number_label_one the number_label_one to set
	 */
	public void setNumber_label_one(String[] number_label_one) {
		this.number_label_one = number_label_one;
	}
	/**
	 * @return the op_label_one
	 */
	public String[] getOp_label_one() {
		return op_label_one;
	}
	/**
	 * @param op_label_one the op_label_one to set
	 */
	public void setOp_label_one(String[] op_label_one) {
		this.op_label_one = op_label_one;
	}
	/**
	 * @return the op_label_two
	 */
	public String[] getOp_label_two() {
		return op_label_two;
	}
	/**
	 * @param op_label_two the op_label_two to set
	 */
	public void setOp_label_two(String[] op_label_two) {
		this.op_label_two = op_label_two;
	}
	/**
	 * @return the number_label_two
	 */
	public String[] getNumber_label_two() {
		return number_label_two;
	}
	/**
	 * @param number_label_two the number_label_two to set
	 */
	public void setNumber_label_two(String[] number_label_two) {
		this.number_label_two = number_label_two;
	}
	/**
	 * @return the alphabet_label_two
	 */
	public String[] getAlphabet_label_two() {
		return alphabet_label_two;
	}
	/**
	 * @param alphabet_label_two the alphabet_label_two to set
	 */
	public void setAlphabet_label_two(String[] alphabet_label_two) {
		this.alphabet_label_two = alphabet_label_two;
	}
	/**
	 * @return the alphabet_label_two_to_upper_case
	 */
	public String[] getAlphabet_label_two_to_upper_case() {
		return alphabet_label_two_to_upper_case;
	}
	/**
	 * @param alphabet_label_two_to_upper_case the alphabet_label_two_to_upper_case to set
	 */
	public void setAlphabet_label_two_to_upper_case(
			String[] alphabet_label_two_to_upper_case) {
		this.alphabet_label_two_to_upper_case = alphabet_label_two_to_upper_case;
	}
	/**
	 * @return the cancel
	 */
	public JButton getCancel() {
		return cancel;
	}
	/**
	 * @param cancel the cancel to set
	 */
	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}
	/**
	 * @return the exit_button
	 */
	public JButton getExit_button() {
		return exit_button;
	}
	/**
	 * @param exit_button the exit_button to set
	 */
	public void setExit_button(JButton exit_button) {
		this.exit_button = exit_button;
	}
	/**
	 * @return the caps_lock
	 */
	public JButton getCaps_lock() {
		return caps_lock;
	}
	/**
	 * @param caps_lock the caps_lock to set
	 */
	public void setCaps_lock(JButton caps_lock) {
		this.caps_lock = caps_lock;
	}
	/**
	 * @return the all_button
	 */
	public ArrayList<JButton> getAll_button() {
		return all_button;
	}
	/**
	 * @param all_button the all_button to set
	 */
	public void setAll_button(ArrayList<JButton> all_button) {
		this.all_button = all_button;
	}
}
final class Arrow extends JButton
{
	public void paint(Graphics g)
	{
		super.paint(g);
		float lineWidth = 1.0f;
	    ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
	    ((Graphics2D)g).drawLine(15, 11, 31, 11);
	    ((Graphics2D)g).drawLine(14, 11, 16, 9);
	    ((Graphics2D)g).drawLine(14, 11, 16, 13);

	}
}
final class Exit extends JButton
{
	public void paint(Graphics g)
	{
		super.paint(g);
		float lineWidth=1.5f;
		((Graphics2D)g).setStroke(new BasicStroke(lineWidth));
		((Graphics2D)g).setColor(Color.gray);
		((Graphics2D)g).drawLine(2, 2, 8, 8);
		((Graphics2D)g).drawLine(2, 8, 8, 2);
	}
}

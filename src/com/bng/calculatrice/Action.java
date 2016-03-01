package com.bng.calculatrice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Action implements ActionListener{
	
	private JButton button;
	private Calculatrice cal;

	public Action(Calculatrice calculatrice, JButton button) {
		this.cal=calculatrice;
		this.button=button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!button.getText().equals("MC") &&  !button.getText().equals("MR") &&  
				!button.getText().equals("MS")&& !button.getText().equals("M+") && 
				!button.getText().equals("M-") && !button.getText().equals("\u2190")&&
				!button.getText().equals("CE") && !button.getText().equals("C") && 
				!button.getText().equals("\u00B1") && !button.getText().equals("\u221A") &&
				!button.getText().equals("=")
		){
			if(button.getText().equals("+") || button.getText().equals("-") || button.getText().equals("*") || button.getText().equals("/"))
			cal.ajouterText(" "+button.getText()+" ");
			else
				cal.ajouterText(button.getText());
		}
		if(button.getText().equals("\u00B1")){
			String val=cal.getafficheur().substring(0, 1);
			if(val.equals("-"))
				cal.setText("+"+cal.getafficheur().substring(1));
			else
				cal.setText("-"+cal.getafficheur().substring(1));				
		}
		if(button.getText().equals("C")){
			cal.setText("0");
		}
		if(button.getText().equals("\u2190")){
			if(cal.getafficheur().length()!=1)
				cal.setText(cal.getafficheur().substring(0, cal.getafficheur().length()-1));
			else
				cal.setText("0");
		}
		if(button.getText().equals("=")){
			String value=cal.getafficheur();
			String[] plus =value.split(" ");
			if(plus.length!=0){
				int som=0;
				String op="";
				for(int i=0; i<plus.length; i++){
					if(i%2!=1)
						som=calcule(op, som, Integer.parseInt(plus[i]));
					else
						op=plus[i];
				}
				cal.setText(som+"");
			}			
		}		
	}	
	
	public int calcule(String op, int resultat, int val){
		switch(op){
		case "+": resultat+=val;break;
		case "-": resultat-=val;break;
		case "*": resultat*=val;break;
		case "/": resultat/=val;break;
		default : resultat=val;
		}
		return resultat;
	}
}

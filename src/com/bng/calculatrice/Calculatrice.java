package com.bng.calculatrice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
/**
 * Calculatrice
 * @author bng (BASSIROU NGOM)(bassiroungom26@gmail.com, https://github.com/bngesp)
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Calculatrice extends JFrame {
	private JButton[] bouton;
	private JPanel panobouton, panoafficheur;
	private JLabel afficheur;
	
	public Calculatrice(){
		super("Calculatrice");
		this.setSize(275, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		//this.add(new JLabel("0"));
		bouton=new JButton[28];
		afficheur=new JLabel("0");
		afficheur.setFont(new Font("ubuntu", Font.PLAIN, 40));	
		//afficheur.setHorizontalAlignment(afficheur.getWidth());
		panoafficheur=new JPanel();panoafficheur.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panobouton = new JPanel();
		panobouton.setLayout(new GridBagLayout());
		afficheur.setSize(this.getWidth(), 50);
		panoafficheur.add(afficheur);
		this.add(panoafficheur,BorderLayout.NORTH);
		this.add(panobouton);
		ajoutBoutons();
		ajoutMenu();
		this.setVisible(true);
	}
	
	public void ajoutBoutons(){
		GridBagConstraints p=new GridBagConstraints();
		int posx[]=new int[28];
		int posy[]=new int[28];
		int heigth[]=new int[28];
		int width[]=new int[28];
		String value[]={
				"MC", "MR", "MS", "M+", "M-","\u2190", "CE", "C", "\u00B1", "\u221A",
				"7", "8", "9", "/","%", "4", "5", "6", "*", "1/x",
				"1", "2", "3", "-", "=", "0", ",","+" 
				};
		for(int i=0, j=0, k=0; i<28; i++){
			heigth[i]=1;
			width[i]=1;
			//if()
			if(i!=0 && i%5==0){j=0; k++;}
			posx[i]=2*j;posy[i]=k;
			j++;
		}
		posx[26]=4;heigth[24]=5; heigth[25]=3;
		posx[27]=6;width[24]=3; width[25]=4;
		for(int i=0; i<28; i++){ 
			p.fill=GridBagConstraints.BOTH;
			p.gridx=posx[i]; p.gridy=posy[i];
			p.gridheight=heigth[i]; p.gridwidth=width[i];
			p.weightx=2; p.weighty=1;
			//p.ipadx=-12; p.ipady=12;
			panobouton.add(bouton[i]=new JButton(value[i]), p);
			bouton[i].addActionListener(new Action(this, bouton[i]));
			//System.out.println("x="+posx[i]+", y="+posy[i]+"");
		}
		
		
		
	}
	public void setText(String s){
			afficheur.setText(s);	
	}
	public void ajouterText(String s){
		afficheur.setText(afficheur.getText()+s);
	}
	
	public String getafficheur(){
		return this.afficheur.getText();
	}
	public void ajoutMenu(){
		JMenuBar bar=new JMenuBar();
		//le menu affichage
		JMenu affichage=new JMenu("Affichage");
		ButtonGroup b=new ButtonGroup();
		JRadioButtonMenuItem st=new JRadioButtonMenuItem("Standard", true);st.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.ALT_MASK));
		JRadioButtonMenuItem sc=new JRadioButtonMenuItem("Scientifique");sc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.ALT_MASK));
		JRadioButtonMenuItem pro=new JRadioButtonMenuItem("Programmeur");pro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.ALT_MASK));
		JRadioButtonMenuItem sta=new JRadioButtonMenuItem("Statistique");sta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, KeyEvent.ALT_MASK));
		b.add(st);st.setMnemonic('S');
		b.add(sc);sc.setMnemonic('i');
		b.add(pro);pro.setMnemonic('p');
		b.add(sta);sta.setMnemonic('a');
		affichage.add(st);
		affichage.add(sc);
		affichage.add(pro);
		affichage.add(sta);
		affichage.addSeparator();
		JCheckBoxMenuItem his=new JCheckBoxMenuItem("Historique");his.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK));
		JCheckBoxMenuItem gr=new JCheckBoxMenuItem("Groupement des chiffres");
		affichage.add(his);his.setMnemonic('H');
		affichage.add(gr);gr.setMnemonic('g');
		affichage.addSeparator();
		ButtonGroup b1=new ButtonGroup();
		JRadioButtonMenuItem stand=new JRadioButtonMenuItem("Standard", true);stand.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
		JRadioButtonMenuItem conv=new JRadioButtonMenuItem("Conversion d'unité");
		JRadioButtonMenuItem cal=new JRadioButtonMenuItem("Calcul de la date");
		b1.add(stand);stand.setMnemonic('t');
		b1.add(conv);conv.setMnemonic('c');
		b1.add(cal);cal.setMnemonic('l');
		affichage.add(stand);
		affichage.add(conv);
		affichage.add(cal);
		affichage.addSeparator();
		JMenu fe=new JMenu("Feille de calcul");fe.setMnemonic('F');
		fe.add(new JRadioButtonMenuItem("prêt hypothecaire", true));
		fe.add(new JRadioButtonMenuItem("Crédit bail"));
		fe.add(new JRadioButtonMenuItem("economie en carburant(1/100km)"));
		affichage.add(fe);
		bar.add(affichage);
		//le menu edition
		JMenu Edition=new JMenu("Edition");Edition.setMnemonic('E');
		JMenuItem cp=new JMenuItem("Copier", 'C');cp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
		JMenuItem cl=new JMenuItem("Coller", 'O');cl.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
		Edition.add(cp);
		Edition.add(cl);
		bar.add(Edition);
		//Le  menu aide
		JMenu aide=new JMenu("?");aide.setMnemonic('?');
		JMenuItem af=new JMenuItem("Afficher l'aide", 'f');af.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.ALT_MASK));
		aide.add(af);
		aide.addSeparator();
		aide.add(new JMenuItem("Apropos de la calculatrice", 'A'));
		bar.add(aide);
		
		this.setJMenuBar(bar);
		
	}
	
	public static void main(String[] a){
		new Calculatrice();
	}

}
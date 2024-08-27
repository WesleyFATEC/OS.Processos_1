package view;

import javax.swing.JOptionPane;
import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		String[] opc = {"Verificar IP", "Fazer Ping", "Sair"};
		int choice; 
	
	RedesController rc = new RedesController();
	do {
		choice = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Menu", 0, 0, null, opc, opc[0]);
		switch (choice){
		case 0:
			rc.ip();
			break;
		case 1:
			rc.ping();
			break;
		case 2:
			System.exit(0);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Escolha entre uma das três opções");
			break;
		}
			
		}while (choice !=2);
	}

}

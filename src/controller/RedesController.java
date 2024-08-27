package controller;

import java.io.IOException;
import java.util.Scanner;

public class RedesController {
	private String os(){
		return System.getProperty("os.name");
	}
	
	public void ip() {
		String os = os();
		String cmd ="";
		boolean win = false;
		boolean hasIpv4 = false;
		
		if (os.contains("dows")) {
			cmd = "ipconfig";
			win = true;
		}
		else {
			cmd = "ifconfig";
		}
		
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			Scanner scanner = new Scanner(process.getInputStream());
			String linha;
			String adaptador = "";
			
			while (scanner.hasNextLine()) {
				linha = scanner.nextLine();
				if (win) 
				{
					if (linha.contains("Adaptador")) 
					{
					adaptador = linha;
					}
					else if (linha.contains("IPv4")) {
						
						String[] haveipv4 = linha.split (":");
							if (haveipv4.length > 1) {
								System.out.println(adaptador + " | IPv4: " + haveipv4[1] );
								hasIpv4 = true;
							}
					}
				}
				else {
					if (linha.startsWith("en") || linha.startsWith("eth") || linha.startsWith("wlan"))
					{
						adaptador = linha.trim();
					} else if (linha.contains("inet "))
					{
						String [] haveipv4 = linha.split(" ");
						for (String hasIp : haveipv4) {
							if (hasIp.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) 
							{
								System.out.println (adaptador + "IPv4: " + hasIp);
								hasIpv4 = true;
							}
						}
					}
				}
			}
			if (!hasIpv4) {
				System.out.println("Nenhum adaptador com Ipv4");
			}
			scanner.close();
			process.waitFor();
		}
		catch(Exception e){
			System.err.print(e.getMessage());		
		}
	}
	
		
	public void ping () {
		String os= os();
		String cmd ="";
		String linha;
		boolean win= false;
		
	 if (os.contains("dows")) {
            cmd = "ping -4 -n 10 www.google.com.br";
            win= true;
        } else {
            cmd = "ping -4 -c 10 www.google.com.br";
		}
	
	try
	{
		Process process = Runtime.getRuntime().exec(cmd);
		Scanner scanner = new Scanner (process.getInputStream());
		String out;
		String avgTime ="";
		while (scanner.hasNextLine()) {
			if (win) 
			{
				linha = scanner.nextLine();
				System.out.print(".");
				if (linha.contains("dia")) {
					String[] mmm = linha.split(","); 
					System.out.println(mmm[2]);
				}
			}
			else 
			{
				linha = scanner.nextLine();
				System.out.print(".");
				if (linha.contains("avg")|| linha.contains("average"))
				{
					String[] mmm = linha.split(" "); 
					System.out.println(mmm[2]);
				}
			}
		
		}
		scanner.close();
		process.waitFor();
	}catch(Exception e) {
		System.err.print(e.getMessage());
		}
	}
	
}

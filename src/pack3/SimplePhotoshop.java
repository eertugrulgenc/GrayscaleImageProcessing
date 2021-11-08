package pack3;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

//please start looking at Git(version control system)
//I will post a very good Git book (open source book)
//in UBIS so please follow my instructions in UBIS on what
//to do for Git

class SimplePhotoshop extends JFrame{
	private DrawingPanel dp;
	private short [][] pixels;
	private short [][][] pixels3;

	private int width, height, fileType;
	//constructor for SimplePhotoshop
	SimplePhotoshop(int select){
		readFile(select);
		dp = new DrawingPanel();
		this.setContentPane(dp);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,600);
		this.setVisible(true);
	}
	void readFile(int select) {
		String txt = "";
		if(select==1) {
			txt += "bitText.txt";
		}
		else if(select==2) {
			txt += "baboonascii.txt";
		}
		else if(select==3) {
			txt += "colorText.txt";
		}
		else if(select==4) {
			txt += "typee5.txt";
		}
		else if(select==5) {
			txt += "type6.advprog";
		}
		if(select==1 || select==2 || select==3) {
		try {
			Scanner inFile = new Scanner(new File(txt));
			fileType = inFile.nextInt();
			width = inFile.nextInt();
			height = inFile.nextInt();
		
			System.out.printf("type: %d, width: %d, height: %d\n",
					fileType,width,height);
		
			if(fileType==1) {
				pixels = new short[height][width];
				for(int row = 0; row<height; row++) {
					for(int col = 0; col<width; col++) {
						pixels [row][col] = inFile.nextShort();
					}
				}
			}
			else if(fileType==2) {
				pixels = new short[height][width];
				for(int row = 0; row<height; row++) {
					for(int col = 0; col<width; col++) {
						pixels [row][col] = inFile.nextShort();
					}
				}
			}
			else if(fileType==3) {
				pixels3 = new short[height][width][3];
				for(int r = 0; r<height; r++) {
					for(int g = 0; g<width; g++) {
							pixels3 [r][g][0] = inFile.nextShort();
							pixels3 [r][g][1] = inFile.nextShort();
							pixels3 [r][g][2] = inFile.nextShort();
					}
				}
			}
			
			} catch (FileNotFoundException e) {
		
				e.printStackTrace();
			}
		}
		if(select==4 || select==5) {
			if(select==4) {
			FileInputStream file = null;
			try {
				file = new FileInputStream(txt);
			fileType = Character.getNumericValue((char)file.read());
		
			file.skip(1);
			String width4= "";
			for(int i=0; i<3; i++) {
				width4+= (char)file.read();
			}
			width = Integer.parseInt(width4);
			file.skip(1);
			String height4= "";
			for(int i=0; i<3; i++) {
				height4+= (char)file.read();
			}
			height = Integer.parseInt(height4);
			file.skip(5);
			
			System.out.printf("type: %d, width: %d, height: %d\n",
					fileType,width,height);
			
			
			pixels = new short[height][width];
			for(int row = 0; row<height; row++) {
				for(int col = 0; col<width; col++) {
					pixels [row][col] =  (short) file.read();
				
				}
			}
		
			} catch (FileNotFoundException e) {
				System.out.println("File bulunamadý");
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(file != null) {
					file.close();
					}
				} catch (IOException e) {
					System.out.println("Dosya Kapatlamadý...");
				}
			}
			}
			if(select==5) {
				FileInputStream file = null;
				try {
					file = new FileInputStream(txt);
				fileType = Character.getNumericValue((char)file.read());
				System.out.println(fileType);
			
				file.skip(3);
			/*
				width = Character.getNumericValue((char)file.read());
				System.out.println(width);
				width = Character.getNumericValue((char)file.read());
				System.out.println(width);
				width = Character.getNumericValue((char)file.read());
			*/	
				String width5= "";
				for(int i=0; i<4; i++) {
					width5+= (char)file.read();
				}
				width = Integer.parseInt(width5);
				System.out.println(width);
				file.skip(4);
				String height5= "";
				for(int i=0; i<3; i++) {
					height5+= (char)file.read();
				}
				height = Integer.parseInt(height5);
				
				System.out.printf("type: %d, width: %d, height: %d\n",
						fileType,width,height);
				
				file.skip(8);
			
				pixels3 = new short[height][width][3];
				for(int r = 0; r<height; r++) {
					for(int g = 0; g<width; g++) {
					
							pixels3 [r][g][0] = (short) file.read();
							pixels3 [r][g][1] = (short) file.read();
							pixels3 [r][g][2] = (short) file.read();
		
					}
				}
			
				} catch (FileNotFoundException e) {
					System.out.println("File bulunamadý");
				} catch (IOException e) {
					e.printStackTrace();
				}
				finally {
					try {
						if(file != null) {
						file.close();
						}
					} catch (IOException e) {
						System.out.println("Dosya Kapatlamadý...");
					}
				}
			}
		}
	}
	//inner class
	//can access any member of the containing class,that is
	//DrawingPanel class can access the members (data fields,
	//methods) of SimplePhotoshop
	//DrawingPanel is not directly accessible (visible) from outside
	//of the SimplePhotoshop
	class DrawingPanel extends JPanel{
		@Override
		
		//55
		public void paintComponent(Graphics g) {
		//	g.drawString("Advanced Programming", 250, 300);
				if(fileType==1) {
					for(int row = 0; row<height; row++) 
						for(int col = 0; col<width; col++) {
			//Color class' constructor takes the three main
				//colors RGB (Red, Green, Blue).
					if(pixels[row][col]==1) {
						g.setColor(new Color(255,
								255,
								255));
						g.fillRect(col, row, 1, 1);
					}
					else {
						g.setColor(new Color(pixels[row][col],
											pixels[row][col],
											pixels[row][col]));
						g.fillRect(col, row, 1, 1);
					}
						}
			}
				else if (fileType==2 || fileType==5) {
					for(int row = 0; row<height; row++) 
						for(int col = 0; col<width; col++) {
							g.setColor(new Color(pixels[row][col],
									pixels[row][col],
									pixels[row][col]));
				g.fillRect(col, row, 1, 1);
						}
				}
				else if (fileType==3 || fileType==6) {
					for(int row = 0; row<height; row++) 
						for(int col = 0; col<width; col++) {
							
								g.setColor(new Color(pixels3[row][col][0],
										pixels3[row][col][1],
										pixels3[row][col][2]));
					g.fillRect(col, row, 1, 1);
							
						}
				}
			
			
		}
	}
	public static void main(String  []args){
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome Photoshop");
		System.out.println("Which do you want to open text based image?");
		System.out.println("1-)Black and White text");
		System.out.println("2-)Baboonascii text");
		System.out.println("3-)Color text");
		System.out.println("4-)Grayscale bird text");
		System.out.println("5-)Colorful text");
		int select = scan.nextInt();
		if(select==1) {
			System.out.println("Type 1 is opening...");
			new SimplePhotoshop(select);
		}
		else if(select==2) {
			System.out.println("Type 2 is opening...");
			new SimplePhotoshop(select);
		}
		else if(select==3) {
			System.out.println("Type 3 is opening...");
			new SimplePhotoshop(select);
		}
		else if(select==4) {
			System.out.println("Type 5 is opening");
			new SimplePhotoshop(select);
		}
		else if(select==5) {
			System.out.println("Type 6 is opening");
			new SimplePhotoshop(select);
		}
		else {
			System.out.println("Wrong!!!");
		}

	}
}

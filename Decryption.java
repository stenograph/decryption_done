package stenography;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

class work{
	BufferedImage image,afterEncryptionImage;
	int height,width,red,green,blue,size;
	short encryptedStringLength;
	File input;
	int row=0,column=0;
	int count=0;
	int[][] pixeldata;
	int[] pixels;
	Color c;
	String inputString,encryptedString;
	byte[] stringBytes;
	int strlen=0,number=0;
	WritableRaster raster;
	StringBuffer str;
	String DecryptedString;
	byte []b; 
	String str1;
	void getImageAndDimension() throws IOException
	{
		input=new File("2.png");
		image = ImageIO.read(input);
		width= image.getWidth();
		height = image.getHeight();
		size = height * width;
	}
	void createPixelData()
	{
		pixeldata = new int[size][3];
		for(int i = 0; i < height;i++)
		{
			for(int j = 0;j < width;j++)
			{
				c = new Color(image.getRGB(j, i));
				red = c.getRed();
				green = c.getGreen();
				blue = c.getBlue();
				pixeldata[count][0]=red;
				pixeldata[count][1]=green;
				pixeldata[count][2]=blue;
				count++;
			}
		}
	}
	void printPixelData()
	{
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<3;j++)
			{
				System.out.print(pixeldata[i][j] + "  ");
			}
			System.out.println("");
			
		}
	}
	
	void getlength()
	{   
	    
		for(int i=0;i<16;i++){
			strlen=strlen<<1;
			strlen+=(pixeldata[row][column]&1);
			
			if( column==2){
				row++;
				column=0;
			}
			else{
				column++;
			}
		
		}
//		strlen=strlen>>1;
		System.out.println(strlen);	
	}
	
	void getEncryptedText(){
		int j=strlen;
		b=new byte[j];
		//System.out.println(row + " " + column);
		for(int i=0;i<j;i++){
			number=0;
			for(int k=0;k<8;k++){
				number=number<<1;
				number+=(pixeldata[row][column]&1);
				if(column==2){
					column=0;
					row++;
				}
				else{
					column++;
					}
				}
			b[i]=(byte) number;
			}
		/*for(int i=0;i<strlen;i++){
			System.out.println(b[i]);
		}*/
		}
	
	void getEncryptedstring(){
		str1=new String(b);
		System.out.println(str1);
		}
	
	void getDecryptedString() throws Exception{
		TripleDESTest DESobj1 = new TripleDESTest();
		DecryptedString=DESobj1._decrypt(str1, "SecretKey");
		System.out.println(DecryptedString);
	}
	}


public class Decryption{
	
	public static void main(String[] args) throws Exception{
		
		work obj=new work();
		obj.getImageAndDimension();
		obj.createPixelData();
		//obj.printPixelData();
		obj.getlength();
		obj.getEncryptedText();
		obj.getEncryptedstring();
		obj.getDecryptedString();
		
	}
}


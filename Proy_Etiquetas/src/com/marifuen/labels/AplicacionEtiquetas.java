package com.marifuen.labels;

import java.io.File;
import java.io.FileOutputStream;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

public class AplicacionEtiquetas
{
	public static void main(String[] args)
	{
		System.out.println("Hello Marifuen!");
		System.out.println("Hello Manueeeee");

		String text = "123456789101";

		int width = 300;
		int height = 100;
		String imgFormat = "png";

		BitMatrix bitMatrix = null;
		try
		{
			Code128Writer writer=new Code128Writer();  
			bitMatrix = writer.encode(text, BarcodeFormat.CODE_128, width, height);
		}
		catch (WriterException e)
		{
			e.printStackTrace();
		}
		try
		{
			MatrixToImageWriter.writeToStream(bitMatrix, imgFormat, new FileOutputStream(new File("C:\\Manuel\\code_.png")));
			System.out.println("Success!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}

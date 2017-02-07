package com.marifuen.labels;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.apache.fop.apps.FOPException;

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

		// String text = "123456789101";
		String text = "88423221080026372617400000010000";

		int width = 300;
		int height = 100;
		String imgFormat = "png";

		BitMatrix bitMatrix = null;
		try
		{
			Code128Writer writer = new Code128Writer();
			bitMatrix = writer.encode(text, BarcodeFormat.CODE_128, width, height);
		}
		catch (WriterException e)
		{
			e.printStackTrace();
		}
		try
		{
			MatrixToImageWriter.writeToStream(bitMatrix, imgFormat, new FileOutputStream(new File("C:\\Manuel\\code_.png")));
			System.out.println("Éxito generando código de barras!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			FopUtils.convertToPDF("C:\\Manuel\\listado_utrera.xsl","C:\\Manuel\\listado_utrera.pdf");
		}
		catch (FOPException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (TransformerException e)
		{
			e.printStackTrace();
		}
		
	}
}

package com.marifuen.labels;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class FopUtils
{
	public static void convertToPDF(String xsl, String pOut) throws IOException, FOPException, TransformerException
	{
		// the XSL FO file
		File xsltFile = new File(xsl);
		// the XML file which provides the input
		byte[] listadoXml = creaXML();
		InputStream xml = new ByteArrayInputStream(listadoXml);
		StreamSource xmlSource = new StreamSource(xml);
		// create an instance of fop factory
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		// a user agent is needed for transformation
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		// Setup output
		OutputStream out;
		out = new java.io.FileOutputStream(pOut);

		try
		{
			// Construct fop with desired output format
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

			// Setup XSLT
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));

			// Resulting SAX events (the generated FO) must be piped through to FOP
			Result res = new SAXResult(fop.getDefaultHandler());

			// Start XSLT transformation and FOP processing
			// That's where the XML is first transformed to XSL-FO and then
			// PDF is created
			transformer.transform(xmlSource, res);
		}
		finally
		{
			out.close();
		}
	}
	
	public static byte[] creaXML() 
	{
		// Se crea el XML
		Element root = new Element("doc");
		Document document = new Document(root);

		// Se añade la cabecera
		// Element cabecera = ElementosXML.componerElementoCabecera(this);
		// root.addContent(cabecera);

		// Se añade el pie
		// Element pie = ElementosXML.componerElementoPie();
		// root.addContent(pie);

		// Se añaden el resto de elementos
		// Element elementoDatosListado = ElementosXML.componerElementoDatosListado(this);
		// root.addContent(elementoDatosListado);

		// Se devuelve el XML
		XMLOutputter serializer = new XMLOutputter();
		Format formatoEncoding = Format.getCompactFormat();
		formatoEncoding.setEncoding("UTF-8");
		serializer.setFormat(formatoEncoding);
		return serializer.outputString(document).getBytes();
	}
}

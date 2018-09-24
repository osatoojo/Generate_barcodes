package barcode;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.lang.RandomStringUtils;

import java.io.FileOutputStream;

public class Barcode_PDF {

	public static String generatingRandomAlphabeticString() {

		String generatedString = RandomStringUtils.randomAlphabetic(10);

		return generatedString;

	}

	public static void createPDF(String pdfFilename,String myString) {
		Document doc = new Document();
		PdfWriter docWriter = null;
		try {

			docWriter = PdfWriter.getInstance(doc,
					new FileOutputStream("/Users/osato/Downloads/Generate_barcodes/src/main/resources/file/document/" + pdfFilename));
			doc.addAuthor("osato");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator("osatoDev");
			doc.addTitle("Barcode test");
			doc.setPageSize(PageSize.LETTER);
			doc.open();

			PdfContentByte cb = docWriter.getDirectContent();


			Barcode128 code128 = new Barcode128();
			code128.setCode(myString.trim());
			code128.setCodeType(Barcode128.CODE128);
			Image code128Image = code128.createImageWithBarcode(cb, null, null);
			code128Image.setAbsolutePosition(10, 700);
			code128Image.scalePercent(125);
			doc.add(code128Image);


			BarcodeEAN codeEAN = new BarcodeEAN();
			codeEAN.setCode(myString.trim());
			codeEAN.setCodeType(BarcodeEAN.EAN13);
			Image codeEANImage = code128.createImageWithBarcode(cb, null, null);
			codeEANImage.setAbsolutePosition(10, 600);
			codeEANImage.scalePercent(125);
			doc.add(codeEANImage);



		} catch (DocumentException dex) {
			dex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (doc != null) {
				doc.close();
			}
			if (docWriter != null) {
				docWriter.close();
			}
		}

	}
	
	public static void main(String[] args) {

		Barcode_PDF.createPDF("barcode.pdf",generatingRandomAlphabeticString());

	}
	


}

package barcode;

import org.apache.commons.lang.RandomStringUtils;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class Barcode_Image {

//	private String image_name;
//
//	private String myString;

	public static String generatingRandomAlphabeticString() {

		String generatedString = RandomStringUtils.randomAlphabetic(10);

		return generatedString;

	}
	
	
	public static void main(String[] args) {
		Barcode_Image.createImage("barcode.png", generatingRandomAlphabeticString());
		System.out.println("finished");
	}

	public static void createImage(String image_name,String myString)  {
		try {


		Code128Bean code128 = new Code128Bean();
		code128.setHeight(15f);
		code128.setModuleWidth(0.3);
		code128.setQuietZone(10);
		code128.doQuietZone(true);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
		code128.generateBarcode(canvas, myString);
		canvas.finish();

		//write to png file
		FileOutputStream fos = new FileOutputStream("/Users/osato/Downloads/Generate_barcodes/src/main/resources/file/image/"+image_name);
		fos.write(baos.toByteArray());
		fos.flush();
		fos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}

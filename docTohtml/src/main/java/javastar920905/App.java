package javastar920905;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "word 转html(默认将d盘下resume.doc 转换为htm文件)" );



        try {
            final String path = "d://";
            final String file = "resume.doc";
            InputStream input = new FileInputStream(path + file);
            HWPFDocument wordDocument = new HWPFDocument(input);
            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                    DocumentBuilderFactory.newInstance().newDocumentBuilder()
                            .newDocument());
            wordToHtmlConverter.setPicturesManager(new PicturesManager() {
                public String savePicture(byte[] content, PictureType pictureType,
                                          String suggestedName, float widthInches, float heightInches) {
                    return suggestedName;
                }
            });
            wordToHtmlConverter.processDocument(wordDocument);
            List pics = wordDocument.getPicturesTable().getAllPictures();
            if (pics != null) {
                for (int i = 0; i < pics.size(); i++) {
                    Picture pic = (Picture) pics.get(i);
                    try {
                        pic.writeImageContent(new FileOutputStream(path
                                + pic.suggestFullFileName()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            Document htmlDocument = wordToHtmlConverter.getDocument();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(outStream);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
            outStream.close();
            String content = new String(outStream.toByteArray());
            FileUtils.writeStringToFile(new File(path, "1.html"), content, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        System.out.println( "转换完成 查看d盘下 1.html" );

    }

        /*@Test
    public void word2007ToHtml() throws Exception {
        String filepath = "D:\\Tomcat8.0\\File\\";
        String sourceFileName =filepath+"1.docx";
        String targetFileName = filepath+"1496717486420.html";
        String imagePathStr = filepath+"/image/";
        OutputStreamWriter outputStreamWriter = null;
        try {
          XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
          XHTMLOptions options = XHTMLOptions.create();
          // 存放图片的文件夹
          options.setExtractor(new FileImageExtractor(new File(imagePathStr)));
          // html中图片的路径
          options.URIResolver(new BasicURIResolver("image"));
          outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
          XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
          xhtmlConverter.convert(document, outputStreamWriter, options);
        } finally {
          if (outputStreamWriter != null) {
            outputStreamWriter.close();
          }
        }
      } */
}

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlParser {

    static class Record {

        String ver_major,ver_minor,cbeffVer_major,cbeffVer_minor,quality_score;
        String integrity;
        String organization,type,biometric_type,getBiometric_subType,purpose,algo_organization,algo_type,biometric_record;

    }
    public Record Parser() {

        Record record=new Record();

        try {

            File inputFile = new File("input.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();



         //   System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Version");
            record.ver_major=nList.item(0).getChildNodes().item(0).getTextContent();
            record.ver_minor=nList.item(0).getChildNodes().item(1).getTextContent();

            nList = doc.getElementsByTagName("CBEFFVersion");
            record.cbeffVer_major=nList.item(0).getChildNodes().item(0).getTextContent();
            record.cbeffVer_minor=nList.item(0).getChildNodes().item(1).getTextContent();

            record.integrity=doc.getElementsByTagName("BIRInfo").item(0).getChildNodes().item(0).getTextContent();

            nList = doc.getElementsByTagName("BDBInfo");

            record.biometric_type=nList.item(0).getChildNodes().item(2).getTextContent();
            record.getBiometric_subType=nList.item(0).getChildNodes().item(3).getTextContent();

            record.algo_organization=nList.item(0).getChildNodes().item(5).getChildNodes().item(0).getChildNodes().item(0).getTextContent();
            record.algo_type=nList.item(0).getChildNodes().item(5).getChildNodes().item(0).getChildNodes().item(1).getTextContent();
            record.quality_score=nList.item(0).getChildNodes().item(5).getChildNodes().item(1).getTextContent();

            record.biometric_record=doc.getElementsByTagName("BIR").item(0).getChildNodes().item(3).getChildNodes().item(2).getTextContent();

         //   System.out.println(record.biometric_record);

                } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

         catch (Exception e) {
             e.printStackTrace();
         }
        return record;
    }
}
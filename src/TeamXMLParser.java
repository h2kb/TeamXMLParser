import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class TeamXMLParser {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        File fileTeams = new File("./xml/teams.xml");
        File fileResults = new File("./xml/results.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document documentResults = documentBuilder.parse(fileResults);

        NodeList resultsNodeList = documentResults.getElementsByTagName("game");

    }
}

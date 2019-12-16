import org.w3c.dom.Document;
import org.w3c.dom.Node;
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

        // Create document builder
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // Create DOM tree from file
        Document documentOfTeams = documentBuilder.parse(fileTeams);
        // Get root item
        Node rootOfTeams = documentOfTeams.getDocumentElement();

        System.out.println("List of teams");
        // Get root child items
        NodeList teams = rootOfTeams.getChildNodes();

        for (int i = 0; i < teams.getLength(); i++) {
            Node team = teams.item(i);
            if (team.getNodeType() != Node.TEXT_NODE) {
                NodeList players = team.getChildNodes();
                for (int j = 0; j < players.getLength(); j++) {
                    Node player = players.item(j);
                    if (player.getNodeType() != Node.TEXT_NODE) {
                        System.out.println(player.getNodeName() + ":" + player.getTextContent());
                    }
                }

                System.out.println(">>>>>>>>>>");
            }
        }

    }
}

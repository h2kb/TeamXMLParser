import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TeamXMLParser {

    private static ArrayList<Player> players = new ArrayList<>();
    private static HashMap<String, Integer> gameResult = new HashMap<>();

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        File fileTeams = new File("./xml/teams.xml");
        File fileResults = new File("./xml/results.xml");

        // Create document builder
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        // Create DOM trees from files
        Document docOfResult = documentBuilder.parse(fileResults);
        Document docOfTeam = documentBuilder.parse(fileTeams);

        NodeList results = docOfResult.getElementsByTagName("result");
        NodeList teams = docOfTeam.getElementsByTagName("team");

        for (int i = 0; i < results.getLength(); i++) {
            Node result = results.item(i);

            if (result.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) result;
                String team = element.getElementsByTagName("team").item(0).getTextContent();
                int place = Integer.parseInt(element.getElementsByTagName("place").item(0).getTextContent());
                gameResult.put(team, place);
            }
        }

        for (int i = 0; i < teams.getLength(); i++) {
            Node teamNode = teams.item(i);

            if (teamNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) teamNode;

                String teamName = element.getElementsByTagName("name").item(0).getTextContent();
                NodeList playersList = element.getElementsByTagName("player");

                for (int j = 0; j < playersList.getLength(); j++) {
                    Node playerNode = playersList.item(j);

                    if (playerNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element playerDetail = (Element) playerNode;

                        String name = playerDetail.getElementsByTagName("name").item(0).getTextContent();
                        String surname = playerDetail.getElementsByTagName("surname").item(0).getTextContent();
                        int age = Integer.parseInt(playerDetail.getElementsByTagName("age").item(0).getTextContent());
                        String role = playerDetail.getElementsByTagName("role").item(0).getTextContent();

                        Player player = new Player(name, surname, age, teamName, role);
                        players.add(player);
                    }
                }
            }
        }

        for (Player player : players) {
            System.out.println(player);
        }
    }
}

class Player {
    private String name;
    private String surname;
    private int age;
    private String team;
    private String role;

    public Player(String name, String surname, int age, String team, String role) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.team = team;
        this.role = role;
    }

    public String toString() {
        return name + " " + surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getTeam() {
        return team;
    }

    public String getRole() {
        return role;
    }
}

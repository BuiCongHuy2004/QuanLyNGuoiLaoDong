import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserXMLUtil {

    private static final String XML_FILE_PATH = "users.xml";

    public static List<User> readUsersFromXML() {
        List<User> users = new ArrayList<>();
        try {
            File xmlFile = new File(XML_FILE_PATH);
            if (!xmlFile.exists()) {
                return users;  // Return empty list if file doesn't exist
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("user");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String gender = element.getElementsByTagName("gender").item(0).getTextContent();
                    int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                    Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(element.getElementsByTagName("birthDate").item(0).getTextContent());
                    String address = element.getElementsByTagName("address").item(0).getTextContent();
                    String permanentAddress = element.getElementsByTagName("permanentAddress").item(0).getTextContent();
                    String occupation = element.getElementsByTagName("occupation").item(0).getTextContent();
                    String maritalStatus = element.getElementsByTagName("maritalStatus").item(0).getTextContent();
                    double income = Double.parseDouble(element.getElementsByTagName("income").item(0).getTextContent());
                    String idNumber = element.getElementsByTagName("idNumber").item(0).getTextContent();
                    String imagePath = element.getElementsByTagName("imagePath").item(0).getTextContent();

                    users.add(new User(id, firstName, lastName, gender, age, birthDate, address, permanentAddress, occupation, maritalStatus, income, idNumber, imagePath));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void writeUsersToXML(List<User> users) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("users");
            doc.appendChild(rootElement);

            for (User user : users) {
                Element userElement = doc.createElement("user");

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(String.valueOf(user.getId())));
                userElement.appendChild(id);

                Element firstName = doc.createElement("firstName");
                firstName.appendChild(doc.createTextNode(user.getFirstName()));
                userElement.appendChild(firstName);

                Element lastName = doc.createElement("lastName");
                lastName.appendChild(doc.createTextNode(user.getLastName()));
                userElement.appendChild(lastName);

                Element gender = doc.createElement("gender");
                gender.appendChild(doc.createTextNode(user.getGender()));
                userElement.appendChild(gender);

                Element age = doc.createElement("age");
                age.appendChild(doc.createTextNode(String.valueOf(user.getAge())));
                userElement.appendChild(age);

                Element birthDate = doc.createElement("birthDate");
                birthDate.appendChild(doc.createTextNode(new SimpleDateFormat("dd/MM/yyyy").format(user.getBirthDate())));
                userElement.appendChild(birthDate);

                Element address = doc.createElement("address");
                address.appendChild(doc.createTextNode(user.getAddress()));
                userElement.appendChild(address);

                Element permanentAddress = doc.createElement("permanentAddress");
                permanentAddress.appendChild(doc.createTextNode(user.getPermanentAddress()));
                userElement.appendChild(permanentAddress);

                Element occupation = doc.createElement("occupation");
                occupation.appendChild(doc.createTextNode(user.getOccupation()));
                userElement.appendChild(occupation);

                Element maritalStatus = doc.createElement("maritalStatus");
                maritalStatus.appendChild(doc.createTextNode(user.getMaritalStatus()));
                userElement.appendChild(maritalStatus);

                Element income = doc.createElement("income");
                income.appendChild(doc.createTextNode(String.valueOf(user.getIncome())));
                userElement.appendChild(income);

                Element idNumber = doc.createElement("idNumber");
                idNumber.appendChild(doc.createTextNode(user.getIdNumber()));
                userElement.appendChild(idNumber);

                Element imagePath = doc.createElement("imagePath");
                imagePath.appendChild(doc.createTextNode(user.getImagePath()));
                userElement.appendChild(imagePath);

                rootElement.appendChild(userElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(XML_FILE_PATH));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}

package it.fmach.femmetastore.xml;


import it.fmach.femmetastore.MetastoreClient;
import it.fmach.femmetastore.resource.MetastoreResult;
import it.fmach.femmetastore.resource.ResourceDescription;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import uk.ac.ebi.utils.xml.XPathReader;

import javax.xml.xpath.XPathConstants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com), Roman Mylonas
 *         <p/>
 *         Date: 12/09/2011
 *         Time: 16:44
 */
public class MetastoreXMLHandler {

    public List<MetastoreResult> parseXML(String xml, ResourceDescription resourceDescription) throws FileNotFoundException {
        XPathReader reader = new XPathReader(new FileInputStream(xml));

        List<MetastoreResult> terms = new ArrayList<MetastoreResult>();

        System.out.println("MetastoreXMLHandler xml: " + xml);

        NodeList preferredTerms = (NodeList) reader.read("/preferredTerms/preferredTerm", XPathConstants.NODESET);

        if (preferredTerms.getLength() > 0) {

            for (int sectionIndex = 0; sectionIndex <= preferredTerms.getLength(); sectionIndex++) {
                String id = (String) reader.read("/preferredTerms/preferredTerm[" + sectionIndex + "]/@id", XPathConstants.STRING);
                String token = (String) reader.read("/preferredTerms/preferredTerm[" + sectionIndex + "]/token", XPathConstants.STRING);

                // construct comment map
                NodeList comments = (NodeList) reader.read("/preferredTerms/preferredTerm[" + sectionIndex + "]/comments/*", XPathConstants.NODESET);
                Map<String, String> commentMap = new HashMap<String, String>();

                System.out.println("NodeList: " + comments + " : " + comments.getLength());

                for (int commentIndex = 0; commentIndex < comments.getLength(); commentIndex++) {
                //for (Node comment : comments) {
                    Node comment = comments.item(commentIndex);
                    System.out.println("comment node: " + comment);
                    String text = comment.getTextContent().trim();
                    System.out.println("comment: " + comment.getNodeName() + " : [" + text + "]");
                    
                    if(text.length() > 0){
                        commentMap.put(comment.getNodeName(), text);
                    }
                }

                System.out.println("Comment map: " + commentMap);

                if (!id.equalsIgnoreCase("")) {
                    terms.add(new MetastoreResult(id, token, commentMap, resourceDescription));
                }
            }

            return terms;
        }
        return new ArrayList<MetastoreResult>();
    }

}

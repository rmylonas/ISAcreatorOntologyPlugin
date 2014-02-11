package it.fmach.femmetastore.utils;

import org.isatools.isacreator.ontologymanager.OntologySourceRefObject;
import org.isatools.isacreator.ontologymanager.common.OntologyTerm;
import it.fmach.femmetastore.MetastoreClient;
import it.fmach.femmetastore.resource.MetastoreResult;
import it.fmach.femmetastore.resource.ResourceDescription;

import java.util.*;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com), Roman Mylonas
 *         <p/>
 *         Date: 03/10/2011
 *         Time: 17:04
 */
public class Convert {

    public static Map<OntologySourceRefObject, List<OntologyTerm>> convertMetastoreResult(List<MetastoreResult> metastoreResults, ResourceDescription resourceDescription) {
        Map<OntologySourceRefObject, List<OntologyTerm>> convertedResult = new HashMap<OntologySourceRefObject, List<OntologyTerm>>();

        OntologySourceRefObject source = new OntologySourceRefObject(
                resourceDescription.getResourceAbbreviation(), "", resourceDescription.getResourceVersion(), resourceDescription.getResourceName());

        convertedResult.put(source, new ArrayList<OntologyTerm>());

        System.out.println("Converting..");

        for(MetastoreResult result : metastoreResults) {
            System.out.println("result: " + result.getToken() + " ,source: " + source);
            OntologyTerm ontologyTerm = new OntologyTerm(result.getToken(), result.getId(), "", source);

            //add all comments
            for (Map.Entry<String, String> comment : result.getCommentMap().entrySet()) {
                ontologyTerm.addToComments(comment.getKey(), comment.getValue());
            }

            convertedResult.get(source).add(ontologyTerm);
        }

        return convertedResult;
    }
}

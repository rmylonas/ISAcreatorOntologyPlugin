package it.fmach.femmetastore.resource;

import java.util.*;

/**
 * Created by the ISA team
 *
 * @author Eamonn Maguire (eamonnmag@gmail.com), Roman Mylonas
 *         <p/>
 *         Date: 14/09/2011
 *         Time: 21:01
 */
public class MetastoreResult {

    private String id;
    private String token;
    private Map<String, String> commentMap;
    private ResourceDescription parentResource;

    public MetastoreResult(String id, String token, Map<String, String> commentMap, ResourceDescription parentResource) {
        this.id = id;
        this.token = token;
        this.commentMap = commentMap;
        this.parentResource = parentResource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, String> getCommentMap() {
        return commentMap;
    }

    public void setCommentMap(Map<String, String> commentMap) {
        this.commentMap = commentMap;
    }

    public ResourceDescription getParentResource() {
        return parentResource;
    }

    public void setParentResource(ResourceDescription parentResource) {
        this.parentResource = parentResource;
    }
}

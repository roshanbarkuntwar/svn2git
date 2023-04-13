/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.branchHierarchy;

/**
 *
 * @author kapil.gupta
 */
public class TreeMenuBean {

    private String id;  // client_code
    private String parent;  // parent_code
    private String text;   // client_name

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "id:" + id + ", parent:" + parent + ", text:" + text;
    }

}

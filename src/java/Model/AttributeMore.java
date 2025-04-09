/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Anh Tuan
 */
public class AttributeMore extends Attribute {

    private String attributeDescription;

    public AttributeMore() {
    }

    public AttributeMore(int attributeID, String attributeName, String attributeDescription) {
        super(attributeID, attributeName);
        this.attributeDescription = attributeDescription;
    }

    public String getAttributeDescription() {
        return attributeDescription;
    }

    public void setAttributeDescription(String attributeDescription) {
        this.attributeDescription = attributeDescription;
    }

}

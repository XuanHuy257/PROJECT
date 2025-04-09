/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author
 */
public class PostCategory {
    private int postCategoryID;
    private String postCategoryName;

    public PostCategory() {
    }

    public PostCategory(int postCategoryID, String postCategoryName) {
        this.postCategoryID = postCategoryID;
        this.postCategoryName = postCategoryName;
    }

    public int getPostCategoryID() {
        return postCategoryID;
    }

    public void setPostCategoryID(int postCategoryID) {
        this.postCategoryID = postCategoryID;
    }

    public String getPostCategoryName() {
        return postCategoryName;
    }

    public void setPostCategoryName(String postCategoryName) {
        this.postCategoryName = postCategoryName;
    }

   
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author 
 */
public class Post {

    private int postID;
    private int userID;
    private String title;
    private String postImg;
    private String postContent;
    private Date postDate;
    private String postBrief;
    private boolean postFlag;
    private int postCategoryID;
    private String status;

    private String userName;
    private String postCategoryName;

    public Post() {
    }

    public Post(int postID, int userID, String title, String postImg, String postContent, Date postDate, String postBrief, boolean postFlag, int postCategoryID) {
        this.postID = postID;
        this.userID = userID;
        this.title = title;
        this.postImg = postImg;
        this.postContent = postContent;
        this.postDate = postDate;
        this.postBrief = postBrief;
        this.postFlag = postFlag;
        this.postCategoryID = postCategoryID;
    }

    public Post(int postID, int userID, String title, String postImg, String postContent, Date postDate, String postBrief, boolean postFlag, int postCategoryID, String userName, String postCategoryName) {
        this.postID = postID;
        this.userID = userID;
        this.title = title;
        this.postImg = postImg;
        this.postContent = postContent;
        this.postDate = postDate;
        this.postBrief = postBrief;
        this.postFlag = postFlag;
        this.postCategoryID = postCategoryID;
        this.userName = userName;
        this.postCategoryName = postCategoryName;
    }

    public Post(int postID, int userID, String title, String postImg, String postContent, Date postDate, String postBrief, boolean postFlag, int postCategoryID, String userName, String postCategoryName, String status) {
        this.postID = postID;
        this.userID = userID;
        this.title = title;
        this.postImg = postImg;
        this.postContent = postContent;
        this.postDate = postDate;
        this.postBrief = postBrief;
        this.postFlag = postFlag;
        this.postCategoryID = postCategoryID;
        this.userName = userName;
        this.postCategoryName = postCategoryName;
        this.status = status;
    }

    public Post(int postID, String title, String postImg, String postContent, String postBrief, boolean postFlag, int postCategoryID, String status) {
        this.postID = postID;
        this.title = title;
        this.postImg = postImg;
        this.postContent = postContent;
        this.postBrief = postBrief;
        this.postFlag = postFlag;
        this.postCategoryID = postCategoryID;
        this.status = status;
    }

    public Post(int userID, String title, String postImg, String postContent, String postBrief, int postCategoryID) {
        this.userID = userID;
        this.title = title;
        this.postImg = postImg;
        this.postContent = postContent;
        this.postBrief = postBrief;
        this.postCategoryID = postCategoryID;
    }
    
    

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostImg() {
        return postImg;
    }

    public void setPostImg(String postImg) {
        this.postImg = postImg;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getPostBrief() {
        return postBrief;
    }

    public void setPostBrief(String postBrief) {
        this.postBrief = postBrief;
    }

    public boolean isPostFlag() {
        return postFlag;
    }

    public void setPostFlag(boolean postFlag) {
        this.postFlag = postFlag;
    }

    public int getPostCategoryID() {
        return postCategoryID;
    }

    public void setPostCategoryID(int postCategoryID) {
        this.postCategoryID = postCategoryID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostCategoryName() {
        return postCategoryName;
    }

    public void setPostCategoryName(String postCategoryName) {
        this.postCategoryName = postCategoryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

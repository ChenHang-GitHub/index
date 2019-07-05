/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author chenshihang
 */
public class Post {

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPost_depart() {
        return post_depart;
    }

    public void setPost_depart(String post_depart) {
        this.post_depart = post_depart;
    }

    public String getPost_superior() {
        return post_superior;
    }

    public void setPost_superior(String post_superior) {
        this.post_superior = post_superior;
    }

    public String getPost_cate() {
        return post_cate;
    }

    public void setPost_cate(String post_cate) {
        this.post_cate = post_cate;
    }

    public String getPost_desc() {
        return post_desc;
    }

    public void setPost_desc(String post_desc) {
        this.post_desc = post_desc;
    }

    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", postCode=" + postCode + ", postName=" + postName + ", post_depart=" + post_depart + ", post_superior=" + post_superior + ", post_cate=" + post_cate + ", post_desc=" + post_desc + '}';
    }
    private int postId;
    private String postCode;
    private String postName;
    private String post_depart;
    private String post_superior;
    private String post_cate;
    private String post_desc;
}

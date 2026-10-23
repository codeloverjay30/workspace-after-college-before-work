package main.java.bean;


public class Member {
    private String username;
    private String nickname;
    private byte[] img;
    private String imgBase64Str;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getImgBase64Str() {
        return imgBase64Str;
    }

    public void setImgBase64Str(String imgBase64Str) {
        this.imgBase64Str = imgBase64Str;
    }
}
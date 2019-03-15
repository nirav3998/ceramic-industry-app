package com.example.valenzaceramic;

public class Upload {
    private String mName;
    private String mImageUrl;
  //  private static int i=0;
    public Upload()
    {

    }
    public Upload(String name,String imageUrl)
    {
        if(name.trim().equals(""))
        {
           // i = i+1;
            name = "No name 1";

        }

        mName= name;
        mImageUrl = imageUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}

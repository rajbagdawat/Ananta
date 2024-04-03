package com.shiva.ananta.models;

public class EBookcomponent_AdapterGS {
    String EBook_Name ,EBook_minidescription,EBook_Name_Type,EBook_description,
            EBook_Rating;
    String EBook_Images;
    private boolean isChecked;

    public EBookcomponent_AdapterGS(String EBook_Name,String EBook_Name_Type, String EBook_minidescription, String EBook_Images, String EBook_description) {
        this.EBook_Name = EBook_Name;
        this.EBook_Name_Type = EBook_Name_Type;
        this.EBook_minidescription = EBook_minidescription;
        this.EBook_Images = EBook_Images;
        this.EBook_description = EBook_description;
        this.isChecked = isChecked;
    }

    public String getEBook_minidescription() {
        return EBook_minidescription;
    }

    public void setEBook_minidescription(String EBook_minidescription) {
        this.EBook_minidescription = EBook_minidescription;
    }

    public String getEBook_Rating() {
        return EBook_Rating;
    }

    public void setEBook_Rating(String EBook_Rating) {
        this.EBook_Rating = EBook_Rating;
    }

    public String getEBook_Name_Type() {
        return EBook_Name_Type;
    }

    public void setEBook_Name_Type(String EBook_Name_Type) {
        this.EBook_Name_Type = EBook_Name_Type;
    }

    public String getEBook_Name() {
        return EBook_Name;
    }

    public void setEBook_Name(String EBook_Name) {
        this.EBook_Name = EBook_Name;
    }

    public String getEBook_description() {
        return EBook_description;
    }

    public void setEBook_description(String EBook_description) {
        this.EBook_description = EBook_description;
    }

    public String getEBook_Images() {
        return EBook_Images;
    }

    public void setEBook_Images(String EBook_Images) {
        this.EBook_Images = EBook_Images;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


}

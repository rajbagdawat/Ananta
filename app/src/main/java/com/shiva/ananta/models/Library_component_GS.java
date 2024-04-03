package com.shiva.ananta.models;

public class Library_component_GS {
    String LibraryBookImage;
    String LibraryBookName;
    String LibraryAuthorName;
    String Library_BookDis;
    String Library_Bookid;
    String Library_bookPagecount;
    String Library_bookURL;
    String Library_bookVersion;
    String Library_bookkeyword;
    String Library_bookminidescription;
    String Library_bookrating;


    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getLibraryBookImage() {
        return LibraryBookImage;
    }

    public void setLibraryBookImage(String libraryBookImage) {
        LibraryBookImage = libraryBookImage;
    }

    public String getLibraryBookName() {
        return LibraryBookName;
    }

    public void setLibraryBookName(String libraryBookName) {
        LibraryBookName = libraryBookName;
    }

    public String getLibraryAuthorName() {
        return LibraryAuthorName;
    }

    public void setLibraryAuthorName(String libraryAuthorName) {
        LibraryAuthorName = libraryAuthorName;
    }

    public String getLibrary_Bookid() {
        return Library_Bookid;
    }

    public void setLibrary_Bookid(String library_Bookid) {
        Library_Bookid = library_Bookid;
    }

    public String getLibrary_BookDis() {
        return Library_BookDis;
    }

    public void setLibrary_BookDis(String library_BookDis) {
        Library_BookDis = library_BookDis;
    }

    public Library_component_GS(String libraryBookImage, String libraryBookName, String libraryAuthorName, String library_BookDis, String Library_Bookid
     ) {
        LibraryBookImage = libraryBookImage;
        LibraryBookName = libraryBookName;
        LibraryAuthorName = libraryAuthorName;
        Library_BookDis = library_BookDis;
        this.Library_Bookid = Library_Bookid;
        this.isChecked = false;

    }
}

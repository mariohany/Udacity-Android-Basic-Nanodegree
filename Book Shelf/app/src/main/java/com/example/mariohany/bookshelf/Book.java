package com.example.mariohany.bookshelf;

/**
 * Created by Mario Hany on 2017-07-29.
 */

public class Book {

    private String mBookName;
    private String mBookWriter;
    private String mBookDescription;

    public Book(String mBookName, String mBookWriter, String mBookDescription) {
        this.mBookName = mBookName;
        this.mBookWriter = mBookWriter;
        this.mBookDescription = mBookDescription;
    }

    public String getBookName() {
        return mBookName;
    }

    public String getBookWriter() {
        return mBookWriter;
    }

    public String getBookDescription() {
        return mBookDescription;
    }
}

package com.example.vedantmehra.homepage2;
public class Word {

    /** Default translation for the word */
    private String Header;

    /** Miwok translation for the word */
    private String SubHeader;

    /** Audio resource ID for the word */
    private int RequestStatus;

    /** Image resource ID for the word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    private int response;
    public int amount;

    public String date,description;
    //private String name;

    /**
     * Create a new Word object.
     *
     * @param head is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param subhead is the word in the Miwok language
     * @param reqstat is the resource ID for the audio file associated with this word
     */
    public Word(String head, String subhead, int reqstat) {
        Header = head;
        SubHeader = subhead;
        RequestStatus = reqstat;
        response = 0;
    }

    public Word(int amt,String head, String subhead, int imageResourceId,
                  int reqstat) {
        Header = head;
        SubHeader = subhead;
        mImageResourceId = imageResourceId;
        RequestStatus = reqstat;
        response = 0;
        amount = amt;
    }
    /**
     * Create a new Word object.
     *
     * @param head is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param subhead is the word in the Miwok language
     * @param imageResourceId is the drawable resource ID for the image associated with the word
     * @param reqstat is the resource ID for the audio file associated with this word
     */
    public Word(String head, String subhead, int imageResourceId,
                int reqstat) {
        Header = head;
        SubHeader = subhead;
        mImageResourceId = imageResourceId;
        RequestStatus = reqstat;
        response = 0;
    }
    public Word(String dat,String desc,String head, String subhead, int imageResourceId,
                int reqstat) {
        Header = head;
        SubHeader = subhead;
        mImageResourceId = imageResourceId;
        RequestStatus = reqstat;
        response = 0;
        date = dat;
        description = desc;
    }

    /**
     * Get the default translation of the word.
     */
    public String getSubHeader() {
        return SubHeader;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getHeader() {
        return Header;
    }

    /**
     * Return the image resource ID of the word.
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Return the audio resource ID of the word.
     */
    public int getRequestStatus() {
        return RequestStatus;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public int getResponse() {
        return response;
    }

    public void setRequestStatus(int requestStatus) {
        RequestStatus = requestStatus;
    }
}
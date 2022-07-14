package com.luv2code.springboot.thymeleafdemo.entity;


import javax.persistence.*;

@Entity
@Table(name="docs")
public class Doc {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    private String docName;
    private String docType;

    @Lob
    private byte[] data;


    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;


    public Doc(String docName, String docType, byte[] data, Application application) {
        this.id = id;
        this.docName = docName;
        this.docType = docType;
        this.data = data;
        this.application = application;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Doc() {
    }


    public Doc( String docName, String docType, byte[] data) {
        this.id = id;
        this.docName = docName;
        this.docType = docType;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

package interview.library.model;

import interview.library.model.converter.BooleanStringConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonIdInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    String documentType;

    @Column(nullable = false)
    String issuingState;

    @Column(nullable = false)
    String documentNumber;

    @Column(nullable = false)
    @Convert(converter = BooleanStringConverter.class)
    boolean isValidDocumentNumber;

    @Column(nullable = false)
    String dateOfBirth;

    @Column(nullable = false)
    @Convert(converter = BooleanStringConverter.class)
    boolean isValidDateOfBirth;

    @Column(nullable = false)
    String sex;

    @Column(nullable = false)
    String dateOfExpiry;

    @Column(nullable = false)
    @Convert(converter = BooleanStringConverter.class)
    boolean isValidDateOfExpiry;

    @Column(nullable = false)
    String nationality;

    @Column(nullable = false)
    @Convert(converter = BooleanStringConverter.class)
    boolean isValidFirstTwoLines;

    @Column(nullable = false)
    String primaryIdentifier;

    @Column(nullable = false)
    String secondaryIdentifier;

    public PersonIdInfo() {}

    public PersonIdInfo(String documentType, String issuingState, String documentNumber, boolean isValidDocumentNumber, String dateOfBirth, boolean isValidDateOfBirth, String sex, String dateOfExpiry, boolean isValidDateOfExpiry, String nationality, boolean isValidFirstTwoLines, String primaryIdentifier, String secondaryIdentifier) {
        this.documentType = documentType;
        this.issuingState = issuingState;
        this.documentNumber = documentNumber;
        this.isValidDocumentNumber = isValidDocumentNumber;
        this.dateOfBirth = dateOfBirth;
        this.isValidDateOfBirth = isValidDateOfBirth;
        this.sex = sex;
        this.dateOfExpiry = dateOfExpiry;
        this.isValidDateOfExpiry = isValidDateOfExpiry;
        this.nationality = nationality;
        this.isValidFirstTwoLines = isValidFirstTwoLines;
        this.primaryIdentifier = primaryIdentifier;
        this.secondaryIdentifier = secondaryIdentifier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getIssuingState() {
        return issuingState;
    }

    public void setIssuingState(String issuingState) {
        this.issuingState = issuingState;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public boolean isValidDocumentNumber() {
        return isValidDocumentNumber;
    }

    public void setIsValidDocumentNumber(boolean validDocumentNumber) {
        isValidDocumentNumber = validDocumentNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isValidDateOfBirth() {
        return isValidDateOfBirth;
    }

    public void setIsValidDateOfBirth(boolean isValidDateOfBirth) {
        this.isValidDateOfBirth = isValidDateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public boolean isValidDateOfExpiry() {
        return isValidDateOfExpiry;
    }

    public void setIsValidDateOfExpiry(boolean validDateOfExpiry) {
        isValidDateOfExpiry = validDateOfExpiry;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isValidFirstTwoLines() {
        return isValidFirstTwoLines;
    }

    public void setIsValidFirstTwoLines(boolean isValidFirstTwoLines) {
        this.isValidFirstTwoLines = isValidFirstTwoLines;
    }

    public String getPrimaryIdentifier() {
        return primaryIdentifier;
    }

    public void setPrimaryIdentifier(String primaryIdentifier) {
        this.primaryIdentifier = primaryIdentifier;
    }

    public String getSecondaryIdentifier() {
        return secondaryIdentifier;
    }

    public void setSecondaryIdentifier(String secondaryIdentifier) {
        this.secondaryIdentifier = secondaryIdentifier;
    }
}

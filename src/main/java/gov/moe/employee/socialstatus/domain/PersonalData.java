package gov.moe.employee.socialstatus.domain;



import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A PersonalData.
 */
@Entity
@Table(name = "personal_data")
public class PersonalData implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotNull
    @Column(name = "oracle_number", nullable = false)
    private String oracleNumber;

    @NotNull
    @Column(name = "job_function", nullable = false)
    private String jobFunction;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @Column(name = "school_administration")
    private String schoolAdministration;

    @Column(name = "gender")
    private String gender;

    @Column(name = "sector")
    private String sector;

    @Column(name = "social_status")
    private String socialStatus;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_at")
    private Instant modifiedAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    @OneToOne
    @JoinColumn(unique = true)
    private AttachmentInfo attachmentInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public PersonalData fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOracleNumber() {
        return oracleNumber;
    }

    public PersonalData oracleNumber(String oracleNumber) {
        this.oracleNumber = oracleNumber;
        return this;
    }

    public void setOracleNumber(String oracleNumber) {
        this.oracleNumber = oracleNumber;
    }

    public String getJobFunction() {
        return jobFunction;
    }

    public PersonalData jobFunction(String jobFunction) {
        this.jobFunction = jobFunction;
        return this;
    }

    public void setJobFunction(String jobFunction) {
        this.jobFunction = jobFunction;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public PersonalData appointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
        return this;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getSchoolAdministration() {
        return schoolAdministration;
    }

    public PersonalData schoolAdministration(String schoolAdministration) {
        this.schoolAdministration = schoolAdministration;
        return this;
    }

    public void setSchoolAdministration(String schoolAdministration) {
        this.schoolAdministration = schoolAdministration;
    }

    public String getGender() {
        return gender;
    }

    public PersonalData gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSector() {
        return sector;
    }

    public PersonalData sector(String sector) {
        this.sector = sector;
        return this;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSocialStatus() {
        return socialStatus;
    }

    public PersonalData socialStatus(String socialStatus) {
        this.socialStatus = socialStatus;
        return this;
    }

    public void setSocialStatus(String socialStatus) {
        this.socialStatus = socialStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public PersonalData nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public PersonalData contactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public PersonalData createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PersonalData createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public PersonalData modifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public PersonalData modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public AttachmentInfo getAttachmentInfo() {
        return attachmentInfo;
    }

    public PersonalData attachmentInfo(AttachmentInfo attachmentInfo) {
        this.attachmentInfo = attachmentInfo;
        return this;
    }

    public void setAttachmentInfo(AttachmentInfo attachmentInfo) {
        this.attachmentInfo = attachmentInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonalData personalData = (PersonalData) o;
        if (personalData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personalData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PersonalData{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", oracleNumber='" + getOracleNumber() + "'" +
            ", jobFunction='" + getJobFunction() + "'" +
            ", appointmentDate='" + getAppointmentDate() + "'" +
            ", schoolAdministration='" + getSchoolAdministration() + "'" +
            ", gender='" + getGender() + "'" +
            ", sector='" + getSector() + "'" +
            ", socialStatus='" + getSocialStatus() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", contactNumber='" + getContactNumber() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", modifiedAt='" + getModifiedAt() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            "}";
    }
}

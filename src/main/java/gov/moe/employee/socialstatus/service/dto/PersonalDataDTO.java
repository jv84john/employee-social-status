package gov.moe.employee.socialstatus.service.dto;
import java.time.Instant;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PersonalData entity.
 */
public class PersonalDataDTO implements Serializable {

    private Long id;

    @NotNull
    private String fullName;

    @NotNull
    private String oracleNumber;

    @NotNull
    private String jobFunction;

    private LocalDate appointmentDate;

    private String schoolAdministration;

    private String gender;

    private String sector;

    private String socialStatus;

    private String nationality;

    private String contactNumber;

    private Instant createdAt;

    private String createdBy;

    private Instant modifiedAt;

    private String modifiedBy;


    private Long attachmentInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOracleNumber() {
        return oracleNumber;
    }

    public void setOracleNumber(String oracleNumber) {
        this.oracleNumber = oracleNumber;
    }

    public String getJobFunction() {
        return jobFunction;
    }

    public void setJobFunction(String jobFunction) {
        this.jobFunction = jobFunction;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getSchoolAdministration() {
        return schoolAdministration;
    }

    public void setSchoolAdministration(String schoolAdministration) {
        this.schoolAdministration = schoolAdministration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(String socialStatus) {
        this.socialStatus = socialStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getAttachmentInfoId() {
        return attachmentInfoId;
    }

    public void setAttachmentInfoId(Long attachmentInfoId) {
        this.attachmentInfoId = attachmentInfoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonalDataDTO personalDataDTO = (PersonalDataDTO) o;
        if (personalDataDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personalDataDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PersonalDataDTO{" +
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
            ", attachmentInfo=" + getAttachmentInfoId() +
            "}";
    }
}

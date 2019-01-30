package gov.moe.employee.socialstatus.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DependentInfo entity.
 */
public class DependentInfoDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private LocalDate marriageDate;

    private String workingStatus;

    private String employer;

    private String employerType;

    private LocalDate hireDate;

    private String houseAllowanceProvided;

    private String governmentHousing;


    private Long personalDataId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    public String getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getEmployerType() {
        return employerType;
    }

    public void setEmployerType(String employerType) {
        this.employerType = employerType;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getHouseAllowanceProvided() {
        return houseAllowanceProvided;
    }

    public void setHouseAllowanceProvided(String houseAllowanceProvided) {
        this.houseAllowanceProvided = houseAllowanceProvided;
    }

    public String getGovernmentHousing() {
        return governmentHousing;
    }

    public void setGovernmentHousing(String governmentHousing) {
        this.governmentHousing = governmentHousing;
    }

    public Long getPersonalDataId() {
        return personalDataId;
    }

    public void setPersonalDataId(Long personalDataId) {
        this.personalDataId = personalDataId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DependentInfoDTO dependentInfoDTO = (DependentInfoDTO) o;
        if (dependentInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dependentInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DependentInfoDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", marriageDate='" + getMarriageDate() + "'" +
            ", workingStatus='" + getWorkingStatus() + "'" +
            ", employer='" + getEmployer() + "'" +
            ", employerType='" + getEmployerType() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            ", houseAllowanceProvided='" + getHouseAllowanceProvided() + "'" +
            ", governmentHousing='" + getGovernmentHousing() + "'" +
            ", personalData=" + getPersonalDataId() +
            "}";
    }
}

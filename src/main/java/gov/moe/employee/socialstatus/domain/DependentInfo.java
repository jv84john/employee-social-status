package gov.moe.employee.socialstatus.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DependentInfo.
 */
@Entity
@Table(name = "dependent_info")
public class DependentInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "marriage_date")
    private LocalDate marriageDate;

    @Column(name = "working_status")
    private String workingStatus;

    @Column(name = "employer")
    private String employer;

    @Column(name = "employer_type")
    private String employerType;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "house_allowance_provided")
    private String houseAllowanceProvided;

    @Column(name = "government_housing")
    private String governmentHousing;

    @ManyToOne
    @JsonIgnoreProperties("dependentInfos")
    private PersonalData personalData;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public DependentInfo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public DependentInfo marriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
        return this;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    public String getWorkingStatus() {
        return workingStatus;
    }

    public DependentInfo workingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
        return this;
    }

    public void setWorkingStatus(String workingStatus) {
        this.workingStatus = workingStatus;
    }

    public String getEmployer() {
        return employer;
    }

    public DependentInfo employer(String employer) {
        this.employer = employer;
        return this;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getEmployerType() {
        return employerType;
    }

    public DependentInfo employerType(String employerType) {
        this.employerType = employerType;
        return this;
    }

    public void setEmployerType(String employerType) {
        this.employerType = employerType;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public DependentInfo hireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getHouseAllowanceProvided() {
        return houseAllowanceProvided;
    }

    public DependentInfo houseAllowanceProvided(String houseAllowanceProvided) {
        this.houseAllowanceProvided = houseAllowanceProvided;
        return this;
    }

    public void setHouseAllowanceProvided(String houseAllowanceProvided) {
        this.houseAllowanceProvided = houseAllowanceProvided;
    }

    public String getGovernmentHousing() {
        return governmentHousing;
    }

    public DependentInfo governmentHousing(String governmentHousing) {
        this.governmentHousing = governmentHousing;
        return this;
    }

    public void setGovernmentHousing(String governmentHousing) {
        this.governmentHousing = governmentHousing;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public DependentInfo personalData(PersonalData personalData) {
        this.personalData = personalData;
        return this;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
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
        DependentInfo dependentInfo = (DependentInfo) o;
        if (dependentInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dependentInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DependentInfo{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", marriageDate='" + getMarriageDate() + "'" +
            ", workingStatus='" + getWorkingStatus() + "'" +
            ", employer='" + getEmployer() + "'" +
            ", employerType='" + getEmployerType() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            ", houseAllowanceProvided='" + getHouseAllowanceProvided() + "'" +
            ", governmentHousing='" + getGovernmentHousing() + "'" +
            "}";
    }
}

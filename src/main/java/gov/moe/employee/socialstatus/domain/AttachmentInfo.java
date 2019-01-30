package gov.moe.employee.socialstatus.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AttachmentInfo.
 */
@Entity
@Table(name = "attachment_info")
public class AttachmentInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "marriage_cert")
    private String marriageCert;

    @Column(name = "salary_cert")
    private String salaryCert;

    @Column(name = "death_cert")
    private String deathCert;

    @Column(name = "divorce_doc")
    private String divorceDoc;

    @Column(name = "synopsis")
    private String synopsis;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarriageCert() {
        return marriageCert;
    }

    public AttachmentInfo marriageCert(String marriageCert) {
        this.marriageCert = marriageCert;
        return this;
    }

    public void setMarriageCert(String marriageCert) {
        this.marriageCert = marriageCert;
    }

    public String getSalaryCert() {
        return salaryCert;
    }

    public AttachmentInfo salaryCert(String salaryCert) {
        this.salaryCert = salaryCert;
        return this;
    }

    public void setSalaryCert(String salaryCert) {
        this.salaryCert = salaryCert;
    }

    public String getDeathCert() {
        return deathCert;
    }

    public AttachmentInfo deathCert(String deathCert) {
        this.deathCert = deathCert;
        return this;
    }

    public void setDeathCert(String deathCert) {
        this.deathCert = deathCert;
    }

    public String getDivorceDoc() {
        return divorceDoc;
    }

    public AttachmentInfo divorceDoc(String divorceDoc) {
        this.divorceDoc = divorceDoc;
        return this;
    }

    public void setDivorceDoc(String divorceDoc) {
        this.divorceDoc = divorceDoc;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public AttachmentInfo synopsis(String synopsis) {
        this.synopsis = synopsis;
        return this;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
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
        AttachmentInfo attachmentInfo = (AttachmentInfo) o;
        if (attachmentInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attachmentInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttachmentInfo{" +
            "id=" + getId() +
            ", marriageCert='" + getMarriageCert() + "'" +
            ", salaryCert='" + getSalaryCert() + "'" +
            ", deathCert='" + getDeathCert() + "'" +
            ", divorceDoc='" + getDivorceDoc() + "'" +
            ", synopsis='" + getSynopsis() + "'" +
            "}";
    }
}

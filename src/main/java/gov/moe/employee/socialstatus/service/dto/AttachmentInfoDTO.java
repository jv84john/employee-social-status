package gov.moe.employee.socialstatus.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AttachmentInfo entity.
 */
public class AttachmentInfoDTO implements Serializable {

    private Long id;

    private String marriageCert;

    private String salaryCert;

    private String deathCert;

    private String divorceDoc;

    private String synopsis;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarriageCert() {
        return marriageCert;
    }

    public void setMarriageCert(String marriageCert) {
        this.marriageCert = marriageCert;
    }

    public String getSalaryCert() {
        return salaryCert;
    }

    public void setSalaryCert(String salaryCert) {
        this.salaryCert = salaryCert;
    }

    public String getDeathCert() {
        return deathCert;
    }

    public void setDeathCert(String deathCert) {
        this.deathCert = deathCert;
    }

    public String getDivorceDoc() {
        return divorceDoc;
    }

    public void setDivorceDoc(String divorceDoc) {
        this.divorceDoc = divorceDoc;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AttachmentInfoDTO attachmentInfoDTO = (AttachmentInfoDTO) o;
        if (attachmentInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attachmentInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttachmentInfoDTO{" +
            "id=" + getId() +
            ", marriageCert='" + getMarriageCert() + "'" +
            ", salaryCert='" + getSalaryCert() + "'" +
            ", deathCert='" + getDeathCert() + "'" +
            ", divorceDoc='" + getDivorceDoc() + "'" +
            ", synopsis='" + getSynopsis() + "'" +
            "}";
    }
}

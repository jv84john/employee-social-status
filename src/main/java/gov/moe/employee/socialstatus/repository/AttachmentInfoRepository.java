package gov.moe.employee.socialstatus.repository;

import gov.moe.employee.socialstatus.domain.AttachmentInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AttachmentInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttachmentInfoRepository extends JpaRepository<AttachmentInfo, Long> {

}

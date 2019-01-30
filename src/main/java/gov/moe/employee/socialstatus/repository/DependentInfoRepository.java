package gov.moe.employee.socialstatus.repository;

import gov.moe.employee.socialstatus.domain.DependentInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DependentInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DependentInfoRepository extends JpaRepository<DependentInfo, Long> {

}

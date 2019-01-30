package gov.moe.employee.socialstatus.repository;

import gov.moe.employee.socialstatus.domain.PersonalData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PersonalData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {

}

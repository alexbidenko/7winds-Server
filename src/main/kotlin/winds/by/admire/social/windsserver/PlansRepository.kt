package winds.by.admire.social.windsserver

import org.springframework.data.jpa.repository.JpaRepository

internal interface PlansRepository : JpaRepository<PlanObject, Long>
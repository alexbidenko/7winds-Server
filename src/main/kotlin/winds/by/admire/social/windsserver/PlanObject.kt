package winds.by.admire.social.windsserver

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class PlanObject(
        val project_id: Long?,
        val time: Long?) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null
}
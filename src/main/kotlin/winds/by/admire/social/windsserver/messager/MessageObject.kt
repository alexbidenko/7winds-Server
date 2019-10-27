package winds.by.admire.social.windsserver.messager

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class MessageObject(
        val id_from: Long?,
        val id_to: Long?,
        val message: String,
        var time: Long?) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null
}
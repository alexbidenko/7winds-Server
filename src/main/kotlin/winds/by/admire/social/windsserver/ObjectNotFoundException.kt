package winds.by.admire.social.windsserver

internal class ObjectNotFoundException(id: Long?) : RuntimeException("Could not find employee " + id!!)
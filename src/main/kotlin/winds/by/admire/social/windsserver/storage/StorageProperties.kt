package winds.by.admire.social.windsserver.storage

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("storage")
class StorageProperties {

    var location = "upload-dir"
}
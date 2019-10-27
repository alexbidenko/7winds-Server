package winds.by.admire.social.windsserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import winds.by.admire.social.windsserver.storage.StorageProperties

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties::class)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
    // nohup java -jar 7winds-server-0.1.2.jar 2>&1 >> logfile.log &
    // kill 30433
}
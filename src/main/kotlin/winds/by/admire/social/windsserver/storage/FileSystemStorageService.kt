package winds.by.admire.social.windsserver.storage

import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.stream.Stream

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile

@Service
class FileSystemStorageService @Autowired
constructor(properties: StorageProperties) : StorageService {

    private val rootLocation = Paths.get(properties.location)

    init {
        try {
            Files.createDirectories(rootLocation)
        } catch (e: IOException) {
            throw StorageException("Could not initialize storage", e)
        }
    }

    override fun store(file: MultipartFile) {
        val filename = Filename.value
        try {
            println("isEmpty")
            if (file.isEmpty) {
                throw StorageException("Failed to store empty file $filename")
            }
            println("contains")
            if (filename.contains("..")) {
                // This is a security check
                throw StorageException(
                        "Cannot store file with relative path outside current directory $filename")
            }
            println(this.rootLocation.resolve(filename).toAbsolutePath().toString())
            file.inputStream.use { inputStream ->
                Files.copy(inputStream, this.rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING)
            }
            println("finish store")
        } catch (e: IOException) {
            throw StorageException("Failed to store file $filename", e)
        }
    }

    override fun loadAll(): Stream<Path> {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter { path -> path != this.rootLocation }
                    //.map(Function<Path, Path> { this.rootLocation.relativize(it) })
        } catch (e: IOException) {
            throw StorageException("Failed to read stored files", e)
        }

    }

    override fun load(filename: String): Path {
        return rootLocation.resolve(filename)
    }

    override fun loadAsResource(filename: String): Resource {
        try {
            val file = load(filename)
            val resource = UrlResource(file.toUri())
            return if (resource.exists() || resource.isReadable) {
                resource
            } else {
                throw StorageFileNotFoundException(
                        "Could not read file: $filename")

            }
        } catch (e: MalformedURLException) {
            throw StorageFileNotFoundException("Could not read file: $filename", e)
        }

    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile())
    }

    override fun deleteFile(filename: String) {
        FileSystemUtils.deleteRecursively(this.rootLocation.resolve(filename))
    }

    override fun existFile(filename: String): Boolean {
        return Files.exists(this.rootLocation.resolve(filename))
    }
}
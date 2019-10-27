package winds.by.admire.social.windsserver

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import winds.by.admire.social.windsserver.storage.Filename
import winds.by.admire.social.windsserver.storage.StorageService;

@Controller
class FileController @Autowired constructor(var storageService: StorageService) {

    /*@GetMapping("/")
    fun listUploadedFiles(model: Model): String {

        model.addAttribute("files", storageService!!.loadAll().map(
                path to MvcUriComponentsBuilder.fromMethodName(FileController::class.java,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }*/

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    fun serveFile(@PathVariable filename: String): ResponseEntity<Resource> {

        val file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                """attachment; filename="${file.filename}"""").body(file)
    }

    @PostMapping("/upload-file")
    @ResponseBody
    fun handleFileUpload(@RequestParam("file") file: MultipartFile,
                         @RequestParam("old_file") old_file: String?,
            redirectAttributes: RedirectAttributes): String {

        println("Start")
        try {
            storageService.deleteFile(old_file!!)
            println("Delete")
        } catch (e: Exception) {}

        println("Next")
        Filename.value = System.currentTimeMillis().toString() + "." + file.originalFilename!!.substringAfterLast(".")
        println("store")
        storageService.store(file)
        println("message")
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.originalFilename + "!")
        println("complete")

        return Filename.value
    }

    @GetMapping("/delete/{filename:.+}")
    @ResponseBody
    fun deleteFile(@PathVariable filename: String): String {

        if(storageService.existFile(filename))
            storageService.deleteFile(filename)

        return "well"
    }
}
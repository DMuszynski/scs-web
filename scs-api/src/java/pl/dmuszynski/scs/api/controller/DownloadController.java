package pl.dmuszynski.scs.api.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping(value = "/download/client")
public class DownloadController {

    @GetMapping("/{fileName}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<InputStreamResource> downloadClient(@PathVariable("fileName") String fileName) throws IOException {
        Resource resource = new ClassPathResource("scs-client.zip");
        InputStream inputStream = resource.getInputStream();

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".zip")
            .contentType(MediaType.parseMediaType("application/zip")).contentLength(resource.contentLength())
            .body(new InputStreamResource(inputStream));
    }
}

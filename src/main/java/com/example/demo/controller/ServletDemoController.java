package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servlet-demo")
public class ServletDemoController {


  @GetMapping
  public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {

    ServletOutputStream stream = response.getOutputStream();

    Path path = Paths.get("C:\\file\\hoge.txt");
    File file = path.toFile();
//    try {
//
//      download(response, file, MediaType.APPLICATION_JSON_VALUE);
//    } catch(IOException e) {
//
////      response.sendError(HttpServletResponse.SC_ACCEPTED);
//    }
    ServletOutputStream servletOutputStream = null;
    try  {
      servletOutputStream = response.getOutputStream();
      throw new IOException();
    } catch (IOException e) {
      response.sendError(HttpServletResponse.SC_BAD_GATEWAY);
    } finally {

      servletOutputStream.close();

    }


    // stream.flush();

  }

  public void download(HttpServletResponse response, File file, String contentType)
      throws Exception {

    response.setContentType(contentType);
    // if(!file.exists()) {
    // return;
    // }
    try (ServletOutputStream out = response.getOutputStream();) {

//      output(file, out);
      throw new IOException();

    } catch (IOException e) {

      response.sendError(HttpServletResponse.SC_ACCEPTED);
//      throw e;
    }
  }

  public static void output(File file, OutputStream stream) throws IOException {

    int r;
    try (InputStream iStream = new FileInputStream(file);) {

      BufferedInputStream bis = new BufferedInputStream(iStream);
      BufferedOutputStream bos = new BufferedOutputStream(stream);
      while ((r = bis.read()) != -1) {
        // bos.write(r);

      }

      // bos.flush();

    } catch (IOException e) {

      throw e;

    }

  }
}

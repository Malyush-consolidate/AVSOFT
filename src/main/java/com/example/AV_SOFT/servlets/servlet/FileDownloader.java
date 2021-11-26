package com.example.AV_SOFT.servlets.servlet;

import com.example.AV_SOFT.servlets.service.FileListUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(value = "/get_file")
public class FileDownloader extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String downloadingFileName = req.getParameter("file_name");
        Path path = Paths.get(FileListUtil.filesPath.toString(), downloadingFileName).toAbsolutePath();
        resp.setContentType("APPLICATION/OCTET-STREAM");
        resp.setHeader("Content-disposition", "attachment; filename=" + downloadingFileName);
        try(PrintWriter out = resp.getWriter();
            FileInputStream in = new FileInputStream(path.toString());  ) {
            int i;
            while ((i=in.read()) != -1) {
                out.write(i);
            }
        }
    }
}

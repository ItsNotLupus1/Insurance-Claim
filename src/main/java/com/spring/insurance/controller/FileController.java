package com.spring.insurance.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.insurance.entity.FileUpload;
import com.spring.insurance.entity.Holder;
import com.spring.insurance.repo.FileRepository;
import com.spring.insurance.repo.HolderRepository;

@Controller
public class FileController {
	
	@Autowired
	private FileRepository Filerepo;
	
	@Autowired
	private HolderRepository repo;

	@ModelAttribute("FilesEntity")
	public Holder getDetails() {
		String fname = SecurityContextHolder.getContext().getAuthentication().getName();
		Holder user = repo.findByUsername(fname);
		return user;
	}
	
	@RequestMapping(value = "/getFile", method = RequestMethod.GET)
	public String showUploadForm(Model model) {
		model.addAttribute("file", new FileUpload());
		return "Upload";
	}
	
	@GetMapping("/getFileCancelMethod")
	public String cancel(@ModelAttribute("entity") Holder user)
	{
		return "home";
	}
	
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam MultipartFile[] fileUpload, @RequestParam("holder_id") Long id, Model model)  throws Exception {
         
        if (fileUpload != null && fileUpload.length > 0) {
            for (MultipartFile aFile : fileUpload){
                
            	try {
            		String rootpath = System.getProperty("user.home");
            		File dir = new File(rootpath + File.separator + "FileUploadTesting" + File.separator + id);
            		System.out.println("Path " + dir.getAbsolutePath());
            		if(!dir.exists()) {
            			dir.mkdirs();
            		}
            		
            		File serverFile = new File(dir.getAbsolutePath() + File.separator + aFile.getOriginalFilename());
            		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            		stream.write(aFile.getBytes());
            		stream.close();
            		System.out.println("Server File Location = " + serverFile.getAbsolutePath());
            	
	                System.out.println("Saving file: " + aFile.getOriginalFilename());
	                
	                FileUpload uploadFile = new FileUpload();
	                uploadFile.setFileName(aFile.getOriginalFilename());
	                uploadFile.setFilepath(serverFile.getAbsolutePath());
	                uploadFile.setHolder_id(id);
	                model.addAttribute("entity", new Holder());
	                Filerepo.save(uploadFile);
	                System.out.println( "You successfully uploaded file=" + aFile.getOriginalFilename());
	            }
            	catch (Exception e) {
            		System.out.println("You failed to upload " + aFile.getOriginalFilename() + " => " + e.getMessage());
				}
            }
        }
        return "redirect:/home";
    }
    
    @RequestMapping(value = "/download/filename={fileName}&&user={holder_id}")
    public void downloadFile(@PathVariable("holder_id") Long id, @PathVariable("fileName") String fileName,
    		HttpServletResponse resp, @ModelAttribute("entity") Holder user) throws Exception {
    	
    	try {
    		String rootpath = System.getProperty("user.home");
    		File dir = new File(rootpath + File.separator + "FileUploadTesting" + File.separator + id);
			FileInputStream input = new FileInputStream(dir.getAbsolutePath() + File.separator + fileName);
			BufferedInputStream buffer = new BufferedInputStream(input);
			resp.setContentType("APPLICATION/OCTET-STREAM");
			resp.addHeader("Content-Disposition", "attachment; filename="+fileName);
			IOUtils.copy(buffer, resp.getOutputStream());
			resp.getOutputStream().flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    }
}

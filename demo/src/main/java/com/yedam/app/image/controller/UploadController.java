package com.yedam.app.image.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class UploadController {

	@Value("${part4.upload.path}")
	private String uploadPath;

	@GetMapping("/uploadForm")
	public String getUploadForm() {
		return "imageUpload";
	}
	@PostMapping("/uploadAjax")
	@ResponseBody
	public void uploadFile(MultipartFile[] uploadFiles) {

		for (MultipartFile uploadFile : uploadFiles) {

			if (uploadFile.getContentType().startsWith("image") == false) {
				log.warn("this file is not image type");
				return;
			}
			String originalName = uploadFile.getOriginalFilename();
			String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);

			log.info("fileName" + fileName);

			// 날짜 폴더 생성
			String folderPath = makeFolder();
			// UUID
			String uuid = UUID.randomUUID().toString();
			// 저장할 파일 이름 중간에 "_"를 이용하여 구분
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

			Path savePath = Paths.get(saveName);
			// Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)

			try {
				uploadFile.transferTo(savePath);
				// uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
			} catch (IOException e) {
				e.printStackTrace();
				// printStackTrace()를 호출하면 로그에 Stack trace가 출력됩니다.
			}

		} // end for
	}

	private String makeFolder() {

		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator);
		// 만약 Data 밑에 exam.jpg라는 파일을 원한다고 할때,
		// 윈도우는 "Data\\"eaxm.jpg", 리눅스는 "Data/exam.jpg"라고 씁니다.
		// 그러나 자바에서는 "Data" +File.separator + "exam.jpg" 라고 쓰면 됩니다.

		// make folder ==================
		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File(dir,"파일명");
		// ->부모 디렉토리를 파라미터로 인스턴스 생성

		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}
}

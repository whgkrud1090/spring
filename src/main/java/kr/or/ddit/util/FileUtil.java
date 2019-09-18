package kr.or.ddit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import kr.or.ddit.util.model.FileInfo;

public class FileUtil {

	public static FileInfo getFileInfo(String originalFileName) {
		String path = getPath();
		
		String uuidFileName = UUID.randomUUID().toString();
		String extName = getExtension(originalFileName);
		
		//업로드할 파일
		File file = new File(path + "\\" + uuidFileName + extName);
		FileInfo fileInfo = new FileInfo(file, extName, originalFileName);
		
		return fileInfo;
	}

	private static String getPath() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String yyyyMM = sdf.format(new Date());
		String yyyy = yyyyMM.substring(0, 4);
		String MM = yyyyMM.substring(4, 6);
		
		String path = "e:\\springUpload\\" + yyyy + "\\" + MM;
		File pathFile = new File(path);
		
		//파일이 존재하지 않으면 생성
		if(!pathFile.exists()) pathFile.mkdir();
		return path;
	}

	private static String getExtension(String originalFileName) {
		//확장자가 없을 경우 공백 리턴
		//파일명에 .이 여러개 등장할 경우 가장 마지막에 있는 녀석이 확장자 구분자로 판단
		int index = originalFileName.lastIndexOf(".");
		if(index > 0) {
			return originalFileName.substring(originalFileName.lastIndexOf("."));
		}else {
			return "";
		}
	}
}

package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

public class FileDownloadView extends AbstractView{

	//model : 개발자가 controller메소드에서 등록한 속성들이 저장
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//컨트롤러 메소드에 추가한 속성
		String pictureName = (String) model.get("pictureName");
		
		//이미지 존재 여부 확인
		pictureName = pictureName == null ? "noimage" : pictureName;

		//고정된 이미지응답으로 생성
		//파일 전송임을 알려주는 헤더정보 설정
		response.setHeader("content-disposition", "attachment;filename="+pictureName+".png");
		response.setContentType("application/octet-stream");	//바이너리로 요청이 전송됨
		File file = new File("e:\\rangers\\"+pictureName+".png");
		FileInputStream fis = new FileInputStream(file);
		
		//파일다운로드
		ServletOutputStream sos = response.getOutputStream();

		byte[] buffer = new byte[512];
		int len = 0;
		
		while((len = fis.read(buffer, 0, 512)) != -1) sos.write(buffer, 0, len);
		fis.close();
	}

}

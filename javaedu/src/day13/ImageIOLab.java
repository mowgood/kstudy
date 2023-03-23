package day13;

/*
(1) list.txt 파일의 내용을 읽는다.
     파일 안에는 이미지 이름과 읽어올 이미지의 URL 이 존재한다.
(2) 지정된 URL로 이미지 파일을 읽어와서 c:/iotest/myimage 폴더에 
     이미지 이름으로 저장한다.(10개)

     생성되는 이미지 파일명은  cuteone.jpg, cutetwo.jpg .... cuteten.jpg 이다.

(3) 구현된 소스와 이미지 파일 10개를 압축하여 제출한다.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ImageIOLab {
	public static void main(String[] args) {
		String[] strArr;

		File f = new File("c:/iotest/list.txt");
		String word = "";
		try (Scanner scan = new Scanner(f);) {
			while(scan.hasNext()) {
				word = scan.next();
				strArr = word.split(",");
				
				String path = "C:/iotest/myimage";
				File isDir = new File(path);
				if (!isDir.exists()) {
					isDir.mkdirs();
				}
				try {
					URL req = new URL(strArr[1]);
					InputStream is = req.openStream();
					String fileName = "c:/iotest/myimage/" + strArr[0] + ".jpg";
					FileOutputStream fos = new FileOutputStream(fileName);
					int input = 0;
					while (true) {
						input = is.read();
						if (input == -1)
							break;
						fos.write(input);
					}
					fos.close();
				} catch (MalformedURLException e) {
					System.out.println("URL문자열 오류 : " + e.getMessage());
				} catch (IOException e) {
					System.out.println("IO 오류 : " + e.getMessage());
				}
			
			}				
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다");
		}
	}

}

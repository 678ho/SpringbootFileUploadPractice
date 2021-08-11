package org.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable{

    private String fileName;
    private String uuid;
    private String folderPath;
    public String getImageURL() {
        try{
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}

// 실제 파일과 관련된 모든 정보를 가지는데 전체 경로가 필요한 경우를 위해 getImageURL()을 제공.
//UploadController 에서는 업로드 결과를 반환하기 위해서 ResponseEntity를 이용해서 처리.


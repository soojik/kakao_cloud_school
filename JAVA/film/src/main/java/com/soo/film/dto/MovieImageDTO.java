package com.soo.film.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
//Serializable(직렬화): 데이터를 전송할 때 객체 단위로 전송할 수 있도록 해주는
//인터페이스
@Getter
public class MovieImageDTO implements Serializable {
  private String imgName;
  private String uuid;
  private String path;

  //실제 이미지 경로를 리턴해주는 메서드
  public String getImageURL(){
    try{
      return URLEncoder.encode(
          path + "/" + uuid + imgName,"UTF-8");
    }catch(UnsupportedEncodingException e){
      System.out.println(e.getLocalizedMessage());
      e.printStackTrace();
    }
    return "";
  }

  public String getThumbnailURL(){
    try{
      return URLEncoder.encode(
          path + "/s_" + uuid + imgName,"UTF-8");
    }catch(UnsupportedEncodingException e){
      System.out.println(e.getLocalizedMessage());
      e.printStackTrace();
    }
    return "";
  }
}

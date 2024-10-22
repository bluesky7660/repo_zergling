package com.exion.infra.kakao;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoBookResponse {
	@JsonProperty("documents")
    private List<KakaoBookVo> documents;

    public List<KakaoBookVo> getDocuments() {
        return documents;
    }
}

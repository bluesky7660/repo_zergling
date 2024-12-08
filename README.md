# repo_zergling
<h1>개인프로젝트1 - 도서 쇼핑몰[교보문고]</h1>

#URL <br/><br/>
     -USER- http://3.35.53.81:8080/index<br/>
     -ADMIN - http://3.35.53.81:8080/v1/infra/index/indexXdmView
<br /><br /><br /><br /><br /><br />
![image](https://github.com/user-attachments/assets/fc87222c-0543-466d-abc2-8742b5b2ad53)

<br /><br /><br />

## 💻 프로젝트 소개
도서 쇼핑몰 교보문고


<br /><br /><br />

##  🎯 구현 기능
<table>
     <tr>
          <th>🔐계정</th>
          <th>📕도서</th>
          <th>👤회원</th>
          <th>📝서평</th>
     </tr>
     <tr>
          <td>
               -로그인/로그아웃(카카오로그인)<br />
               -captcha 이용한 자동화방지<br />
               -회원가입<br />
          </td>
          <td>
               -카테고리,조건 검색<br />
               -도서 목록<br />
               -도서 리뷰 등록/삭제<br />
               -네이버,카카오[다음]의 베스트셀러<br />
          </td>
          <td>
               -회원정보 변경,비밀번호 변경<br />
               -카카오맵,우편번호api를 이용한 배송지 주소 등록/수정/삭제[CRUD]<br />
               -주문/배송 배송조회/취소<br />
          </td>
          <td>
               -글로 된 서평,출판사 서평이 아닌 영상으로 듣고 볼수 있는 Youtube 도서서평 영상<br />
               -관리자가 추가한 채널들의 서평관련 Youtube채널 등록/수정/삭제[CRUD]<br />
          </td>
     </tr>
</table>
<br /><br /><br />

## ⚙️ 기술 스택

### Frontend
<img src="https://img.shields.io/badge/HTML-%23E34F26.svg?style=flat&logo=html5&logoColor=white" alt="HTML5" height="40"/>  <img src="https://img.shields.io/badge/CSS-%231572B6.svg?style=flat&logo=css3&logoColor=white" alt="CSS3" height="40"/>
<img src="https://img.shields.io/badge/JavaScript-%23323330.svg?style=flat&logo=javascript&logoColor=F7DF1E" alt="JavaScript" height="40"/> <img src="https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery" alt="jQuery" height="40"/> <img src="https://img.shields.io/badge/Bootstrapap-7952B3?style=flat-square&logo=bootstrap&logoColor=white" alt="Bootstrap" height="40"/>  

### Backend
<img src="https://img.shields.io/badge/Spring-%6DB33F.svg?style=flat&logo=spring&logoColor=white" alt="Spring" height="40"/> <img src="https://img.shields.io/badge/Java-f26522.svg?style=flat&logo=java&logoColor=white" alt="Java"  height="40"/>  

### Database
<img src="https://img.shields.io/badge/MySQL-%234F78A1.svg?style=flat&logo=mysql&logoColor=white" alt="MySQL" height="40"/>  

### Tools & Platforms
<img src="https://img.shields.io/badge/GitHub-%23121011.svg?style=flat&logo=github&logoColor=white" alt="GitHub" height="40"/>  <img src="https://img.shields.io/badge/Amazon%20Web%20Services-FF9900?style=for-the-badge&logo=amazonwebservices" alt="AWS" height="40"/>  

### Authentication
<img src="https://img.shields.io/badge/Kakao-FFCC00.svg?style=flat&logo=kakao&logoColor=white" alt="Kakao Login" height="40"/>  
<br /><br /><br />

## 🌟 내부 기능 소개

<h4>1. 검색기능</h4>
<ul>
     <li>
          네비게이션 바의 검색 영역에 있는 셀렉트 박스를 활용하여 검색 타입을 지정할 수 있도록 구현했습니다. 이를 통해 사용자들이 더 정확하고 효율적으로 검색할 수 있도록 개선했습니다.
     </li>
     <li>
          도서 목록 페이지에 들어가면 왼쪽 사이드에서 다양한 검색 조건을 설정하여 더욱 자세히 검색할 수 있습니다.
     </li>
     <li>
          첫 번째 검색 조건으로는 도서 종류를 선택할 수 있으며, 두 번째 조건으로는 베스트셀러, 신상품, 또는 Today's Pick과 같은 자주 선택되는 키워드로 검색 조건을 지정하여 구현하였습니다.
     </li>
     <li>
          그 다음 조건으로 도서에 해당하는 혜택, 추가 조건, 가격별, 리뷰별 검색이 가능하며, 이때 nouislider를 사용하여 가격별, 리뷰별 검색을 최소값과 최대값을 설정할 수 있도록 구현하여 원하는 검색을 할수있도록 하였습니다..
     </li>
     <li>
          마지막으로, 라디오 버튼을 통해 특정 기간 동안 출간된 도서만 검색할 수 있는 기능도 추가했습니다.
     </li>
</ul>
<h4>2.리뷰 기능</h4>
<ul>
     <li>
          도서에 대한 키워드를 라디오 버튼을 통해 하나만 선택하도록 하여, 독자의 감성을 보다 잘 파악할 수 있도록 하였습니다.
     </li>
     <li>
          리뷰에 꽃과 같은 리뷰 점수를 매길 수 있도록 krajee의 star-rating 라이브러리를 사용하여 별점을 동적으로 선택할 수 있게 구현하였습니다.
     </li>
     <li>
          리뷰 목록 부분에서 리뷰를 작성한 후, 즉시 반영되어 리뷰들의 총점과 선택한 감성 태그들의 분포, 가장 많이 선택된 태그를 확인할 수 있도록 구현하였습니다.
     </li>
</ul>

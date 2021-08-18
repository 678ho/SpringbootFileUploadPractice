# SpringbootFileUploadPractice

1. movie, movie_image 테이블 생성

![1](https://user-images.githubusercontent.com/43158428/128112228-1715a1f9-5a46-463e-b9cc-ad82ad0d01c4.PNG)

2. m_member, review 테이블 생성

![2](https://user-images.githubusercontent.com/43158428/128114384-36cbc257-3ba1-4d3d-a954-5d0bc2b57298.PNG)

3. insertMovie Test Method result

![3](https://user-images.githubusercontent.com/43158428/128118145-205c4ec7-016a-46b5-80fc-bc1b12437394.PNG)
![4](https://user-images.githubusercontent.com/43158428/128118725-0b0e63f3-2594-459a-b0ac-92badfd2ad03.PNG)

4. insertMember Test Method result

![5](https://user-images.githubusercontent.com/43158428/128119670-719be4ff-9601-49b8-8a50-f9212db2dd7e.PNG)

5. insertMovieReview 테스트

![12](https://user-images.githubusercontent.com/43158428/128284009-48c3fbd7-4f80-40da-83e3-487ed0c2ad1a.PNG)

6. testListPage 테스트

![13](https://user-images.githubusercontent.com/43158428/128285665-c06ec5e9-280a-48e8-86bf-5d92b21cb814.PNG)

7. testGetMovieWithAll 테스트

![14](https://user-images.githubusercontent.com/43158428/128288503-980709de-5b08-4816-a69a-e6c35ccd6bc8.PNG)

8.testGetMovieReviews 테스트

![12](https://user-images.githubusercontent.com/43158428/128497447-bf7bd20e-36e8-4a5a-a2bd-63a568bd1420.PNG)

9.testDeleteMember 테스트

![13](https://user-images.githubusercontent.com/43158428/128500784-f97fcd71-5ac7-435b-b9dc-8bdff7a2c9f0.PNG)
![132](https://user-images.githubusercontent.com/43158428/128500793-7aea33b1-debb-40a9-890c-36fdf8d15e56.PNG)

10.register 후 데이터베이스 적용

![1423](https://user-images.githubusercontent.com/43158428/129831855-c1269532-73df-449d-89dc-34a7ada2a690.JPG)





### Prerequisites / 선행 조건

```
STS or Intellij or VSCode IDE 설치 되어있어야함.
```

### Installing / 설치

아래 사항들로 현 프로젝트에 관한 모듈들을 설치할 수 있습니다.

```
Spring Boot DevTools
Lombok
Spring web
Thymeleaf
Spring Data JPA
```

## Running the tests / 테스트의 실행

어떻게 테스트가 이 시스템에서 돌아가는지에 대한 설명을 합니다

### 테스트는 이런 식으로 동작합니다


```
1. insertMovies()

영화와 이미지들은 같은 시점에 insert처리가 되어야함. 때문에 Movie객체를 먼저 save후 PK에 해당하는 mno값이 할당되어, 이를 사용해 영화의 이미지를 추가시켜준다.
(이미지들은 최대 5개까지 임의로 저장되고 많은 이미지를 처리할 경우가 있으므로 임의의 수로 테스트 수행)

2. insertMembers()

테스트 코드를 이용해서 총 100명의 Member 추가.

3. insertMovieReviews()

200개의 MovieReview를 저장하고 영화의 번호와 회원은 임의의 값으로 현재 데이터베이스에 존재하는 값으로 생성 처리.

영화의 평점과 리뷰의 내용을 작성해서 MovieReview 객체를 생성해 저장.

4. testGetMovieWithAll()

리뷰와 관련된 내용 처리는 left outer join을 이용.

리뷰와 조인한 후에 count(), avg() 함수를 이용하는데 영화 이미지별로 group by 부분에 영화 이미지별로 그룹을 만들어서 이미지 개수만큼 데이터를 생성.

5. testGetMovieReviews()

@EntityGraph는 엔티티의 특정한 속성을 같이 로딩하도록 표시하는 어노테이션.

JPA를 이용하는 경우에는 연관 관계의 FATCH속성값은 LAZY로 지정하는것이 일반적임.

@EntityGraph는 이러한 상황에서 특정 기능을 수행할 때만 EAGER로딩을 하도록 지정할수있음.

6. testDeleteMember()

ReviewRepository에서 @Query를 이용해서 where절을 지정하게되면 한 번에 review테이블에서 삭제가됨.

(기존에는 where조건절에 member_mid 칼럼을 이용해서 3개의 데이터를 한번에 삭제하는것처럼 보이지만 실제로는 review 테이블에서 3번 반복적으로 실행된 후 m_member 테이블을 삭제함.)

7. 업로드 파일 데이터의 처리과정.

1)  ![1](https://user-images.githubusercontent.com/43158428/129832273-b549cd3e-7d71-4a22-a87f-c030140d78ee.JPG)

2)  ![2](https://user-images.githubusercontent.com/43158428/129832285-dc693e1f-6b73-4e0c-af07-8843d63e5445.JPG)

3)  ![3](https://user-images.githubusercontent.com/43158428/129832291-bb96df9a-a443-46d7-ab44-2ea933a9278a.JPG)

4)  ![4](https://user-images.githubusercontent.com/43158428/129832304-70a8fb13-cc47-4e5f-8e01-7803a3a6ba5f.JPG)

5)  ![5](https://user-images.githubusercontent.com/43158428/129832310-848dbb85-0538-4046-b8ab-1c01ba9da261.JPG)

```

### 테스트는 이런 식으로 작성하시면 됩니다

```
test 폴더 아래에 적절한 폴더를 생성후 폴더안에 테스트클래스 작성 후 테스트 실행.
```


## License / 라이센스

This project is licensed under the MIT License - see the [LICENSE.md](https://gist.github.com/PurpleBooth/LICENSE.md) file for details / 이 프로젝트는 MIT 라이센스로 라이센스가 부여되어 있습니다. 자세한 내용은 LICENSE.md 파일을 참고하세요.

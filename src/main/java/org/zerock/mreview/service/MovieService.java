package org.zerock.mreview.service;

import org.zerock.mreview.dto.MovieDTO;
import org.zerock.mreview.dto.MovieImageDTO;
import org.zerock.mreview.dto.PageRequestDTO;
import org.zerock.mreview.dto.PageResultDTO;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public interface MovieService {

    Long register(MovieDTO movieDTO);

    PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    default MovieDTO entitiesToDTO(Movie movie, List<MovieImage> movieImages, Double avg, Long reviewCnt) {
        MovieDTO movieDTO = MovieDTO.builder().mno(movie.getMno()).title(movie.getTitle()).regDate(movie.getRegDate()).modDate(movie.getModDate()).build();

        List<MovieImageDTO> movieImageDTOList = movieImages.stream().map(movieImage -> {
           return MovieImageDTO.builder().imgName(movieImage.getImgName()).path(movieImage.getPath()).uuid(movieImage.getUuid()).build();
        }).collect(Collectors.toList());

        movieDTO.setImageDTOList(movieImageDTOList);
        movieDTO.setAvg(avg);
        movieDTO.setReviewCnt(reviewCnt.intValue());

        return movieDTO;
    }
    /* 추가된 entitiesTODTO의 파라미터들
    *  1. Movie 엔티티
    *  2. List<MovieImage> - 조회 화면에서 여러 개의 이미지를 처리하기 위해.
    *  3. Double 타입의 평점
    *  4. Long 타입의 리뷰 갯수
    * */

    default Map<String, Object> dtoToEntity(MovieDTO movieDTO) {

        Map<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();

        entityMap.put("movie", movie);

        List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();

        if (imageDTOList != null && imageDTOList.size() > 0) {
            List<MovieImage> movieImageList = imageDTOList.stream().map(movieImageDTO -> {

                MovieImage movieImage = MovieImage.builder()
                        .path(movieImageDTO.getPath())
                        .imgName(movieImageDTO.getImgName())
                        .uuid(movieImageDTO.getUuid())
                        .movie(movie)
                        .build();
                return movieImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", movieImageList);
        }

        return entityMap;
    }
}

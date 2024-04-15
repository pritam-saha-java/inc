package com.incallup.backend.service.impl;


import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Post;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.repository.CategoryRepository;
import com.incallup.backend.repository.LocationRepository;
import com.incallup.backend.repository.PostRepository;
import com.incallup.backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final LocationRepository locationRepository;


    @Override
    public Post searchByTitle(String title) throws ApplicationException{
       Optional<Post> postOptional = postRepository.findPostByName(title);


        if(postOptional.isEmpty()){
            throw ApplicationException.builder()
                    .title("TitleNotFound")
                    .Description("Title Not Found")
                    .status(503)
                    .build();
        }

        var post = postOptional.get();
        try {
        post.setByteString(convertByteArrayToBase64(post.getImageData1().getBytes(1,(int)post.getImageData1().length())));
            post.setByteString2(convertByteArrayToBase64(post.getImageData2().getBytes(1,(int)post.getImageData2().length())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        log.info("logging information : "+post.getByteString2().length());


        int views = post.getViews();
        post.setViews(++views);
        postRepository.save(post);

        String inputDate = post.getCreatedAt().toString();
        String[] parts = inputDate.split("T");
        String dateString = parts[0];
        DateTimeFormatter inputFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate dateTime = LocalDate.parse(dateString);

        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM", Locale.ENGLISH);

        // Format the LocalDateTime object to a string
        String formattedDate = dateTime.format(formatter);
        post.setDate(formattedDate);

        return post;
    }

    @Override
    public List<Post> searchByCategory(String category) throws ApplicationException{





        Optional<Category> categoryOptional = categoryRepository.findCategoryByName(category.trim());
        if(categoryOptional.isEmpty()){
            throw ApplicationException.builder()
                    .title(category+" Category Not Found")
                    .Description("Please Enter proper category")
                    .status(404)
                    .build();
        }
        var categoryObj = categoryOptional.get();
        var posts = categoryObj.getPosts();
        posts.forEach((post -> {
            try {
                post.setByteString(convertByteArrayToBase64(post.getImageData1().getBytes(1,(int)post.getImageData1().length())));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(post.getTitle().length()>30)
                post.setTitle(post.getTitle().substring(0,28)+"..");
            if(post.getDescription().length()>150)
                post.setDescription(post.getDescription().substring(0,148)+"..");
        }));

       return posts;

    }

    public String convertByteArrayToBase64(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }


    @Override
    public List<Post> searchByCategoryAndLocation(String categoryString, String locationString) throws ApplicationException{
        var postsByLocationOptional = locationRepository.findLocationByDistrict(locationString);
        var categoryOptional = categoryRepository.findCategoryByName(categoryString);
        if(postsByLocationOptional.isEmpty()||categoryOptional.isEmpty()){
            throw ApplicationException.builder()
                    .title("NotFoundByCategoryAndLocation")
                    .Description("Not Found By Category or Location")
                    .status(503)
                    .build();
        }
        var category = categoryOptional.get();
        var location = postsByLocationOptional.get();
        var posts = location.getPosts();
         List<Post> postsByLocation = new LinkedList<>();
        posts.forEach( post -> {
            if(post.getCategory().equals(category))
                postsByLocation.add(post);
        });


        postsByLocation.forEach((post -> {
            try {
                post.setByteString(convertByteArrayToBase64(post.getImageData1().getBytes(1,(int)post.getImageData1().length())));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(post.getTitle().length()>30)
                post.setTitle(post.getTitle().substring(0,28)+"..");
            if(post.getDescription().length()>150)
                post.setDescription(post.getDescription().substring(0,148)+"..");
        }));
        return new ArrayList<>(postsByLocation);
    }

    @Override
    public List<Post> searchByLocation(String location) throws ApplicationException{
        var locationOpt = locationRepository.findLocationByDistrict(location);
        if(locationOpt.isEmpty()){
            throw ApplicationException.builder()
                    .title("LocationNotFound")
                    .Description("Location Not Found")
                    .status(402)
                    .build();
        }
        var locationObj = locationOpt.get();
        var posts =  locationObj.getPosts();

        posts.forEach((post -> {
            try {
                post.setByteString(convertByteArrayToBase64(post.getImageData1().getBytes(1,(int)post.getImageData1().length())));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(post.getTitle().length()>15)
                post.setTitle(post.getTitle().substring(0,13)+"..");
            if(post.getDescription().length()>50)
                post.setDescription(post.getDescription().substring(0,48)+"..");
        }));

        return posts;
    }

}

package com.movie.app.user.entity.model;

import com.movie.app.movie.entity.enums.CategoryName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String email;
    private String phoneNumber;
    private List<CategoryName> favoriteCategories;
}

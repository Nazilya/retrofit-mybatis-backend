package com.geekbrains.enums;

import lombok.Getter;

public enum CategoryType {
    FOOD("Food", 1),
    ELECTRONICS("Electronics", 2),
    FURNITURE("Furniture", 3),
    BOOK("book", 4);    //Wrong CategoryType (for getCategoryByIdNegativeTests)

    @Getter
    private final String title;
    @Getter
    private final Integer id;

    CategoryType(String title, Integer id) {
        this.title = title;
        this.id = id;
    }
}


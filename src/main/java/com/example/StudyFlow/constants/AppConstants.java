package com.example.StudyFlow.constants;

public final class AppConstants {

    private AppConstants() {}

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "20";
    public static final String DEFAULT_SORT_BY = "createdAt";
    public static final String DEFAULT_SORT_DIRECTION = "desc";

    // Reserved Subdomain Slugs (Cannot be claimed by institutes)
    public static final String[] RESERVED_SLUGS = {
        "admin", "api", "auth", "login", "register", "billing", 
        "support", "www", "app", "mail", "static", "dashboard"
    };
}


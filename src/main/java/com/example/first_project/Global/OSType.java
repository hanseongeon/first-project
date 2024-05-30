package com.example.first_project.Global;

import lombok.Getter;

public enum OSType {
    Window("C:/web","file:///c:/web/"),
    Linux("/home/ubuntu/talk/data","file:/home/ubuntu/talk/data/")
    //
    ;
    @Getter
    private final String path;
    @Getter
            private final String resourceHandler;
    OSType(String path,String resourceHandler) {
        this.path=path;this.resourceHandler=resourceHandler;
    }
    public static OSType getInstance(){
        String osName = System.getProperty("os.name").toLowerCase();
        if(osName.toLowerCase().contains("win"))
            return Window;
        if(osName.toLowerCase().contains("linux"))
            return Linux;
        else {
            System.out.println(osName);
            return null;
        }
    }
}

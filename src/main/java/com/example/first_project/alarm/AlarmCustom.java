package com.example.first_project.alarm;

import com.example.first_project.user.SiteUser;

import java.util.List;

public interface AlarmCustom {

    List<Alarm> findByAcceptUser(SiteUser acceptUser);
}

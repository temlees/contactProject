package com.ohgiraffers.controller;

import com.ohgiraffers.function.Searcher;
import com.ohgiraffers.function.UserAccountManager;
import com.ohgiraffers.function.UserPrefer;
import com.ohgiraffers.dto.UserDTO;

public class ContactController
{
    UserAccountManager userAccountManager = new UserAccountManager();
    Searcher searcher = new Searcher();
    UserPrefer userPrefer = new UserPrefer();

    public UserDTO login()
    {
        return userAccountManager.login();
    }

    public UserDTO signup()
    {
        return userAccountManager.signup();
    }

    public void searchContact(int userCode)
    {
        searcher.search(userCode);
    }

    public void saveUserPrefer(UserDTO userDTO)
    {
        userPrefer.saveUserPrefer(userDTO);
    }
}

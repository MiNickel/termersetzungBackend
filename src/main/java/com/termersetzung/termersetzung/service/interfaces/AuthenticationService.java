package com.termersetzung.termersetzung.service.interfaces;

import java.util.List;

import fr.opensagres.xdocreport.document.json.JSONObject;

public interface AuthenticationService {

    public JSONObject authenticate(String username, String password);

    public List<Object> search(String username);

}
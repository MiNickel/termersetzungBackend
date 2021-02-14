package com.termersetzung.termersetzung.service.implementation;

import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import com.termersetzung.termersetzung.service.interfaces.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.opensagres.xdocreport.document.json.JSONObject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.termersetzung.termersetzung.config.SecurityConstants.*;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private LdapTemplate ldapTemplate;

    public JSONObject authenticate(String username, String password) {

        try {
            List<Object> list = search(username);
            String uid = (String) list.get(0);
            String fullname = (String) list.get(1);
            String typ = (String) list.get(2);
    
            String base = "uid=" + uid + ",ou=" + typ + ",dc=springframework,dc=org";
    
            LdapQuery query = LdapQueryBuilder.query().base(base).where("objectclass").is("person")
                    .and("uid").isPresent();
    
            ldapTemplate.authenticate(query, password);
    
            String jwtToken = "";
            JSONObject json = new JSONObject();
    
            if (typ.equals("professors")) {
                jwtToken = Jwts.builder().setSubject(username)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET_PROF).compact();
            } else if (typ.equals("students")) {
                jwtToken = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_STUDENT).compact();
            }
    
            json.put("username", username);
            json.put("fullname", fullname);
            json.put("token", TOKEN_PREFIX + jwtToken);
            json.put("type", typ);
    
            return json;
        } catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        
        
    }

    public List<Object> search(String username) {
        String BaseDN_Student = "ou=students,dc=springframework,dc=org";
        String BaseDN_Professors = "ou=professors,dc=springframework,dc=org";
        String uid = username;
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "person"));
        filter.and(new EqualsFilter("uid", uid));

        List<Object> name = ldapTemplate.search(BaseDN_Student, filter.encode(), new AttributesMapper<Object>() {
            public Object mapFromAttributes(Attributes attrs) throws NamingException {
                return attrs.get("uid").get();
            }
        });

        if (name.isEmpty()) {
            name = ldapTemplate.search(BaseDN_Professors, filter.encode(), new AttributesMapper<Object>() {
                public Object mapFromAttributes(Attributes attrs) throws NamingException {
                    return attrs.get("uid").get();
                }
            });
            if (!name.isEmpty()) {
                List<Object> fullname = ldapTemplate.search(BaseDN_Professors, filter.encode(), new AttributesMapper<Object>() {
                    public Object mapFromAttributes(Attributes attrs) throws NamingException {
                        return attrs.get("cn").get();
                    }
                });
                name.add(fullname.get(0));
                name.add("professors");
            }
        } else {
            List<Object> fullname = ldapTemplate.search(BaseDN_Student, filter.encode(), new AttributesMapper<Object>() {
                public Object mapFromAttributes(Attributes attrs) throws NamingException {
                    return attrs.get("cn").get();
                }
            });
            name.add(fullname.get(0));
            name.add("students");
        }

        return name;

    }

}
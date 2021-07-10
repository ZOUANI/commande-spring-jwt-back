package com.zs.commandev2.bean;

import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss.SSS")
     @Temporal(TemporalType.TIMESTAMP)
     private Date updatedAt ;
     private String authority ;
     @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss.SSS")
     @Temporal(TemporalType.TIMESTAMP)
     private Date createdAt ;


     public Role(){
       super();
     }

     public Long getId(){
          return this.id;
     }
     public void setId(Long id){
          this.id = id;
     }
     public String getAuthority(){
          return this.authority;
     }
     public void setAuthority(String authority){
          this.authority = authority;
     }
     public Date getCreatedAt(){
          return this.createdAt;
     }
     public void setCreatedAt(Date createdAt){
          this.createdAt = createdAt;
     }
     public Date getUpdatedAt(){
          return this.updatedAt;
     }
     public void setUpdatedAt(Date updatedAt){
          this.updatedAt = updatedAt;
     }
}

